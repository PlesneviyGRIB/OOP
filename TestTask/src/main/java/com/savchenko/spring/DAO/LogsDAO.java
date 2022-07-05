package com.savchenko.spring.DAO;

import com.savchenko.spring.models.LogRecord;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Timestamp;
import java.util.List;

@Component
@Transactional
public class LogsDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public LogsDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void add(LogRecord logRecord){
        logRecord.setTimeStamp(new Timestamp(System.currentTimeMillis()));
        sessionFactory.getCurrentSession().persist(logRecord);
    }

    public List<LogRecord> getAll(){
        return sessionFactory.getCurrentSession().createCriteria(LogRecord.class).list();
    }

    public void delete(){
        Query query = sessionFactory.getCurrentSession().createSQLQuery("truncate log");
        query.executeUpdate();
    }
}
