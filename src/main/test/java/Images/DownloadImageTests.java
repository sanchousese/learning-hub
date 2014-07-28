package Images;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

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
    }
}
