package ml;

import net.sf.javaml.classification.Classifier;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.DefaultDataset;
import net.sf.javaml.core.Instance;
import utils.DatasetProcessor;

import java.util.Map;

public class DTree implements Classifier {

    public DTree() {
        this.root = new Node();
    }

    @Override
    public void buildClassifier(Dataset instances) {
        this.root = createNode(instances, 0);
    }

    @Override
    public Object classify(Instance instance) {
        return classify(instance, root);
    }

    @Override
    public Map<Object, Double> classDistribution(Instance instance) {
        return null;
    }

    private Integer classify(Instance instance, Node node) {
        if (node.leaf) {
            return node.classValue;
        }
        if (instance.get(node.index) <= node.value) {
            return classify(instance, node.left);
        } else {
            return classify(instance, node.right);
        }
    }

    private Node createNode(Dataset instances, Integer prevClass) {
        Node node = new Node();
        Node workNode = new Node();
        if (instances.size() == 0) {
            node.leaf = true;
            node.classValue = prevClass;
            return node;
        }

        Integer dominantClass = DatasetProcessor.getDominantClass(instances);
        if (DatasetProcessor.getClassNumber(instances) == 1) {
            node.leaf = true;
            node.classValue = dominantClass;
            return node;
        }

        Double minGain = null;
        Integer minIndex = 0;
        for (Integer index: DatasetProcessor.getIndeces(instances)) {
            workNode.index = index;
            workNode.value = DatasetProcessor.getMedian(instances, index);

            if (minGain != null) {
                workNode.index = index;
                workNode.value = DatasetProcessor.getMedian(instances, index);
                Dataset leftSubnode = getSubset(workNode, instances, false);
                Dataset rightSubnode = getSubset(workNode, instances, true);
                Double currentGain = DatasetProcessor.getEntropy(leftSubnode)
                        + DatasetProcessor.getEntropy(rightSubnode);
                if (currentGain < minGain) {
                    minGain = currentGain;
                    minIndex = index;
                }
            } else {
                minIndex = index;
                minGain = DatasetProcessor.getEntropy(getSubset(workNode, instances, false))
                        + DatasetProcessor.getEntropy(getSubset(workNode, instances, true));
            }
        }

        node.index = minIndex;
        node.classValue = DatasetProcessor.getDominantClass(instances);
        node.value = DatasetProcessor.getMedian(instances, node.index);
        Dataset leftSubnode = getSubset(workNode, instances, false);
        Dataset rightSubnode = getSubset(workNode, instances, true);

        node.left = createNode(leftSubnode, node.classValue);
        node.right = createNode(rightSubnode, node.classValue);



        return node;
    }

    private Dataset getSubset(Node node, Dataset instances, Boolean right) {
        Dataset dataset = new DefaultDataset();

        for (Instance instance: instances) {
            if ((right) ^ (instance.get(node.index) <= node.value)) {
                dataset.add(instance);
            }
        }

        if (dataset.size() == instances.size()) {
            dataset.clear();
            for (Integer index = 0; index < instances.size() / 2; ++index) {
                dataset.add(instances.get(index));
            }
        }

        if (dataset.size() == 0) {
            dataset.clear();
            for (Integer index = instances.size() / 2; index < instances.size(); ++index) {
                dataset.add(instances.get(index));
            }
        }

        return dataset;
    }

    Node root;
}
