/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava;

/**
 *
 * @author bouth
 */
public class InscriptionParticipant {
    private Integer inscriptionId;
    private Integer conferenceId;
    private Integer utilisateurId;

    public void setInscriptionId(Integer inscriptionId) {
        this.inscriptionId = inscriptionId;
    }

    public void setConferenceId(Integer conferenceId) {
        this.conferenceId = conferenceId;
    }

    public void setUtilisateurId(Integer utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public Integer getInscriptionId() {
        return inscriptionId;
    }

    public Integer getConferenceId() {
        return conferenceId;
    }

    public Integer getUtilisateurId() {
        return utilisateurId;
    }

    public String getModePaiement() {
        return modePaiement;
    }
    private String modePaiement;

    public InscriptionParticipant(Integer inscriptionId, Integer conferenceId, Integer utilisateurId, String modePaiement) {
        this.inscriptionId = inscriptionId;
        this.conferenceId = conferenceId;
        this.utilisateurId = utilisateurId;
        this.modePaiement = modePaiement;
    }
}
