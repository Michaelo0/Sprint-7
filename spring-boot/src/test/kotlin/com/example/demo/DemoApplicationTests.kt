package com.example.demo

import com.example.demo.persistance.Entity
import com.example.demo.persistance.EntityRepository
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private lateinit var entityRepository: EntityRepository

	@Test
	fun test() {
		val savedEntity = entityRepository.save(Entity(name = "name"))

		val foundEntity = savedEntity.id?.let { entityRepository.findById(it) }

		if (foundEntity != null) {
			assertTrue(foundEntity.get() == savedEntity)
		}
	}

}
