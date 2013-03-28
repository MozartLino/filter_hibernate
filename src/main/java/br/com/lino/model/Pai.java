package br.com.lino.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@Entity
@FilterDef(name = "filterIdade", parameters = @ParamDef(name = "idade", type = "integer"))
public class Pai {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany
	@Cascade({ CascadeType.ALL })
	@JoinColumn(name = "pai_id")
	@Filter(name = "filterIdade", condition = "idade > :idade")
	private List<Filho> filhos;

	public Pai(String name, List<Filho> filhos) {
		this.name = name;
		this.filhos = filhos;
	}

	public Pai(Long id) {
		this.id = id;
	}

	protected Pai() {
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Filho> getFilhos() {
		return filhos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pai other = (Pai) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
