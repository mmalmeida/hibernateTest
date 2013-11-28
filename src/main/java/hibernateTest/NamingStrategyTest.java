package hibernateTest;

import org.hibernate.cfg.ImprovedNamingStrategy;

/**
 * The naming strategy to use in the application. It is used by hibernate 
 * to translate class and properties from/into database tables and columns
 * @author malmeida
 *
 */
@SuppressWarnings("serial")
public class NamingStrategyTest extends ImprovedNamingStrategy {

	@Override
	public String foreignKeyColumnName(String propertyName,
			String propertyEntityName, String propertyTableName,
			String referencedColumnName) {

		if (propertyName != null && columnName(propertyName) != null)
			return columnName(propertyName).toLowerCase() + "_id";
		else
			return propertyTableName.toLowerCase() + "_"
					+ referencedColumnName.toLowerCase();

	}
}
