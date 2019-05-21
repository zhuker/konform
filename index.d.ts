import {Long, Pair} from "kotlin"
import {Observable} from "rx"

export namespace com {
    namespace vg {
        namespace audio {
            class SampleRange {
                constructor(start: Long, end: Long)

                start: Long;
                end: Long;
                duration(): Long;

                toString(): string
            }

            class FastAudioIndex {
                subIndex(startSample: Long, endSample: Long): FastAudioIndex

                toTransferable(): TransferableIndex
            }

            class TransferableIndex {
            }

            function indexFromTransferable(tidx: TransferableIndex): FastAudioIndex

            function matchOffsets(index1: FastAudioIndex, index2: FastAudioIndex): Long[]

            function bestOffset(matchOffsets: Long[]): Long

        }
    }
}

export namespace org {
    namespace jcodec {
        namespace common {
            namespace model {
                enum ChannelLabel {
                    MONO, STEREO_LEFT, STEREO_RIGHT, LEFT_TOTAL, RIGHT_TOTAL, FRONT_LEFT, FRONT_RIGHT, CENTER, LFE, REAR_LEFT, REAR_RIGHT, FRONT_CENTER_LEFT, FRONT_CENTER_RIGHT, REAR_CENTER, SIDE_LEFT, SIDE_RIGHT
                }

                namespace ChannelLabel {
                    export function values(): ChannelLabel[];

                    export function valueOf(str: String): ChannelLabel | undefined;
                }
            }
        }
        namespace codecs {
            namespace wav {
                import ChannelLabel = org.jcodec.common.model.ChannelLabel;

                class WavHeader {
                    getSampleCount(): Long

                    getSampleRate(): number

                    channelLabels: ChannelLabel[];
                    dataOffset: number;
                    dataSize: Long;
                }
            }
        }
    }
}

export namespace konform {
    import WavHeader = org.jcodec.codecs.wav.WavHeader;
    import SampleRange = com.vg.audio.SampleRange;
    import ChannelLabel = org.jcodec.common.model.ChannelLabel;
    import FastAudioIndex = com.vg.audio.FastAudioIndex;

    class Dimension {
        width: number;
        height: number;
    }

    class ChannelInfo {
        constructor(channelNumber: number, trackId: String, movieId: String, sampleCount: Long, sampleRate: number, targetSampleRate: number)

        channelNumber: number;
        trackId: String;
        movieId: String;
        sampleCount: Long;
        sampleRate: number;
        targetSampleRate: number;
    }

    interface IWaveData {
        dataSampleRange: com.vg.audio.SampleRange
        dataSamples: number
        spp: number

        getMinMax8(startidx: number, endidx: number): number

        getMinMax16(sampleOffset: number, minmax: Int32Array): void

        getSampleOffset(sample: Long): number
    }


    interface WaveformSource {
        getWaveImage(
            sampleRange: SampleRange,
            datasamples: number,
            onload: (IWaveData) => void,
            onerror: (any) => void
        )
    }

    class WaveformSourceImpl implements WaveformSource {
        constructor(mono: MonoSource)

        getWaveImage(sampleRange: SampleRange, datasamples: number, onload: (IWaveData) => void, onerror: (any) => void);
    }


    class CssWaveCollider {
        constructor(waveformSource: WaveformSource, canvas: HTMLCanvasElement)

        waveColor: string;

        xToSample(offsetX: number): number

        sampleToX(sample: number): number

        drawWave(sampleRange: SampleRange, selectionRange?: SampleRange): void

        clear(): void
    }


    function readSamples(wavHeader: WavHeader, file: Blob, samplesPerChunk: number, startSample: number): Observable<Pair<SampleRange, Float32Array>>;

    function wavHeader(file: Blob): Observable<org.jcodec.codecs.wav.WavHeader>;

    function waveDataFromWav(file: Blob, wavHeader: WavHeader, samplesPerPixel: number): Observable<IWaveData>;

    function waveDataFromMonoSource(mono: MonoSource, samplesPerPixel: number): Observable<IWaveData>;

    function indexFromMonoSource(mono: MonoSource, sampleRate: number, onProgress?: (sample: Long) => void): Observable<FastAudioIndex>;

    function indexToBlob(index: FastAudioIndex): Blob ;

    export class SampleBuffer {
        public range: SampleRange;
        public samples: Float32Array[];

        constructor(range: SampleRange, samples: Float32Array[])
    }

    export interface MonoSource {
        /** SampleBuffer MUST have one and only one Float32Array in SampleBuffer.samples[] even if underlying source is stereo, 5.1, etc .
         * Implementation should decide whether to downmix to mono or extract any one channel.
         * Sample values MUST be in -1f..1f range */
        readMono(samplesPerChunk: number, startSample: number): Observable<SampleBuffer>;

    }

    export interface SampleSource extends MonoSource {
        /** SampleBuffer MUST have two and only two Float32Array-s in SampleBuffer.samples[] even if underlying source is mono, 5.1, etc .
         * Implementation should decide whether to downmix to stereo or extract any two channels.
         * Sample values MUST be in -1f..1f range */
        readStereo(samplesPerChunk: number, startSample: number): Observable<SampleBuffer>;
    }

    export class WavSource implements SampleSource {
        constructor(file: File, wav: WavHeader)

        public readMono(samplesPerChunk: number, startSample: number): Observable<SampleBuffer>

        public readStereo(samplesPerChunk: number, startSample: number): Observable<SampleBuffer>
    }


    export class StereoDownmixSource implements SampleSource {
        constructor(sources: MonoSource[], labels: ChannelLabel[])

        public readMono(samplesPerChunk: number, startSample: number): Observable<SampleBuffer>

        public readStereo(samplesPerChunk: number, startSample: number): Observable<SampleBuffer>
    }

    export class ResampleFilter implements MonoSource {
        constructor(sources: MonoSource, srcRate: number, dstRate: number)

        public readMono(samplesPerChunk: number, startSample: number): Observable<SampleBuffer>
    }

}
