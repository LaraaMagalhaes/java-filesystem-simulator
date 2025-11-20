package com.fs.system;

import com.fs.model.Directory;
import com.fs.model.File;

import java.util.List;

public class FileSystemSimulator {
    private Directory root;
    private Directory currentDirectory;

    public FileSystemSimulator() {
        this.root = new Directory("/");
        this.currentDirectory = this.root;
    }
    public void createDirectory(String name) {
        if (currentDirectory.getSubDirectoryByName(name) != null) {
            System.out.println("Erro: O diretório '" + name + "' já existe.");
            return;
        }
        
        Directory newDir = new Directory(name);
        currentDirectory.addDirectory(newDir);
        System.out.println("Diretório '" + name + "' criado.");
        
    }

    public void createFile(String name, String content) {
        if (currentDirectory.getFileByName(name) != null) {
            System.out.println("Erro: O arquivo '" + name + "' já existe.");
            return;
        }

        File newFile = new File(name, content);
        currentDirectory.addFile(newFile);
        System.out.println("Arquivo '" + name + "' criado.");
        
    }

    public void listDirectory() {
        System.out.println("Conteúdo de " + currentDirectory.getName() + ":");
        
        List<Directory> subDirs = currentDirectory.getSubDirectories();
        List<File> files = currentDirectory.getFiles();

        if (subDirs.isEmpty() && files.isEmpty()) {
            System.out.println("(vazio)");
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
            System.out.println("Entrou em: " + name);
        } else {
            System.out.println("Erro: Diretório '" + name + "' não encontrado.");
        }
    }


    public String getCurrentPath() {
        return currentDirectory.getName();
    }
}