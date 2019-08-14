package no.brightfield.hptrysil.repository

import no.brightfield.hptrysil.entity.House
import no.brightfield.hptrysil.entity.Points
import org.springframework.data.repository.CrudRepository

interface PointsRepository : CrudRepository<Points, Long> {
    fun findAllByHouseOrderByAddedAtAsc(house: House): Iterable<Points>
}