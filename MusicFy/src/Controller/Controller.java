package Controller;

import Model.Album;
import Model.Artist;
import Model.Model;
import Model.Musicfy;
import Model.Playlist;
import Model.Song;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Pablo Jesús González Rubio
 * @serial 70894492M
 */
public class Controller {

    Model m = new Model();

    public int vrfy_files() {
        return m.vrfy_files();
    }

    public int random(int num) {
        return m.random(num);
    }

    public int export_artist() {
        FileWriter fw = null;
        PrintWriter pw = null;
        List<Artist> list_artistas = m.export_artist();
        //Crea directorio.
        File directory = FileSystems.getDefault().getPath(System.getProperty("user.home"), File.separator + "Desktop" + File.separator + "musicfy" + File.separator + "salida").toFile();
        if (!directory.exists()) {
            directory.mkdir();
        }
        //Crea fichero.
        File f = FileSystems.getDefault().getPath(System.getProperty("user.home"), File.separator + "Desktop" + File.separator + "musicfy" + File.separator + "salida" + File.separator + "artistas.col").toFile();
        try {
            fw = new FileWriter(f);
            pw = new PrintWriter(fw);
            for (Artist artista : list_artistas) {
                pw.printf("| %22s | %275s | %22s | %22s | %22s | %45s |", artista.getNombre(), artista.getBiografia(), artista.getInstagram(), artista.getTwitter(), artista.getFacebook(), artista.getWikipedia());
                for (String album : artista.getAlbumes()) {
                    pw.printf(" %30s |", album);
                }
                pw.printf("\n\n");
            }
            pw.close();
            return 0;
        } catch (IOException ex) {
            return -1;
        }
    }

    public int export_album() {
        List<Album> list_albumes = m.export_album();
        File directory = FileSystems.getDefault().getPath(System.getProperty("user.home"), File.separator + "Desktop" + File.separator + "musicfy" + File.separator + "salida").toFile();
        if (!directory.exists()) {
            directory.mkdir();
        }
        File f = FileSystems.getDefault().getPath(System.getProperty("user.home"), File.separator + "Desktop" + File.separator + "musicfy" + File.separator + "salida" + File.separator + "albumes.html").toFile();
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(f);
        } catch (IOException ex) {
            return -1;
        }
        PrintWriter printWriter = new PrintWriter(fileWriter);

        printWriter.print("<!DOCTYPE html>\n\n" + "<html>\n\n" + "<head>\n" + "<title>Álbumes</title>\n" + "</head>\n\n" + "<body>\n" + "<table style=\"border: black 10px double; text-align: center;border-spacing: 15px;\">\n");
        printWriter.printf("<tr>\n<th>Título</th><th>Año</th><th>Duración</th><th>Nº Canciones</th><th>Tipo</th><th>Intérpretes</th>\n</tr>\n");
        //AQUÍ EMPIEZA LO DE CADA ALBUM
        for (Album album : list_albumes) {
            printWriter.printf("<tr>\n"
                    + "<td>%s</td>\n"
                    + "<td>%d</td>\n"
                    + "<td>%.2f min.</td>\n"
                    + "<td>%d</td>\n"
                    + "<td>%s</td>\n", album.getTitulo(), album.getAno(), album.getDuracion(), album.getNum_songs(), album.getTipo());
            for (String interprete : album.getInterpretes()) {
                printWriter.printf("<td>%s</td>\n", interprete);
            }
        }
        printWriter.print("</tr>\n</table>\n" + "</body>\n\n" + "</html>");
        printWriter.close();
        return 0;
    }

    public void new_album(Album album) {
        m.new_album(album);
    }

    public void del_album(Album album) {
        m.del_album(album);
    }

    public void mod_album(int opcion, Album album, Object dato) {
        m.mod_album(opcion, album, dato);
    }

    public Album consulta(String albumConsultado) {
        return m.consulta(albumConsultado);
    }

    public void new_artist(Artist artista) {
        m.new_artist(artista);
    }

    public int del_artist(String artista) {
        return m.del_artist(artista);
    }

    public void mod_artist(int opcion, Artist artista, String dato) {
        m.mod_artist(opcion, artista, dato);
    }

    public List<Album> albumes(String artista) {
        return m.albumes(artista);
    }

    public void new_playlist(String titulo, int num_songs) {
        m.new_playlist(titulo, num_songs);
    }

    public int new_song(String titulo, String playlist) {
        return m.new_song(titulo, playlist);
    }

    public int del_song(String cancion, Playlist pla) {
        return m.del_song(cancion, pla);
    }

    public List<Song> list() {
        return m.list();
    }

    public void save() {
        Musicfy datos = m.save();

        File directory = FileSystems.getDefault().getPath(System.getProperty("user.home"), File.separator + "Desktop" + File.separator + "musicfy" + File.separator + "binarios").toFile();
        if (!directory.exists()) {
            directory.mkdir();
        }

        File f = FileSystems.getDefault().getPath(System.getProperty("user.home"), File.separator + "Desktop" + File.separator + "musicfy" + File.separator + "binarios" + File.separator + "musicfy.bin").toFile();
        FileOutputStream fos;
        BufferedOutputStream bos;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(f);
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);
            oos.writeObject(datos);
            oos.close();
        } catch (IOException ex) {
            System.out.println("No fue posible guardar el archivo.");
            System.out.println(ex.toString());
        } finally {
            if (null != oos) {
                try {
                    oos.close();
                } catch (IOException ex) {
                    System.out.println("No fue posible guardar el archivo.");
                    System.out.println(ex.toString());
                }
            }
        }
    }

    public Song vrfy_song(String cancion) {
        return m.vrfy_song(cancion);
    }

    public Album vrfy_album(String album) {
        return m.vrfy_album(album);
    }

    public Artist vrfy_artist(String artista) {
        return m.vrfy_artist(artista);
    }

    public Playlist vrfy_playlist(String playlist) {
        return m.vrfy_playlist(playlist);
    }

}
