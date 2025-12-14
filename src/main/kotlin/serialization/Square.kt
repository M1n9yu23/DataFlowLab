package serialization

import common.storage.JsonFileStorage
import kotlinx.serialization.Serializable

@Serializable
data class Square(
    val width: Int,
    val height: Int,
)

fun main() {
    val fileName = "square_data.json"

    val originalSquare = Square(width = 50, height = 20)
    println("--- [1] 원본 데이터 생성 ---")
    println(originalSquare)

    println("\n--- [2] 파일 저장 시도 ---")
    JsonFileStorage.save(originalSquare, fileName)

    println("\n--- [3] 파일 로드 시도 ---")
    val loadedSquare: Square? = JsonFileStorage.load(fileName)

    if (loadedSquare == originalSquare) {
        println("\n[결과] 성공! 원본과 불러온 데이터가 완벽히 일치합니다.")
        println("로드된 데이터: $loadedSquare")
    } else {
        println("\n[결과] 실패 or 데이터 불일치")
    }
}