public class HashMap<K, V> {

    private static final int INIT_BUCKET_COUNT = 16;
    private Bucket[] buckets;
    class Entity{
        K key;
        V value;
    }

    class Bucket<K, V>{

        class Node{

            Node next;
            Entity value;
        }
    }

    private int calcBucketIndex(K key){
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public V put(K key, V value){
        int index = calcBucketIndex(key);
        Bucket bucket = buckets[index];
    }

    public HashMap(){
        this(INIT_BUCKET_COUNT);
    }

    public HashMap(int initCount){
        buckets = new Bucket[initCount];
    }
}
