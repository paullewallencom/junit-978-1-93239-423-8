package junit.cookbook.oddsandends.test;

import java.io.*;

import junit.cookbook.oddsandends.*;
import junit.framework.TestCase;

public class DeploymentTest extends TestCase {
    public void testTargetFileNotFound() throws Exception {
        Deployer fileNotFoundDeployer = new FileNotFoundDeployer();
        Deployment deployment = new Deployment(fileNotFoundDeployer);

        try {
            deployment.deploy(new File("hello"));
            fail("Found target file?!");
        }
        catch (FileNotFoundException expected) {
            assertEquals("hello", expected.getMessage());
        }
    }
}
