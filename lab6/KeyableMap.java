import java.util.Optional;
import java.util.Map;

class KeyableMap<V extends Keyable> implements Keyable {
    private final String name;
    private final ImmutableMap<String, V> map;

    public KeyableMap(String name) {
        this.name = name;
        this.map = new ImmutableMap<>();
    }

    protected KeyableMap(String name, ImmutableMap<String, V> map) {
        this.name = name;
        this.map = map;
    }

    public KeyableMap<V> put(V item) {
        return new KeyableMap<>(name, map.put(item.getKey(), item));
    }

    public Optional<V> get(String key) {
        return map.get(key);
    }

    @Override
    public String getKey() {
        return name;
    }

    protected ImmutableMap<String, V> getMap() {
        return map;
    }

    @Override
    public String toString() {
        if (map.isEmpty()) {
            return name + ": {}";
        }
        String output = "";
        for (Map.Entry<String,V> e: map) {
            output += e.getValue().toString() + ", ";
        }
        output = output.substring(0, output.length() - 2);
        output = name + ": {" + output + "}";
        return output;
    }
}
