package junit.cookbook.oddsandends;

import java.io.*;

public class Deployer {
    private static Deployer deployer = new Deployer();

    public static Deployer getInstance() {
        return deployer;
    }

    public void deploy(Deployment deployment, File targetFile)
        throws FileNotFoundException {
        // Does some scary deployment
    }
}
