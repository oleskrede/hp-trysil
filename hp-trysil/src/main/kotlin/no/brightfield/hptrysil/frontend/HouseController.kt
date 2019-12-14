package no.brightfield.hptrysil.frontend

import no.brightfield.hptrysil.renderer.renderHouse
import no.brightfield.hptrysil.repository.HouseRepository
import no.brightfield.hptrysil.repository.PointsRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class HouseController (private val houseRepository: HouseRepository,
                       private val pointsRepository: PointsRepository) {

    @GetMapping("/house/{name}")
    fun house(@PathVariable name: String, model: Model): String {
        val house = houseRepository.findByName(name)
                ?: throw IllegalArgumentException("Wrong house name provided")
        val pointList = pointsRepository.findAllByHouseOrderByAddedAtAsc(house)
        val pointsSum = pointList.sumBy { it.value }
        val renderedHouse = renderHouse(house, pointsSum)
        model["title"] = "Magic in the Woods - " + house.name + " homeroom"
        model["pointList"] = pointList
        model["house"] = renderedHouse
        return "house"
    }

}