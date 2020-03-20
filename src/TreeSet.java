import java.util.*;


public class TreeSet<T extends Comparable<T>> implements SortedSet<T> {
    private BinarySearchTree<T> bst;
    private Comparator<T> cmp;
    private int size;
    private T head;
    private T tail;
    private BinarySearchTreeNode<T> prev;

    private class EntryComparator<T> implements Comparator<T>{
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

    // Instansierar ett nytt binarärt sökträd med en tom konstruktor
    public TreeSet(){
        this.bst = new BinarySearchTree<>();
        this.cmp = new EntryComparator<>();
        this.size = 0;
    }

    //Skapar ett treeset med element från collection.
    public TreeSet(Collection<T> collection){
        this.bst = new BinarySearchTree<>();
        this.cmp = new EntryComparator<>();
        this.size = 0;
        addAll(collection);
    }

    //Skapar ett treeset med en anpassad comparator
    public TreeSet(Comparator<T> comparator){
        this.bst = new BinarySearchTree<>();
        this.size = 0;
        cmp = comparator;
    }



    @Override
    public Comparator<? super T> comparator() {
        return cmp;
    }

    @Override
    public SortedSet<T> subSet(T t, T e1) {
        //Ordna alla element i en sorterad lista
        List<T> list = bst.makeList();
        //Skapa ett nytt treeset
        TreeSet<T> subset = new TreeSet<>();
        for (T value : list) {
            //Lägg in element i subset om värdet är inom range för t (start) och e1 (slut)
            int cmp = value.compareTo(t);
            int cmp2 = value.compareTo(e1);
            if (cmp >= 0 && cmp2 <= 0) {
                subset.add(value);
            }
        }
        //returnera nya treeset
        return subset;
    }

    @Override
    public SortedSet<T> headSet(T t) {
        //Iterera genom settet, om next() är mindre än t, lägg till i headSet
        TreeSet<T> headSet = new TreeSet<>();
        Iterator<T> itr = iterator();
        while(itr.hasNext()){
            T data = itr.next();
            if(t.compareTo(data) > 0){
                headSet.add(data);
            }
        }
        return headSet;
    }

    @Override
    public SortedSet<T> tailSet(T t) {
        List<T> list = bst.makeList();
        TreeSet<T> tailSet = new TreeSet<>();
        for (T value : list) {
            //Lägg in i tailSet om värdet är större än t
            if (value.compareTo(t) > 0)
                tailSet.add(value);
        }
        return tailSet;
    }

    @Override
    public T first() {
        //returnera head för att vårt treeset implementerar länkadlista egenskaper, annars vänstraste värdet i trädet (findMin).
        return head;
    }

    @Override
    public T last() {
        //returnera tail för att vårt treeset implementerar länkadlista egenskaper, annars högraste värdet i trädet (findMax).
        return tail;
    }


    @Override
    public int size() {
        //returnera trädets storlek
        return size;
    }

    @Override
    // Boolean metod som blir sann ifall binära sökträdets storlek är 0, dvs att den är tom
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        T data = (T) o;
        //Kolla om o finns i trädet
        return (bst.contains(data));
    }

    @Override
    public Iterator<T> iterator() {
        //Skapar en iterator med minsta noden som argument för att iterera genom länkade noder till högsta
        return new TreeSetIterator(bst.getRoot().getMinNode());
    }


    public Iterator<T> descendingIterator(){
        //Skapar en iterator med högsta noden som argument för att iterera neråt genom länkade noder till lägsta
        return new DescendingIterator(bst.getRoot().getMaxNode());
    }


    @Override
    public Object[] toArray() {
        //Gör en ordnad lista av elementen och lägg in i en array, returnera arrayen
        List<T> list = bst.makeList();
        Object[] arr = new Object[list.size()];
        list.toArray(arr);
        return arr;
    }

    @Override
    public <T1> T1[] toArray(T1[] t1s) {
        //Gör en array av treeset
        Object[] arr1 = toArray();
        //Om t1s inte är null, gör en array med längden av t1s, lägg in alla värden på respektive plats
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

        //Om värdet inte finns i treeset, lägg till i bst
        if(!contains(t) && bst.add(t)){
            //Om det lyckas, upprätthåll länkarna till nästa mindre och nästa högre nod
            size++;
            if(size == 1){
                head = tail = t;
            }
            maintainLinks(bst.getRoot().getNode(t));
            return true;
            }
        return false;
    }

