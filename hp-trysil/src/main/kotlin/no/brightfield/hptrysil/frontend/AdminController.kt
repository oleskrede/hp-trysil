package no.brightfield.hptrysil.frontend

import no.brightfield.hptrysil.entity.Points
import no.brightfield.hptrysil.repository.HouseRepository
import no.brightfield.hptrysil.repository.PlayerRepository
import no.brightfield.hptrysil.repository.PointsRepository
import no.brightfield.hptrysil.util.ALL_HOUSES
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
@RequestMapping("/admin")
class AdminController (private val houseRepository: HouseRepository,
                       private val pointsRepository: PointsRepository,
                       private val playerRepository: PlayerRepository) {

    @GetMapping("/points")
    fun pointsForm(model: Model): String {
        initAdminPointsModel(model)
        return "admin_points"
    }

    @PostMapping("/points")
    fun pointsSubmit(model: Model,
                     @RequestParam params: Map<String, String>): String {
        val houseName = params["houseField"]
                ?: throw IllegalArgumentException("No house name provided")
        val points = params["pointsField"]?.toInt()
                ?: throw IllegalArgumentException("No points value provided")
        val reason = params["reasonField"]
                ?: throw IllegalArgumentException("No reason provided")
        val house = houseRepository.findByName(houseName)
                ?: throw IllegalArgumentException("Wrong house name provided")
        val playerName = params["playerField"]
                ?: throw IllegalArgumentException("No player name provided")
        val player = playerRepository.findByName(playerName)
                ?: throw IllegalArgumentException("Wrong player name provided")

        pointsRepository.save(Points(points, house, reason, player))

        initAdminPointsModel(model)
        model["saveResult"] = RenderedSaveResult(houseName, playerName, points, reason)
        return "admin_points"
    }

    private fun initAdminPointsModel(model: Model) {
        model["activeAdmin"] = ""
        model["title"] = "Poengregistrering"
        model["houses"] = ALL_HOUSES
        model["wizards"] = playerRepository.findAll()
    }

    data class RenderedSaveResult(
            val house: String,
            val player: String,
            val points: Int,
            val reason: String
    )
}