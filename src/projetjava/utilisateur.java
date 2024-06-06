/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package projetjava;

/**
 *
 * @author bouth
 */
public class utilisateur {
   private Integer  utilisateur_id ;
   private String nom;
   private String adresse_email;
   private String  institution;
   private String username;
   private String  pswrd;
   private Integer  est_administrateur ;

    public utilisateur(Integer utilisateur_id, String nom, String adresse_email, String institution, String username, String pswrd, Integer est_administrateur) {
        this.utilisateur_id = utilisateur_id;
        this.nom = nom;
        this.adresse_email = adresse_email;
        this.institution = institution;
        this.username = username;
        this.pswrd = pswrd;
        this.est_administrateur = est_administrateur;
    }

     public utilisateur() {
        // Initialisation des valeurs par défaut ou laisser les champs non initialisés
        this.utilisateur_id =null;
        this.nom = "";
        this.adresse_email = "";
        this.institution = "";
        this.username = "";
        this.pswrd = "";
        this.est_administrateur =null;
    }
   

    public Integer getUtilisateur_id() {
        return utilisateur_id;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse_email() {
        return adresse_email;
    }

    public String getInstitution() {
        return institution;
    }

    public String getUsername() {
        return username;
    }

    public String getPswrd() {
        return pswrd;
    }

    public Integer getEst_administrateur() {
        return est_administrateur;
    }

    public void setUtilisateur_id(Integer utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse_email(String adresse_email) {
        this.adresse_email = adresse_email;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPswrd(String pswrd) {
        this.pswrd = pswrd;
    }

    public void setEst_administrateur(Integer est_administrateur) {
        this.est_administrateur = est_administrateur;
    }

    @Override
    public String toString() {
        return "utilisateur{" + "utilisateur_id=" + utilisateur_id + ", nom=" + nom + ", adresse_email=" + adresse_email + ", institution=" + institution + ", username=" + username + ", pswrd=" + pswrd + ", est_administrateur=" + est_administrateur + '}';
    }
   
}
