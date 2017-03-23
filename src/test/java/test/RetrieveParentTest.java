package test;

import static org.junit.Assert.*;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import hibernateTest.Child;
import hibernateTest.Parent;

public class RetrieveParentTest extends HibernateAbstractTest {
	
	@Test
	public void retrieveParentsOnlyTest() {
		Parent Bonnie = new Parent("Bonnie");
		save(Bonnie);
		Parent Clyde = new Parent("Clyde");
		save(Clyde);
		Child child = new Child("Bonnie+Clyde");
		save(child);
		
		sessionFactory.getCurrentSession().flush();
		
		Criteria criteria = sessionFactory.getCurrentSession()
				.createCriteria(Parent.class, "Parent")
				.add(Restrictions.eq("class", Parent.class));
		
		List<Parent> parents = criteria.list();
		assertEquals(2, parents.size());
		
		for(Parent parent : parents) {
			System.out.println(parent);
		}
	}

	
}
