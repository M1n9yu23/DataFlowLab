package common.storage

import common.logger.printLog
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.io.FileNotFoundException

object JsonFileStorage {
    @PublishedApi
    internal val jsonConfig = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }

    inline fun <reified T> save(data: T, fileName: String) {
        runCatching {
            val jsonString = jsonConfig.encodeToString(data)

            File(fileName).writeText(jsonString)
        }.onSuccess {
            this.printLog("파일 저장 성공: $fileName (Ser: ${T::class.simpleName})")
        }.onFailure {
            this.printLog("파일 저장 실패: ${it.message}")
        }
    }

    inline fun <reified T> load(fileName: String): T? {
        return runCatching {
            val file = File(fileName)
            if (!file.exists()) throw FileNotFoundException("파일이 없습니다.")

            val jsonString = file.readText()
            jsonConfig.decodeFromString<T>(jsonString)
        }.onSuccess {
            this.printLog("파일 로드 성공: $fileName")
        }.onFailure {
            this.printLog("파일 로드 실패: ${it.message}")
        }.getOrNull()
    }
}
