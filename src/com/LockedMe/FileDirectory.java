package com.LockedMe;

import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileDirectory {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to LockedMe");
        System.out.println("Developed by Ajay S Chandran");
        int option = 0;
        while (option != 3) {
            System.out.println("Please select an option:");
            System.out.println("1. List all files in ascending order");
            System.out.println("2. File management menu");
            System.out.println("3. Close the application");
            try {
                option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        System.out.println("Enter the directory path:");
                        String path = scanner.nextLine();
                        try {
                            listFiles(path);
                        } catch (FileNotFoundException e) {
                            System.out.println("The specified directory does not exist.");
                        }
                        break;
                    case 2:
                        try {
                            fileManagement(scanner);
                        } catch (FileNotFoundException e) {
                            System.out.println("The specified directory does not exist.");
                        }
                        break;
                    case 3:
                        System.out.println("Closing the application.");
                        System.out.println("Thank you for using LockedMe.");
                        break;
                    default:
                        System.out.println("Invalid option. Please select again.");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid option. Please select again.");
            }
        }
        scanner.close();
    }

    public static void listFiles(String path) throws FileNotFoundException {
        File dir = new File(path);
        if (!dir.exists()) {
            throw new FileNotFoundException();
        }
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            System.out.println("No files found in the directory.");
            return;
        }
        Arrays.sort(files);
        for (File file : files) {
            if (file.isFile()) {
                System.out.println(file.getName());
            }
        }
    }

    public static void fileManagement(Scanner scanner) throws FileNotFoundException {
        System.out.println("Enter the directory path:");
        String path = scanner.nextLine();
        File dir = new File(path);
        if (!dir.exists()) {
            throw new FileNotFoundException();
        }
        List<String> files = new ArrayList<>();
        while (true) {
            System.out.println("Please select an option:");
            System.out.println("a. Add a file to the directory");
            System.out.println("b. Delete a file from the directory");
            System.out.println("c. Search for a file in the directory");
            System.out.println("d. Return to main menu");
            String option = scanner.nextLine().toLowerCase();
            switch (option) {
                case "a":
                    System.out.println("Enter the name of the file to add:");
                    String fileName = scanner.nextLine();
                    File file = new File(path, fileName);
                    try {
                        if (file.createNewFile()) {
                            System.out.println(fileName + " added to the directory.");
                        } else {
                            System.out.println("File already exists in the directory.");
                        }
                    } catch (IOException e) {
                        System.out.println("Failed to create file: " + e.getMessage());
                    }
                    break;
                case "b":
                    System.out.println("Enter the name of the file to delete:");
                    fileName = scanner.nextLine();
                    file = new File(path, fileName);
                    try {
                        if (file.delete()) {
                            System.out.println(fileName + " deleted from the directory.");
                        } else {
                            System.out.println("File not found in the directory.");
                        }
                    } catch (SecurityException e) {
                        System.out.println("Failed to delete file: " + e.getMessage());
                    }
                    break;
                case "c":
                    System.out.println("Enter the name of the file to search for:");
                    fileName = scanner.nextLine();
                    file = new File(path, fileName);
                    try {
                        if (file.exists()) {
                            System.out.println(fileName + " found in the directory.");
                        } else {
                            System.out.println("File not found in the directory.");
                        }
                    } catch (SecurityException e) {
                        System.out.println("Unable to access file: " + e.getMessage());
                    }
                    break;
                case "d":
                	System.out.println("Returned to the main menu");
                    return;
                default:
                    System.out.println("Invalid option. Please select again.");
                    break;
                }
        }

	}

}
