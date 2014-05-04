import domains.Person;
import ml.Validator;
import tests.ML_KNN_Test;
import utils.PersonReader;

import net.sf.javaml.core.Instance;
import net.sf.javaml.core.SparseInstance;
import net.sf.javaml.tools.data.FileHandler;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.classification.Classifier;

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
        System.out.println(dataset.size());
        System.out.println(dataset);
        System.out.println(testDataSet);
        ArrayList<Double> errorsList = ML_KNN_Test.getKNNErrors(10, dataset, testDataSet);
        System.out.println(errorsList);
    }
}
