package ua.com.learninghub.model.dao;

import com.sun.corba.se.spi.orbutil.fsm.Input;
import ua.com.learninghub.model.dao.implementation.CourseDaoImpl;
import ua.com.learninghub.model.dao.implementation.LessonDaoImpl;
import ua.com.learninghub.model.dao.interfaces.CourseDao;
import ua.com.learninghub.model.entities.Course;

import javax.ws.rs.WebApplicationException;
import java.io.*;
import java.nio.file.Files;
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

    public static File getCourseLogoByFilename(int courseId, String filename){
        File file = null;
        if(filename == null) file = getDefaultLogo();
        else file = new File(storagePath + "courses"+ separator +courseId + separator + "img"  + separator + filename);
        if (!file.exists())
            file = getDefaultLogo();
        return file;
    }

    private static File getDefaultLogo(){
        File file = new File(storagePath + "courses" + separator + "default.jpg");
        if (!file.exists())
            throw new RuntimeException("Default course logo wasn't found.");
        return file;
    }

    public static void writeCourseLogo(InputStream fileInputStream, String filename, int courseId){
        try {
            File out = new File(storagePath + "courses" + separator + courseId + separator + "img" + separator + filename);
            if(!out.exists()){
                out.getParentFile().mkdirs();
                //out.createNewFile();
            }
            OutputStream outputStream = new FileOutputStream(out);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void writeMainIntro(InputStream fileInputStream, String filename, int courseId) {
        try {
            File out = new File(storagePath + "courses" + separator + courseId + separator + "video" + separator + filename);
            if(!out.exists()){
                out.getParentFile().mkdirs();
                //out.createNewFile();
            }
            OutputStream outputStream = new FileOutputStream(out);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static void writeLessonVideo(InputStream fileInputStream, String filename, int lessonId) {
        try {
            System.out.println("Writing started");
            File out = new File(storagePath + "lessons" + separator + lessonId + separator + "video" + separator + filename);
            if(!out.exists()){
                out.getParentFile().mkdirs();
                //out.createNewFile();
            }
            OutputStream outputStream = new FileOutputStream(out);
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public static File getLessonVideo(int lessonId) throws Exception {
        File file = new File(storagePath + "lessons"+ separator +lessonId + separator + "video" + separator + new LessonDaoImpl().selectById(lessonId).getLessonVideo());
        if (!file.exists())
            //throw new Exception();
            file = getVideo();

        return file;
    }

    public static File getVideoCourse(int courseId) throws Exception {
        File file = new File(storagePath + "courses"+ separator +courseId + separator + "video" + separator + new CourseDaoImpl().selectById(courseId).getMainVideoPath());
        if (!file.exists())
            file = getVideo();
            //throw new Exception();
        return file;
    }

    public static File getVideo(){
        File file = new File(storagePath + "courses" + separator +"default.mp4");
        if (!file.exists())
            throw new RuntimeException("video wasn't found.");
        return file;
    }

    FileSystemUtil(){}
}
