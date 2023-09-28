import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Roster roster = new Roster("Roster");

        for (int i = 0; i < n; i++) {
            String student = sc.next().trim();
            String module = sc.next().trim();
            String assessment = sc.next().trim();
            String grade = sc.next().trim();

            roster = roster.add(student, module, assessment, grade);
        }

        while (sc.hasNext()) {
            String student = sc.next().trim();
            String module = sc.next().trim();
            String assessment = sc.next().trim();

            String grade = roster.getGrade(student, module, assessment);
            System.out.println(grade);
        }
        sc.close();
    }
}

