package no.brightfield.hptrysil.repository

import no.brightfield.hptrysil.entity.Event
import org.springframework.data.repository.CrudRepository

interface EventRepository : CrudRepository<Event, Long> {
    fun findAllByOrderByTimeAsc(): Iterable<Event>
}