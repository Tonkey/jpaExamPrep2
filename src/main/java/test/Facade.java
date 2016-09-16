package test;

import entities.Person;
import java.sql.Date;

/**
 *
 * @author nickl
 */
public interface Facade {
    
    public abstract Person find(String firstName, String lastName, Date birthDate);
    public boolean add(Person p);
    public boolean edit(Person p);
    public boolean delete(int id);
}
