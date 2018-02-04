/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tentamenblok6;

import java.util.ArrayList;

/**
 *
 * @author Rogier
 */
public class Virus {
    
    private int id;
    private String soort;
    private ArrayList<Integer> hostList = new ArrayList<>();
    private ArrayList<String> hostNameList = new ArrayList<>();
    private String classificatie;
    
    Virus(String id, String soort, String hostID, String hostName){
        System.out.println("id: "+id);
        this.id = Integer.valueOf(id);
        this.soort = soort.split(";")[1].replace(" virusses", "");
        this.hostList.add(Integer.valueOf(hostID));
        this.hostNameList.add(hostName);
        System.out.println("VIRUS CREATED");
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
    
    

}
