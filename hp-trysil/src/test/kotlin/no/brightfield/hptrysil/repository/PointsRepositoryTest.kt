package no.brightfield.hptrysil.repository

import no.brightfield.hptrysil.util.GRYFFINDOR
import no.brightfield.hptrysil.util.HUFFELPUFF
import no.brightfield.hptrysil.entity.House
import no.brightfield.hptrysil.entity.Points
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class PointsRepositoryTests @Autowired constructor(
        val entityManager: TestEntityManager,
        val pointsRepository: PointsRepository) {

    @Test
    fun `When findAllByHouseOrderByAddedAtAsc then return iterable of Points`() {
        val gryffindor = House(GRYFFINDOR)
        val huffelpuff = House(HUFFELPUFF)
        entityManager.persist(gryffindor)
        entityManager.persist(huffelpuff)
        entityManager.flush()

        val p1 = Points(10, gryffindor, "For showing great courage")
        val p2 = Points(50, gryffindor, "For excellent spellcasting")
        val p3 = Points(20, huffelpuff, "For showing great sportsmanship in the quidditch final")
        val p4 = Points(-5, gryffindor, "Late to class")
        entityManager.persist(p1)
        entityManager.persist(p2)
        entityManager.persist(p3)
        entityManager.persist(p4)
        entityManager.flush()

        val gryffindorPoints = pointsRepository.findAllByHouseOrderByAddedAtAsc(gryffindor).toList()
        assertThat(gryffindorPoints[0]).isEqualTo(p1)
        assertThat(gryffindorPoints[1]).isEqualTo(p2)
        assertThat(gryffindorPoints[2]).isEqualTo(p4)
    }

}