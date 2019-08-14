package no.brightfield.hptrysil.repository

import no.brightfield.hptrysil.entity.House
import org.springframework.data.repository.CrudRepository

interface HouseRepository : CrudRepository<House, Long> {
    fun findByName(name: String): House?
    fun findAllByOrderByNameAsc(): Iterable<House>
}