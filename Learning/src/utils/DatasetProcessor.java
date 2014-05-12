package utils;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class DatasetProcessor {
    public static Double getEntropy(Dataset instances) {
        HashMap<Integer, Integer> classMap = getClassmap(instances);
        Double entropy = 0.0;
        Double classNumber = ((Integer)instances.size()).doubleValue();
        for (Integer key : classMap.keySet()) {
            Double part = classMap.get(key) / classNumber;
            if (part >= 0.001) {
                entropy -= (part * (Math.log(part) / Math.log(2)));
            }
        }
        return entropy;
    }

    public static Integer getDominantClass(Dataset instances) {
        HashMap<Integer, Integer> classMap = getClassmap(instances);
        Integer maxClassKey = null;
        for (Integer key: classMap.keySet()) {
            if (maxClassKey != null) {
                if (classMap.get(key) > classMap.get(maxClassKey)) {
                    maxClassKey = key;
                }
            } else {
                maxClassKey = key;
            }
        }

        return maxClassKey;
    }

    public static Integer getClassNumber(Dataset instances) {
        HashMap<Integer, Integer> classMap = getClassmap(instances);
        return classMap.size();
    }

    public static Double getMedian(Dataset instances, Integer index) {
        ArrayList<Double> list = new ArrayList<Double>();
        for (Instance instance: instances) {
            list.add(instance.get(index));
        }
        Collections.sort(list);
        Double median = list.get(list.size() / 2);
        if ((median - list.get(list.size() - 1) < 0.001) && (median - list.get(0) > 0.001)) {
            Integer lIndex = list.size() / 2;
            --lIndex;
            while ((lIndex != 0) && (median - list.get(lIndex) < 0.001)) {
                --lIndex;
            }
            median = list.get(lIndex);
        }

        return median;
    }

    public static ArrayList<Integer> getClasses(Dataset instances) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (Integer key: getClassmap(instances).keySet()) {
            list.add(key);
        }
        return list;
    }

    public static ArrayList<Integer> getIndeces(Dataset instances) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Instance instance = instances.get(0);
        for (Integer key: instance.keySet()) {
            list.add(key);
        }

        return list;
    }

    private static HashMap<Integer, Integer> getClassmap(Dataset instances) {
        HashMap<Integer, Integer> classMap = new HashMap<Integer, Integer>();
        for (Instance instance: instances) {
            Integer key = (Integer)instance.classValue();
            Integer value = 1;
            if (classMap.containsKey(key)) {
                value = classMap.get(key) + 1;
            }
            classMap.put(key, value);
        }
        return classMap;
    }
}
