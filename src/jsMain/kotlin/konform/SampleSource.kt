package konform

import OfflineAudioContext
import Rx.Observable
import com.vg.audio.SampleRange
import jdk.Math
import org.jcodec.codecs.wav.WavHeader
import org.jcodec.common.model.ChannelLabel
import org.jcodec.common.model.ChannelLabel.*
import org.w3c.files.File
import kotlin.math.roundToInt
import kotlin.math.roundToLong

class SampleBuffer(val range: SampleRange, val samples: Array<FloatArray>)

interface MonoSource {
    /** SampleBuffer MUST have one and only one Float32Array in SampleBuffer.samples[] even if underlying source is stereo, 5.1, etc .
     * Implementation should decide whether to downmix to mono or extract any one channel.
     * Sample values MUST be in -1f..1f range */
    @JsName("readMono")
    fun readMono(samplesPerChunk: Int, startSample: Int): Observable<SampleBuffer>

    // length in samples
    val length: Long
}

interface SampleSource : MonoSource {
    /** SampleBuffer MUST have two and only two Float32Array-s in SampleBuffer.samples[] even if underlying source is mono, 5.1, etc .
     * Implementation should decide whether to downmix to stereo or extract any two channels.
     * Sample values MUST be in -1f..1f range */
    @JsName("readStereo")
    fun readStereo(samplesPerChunk: Int, startSample: Int): Observable<SampleBuffer>;
}

class WavSource(private val file: File, private val wav: WavHeader) : SampleSource {

    override fun readMono(samplesPerChunk: Int, startSample: Int): Observable<SampleBuffer> {
        return wav.readSamples(this.file, samplesPerChunk, startSample)
    }

    override fun readStereo(samplesPerChunk: Int, startSample: Int): Observable<SampleBuffer> {
        return wav.readSamples(this.file, samplesPerChunk, startSample)
            .map { sb -> SampleBuffer(sb.range, arrayOf(sb.samples[0], sb.samples[0])) };
    }

    override val length: Long
        get() = wav.getSampleCount()

}

private const val GAIN = 0.7f

private fun clamp(sum: FloatArray, mul: FloatArray): FloatArray {
    for (i in sum.indices) {
        val s = sum[i];
        val m = mul[i];
        sum[i] = Math.max(-1f, Math.min(1f, (s - m)));
    }
    return sum;
}

fun downmix(lcount: Int, rcount: Int, labels: Array<ChannelLabel>, sampleBuffers: Array<SampleBuffer>): SampleBuffer {
    // console.log("downmix", sampleBuffers);
    val sampleCount = sampleBuffers[0].samples[0].size;
    val lSum = FloatArray(sampleCount)
    val rSum = FloatArray(sampleCount)
    val lMul = FloatArray(sampleCount) { 1f }
    val rMul = FloatArray(sampleCount) { 1f }
    for (i in sampleBuffers.indices) {
        val label = labels[i];
        val sampleBuffer = sampleBuffers[i];
        val samples = sampleBuffer.samples[0];
        when (label) {
            MONO,
            LFE,
            CENTER,
            REAR_CENTER -> {
                for (j in samples.indices) {
                    val sample = samples[j] * GAIN;
                    lSum[j] = lSum[j] + (sample);
                    lMul[j] = lMul[j] + (sample);
                    rSum[j] = rSum[j] + (sample);
                    rMul[j] = rMul[j] + (sample);
                }
            }
            LEFT_TOTAL,
            STEREO_LEFT,
            FRONT_LEFT -> {
                for (j in samples.indices) {
                    val sample = samples[j]
                    lSum[j] = lSum[j] + (sample);
                    lMul[j] = lMul[j] + (sample);
                }
            }
            FRONT_RIGHT,
            RIGHT_TOTAL,
            STEREO_RIGHT -> {
                for (j in samples.indices) {
                    val sample = samples[j]
                    rSum[j] = rSum[j] + (sample);
                    rMul[j] = rMul[j] + (sample);
                }
            }
            SIDE_LEFT,
            REAR_LEFT,
            FRONT_CENTER_LEFT -> {
                for (j in samples.indices) {
                    val sample = samples[j] * GAIN;
                    lSum[j] = lSum[j] + (sample);
                    lMul[j] = lMul[j] + (sample);
                }
            }
            SIDE_RIGHT,
            FRONT_CENTER_RIGHT,
            REAR_RIGHT -> {
                for (j in samples.indices) {
                    val sample = samples[j] * GAIN;
                    rSum[j] = rSum[j] + (sample);
                    rMul[j] = rMul[j] + (sample);
                }
            }

        }
    }

    val outLeft = if (lcount > 1) clamp(lSum, lMul) else lSum;
    val outRight = if (rcount > 1) clamp(rSum, rMul) else rSum;
    // console.log("downmix ok", sampleBuffers, outLeft, outRight);

    return SampleBuffer(sampleBuffers[0].range, arrayOf(outLeft, outRight));
}

