package ml;

import domains.Person;
import net.sf.javaml.core.Instance;

public class Validator {
    public static Boolean isNoize(Person person) {
        if ((person.getClassValue() == 0) && (person.getAttribute(1) > 3000)) {
            return true;
        }
        if ((person.getClassValue() == 1) && (person.getAttribute(0) <= 18)) {
            return true;
        }

        return false;
    }

    public static Boolean isNoize(Instance instance) {
        if ((instance.classValue().equals(0)) && (instance.get(1) > 3000)) {
            return true;
        }
        if ((instance.classValue().equals(1)) && (instance.get(0) <= 18)) {
            return true;
        }

        return false;
    }
}
