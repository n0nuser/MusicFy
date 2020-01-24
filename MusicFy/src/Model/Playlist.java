package Model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Pablo Jesús González Rubio
 * @serial 70894492M
 */
public class Playlist implements Serializable {

    private String nombre;
    private List<Song> canciones; //(colección con las canciones (objetos canción) de la playlist

    public Playlist(String nombre, List<Song> canciones) {
        this.nombre = nombre;
        this.canciones = canciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Song> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<Song> canciones) {
        this.canciones = canciones;
    }

    public String consultaPlaylist(Playlist pla) {
        String datos = String.format("%nTítulo: %s%nCanciones:%n", pla.getNombre());
        for (Song cancion : pla.getCanciones()) {
            datos = datos.concat(String.format("%n  - %s", cancion.getTitulo()));
        }
        return datos;
    }
}
