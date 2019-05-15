import kotlin.js.Promise

external interface OfflineAudioCompletionEvent {
    val renderedBuffer: AudioBuffer
}

// https://developer.mozilla.org/en-US/docs/Web/API/OfflineAudioContext
external class OfflineAudioContext(numberOfChannels: Int, length: Int, sampleRate: Int) : BaseAudioContext {
    override val destination: AudioDestinationNode
        get() = definedExternally

    override fun createBufferSource(): AudioBufferSourceNode
    override fun createBuffer(numOfchannels: Int, length: Int, sampleRate: Int): AudioBuffer
    override fun startRendering(): Promise<AudioBuffer>
    var oncomplete: (e: OfflineAudioCompletionEvent) -> Unit
}


// https://developer.mozilla.org/en-US/docs/Web/API/AudioBuffer
external interface AudioBuffer {
    fun getChannelData(channel: Int): FloatArray
    fun copyToChannel(channelData: FloatArray, channel: Int, startInChannel: Int = definedExternally)
    val sampleRate: Int
    val length: Int
    val numberOfChannels: Int
}

//https://developer.mozilla.org/en-US/docs/Web/API/AudioBufferSourceNode
external interface AudioBufferSourceNode : AudioNode, AudioScheduledSourceNode {
    var buffer: AudioBuffer?
}

// https://developer.mozilla.org/en-US/docs/Web/API/AudioScheduledSourceNode
external interface AudioScheduledSourceNode {
    fun start(whenTime: Double, offset: Double = definedExternally, duration: Double = definedExternally)
}

//https://developer.mozilla.org/en-US/docs/Web/API/AudioNode
external interface AudioNode {
    fun connect(destination: AudioNode, outputIndex: Int = definedExternally);
}

//https://developer.mozilla.org/en-US/docs/Web/API/AudioDestinationNode
external interface AudioDestinationNode : AudioNode {
}

external interface BaseAudioContext {
    val destination: AudioDestinationNode
    fun createBuffer(numOfchannels: Int, length: Int, sampleRate: Int): AudioBuffer
    fun startRendering(): Promise<AudioBuffer>
    fun createBufferSource(): AudioBufferSourceNode
}