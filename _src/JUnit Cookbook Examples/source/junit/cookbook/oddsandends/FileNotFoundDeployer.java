package junit.cookbook.oddsandends;

import java.io.*;


public class FileNotFoundDeployer extends Deployer {
    public void deploy(Deployment deployment, File targetFile)
        throws FileNotFoundException {

        throw new FileNotFoundException(targetFile.getPath());
    }
}
