package guru.springframework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data 
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne // no need to specify cascade here because Recipe will own this (because if we
				// delete Notes, we don't want to delete Recipe)
	private Recipe recipe;
	
	@Lob		// JPA will store this in a CLOB field in the DB
	private String recipeNotes;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(recipeNotes);
		return sb.toString();
	}
	
}
