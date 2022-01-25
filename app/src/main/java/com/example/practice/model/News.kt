package com.example.practice.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.threeten.bp.LocalDate
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoUnit
import java.util.Calendar
import java.util.TimeZone

@Serializable
data class News(
    @SerialName("categoriesId")
    val categoriesId: List<Int>,
    @SerialName("dateEnd")
    val dateEnd: Long,
    @SerialName("dateStart")
    val dateStart: Long,
    @SerialName("description")
    val description: String,
    @SerialName("email")
    val email: String,
    @SerialName("followers")
    val followers: List<Follower>,
    @SerialName("id")
    val id: Int,
    @SerialName("imagesResourcesNames")
    val imagesResourcesNames: List<String>,
    @SerialName("location")
    val location: String,
    @SerialName("owner")
    val owner: String,
    @SerialName("phoneNumbers")
    val phoneNumbers: List<String>,
    @SerialName("posterResourceName")
    val posterResourceName: String,
    @SerialName("site")
    val site: String,
    @SerialName("title")
    val title: String,
    var isRead: Boolean = false
) {
    private fun getDateStart(): LocalDate {
        return createLocalDateForTimestamp(dateStart)
    }

    private fun getDateEnd(): LocalDate {
        return createLocalDateForTimestamp(dateEnd)
    }

    private fun createLocalDateForTimestamp(time: Long): LocalDate {
        val calendar = Calendar.getInstance(TimeZone.getDefault())
        calendar.timeInMillis = time * 1000
        return LocalDate.of(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    fun getDateFormatted(): String {
        val days = ChronoUnit.DAYS.between(LocalDate.now(), getDateStart())
        val dtf = DateTimeFormatter.ofPattern("dd.MM")
        return "Осталось $days дней (${getDateStart().format(dtf)} - ${getDateEnd().format(dtf)})"
    }
}

@Serializable
data class Follower(
    @SerialName("avatarResourceName")
    val avatarResourceName: String,
    @SerialName("id")
    val id: Int,
    @SerialName("name")
    val name: String
)
