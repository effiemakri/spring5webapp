package guru.springframework.domain;

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

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	private BigDecimal amount;

	@OneToOne(fetch=FetchType.EAGER)		// no cascading here. Unidirectional relationship from Ingredient to UnitOfMeasure
	private UnitOfMeasure uom;

	@ManyToOne							// we don't want a cascade here, so that we can delete ingredients without affecting recipe
	private Recipe recipe; 				// Recipe that this ingredient belongs to.
	
	public Ingredient() {}
	
	public Ingredient(BigDecimal amount, UnitOfMeasure uom, String description) {
		this.amount = amount;
		this.uom = uom;
		this.description = description;
	}

	public Ingredient(BigDecimal amount, UnitOfMeasure uom, String description, Recipe recipe) {
		this.amount = amount;
		this.uom = uom;
		this.description = description;
		this.recipe = recipe;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\t").append(amount)
			.append(" ")	.append(uom)
			.append(" ")	.append(description);
		return sb.toString();
	}
}
