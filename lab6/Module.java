class Module extends KeyableMap<Assessment> {
    public Module(String key) {
        super(key);
    }

    private Module(String key, ImmutableMap<String, Assessment> map) {
        super(key, map);
    }

    @Override
    public Module put(Assessment item) {
        return new Module(super.getKey(), super.getMap().put(item.getKey(), item));
    }

    // @Override
    // public String toString() {
    //     if (super.getMap().isEmpty()) {
    //         return super.getKey() + ": {}";
    //     }
    //     String output = "";
    //     for (Assessment assessment : super.getMap().values()) {
    //         output += assessment.toString() + ", ";
    //     }
    //     output = output.substring(0, output.length() - 2);
    //     output = super.getKey() + ": {" + output + "}";
    //     return output;
    // }
}
