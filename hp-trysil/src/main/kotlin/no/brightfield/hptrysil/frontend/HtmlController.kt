package no.brightfield.hptrysil.frontend

import no.brightfield.hptrysil.entity.House
import no.brightfield.hptrysil.repository.HouseRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.lang.IllegalArgumentException

@Controller
class HtmlController (private val houseRepository: HouseRepository) {

    @GetMapping("/")
    fun frontpage(model: Model): String {
        model["title"] = "Harry Potter partay"
        model["houses"] = houseRepository.findAllByOrderByNameAsc().map { it.render(72) }
        return "frontpage"
    }

    @GetMapping("/house/{name}")
    fun house(@PathVariable name: String, model: Model): String {
        val house = houseRepository.findByName(name)
                ?.render(72)
                ?: throw IllegalArgumentException("Wrong house name provided")
        model["title"] = house.name
        model["house"] = house
        return "house"
    }

    fun House.render(points: Int) = RenderedHouse(
            name,
            points
    )

    data class RenderedHouse(
            val name: String,
            val points: Int
    )
}