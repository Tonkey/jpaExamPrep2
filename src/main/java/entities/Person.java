package entities;

import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.Transient;
import test.Facade;

/**
 * The reason for using a JOINED inheritance is due to the fact that it
 * normalizes very well as well as making it extremly easy to execute different 
 * Queryes as everything is connected due to normalization!
 * the joined inheritance is by no means the fastest!!!
 * @author nickl
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements Facade{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    protected String firstName;
    protected String lastName;
    protected Date birthDate;
    protected int age;
    protected boolean isMarried;
    
    @ManyToOne
    private Supervisor supervisor;
    
    @OneToOne(cascade = CascadeType.REMOVE)
    protected Grade grade;
    
    @Transient
    protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaE2_PU");
    
    public Person(){
        
    }
    
    public Person(String firstName, String lastName, Date birthDate, int age, boolean isMarried){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.age = age;
        this.isMarried = isMarried;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public Grade getGrade() {
        return grade;
    }
    
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public int getAge() {
        return age;
    }

    public boolean isIsMarried() {
        return isMarried;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIsMarried(boolean isMarried) {
        this.isMarried = isMarried;
    }


    @Override
    public boolean add(Person p) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    @Override
    public boolean edit(Person p) {
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
            
        } catch (Exception e){
            em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        EntityManager em = emf.createEntityManager();
        try{
            Person p = em.find(Person.class, id);
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
        } catch(Exception e){
            return false;
        } finally {
            em.close();
        }
        return true;
    }

   @Override
    public Person find(String firstName, String lastName, Date birthDate) {
        Person s;
        EntityManager em = emf.createEntityManager();
        try {

            em.getTransaction().begin();
            Query q = em.createQuery("SELECT p FROM Person p WHERE p.firstName=:firstName and p.lastName=:lastName and p.birthDate=:birthDate");
            q.setParameter("firstName", firstName);
            q.setParameter("lastName", lastName);
            q.setParameter("birthDate", birthDate);

            em.getTransaction().commit();
            
            s = (Person) q.getSingleResult();
        } catch (Exception e) {
            System.out.println("Something went wrong while looking up Person!");
            return null;
        } finally {
            em.close();
        }
        return s;
    }
}
