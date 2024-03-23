import java.util.HashMap;
public class Node<K, V> {
    private final int hash;
    private final K key;
    private V value;
    private Node<K, V> next;
    Node(int hash, K key, V value, Node<K,V> next) {
        this.hash = hash;
        this.key = key;
        this.value = value;
        this.next = next;
    }
    public final K getKey(){
        return key;
    }
    public final V getValue(){
        return value;
    }
    public final void redefinitionNext(Node<K, V> nextNode){
        this.next = nextNode;
    }
    public final Node<K, V> getNext(){
        return next;
    }
}