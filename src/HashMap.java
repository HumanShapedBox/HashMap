public class HashMap<K, V> {

    private static final int INIT_BUCKET_COUNT = 16;
    private Bucket[] buckets;
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
    }

    private int calcBucketIndex(K key){
        return Math.abs(key.hashCode()) % buckets.length;
    }

    public V put(K key, V value){
        int index = calcBucketIndex(key);
        Bucket bucket = buckets[index];
        if(bucket == null){
            bucket = new Bucket();
            buckets[index] = bucket;
        }
        Entity entity = new Entity();
        entity.key = key;
        entity.value = value;
        return (V)bucket.add(entity);
    }

    public V get(K key){
        int index = calcBucketIndex(key);
        Bucket bucket = buckets[index];
        if(bucket == null){return null;}
        return bucket.get(key);
    }

    public HashMap(){
        this(INIT_BUCKET_COUNT);
    }

    public HashMap(int initCount){
        buckets = new Bucket[initCount];
    }
}
