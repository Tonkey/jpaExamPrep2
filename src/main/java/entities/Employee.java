package entities;



import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author nickl
 */
@Entity
public class Employee extends Person{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private int soSecNr;
    private float wage;
    private String taxClass;
    
    public Employee(){
        
    }

    public Employee(int soSecNr, float wage, String taxClass, String firstName, String lastName, Date birthDate, int age, boolean isMarried) {
        super(firstName, lastName, birthDate, age, isMarried);
        this.soSecNr = soSecNr;
        this.wage = wage;
        this.taxClass = taxClass;
    }
    

    public int getSoSecNr() {
        return soSecNr;
    }

    public float getWage() {
        return wage;
    }

    public String getTaxClass() {
        return taxClass;
    }

    public void setSoSecNr(int soSecNr) {
        this.soSecNr = soSecNr;
    }

    public void setWage(float wage) {
        this.wage = wage;
    }

    public void setTaxClass(String taxClass) {
        this.taxClass = taxClass;
    }
    
    
    
}
