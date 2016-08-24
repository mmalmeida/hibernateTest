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
	private static final String KEY_BRAND = "brand";
	private static final String VALUE_GREEN = "Green";
	
	private static final String VALUE_BMW = "BMW";
	private static final String VALUE_AUDI = "AUDI";

	@Before
	public void setUp(){
		createKey(KEY_COLOUR);
		createValue(VALUE_GREEN);
	}
	
	/**
	 * If we just search for the map's value it's ok.
	 * In this example we're only searching for codes where the value in the map is Green.
	 */
	@Test
	public void searchByValueIsOK(){
		
		createCodeWithGreenColour();
		
		Criteria criteria = getSession().createCriteria(Code.class);
		
		criteria.createAlias("propertiesMap", "pm");
		criteria.add(Restrictions.eq("pm.value", VALUE_GREEN));
		criteria.setFetchMode("code.properties", FetchMode.JOIN);
		List<Code> results = criteria.list();
		assertEquals(1,results.size());
	}
	
	/**
	 * 2 codes:
	 * greenAudi - <colour,green> and <brand,audi>
	 * greenBMW - <colour,green> and <brand,bmw>
	 * 
	 * Goal: search for codes whose brand is bmw
	 * 
	 * See the problematic row: there doesn't seem to be a way to filter by the map key.
	 */
	@Test
	public void retrieveBMWs(){
		
		Code greenAudi = createCodeWithGreenColour();
		Code greenBMW = createCodeWithGreenColour();
		
		//creating brands 
		CodeProperty brand = createKey(KEY_BRAND);
		CodeValue bmw = createValue(VALUE_BMW);
		CodeValue audi = createValue(VALUE_AUDI);
		
		greenBMW.getPropertiesMap().put(brand, bmw);
		greenAudi.getPropertiesMap().put(brand, audi);
		
		Criteria criteria = getSession().createCriteria(Code.class);
		
		criteria.createAlias("propertiesMap", "pm");
		criteria.add(Restrictions.eq("pm.key", KEY_BRAND));//this is the problematic instruction. could not resolve property: key of: hibernateTest.mapTest.CodeValue
		criteria.add(Restrictions.eq("pm.value", VALUE_BMW));
		criteria.setFetchMode("code.properties", FetchMode.JOIN);
		List<Code> results = criteria.list();
		assertEquals(1,results.size());
	}

	/**
	 * Creates a code with one entry in the Map: <KEY_COLOUR,VALUE_GREEN>
	 */
	private Code createCodeWithGreenColour() {
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
	
	private CodeProperty createKey(String key) {
		CodeProperty codeProperty = new CodeProperty(key);
		save(codeProperty);
		return codeProperty;
		
	}

	private CodeValue createValue(String name) {
		CodeValue codeValue = new CodeValue(name);
		save(codeValue);
		return codeValue;
	}
}
