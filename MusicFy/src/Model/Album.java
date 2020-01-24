package Model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Pablo Jesús González Rubio
 * @serial 70894492M
 */
public class Album implements Serializable {

    private String titulo;
    private int ano;
    private float duracion;
    private List<String> interpretes; //Sólo nombres de intérpretes
    private int num_songs;
    private String tipo; //Album o sencillo
    private List<Song> canciones; //Sólo las canciones (objeto canción)

    public Album(String titulo, int ano, float duracion, List<String> interpretes, int num_songs, String tipo, List<Song> canciones) {
        this.titulo = titulo;
        this.ano = ano;
        this.duracion = duracion;
        this.interpretes = interpretes;
        this.num_songs = num_songs;
        this.tipo = tipo;
        this.canciones = canciones;
    }

    public String consultaAlbum(Album album) {
        String datos = String.format("%nTítulo: %s%nAño: %d%nDuración: %.2f%nNº Canciones: %d%nTipo: %s%nIntérpretes:", album.getTitulo(), album.getAno(), album.getDuracion(), album.getNum_songs(), album.getTipo());
        for (String interprete : album.getInterpretes()) {
            datos = datos.concat(String.format("%n  - %s", interprete));
        }
        datos = datos.concat(String.format("%nCanciones:"));
        for (Song cancion : album.getCanciones()) {
            datos = datos.concat(String.format("%n  - %s", cancion.getTitulo()));
        }
        return datos;
    }

    public String consultaAlbumTabla(Album album) {
        String datos = String.format("| %20s | %5d | %5.2f | %5d | %5s |", album.getTitulo(), album.getAno(), album.getDuracion(), album.getNum_songs(), album.getTipo());
        for (String interprete : album.getInterpretes()) {
            datos = datos.concat(String.format(" %s |", interprete));
        }
        datos = datos.concat(String.format("\n"));
        return datos;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAno() {
        return ano;
    }

    public float getDuracion() {
        return duracion;
    }

    public List<String> getInterpretes() {
        return interpretes;
    }

    public int getNum_songs() {
        return num_songs;
    }

    public String getTipo() {
        return tipo;
    }

    public List<Song> getCanciones() {
        return canciones;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public void setInterpretes(List<String> interpretes) {
        this.interpretes = interpretes;
    }

    public void setNum_songs(int num_songs) {
        this.num_songs = num_songs;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setCanciones(List<Song> canciones) {
        this.canciones = canciones;
    }

}
