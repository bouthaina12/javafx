/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava;

/**
 *
 * @author bouth
 */
public class ConferencierInvite {
    private Integer conferencierInviteId;
    private Integer conferenceId;
    private String nom;
    private String institution;
    private String paysOrigine;
    private String titrePresentation;

    public ConferencierInvite() {
        // Default constructor
    }

    public ConferencierInvite(Integer conferencierInviteId,Integer conferenceId, String nom, String institution, String paysOrigine, String titrePresentation) {
        this.conferencierInviteId=conferencierInviteId;
        this.conferenceId = conferenceId;
        this.nom = nom;
        this.institution = institution;
        this.paysOrigine = paysOrigine;
        this.titrePresentation = titrePresentation;
    }

    // Getters and Setters for each field
    public Integer getConferencierInviteId() {
        return conferencierInviteId;
    }

    public void setConferencierInviteId(int conferencierInviteId) {
        this.conferencierInviteId = conferencierInviteId;
    }

    public Integer getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getPaysOrigine() {
        return paysOrigine;
    }

    public void setPaysOrigine(String paysOrigine) {
        this.paysOrigine = paysOrigine;
    }

    public String getTitrePresentation() {
        return titrePresentation;
    }

    public void setTitrePresentation(String titrePresentation) {
        this.titrePresentation = titrePresentation;
    }

}
