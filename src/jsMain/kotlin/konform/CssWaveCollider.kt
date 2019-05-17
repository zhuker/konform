package konform

import com.vg.audio.SampleRange
import jdk.Math
import org.w3c.dom.BEVEL
import org.w3c.dom.CanvasLineJoin
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.browser.window
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.roundToInt

data class Dimension(val width: Int, val height: Int)
/**
 * <code>
 * channelInfo = {
 *      channelNumber: 1,
 *      sampleCount: 406486080,
 *      sampleRate: 48000,
 *      targetSampleRate: 0
 * }
 * </code>
 */

data class ChannelInfo(
    val channelNumber: Int,
    val trackId: String,
    val movieId: String,
    val sampleCount: Long,
    val sampleRate: Int,
    val targetSampleRate: Int
)

//TODO: https://coderwall.com/p/vmkk6a/how-to-make-the-canvas-not-look-like-crap-on-retina
class CssWaveCollider(val waveformSource: WaveformSource, val canvas: HTMLCanvasElement) {
    val waveColor = defaultWaveColor

    private val amplify: Double = 1.0
    private var uiSpp: Double = 0.0
    private var ruler = false
    private var rulerText = false
    private var rulerTextOffset = 20
    private var lastKnownSampleRange: SampleRange? = null
    val _debug = true

    companion object {
        const val defaultWaveColor = "#475164"

        private val _logLut = intLut(128, 6)
        private val _logLut16 = intLut(32768, 6)


        // scale with a chunk of y = sqrt(x) function
        fun sqrtScale(x: Int, m: Int, k: Int): Int {
            return (m * (Math.sqrt(4.0 * k * (k - 1) * x / m + 1) - 1) / (2.0 * (k - 1))).toInt()
        }

        // fun is an optional scaling function
        fun intLut(m: Int, k: Int, scalefun: (Int, Int, Int) -> Int = { a, b, c -> sqrtScale(a, b, c) }): IntArray {
            val lut = IntArray(m + 1)
            lut[0] = 0
            for (i in 1..m) {
                lut[i] = scalefun(i, m, k)
            }
            return lut
        }

        fun avg(min: Int, max: Int): Int = max(abs(min), abs(max))
    }

    @JsName("xToSample")
    fun xToSample(x: Double): Int = ((this.lastKnownSampleRange?.start ?: 0L) + this.uiSpp * x).roundToInt()

    @JsName("sampleToX")
    fun sampleToX(sample: Double): Int = ((sample - (this.lastKnownSampleRange?.start ?: 0L)) / this.uiSpp).toInt()

    fun getDimensions(): Dimension = Dimension(this.canvas.clientWidth, this.canvas.clientHeight)

    @JsName("drawWave")
    fun drawWave(sampleRange: SampleRange, selectionRange: SampleRange?) {
        if (this._debug) {
            this.ruler = true
            this.rulerText = true
            this.rulerTextOffset = 20
        }

        val clientWidth = this.getDimensions().width
        this.uiSpp = sampleRange.duration() / clientWidth.toDouble()

        waveformSource.getWaveImage(
            sampleRange,
            clientWidth,
            { waveData ->
                val precise = waveData.dataSampleRange.containsRange(sampleRange)
//                if (precise) {
//                    _drawPreciseWave(waveData, sampleRange, selectionRange)
//                } else {
                _drawWave(waveData, sampleRange, selectionRange)
//                }
                this.lastKnownSampleRange = sampleRange
            },
            { error ->
                console.log("error", error)
                clear()
            })
    }

