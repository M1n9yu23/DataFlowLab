package serialization

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Memo(
    @Transient
    val currentId: String = "",
    val title: String,
)
