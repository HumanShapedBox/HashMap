import java.util.ArrayList;
import java.util.List;

public class HashMap<K, V>{

    private static final int INIT_BUCKET_COUNT = 16;
    private static final double LOAD_FACTOR = 0.5;
    private int size;
    private Bucket[] buckets;

    public HashMap(){
        this(INIT_BUCKET_COUNT);
    }

    public HashMap(int initCount){
        buckets = new Bucket[initCount];
    }
    class Entity{
        K key;
        V value;
    }

    class Bucket<K, V>{

        Node head;
        class Node{

            Node next;
            Entity value;
        }

        public V add(Entity entity){
            Node node = new Node();
            node.value = entity;
            if(head == null){
                head = node;
                return null;
            }
            Node currentNode = head;
            while(true){
                if(currentNode.value.key.equals(entity.key)){
                    V temp = (V)currentNode.value.value;
                    currentNode.value.value = entity.value;
                    return temp;
                }
                if(currentNode.next != null){currentNode = currentNode.next;}
                else {
                    currentNode.next = node;
                    return null;
                }
            }
        }

        public V get(K key){
            Node node = head;
            while (node != null){
                if(node.value.key.equals(key)){return (V)node.value.value;}
                node = node.next;
            }
            return null;
        }

        public V remove(K key){
            if(head == null){return null;}
            if(head.value.key.equals(key)){
                V temp = (V)head.value.value;
                head = head.next;
                return temp;
            }else {
                Node node = head;
                while(node.next != null){
                    if(node.value.key.equals(key)){
                        V temp = (V)node.next.value.value;
                        node.next = node.next.next;
                        return temp;
                    }
                    node = node.next;
                }
                return null;
            }
        }

//        public void showData(Node node){
//            List<String> data = new ArrayList<>();
//            data.add(node.value.key.toString());
//            data.add(node.value.value.toString());
//            for (String item: data) {
//                System.out.println(item);
//            }
//        }
    }

    private int calcBucketIndex(K key){
        return Math.abs(key.hashCode()) % buckets.length;
    }

    private void recalc(){
        size = 0;
        Bucket<K, V>[] old = buckets;
        buckets = new Bucket[old.length * 2];
        for(int i = 0; i < old.length; i++){
            Bucket<K, V> bucket = old[i];
            if(bucket != null) {
                Bucket.Node node = bucket.head;
                while (node != null){
                    put((K)node.value.key, (V)node.value.value);
                    node = node.next;
                }
            }
            old[i] = null;
        }
    }

    public V put(K key, V value){
        if(buckets.length * LOAD_FACTOR <= size){recalc();}

        int index = calcBucketIndex(key);
        Bucket bucket = buckets[index];
        if(bucket == null){
            bucket = new Bucket();
            buckets[index] = bucket;
        }
        Entity entity = new Entity();
        entity.key = key;
        entity.value = value;
        V ret = (V)bucket.add(entity);
        if(ret == null){size++;}
        return ret;
    }

    public V get(K key){
        int index = calcBucketIndex(key);
        Bucket bucket = buckets[index];
        if(bucket == null){return null;}
        V ret = (V)bucket.get(key);
        if(ret != null){size--;}
        return ret;
    }

    public V remove(K key){
        int index = calcBucketIndex(key);
        Bucket bucket = buckets[index];
        if(bucket == null){return null;}
        return (V)bucket.remove(key);
    }

    public void showData(){
        Bucket<K, V>[] data = buckets;
        if(data == null){System.out.println("No data");}
        for (Bucket<K, V> item: data) {
            Bucket<K, V> itemData = item;
            if(itemData != null){
                Bucket.Node node = itemData.head;
                if(itemData != null){
                    System.out.println("Key: " + node.value.key.toString() + " Value: " + node.value.value.toString());
                }
            }
        }
    }
}