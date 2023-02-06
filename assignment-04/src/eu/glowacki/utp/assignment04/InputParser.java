package eu.glowacki.utp.assignment04;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InputParser {
	
	// 1. Use regular expresssions (Pattern) for validating input data
	//    U�y� regularnych wyra�e� (Pattern) do walidacji danych wej�ciowych
	//
	// 2. Convert input string representing date using SimpleDateFormat "yyyy-MM-dd" 
	//    Konwersj� wej�ciowego ci�gu znak�w reprezentuj�cego dat� nale�y oprze� np. DateFormat 
	//    SimpleDateFormat format "yyyy-MM-dd"
	final static String regDateSep = "-";
	final static String regGrpSep = "[ \t]+";

	final static String groupName = "name";
	final static String groupSurname = "surname";
	final static String groupDOB = "DOB";

	final static String regName = "([A-Z][a-z]+)";

	final static String regYear = "((0{0,3}+[1-9])|(0{0,2}+[1-9]{2})|((0?|1)[1-9]{3})|(20(([0-1][0-9])|2[01])))";
	final static String regMonth = "(0?[1-9]|1[012])";
	final static String regDay = "(0?[1-9]|[12][0-9]|3[01])";
	final static String regDate = regYear + regDateSep + regMonth + regDateSep + regDay;

	final static String namePat 	= "(?<" + groupName + ">" + regName + ")";
	final static String surnamePat 	= "(?<" + groupSurname + ">" + regName + ")";
	final static String dobPat 		= "(?<" + groupDOB + ">" + regDate + ")";

	final static String regLine = namePat + regGrpSep + surnamePat + regGrpSep + dobPat;
	final static Pattern patLine = Pattern.compile(regLine);

	final static String path = "src/eu/glowacki/utp/assignment04/save.txt";
	final static File file = new File(path);

	public static List<Person> getPerson() {
		try {return parse(file);}
		catch (IOException e) {e.printStackTrace();}
		return null;
	}

	public static List<Person> parse(File file) throws IOException {
		List<Person> out = new ArrayList<>();
		if (file == null)return out;
		if (!file.exists())throw new NoSuchFileException("Path error");
		//new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"))
		BufferedReader br1 = Files.newBufferedReader(Path.of(file.getPath()));
		String tmp;
		while ((tmp = br1.readLine()) != null){
			Person tmp2 = check(tmp);
			if (tmp2 != null)out.add(tmp2);
		}
		br1.close();
		return out;
	}
	public static Person check(String input){
		if (input == null)return null;
		Matcher m = patLine.matcher(input);
		ValidatorDF valid = new ValidatorDF("yyyy-MM-dd");
		if (m.matches() && valid.isValid(m.group(groupDOB))){
			String name = m.group(groupName);
			String surname = m.group(groupSurname);
			AssignDate assignDate = new AssignDate(m.group(groupDOB));
			return new Person(name, surname, assignDate.getDate());
		}
		return null;
	}
}