    private fun _drawPreciseWave(waveData: IWaveData, sampleRange: SampleRange, selectionRange: SampleRange?) {
        assertTrue(
            "sample range $sampleRange not within data range ${waveData.dataSampleRange}",
            waveData.dataSampleRange.containsRange(sampleRange)
        )

        val dimensions = this.getDimensions()

        if (dimensions.width == 0 || dimensions.height == 0) return

        val wctx = this.getContext()

        var mid = dimensions.height / 2
        val h = this.canvas.clientHeight + 1
        val d = h / 32768.0 * this.amplify * 0.75

        wctx.shadowColor = "transparent"
        wctx.clearRect(0.0, 0.0, dimensions.width.toDouble(), dimensions.height.toDouble())

        wctx.strokeStyle = "rgba(0,0,0,0.9)"
        wctx.lineWidth = 0.5
        wctx.lineJoin = CanvasLineJoin.BEVEL
        wctx.fillStyle = this.waveColor
        wctx.beginPath()


        val sampleOff = waveData.getSampleOffset(sampleRange.start)
        val minmax = intArrayOf(0, 0)
        wctx.moveTo(0.0, h.toDouble())
        if (this.uiSpp >= 1) {
            for (x in 0 until dimensions.width) {
                waveData.getMinMax16(x + sampleOff, minmax)
                var sampleIndex = sampleRange.start + Math.round(x * this.uiSpp)
                var sampleValue = avg(minmax[0], minmax[1])
                sampleValue = _logLut16[sampleValue]
//                sampleValue = this.bezierFilter(sampleIndex, sampleValue)
                wctx.lineTo(x.toDouble(), h - (sampleValue * d))
            }
        } else { // hack for spp < 1 to avoid comb-like waveform
            var x = 0
            while (x / waveData.spp < dimensions.width) {
                waveData.getMinMax16(x / waveData.spp + sampleOff, minmax)
                val sampleIndex = sampleRange.start + x
                var sampleValue = avg(minmax[0], minmax[1])
                if (sampleValue == 0) { //try neighbours
                    waveData.getMinMax16(x / waveData.spp + 1 + sampleOff, minmax)
                    sampleValue = avg(minmax[0], minmax[1])
                    if (sampleValue/*still*/ == 0) {
                        waveData.getMinMax16(x / waveData.spp - 1 + sampleOff, minmax)
                        sampleValue = avg(minmax[0], minmax[1])
                    }
                }
                sampleValue = _logLut16[sampleValue]
//                sampleValue = this.bezierFilter(sampleIndex, sampleValue)
                wctx.lineTo((x / waveData.spp).toDouble(), h - (sampleValue * d))
                x++
            }
        }

        wctx.lineTo(dimensions.width.toDouble(), h.toDouble())
        wctx.closePath()
        wctx.fill()
        wctx.stroke()

        if (selectionRange != null) {
            this.drawSelection(wctx, h, selectionRange)
        }
//        if (this.fades) this.drawFades(wctx)
//        if (this.ruler) this.drawRuler(wctx)
    }


    fun getContext(): CanvasRenderingContext2D {
        this.canvas.width = (this.canvas.clientWidth)
        this.canvas.height = (this.canvas.clientHeight)
//        this.canvas.width = (this.canvas.clientWidth * window.devicePixelRatio).toInt()
//        this.canvas.height = (this.canvas.clientHeight * window.devicePixelRatio).toInt()
        if (!this.canvas.asDynamic().getContext) {
            console.log("cant draw", this.canvas)
        }
        val context = this.canvas.getContext("2d")
        val ctx = (context as? CanvasRenderingContext2D)!!
//        ctx.scale(window.devicePixelRatio, window.devicePixelRatio)
        return ctx
    }


    private fun drawSelection(wctx: CanvasRenderingContext2D, h: Int, selectionRange: SampleRange) {
        val start = selectionRange.start
        val end = selectionRange.end
        wctx.beginPath()
        wctx.fillStyle = "rgba(0,0,0,0.3)"
        val startx = this.sampleToX(start.toDouble())
        val endx = this.sampleToX(end.toDouble())
        wctx.fillRect(startx.toDouble(), 0.0, (endx - startx).toDouble(), h.toDouble())
        wctx.closePath()
        wctx.fill()
    }

