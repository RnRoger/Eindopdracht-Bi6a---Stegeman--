/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tentamenblok6;
import roger.file.*;
/**
 *
 * @author Rogier
 */
public class VirusLogica {
    public static void openFile(String filename){
        if (filename.startsWith("http") || filename.startsWith("www.") || filename.startsWith("ftp:")){
            
        }
        else{
            roger.file.ReadFile.bufferedReader(filename);
        }
    }
}
