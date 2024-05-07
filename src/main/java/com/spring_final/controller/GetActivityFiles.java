package com.spring_final.controller;

import com.spring_final.model.Activity;
import com.spring_final.model.ActivityFile;
import com.spring_final.service.ActivityService;
import com.spring_final.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class GetActivityFiles {

    @Autowired
    FileService service;

    @RequestMapping("/getFiles")
    public ModelAndView getActivityFile(@RequestParam("activityId") int activityId){
        ModelAndView mv = new ModelAndView();

        List<ActivityFile> activityFiles = service.getActivityFiles(activityId);

        mv.addObject("files", activityFiles);
        mv.setViewName("WEB-INF/pages/activityFiles");
        return mv;
    }

}
