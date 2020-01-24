package Model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import static java.lang.Integer.sum;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * @author Pablo Jesús González Rubio
 * @serial 70894492M
 */
public class Model {

    Random rand = new Random();

    public Musicfy datos;

    public List<Album> list_albumes = new ArrayList<>();
    Album alb;
    List<String> nom_albumes = new ArrayList<>();

    public List<Artist> list_artistas = new ArrayList<>();
    Artist art;
    List<String> nom_artistas = new ArrayList<>();

    public List<Song> list_canciones = new ArrayList<>();
    Song son;
    List<String> nom_canciones = new ArrayList<>();

    public List<Playlist> list_playlists = new ArrayList<>();
    Playlist pla;
    List<String> nom_playlists = new ArrayList<>();

    public List<Album> getList_albumes() {
        return list_albumes;
    }

    public List<Artist> getList_artistas() {
        return list_artistas;
    }

    public List<Song> getList_canciones() {
        return list_canciones;
    }

    public List<Playlist> getList_playlists() {
        return list_playlists;
    }

    public Song vrfy_song(String cancion) {
        for (Song son : list_canciones) {
            if (cancion.equals(son.getTitulo())) {
                return son;
            }
        }
        return null;
    }

    public Album vrfy_album(String album) {
        for (Album alb : list_albumes) {
            if (album.equals(alb.getTitulo())) {
                return alb;
            }
        }
        return null;
    }

    public Artist vrfy_artist(String artista) {
        for (Artist art : list_artistas) {
            if (artista.equals(art.getNombre())) {
                return art;
            }
        }
        return null;
    }

    public Playlist vrfy_playlist(String playlist) {
        for (Playlist pla : list_playlists) {
            if (playlist.equals(pla.getNombre())) {
                return pla;
            }
        }
        return null;
    }

