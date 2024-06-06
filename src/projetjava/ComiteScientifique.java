/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava;

/**
 *
 * @author bouth
 */
public class ComiteScientifique  extends utilisateur{
        private Integer comiteScientifiqueId;
        private Integer conferenceId;
        

   public ComiteScientifique( ) {
       super();
       this.comiteScientifiqueId=null;
       this.conferenceId=null;
    }

    // Getters and Setters for comiteScientifiqueId and conferenceId
    public int getComiteScientifiqueId() {
        return comiteScientifiqueId;
    }

    public void setComiteScientifiqueId(Integer comiteScientifiqueId) {
        this.comiteScientifiqueId = comiteScientifiqueId;
    }

    public int getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }

    // Constructor to call the parent class constructor and initialize additional fields
    public ComiteScientifique( Integer comiteScientifiqueId, Integer conferenceId,Integer utilisateurId, String nom, String adresse_email, String institution, String username,String pswrd, Integer est_administrateur) {
        super(utilisateurId, nom, adresse_email, institution, username, pswrd, est_administrateur); // Call parent constructor
        this.comiteScientifiqueId = comiteScientifiqueId;
        this.conferenceId = conferenceId;
    }
}
