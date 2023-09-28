class Assessment implements Keyable {
    private final String test;
    private final String grade;

    public Assessment(String test, String grade) {
        this.test = test;
        this.grade = grade;
    }

    @Override
    public String getKey() {
        return test;
    }

    public String getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return String.format("{%s: %s}", test, grade);
    }
}
