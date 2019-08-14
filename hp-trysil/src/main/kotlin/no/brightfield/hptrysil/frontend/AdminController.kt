package no.brightfield.hptrysil.frontend

import no.brightfield.hptrysil.entity.Points
import no.brightfield.hptrysil.repository.HouseRepository
import no.brightfield.hptrysil.repository.PointsRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import java.lang.IllegalArgumentException

@Controller
@RequestMapping("/admin")
class AdminController (private val houseRepository: HouseRepository,
                       private val pointsRepository: PointsRepository) {

    @GetMapping("/points")
    fun pointsForm(model: Model): String {
        model["title"] = "Poengregistrering"
        return "admin_points"
    }

    @PostMapping("/points")
    fun pointsSubmit(model: Model,
                     @RequestParam params: Map<String, String>): String {
        val houseName = params["house"]
                ?: throw IllegalArgumentException("No house name provided")
        val points = params["points"]
                ?: throw IllegalArgumentException("No points value provided")
        val reason = params["reason"]
                ?: throw IllegalArgumentException("No reason provided")
        val house = houseRepository.findByName(houseName)
                ?: throw IllegalArgumentException("Wrong house name provided")

        pointsRepository.save(Points(points.toInt(), house, reason))

        model["title"] = "Poengregistrering"
        return "admin_points"
    }
}