package Model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Pablo Jesús González Rubio
 * @serial 70894492M
 */
public class Song implements Serializable {

    private String titulo;
    private int ano;
    private float duracion;
    private List<String> interpretes; //Sólo los nombres

    public Song(String titulo, int ano, float duracion, List<String> interpretes) {
        this.titulo = titulo;
        this.ano = ano;
        this.duracion = duracion;
        this.interpretes = interpretes;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public float getDuracion() {
        return duracion;
    }

    public void setDuracion(float duracion) {
        this.duracion = duracion;
    }

    public List<String> getInterpretes() {
        return interpretes;
    }

    public void setInterpretes(List<String> interpretes) {
        this.interpretes = interpretes;
    }

}
