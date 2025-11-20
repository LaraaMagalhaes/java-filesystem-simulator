package com.fs.system;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Journal {
    private static final String LOG_FILE_PATH = "storage/journal.log";

    public Journal() {
        File directory = new File("storage");
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public void log(String action, String target) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        String logMessage = String.format("[%s] [%s] %s", timestamp, action, target);

        try (FileWriter fileWriter = new FileWriter(LOG_FILE_PATH, true); 
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            
            printWriter.println(logMessage);

        } catch (IOException e) {
            System.err.println("Erro ao escrever no Journal: " + e.getMessage());
        }
    }
}
