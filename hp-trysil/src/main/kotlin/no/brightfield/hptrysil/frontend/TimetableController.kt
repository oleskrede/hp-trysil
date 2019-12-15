package no.brightfield.hptrysil.frontend

import no.brightfield.hptrysil.repository.EventRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import java.time.format.DateTimeFormatter

@Controller
class TimetableController(private val eventRepository: EventRepository) {

    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    @GetMapping("/timetable")
    fun timetable(model: Model): String {
        model["activeTimetable"] = ""
        model["title"] = "Timetable"
        val events = eventRepository.findAllByOrderByTimeAsc().toList()
        model["eventRows"] = events
                .map { RenderedEvent(it.name, it.location, it.time.format(formatter), it.imageSource, it.description) }

        return "timetable"
    }

    data class RenderedEvent(
            val name: String,
            val location: String,
            val time: String,
            val imageSource: String,
            val description: String
    )
}