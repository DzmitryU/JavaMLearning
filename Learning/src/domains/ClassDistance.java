package domains;

public class ClassDistance implements Comparable<ClassDistance> {

    public ClassDistance(Double distance, Integer classValue) {
        this.distance = distance;
        this.classValue = classValue;
    }

    @Override
    public int compareTo(ClassDistance o) {
        return distance < o.distance ? -1 : distance > o.distance ? 1 : 0;
    }

    public Integer getClassValue() {
        return classValue;
    }

    public Double getDistance() {
        return distance;
    }

    private Double distance;
    private Integer classValue;
}
