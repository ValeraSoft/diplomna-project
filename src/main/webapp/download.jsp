<%
    String filename = request.getParameter("fileName");
    System.out.println("downloading file " + filename);
    String filepath = "D:\\apache-tomcat-9.0.87\\webapps\\Spring_Final_Task_war\\";
    response.setContentType("APPLICATION/OCTET-STREAM");
    response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");

    java.io.FileInputStream fileInputStream=new java.io.FileInputStream(filepath + filename);

    int i;
    while ((i=fileInputStream.read()) != -1) {
        out.write(i);
    }
    fileInputStream.close();
%>