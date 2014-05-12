package ml;

import domains.ClassDistance;
import net.sf.javaml.classification.Classifier;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import utils.InstanceProcessor;

import java.util.*;

public class KNN implements Classifier {
    public KNN(Integer neighboursNumber) {
        this.neighboursNumber = neighboursNumber;
    }

    @Override
    public void buildClassifier(Dataset instances) {
        this.objectDataset = instances;
    }

    @Override
    public Integer classify(Instance instance) {
        if (instance.get(0) <= 18) {
            return 0;
        }
        if (instance.get(1) > 3000) {
            return 1;
        }

        List<ClassDistance> list = new ArrayList<ClassDistance>();
        for (Instance baseInstance: objectDataset) {
            Double distance = InstanceProcessor.calculateDistance(baseInstance, instance);
            list.add(new ClassDistance(distance, baseInstance.classValue()));
        }

        Collections.sort(list);
        Integer maxRealNeighboursNumber = Math.min(list.size(), neighboursNumber);
        Integer classSum = 0;
        for (Integer index = 0; index < maxRealNeighboursNumber; ++index) {
            classSum += (Integer)list.get(index).getClassValue();
        }

        return ((Long)Math.round(classSum / maxRealNeighboursNumber.doubleValue())).intValue();
    }

    @Override
    public Map<Object, Double> classDistribution(Instance instance) {
        return null;
    }

    private Dataset objectDataset;
    private Integer neighboursNumber;

}
