package igor.dev.dao;

import igor.dev.domain.Task;
import igor.dev.dto.TaskDto;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@SuppressWarnings("deprecation")
@Repository
@RequiredArgsConstructor
public class TaskDao {

    private final SessionFactory sessionFactory;

    @Transactional
    public void createTask(Task task) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(task);
    }

    @Transactional(readOnly = true)
    public Task getTaskById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Task.class, id);
    }

    @Transactional(readOnly = true)
    public List<Task> getTasksWithPagination(int pageNumber, int pageSize) {
        Session session = sessionFactory.getCurrentSession();
        int offset = (pageNumber - 1) * pageSize;
        Query<Task> query = session.createQuery("SELECT t FROM Task t", Task.class);
        query.setFirstResult(offset);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    @Transactional
    public void updateTask(Task updatedTask) {
        Session session = sessionFactory.getCurrentSession();
        session.update(updatedTask);
    }

    @Transactional
    public void removeTask(int id) {
        Session session = sessionFactory.getCurrentSession();
        Task task = session.byId(Task.class).load(id);
        session.delete(task);
    }
}