package com.fs.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Directory implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private List<Directory> subDirectories;
    private List<File> files;

    public Directory(String name) {
        this.name = name;
        this.subDirectories = new ArrayList<>();
        this.files = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Directory> getSubDirectories() {
        return subDirectories;
    }

    public List<File> getFiles() {
        return files;
    }

    public void addFile(File file) {
        this.files.add(file);
    }

    public void removeFile(File file) {
        this.files.remove(file);
    }

    public void addDirectory(Directory directory) {
        this.subDirectories.add(directory);
    }

    public void removeDirectory(Directory directory) {
        this.subDirectories.remove(directory);
    }

    public Directory getSubDirectoryByName(String name) {
        for (Directory dir : subDirectories) {
            if (dir.getName().equals(name)) {
                return dir;
            }
        }
        return null; 
    }

    public File getFileByName(String name) {
        for (File file : files) {
            if (file.getName().equals(name)) {
                return file;
            }
        }
        return null;
    }
}