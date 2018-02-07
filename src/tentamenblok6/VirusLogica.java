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
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultListModel;

/**
 *
 * @author Rogier
 */
public class VirusLogica {

    //public static ArrayList<String> fileLines = new ArrayList<String>();
    public static HashMap<Integer, Virus> virusHM = new HashMap<>();
    public static ArrayList<String> uniqueHosts = new ArrayList<>();
    public static ArrayList<String> uniqueHostIDs = new ArrayList<>();
    private static DefaultListModel<String> dlm = new DefaultListModel<>();
    private static DefaultListModel<String> dlmChecked1 = new DefaultListModel<>();
    private static DefaultListModel<String> dlmChecked2 = new DefaultListModel<>();
    private static DefaultListModel<String> dlmCheckHalfway = new DefaultListModel<>();
    public static ArrayList<Integer> dlmList = new ArrayList<>();

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
                            Virus virusObject = new Virus(line[0], line[1], line[2], line[7], line[8]);
                            virusHM.put(Integer.valueOf(line[0]), virusObject);
                            previousID = line[0];
                            if (!uniqueHosts.contains(line[7] + "  (" + line[8] + ")")) {
                                uniqueHosts.add(line[7] + "  (" + line[8] + ")");
                                uniqueHostIDs.add(line[7]);
                            }
                        } else {
                            virusHM.get(Integer.valueOf(line[0])).addHost(line[7], line[8]);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("The virus with ID " + line[0] + " is incomplete; information missing.");
                    } catch (NullPointerException e) {
                        System.out.println("NullPointerException occurred");
                    }
                    nextLine = br.readLine();
                }
                Collections.sort(uniqueHosts);
                uniqueHosts.remove(0);
                FillHostIDCombo();
                FillVirusLists();
                VirusGUI.fileLoaded = true;
            } catch (FileNotFoundException e) {
                System.out.println("The file was not found");
                VirusGUI.buttonFind.setEnabled(false);
                VirusGUI.labelFindButtonUnderscript.setVisible(true);
            } catch (IOException e) {
                System.out.println("IOException occurred");
                VirusGUI.buttonFind.setEnabled(false);
                VirusGUI.labelFindButtonUnderscript.setVisible(true);
            } catch (NumberFormatException e) {
                System.out.println("A NumberFormatException has been found " + e.getMessage());
            } catch (NullPointerException e) {
                System.out.println("A NumberFormatException has occurred, please check the data of your file.\nNote that the file must contain at least 2 lines.");
            }
        }
    }

    /**
     * Adds all host ID's to the combo boxes in the GUI
     */
    public static void FillHostIDCombo() {
        for (String uniqueHost : uniqueHosts) {
            VirusGUI.comboHostID1.addItem(uniqueHost);
            VirusGUI.comboHostID2.addItem(uniqueHost);
        }
        /*for (Iterator<Virus> it = new ArrayList<>(virusHM.values()).iterator(); it.hasNext();) {
         Virus currentVirus = it.next();
         ArrayList<Integer> retrievedHosts = currentVirus.getHostList();
         ArrayList<String> retrievedHostNames = currentVirus.getHostNameList();
         System.out.println(retrievedHosts+""+retrievedHostNames);
         for (int i = 0; i < retrievedHosts.size(); i++){
         dlm.addElement(String.valueOf(retrievedHosts.get(i))+"  ("+retrievedHostNames.get(i)+")");
         }
            
         }
         */
        /*
         DefaultListModel<String> dlm = new DefaultListModel<>();
         for (String uniqueHost : uniqueHosts){
         dlm.addElement(uniqueHost);
         }
         VirusGUI.listHostID1.setModel(dlm);
         VirusGUI.listHostID2.setModel(dlm);
         */
    }

    /**
     * Adds all the virus id's to the lists in the GUI
     */
    public static void FillVirusLists() {
        for (Map.Entry<Integer, Virus> entry : virusHM.entrySet()) {
            dlmList.add(entry.getKey());
            dlm.addElement(String.valueOf(entry.getKey()) + "  (" + entry.getValue().getSoort() + ")");
        }
        VirusGUI.listHostID1.setModel(dlm);
        VirusGUI.listHostID2.setModel(dlm);
    }

    public static void SortVirusLists(String viralClassificationState, String hostIDState_1, String hostIDState_2) {
        dlmChecked1.clear();
        dlmChecked2.clear();
        dlmCheckHalfway.clear();
        for (Map.Entry<Integer, Virus> entry : virusHM.entrySet()) {
            int virusID = entry.getKey();
            Virus currentVirus = virusHM.get(virusID);
            //dlmCheckHalfway.addElement(String.valueOf(virusID));
            if (!viralClassificationState.equals("none")) {
                if (currentVirus.SortVirusListCheckClassification(viralClassificationState)) {
                    SortVirusListsLogic(currentVirus, hostIDState_1, hostIDState_2,virusID);
                }
            } else {
                SortVirusListsLogic(currentVirus, hostIDState_1, hostIDState_2,virusID);
            }
        }
        VirusGUI.listHostID1.setModel(dlmChecked1);
        VirusGUI.listHostID2.setModel(dlmChecked2);
        /*
         if ((!viralClassificationState.equals("none")) && (hostIDState_1.equals("none") || hostIDState_2.equals("none"))) {
         SortVirusListsLogic(1, viralClassificationState, "none", "none");
         } else if ((viralClassificationState.equals("none")) && (!hostIDState_1.equals("none") && !hostIDState_2.equals("none"))) {
         SortVirusListsLogic(2, "none", hostIDState_1, hostIDState_2);
         }
         */
    }

    private static void SortVirusListsLogic(Virus currentVirus, String hostIDState_1, String hostIDState_2,int virusID) {
        System.out.println("A1");
        if (!hostIDState_1.equals("none")) {
            System.out.println("A2");
            if (currentVirus.SortVirusListCheckHostIDState(virusID)) {
                System.out.println("A3");
                dlmChecked1.addElement(String.valueOf(currentVirus.getId()) + "  (" + currentVirus.getSoort() + ")");
            }
        } else {
            dlmChecked1.addElement(String.valueOf(currentVirus.getId()) + "  (" + currentVirus.getSoort() + ")");
        }
        if (!hostIDState_2.equals("none")) {
            if (currentVirus.SortVirusListCheckHostIDState(virusID)) {
                dlmChecked2.addElement(String.valueOf(currentVirus.getId()) + "  (" + currentVirus.getSoort() + ")");
            }
        } else {
            dlmChecked2.addElement(String.valueOf(currentVirus.getId()) + "  (" + currentVirus.getSoort() + ")");
        }

        
        /*
         dlmChecked1.clear();
         dlmChecked2.clear();
         for (int virusID : dlmList) {
         Virus currentVirus = virusHM.get(virusID);
         switch (currentCase) {
         case 1:
         if (currentVirus.SortVirusListCheckClassification(viralClassificationState)) {
         dlmChecked1.addElement(String.valueOf(currentVirus.getId()) + "  (" + currentVirus.getSoort() + ")");
         }
         if (currentVirus.SortVirusListCheckClassification(viralClassificationState)) {
         dlmChecked2.addElement(String.valueOf(currentVirus.getId()) + "  (" + currentVirus.getSoort() + ")");
         }
         case 2:
         if (currentVirus.SortVirusListCheckHostIDState(hostIDState_1)){
         dlmChecked1.addElement(String.valueOf(currentVirus.getId()) + "  (" + currentVirus.getSoort() + ")");
         System.out.println("check");
         }
         if (currentVirus.SortVirusListCheckHostIDState(hostIDState_2)){
         dlmChecked2.addElement(String.valueOf(currentVirus.getId()) + "  (" + currentVirus.getSoort() + ")");
         }
         }
         VirusGUI.listHostID1.setModel(dlmChecked1);
         VirusGUI.listHostID2.setModel(dlmChecked2);
         }
         */
    }

    public static void Compare(String viralClassificationState, String hostIDState_1, String hostIDState_2) {
        System.out.println("aaa" + viralClassificationState + hostIDState_1 + hostIDState_2);

        /* SORTING
         ArrayList<Virus> virusList = new ArrayList<Virus>(virusHM.values());
         Collections.sort(virusList);
         for (Virus str : virusList){
         System.out.println("K: "+str.getId()+" - "+str.getHostList().size());
         }
         */
    }
}
