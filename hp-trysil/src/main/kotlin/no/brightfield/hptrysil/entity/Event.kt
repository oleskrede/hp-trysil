package no.brightfield.hptrysil.entity

import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Event(
        var name: String,
        var location: String,
        var time: LocalDateTime,
        var imageSource: String,
        var description: String,
        @Id @GeneratedValue var id: Long? = null
) {
    override fun toString() : String {
        return name
    }
}