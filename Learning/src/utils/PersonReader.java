package utils;

import domains.Person;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PersonReader {
    public static ArrayList<Person> readPersonsList(String csvFile) {
        BufferedReader br = null;
        String line;
        String cvsSplitBy = ",";

        ArrayList<Person> list = new ArrayList<Person>();

        try {

            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] personInfo = line.split(cvsSplitBy);

                Person person = getPerson(personInfo);
                list.add(person);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    private static Person getPerson(String[] personInfo) {
        Integer classValue = 0;
        if ("50000+".equals(personInfo[41])) {
            classValue = 1;
        }
        Person person = new Person(classValue);

        person.addAttribute(0, Double.parseDouble(personInfo[0]));
        person.addAttribute(1, Double.parseDouble(personInfo[18]));
        Double marrigeStatus = 0.0;
        if (personInfo[7].startsWith("Married")) {
            marrigeStatus = 1.0;
        }
        person.addAttribute(2, marrigeStatus);
        Double employer = 1.0;
        if ("0".equals(personInfo[30])) {
            employer = 0.0;
        }
        person.addAttribute(3, employer);

        return person;
    }
}
