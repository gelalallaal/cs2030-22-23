import java.util.Optional;
import java.util.Map;

class Roster extends KeyableMap<Student> {
    public Roster(String key) {
        super(key);
    }

    private Roster(String key, ImmutableMap<String, Student> map) {
        super(key, map);
    }

    @Override
    public Roster put(Student item) {
        Student stu = super.get(item.getKey()).map(s -> {
            for (Map.Entry<String, Module> m: item.getMap()) {
                s = s.put(m.getValue());
            }
            return s;
        }).orElse(item);
        return new Roster(super.getKey(), super.getMap().put(
            stu.getKey(), stu));
    }

    public String getGrade(String student, String module, String assessment) {
        Optional<String> y = super.get(student).flatMap(x -> x.get(module))
            .flatMap(x -> x.get(assessment)).map(x -> x.getGrade());
        String output = y.orElse(String.format("No such record: %s %s %s",
            student, module, assessment));
        return output;
    }

    public Roster add(String student, String module, String assessment,
        String grade) {
        return this.put(new Student(student).put(new Module(module).put(
            new Assessment(assessment, grade))));
    }
}
