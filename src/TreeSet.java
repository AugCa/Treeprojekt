import java.lang.reflect.Array;
import java.util.*;


public class TreeSet<T extends Comparable<T>> implements SortedSet<T> {
    BinarySearchTree<T> bst = new BinarySearchTree<T>();



    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    @Override
    public SortedSet<T> subSet(T t, T e1) {
        List<T> list = bst.makeList();
        TreeSet<T> ts = new TreeSet<>();
        for (T value : list) {
            int cmp = value.compareTo(t);
            int cmp2 = value.compareTo(e1);
            if (cmp >= 0 && cmp2 <= 0) {
                ts.add(value);
            }
        }
        return ts;


    }

    @Override
    public SortedSet<T> headSet(T t) {
        List<T> list = bst.makeList();
        TreeSet<T> ts = new TreeSet<>();
        for (T value : list) {
            if (value.compareTo(t) >= 0)
                ts.add(value);
        }
        return ts;

    }

    @Override
    public SortedSet<T> tailSet(T t) {
        List<T> list = bst.makeList();
        TreeSet<T> ts = new TreeSet<>();
        for (T value : list) {
            if (value.compareTo(t) <= 0)
                ts.add(value);
        }
        return ts;


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
        List<T> list = bst.makeList();
        return new TreeSetIterator(list);
    }


    @Override
    public Object[] toArray() {
        List<T> list = bst.makeList();
        Object[] arr = new Object[list.size()];
        list.toArray(arr);
        return arr;
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        return null;
    }

    @Override
    public boolean add(T t) {
        return (!contains(t)) && bst.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return bst.remove((T) o);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if(size() < collection.size()){
            return false;
        }
        Iterator<?> itr = collection.iterator();
        while(itr.hasNext()){
            if(!contains(itr.next())){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean hasChanged = false;
        for(T e : collection){
            if(add(e))
                hasChanged = true;

        }
        return hasChanged;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        Iterator<T> itr = iterator();
        boolean hasChanged = false;
        while(itr.hasNext()){
            T data = itr.next();
            if(!collection.contains(data)){
                remove(data);
                hasChanged = true;
            }
        }
        return hasChanged;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean success = false;
        for(Object e : collection){
            if(bst.remove((T) e))
                success = true;
        }
        return success;
    }

    @Override
    public void clear() {
        bst.clear();
    }


    private class TreeSetIterator implements Iterator<T> {
        List<T> list;
        int size;
        int i = 0;


        public TreeSetIterator(List<T> list){
            this.list = list;
            this.size = list.size();
        }


        @Override
        public boolean hasNext() {
            if (i == size) {
                return false;
            } else
                return true;

        }



        @Override
        public T next() {

            if(i==size){
                throw new java.util.NoSuchElementException();
            }else{
                i++;
                return list.get(i-1);
            }
        }
    }


}
