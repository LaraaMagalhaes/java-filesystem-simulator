package com.fs.cli;

import com.fs.system.FileSystemSimulator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileSystemSimulator fs = new FileSystemSimulator();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Java File System Simulator ===");
        System.out.println("Type 'help' to see available commands.");

        while (true) {
            System.out.print("user@jfs:" + fs.getCurrentPath() + "$ ");
            
            String input = scanner.nextLine();

            if (input.trim().isEmpty()) continue;

            String[] parts = input.split(" ");
            String command = parts[0];

            switch (command) {
                case "mkdir":
                    if (parts.length < 2) {
                        System.out.println("Usage: mkdir <directory_name>");
                    } else {
                        fs.createDirectory(parts[1]);
                    }
                    break;
                
                case "create": 
                    if (parts.length < 3) {
                        System.out.println("Usage: create <filename> <content>");
                    } else {
                        StringBuilder content = new StringBuilder(); 
                        for(int i=2; i<parts.length; i++) {
                            content.append(parts[i]).append(" ");
                        }
                        fs.createFile(parts[1], content.toString().trim());
                    }
                    break;

                case "ls":
                    fs.listDirectory();
                    break;

                case "cd":
                    if (parts.length < 2) {
                        System.out.println("Usage: cd <directory_name>");
                    } else {
                        fs.changeDirectory(parts[1]);
                    }
                    break;

                case "help":
                    System.out.println("Available commands:");
                    System.out.println("  mkdir <name>          - Create a directory");
                    System.out.println("  create <name> <text>  - Create a file with content");
                    System.out.println("  ls                    - List directory content");
                    System.out.println("  cd <name>             - Change directory (use '/' for root)");
                    System.out.println("  exit                  - Exit simulator");
                    break;

                case "exit":
                    System.out.println("Exiting system...");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Unknown command: " + command);
            }
        }
    }
}