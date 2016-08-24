package test;

import org.junit.Test;

import hibernateTest.Location;
import hibernateTest.Trial;

public class HibernateTest extends HibernateAbstractTest {

//	@Test
//	public void failedTest() {
//		setUp();
//		getSession().createCriteria(Location.class).list();
//	}
	
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
		save(location);
		Trial trial = new Trial();
		location.addTrial(trial);
		save(trial);
		getSession().createCriteria(Location.class).list();
		location.setCode("other");
		getSession().update(location);
		update(trial);
		return trial;
	}
	
}
