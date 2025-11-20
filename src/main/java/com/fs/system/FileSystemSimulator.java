package com.fs.system;

import com.fs.model.Directory;
import com.fs.model.File;
import java.util.List;

public class FileSystemSimulator {
    private Directory root;
    private Directory currentDirectory;

    private Journal journal;

    public FileSystemSimulator() {
        this.root = new Directory("/");
        this.currentDirectory = this.root;
        this.journal = new Journal();
    }
    public void createDirectory(String name) {
        if (currentDirectory.getSubDirectoryByName(name) != null) {
            System.out.println("Error: the directory '" + name + "' already exists.");
            return;
        }
        
        Directory newDir = new Directory(name);
        currentDirectory.addDirectory(newDir);
        System.out.println("Directory '" + name + "' created.");

        journal.log("CREATE_DIR", "Created directory '" + name + "' in " + currentDirectory.getName());
        
    }

    public void createFile(String name, String content) {
        if (currentDirectory.getFileByName(name) != null) {
            System.out.println("Error: the file '" + name + "' already exists.");
            return;
        }

        File newFile = new File(name, content);
        currentDirectory.addFile(newFile);
        System.out.println("File '" + name + "' created.");

        journal.log("CREATE_FILE", "Created file '" + name + "' in " + currentDirectory.getName());
        
    }

    public void listDirectory() {
        System.out.println("Contents of " + currentDirectory.getName() + ":");
        
        List<Directory> subDirs = currentDirectory.getSubDirectories();
        List<File> files = currentDirectory.getFiles();

        if (subDirs.isEmpty() && files.isEmpty()) {
            System.out.println("(empty)");
            return;
        }

        for (Directory dir : subDirs) {
            System.out.println("[D] " + dir.getName());
        }
        
        for (File file : files) {
            System.out.println("[F] " + file.getName() + " (" + file.getContent().length() + " bytes)");
        }
    }

    public void changeDirectory(String name) {
        if (name.equals("/")) {
            this.currentDirectory = this.root;
            return;
        }

        Directory target = currentDirectory.getSubDirectoryByName(name);
        
        if (target != null) {
            this.currentDirectory = target;
            System.out.println("Entered: " + name);
        } else {
            System.out.println("Error: Directory '" + name + "' not found.");
        }
    }


    public String getCurrentPath() {
        return currentDirectory.getName();
    }
}