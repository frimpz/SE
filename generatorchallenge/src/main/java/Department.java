import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


    @Entity
    @Table(name="Department")
    public class Department implements Serializable {

        private String departmentName;
        private Integer facultySize ;
        private Integer studentSize ;
        private String category;

        public Department() {

        }

        @Column(name = "department")
        public String getDepartmentName() {
            return departmentName;
        }

        public void setDepartmentName(String departmentName) {
            this.departmentName = departmentName;
        }

        @Column(name = "faculty size")
        public Integer getFacultySize() {
            return facultySize;
        }

        public void setFacultySize(Integer facultySize) {
            this.facultySize = facultySize;
        }

        @Column(name = "student size")
        public Integer getStudentSize() {
            return studentSize;
        }

        public void setStudentSize(Integer studentSize) {
            this.studentSize = studentSize;
        }

        @Column(name = "category")
        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }

