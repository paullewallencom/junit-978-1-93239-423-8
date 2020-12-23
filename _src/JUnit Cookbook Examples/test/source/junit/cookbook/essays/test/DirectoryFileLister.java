package junit.cookbook.essays.test;

import java.io.File;

import junit.cookbook.essays.FileLister;


public class DirectoryFileLister implements FileLister {
    private File directory;

    public DirectoryFileLister(File directory) {
        this.directory = directory;
    }

    public File[] listFiles() {
        return directory.listFiles();
    }

}
