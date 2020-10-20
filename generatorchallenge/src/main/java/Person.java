import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


@Entity
@Table(name="Person")
public class Person implements Serializable{

    private String name;
    private Date born ;
    private Integer salary ;

    public Person() {

    }

    @Column(name = "name" , nullable =false , length = 100)
    @NotEmpty
    @Size(max = 100)
    public String getName() {return this.name;}
    public void setName( String name) {this.name = name;}

    @Column(name = "born" , nullable = false )
    @Temporal(TemporalType.DATE)
    @NotEmpty
    @Past
    public Date getBorn () {return this.born ;}
    public void setBorn(Date born) {
        this.born = born;
    }

    @Column(name ="salary", nullable = false) @Min(1000) @Max(15000)
    @NotEmpty
    public Integer getSalary () {return this.salary ;}
    public void setSalary(Integer salary) {
        this.salary = salary;
    }
}
