import domains.Person;
import net.sf.javaml.core.Instance;
import net.sf.javaml.core.SparseInstance;

import java.io.*;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IOException {
        String csvFile = "train.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        ArrayList<Person> list = new ArrayList<Person>();

        try {

            br = new BufferedReader(new FileReader(csvFile));
            line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] country = line.split(cvsSplitBy);
                Integer classValue = 0;
                if ("50000+".equals(country[41])) {
                    classValue = 1;
                }
                Person person = new Person(classValue);
                person.addAttribute(0, Double.parseDouble(country[0]));
                person.addAttribute(1, Double.parseDouble(country[18]));
                Double marrigeStatus = 0.0;
                if (country[7].startsWith("Married")) {
                    marrigeStatus = 1.0;
                }
                person.addAttribute(2, marrigeStatus);
                Double employer = 1.0;
                if ("0".equals(country[30])) {
                    employer = 0.0;
                }
                person.addAttribute(3, marrigeStatus);
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

        for (Integer index = 0; index < list.size(); ++index) {
            System.out.println(list.get(index).getInstance());
        }
    }

        //Dataset data = FileHandler.loadDataset(new File("train.csv"), ",");
        //System.out.println(data);

    //}
}
