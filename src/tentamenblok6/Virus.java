//Package
package tentamenblok6;
//import
import java.util.ArrayList;

/**
 * Virus is the Object model for the Virus objects.
 * It contains several attributes which define a certain virus
 * @author Rogier
 */
public class Virus implements Comparable<Virus>{
    //Variables declaration
    private int id;
    private String soort;
    private String classificatie;
    private ArrayList<Integer> hostList = new ArrayList<>();
    private ArrayList<String> hostNameList = new ArrayList<>();
    //End of variables declaration
    
    /**
     * Constructor for Virus
     * @param id        The virus ID
     * @param name      The virus name
     * @param soort     The virus classifications
     * @param hostID    The host's ID
     * @param hostName  The host's name
     */
    Virus(String id, String name, String soort, String hostID, String hostName){
        System.out.println("id: "+id);
        this.id = Integer.valueOf(id);
        this.soort = name;
        this.classificatie = soort.split(";")[1].replace(" virusses", "");
        this.hostList.add(Integer.valueOf(hostID));
        this.hostNameList.add(hostName);
    }

    /* Get the value of id
     * @return the value of id
     * Same for all other getters...
     */
    public int getId() {
        return id;
    }
    
    /* Set the value of id
     * @param id new value of id
     * Same for all other setters...
     */
    public void setId(int id) {
        this.id = id;
    }

    // Other getters and setters
    public String getSoort() {
        return soort;
    }

    public void setSoort(String soort) {
        this.soort = soort;
    }

    public ArrayList<Integer> getHostList() {
        return hostList;
    }

    public ArrayList<String> getHostNameList() {
        return hostNameList;
    }

    public String getClassificatie() {
        return classificatie;
    }

    public void setHostList(ArrayList<Integer> hostList) {
        this.hostList = hostList;
    }

    public void setHostNameList(ArrayList<String> hostNameList) {
        this.hostNameList = hostNameList;
    }

    public void setClassificatie(String classificatie) {
        this.classificatie = classificatie;
    }
    
    /**
     * Used to add another host's ID and name to an already existing Virus.
     * @param hostID    The host ID
     * @param hostName  The host Name
     */
    public void addHost(String hostID, String hostName){
        this.hostList.add(Integer.valueOf(hostID));
        this.hostNameList.add(hostName);
    }
    
    /**
     * The implementation of comparable. This method allows for comparing between viruses
     * which are user defined objects and therefore have no standard way to be compared.
     * @param v The virus object that will be compared to this object
     * @return an integer of <0, 0 or >0
     */
    @Override
    public int compareTo(Virus v) {
        System.out.println("OI");
        return -1*Integer.compare(this.hostList.size(), v.hostList.size());
    }
        
    /**
     * Used to check if the user selected viral classification is present in this virus.
     * @param viralClassificationState The selected classification from the combo box in the GUI
     * @return true or false; true if the viralClassificationState is present in this virus' classification list
     *                        false if not.
     */
    public boolean SortVirusListCheckClassification(String viralClassificationState){
        return classificatie.toLowerCase().contains(viralClassificationState.toLowerCase());
    }
    
    /**
     * Checks whether the host ID from the combo box in the GUI is present in this virus
     * @param hostIDState   Selected host ID from the combo box
     * @return true or false; true if the hostIDState is present in this virus' host ID list
     *                        false if not.
     */
    public boolean SortVirusListCheckHostIDState(int hostIDState){
        return hostList.contains(hostIDState);
    }
}
