package com.example.walter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class fileReader {

    public ArrayList<String> byteStash = new ArrayList<>();

    //this filereader is kinda broken do not try to fix it
    //hours wasted:8

    public fileReader()
    {
    }
    public void read(String path)
    {
        byteStash.clear();
        try (java.io.FileReader reader = new java.io.FileReader("data/"+path+".txt")) {
            if (reader == null) {
                System.out.println("File not found in resources: " + path);
                return;
            }

            Scanner scanny = new Scanner(reader);
            while (scanny.hasNextLine()) {
                String data = scanny.nextLine();
                byteStash.add(data);
            }

        } catch (Exception e) {
            System.out.println("An error occurred while reading: " + path);
            e.printStackTrace();
        }
    }
    public int getFileInfo(String path)
    {
        File infile = new File(path);
        if (infile.exists()) {
            System.out.println("File name: " + infile.getName());
            System.out.println("Absolute path: " + infile.getAbsolutePath());
            System.out.println("Writeable: " + infile.canWrite());
            System.out.println("Readable " + infile.canRead());
            System.out.println("File size in bytes " + infile.length());
            return 1;
        } else {
            System.out.println("The file does not exist.");
            return 0;
        }
    }

    // Replace a specific line
    public void replaceLine(int lineNumber, String newContent, String path) {
        byteStash.clear();
        read(path);
        if (lineNumber < 0 || lineNumber >= byteStash.size()) {
            System.out.println("Invalid line number: " + lineNumber);
            return;
        }
        byteStash.set(lineNumber, newContent);
    }

    // Insert a new line
    public void insertLine(int index, String content, String path) {
        byteStash.clear();
        read(path);
        if (index < 0 || index > byteStash.size()) {
            System.out.println("Invalid index: " + index);
            return;
        }
        byteStash.add(index, content);
    }
    public void addLine(String content, String path) {
        byteStash.clear();
        read(path);

        byteStash.add(content);
    }

    // Remove a line
    public void removeLine(int index, String path) {
        byteStash.clear();
        read(path);
        if (index < 0 || index >= byteStash.size()) {
            System.out.println("Invalid index: " + index);
            return;
        }
        byteStash.remove(index);
    }

    // Write out to a new file
    public void writeToFile(String outputPath) {
        try (java.io.PrintWriter writer = new java.io.PrintWriter("data/"+outputPath+".txt")) {
            for (int i = 0; i< byteStash.size();i++) {
                String line = byteStash.get(i);
                writer.println(line);
            }
            System.out.println("File written to: " + outputPath);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while writing to file: " + outputPath);
            e.printStackTrace();
        }
    }

}
