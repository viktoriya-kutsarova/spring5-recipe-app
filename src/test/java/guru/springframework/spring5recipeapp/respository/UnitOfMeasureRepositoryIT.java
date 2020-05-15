package guru.springframework.spring5recipeapp.respository;

import java.util.Optional;

import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryIT {

	@Autowired
	UnitOfMeasureRepository unitOfMeasureRepository;

	@BeforeEach
	void setUp() {
	}

	@Test
	void findByUom() {

		String expectedUnitOfMeasure = "Teaspoon";
		Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByUom("Teaspoon");

		assertEquals(expectedUnitOfMeasure, unitOfMeasure.get().getUom());
	}

	@Test
	void findByUomCup() {

		String expectedUnitOfMeasure = "Cup";
		Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByUom("Cup");

		assertEquals(expectedUnitOfMeasure, unitOfMeasure.get().getUom());
	}
}