    public void maintainLinks(BinarySearchTreeNode<T> node){
        T data = node.getData();
        //om nya noden inte var första elementet (root)
        if(size !=1){
            //om noden är mindre än huvudnoden blir den nya huvudnoden
            if(data.compareTo(head) < 0){
                node.larger = bst.getRoot().getNode(head);
                node.larger.smaller = node;
                head = data;
                //om noden är större än tail noden blir den nya tailnoden
            }else if(data.compareTo(tail) > 0){
                node.smaller = bst.getRoot().getNode(tail);
                node.smaller.larger = node;
                tail = data;
            }else{
                //fixLinks fixar länkar för hela trädet så används bara om den nya noden inte var head eller tail
                fixLinks(bst.getRoot());
            }

        }
    }

    public void fixLinks(BinarySearchTreeNode<T> node){
        if(node == null){
            return;
        }
        //Börjar från vänstraste noden
        fixLinks(node.left);
        //Den senaste besökta kommer alltid vara mindre än nuvarande nod och vice versa
        if(prev!= null){
            node.smaller = prev;
            prev.larger = node;
        }
        prev = node;
        //Sedan högra sidan
        fixLinks(node.right);

    }


    @Override
    public boolean remove(Object o) {
        if(bst.remove((T) o)){
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        //Om storleken är mindre än samlingen så är containsall alltid falskt
        if(size() < collection.size()){
            return false;
        }
        Iterator<?> itr = collection.iterator();
        //Iterera genom samlingen och kolla contains för varje element
        while(itr.hasNext()){
            if(!contains(itr.next()))
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean hasChanged = false;
        //För varje element i samlingen, lägg till i treeset, dubletter hanteras i add
        for(T e : collection){
            //Om något av elementet läggs till i samlingen, så har treeset ändrat
            if(add(e))
                hasChanged = true;
        }
        return hasChanged;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean hasChanged = false;
        //för varje objekt i samlingen, om remove på objektet returnerar true så är haschanged true;
        for(Object item : collection)
            if(remove(item))
                hasChanged = true;
        return hasChanged;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean success = false;
        //Typ samma som RetainAll
        for(Object e : collection){
            if(bst.remove((T) e))
                success = true;
        }
        return success;
    }

    // Sätter roten till null och är därav snabbare än exempelvis remove / removeAll
    @Override
    public void clear() {
        //Använder binarysearchtree clear
        size = 0;
        head = tail = null;
        bst.clear();
    }

    public String toString(){
        //Använder binarysearchtree tostring
        return bst.toString();
    }

    private class DescendingIterator implements Iterator<T>{
        BinarySearchTreeNode<T> node;
        int size;
        int i;

        public DescendingIterator(BinarySearchTreeNode<T> node){
            //tailnoden
            this.node = node;
            this.size = size();
            int i = size();
        }
        @Override
        public boolean hasNext() {
            if (i == 0)
                return false;
             else
                return true;
        }

        @Override
        public T next() {
            if(i==size){
                i--;
                //sista noden
                return node.getData();
            } else{
                i--;
                //Nästa nod som är mindre
                node = node.smaller;
                return node.getData();
            }
        }

    }

    private class TreeSetIterator implements Iterator<T> {
        BinarySearchTreeNode<T> node;
        int i;
        int size;

        public TreeSetIterator(BinarySearchTreeNode<T> node){
            //headnoden
            this.node = node;
            this.size = size();
            this.i = 0;
        }

        @Override
        public boolean hasNext() {
            if(i == size ) {
                return false;
            } else
                return true;
        }

        @Override
        public T next() {

            if(i == 0){
                i++;
                return node.getData();
            }else{
                i++;
                node = node.larger;
                return node.getData();
            }


        }
    }

    public Object clone(){
        return this;
    }

    public T ceiling(T t){
        T last = last();
        if(last == t){
            return t;
        }else if(last.compareTo(t)<0){
            return null;
        }
        /*BinarySearchTreeNode<T> node = bst.getNode(head);
        while(t.compareTo(node.getData()) < 0 ){
            node = node.larger;
        }
        return node.getData();

         */
        List<T> list = bst.makeList();
        int j = 0;
        for(int i = list.size() -1; i> 0; i--){
            if(list.get(i).compareTo(t) >0)
                j = i;
            while(list.get(j).compareTo(t) >0){
                j--;
            }
            return list.get(j+1);

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
        int j = 0;
        for(int i = 0; i< list.size(); i++){
            if(list.get(i).compareTo(t) < 0)
                j = i;
                while(list.get(j).compareTo(t) < 0){
                    j++;
                }
                return list.get(j-1);

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
        return (remove(head)) ? head: null;
    }

    public T pollLast(){
        return (remove(tail)) ? tail : null;
    }






}
