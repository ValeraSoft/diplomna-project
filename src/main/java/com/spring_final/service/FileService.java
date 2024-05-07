package com.spring_final.service;

import com.spring_final.daos.hibernateImpl.ActivityDao;
import com.spring_final.daos.hibernateImpl.FileDao;
import com.spring_final.model.Activity;
import com.spring_final.model.ActivityFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileService {

    @Autowired
    private FileDao fileDao;

    public void addActivityFile(ActivityFile activityFile){
        fileDao.addFile(activityFile);
    }

    public List<ActivityFile> getActivityFiles(int activityId){
        return fileDao.getActivityFiles(activityId);
    }
}