private val leftlabels =
    setOf(MONO, LFE, CENTER, REAR_CENTER, LEFT_TOTAL, STEREO_LEFT, FRONT_LEFT, SIDE_LEFT, REAR_LEFT, FRONT_CENTER_LEFT)

private val rightlabels = setOf(
    MONO,
    LFE,
    CENTER,
    REAR_CENTER,
    RIGHT_TOTAL,
    STEREO_RIGHT,
    FRONT_RIGHT,
    SIDE_RIGHT,
    REAR_RIGHT,
    FRONT_CENTER_RIGHT
)

class StereoDownmixSource(private val sources: Array<MonoSource>, private val labels: Array<ChannelLabel>) :
    SampleSource {
    private val lcount: Int = labels.count { leftlabels.contains(it) }
    private val rcount: Int = labels.count { rightlabels.contains(it) };

    override fun readMono(samplesPerChunk: Int, startSample: Int): Observable<SampleBuffer> {
        TODO("not implemented")
    }

    override fun readStereo(samplesPerChunk: Int, startSample: Int): Observable<SampleBuffer> {
        return Observable.fromArray(sources).concatMap { source ->
            source.readMono(samplesPerChunk, startSample).take(1)
        }.toArray().map { sampleBuffers -> downmix(lcount, rcount, labels, sampleBuffers) }
    }

    override val length: Long
        get() = sources.maxBy { it.length }?.length ?: 0L

}

class ResampleFilter(private val src: MonoSource, private val srcRate: Int, private val dstRate: Int) : MonoSource {
    override fun readMono(samplesPerChunk: Int, startSample: Int): Observable<SampleBuffer> {
        val srcSamplesPerChunk = duration(samplesPerChunk)
        val srcStartSample = pos(startSample.toLong())
        var hackStart = startSample.toLong()
        console.log("resample", srcRate, dstRate, samplesPerChunk, startSample)
        return src.readMono(srcSamplesPerChunk, srcStartSample.toInt()).concatMap { sb ->
            Observable.create<FloatArray> { emitter ->
                val offlineCtx = OfflineAudioContext(1, samplesPerChunk, dstRate);
                // https://stackoverflow.com/questions/27598270/resample-audio-buffer-from-44100-to-16000
                val sourceAudioBuffer = offlineCtx.createBuffer(1, sb.samples[0].size, srcRate);
//                console.log("sourceAudioBuffer", sourceAudioBuffer);
                sourceAudioBuffer.copyToChannel(sb.samples[0], 0)
                val source = offlineCtx.createBufferSource();
                source.buffer = sourceAudioBuffer;
                source.connect(offlineCtx.destination);
                source.start(0.0);
                offlineCtx.oncomplete = { e ->
                    val resampled = e.renderedBuffer;
                    val leftFloat32Array = resampled.getChannelData(0);
//                    console.log("resampled", resampled, leftFloat32Array);
                    emitter.onNext(leftFloat32Array)
                    emitter.onCompleted()
                };
                offlineCtx.startRendering();
            }.map { resampled ->
                hackStart += resampled.size
                val start = hackStart
                val end = hackStart + resampled.size - 1
                val range = SampleRange(start, end)
                if (range.duration() != resampled.size.toLong()) {
                    throw IllegalStateException("resample bug ${range.duration()} != ${resampled.size}")
                }
                SampleBuffer(range, arrayOf(resampled))
            }
        }
    }

    // returns position in src by position in resampled source
    private fun pos(rPos: Long): Long {
        return (rPos.toDouble() * srcRate / dstRate).roundToLong()
    }

    // returns position in resampled by position in src
    private fun rpos(pos: Long): Long {
        return Math.ceil(pos.toDouble() * dstRate / srcRate).toLong()
    }


    // returns duration in src by duration in resampled source
    private fun duration(rSamples: Int): Int {
        return (rSamples.toDouble() * srcRate / dstRate).roundToInt()
    }

    override val length: Long
        get() = src.length * dstRate.toLong() / srcRate.toLong()
}