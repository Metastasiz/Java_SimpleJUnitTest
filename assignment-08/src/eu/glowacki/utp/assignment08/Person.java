package eu.glowacki.utp.assignment08;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public final class Person implements Comparable<Person> {
	public final static String myPath = "src/eu/glowacki/utp/assignment08/serial.txt";
	private final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy/MM/dd");
	private final String _name;
	private final String _surname;
	private final Date _birthdate;
	private final Comparator<Person> comp = Comparator.comparing(Person::getSurname)
			.thenComparing(Person::getName)
			.thenComparing(Person::getDOB);
	
	public Person(String name, String surname, Date birthdate) {
		_name = name;
		_surname = surname;
		_birthdate = birthdate;
	}
	public String getName(){if(_name==null)return ""; return _name;}
	public String getSurname(){if(_surname==null)return ""; return _surname;}
	public Date getDOB(){if(_birthdate==null)return null; return _birthdate;}

	@Override
	public String toString(){
		return _surname.toUpperCase(Locale.ROOT) + "\t\t" + _name.toUpperCase(Locale.ROOT) + "\n" + timeFormat.format(_birthdate) + "\n";
	}
	@Override
	public int compareTo(Person a) {
		return comp.compare(this,a);
			// natural order based on:
		// (1) surname;
		// (2) first name;
		// (3) birth date.
		// TODO Auto-generated method stub
	}
	@Override
	public boolean equals(Object a){
		if (!(a instanceof Person) || a == null) return false;
		Person tmp = (Person)a;
		if (getName().equals(tmp.getName()) && getSurname().equals(tmp.getSurname()) && getDOB().equals(tmp.getDOB())) {
			return true;
		}
		return false;
	}

	// assignment 8
	public void serialize(DataOutputStream output) throws Assignment08Exception {
		try{
			output.writeUTF(this.getName());
			output.writeUTF(this.getSurname());
			output.writeLong(this.getDOB().getTime());
		}catch (IOException e){throw new Assignment08Exception("serialise borked",e);}
		// serialize birth date with getTime() method
		// encapsulate IOException in Assignment08Exception
	}

	public static Person deserialize(DataInputStream input) throws Assignment08Exception {
		try{
			String tmp1 = input.readUTF();
			String tmp2 = input.readUTF();
			Date tmp3 = new Date(input.readLong());
			return new Person(tmp1, tmp2, tmp3);
		}catch (IOException e){throw new Assignment08Exception("serialise borked",e);}
	}
	public static void main(String[] arg){


	}
}