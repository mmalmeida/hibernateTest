package hibernateTest;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(schema = "clinical")
public class Child extends Parent {

	@Override
	public String toString() {
		return "ChildClass [parent=" + parent + "]";
	}

	@ManyToOne(targetEntity=Parent.class)
	@JoinColumn(name="parent_id")
	private Parent parent;

	public Child(String name) {
		super(name);
	}

}
