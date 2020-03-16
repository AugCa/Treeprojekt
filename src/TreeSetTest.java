import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class TreeSetTest {
    @Test
    public static class ReversedIntegerComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            return -(((Integer) o1).compareTo((Integer) o2));
        }
        public boolean equals(Object o1, Object o2) {
            return ((Integer) o1).compareTo((Integer) o2) == 0;
        }
    }

}