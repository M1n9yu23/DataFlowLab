package serialization

import common.logger.printLog
import kotlinx.serialization.Serializable

@Serializable
data class RpgAvatar(
    val attackPower: Int,
    val raceName: String,
    val equippedWeapons: List<String>,
) {
    fun summaryText(): String {
        val weaponsText = equippedWeapons.joinToString(separator = ", ")
        this.printLog("summaryText() call: power=$attackPower race=$raceName weapons=$weaponsText")
        return "RpgAvatar(power=$attackPower, race=$raceName, weapons=[$weaponsText])"
    }
}

