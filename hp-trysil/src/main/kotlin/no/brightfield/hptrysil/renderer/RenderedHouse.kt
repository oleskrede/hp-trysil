package no.brightfield.hptrysil.renderer

import no.brightfield.hptrysil.entity.House

fun renderHouse(house: House, points: Int) = RenderedHouse(
        house.name,
        points
)

data class RenderedHouse(
        val name: String,
        val points: Int
)