    public int vrfy_files() {
        File musicfy = FileSystems.getDefault().getPath(System.getProperty("user.home"), File.separator + "Desktop" + File.separator + "musicfy" + File.separator + "binarios" + File.separator + "musicfy.bin").toFile();
        int salir = 1;
        if (musicfy.exists()) {
            FileInputStream fis;
            BufferedInputStream bis;
            ObjectInputStream ois = null;
            try {
                fis = new FileInputStream(musicfy);
                bis = new BufferedInputStream(fis);
                ois = new ObjectInputStream(bis);
                datos = (Musicfy) ois.readObject();
                ois.close();
            } catch (IOException | ClassNotFoundException | ClassCastException ex) {
                salir = -6;
            } finally {
                if (null != ois) {
                    try {
                        ois.close();
                    } catch (IOException ex) {
                        salir = -6;
                    }
                }
            }
            if (salir != -6) {
                list_canciones = datos.getCanciones();
                list_albumes = datos.getAlbumes();
                list_artistas = datos.getArtistas();
                list_playlists = datos.getPlaylists();
                return salir;
            }
        }
        if (salir == -6 || !musicfy.exists()) {
            //Recoge los datos de la carpeta datos si no existe el musicfy.bin
            File albumes = FileSystems.getDefault().getPath(System.getProperty("user.home"), File.separator + "Desktop" + File.separator + "musicfy" + File.separator + "datos" + File.separator + "albumes.txt").toFile();
            if (albumes.exists()) {
                File artistas = FileSystems.getDefault().getPath(System.getProperty("user.home"), File.separator + "Desktop" + File.separator + "musicfy" + File.separator + "datos" + File.separator + "artistas.txt").toFile();
                if (artistas.exists()) {
                    try {
                        //Leer datos de albumes
                        nom_albumes = Files.readAllLines(albumes.toPath());
                        for (String st : nom_albumes) {
                            String[] dal = st.split("\t"); //Separa la línea por tabuladores
                            if (dal.length != 6 && dal.length != 7) {
                                return -3;
                            }
                            //DURACIÓN
                            //Con un regexp coge los números y los mete en una lista
                            List<String> length = Arrays.asList(dal[3].replaceAll("[^0-9]+", " ").trim().split(" "));
                            float duracion = 0;
                            if (length.size() == 1) { //Si sólo están los minutos
                                duracion = Float.parseFloat(String.format("%d", Integer.parseInt(length.get(0))));
                            } else { //Si están los minutos y los segundos
                                duracion = Float.parseFloat(String.format("%d.%d", Integer.parseInt(length.get(0)), Integer.parseInt(length.get(1))));
                            }
                            //////////

                            //LISTA INTERPRETES
                            nom_artistas = Arrays.asList(dal[1].split(";"));
                            //Generamos artistas para la lista de artistas.
                            for (String artista : nom_artistas) {
                                List<String> alb = new ArrayList<>();
                                alb = Arrays.asList(dal[0].split(";"));
                                list_artistas.add(new Artist(artista, " ", " ", " ", " ", " ", alb));
                            }
                            ///////////////////

                            if (dal[5].equals("álbum")) {
                                //Lista de nombres de canciones
                                List<String> nom_canciones = Arrays.asList(dal[6].split(";"));
                                List<Song> cancionesAlbum = new ArrayList<>();
                                for (String nom_cancion : nom_canciones) {
                                    cancionesAlbum.add(new Song(nom_cancion, Integer.parseInt(dal[2]), rand.nextInt((int) duracion) + 1, nom_artistas));
                                }
                                list_albumes.add(new Album(dal[0], Integer.parseInt(dal[2]), duracion, nom_artistas, Integer.parseInt(dal[4]), dal[5], cancionesAlbum));
                                list_canciones.addAll(cancionesAlbum);
                            } else if (dal[5].equals("sencillo")) {
                                List<Song> tmp = new ArrayList<>();
                                tmp.add(new Song(dal[0], Integer.parseInt(dal[2]), rand.nextInt((int) duracion) + 1, nom_artistas));
                                list_albumes.add(new Album(dal[0], Integer.parseInt(dal[2]), duracion, nom_artistas, Integer.parseInt(dal[4]), dal[5], tmp));
                                list_canciones.addAll(tmp);
                            } else {
                                return -4;
                            }
                        }

                        //Leer datos de artistas
                        List<String> datos_artistas = Files.readAllLines(artistas.toPath());
                        for (String st : datos_artistas) {
                            String[] dar = st.split("#"); //Separa la línea por tabuladores
                            if (dar.length != 7) {
                                return -5;
                            }
                            List<String> list_albums = Arrays.asList(dar[6].split(";")); //Crea la lista de interpretes
                            //Metemos la lista de albumes de los artistas en la lista de albumes del programa.
                            //Como no se dan más datos se generan algunos aleatorios.
                            for (String album_tmp : list_albums) {
                                int randYear = 1 + rand.nextInt(Calendar.getInstance().get(Calendar.YEAR));
                                float randDuracion = 1 + rand.nextFloat() * (100 - 1);
                                List<String> tmp = new ArrayList<>();
                                tmp.add(dar[0]);
                                List<Song> tmp2 = new ArrayList<>();
                                tmp2.add(new Song(" ", randYear, randDuracion, tmp));
                                list_albumes.add(new Album(album_tmp, randYear, randDuracion, tmp, 1, "sencillo", tmp2));
                            }
                            list_artistas.add(new Artist(dar[0], dar[1], dar[2], dar[3], dar[4], dar[5], list_albums));
                        }
                        return 2;
                    } catch (IOException ex) {
                        return -7;
                    }
                } else {
                    return -2;
                }
            } else {
                return -1;
            }
        }
        return 0;
    }

