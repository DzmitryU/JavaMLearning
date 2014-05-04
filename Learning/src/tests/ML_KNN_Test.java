package tests;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.core.Instance;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.distance.ChebychevDistance;
import net.sf.javaml.distance.EuclideanDistance;

import java.util.ArrayList;

public class ML_KNN_Test {
    public static ArrayList<Double> getKNNErrors(Integer maxN, Dataset dataset, Dataset testDataSet) {
        ArrayList<Double> resultList = new ArrayList<Double>();
        for (Integer neighborsNumber = 1; neighborsNumber <= maxN; ++neighborsNumber) {
            Classifier knn = new KNearestNeighbors(neighborsNumber);
            knn.buildClassifier(dataset);

            Integer correct = 0, wrong = 0;
            for (Integer index = 0; index < testDataSet.size(); ++index) {
                Instance instance = testDataSet.get(index);
                Object predictedClassValue = knn.classify(instance);
                Object realClassValue = instance.classValue();
                if (predictedClassValue.equals(realClassValue))
                    correct++;
                else
                    wrong++;
            }

            resultList.add((100.0 * wrong) / (double) (testDataSet.size()));
        }
        return  resultList;
    }
}
