package ml;

import net.sf.javaml.core.Dataset;
import utils.DatasetProcessor;

public class Node {

    public Node() {
    }

    public Integer index = 0;
    public Double value = 0.0;
    public Boolean leaf = false;
    public Integer classValue = 0;
    public Node right = null;
    public Node left = null;
}
