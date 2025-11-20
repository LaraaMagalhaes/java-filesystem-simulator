package com.fs.model;

import java.io.Serializable;

public class File implements Serializable {
    // number of the class version for serialization
    private static final long serialVersionUID = 1L;

    private String name;
    private String content;

    public File(String name, String content) {
        this.name = name;
        this.content = content;
    }

    // Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    @Override
    public String toString() {
        return "Arquivo: " + name + " (Tamanho: " + content.length() + " caracteres)";
    }
}