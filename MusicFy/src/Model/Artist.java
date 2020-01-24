package Model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Pablo Jesús González Rubio
 * @serial 70894492M
 */
public class Artist implements Serializable {

    private String nombre;
    private String biografia;
    private String instagram;
    private String twitter;
    private String facebook;
    private String wikipedia;
    private List<String> albumes; //Sólo nombres de álbumes

    public Artist(String nombre, String biografia, String instagram, String twitter, String facebook, String wikipedia, List<String> albumes) {
        this.nombre = nombre;
        this.biografia = biografia;
        this.instagram = instagram;
        this.twitter = twitter;
        this.facebook = facebook;
        this.wikipedia = wikipedia;
        this.albumes = albumes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public List<String> getAlbumes() {
        return albumes;
    }

    public void setAlbumes(List<String> albumes) {
        this.albumes = albumes;
    }

}
