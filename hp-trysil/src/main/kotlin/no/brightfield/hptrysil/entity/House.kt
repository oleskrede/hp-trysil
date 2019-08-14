package no.brightfield.hptrysil.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class House(
        var name: String,
        @Id @GeneratedValue var id: Long? = null
) {
    override fun toString() : String {
        return name
    }
}