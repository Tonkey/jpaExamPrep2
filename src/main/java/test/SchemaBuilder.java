package test;

import entities.Grade;
import entities.Person;
import entities.Student;
import entities.Supervisor;
import java.sql.Date;
import java.util.HashMap;
import javax.persistence.Persistence;

/**
 *
 * @author nickl
 */
public class SchemaBuilder {

    private static final boolean EDITING = true;

    public static void main(String[] args) {
        HashMap<String, Object> puproperties = new HashMap<>();

        puproperties.put("javax.persistence.sql-load-source", "scripts/clearDB.sql");
        puproperties.put("javax.persistence.schema-generation.database.action", "drop-and-create");

        Persistence.generateSchema("jpaE2_PU", puproperties);

        if (EDITING) {
            //Edit in Database to show that methods work as intended, Person object is used to call methods
            Person pe = new Person();

            pe.add(new Supervisor("Lars", "Hansen", new Date(79, 12, 3), 40, true));
            Supervisor svLars = (Supervisor) pe.find("Lars", "Hansen", new Date(79, 12, 3));

            Student s = new Student(1, new Date(System.currentTimeMillis()), "Nicklas", "Molving", new Date(96, 11, 14), 19, false);
            pe.add(s);

            s.setSupervisor(svLars);
            s.edit(s);
            svLars.getPersons().add(s);
            svLars.edit(svLars);

            Student s2 = (Student) pe.find(s.getFirstName(), s.getLastName(), s.getBirthDate());
            System.out.println(s2.getSupervisor().getFirstName());

            Grade g = new Grade("SomeGrade", 20);
            s.setGrade(g);
            s.edit(s);
            s2 = (Student) pe.find(s.getFirstName(), s.getLastName(), s.getBirthDate());
            System.out.println(s2.getGrade().getName());

            Supervisor svLars2 = (Supervisor) pe.find(svLars.getFirstName(), svLars.getLastName(), svLars.getBirthDate());
            System.out.println(svLars2.getPersons().get(0).getFirstName());

            //So as you can see I can effectively add,edit and find objects from the Database!
            //Now to clean up, if all the following statements return true, all the objects will have been deleted from the database(Including the Grade object!!!)!
            System.out.println(pe.delete(s2.getId()));
            System.out.println(pe.delete(svLars2.getId()));
        }
    }
}
