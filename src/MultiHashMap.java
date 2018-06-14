import java.util.*;

/**
 * Implements a multimap using a hashmap that contains
 * array lists to store its elements.
 *
 * @author Marcus
 *
 * @param <K> The key.
 * @param <V> The value.
 */
public class MultiHashMap<K, V> implements MultiMap<K, V> {

    HashMap<K, List<V>> hashmap = new HashMap<>();

    @Override
    public Set<K> getKeys() {
        return hashmap.keySet();
    }

    @Override
    public boolean containsKey(K key) throws NullPointerException {
        if (key == null) {
            throw new NullPointerException("Key must not be null.");
        }
        return hashmap.containsKey(key);
    }

    @Override
    public List removeKey(K key) throws KeyNotFoundException, NullPointerException {
        if (key == null) {
            throw new NullPointerException("Key must not be null.");
        }
        if (!hashmap.containsKey(key)) {
            throw new KeyNotFoundException("Key not found.");
        }
        List<V> list = hashmap.get(key);
        hashmap.remove(key);
        return list;
    }

    @Override
    public List<V> getValues(K key) throws KeyNotFoundException, NullPointerException {
        if (!hashmap.containsKey(key)) {
            throw new KeyNotFoundException("Key not found.");
        }
        if (hashmap.get(key) == null) {
            throw new NullPointerException("Key must not be null.");
        }
        return hashmap.get(key);
    }

    @Override
    public boolean removeValue(K key, V value) throws KeyNotFoundException, NullPointerException {
        if (key == null) {
            throw new NullPointerException("Key must not be null.");
        }
        if (value == null) {
            throw new NullPointerException("Value must not be null.");
        }
        if (hashmap.containsKey(key)) {
            if (hashmap.get(key).contains(value)) {
                hashmap.get(key).remove(value);
                if (hashmap.get(key).isEmpty()) {
                    removeKey(key);
                }
                return true;
            }

        }
        else {
            throw new KeyNotFoundException("Key not found");
        }
        return false;
    }

    @Override
    public void addValue(K key, V value) throws NullPointerException {
        if (key == null || value == null) {
            throw new NullPointerException();
        }
        List<V> list = new ArrayList<>();
        if (!hashmap.containsKey(key)) {
            list.add(value);
            hashmap.put(key, list);
        } else {
            hashmap.get(key).add(value);
        }
    }



    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (K key : hashmap.keySet()) {
            out.append(key).append(": ").append("\n");
            for (V value : hashmap.get(key)) {
                out.append("\t").append(value).append("\n");
            }
            out.append("\n");
        }
        return out.toString();
    }
}
