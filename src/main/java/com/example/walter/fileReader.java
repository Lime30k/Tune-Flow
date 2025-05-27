package com.example.walter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class fileReader {
    public fileReader()
    {
    }
    public void read(String path)
    {
        try {
            File infile = new File(path);
            Scanner reader = new Scanner(infile);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                System.out.println(data);
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
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
