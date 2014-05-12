import domains.Person;
import ml.DTree;
import ml.Validator;
import net.sf.javaml.core.Instance;
import utils.DatasetProcessor;
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

//        ArrayList<Double> errorsList = ML_Test.getStandardKNNErrors(10, dataset, testDataSet);
//        System.out.println(errorsList);
//        errorsList = ML_Test.getKNNErrors(10, dataset, testDataSet);
//        System.out.println(errorsList);
//        System.out.println(ML_Test.getStandardKDTreeErrors(4, dataset, testDataSet));

        DTree dTree = new DTree();
//        for (Instance instance: dataset) {
//            System.out.println(instance.get(2));
//        }
        dTree.buildClassifier(dataset);
        //System.out.println(DatasetProcessor.getMedian(dataset, 3));
        //System.out.println((Math.log(0) / Math.log(2)));

    }
}
