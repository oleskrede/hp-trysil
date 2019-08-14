package no.brightfield.hptrysil.configuration

import no.brightfield.hptrysil.util.ALL_HOUSES
import no.brightfield.hptrysil.entity.House
import no.brightfield.hptrysil.repository.HouseRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class HpTrysilConfiguration {

    @Bean
    fun databaseInitializer(houseRepository: HouseRepository) = ApplicationRunner {
        ALL_HOUSES.forEach { houseName ->
            houseRepository.save(House(houseName))
        }
    }
}