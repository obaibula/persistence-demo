package com.example;

import com.codahale.metrics.MetricRegistry;
import com.example.entity.Change;
import com.example.entity.StatusType;
import com.example.entity.Task;
import jakarta.persistence.Persistence;

import java.util.Date;

public class Demo {
    static final MetricRegistry metrics = new MetricRegistry();
    public static void main(String[] args) {
        var emf = Persistence.createEntityManagerFactory("default");
        var em = emf.createEntityManager();
        /*em.unwrap(Session.class)
                .setDefaultReadOnly(true);*/

        try {
            em.getTransaction().begin();

            var task = new Task();
            task.setStatus(StatusType.NEW);
            var change = new Change();
            change.setChangedBy("Oleh");
            change.setChangedOn(new Date(System.currentTimeMillis()));
            task.setChange(change);
            em.persist(task);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}
