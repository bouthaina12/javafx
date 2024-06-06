/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava;

import java.time.LocalDate;

/**
 *
 * @author bouth
 */
public class conference {
    private Integer conference_id ;
    private String nom;
    private String institution_organisatrice;
    private Integer utilisateur_id ;
    private String date_debut;
    private String date_fin ;

    
    private String lieu ;
    private String date_limite_soumission_articles;
    private String date_limite_inscriptions;
    private String frais_inscription ;
    public conference(Integer conference_id, String nom, String institution_organisatrice, Integer utilisateur_id, String date_debut, String date_fin, String lieu, String date_limite_soumission_articles, String date_limite_inscriptions, String frais_inscription) {
        this.conference_id = conference_id;
        this.nom = nom;
        this.institution_organisatrice = institution_organisatrice;
        this.utilisateur_id = utilisateur_id;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.lieu = lieu;
        this.date_limite_soumission_articles = date_limite_soumission_articles;
        this.date_limite_inscriptions = date_limite_inscriptions;
        this.frais_inscription = frais_inscription;
    }

    public int getConference_id() {
        return conference_id;
    }

    public String getNom() {
        return nom;
    }

    public String getInstitution_organisatrice() {
        return institution_organisatrice;
    }

    public int getUtilisateur_id() {
        return utilisateur_id;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public String getLieu() {
        return lieu;
    }

    public String getDate_limite_soumission_articles() {
        return date_limite_soumission_articles;
    }

    public String getDate_limite_inscriptions() {
        return date_limite_inscriptions;
    }

    public String getFrais_inscription() {
        return frais_inscription;
    }

    @Override
    public String toString() {
        return "conference{" + "conference_id=" + conference_id + ", nom=" + nom + ", institution_organisatrice=" + institution_organisatrice + ", utilisateur_id=" + utilisateur_id + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", lieu=" + lieu + ", date_limite_soumission_articles=" + date_limite_soumission_articles + ", date_limite_inscriptions=" + date_limite_inscriptions + ", frais_inscription=" + frais_inscription + '}';
    }

    public void setConference_id(Integer conference_id) {
        this.conference_id = conference_id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setInstitution_organisatrice(String institution_organisatrice) {
        this.institution_organisatrice = institution_organisatrice;
    }

    public void setUtilisateur_id(Integer utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setDate_limite_soumission_articles(String date_limite_soumission_articles) {
        this.date_limite_soumission_articles = date_limite_soumission_articles;
    }

    public void setDate_limite_inscriptions(String date_limite_inscriptions) {
        this.date_limite_inscriptions = date_limite_inscriptions;
    }

    public void setFrais_inscription(String frais_inscription) {
        this.frais_inscription = frais_inscription;
    }
}
    

