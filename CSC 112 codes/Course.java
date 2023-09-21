class Course {
    private String CourseName;
    private int SectionNumber;
    private int NumberOfCredits;
    private Course next;

    public Course() {
        CourseName = "";
        SectionNumber = 0;
        NumberOfCredits = 0;
        next = null;
    }

    public Course(String CourseName, int SectionNumber, int NumberOfCredits) {
        this.CourseName = CourseName;
        this.SectionNumber = SectionNumber;
        this.NumberOfCredits = NumberOfCredits;
        next = null;
    }

    public Course(Course course) {
        this.CourseName = course.CourseName;
        this.SectionNumber = course.SectionNumber;
        this.NumberOfCredits = course.NumberOfCredits;
        this.next = null;
    }

    public void setCourseName(String CourseName) {
        this.CourseName = CourseName;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setSectionNumber(int SectionNumber) {
        this.SectionNumber = SectionNumber;
    }

    public int getSectionNumber() {
        return SectionNumber;
    }

    public void setNumberOfCredits(int NumberOfCredits) {
        this.NumberOfCredits = NumberOfCredits;
    }

    public int getNumberOfCredits() {
        return NumberOfCredits;
    }

    public void setLink(Course next) {
        this.next = next;
    }

    public Course getLink() {
        return next;
    }

    public String toString() {
        return "Course Name: " + CourseName + ", Section: " + SectionNumber + ", Credits: " + NumberOfCredits;
    }
}