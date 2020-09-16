package guru.springframework.spring5recipeapp.domain;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by Viktoriya on 03-Apr-20
 */
@Entity
@Data
@EqualsAndHashCode(exclude = {"recipe"})
@ToString(exclude = {"recipe"})
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private BigDecimal amount;
	@OneToOne(fetch = FetchType.EAGER)
	private UnitOfMeasure unitOfMeasure;

	public Ingredient() {
	}

	public Ingredient(BigDecimal amount, UnitOfMeasure unitOfMeasure, String description) {
		this.description = description;
		this.amount = amount;
		this.unitOfMeasure = unitOfMeasure;
		this.recipe = recipe;
	}

	public Ingredient(BigDecimal amount, String description) {
		this.description = description;
		this.amount = amount;
		this.recipe = recipe;
	}

	public Ingredient(String description) {
		this.description = description;
		this.recipe = recipe;
	}

	@ManyToOne
	private Recipe recipe;
}
