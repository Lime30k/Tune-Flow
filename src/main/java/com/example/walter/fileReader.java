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
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path)) {
            if (inputStream == null) {
                System.out.println("File not found in resources: " + path);
                return;
            }

            Scanner reader = new Scanner(inputStream);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                byteStash.add(data);
            }
            reader.close();

        } catch (Exception e) {
            System.out.println("An error occurred while reading: " + path);
            e.printStackTrace();
        }
    }
    public void getFileInfo(String path)
    {
        File infile = new File(path);
        if (infile.exists()) {
            System.out.println("File name: " + infile.getName());
            System.out.println("Absolute path: " + infile.getAbsolutePath());
            System.out.println("Writeable: " + infile.canWrite());
            System.out.println("Readable " + infile.canRead());
            System.out.println("File size in bytes " + infile.length());
        } else {
            System.out.println("The file does not exist.");
        }
    }

}
