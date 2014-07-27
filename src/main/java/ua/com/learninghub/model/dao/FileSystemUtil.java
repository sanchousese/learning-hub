package ua.com.learninghub.model.dao;

import com.sun.corba.se.spi.orbutil.fsm.Input;

import javax.ws.rs.WebApplicationException;
import java.io.*;
import java.util.Scanner;

/**
 * Created by Валентин on 27.07.2014.
 */
public class FileSystemUtil {
    private static String storagePath;

    static {
        InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("META-INF\\storage.info");
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
        File file = new File(storagePath + "img\\CourseLogo\\" + filename);
        if (!file.exists())
            file = getDefaultLogo();
        return file;
    }

    private static File getDefaultLogo(){
        File file = new File(storagePath + "img\\CourseLogo\\default.jpg");
        if (!file.exists())
            throw new RuntimeException("Default course logo wasn't found.");
        return file;
    }

    public static void writeCourseLogo(InputStream fileInputStream, String filename){
        try {
            OutputStream outpuStream = new FileOutputStream(new File(storagePath + "img\\CourseLogo\\" + filename));
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

    FileSystemUtil(){}
}
