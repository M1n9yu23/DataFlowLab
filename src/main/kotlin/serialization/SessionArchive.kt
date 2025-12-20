/**
 *
 * 직렬화 가능한 객체(SessionArchive) 안에 포함된 객체(LiveSocket)가 직렬화를 지원하지 않으면
 * 저장(직렬화) 시점에 예외가 발생한다는 것을 확인하기 위한 예시 코드.
 *
 */

package serialization

import common.storage.JsonFileStorage
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

class LiveSocket

@Serializable
data class SessionArchive(
    @Contextual
    val socket: LiveSocket = LiveSocket(),
)

fun main() {
    val fileName = "session_archive.json"

    val archive = SessionArchive()

    println("--- [1] 원본 데이터 생성 ---")
    println(archive)

    println("\n--- [2] 파일 저장 시도 ---")
    JsonFileStorage.save(archive, fileName)

    println("\n--- [3] 파일 로드 시도 ---")
    val loaded: SessionArchive? = JsonFileStorage.load(fileName)

    if (loaded == archive) {
        println("\n[결과] 성공! 원본과 불러온 데이터가 완벽히 일치합니다.")
        println("로드된 데이터: $loaded")
    } else {
        println("\n[결과] 실패 or 데이터 불일치")
    }
}