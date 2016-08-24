package test.mapTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.criterion.Restrictions;
import org.junit.Before;
import org.junit.Test;

import hibernateTest.mapTest.Code;
import hibernateTest.mapTest.CodeProperty;
import hibernateTest.mapTest.CodeValue;
import test.HibernateAbstractTest;

public class MapTest extends HibernateAbstractTest{

	private static final String KEY_COLOUR = "colour";
	private static final String VALUE_GREEN = "Green";

	@Before
	public void setUp(){
		createKey(KEY_COLOUR);
		createValue(VALUE_GREEN);
	}
	
	private void createKey(String keyColour) {
		CodeProperty codeProperty = new CodeProperty(keyColour);
		save(codeProperty);
		
	}

	private void createValue(String name) {
		CodeValue codeValue = new CodeValue(name);
		save(codeValue);
	}

	@Test
	public void searchByValueIsOK(){
		
		createCode();
		
		Criteria criteria = getSession().createCriteria(Code.class);
		
		criteria.createAlias("propertiesMap", "pm");
		criteria.add(Restrictions.eq("pm.value", VALUE_GREEN));
		criteria.setFetchMode("code.properties", FetchMode.JOIN);
		List<Code> results = criteria.list();
		assertEquals(1,results.size());
	}
	
	@Test
	public void searcha(){
		
		Code code2 = createCode();
		
		Criteria criteria = getSession().createCriteria(Code.class);
		
		criteria.createAlias("propertiesMap", "pm");
		criteria.add(Restrictions.eq("pm.value", VALUE_GREEN));
		criteria.setFetchMode("code.properties", FetchMode.JOIN);
		List<Code> results = criteria.list();
		assertEquals(1,results.size());
	}

	/**
	 * Creates a code with one entry in the Map: <KEY_COLOUR,VALUE_GREEN>
	 */
	private Code createCode() {
		Code code = new Code();
		
		save(code);
		assertTrue(code.getId()!= 0);
		
		CodeProperty codeProperty = new CodeProperty(KEY_COLOUR);
		save(codeProperty);
		
		CodeValue codeValue = new CodeValue(VALUE_GREEN);
		save(codeValue);
		
		code.getPropertiesMap().put(codeProperty, codeValue);
		update(code);
		return code;
	}
}
