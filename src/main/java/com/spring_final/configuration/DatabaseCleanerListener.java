package com.spring_final.configuration;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Component
public class DatabaseCleanerListener implements ApplicationListener<ContextRefreshedEvent>{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        sessionFactory.getCurrentSession().
                createQuery("delete from ActivityFile")
                .executeUpdate();
    }
}
