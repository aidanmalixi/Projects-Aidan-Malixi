import java.util.Scanner;
public class StudentsDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Students students = new Students();

        students.addStudent(1111);
        students.addStudent(1234);
        students.addStudent(2357);

        int choice = 0;

        while (choice != 9) {
            System.out.println("What action would you like to implement?");
            System.out.println("1: Show all Students");
            System.out.println("2: Add a Course");
            System.out.println("3: Drop a Course");
            System.out.println("9: Quit");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    students.showAllStudents();
                    break;
                case 2:
                    System.out.println("Enter the student ID:");
                    int id1 = scanner.nextInt();
                    System.out.println("Enter the course name:");
                    String CourseName = scanner.next();
                    System.out.println("Enter the section number:");
                    int SectionNumber = scanner.nextInt();
                    System.out.println("Enter the number of credits:");
                    int numberOfCredits = scanner.nextInt();
                    students.addCourse(id1, CourseName, SectionNumber, numberOfCredits);
                    break;
                case 3:
                    System.out.println("Enter the student ID:");
                    int id2 = scanner.nextInt();
                    System.out.println("Enter the course name:");
                    String CourseName2 = scanner.next();
                    System.out.println("Enter the section number:");
                    int SectionNumber2 = scanner.nextInt();
                    students.dropCourse(id2, CourseName2, SectionNumber2);
                    break;
                case 9:
                    System.out.println("Exiting program");
                    break;
                default:
                    System.out.println("Invalid option. Pick an option from the menu.");
            }
        }
    }
}