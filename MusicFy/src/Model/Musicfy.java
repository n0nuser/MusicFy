package Model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Pablo Jesús González Rubio
 * @serial 70894492M
 */
public class Musicfy implements Serializable {

    private List<Song> canciones;
    private List<Album> albumes;
    private List<Artist> artistas;
    private List<Playlist> playlists;

    public Musicfy(List<Song> canciones, List<Album> albumes, List<Artist> artistas, List<Playlist> playlists) {
        this.canciones = canciones;
        this.albumes = albumes;
        this.artistas = artistas;
        this.playlists = playlists;
    }

    public List<Song> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Song> canciones) {
        this.canciones = canciones;
    }

    public List<Album> getAlbumes() {
        return albumes;
    }

    public void setAlbumes(List<Album> albumes) {
        this.albumes = albumes;
    }

    public List<Artist> getArtistas() {
        return artistas;
    }

    public void setArtistas(List<Artist> artistas) {
        this.artistas = artistas;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

}