    public int random(int num_datos) {
        File nombresAlbumes = FileSystems.getDefault().getPath(System.getProperty("user.home"), File.separator + "Desktop" + File.separator + "musicfy" + File.separator + "aleatorios" + File.separator + "nombresAlbumes.txt").toFile();
        File nombresArtistas = FileSystems.getDefault().getPath(System.getProperty("user.home"), File.separator + "Desktop" + File.separator + "musicfy" + File.separator + "aleatorios" + File.separator + "nombresArtistas.txt").toFile();
        File nombresPlaylists = FileSystems.getDefault().getPath(System.getProperty("user.home"), File.separator + "Desktop" + File.separator + "musicfy" + File.separator + "aleatorios" + File.separator + "nombresPlaylists.txt").toFile();
        File titulosCanciones = FileSystems.getDefault().getPath(System.getProperty("user.home"), File.separator + "Desktop" + File.separator + "musicfy" + File.separator + "aleatorios" + File.separator + "titulosCanciones.txt").toFile();
        if (!nombresAlbumes.exists() || nombresAlbumes.length() == 0) {
            if (!nombresArtistas.exists() || nombresArtistas.length() == 0) {
                if (!nombresPlaylists.exists() || nombresPlaylists.length() == 0) {
                    if (!titulosCanciones.exists() || titulosCanciones.length() == 0) {
                        return -4;
                    }
                    return -3;
                }
                return -2;
            }
            return -1;
        }
        try {
            //Canciones
            nom_canciones = Files.readAllLines(titulosCanciones.toPath());
            List<String> canciones2 = new ArrayList<>();
            // Coge un número aleatorio de nombres de la lista original
            for (int n = 0; n < num_datos; n++) {
                // Coge valores aleatorios de la lista
                canciones2.add(nom_canciones.get(rand.nextInt(nom_canciones.size())));
            }
            for (int i = 0; i < num_datos; i++) {
                String cancion = nom_canciones.get(rand.nextInt(nom_canciones.size()));
                float randDuration = 1 + rand.nextFloat() * (7 - 1);
                int randYear = sum(rand.nextInt(Calendar.getInstance().get(Calendar.YEAR)), 1);
                son = new Song(cancion, 0, randDuration, canciones2);
                list_canciones.add(son);
            }
            ///////////

            //Playlists
            nom_playlists = Files.readAllLines(nombresPlaylists.toPath());
            //En las canciones de la playlist son una lista objetos Song.
            for (int i = 0; i < num_datos; i++) {
                String playlist = nom_playlists.get(rand.nextInt(nom_playlists.size()));
                //Coge la lista aleatoria de canciones anterior.
                pla = new Playlist(playlist, list_canciones);
                list_playlists.add(pla);
            }
            ///////////

            //Artistas
            nom_artistas = Files.readAllLines(nombresArtistas.toPath());
            //Lista albumes
            nom_albumes = Files.readAllLines(nombresAlbumes.toPath());
            List<String> albumes2 = new ArrayList<>();
            for (int n = 0; n < num_datos; n++) {
                albumes2.add(nom_albumes.get(rand.nextInt(nom_albumes.size())));
            }
            // Llena la lista de Artistas con esos valores
            for (int i = 0; i < num_datos; i++) {
                String artista = nom_artistas.get(rand.nextInt(nom_artistas.size()));
                art = new Artist(artista, " ", " ", " ", " ", " ", albumes2);
                list_artistas.add(art);
            }
            //////////

            //Albumes
            for (int i = 0; i < num_datos; i++) {
                String album = nom_albumes.get(rand.nextInt(nom_albumes.size()));
                float randDurationSong = 1 + rand.nextFloat() * (7 - 1);
                float randDurationAlbum = 1 + rand.nextFloat() * (100 - 1);
                int randYear = sum(rand.nextInt(Calendar.getInstance().get(Calendar.YEAR)), 1);
                List<String> lista_interpretes = new ArrayList<>();
                lista_interpretes.add(nom_artistas.get(rand.nextInt(nom_artistas.size())));
                if ((rand.nextInt(2)) != 1) {
                    //Aquí si da 0. Se crea un álbum.
                    List<Song> lista_canciones = new ArrayList<>();
                    Song temp1 = new Song(album, randYear, randDurationSong, lista_interpretes);
                    for (int j = 0; j < num_datos; j++) {
                        lista_canciones.add(list_canciones.get(rand.nextInt(list_canciones.size())));
                    }
                    alb = new Album(album, randYear, randDurationAlbum, lista_interpretes, lista_canciones.size(), "álbum", lista_canciones);
                } else {
                    //Aquí se crea un sencillo
                    List<Song> lista_canciones = new ArrayList<>();
                    Song temp1 = new Song(album, randYear, randDurationSong, lista_interpretes);
                    lista_canciones.add(temp1);
                    alb = new Album(album, randYear, randDurationAlbum, lista_interpretes, 1, "sencillo", lista_canciones);
                }
                list_albumes.add(alb);
            }
            /////////
            return 0;
        } catch (IOException ex) {
            return -5;
        } catch (Throwable ex) {
            System.out.println(ex);
            return -5;
        }
    }

    public List<Artist> export_artist() {
        return list_artistas;
    }

    public List<Album> export_album() {
        return list_albumes;
    }

    public void new_album(Album album) {
        list_albumes.add(album);
        List<String> lista_albumes = new ArrayList<>();
        lista_albumes.add(album.getTitulo());
        //Añade las canciones del album a la lista de canciones
        for (Song cancion : album.getCanciones()) {
            list_canciones.add(cancion);
        }
        for (String artista : album.getInterpretes()) {
            Artist art = vrfy_artist(artista);
            if (art == null) {
                //Si no existe el artista, lo crea.
                list_artistas.add(new Artist(artista, "", "", "", "", "", lista_albumes));
            } else {
                //Si el artista existe, añade sólo el título del álbum.
                art.getAlbumes().add(album.getTitulo());
            }
        }
    }

