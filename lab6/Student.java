import java.util.Map;

class Student extends KeyableMap<Module> {
    public Student(String key) {
        super(key);
    }

    private Student(String key, ImmutableMap<String, Module> map) {
        super(key, map);
    }

    @Override
    public Student put(Module item) {
        Module module = super.get(item.getKey()).map(m -> {
            for (Map.Entry<String, Assessment> a: item.getMap()) {
                m = m.put(a.getValue());
            }
            return m;
        }).orElse(item);

        return new Student(super.getKey(), super.getMap().put(
            module.getKey(), module));

        // for (Map.Entry<String, Module> entry : super.getMap().entrySet()) {
        //     if (entry.getValue().equals(item)) {
        //         return new Student(super.getKey(), super.getMap().put(
        //             entry.getKey(), item));
        //     }
        // }
        // Module mod = super.get(item.getKey()).orElse(new Module(
        //     item.getKey()));
        // mod = mod.putAll(item.getMap());
        // return new Student(super.getKey(), super.getMap().put(
        //     item.getKey(), mod));
    }
}
