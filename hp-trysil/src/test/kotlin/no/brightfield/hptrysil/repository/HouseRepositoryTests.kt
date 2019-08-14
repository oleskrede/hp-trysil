package no.brightfield.hptrysil.repository

import no.brightfield.hptrysil.util.GRYFFINDOR
import no.brightfield.hptrysil.util.HUFFELPUFF
import no.brightfield.hptrysil.util.RAVENCLAW
import no.brightfield.hptrysil.util.SLYTHERIN
import no.brightfield.hptrysil.entity.House
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class HouseRepositoryTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val houseRepository: HouseRepository) {

    @Test
    fun `When findByName then return House`() {
        val gryffindor = House(GRYFFINDOR)
        val huffelpuff = House(HUFFELPUFF)
        entityManager.persist(gryffindor)
        entityManager.persist(huffelpuff)
        entityManager.flush()
        val found = houseRepository.findByName(GRYFFINDOR)
        assertThat(found).isEqualTo(gryffindor)
    }

    @Test
    fun `When findAllByOrderByNameAsc then return all House in lexical order` () {
        val huffelpuff = House(HUFFELPUFF)
        val slytherin = House(SLYTHERIN)
        val gryffindor = House(GRYFFINDOR)
        val ravenclaw = House(RAVENCLAW)
        entityManager.persist(slytherin)
        entityManager.persist(huffelpuff)
        entityManager.persist(ravenclaw)
        entityManager.persist(gryffindor)
        entityManager.flush()

        val houses = houseRepository.findAllByOrderByNameAsc().toList()
        assertThat(houses[0]).isEqualTo(gryffindor)
        assertThat(houses[1]).isEqualTo(huffelpuff)
        assertThat(houses[2]).isEqualTo(ravenclaw)
        assertThat(houses[3]).isEqualTo(slytherin)
    }
}