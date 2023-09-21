public interface StudentsADT {
    void addStudent(int id);
    void addCourse(int id, String courseName, int sectionNumber, int numberOfCredits);
    void dropCourse(int id, String courseName, int sectionNumber);
    void showAllStudents();
}