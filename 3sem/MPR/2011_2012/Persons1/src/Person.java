
import java.text.CollationKey;
import java.text.Collator;
import java.util.Locale;

public class Person implements Comparable<Person>{
	private final String name;
	private final String surname;
	private final String birthDate;

	private final CollationKey nameKey;
	private final CollationKey surnameKey;
	private final int[] birthKey;

	private int compareType;
	
	public static final int BY_NAME=1;
	public static final int BY_SURNAME=2;
	public static final int BY_DATE=3;
	public static final int BY_CHOSEN_DATE=4;
	
	private static final Collator collator = Collator.getInstance(new Locale("pl"));
	
	public Person(String name, String surname, String birthDate, int compareType) {
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.surnameKey=collator.getCollationKey(surname);
		this.nameKey=collator.getCollationKey(name);
		this.birthKey=parseDate(birthDate);
		this.compareType=compareType;
	}
	public void setCompareType(int compareType) {
		this.compareType = compareType;
	}
	public String getName() {
		return name;
	}
	public String getSurname() {
		return surname;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public int[] getBirthKey() {
		return birthKey;
	}
	public void sortByName()
	{
		this.compareType=BY_NAME;
	}
	public void sortBySurame()
	{
		this.compareType=BY_SURNAME;
	}
	public void sortByDate()
	{
		this.compareType=BY_DATE;
	}
	public String getElementAt(int i)
	{
		if(i==0) return name;
		if(i==1) return surname;
		if(i==2) return birthDate;
		return "";
	}
	public CollationKey getCollationKey()
	{
		if (compareType==BY_NAME) return nameKey;
		else return surnameKey;
	}
	
	@Override
	public int compareTo(Person o) 
	{
		if(compareType!=BY_DATE)
			return (this.getCollationKey()).compareTo(o.getCollationKey());
		else
			return compareDate(birthKey,o.getBirthKey()); 
	}	

	public static int compareDate(int[] date1, int[] date2)
	{
		for(int i=0;i<3;i++)
		{
			if(date1[i]>date2[i]) return 1;
			if(date1[i]<date2[i]) return -1;
		}
		return 0;
	}
	//parsowanie daty
	public static int[] parseDate(String birthDate)
	{
		int[] ret = new int[3];
		ret[0]=Integer.parseInt(birthDate.substring(0, 4));
		ret[1]=Integer.parseInt(birthDate.substring(5, 7));
		ret[2]=Integer.parseInt(birthDate.substring(8, 10));
		return ret;
	}

}
