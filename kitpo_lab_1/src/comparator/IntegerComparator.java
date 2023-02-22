package comparator;

import type.IntegerType;

import java.io.Serializable;

public class IntegerComparator implements Comparator, Serializable {
    @Override
    public double compare(Object firstObject, Object secondObject) {
        return ((IntegerType)firstObject).getTypeValue() - ((IntegerType)secondObject).getTypeValue();
    }
}
