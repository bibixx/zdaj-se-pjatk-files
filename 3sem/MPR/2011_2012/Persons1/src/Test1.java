
import org.junit.Test;

import junit.framework.Assert;

public class Test1 {
	@Test
	public void test0() {
		Person osoba1 = new Person("Mateusz", "Sobik", "1991-10-11",
				Person.BY_SURNAME);
		Person osoba2 = new Person("Marek", "Soreik", "1991-10-12",
				Person.BY_SURNAME);
		Assert.assertTrue(osoba1.compareTo(osoba2) == -1);
	}

	@Test
	public void test1()
	{
		Person osoba1=new Person("Patryk", "O", "1888-09-08",Person.BY_SURNAME);
		Person osoba2=new Person("Patryk", "Ó", "1888-09-08",Person.BY_SURNAME);
		Assert.assertTrue(osoba1.compareTo(osoba2)==-1);
	}
	
	@Test
	public void test2()
		{
		Assert.assertTrue(ListsSet.isDateValid("2009-12-10"));
		Assert.assertFalse(ListsSet.isDateValid("2100-15-11"));
		Assert.assertTrue(Person.compareDate(Person.parseDate("1188-11-10"), Person.parseDate("1188-11-10"))==0);
		}
		
	
}
