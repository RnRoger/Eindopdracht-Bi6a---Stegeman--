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
    public static HashMap<Integer, Virus> virusHM = new HashMap<>();

    public static void openFile(String filename) {
        if (filename.startsWith("http") || filename.startsWith("www.") || filename.startsWith("ftp:")) {

        } else {
            try {
                filename = System.getProperty("user.dir") + "\\src\\tentamenblok6\\" + filename;
                System.out.println("f: " + filename);
                BufferedReader br = new BufferedReader(new FileReader(filename));
                String nextLine = br.readLine();
                System.out.println(nextLine);
                String[] line;
                String fullline = "";
                String previousID = "";
                String test = "";
                while (nextLine != null) {
                    try {
                        nextLine = br.readLine();
                        //line = br.readLine().split("\t");
                        line = nextLine.split("\t");
                        if (previousID.equals(line[0])) {
                            System.out.println("UN: "+line[0]);
                            Virus virusObject = new Virus(line[0], line[2], line[7], line[8]);
                            virusHM.put(Integer.valueOf(line[0]), virusObject);
                        }
                        else{
                            test += line[0];
                        }
                    }
                    catch(AbstractMethodError e){     
                    }
                    /*
                    } catch (NullPointerException e) {
                        System.out.println("Exception occurred");
                    }
                    */
                }
                System.out.println("TEST: "+test);
            } catch (FileNotFoundException e) {
                System.out.println("The file was not found");
            } catch (IOException e) {
                System.out.println("IOException occurred");
            }
        }
    }
    /*
     public static void createObject(ArrayList<String> fileText){
     for (String line : fileText){  
     }
     }*/
}
