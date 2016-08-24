package hibernateTest.mapTest;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.MapKeyClass;
import javax.persistence.Table;

@Entity
@Table(schema = "clinical", name="code")
public class Code {
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PK_TRIAL")
	private int id;

	@ElementCollection(targetClass = CodeValue.class)
	@MapKeyClass(CodeProperty.class)
	@JoinTable(name = "code_properties")
	private Map<CodeProperty, CodeValue> propertiesMap = new HashMap<CodeProperty, CodeValue>();

	public int getId() {
		return id;
	}

	public Map<CodeProperty, CodeValue> getPropertiesMap() {
		return propertiesMap;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setPropertiesMap(Map<CodeProperty, CodeValue> propertiesMap) {
		this.propertiesMap = propertiesMap;
	}
	
	
}
