package no.brightfield.hptrysil.frontend

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/admin")
class AdminController () {

    @GetMapping("/points")
    fun points(model: Model): String {
        model["title"] = "Poengregistrering"
        return "admin_points"
    }
}