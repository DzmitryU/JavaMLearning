package ml;

import domains.ClassDistance;
import domains.Person;
import net.sf.javaml.core.Instance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class KNN {
    public KNN(Integer neighboursNumber) {
        this.neighboursNumber = neighboursNumber;
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
        if (instance.get(1) > 3000) {
            return 1;
        }

        List<ClassDistance> list = new ArrayList<ClassDistance>();
        for (Person person: objectsList) {
            Double distance = 0.0;

        }

        return 1;
    }

    private List<Person> objectsList;
    private Integer neighboursNumber;
}
