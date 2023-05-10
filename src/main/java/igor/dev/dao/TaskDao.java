package igor.dev.dao;

import igor.dev.domain.Task;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public Page<Task> getAllTasksPages(Pageable pageable) {
        Session session = sessionFactory.getCurrentSession();
        Query<Task> query = session.createQuery("SELECT t FROM Task t ORDER BY t.description", Task.class);

        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        query.setMaxResults(pageable.getPageSize());

        List<Task> list = query.list();

        Query<Long> countQuery = session.createQuery("SELECT count(*) FROM Task t", Long.class);
        long totalCount = countQuery.uniqueResult();

        return new PageImpl<>(list, pageable, totalCount);
    }
}
