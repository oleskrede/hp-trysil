package no.brightfield.hptrysil.configuration

import no.brightfield.hptrysil.entity.Event
import no.brightfield.hptrysil.util.ALL_HOUSES
import no.brightfield.hptrysil.entity.House
import no.brightfield.hptrysil.entity.Points
import no.brightfield.hptrysil.repository.EventRepository
import no.brightfield.hptrysil.repository.HouseRepository
import no.brightfield.hptrysil.repository.PointsRepository
import no.brightfield.hptrysil.util.GRYFFINDOR
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime

@Configuration
class HpTrysilConfiguration {

    @Bean
    fun databaseInitializer(eventRepository: EventRepository,
                            houseRepository: HouseRepository,
                            pointsRepository: PointsRepository) = ApplicationRunner {
        ALL_HOUSES.forEach { houseName ->
            houseRepository.save(House(houseName))
        }
        val gryffindor = houseRepository.findByName(GRYFFINDOR)
        pointsRepository.save(Points(10, gryffindor!!, "taking a shot"))
        pointsRepository.save(Points(30, gryffindor!!, "excellent spellcasting"))

        eventRepository.save(Event("Arrival","Wizarding Cabin", LocalDateTime.of(2020,2,9,16,0), "5513727-harry-potter-wallpaper.jpg","Welcome to the wondorous wizarding cabin! We hope you enjoy your stay!"))
        eventRepository.save(Event("Quidditch","Quidditch Pitch", LocalDateTime.of(2020,2,10,12,0), "Quidditch_Pitch.jpg","Quidditch is the most popular sport in the wizarding world.Play for your house and win honor and the 2020 Quidditch House Cup!"))
        eventRepository.save(Event("Potions","Dungeons", LocalDateTime.of(2020,2,10,18,0),"223821.jpg", "Potions with Snape! Who can think of a better thing to spend time doing on a saturaday evening!"))
    }
}