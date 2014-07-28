package Images;

import org.junit.Test;

import java.io.*;

/**
 * Created by Валентин on 27.07.2014.
 */
public class DownloadImageTests {

    @Test
    public void checkPath(){
        try {
            InputStream stream = new FileInputStream(
                    "/home/vasax32/IdeaProjects/learning-hub/storage/img/CourseLogo/default.jpg");
            System.out.println(stream.available());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(File.separator);
        System.out.println(File.separatorChar);
    }
}
