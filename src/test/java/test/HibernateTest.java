package test;

import hibernateTest.ClinicalTrial;
import hibernateTest.Location;

import org.junit.Test;

public class HibernateTest extends HibernateAbstractTest {

	@Test
	public void failedTest() {
		setUp();
		getSession().createCriteria(Location.class).list();
	}
	
	@Test
	public void refreshTest() {
		ClinicalTrial trial = setUp();
		getSession().refresh(trial);
		getSession().createCriteria(Location.class).list();
	}
	
	@Test
	public void evictTest() {
		ClinicalTrial trial = setUp();
		getSession().evict(trial);
		getSession().createCriteria(Location.class).list();
	}

	/**
	 * @return
	 */
	private ClinicalTrial setUp() {
		Location location = new Location("name", "code");
		saveOrUpdate(location);
		ClinicalTrial trial = new ClinicalTrial();
		location.addTrial(trial);
		saveOrUpdate(trial);
		getSession().createCriteria(Location.class).list();
		location.setCode("other");
		getSession().saveOrUpdate(location);
		saveOrUpdate(trial);
		return trial;
	}
	
}
