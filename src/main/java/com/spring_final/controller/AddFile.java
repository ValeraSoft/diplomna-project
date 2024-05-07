package com.spring_final.controller;

import com.spring_final.model.Activity;
import com.spring_final.model.ActivityFile;
import com.spring_final.model.User;
import com.spring_final.service.ActivityRequestService;
import com.spring_final.service.ActivityService;
import com.spring_final.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.output.*;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import java.io.File;
import java.util.Iterator;
import java.util.List;

@Controller
public class AddFile {

    @Autowired
    FileService fileService;

    @RequestMapping("/activityAddFile")
    public String addRequest(@RequestParam("activityId") Integer activityId, HttpSession session,
                             HttpServletRequest request){
//        User user = (User) session.getAttribute("authUser");
//        Activity activity = activityService.getActivity(activityId);
//        requestService.makeAddRequest(user, activity);
//        return "redirect:/activities";

        System.out.println(activityId);

        ServletContext context = request.getServletContext();

        File file ;
        int maxFileSize = 5000 * 1024;
        int maxMemSize = 5000 * 1024;
        String filePath = context.getInitParameter("file-upload");

        // Verify the content type
        String contentType = request.getContentType();

        if ((contentType.indexOf("multipart/form-data") >= 0)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // maximum size that will be stored in memory
            factory.setSizeThreshold(maxMemSize);

            // Location to save data that is larger than maxMemSize.
            factory.setRepository(new File("D:\\apache-tomcat-9.0.87\\webapps\\Spring_Final_Task_war\\"));

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // maximum file size to be uploaded.
            upload.setSizeMax( maxFileSize );

            try {
                // Parse the request to get file items.
                List fileItems = upload.parseRequest(request);

                // Process the uploaded file items
                Iterator i = fileItems.iterator();

                while ( i.hasNext () ) {
                    ActivityFile activityFile = new ActivityFile();
                    FileItem fi = (FileItem)i.next();
                    if ( !fi.isFormField () ) {
                        // Get the uploaded file parameters
                        String fieldName = fi.getFieldName();
                        String fileName = fi.getName();
                        activityFile.setFileName(fileName);
                        activityFile.setActivityId(activityId);
                        boolean isInMemory = fi.isInMemory();
                        long sizeInBytes = fi.getSize();

                        // Write the file
                        if( fileName.lastIndexOf("\\") >= 0 ) {
                            file = new File( filePath +
                                    fileName.substring( fileName.lastIndexOf("\\"))) ;
                        } else {
                            file = new File( filePath +
                                    fileName.substring(fileName.lastIndexOf("\\")+1)) ;
                        }
                        fi.write( file ) ;
                        fileService.addActivityFile(activityFile);
                        System.out.println("Uploaded Filename: " + filePath + fileName);
                    }
                }
            } catch(Exception ex) {
                System.out.println(ex);
            }
        } else {
            System.out.println("Cannot upload file");
        }
        return "redirect:/activities";
    }

}
