import java.util.*;


public class TreeSet<T extends Comparable<T>> implements SortedSet<T> {
    BinarySearchTree<T> bst;
    Comparator<T> cmp = new entryComparator<T>();

    private class entryComparator<T> implements Comparator<T>{
        @Override
        public int compare(T t, T t1) {
            if(t.hashCode() > t1.hashCode()){
                return 1;
            }else if(t.hashCode()<t1.hashCode()){
                return -1;
            }
            return 0;
        }

    }


    public TreeSet(){
        this.bst = new BinarySearchTree<>();
    }

    public TreeSet(Collection<T> collection){
        this.bst = new BinarySearchTree<>();
        addAll(collection);
    }

    public TreeSet(Comparator<T> comparator){
        this.bst = new BinarySearchTree<>();
        cmp = comparator;
    }



    @Override
    public Comparator<? super T> comparator() {
        return cmp;
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
        return new TreeSetIterator(bst.getRoot().getMinNode());
    }


    public Iterator<T> descendingIterator(){
        List<T> list = bst.makeList();
        return new DescendingIterator(list);
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
        Object[] arr1 = toArray();

        if(t1s != null){
            Object[] arr = new Object[t1s.length];
            for(int i = 0; i<arr1.length; i++){
                arr[i] = arr1[i];
            }
            return (T1[]) arr;
        }
        throw new NullPointerException();
    }

    @Override
    public boolean add(T t) {
        if(!contains(t))
            if(bst.add(t)) {
                maintainLinks();
                return true;
            }
        return false;

    }

    public void maintainLinks(){
        List<T> dataList = bst.makeList();
        int size = dataList.size();
        BinarySearchTreeNode<T> node;
        for(int i = 0; i < size; i++){
            node = bst.getRoot().getNode(dataList.get(i));
            node.larger = (i == size-1) ? null : bst.getRoot().getNode(dataList.get(i+1));
            if(i!=0){
                node.smaller = bst.getRoot().getNode(dataList.get(i-1));
            }
        }
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


    public String toString(){
        return bst.toString();
    }

    private class DescendingIterator implements Iterator<T>{
        List<T> list;
        int size;
        int i;

        public DescendingIterator(List<T> list){
            this.list = list;
            this.size = list.size();
            int i = size();
        }
        @Override
        public boolean hasNext() {
            if (i == 0) {
                return false;
            } else
                return true;

        }


        @Override
        public T next() {
            if(i==size){
                throw new java.util.NoSuchElementException();
            }else{
                i--;
                return list.get(i);
            }
        }

    }


    private class TreeSetIterator implements Iterator<T> {
        BinarySearchTreeNode<T> node;
        int i;
        int size;



        public TreeSetIterator(BinarySearchTreeNode<T> node){
            this.node = node;
            this.size = size();
            this.i = 0;
        }


        @Override
        public boolean hasNext() {
            if(i == size -1) {
                return false;
            } else
                return true;
        }

        @Override
        public T next() {
            if(node.larger != null){
                node = node.larger;
            }else{
                System.out.println();
                throw new java.util.NoSuchElementException();
            }
            if(i == 0){
                i++;
                return node.smaller.getData();
            }else if(i == size -1){
                i++;
                return node.getData();
            }
            return null;
        }
    }

    public Object clone(){
        return this;
    }

    public T ceiling(T e){
        T last = last();
        if(last == e){
            return e;
        }else if(last.compareTo(e)<0){
            return null;
        }
        List<T> list = bst.makeList();
        for(int i = list.size(); i > 0; i--){
            if(list.get(i).compareTo(e)< 0){
                return list.get(i+1);
            }
        }
        return null;
    }



    public Set descendingSet(){
        List<T> list = bst.makeList();
        Comparator<T> comparator = new Comparator<T>() {
            @Override
            public int compare(T t, T t1) {
                if(t.hashCode() > t1.hashCode()){
                    return -1;
                }else if(t.hashCode()<t1.hashCode()){
                    return 1;
                }
                return 0;
            }
        };
        list.sort(comparator);
        return (Set) list;

    }

    public T floor(T t){
        List<T> list = bst.makeList();
        T first = first();
        if(first.compareTo(t) > 0)
            return null;
        if(first.equals(t)){
            return first;
        }
        for(T item : list){
            if(item.compareTo(t) < 0)
                return item;
        }
        return null;
    }

    public T higher(T t){
        T item = ceiling(t);
        return (t.equals(item)) ? null : item;
    }
    public T lower(T t){
        T item = floor(t);
        return (t.equals(item)) ? null : item;
    }

    public T pollFirst(){
        T first = first();
        return (remove(first)) ? first: null;
    }

    public T pollLast(){
        T last = last();
        return (remove(last)) ? last : null;
    }






}
