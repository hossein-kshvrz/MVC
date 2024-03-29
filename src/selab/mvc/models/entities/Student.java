package selab.mvc.models.entities;

import selab.mvc.models.Model;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class Student implements Model {
    private String name;
    private String studentNo;
    public ArrayList<Course> courses = new ArrayList<>();
    private ArrayList<Float> grades = new ArrayList<>();

    @Override
    public String getPrimaryKey() {
        return this.studentNo;
    }

    public void setName(String value) { this.name = value; }
    public String getName() { return this.name; }

    public void setStudentNo(String value) {
        if (!validateStudentNo(value))
            throw new IllegalArgumentException("The format is not correct");

        this.studentNo = value;
    }
    public String getStudentNo() { return this.studentNo; }

    public ArrayList<Float> getGrades() {
        return grades;
    }

    public float getAverage() {
        if (grades.size() == 0) {
            return 0;
        }
        float sum = 0;
        for (int i = 0; i < grades.size(); i++) {
            sum += grades.get(i);
        }
        return sum / grades.size();
    }

    public String getCourses() {
        String crs = "";
        for (int i = 0; i < courses.size(); i++) {
            crs += courses.get(i).getCourseNo() + ", ";
        }
        return crs;
    }


    /**
     *
     * @param studentNo Student number to be checeked
     * @return true, if the format of the student number is correct
     */
    private boolean validateStudentNo(String studentNo) {
        Pattern pattern = Pattern.compile("^[8-9]\\d{7}$");
        return pattern.matcher(studentNo).find();
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void addGrade(float point) {
        this.grades.add(point);
    }
}
