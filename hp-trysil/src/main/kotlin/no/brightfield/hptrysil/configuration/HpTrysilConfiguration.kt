package no.brightfield.hptrysil.configuration

import no.brightfield.hptrysil.util.ALL_HOUSES
import no.brightfield.hptrysil.entity.House
import no.brightfield.hptrysil.entity.Points
import no.brightfield.hptrysil.repository.HouseRepository
import no.brightfield.hptrysil.repository.PointsRepository
import no.brightfield.hptrysil.util.GRYFFINDOR
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HpTrysilConfiguration {

    @Bean
    fun databaseInitializer(houseRepository: HouseRepository,
                            pointsRepository: PointsRepository) = ApplicationRunner {
        ALL_HOUSES.forEach { houseName ->
            houseRepository.save(House(houseName))
        }
        val gryffindor = houseRepository.findByName(GRYFFINDOR)
        pointsRepository.save(Points(10, gryffindor!!, "taking a shot"))
        pointsRepository.save(Points(30, gryffindor!!, "excellent spellcasting"))
    }
}