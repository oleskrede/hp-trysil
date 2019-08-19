package no.brightfield.hptrysil.frontend

import no.brightfield.hptrysil.entity.House
import no.brightfield.hptrysil.repository.HouseRepository
import no.brightfield.hptrysil.repository.PointsRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.lang.IllegalArgumentException

@Controller
class HtmlController (private val houseRepository: HouseRepository,
                      private val pointsRepository: PointsRepository) {

    @GetMapping("/")
    fun frontpage(model: Model): String {
        model["title"] = "Harry Potter partay"
        val houses = houseRepository.findAllByOrderByNameAsc().toList()
        val housesAndPoints = houses
                .map { Pair(it, pointsRepository.findAllByHouseOrderByAddedAtAsc(it)
                        .sumBy { point -> point.value }) }
        model["houseRows"] = createHouseRows(housesAndPoints)
        return "frontpage"
    }

    @GetMapping("/house/{name}")
    fun house(@PathVariable name: String, model: Model): String {
        val house = houseRepository.findByName(name)
                ?: throw IllegalArgumentException("Wrong house name provided")
        val points = pointsRepository.findAllByHouseOrderByAddedAtAsc(house)
                .sumBy { it.value }
        val renderedHouse = house?.render(points)
        val pointList = pointsRepository.findAllByHouseOrderByAddedAtAsc(house)
        model["title"] = "Magic in the Woods - " + house.name + " homeroom"
        model["pointList"] = pointList
        model["house"] = renderedHouse
        return "house"
    }

    @GetMapping("/house/{name}/points")
    fun points(@PathVariable name: String, model: Model): String {
        val house = houseRepository.findByName(name)
                ?: throw IllegalArgumentException("Wrong house name provided")
        val points = pointsRepository.findAllByHouseOrderByAddedAtAsc(house)
        model["title"] = house.name
        model["points"] = points
        return "points"
    }

    fun createHouseRows(houses: List<Pair<House, Int>>) : List<HouseRow> {
        return houses.chunked(2).map { HouseRow(it) }
    }

    class HouseRow(val h: List<Pair<House, Int>>) {
        val houses = h.map { RenderedHouse(it.first.name, it.second) }
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