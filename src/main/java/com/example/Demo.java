package com.example;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.example.entity.Change;
import com.example.entity.Person;
import com.example.entity.StatusType;
import com.example.entity.Task;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Demo {
    static final MetricRegistry metrics = new MetricRegistry();
    public static void main(String[] args) {
        var emf = Persistence.createEntityManagerFactory("default");
        var em = emf.createEntityManager();
        /*em.unwrap(Session.class)
                .setDefaultReadOnly(true);*/

        try {
            em.getTransaction().begin();

           /* var task = new Task();
            task.setStatus(StatusType.NEW);
            var change = new Change();
            change.setChangedBy("Oleh");
            change.setChangedOn(new Date(System.currentTimeMillis()));
            task.setChange(change);
            em.persist(task);*/
            var task = em.find(Task.class, 1L);
            System.out.println(task);
            task.getChange().setChangedBy("Petro");

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
