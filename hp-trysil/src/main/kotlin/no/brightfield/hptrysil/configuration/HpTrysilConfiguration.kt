package no.brightfield.hptrysil.configuration

import no.brightfield.hptrysil.entity.Event
import no.brightfield.hptrysil.entity.House
import no.brightfield.hptrysil.entity.Player
import no.brightfield.hptrysil.entity.Points
import no.brightfield.hptrysil.repository.EventRepository
import no.brightfield.hptrysil.repository.HouseRepository
import no.brightfield.hptrysil.repository.PlayerRepository
import no.brightfield.hptrysil.repository.PointsRepository
import no.brightfield.hptrysil.util.*
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime

@Configuration
class HpTrysilConfiguration {

    @Bean
    fun databaseInitializer(eventRepository: EventRepository,
                            houseRepository: HouseRepository,
                            pointsRepository: PointsRepository,
                            playerRepository: PlayerRepository) = ApplicationRunner {
        ALL_HOUSES.forEach { houseName ->
            houseRepository.save(House(houseName))
        }
        val gryffindor = houseRepository.findByName(GRYFFINDOR)!!
        val ravenclaw = houseRepository.findByName(RAVENCLAW)!!
        val slytherin = houseRepository.findByName(SLYTHERIN)!!
        val hufflepuff = houseRepository.findByName(HUFFELPUFF)!!

        val alex = Player("Alex", "A sneaky halfblood bastard. Known for his long, but thin wand.", slytherin)
        playerRepository.save(alex)
        val ole = Player("Ole", "A wize old wizard, with the purest blood.", ravenclaw)
        playerRepository.save(ole)
        val monika = Player("Monika", "An old garden gnome, with a good heart and a crooked nose.", hufflepuff)
        playerRepository.save(monika)
        val harry = Player("Harry", "The choosen one.", gryffindor)
        playerRepository.save(harry)
        val voldemort = Player("voldem***", "Whose name must not be spoken...", slytherin)
        playerRepository.save(voldemort)
        val poopyB = Player("Mr. Poopybutthole", "An old family friend", hufflepuff)
        playerRepository.save(poopyB)

        pointsRepository.save(Points(10, gryffindor, "taking a shot", harry))
        pointsRepository.save(Points(30, gryffindor, "excellent spellcasting", harry))
        pointsRepository.save(Points(5, slytherin, "excellent spellcasting", alex))
        pointsRepository.save(Points(10, slytherin, "going commando", voldemort))
        pointsRepository.save(Points(20, slytherin, "funny joke", alex))
        pointsRepository.save(Points(50, hufflepuff, "excellent spellcasting", monika))
        pointsRepository.save(Points(30, ravenclaw, "excellent spellcasting", ole))


        eventRepository.save(Event("Arrival","Wizarding Cabin", LocalDateTime.of(2020,2,9,16,0), "5513727-harry-potter-wallpaper.jpg","Welcome to the wondorous wizarding cabin! We hope you enjoy your stay!"))
        eventRepository.save(Event("Quidditch","Quidditch Pitch", LocalDateTime.of(2020,2,10,12,0), "Quidditch_Pitch.jpg","Quidditch is the most popular sport in the wizarding world.Play for your house and win honor and the 2020 Quidditch House Cup!"))
        eventRepository.save(Event("Potions","Dungeons", LocalDateTime.of(2020,2,10,18,0),"223821.jpg", "Potions with Snape! Who can think of a better thing to spend time doing on a saturaday evening!"))
    }

}