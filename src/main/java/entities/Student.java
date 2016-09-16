package entities;



import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author nickl
 */
@Entity
public class Student extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int matNr;
    private Date matDate;

    public Student() {

    }

    public Student(int matNr, Date matDate, String firstName, String lastName, Date birthDate, int age, boolean isMarried) {
        super(firstName, lastName, birthDate, age, isMarried);
        this.matNr = matNr;
        this.matDate = matDate;
    }

    public int getMatNr() {
        return matNr;
    }

    public Date getMatDate() {
        return matDate;
    }

    public void setMatNr(int matNr) {
        this.matNr = matNr;
    }

    public void setMatDate(Date matDate) {
        this.matDate = matDate;
    }
}
