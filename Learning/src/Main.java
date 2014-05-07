import domains.Person;
import ml.Validator;
import net.sf.javaml.core.Instance;
import tests.ML_Standart_Test;
import utils.PersonReader;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;

import java.io.*;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws IOException {

        ArrayList<Person> list = PersonReader.readPersonsList("train.csv");
        Dataset dataset = new DefaultDataset();
        Dataset testDataSet = new DefaultDataset();
        Integer M = 500;
        for (Integer index = 0; index < list.size() - M; ++index) {
            if (!Validator.isNoize(list.get(index))) {
                dataset.add(list.get(index).getInstance());
            }
        }
        for (Integer index = list.size() - M; index < list.size(); ++index) {
            testDataSet.add(list.get(index).getInstance());
        }

        System.out.println(list.get(1).getInstance());
        System.out.println(list.get(2).getInstance().keySet().equals(list.get(1).getInstance().keySet()));
        Instance instance = list.get(1).getInstance();
        System.out.println(instance.keySet());
        for (Double value : instance) {
            System.out.println(value);
        }
//        System.out.println(dataset.size());
//        System.out.println(dataset);
//        System.out.println(testDataSet);
//        ArrayList<Double> errorsList = ML_Standart_Test.getKNNErrors(10, dataset, testDataSet);
//        System.out.println(errorsList);
//        System.out.println(ML_Standart_Test.getKDTreeErrors(4, dataset, testDataSet));
    }
}
