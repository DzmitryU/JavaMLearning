package tests;

import ml.DTree;
import ml.KNN;
import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KDtreeKNN;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.core.kdtree.KDTree;
import net.sf.javaml.core.Instance;
import net.sf.javaml.core.Dataset;

import java.util.ArrayList;

public class ML_Test {

    private static Double getError(Classifier classifier, Dataset testDataSet) {
        Integer correct = 0, wrong = 0;
        for (Integer index = 0; index < testDataSet.size(); ++index) {
            Instance instance = testDataSet.get(index);
            Object predictedClassValue = classifier.classify(instance);
            Object realClassValue = instance.classValue();
            if (predictedClassValue.equals(realClassValue))
                correct++;
            else {
                wrong++;
            }
        }
        return (100.0 * wrong) / (double) (testDataSet.size());
    }

    public static ArrayList<Double> getStandardKNNErrors(Integer maxN, Dataset dataset, Dataset testDataSet) {
        ArrayList<Double> resultList = new ArrayList<Double>();
        for (Integer neighborsNumber = 1; neighborsNumber <= maxN; ++neighborsNumber) {
            Classifier knn = new KNearestNeighbors(neighborsNumber);
            knn.buildClassifier(dataset);

            resultList.add(getError(knn, testDataSet));
        }
        return  resultList;
    }

    public static ArrayList<Double> getKNNErrors(Integer maxN, Dataset dataset, Dataset testDataSet) {
        ArrayList<Double> resultList = new ArrayList<Double>();
        for (Integer neighborsNumber = 1; neighborsNumber <= maxN; ++neighborsNumber) {
            Classifier knn = new KNN(neighborsNumber);
            knn.buildClassifier(dataset);

            resultList.add(getError(knn, testDataSet));
        }
        return  resultList;
    }

    public static Double getDTreeErrors(Dataset dataset, Dataset testDataSet) {

        Classifier dTree = new DTree();
        dTree.buildClassifier(dataset);
         return getError(dTree, testDataSet);
    }

    public static Double getStandardKDTreeErrors(Integer dimensionNumber, Dataset dataset, Dataset testDataSet) {
        Classifier kdTree = new KDtreeKNN(dimensionNumber);
        kdTree.buildClassifier(dataset);

        return getError(kdTree, testDataSet);
    }
}

