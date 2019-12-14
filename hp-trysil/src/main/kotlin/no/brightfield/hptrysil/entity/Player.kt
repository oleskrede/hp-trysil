package no.brightfield.hptrysil.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class Player(
        var name: String,
        var bio: String,
        @ManyToOne var house: House,
        @Id @GeneratedValue var id: Long? = null
) {
    override fun toString() : String {
        return name
    }
}