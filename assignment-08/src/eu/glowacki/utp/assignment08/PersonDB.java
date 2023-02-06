package eu.glowacki.utp.assignment08;

import eu.glowacki.utp.assignment08.comparators.BirthdateComparator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.*;

public final class PersonDB {
	private final List<Person> in;
	private List<Person> sortedByN;
	private List<Person> sortedBySNB;
	private List<Person> sortedByB;
	private Map<Date, List<Person>> searchByB;
	public PersonDB(List<Person> in){
		this.in = in;
		INIT();
	}
	private void INIT(){
		searchByB();
		sortedByN();
		sortedBySNB();
		sortedByB();
	}

	public List<Person> getSearchByB(Date date) {return searchByB.getOrDefault(date, null); }
	public List<Person> getSortedByB() {
		return sortedByB;
	}
	public List<Person> getSortedByN() {
		return sortedByN;
	}
	public List<Person> getSortedBySNB() {
		return sortedBySNB;
	}

	public void searchByB() {
		Map<Date, List<Person>> out = new TreeMap<>();
		if (in == null)return;
		for (Person e : in){
			Date find = e.getDOB();
			if (out.get(find) == null)out.put(find, bornOnDay(find));
		}
		searchByB = out;
	}
	public void sortedByN() {
		//FirstName
		List<Person> out = new ArrayList<>(in);
		if (in == null)return;

		//Comparator<Person> tmp = new FirstNameComparator();
		out.sort(Comparator.comparing(Person::getName));
		//out.sort(tmp);
		sortedByN = out; // external rule for ordering (based on Comparator --- FirstNameComparator)
	}
	public void sortedBySNB() {
		//SurnameFirstNameAndBirthdate
		List<Person> out = new ArrayList<>(in);
		if (in == null)return;

		Comparator<Person> tmp = Comparator.naturalOrder();
		Collections.sort(out);
		sortedBySNB = out; // natural order (Comparable)
	}
	
	public void sortedByB() {
		//sortedByBirthdate
		List<Person> out = new ArrayList<>(in);
		if (in == null)return;

		Comparator<Person> tmp = new BirthdateComparator();
		out.sort(tmp);
		sortedByB = out; // external rule for ordering (based on Comparator --- BirthdateComparator)
	}
	
	public List<Person> bornOnDay(Date date) {
		List<Person> out = new ArrayList<>();
		if (this.in == null)return out;

		for (Person e : this.in)if (e.getDOB().equals(date))out.add(e);
		return out;
	}
	//
	// assignment 8 - factory method based on deserialization
	public static PersonDB deserialize(DataInputStream in) throws Assignment08Exception {
		try{
			int size = in.readInt();
			List<Person> out = new ArrayList<>();
			for (int i = 0; in.available() > i && i < size; i++){
				Person tmp = Person.deserialize(in);
				out.add(tmp);
			}
			assert out.size() == size;
			return new PersonDB(out);
		} catch (IOException e) {
			throw new Assignment08Exception("deserialisation borked",e);
		}
	}
	@Override
	public boolean equals(Object in){
		if(!(in instanceof PersonDB) || this.getSortedBySNB().size() != ((PersonDB) in).getSortedBySNB().size())return false;
		for (int i = 0; i < this.getSortedBySNB().size(); i++){
			if (!(this.getSortedBySNB().get(i).equals(((PersonDB) in).getSortedBySNB().get(i))))return false;
		}
		return true;
	}
	// assignment 8
	public void serialize(DataOutputStream in) throws Assignment08Exception {
		try {
			in.writeInt(sortedBySNB.size());
			sortedBySNB.forEach(e -> {
				try {
					e.serialize(in);
				} catch (Assignment08Exception ex) {
					ex.printStackTrace();
				}
			});
		}catch (IOException e){throw new Assignment08Exception("serialise borked",e);}

	}
	public static void main(String[] arg){
	}
}