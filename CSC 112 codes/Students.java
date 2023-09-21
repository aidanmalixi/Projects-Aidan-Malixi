public class Students {
    private class Student {
        private int id;
        private Course courseList;

        public Student() {
            id = 0;
            courseList = null;
        }

        public Student(int id, Course courseList) {
            this.id = id;
            this.courseList = courseList;
        }

        public void setID(int id) {
            this.id = id;
        }

        public int getID() {
            return id;
        }

        public void setCourses(Course courseList) {
            this.courseList = courseList;
        }

        public Course getCourses() {
            return courseList;
        }

        public void addCourse(String courseName, int sectionNumber, int numberOfCredits) {
            Course newCourse = new Course(courseName, sectionNumber, numberOfCredits);
            if (courseList == null) {
                courseList = newCourse;
            } else {
                Course current = courseList;
                while (current.getLink() != null) {
                    current = current.getLink();
                }
                current.setLink(newCourse);
            }
        }

        public void dropCourse(String courseName, int sectionNumber) {
            if (courseList == null) {
                System.out.println("No courses to drop.");
                return;
            }
            if (courseList.getCourseName().equals(courseName) && courseList.getSectionNumber() == sectionNumber) {
                courseList = courseList.getLink();
                return;
            }
            Course current = courseList;
            while (current.getLink() != null) {
                if (current.getLink().getCourseName().equals(courseName) && current.getLink().getSectionNumber() == sectionNumber) {
                    current.setLink(current.getLink().getLink());
                    return;
                }
                current = current.getLink();
            }
            System.out.println("Course not found.");
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Student ID: ").append(id).append("\n");
            if (courseList == null) {
                sb.append("No courses enrolled.");
            } else {
                sb.append("Course List:\n");
                Course current = courseList;
                while (current != null) {
                    sb.append(current.toString()).append("\n");
                    current = current.getLink();
                }
            }
            return sb.toString();
        }
    }

    private Student[] studentList;
    private int maxNumberOfStudents;
    private int currentNumberOfStudents;

    public Students() {
        maxNumberOfStudents = 100;
        currentNumberOfStudents = 0;
        studentList = new Student[maxNumberOfStudents];
    }

    public Students(int maxNumberOfStudents) {
        this.maxNumberOfStudents = maxNumberOfStudents;
        currentNumberOfStudents = 0;
        studentList = new Student[maxNumberOfStudents];
    }

    public void addStudent(int id) {
        if (currentNumberOfStudents >= maxNumberOfStudents) {
            System.out.println("Maximum number of students reached.");
            return;
        }
        Student newStudent = new Student(id, null);
        studentList[currentNumberOfStudents] = newStudent;
        currentNumberOfStudents++;
    }

    public void addCourse(int id, String courseName, int sectionNumber, int numberOfCredits) {
        int index = findStudentIndex(id);
        if (index == -1) {
            System.out.println("Student not found.");
            return;
        }
        studentList[index].addCourse(courseName, sectionNumber, numberOfCredits);
    }

    public void dropCourse(int id, String courseName, int sectionNumber) {
        int index = findStudentIndex(id);
        if (index == -1) {
            System.out.println("Student not found.");
            return;
        }
        studentList[index].dropCourse(courseName, sectionNumber);
    }

    private int findStudentIndex(int id) {
        for (int i = 0; i < currentNumberOfStudents; i++) {
            if (studentList[i].getID() == id) {
                return i;
            }
        }
        return -1;
    }

    public void showAllStudents() {
        for (int i = 0; i < currentNumberOfStudents; i++) {
            System.out.println(studentList[i].toString());
        }
    }
}