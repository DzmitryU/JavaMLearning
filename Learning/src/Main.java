import domains.Person;
import ml.DTree;
import ml.Validator;
import net.sf.javaml.core.Instance;
import tests.ML_Test;
import utils.ConstantHolder;
import utils.DatasetProcessor;
import utils.PersonReader;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;


public class Main {

    public static void main(String[] args) throws IOException {

        Dataset instances = DatasetProcessor.ParsePersonList(PersonReader.readPersonsList("train.csv"));
        Dataset dataset = DatasetProcessor.getLearningDataset(instances, ConstantHolder.testDatasetSize);
        Dataset testDataSet = DatasetProcessor.getTestDataset(instances, ConstantHolder.testDatasetSize);

        System.out.println("Standard KNN:");
        Date startDate = new Date();
        System.out.println(ML_Test.getStandardKNNErrors(10, dataset, testDataSet));
        Date stopDate = new Date();
        System.out.println("Time: " + (stopDate.getTime() - startDate.getTime()));
        System.out.println("*******************************");

        System.out.println("Developed KNN:");
        startDate = new Date();
        System.out.println(ML_Test.getKNNErrors(10, dataset, testDataSet));
        stopDate = new Date();
        System.out.println("Time: " + (stopDate.getTime() - startDate.getTime()));
        System.out.println("*******************************");

        System.out.println("Standard DTree:");
        startDate = new Date();
        System.out.println(ML_Test.getStandardKDTreeErrors(4, dataset, testDataSet));
        stopDate = new Date();
        System.out.println("Time: " + (stopDate.getTime() - startDate.getTime()));
        System.out.println("*******************************");

        System.out.println("Developed DTree:");
        startDate = new Date();
        System.out.println(ML_Test.getDTreeErrors(dataset, testDataSet));
        stopDate = new Date();
        System.out.println("Time: " + (stopDate.getTime() - startDate.getTime()));

    }
}
