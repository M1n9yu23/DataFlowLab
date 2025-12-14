package common.logger

// 필요할 때 모든 로그를 끌 때 사용
object AppLogger {
    var isDebugMode = true
}

fun Any.printLog(message: String) {
    if (!AppLogger.isDebugMode) return

    val tag = this::class.simpleName ?: "익명"

    println("[$tag] $message")
}

// 타입이 없는 공간에서 사용할 때 사용
inline fun <reified T> printLog(message: String) {
    if (!AppLogger.isDebugMode) return

    val tag = T::class.simpleName ?: "정적"

    println("[$tag] $message")
}