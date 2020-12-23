package junit.cookbook.oddsandends;

import java.io.*;

public class Deployment {
    private Deployer deployer;

    public Deployment() {
        this(Deployer.getInstance());
    }

    public Deployment(Deployer deployer) {
        this.deployer = deployer;
    }

    public void deploy(File targetFile) throws FileNotFoundException {
        deployer.deploy(this, targetFile);
    }
}
