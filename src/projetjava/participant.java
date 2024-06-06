/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava;

/**
 *
 * @author bouth
 */
public class participant extends utilisateur {
    private Integer inscription_id;
    private Integer conference_id;
    private String mode_paiement;

    public participant(Integer inscription_id, Integer conference_id, String mode_paiement, Integer utilisateur_id, String nom, String adresse_email, String institution, String username, String pswrd, Integer est_administrateur) {
        super(utilisateur_id, nom, adresse_email, institution, username, pswrd, est_administrateur);
        this.inscription_id = inscription_id;
        this.conference_id = conference_id;
        this.mode_paiement = mode_paiement;
    }

    public Integer getInscription_id() {
        return inscription_id;
    }

    public Integer getConference_id() {
        return conference_id;
    }

    public String getMode_paiement() {
        return mode_paiement;
    }

    public void setInscription_id(Integer inscription_id) {
        this.inscription_id = inscription_id;
    }

    public void setConference_id(Integer conference_id) {
        this.conference_id = conference_id;
    }

    public void setMode_paiement(String mode_paiement) {
        this.mode_paiement = mode_paiement;
    }

   

}
