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
import java.util.Arrays;
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
                BufferedReader br = new BufferedReader(new FileReader(filename));
                String nextLine = br.readLine();
                String[] line = {};
                String previousID = "";
                nextLine = br.readLine(); // To skip the header
                while (nextLine != null) {
                    try {
                        line = nextLine.split("\t");
                        if (!previousID.equals(line[0])) {
                            Virus virusObject = new Virus(line[0], line[2], line[7], line[8]);
                            virusHM.put(Integer.valueOf(line[0]), virusObject);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("The virus with ID " + line[0] + " is incomplete; information missing.");
                    } catch (NullPointerException e) {
                        System.out.println("NullPointerException occurred");
                    }
                    nextLine = br.readLine();
                }
                System.out.println("er: " + virusHM.get(762660).getSoort());
                System.out.println("er: " + virusHM.get(762660).getId());
                VirusGUI.buttonFind.setEnabled(true);
                VirusGUI.labelFindButtonUnderscript.setVisible(false);
            } catch (FileNotFoundException e) {
                System.out.println("The file was not found");
                VirusGUI.buttonFind.setEnabled(false);
                VirusGUI.labelFindButtonUnderscript.setVisible(true);
            } catch (IOException e) {
                System.out.println("IOException occurred");
                VirusGUI.buttonFind.setEnabled(false);
                VirusGUI.labelFindButtonUnderscript.setVisible(true);
            }
        }
    }
    public static void Compare(){
        
    }
}
