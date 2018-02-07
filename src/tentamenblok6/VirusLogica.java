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
import javax.swing.ListModel;

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
    private static DefaultListModel<String> replicatesListModel = new DefaultListModel<>();
    private static DefaultListModel<String> dlmChecked1 = new DefaultListModel<>();
    private static DefaultListModel<String> dlmChecked2 = new DefaultListModel<>();
    public static ArrayList<Integer> dlmList = new ArrayList<>();
    public static ArrayList<Object> extractedViruses = new ArrayList<>();
    public static ArrayList<Object> extractedVirusesDump = new ArrayList<>();
    private static ListModel extractedListModel;

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
                                System.out.println("asdsadsa" + line[7] + "\n" + Integer.parseInt(line[7]));
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
                Collections.sort(uniqueHostIDs);
                uniqueHosts.remove(0);
                uniqueHostIDs.remove(0);
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
    }

    /**
     * Adds all the virus id's to the lists in the GUI
     */
    public static void FillVirusLists() {
        for (Map.Entry<Integer, Virus> entry : virusHM.entrySet()) {
            dlmList.add(entry.getKey());
            dlm.addElement(String.valueOf(entry.getKey()) + "  (" + entry.getValue().getSoort() + ")");
        }
        VirusGUI.listVirusID1.setModel(dlm);
        VirusGUI.listVirusID2.setModel(dlm);
    }

    public static void SortVirusLists(String viralClassificationState, String hostIDState_1, String hostIDState_2) {
        dlmChecked1.clear();
        dlmChecked2.clear();
        for (int virusID : dlmList) {
            Virus currentVirus = virusHM.get(virusID);
            if (!viralClassificationState.equals("none")) {
                if (currentVirus.SortVirusListCheckClassification(viralClassificationState)) {
                    SortVirusListsLogic(currentVirus, hostIDState_1, hostIDState_2);
                }
            } else {
                SortVirusListsLogic(currentVirus, hostIDState_1, hostIDState_2);
            }
        }
        VirusGUI.listVirusID1.setModel(dlmChecked1);
        VirusGUI.listVirusID2.setModel(dlmChecked2);
    }

    private static void SortVirusListsLogic(Virus currentVirus, String hostIDState_1, String hostIDState_2) {
        if (!hostIDState_1.equals("none")) {
            if (currentVirus.getHostList().contains(Integer.parseInt(uniqueHostIDs.get(uniqueHosts.indexOf(hostIDState_1))))) {
                dlmChecked1.addElement(String.valueOf(currentVirus.getId()) + "  (" + currentVirus.getSoort() + ")");
            }
        } else {
            dlmChecked1.addElement(String.valueOf(currentVirus.getId()) + "  (" + currentVirus.getSoort() + ")");
        }
        if (!hostIDState_2.equals("none")) {
            if (currentVirus.getHostList().contains(Integer.parseInt(uniqueHostIDs.get(uniqueHosts.indexOf(hostIDState_2))))) {
                dlmChecked2.addElement(String.valueOf(currentVirus.getId()) + "  (" + currentVirus.getSoort() + ")");
            }
        } else {
            dlmChecked2.addElement(String.valueOf(currentVirus.getId()) + "  (" + currentVirus.getSoort() + ")");
        }
    }

    public static void Compare() {
        extractedListModel = VirusGUI.listVirusID1.getModel();
        extractedVirusesDump.clear();
        replicatesListModel.clear();
        extractedViruses.clear();
        for (int i = 0; i < extractedListModel.getSize(); i++) {
            extractedViruses.add(extractedListModel.getElementAt(i));
        }
        extractedListModel = VirusGUI.listVirusID2.getModel();
        for (int i = 0; i < extractedListModel.getSize(); i++) {
            extractedViruses.add(extractedListModel.getElementAt(i));
        }
        for (Object vir : extractedViruses){
            if (extractedVirusesDump.contains(vir)){
                replicatesListModel.addElement(vir.toString());
            } else {
                extractedVirusesDump.add(vir);
            }
        }
        VirusGUI.listReplicateViruses.setModel(replicatesListModel);
    }
}