    private fun _drawWave(waveData: IWaveData, sampleRange: SampleRange, selectionRange: SampleRange?) {
        val dimensions = this.getDimensions()

        if (dimensions.width == 0 || dimensions.height == 0) return
        this.uiSpp = sampleRange.duration() / dimensions.width.toDouble()

        val wctx = this.getContext()

        val zoom = this.uiSpp / waveData.spp
        var mid = dimensions.height / 2
        val h = dimensions.height + 1
        val d = h / 256.0 * this.amplify
        val dataStartSample = sampleRange.start / waveData.spp

        wctx.shadowColor = "transparent"
        wctx.clearRect(0.0, 0.0, dimensions.width.toDouble(), dimensions.height.toDouble())

        wctx.strokeStyle = "rgba(0,0,0,0.9)"
        wctx.lineWidth = 0.5
        wctx.lineJoin = CanvasLineJoin.BEVEL
        wctx.fillStyle = this.waveColor
        wctx.beginPath()

        wctx.moveTo(0.0, h.toDouble())
        for (x in 0 until dimensions.width) {
            val startidx = ((x * zoom + dataStartSample)).roundToInt()
            val endidx = (((x + 1) * zoom + dataStartSample)).roundToInt()

            val sampleIndex = sampleRange.start + Math.round((x * this.uiSpp))
            var sampleValue = waveData.getMinMax8(startidx, endidx + 1)
            sampleValue = _logLut[sampleValue]
//            console.log("startidx endidx sample", startidx, endidx, sampleValue)
//            sampleValue = this.bezierFilter(sampleIndex, sampleValue)
            wctx.lineTo(x.toDouble(), h - (sampleValue * d))
        }
        val x = dimensions.width
        wctx.lineTo(x.toDouble(), h.toDouble())
        wctx.closePath()
        wctx.fill()
        wctx.stroke()

        if (selectionRange != null) {
            this.drawSelection(wctx, h, selectionRange)
        }
//        if (this.fades) this.drawFades(wctx)
//        if (this.ruler) this.drawRuler(wctx)
    }

    fun clear() {
        val wctx = this.getContext();
        val dimensions = this.getDimensions();
        wctx.clearRect(0.0, 0.0, dimensions.width.toDouble(), dimensions.height.toDouble());
    }
}

typealias WaveDataOnLoad = (IWaveData) -> Unit

interface WaveformSource {
    @JsName("getWaveImage")
    fun getWaveImage(
        sampleRange: SampleRange,
        datasamples: Int,
        onload: WaveDataOnLoad,
        onerror: (Any) -> Unit = {}
    )
}

class WaveImages(movieId: String, channelInfo: ChannelInfo) {
    var onerror: (Any?) -> Unit = {}
    private val sppArray = intArrayOf(194, 3102, 49620, 198480)
    private val images = mutableMapOf<Int, WaveData>()
    fun getWaveImage(wantedSpp: Int, onload: WaveDataOnLoad): WaveData? {
        val spp = this.nearestSpp(wantedSpp)
        val wi: WaveData? = null
        if (spp != 0) {
            if (!this.images.containsKey(spp)) {
//                var url = this.getSppUrl(_spp)
                val wi = WaveData()
                wi.spp = spp
                this.images[spp] = wi
                wi.addOnLoadListener(onload)
                wi._load()
            } else {
                val wi = this.images.getValue(spp)
                if (wi.isLoading) {
                    wi.addOnLoadListener(onload)
                } else {
                    onload(wi)
                }
            }
        }
        return wi
    }

    private fun nearestSpp(wantedSpp: Int): Int {
        return sppArray.last { it <= wantedSpp }
    }

}

interface IWaveData {
    val dataSampleRange: SampleRange
    val dataSamples: Int
    val spp: Int
    fun getMinMax8(startidx: Int, endidx: Int): Int
    fun getMinMax16(sampleOffset: Int, minmax: IntArray)
    fun getSampleOffset(sample: Long): Int
}

class WaveData : IWaveData {
    override fun getMinMax16(sampleOffset: Int, minmax: IntArray) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override val dataSamples: Int get() = TODO("not implemented")
    override var spp: Int = 0
    private val listeners = mutableListOf<WaveDataOnLoad>()
    override val dataSampleRange: SampleRange
        get() = TODO("not implemented")

    override fun getSampleOffset(sample: Long): Int {
        assertTrue("sample not within data range", dataSampleRange.containsSample(sample))
        val spp = this.getDataSpp()
        return spp * (sample - this.dataSampleRange.start).toInt()
    }

    fun getDataSpp(): Int {
        val d = this.dataSampleRange.duration()
        val spp = this.dataSamples / d
        return spp.toInt()
    }

    fun addOnLoadListener(onload: WaveDataOnLoad) {
        listeners.add(onload)
    }

    fun _load() {
    }

    fun containsRange(start: Long, end: Long): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMinMax8(startidx: Int, endidx: Int): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var isLoading: Boolean = true
}

fun assertTrue(msg: String, expected: Boolean) {
    if (!expected) throw IllegalStateException(msg)
}
