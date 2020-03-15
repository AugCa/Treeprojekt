import java.util.*;


public class MyTreeSet<T extends Comparable<T>> implements SortedSet<T> {
    BinarySearchTree<T> bst = new BinarySearchTree<T>();

    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    @Override
    public SortedSet<T> subSet(T t, T e1) {
        return null;
    }

    @Override
    public SortedSet<T> headSet(T t) {
        return null;
    }

    @Override
    public SortedSet<T> tailSet(T t) {
        return null;
    }

    @Override
    public T first() {
        return bst.getRoot().findMin();
    }

    @Override
    public T last() {
        return bst.getRoot().findMax();
    }

    @Override
    public int size() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.size() == 0;
    }

    @Override
    public boolean contains(Object o) {
        T data = (T) o;
        return (bst.contains(data));
    }

    @Override
    public Iterator<T> iterator() {
        return new TreeSetIterator();
    }


    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        return null;
    }

    @Override
    public boolean add(T t) {
        return (contains(t)) && bst.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return false;
    }

    @Override
    public void clear() {

    }

    private class TreeSetIterator implements Iterator<T> {
        private BinarySearchTreeNode<T> node = bst.getRoot();
        int size = size();
        int i = 0;
        boolean right;


        @Override
        public boolean hasNext() {
            if (i == size) {
                return false;
            } else
                return true;

        }

        @Override
        public T next() {

            if (right = true) {
                right = false;
                node = node.right;
                i++;
                return node.getData();
            } else if (node.left != null && node.right != null) {
                right = true;
                i++;
                return node.left.getData();
            } else if (node.left != null) {
                i++;
                return node.left.getData();
            }else if (node.right != null) {
                i++;
                node = node.right;
                return node.getData();
            }
            throw new java.util.NoSuchElementException();
        }
    }




}
