package ua.com.learninghub.model.dao;

import com.sun.corba.se.spi.orbutil.fsm.Input;
import ua.com.learninghub.model.dao.implementation.CourseDaoImpl;
import ua.com.learninghub.model.dao.interfaces.CourseDao;
import ua.com.learninghub.model.entities.Course;

import javax.ws.rs.WebApplicationException;
import java.io.*;
import java.util.Scanner;

/**
 * Created by Валентин on 27.07.2014.
 */
public class FileSystemUtil {
    private static String storagePath;
    private static char separator = File.separatorChar;

    static {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(
                "META-INF"+separator+"storage.info");
        if(stream == null)
            throw new RuntimeException("Configuration file storage.info is not found.");
        Scanner scanner = new Scanner(stream, "UTF-8");
        if(!scanner.hasNext())
            throw new RuntimeException("Configuration file storage.info is empty.");
        storagePath = scanner.next();
        if (!new File(storagePath).exists())
            throw new RuntimeException("Configuration file storage.info specify unavailable path.");
    }

    public static File getCourseLogoByFilename(String filename){
        File file = null;
        if(filename == null) file = getDefaultLogo();
        else file = new File(storagePath + "img" + separator + "CourseLogo" +separator + filename);
        if (!file.exists())
            file = getDefaultLogo();
        return file;
    }

    private static File getDefaultLogo(){
        File file = new File(storagePath + "img" + separator + "CourseLogo" + separator + "default.jpg");
        if (!file.exists())
            throw new RuntimeException("Default course logo wasn't found.");
        return file;
    }

    public static void writeCourseLogo(InputStream fileInputStream, String filename){
        try {
            OutputStream outpuStream = new FileOutputStream(
                    new File(storagePath + "img" + separator + "CourseLogo" + separator + filename));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = fileInputStream.read(bytes)) != -1) {
                outpuStream.write(bytes, 0, read);
            }
            outpuStream.flush();
            outpuStream.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    public static File getVideoCourse(int courseId) {
        return new File(storagePath + "courses/video/"+courseId + separator +new CourseDaoImpl().selectById(courseId).getMainVideoPath());
    }

    public static File getVideo(){
        File file = new File(storagePath + "courses" + separator + "video" + separator +"1.mp4");
        if (!file.exists())
            throw new RuntimeException("video wasn't found.");
        return file;
    }

    FileSystemUtil(){}
}
