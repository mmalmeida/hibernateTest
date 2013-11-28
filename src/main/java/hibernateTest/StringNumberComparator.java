package hibernateTest;

import java.util.Comparator;

/**
 * Location comparator. Used to compare locations for sorting. <br/>
 * Locations are sorted by code. If the code is numeric, compares the number. Otherwise
 * it compares the code.code string.
 *  
 * @author malmeida
 *
 */
public class StringNumberComparator implements Comparator<Location>{

	public int compare(Location o1, Location o2) {
		Integer code1= null;
		Integer code2= null;
		int result = 0;
		try {
			code1 = Integer.valueOf(o1.getCode());
		} catch (NumberFormatException e) {
			result = o1.getCode().compareTo(o2.getCode());
		}
		try {
			code2 = Integer.valueOf(o2.getCode());
		} catch (NumberFormatException e) {
			result = o1.getCode().toLowerCase().compareTo(o2.getCode().toLowerCase());
		}
		
		if(code1 != null && code2 != null){
			result = code1.compareTo(code2);
		}
		if(result == 0 && o1.getName() != null && o2.getName() != null){
			return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
		}
		else
			return result;
		
	}

	
}
