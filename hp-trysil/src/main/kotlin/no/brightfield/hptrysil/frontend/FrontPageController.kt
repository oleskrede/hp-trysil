package no.brightfield.hptrysil.frontend

import no.brightfield.hptrysil.entity.House
import no.brightfield.hptrysil.renderer.RenderedHouse
import no.brightfield.hptrysil.repository.HouseRepository
import no.brightfield.hptrysil.repository.PointsRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping

@Controller
class FrontPageController(private val houseRepository: HouseRepository,
                          private val pointsRepository: PointsRepository) {

    @GetMapping("/")
    fun frontpage(model: Model): String {
        model["activeHome"] = ""
        model["title"] = "Harry Potter Partay"
        val houses = houseRepository.findAllByOrderByNameAsc().toList()
        val housePoints = houses
                .map { Pair(it, pointsRepository.findAllByHouse(it).sumBy { point -> point.value }) }
        model["houseRows"] = createHouseRows(housePoints)
        return "frontpage"
    }

    @GetMapping("/achievements")
    fun achievements(model: Model): String {
        model["activeAchievements"] = ""
        model["title"] = "Achievements"
        return "achievements"
    }

    @GetMapping("/practicalinfo")
    fun practicalinfo(model: Model): String {
        model["activePracticalInfo"] = ""
        model["title"] = "Practical Information"
        return "practicalInfo"
    }

    fun createHouseRows(houses: List<Pair<House, Int>>) : List<HouseRow> {
        return houses.chunked(2).map { HouseRow(it) }
    }

    class HouseRow(val h: List<Pair<House, Int>>) {
        val houses = h.map { RenderedHouse(it.first.name, it.second) }
    }
}