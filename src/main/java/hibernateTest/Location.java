package hibernateTest;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

@Entity
@Table(schema = "clinical", name="location")
@Audited
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_TRIAL")
	private int id;
	
	private String name;
	
	@Column(nullable=false)
	private String code;
	
	public Location() {

	}
	public Location(String name) {
		this.name = name;
	}
	
	public Location(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}
	public Location(int id) {
		setId(id);
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public void addTrial(Trial trial) {
		if(trial == null){
			return;
		}
		trial.getLocations().add(this);		
	}
	
	public boolean removeTrial(Trial trial) {
		if(trial != null && trial.getLocations() != null){
			boolean removed = trial.getLocations().remove(this);
			return removed;
		}
		return false;
		
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if ( !(obj instanceof Location) ) return false;

		Location other = (Location) obj;
		if (getCode() == null) {
			if (other.getCode() != null)
				return false;
		} else if (!getCode().equals(other.getCode()))
			return false;
		if (getName() == null) {
			if (other.getName() != null)
				return false;
		} else if (!getName().equals(other.getName()))
			return false;
		return true;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString(){
		if(getName() == null){
			return "";
		}
		return getName();
	}
}

