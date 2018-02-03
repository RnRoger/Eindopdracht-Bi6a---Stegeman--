/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tentamenblok6;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import roger.file.*;

/**
 *
 * @author Rogier
 */
public class VirusLogica {

    public static String fileText = "No data found";

    public static void openFile(String filename) {
        if (filename.startsWith("http") || filename.startsWith("www.")){
            
        } else if (filename.startsWith("ftp:")){
            
        } else {
            try {
                filename = System.getProperty("user.dir")+"\\src\\tentamenblok6\\"+filename;
                System.out.println("f: "+filename);
                BufferedReader br = new BufferedReader(new FileReader(filename));
                StringBuilder sb = new StringBuilder();
                String line = br.readLine();
                while (line != null) {
                    try {
                        sb.append(line);
                        sb.append(System.lineSeparator());
                        line = br.readLine();
                    } catch (NullPointerException e) {
                        System.out.println("Exception occurred");
                    }
                }
                fileText = sb.toString();
            } catch (FileNotFoundException e) {
                System.out.println("The file was not found");
            } catch (IOException e) {
                System.out.println("IOException occurred");
            }
        }
        System.out.println(fileText);
    }
}
