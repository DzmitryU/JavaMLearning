package utils;

import net.sf.javaml.core.Instance;

public class InstanceProcessor {
    public static Double calculateDistance(Instance instance1, Instance instance2) {
        if (instance1.keySet().equals(instance2.keySet())) {
            Double distance = 0.0;
            for (Integer key: instance1.keySet()) {
                distance += Math.abs(instance1.get(key) - instance2.get(key));
            }
            return distance;
        } else {
            return Double.MAX_VALUE;
        }
    }
}
