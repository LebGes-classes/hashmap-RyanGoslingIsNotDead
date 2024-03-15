public class HashMap<K, V> {
    private Object[] array;
    private int capacity;
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    public HashMap() {
        this.capacity = DEFAULT_CAPACITY;
        this.array = new Object[DEFAULT_CAPACITY];
    }

    public HashMap(int capacity) {
        this.capacity = capacity;
        this.array = new Object[capacity];
    }

    private int hash(Object key) {
        int hash;
        return (key == null) ? 0 : (hash = key.hashCode()) ^ (hash >>> 16);
    }

    private int indexHash(int hash) {
        return (capacity - 1) & hash;
    }

    public void put(K key, V value) {
        if ((float) size / capacity >= DEFAULT_LOAD_FACTOR){
            resizing();
        }
        size++;
        int hash = hash(key);
        int index = indexHash(hash);
        if (array[index] == null) {
            this.array[index] = new Node<K, V>(hash, key, value, null);
        }
    }

    private Node<K, V> getNode(K key) {
        if (containsKey(key)) {
            return (Node<K, V>) array[indexHash(hash(key))];
        } else {
            throw new IllegalArgumentException("Wrong key");
        }
    }

    public V get(K key) {
        if (containsKey(key)) {
            return getNode(key).getValue();
        } else {
            throw new IllegalArgumentException("Wrong key");
        }
    }

    public boolean containsKey(K key) {
        int hash = hash(key);
        int index = indexHash(hash);
        return array[index] != null;
    }

    public boolean containsValue(V value) {
        for (Object element : array) {
            if (element instanceof Node) {
                Node<K, V> node = (Node<K, V>) element;
                if (node.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }
    public void remove(K key){
        if (containsKey(key)) {
            size--;
            int hash = hash(key);
            int index = indexHash(hash);
            array[index] = null;
        } else {
            throw new IllegalArgumentException("Wrong key");
        }
    }
    public int size(){
        return size;
    }
    public void clear(){
        for(int i =0; i<size;i++){
            array[i]=0;
        }
        size = 0;
    }
    public boolean isEmpty(){
        return size == 0;
    }
    private void resizing(){
        capacity = capacity * 2;
        Object[] newArray = new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, array.length);
        array = newArray;
    }
    public int getCapacity(){
        return capacity;
    }
}