    public void del_album(Album album) {
        //Hay que utilizar un iterator porque no se puede borrar mientras se está leyendo del mismo sitio.
        Iterator<Song> its = list_canciones.iterator();
        while (its.hasNext()) {
            Song actual = its.next();
            for (Song son : album.getCanciones()) {
                if (actual.getTitulo().equals(son.getTitulo())) {
                    its.remove();
                }
            }
        }
        Iterator<Album> ita = list_albumes.iterator();
        while (ita.hasNext()) {
            Album actual = ita.next();
            if (actual.getTitulo().equals(album.getTitulo())) {
                ita.remove();
            }
        }
    }

    public void mod_album(int opcion, Album album, Object dato) {
        int pos = list_albumes.indexOf(album);
        switch (opcion) {
            case 1:
                int ano = (int) dato;
                list_albumes.get(pos).setAno(ano);
                break;
            case 2:
                float duracion = (float) dato;
                list_albumes.get(pos).setDuracion(duracion);
                break;
            case 3:
                int num_songs = (int) dato;
                list_albumes.get(pos).setNum_songs(num_songs);
                break;
        }

    }

    public Album consulta(String albumConsultado) {
        //El album se recoge mal, recoge las canciones de todos los albumes
        //  en vez de sólo el álbum pedido.
        //Creo que tiene que ver con el que se guarde la misma lista de canciones en los albumes (cancionesAlbum).
        //Pero si le hago un clear al principio se borran las de los demás.
        //Entonces lo que se me había ocurrido era hacer una lista de listas song, o un vector de listas song,
        //  pero tendría un problema con el indizado, a lo mejor se podría eliminar con un ListIterator
        //  verificando si es ese valor, pero no sé.
        return vrfy_album(albumConsultado);
    }

    public void new_artist(Artist artista) {
        list_artistas.add(artista);
        for (String alb : artista.getAlbumes()) {
            List<String> interprete = new ArrayList<>();
            interprete.add(artista.getNombre());
            List<Song> canciones = new ArrayList<>();
            canciones.add(new Song("Unknown", 0, 0, interprete));
            list_albumes.add(new Album(alb, 0, 0, interprete, 0, "álbum", canciones));
        }
    }

    public int del_artist(String artista) {
        int flag = 0;
        for (Artist art : list_artistas) {
            if (artista.equals(art.getNombre())) {
                flag = 1;
                for (String alb : art.getAlbumes()) {
                    for (Album album : list_albumes) {
                        if (album.getTitulo().equals(alb)) {
                            flag = 2;
                        }
                    }
                }
                if (flag == 1) {
                    list_artistas.remove(art);
                    return 0;
                }
                break;
            }
        }
        if (flag == 0) {
            return -2;
        } else { //Si el flag vale 2
            return -1;
        }
    }

    public void mod_artist(int opcion, Artist artista, String dato) {
        int pos = list_artistas.indexOf(artista);
        switch (opcion) {
            case 1:
                list_artistas.get(pos).setBiografia(dato);
                break;
            case 2:
                list_artistas.get(pos).setInstagram(dato);
                break;
            case 3:
                list_artistas.get(pos).setTwitter(dato);
                break;
            case 4:
                list_artistas.get(pos).setFacebook(dato);
                break;
            case 5:
                list_artistas.get(pos).setWikipedia(dato);
                break;
        }
    }

    public List<Album> albumes(String artista) {
        List<Album> list = new ArrayList<>();
        for (Album album : list_albumes) {
            for (String art : album.getInterpretes()) {
                if (artista.equals(art)) {
                    list.add(album);
                }
            }
        }
        if (list.size() == 0) {
            return null;
        } else {
            return list;
        }
    }

    public void new_playlist(String titulo, int num_songs) {
        List<Song> canciones = new ArrayList<>();
        for (int n = 0; n < num_songs; n++) {
            canciones.add(list_canciones.get(rand.nextInt(list_canciones.size())));
        }
        list_playlists.add(new Playlist(titulo, canciones));
    }

    public int new_song(String titulo, String playlist) {
        Playlist pla = vrfy_playlist(playlist);
        if (pla == null) {
            return -2;
        }
        Song son = vrfy_song(titulo);
        if (son == null) {
            return -1;
        } else {
            pla.getCanciones().add(son);
            return 0;
        }
    }

    public int del_song(String cancion, Playlist pla) {
        for (Song son : pla.getCanciones()) {
            if (cancion.equals(son.getTitulo())) {
                if (list_playlists.remove(son) == true) {
                    return 0;
                } else {
                    return -1;
                }
            }
        }
        return -1;
    }

    public List<Song> list() {
        return list_canciones;
    }

    public Musicfy save() {
        return (new Musicfy(list_canciones, list_albumes, list_artistas, list_playlists));
    }
}
