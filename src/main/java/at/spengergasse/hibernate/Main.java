package at.spengergasse.hibernate;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Person person = new Person(1L, "Max", "Mustermann");

        SessionFactory sessionFactory = buildSessionFactory(Person.class);

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(person);

        session.getTransaction().commit();

        Person savedPerson = session.get(Person.class, person.getId());


        System.out.println("savedPerson");
        System.out.println(savedPerson);

        session.close();
        sessionFactory.close();
    }

    private static SessionFactory buildSessionFactory(Class<?> clazz) {
        return new Configuration()
                .addAnnotatedClass(clazz)
                .configure()
                .buildSessionFactory();
    }

}