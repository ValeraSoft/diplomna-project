package com.spring_final.controller;

import com.spring_final.model.ActivityFile;
import com.spring_final.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Controller
public class DownloadFile {

    @Autowired
    FileService service;

    @GetMapping("/downloadFile")
    public void downloadFile(@RequestParam("fileName") String fileName,
                                     HttpServletRequest request, HttpServletResponse response) throws IOException {

        String filepath = "D:\\apache-tomcat-9.0.87\\webapps\\Spring_Final_Task_war\\";

        try {
            // get your file as InputStream
            InputStream is = new FileInputStream(filepath + fileName);
            // copy it to response's OutputStream
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
