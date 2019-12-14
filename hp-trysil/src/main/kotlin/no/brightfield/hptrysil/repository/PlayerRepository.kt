package no.brightfield.hptrysil.repository

import no.brightfield.hptrysil.entity.House
import no.brightfield.hptrysil.entity.Player
import no.brightfield.hptrysil.entity.Points
import org.springframework.data.repository.CrudRepository

interface PlayerRepository : CrudRepository<Player, Long> {
    fun findByName(name: String): Player?
    fun findAllByOrderByNameAsc(): Iterable<Player>
    fun findAllByHouse(house: House): Iterable<Player>
}