package com.example;

import com.example.entity.Person;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

public class Demo {
    public static void main(String[] args) {
        var emf = Persistence.createEntityManagerFactory("default");
        var em = emf.createEntityManager();
        /*em.unwrap(Session.class)
                .setDefaultReadOnly(true);*/

        try {
            em.getTransaction().begin();

            /*var person = new Person();
            person.setEmail("poshta@mail.com");
            person.setFirstName("Petro");
            person.setLastName("Poroshenko");
            em.persist(person);*/
            var person = em.find(Person.class, 5L);
            em.detach(person);
            person.setFirstName("Viktor");
            em.merge(person);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
