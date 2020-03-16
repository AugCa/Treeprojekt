import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

class TreeSetTest {

    public static class ReversedIntegerComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            return -(((Integer) o1).compareTo((Integer) o2));
        }
        public boolean equals(Object o1, Object o2) {
            return ((Integer) o1).compareTo((Integer) o2) == 0;
        }
    }

    TreeSet ts;
    Object objArray[] = new Object[1000];

    /**
     * java.util.TreeSet#TreeSet()
     */

    @Test
    public void test_Constructor() {
        // Test for method java.util.TreeSet()
        assertTrue(new TreeSet().isEmpty(), "Did not construct correct TreeSet");
    }


    @Test
    void comparator() {
    }

    @Test
    void subSet() {
    }

    @Test
    void headSet() {
    }

    @Test
    void tailSet() {
    }

    @Test
    void first() {
    }

    @Test
    void last() {
    }

    @Test
    void size() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void contains() {
    }

    @Test
    void iterator() {
    }

    @Test
    void toArray() {
    }

    @Test
    void testToArray() {
    }

    @Test
    void add() {
    }

    @Test
    void remove() {
    }

    @Test
    void containsAll() {
    }

    @Test
    void addAll() {
    }

    @Test
    void retainAll() {
    }

    @Test
    void removeAll() {
    }

    @Test
    void clear() {
    }
}