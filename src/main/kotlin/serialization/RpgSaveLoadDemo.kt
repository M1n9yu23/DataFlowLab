package serialization

import common.logger.printLog
import common.storage.JsonFileStorage

fun main() {
    val saveFileName = "rpg_party.json"

    val party: List<RpgAvatar> = listOf(
        RpgAvatar(
            attackPower = 50,
            raceName = "DJ",
            equippedWeapons = listOf("short bow", "steel sword", "fairy dust")
        ),
        RpgAvatar(
            attackPower = 200,
            raceName = "MG",
            equippedWeapons =  listOf("bare fists", "heavy axe")
        ),
        RpgAvatar(
            attackPower = 120,
            raceName = "KB",
            equippedWeapons = listOf("spell book", "cloak of vanish")
        )
    )

    printLog<RpgAvatar>("\n--- [1] 원본 파티 생성 ---")
    party.forEach {
        println(it.summaryText())
    }

    printLog<RpgAvatar>("\n--- [2] 파일 저장 시도 ---")
    JsonFileStorage.save(
        data = party,
        fileName = saveFileName
    )

    printLog<RpgAvatar>("\n--- [3] 파일 로드 시도 ---")
    val loadedParty: List<RpgAvatar>? = JsonFileStorage.load(saveFileName)

    if(loadedParty == null) {
        printLog<RpgAvatar>("로드 실패: loadedParty == null")
        return
    }

    printLog<RpgAvatar>("\n--- [4] 로드 결과 검증 ---")
    val isSame = loadedParty == party
    printLog<RpgAvatar>("원본/로드 동일 여부: $isSame")

    println("First race: ${loadedParty.getOrNull(0)?.raceName ?: "N/A"}")
    println("Second race: ${loadedParty.getOrNull(1)?.raceName ?: "N/A"}")
    println("Third race: ${loadedParty.getOrNull(2)?.raceName ?: "N/A"}")
}