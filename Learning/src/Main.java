import domains.Person;
import net.sf.javaml.core.Instance;
import net.sf.javaml.core.SparseInstance;
import utils.PersonReader;

import java.io.*;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<Person> list = PersonReader.readPersonsList("train.csv");

        for (Integer index = 0; index < list.size(); ++index) {
            System.out.println(list.get(index).getInstance());
        }
    }
}
