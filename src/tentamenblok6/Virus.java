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
        this.id = Integer.valueOf(id);
        this.soort = soort.split(";")[1].replace(" virusses", "");
        this.hostList.add(Integer.valueOf(hostID));
        this.hostNameList.add(hostName);
    }

    /* Get the value of id
     * @return the value of id
     */
    public int getId() {
        return id;
    }

    /* Set the value of id
     * @param id new value of id
     */
    public void setId(int id) {
        this.id = id;
    }

    /* Get the value of soort
     * @return the value of soort
     */
    public String getSoort() {
        return soort;
    }

    /* Set the value of soort
     * @param soort new value of soort
     */
    public void setSoort(String soort) {
        this.soort = soort;
    }

}
