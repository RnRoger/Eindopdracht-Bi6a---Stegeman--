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
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Rogier
 */

public class VirusLogica {
    
    //public static ArrayList<String> fileLines = new ArrayList<String>();
    public static HashMap<Integer, Virus> virusHM = new HashMap<Integer, Virus>();  
    
    public static void openFile(String filename) {
        if (filename.startsWith("http") || filename.startsWith("www.") || filename.startsWith("ftp:")) {

        } else {
            try {
                filename = System.getProperty("user.dir")+"\\src\\tentamenblok6\\"+filename;
                System.out.println("f: "+filename);
                BufferedReader br = new BufferedReader(new FileReader(filename));
                String line = br.readLine();
                while (line != null) {
                    try {
                        //fileLines.add(line);
                        line = br.readLine();
                        Virus virusObject = new Virus();
                        virusHM.put(Integer.SIZE, virusObject);
                    } catch (NullPointerException e) {
                        System.out.println("Exception occurred");
                    }
                }
                
            } catch (FileNotFoundException e) {
                System.out.println("The file was not found");
            } catch (IOException e) {
                System.out.println("IOException occurred");
            }
        }
        //createObject(fileLines);
    }
    /*
    public static void createObject(ArrayList<String> fileText){
        for (String line : fileText){  
        }
    }*/
}
