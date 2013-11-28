package test;

import hibernateTest.Trial;
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
		Trial trial = setUp();
		getSession().refresh(trial);
		getSession().createCriteria(Location.class).list();
	}
	
	@Test
	public void evictTest() {
		Trial trial = setUp();
		getSession().evict(trial);
		getSession().createCriteria(Location.class).list();
	}

	/**
	 * @return
	 */
	private Trial setUp() {
		Location location = new Location("name", "code");
		saveOrUpdate(location);
		Trial trial = new Trial();
		location.addTrial(trial);
		saveOrUpdate(trial);
		getSession().createCriteria(Location.class).list();
		location.setCode("other");
		getSession().saveOrUpdate(location);
		saveOrUpdate(trial);
		return trial;
	}
	
}
