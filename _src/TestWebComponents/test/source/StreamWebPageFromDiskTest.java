import java.io.*;

import junit.framework.TestCase;

import com.diasparsoftware.gsbase.StreamUtil;

public class StreamWebPageFromDiskTest extends TestCase {
    private File webContentDirectory = new File("test/data");

    public void testLoginPageAsString() throws Exception {
        File loginPageFile =
            new File(webContentDirectory, "login.html");
            
        String content =
            StreamUtil.getContentAsString(
                new FileInputStream(loginPageFile));

        fail(content);
    }
}
