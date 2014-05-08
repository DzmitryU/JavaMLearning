package ml;

import domains.ClassDistance;
import domains.Person;
import net.sf.javaml.classification.Classifier;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import utils.InstanceProcessor;

import java.util.*;

public class KNN implements Classifier {
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

    @Override
    public void buildClassifier(Dataset instances) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object classify(Instance instance) {
        if (instance.get(0) <= 18) {
            return 0;
        }
        if (instance.get(1) > 3000) {
            return 1;
        }

        List<ClassDistance> list = new ArrayList<ClassDistance>();
        for (Person person: objectsList) {
            Double distance = InstanceProcessor.getDistance(person.getInstance(), instance);
            list.add(new ClassDistance(distance, person.getClassValue()));
        }

        Collections.sort(list);
        Integer maxRealNeighboursNumber = Math.min(list.size(), neighboursNumber);
        Integer classSum = 0;
        for (Integer index = 0; index < maxRealNeighboursNumber; ++index) {
            classSum += list.get(index).getClassValue();
        }

        return Math.round(classSum / maxRealNeighboursNumber.doubleValue());
    }

    @Override
    public Map<Object, Double> classDistribution(Instance instance) {
        return null;
    }

    private List<Person> objectsList;
    private Dataset objectDataset;
    private Integer neighboursNumber;

}
