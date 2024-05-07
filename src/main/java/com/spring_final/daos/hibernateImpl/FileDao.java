package com.spring_final.daos.hibernateImpl;

import com.spring_final.model.Activity;
import com.spring_final.model.ActivityFile;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.List;

@Repository
@Transactional
public class FileDao {

   @Autowired
   private SessionFactory sessionFactory;

   public void addFile(ActivityFile activityFile){
      sessionFactory.getCurrentSession().save(activityFile);
   }

   public List<ActivityFile> getActivityFiles(int activityId){
      List<ActivityFile> activityFiles = sessionFactory.getCurrentSession().
              createQuery("FROM ActivityFile WHERE activityId= :id").setParameter("id", activityId).getResultList();
      return activityFiles;
   }

}