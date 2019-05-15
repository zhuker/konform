package js.lang

class StringBuffer {
    private var str: String = ""
    fun append(simpleName: String?) {
        str += (simpleName ?: "null")
    }

    fun append(i: Int) {
        str += "$i"
    }

    override fun toString(): String = str
}