package ml;

import domains.Person;
import net.sf.javaml.core.Instance;

import java.util.ArrayList;

public class KNN {
    public KNN() {
        objectsList = new ArrayList<Person>();
    }

    public void learn(Person person) {
        if (!Validator.isNoize(person)) {
            objectsList.add(person);
        }
    }

    public void learn(ArrayList<Person> persons) {
        for (Person person: persons) {
            learn(person);
        }
    }

    public Integer predict(Instance instance) {
        if (instance.get(0) <= 18) {
            return 0;
        }
        return 1;
    }

    private ArrayList<Person> objectsList;
}
