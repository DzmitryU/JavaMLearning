package ml;

import domains.Person;

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
}
