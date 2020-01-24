package Model;

import java.util.Comparator;

public class CompareSong implements Comparator<Song>{
    @Override
    public int compare(Song o1, Song o2) {
        //Ordena primero por titulo y luego por fecha
        String titulo1 = o1.getTitulo();
        String titulo2 = o2.getTitulo();
        int sComp = titulo1.compareTo(titulo2);
        
        if(sComp != 0){
            return sComp;
        }
        
        return Integer.compare(o1.getAno(),o2.getAno());
    }
}
