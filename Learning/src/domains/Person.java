package domains;

import net.sf.javaml.core.Instance;
import net.sf.javaml.core.SparseInstance;

import java.util.HashMap;
import java.util.Set;

public class Person {

    public Person(Integer initialClass) {
        classValue = initialClass;
        attributes = new HashMap<Integer, Double>();
    }

    public Instance getInstance() {
        Instance instance = new SparseInstance(attributes.size());
        instance.putAll(attributes);
        instance.setClassValue(classValue);
        return instance;
    }

    public void addAttribute(Integer attributeIndex, Double attributeValue) {
        attributes.put(attributeIndex, attributeValue);
    }

    private Integer classValue;
    private HashMap<Integer, Double> attributes;
}
