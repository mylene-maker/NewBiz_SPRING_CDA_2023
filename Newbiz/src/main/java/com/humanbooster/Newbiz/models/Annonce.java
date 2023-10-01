package com.humanbooster.Newbiz.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "annonce")
public class Annonce {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    @NotBlank(message = "Veuillez saisir un titre")
    private String titre;

    @Column(nullable = true)
    private String image;

    @Column(columnDefinition = "TEXT", nullable = false)
    @NotBlank(message = "Veuillez saisir un contenu")
    private String contenu;

    @Temporal(TemporalType.TIMESTAMP)
//    @NotNull(message = "La date est obligatoire")
    @Column(name="date_publication", columnDefinition = "timestamp")
    private Date dateDePublication;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    public Annonce() {
    }

    public Annonce(long id, String titre, String image, String contenu, @NotNull(message = "La date est obligatoire") Date dateDePublication, Category category) {
        this.id = id;
        this.titre = titre;
        this.image = image;
        this.contenu = contenu;
        this.dateDePublication = dateDePublication;
        this.category = category;
    }

    public Annonce(String titre, String image, String contenu, Category category) {
        this.titre = titre;
        this.image = image;
        this.contenu = contenu;
        this.category = category;
        dateDePublication = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDateDePublication() {
        return dateDePublication;
    }

    public void setDateDePublication(Date dateDePublication) {
        this.dateDePublication = dateDePublication;
    }

    public Category getCategory() {
        return (Category) category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
