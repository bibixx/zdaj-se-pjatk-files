
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class ListsSet {

	 private BufferedReader reader;
	 private LinkedList<Person> nameSorted;
	 private ArrayList<Person> surnameSorted;
	 private Vector<Person> dateSorted;
	 private LinkedList<Person> chosenDate;
	 
	ListsSet()
	{
		nameSorted= new LinkedList<Person>();
		surnameSorted = new ArrayList<Person>();
		dateSorted = new Vector<Person>();
		chosenDate = new LinkedList<Person>();
	}
	 
	public List<Person> getNameSorted() {
		return nameSorted;
	}
	
	public List<Person> getSurnameSorted() {
		return surnameSorted;
	}
	
	public List<Person> getDateSorted() {
		return dateSorted;
	}
	
	public List<Person> getListAt(int i)
	{
		if(i==Person.BY_NAME) return nameSorted;
		if(i==Person.BY_SURNAME) return surnameSorted;
		if(i==Person.BY_DATE) return dateSorted;
		if(i==Person.BY_CHOSEN_DATE) return chosenDate;
		return null;
	}

	public void makeChosenDateList(String date, int sortType)
	{
		chosenDate=new LinkedList<Person>();
		for(Person person : getListAt(sortType))
		{
			if(Person.compareDate(Person.parseDate(date), person.getBirthKey())==0)
				chosenDate.add(person);
		}
		Collections.sort(chosenDate);
	}

	public static boolean isDateValid(String date)
	{
		if(date.length()!=10) return false;
		String[] string=new String[3];
		string[0]=date.substring(0, 4);
		string[1]=date.substring(5, 7);
		string[2]=date.substring(8, 10);
		if(!string[0].matches("[12][0-9]{3}")) return false;
		if(!string[1].matches("0[1-9]|1[012]")) return false;
		if(!string[2].matches("0[1-9]|[12][0-9]|3[01]")) return false;
		return true;
	}
	
	 public void read(File file) throws IOException
	 {
		nameSorted= new LinkedList<Person>();
		surnameSorted = new ArrayList<Person>();
		dateSorted = new Vector<Person>();
		reader = new BufferedReader(new FileReader(file));
		
		String line;
		String[] result;
		line=reader.readLine();
		boolean check=true;
		while(line!=null)
		{
			result=line.split(" ");
			if(result.length!=3) check=false;
			if(!isDateValid(result[2])) check=false;
			if(check)
			{
				nameSorted.add(new Person(result[0],result[1],result[2],Person.BY_NAME));
				surnameSorted.add(new Person(result[0],result[1],result[2],Person.BY_SURNAME));
				dateSorted.add(new Person(result[0],result[1],result[2],Person.BY_DATE));
			}
			check=true;
			line=reader.readLine();
		}
		Collections.sort(nameSorted);
		Collections.sort(surnameSorted);
		Collections.sort(dateSorted);

	 }
	
}
