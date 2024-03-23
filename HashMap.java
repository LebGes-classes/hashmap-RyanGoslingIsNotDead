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
        } else {
            if (getNodeByCell(array[index]).getKey().equals(key)){
                this.array[index] = new Node<K, V>(hash, key, value, null);
            } else {
                Node<K, V> currentNode = getNodeByCell(array[index]);
                do {
                    currentNode = currentNode.getNext();
                } while (currentNode.getNext() != null);
                currentNode.redefinitionNext(new Node<K, V>(hash, key, value, null));
            }
        }
    }

    public Node<K, V> getNode(K key) {
        if (containsKey(key)) {
            return (Node<K, V>) array[indexHash(hash(key))];
        } else {
            throw new IllegalArgumentException("Wrong key");
        }
    }
    private Node<K, V> getNodeByCell(Object cell) {
        return (Node<K, V>) cell;
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
        Node<K, V> currentNode = getNodeByCell(array[index]);
        do {
            if (currentNode.getKey().equals(key)){
                return true;
            }
            currentNode = currentNode.getNext();
        }while (currentNode.getNext() != null);
        return false;
    }

    public boolean containsValue(V value) {
        for (Object element : array) {
            if (element instanceof Node) {
                Node<K, V> currentNode = (Node<K, V>) element;
                    do {
                        if (currentNode.getValue().equals(value)) {
                            return true;
                        }
                        currentNode = currentNode.getNext();
                    } while (currentNode.getNext() != null);
            }
        }
        return false;
    }
    public void remove(K key){
        if (containsKey(key)) {
            size--;
            int hash = hash(key);
            int index = indexHash(hash);
            Node<K, V> currentNode = getNodeByCell(array[index]);
            do {
                if (currentNode.getNext().getKey().equals(key)) {
                    currentNode.redefinitionNext(currentNode.getNext().getNext());
                    break;
                }
                currentNode = currentNode.getNext();
            } while (currentNode.getNext() != null);
        } else {
            throw new IllegalArgumentException("Wrong key");
        }
    }
    public int size(){
        return size;
    }
    public void clear(){
        for(int i =0; i<size;i++){
            array[i]=null;
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
