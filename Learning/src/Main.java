import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;

import java.io.File;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {
        Dataset data = FileHandler.loadDataset(new File("train.csv"), ",");
        System.out.println(data);

    }
}
