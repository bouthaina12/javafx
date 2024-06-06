/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projetjava;

/**
 *
 * @author bouth
 */
public class Article {
    private Integer articleId;
    private Integer conferenceId;
    private String titre;
    private String fichierPdf;
    private String etat;
    private Integer comiteScientifiqueId;

    // Constructor with all fields
    public Article(Integer articleId, Integer conferenceId, String titre, String fichierPdf, String etat, Integer comiteScientifiqueId) {
        this.articleId = articleId;
        this.conferenceId = conferenceId;
        this.titre = titre;
        this.fichierPdf = fichierPdf;
        this.etat = etat;
        this.comiteScientifiqueId = comiteScientifiqueId;
    }
    
   
    // Getters and Setters
    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public Integer getConferenceId() {
        return conferenceId;
    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getFichierPdf() {
        return fichierPdf;
    }

    public void setFichierPdf(String fichierPdf) {
        this.fichierPdf = fichierPdf;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Integer getComiteScientifiqueId() {
        return comiteScientifiqueId;
    }

    public void setComiteScientifiqueId(int comiteScientifiqueId) {
        this.comiteScientifiqueId = comiteScientifiqueId;
    }
}
