package hibernateTest;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(schema = "clinical")
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Parent {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_TRIAL")
	public int id;
	
	public String name;
	
	@Override
	public String toString() {
		return "ParentClass [name=" + name + "]";
	}

	public Parent(String name) {
		this.name = name;
	}

}
