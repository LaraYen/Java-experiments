import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PurchaseListKey implements Serializable {

    @Column(name = "student_name", length = 45)
    private String studentName;

    @Column(name = "course_name", length = 45)
    private String courseName;

    public String getStudentName() {
        return studentName;
    }

    public PurchaseListKey() {
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseListKey that = (PurchaseListKey) o;
        return studentName.equals(that.studentName) && courseName.equals(that.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentName, courseName);
    }
}
