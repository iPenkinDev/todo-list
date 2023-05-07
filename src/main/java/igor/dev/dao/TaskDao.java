package igor.dev.dao;

import igor.dev.domain.Task;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SuppressWarnings("deprecation")
@Repository
@RequiredArgsConstructor
public class TaskDao {

    private final SessionFactory sessionFactory;

    @Transactional
    public Task createTask(Task task) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(task);
        session.getTransaction().commit();
        session.close();
        return task;
    }

    @Transactional(readOnly = true)
    public Task getTaskById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Task.class, id);
    }

    @Transactional(readOnly = true)
    public List<Task> getAllTasks() {
        Session session = sessionFactory.openSession();
        List<Task> tasks = session.createQuery("from Task", Task.class).list();
        session.close();
        return tasks;
    }

    @Transactional
    public Task updateTask(Task task) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(task);
        session.getTransaction().commit();
        session.close();
        return task;
    }

    @Transactional
    public void removeTask(int task) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(task);
        session.getTransaction().commit();
        session.close();
    }
}