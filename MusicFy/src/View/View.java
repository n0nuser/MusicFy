package View;

import Controller.Controller;
import Model.*;
import static com.coti.tools.Esdia.readString;
import static com.coti.tools.Esdia.yesOrNo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * @author Pablo Jesús González Rubio
 * @serial 70894492M
 */
public class View {

    Controller c = new Controller();
    Scanner sc = new Scanner(System.in);
    int files_vrfy;

    public void vrfy_files() {
        files_vrfy = c.vrfy_files();
        switch (files_vrfy) {
            case 1:
                System.out.println("\nSe ha leído correctamente el archivo 'musicfy.bin'.\n");
                break;
            case 2:
                System.out.println("\nSe han leído correctamente los datos 'artistas.txt' y 'albumes.txt'.\n");
                break;
            case -1:
                System.out.println("\nEl archivo 'albumes.txt' no existe. Genere ahora los datos aleatoriamente.\n");
                break;
            case -2:
                System.out.println("\nEl archivo 'albumes.txt' existe pero NO el archivo 'artistas.txt'. Genere ahora los datos aleatoriamente.\n");
                break;
            case -3:
                System.out.println("\nEl archivo 'albumes.txt' tiene formato incorrecto. Genere ahora los datos aleatoriamente.\n");
                break;
            case -4:
                System.out.println("\nEn el archivo 'albumes.txt'.Dato 'Álbum' con valor incorrecto, revise el fichero. Genere ahora los datos aleatoriamente.\n");
                break;
            case -5:
                System.out.println("\nEl archivo 'artistas.txt' tiene formato incorrecto. Genere ahora los datos aleatoriamente.\n");
                break;
            case -6:
                System.out.println("\nEl archivo 'musicfy.bin' no se ha podido leer. Genere ahora los datos aleatoriamente.\n");
                break;
            case -7:
                System.out.println("\nLos archivos 'artistas.txt' y 'albumes.txt' no se han podido leer. Genere ahora los datos aleatoriamente.\n");
                break;
            default:
                System.out.println("\nNo se han podido leer los archivos. Genere ahora los datos aleatoriamente..");
                break;
        }
    }

    public void runMenu(String menu) {
        boolean salir = false;
        String option;
        String[] availableOptions = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "q"};
        do {
            System.out.println("\n\nPulse una tecla para continuar.\n");
            try {
                System.in.read();
            } catch (IOException ex) {
            }
            option = readString(menu, availableOptions).toLowerCase();
            switch (option) {
                case "1":
                    this.random();
                    break;
                case "2":
                    this.export_artist();
                    break;
                case "3":
                    this.export_album();
                    break;
                case "4":
                    this.new_album();
                    break;
                case "5":
                    this.del_album();
                    break;
                case "6":
                    this.mod_album();
                    break;
                case "7":
                    this.consulta();
                    break;
                case "8":
                    this.new_artist();
                    break;
                case "9":
                    this.del_artist();
                    break;
                case "10":
                    this.mod_artist();
                    break;
                case "11":
                    this.albumes();
                    break;
                case "12":
                    this.new_playlist();
                    break;
                case "13":
                    this.new_song();
                    break;
                case "14":
                    this.del_song();
                    break;
                case "15":
                    this.list();
                    break;
                case "q":
                    this.salir();
                    salir = yesOrNo("Desea salir: ");
            }
        } while (!salir);
    }

    private void random() {
        // Inicializa todas las colecciones con valores aleatorios.
        // Se debe intentar que los valores sean lo más aleatorios posibles, 
        //  por lo que sería recomendable que los valores se cargaran desde ficheros.
        // Los datos no tienen que ser reales ni guardar relación.
        // Se podrían tener colecciones de títulos tanto de canciones como de álbumes, nombres de artistas, generar años y duraciones, etc.
        System.out.printf("Introduzca el número de datos aleatorios a generar: ");
        try {
            String tmp = sc.nextLine();
            int num = Integer.parseInt(tmp);
            if (num <= 0) {
                System.out.println("\nNo se puede introducir un valor nulo o negativo.");
                return;
            }
            int rand = c.random(num);
            switch (rand) {
                case -1:
                    System.out.println("\n~/Desktop/musicfy/aleatorios/nombresAlbumes.txt no existe o está vacío.");
                    if (files_vrfy != 1 || files_vrfy != 2) {
                        System.out.println("Los archivos de datos iniciales o el musicfy.bin no existen tampoco. Abortando...");
                        System.exit(0);
                    }
                    break;
                case -2:
                    System.out.println("\n~/Desktop/musicfy/aleatorios/nombresArtistas.txt no existe o está vacío.");
                    if (files_vrfy != 1 || files_vrfy != 2) {
                        System.out.println("Los archivos de datos iniciales o el musicfy.bin no existen tampoco. Abortando...");
                        System.exit(0);
                    }
                    break;
                case -3:
                    System.out.println("\n~/Desktop/musicfy/aleatorios/nombresPlaylists.txt no existe o está vacío.");
                    if (files_vrfy != 1 || files_vrfy != 2) {
                        System.out.println("Los archivos de datos iniciales o el musicfy.bin no existen tampoco. Abortando...");
                        System.exit(0);
                    }
                    break;
                case -4:
                    System.out.println("\n~/Desktop/musicfy/aleatorios/titulosCanciones.txt no existe o está vacío.");
                    if (files_vrfy != 1 || files_vrfy != 2) {
                        System.out.println("Los archivos de datos iniciales o el musicfy.bin no existen tampoco. Abortando...");
                        System.exit(0);
                    }
                    break;
                case -5:
                    System.out.println("No se han podido leer los archivos.");
                    break;
                case -6:
                    System.out.println("No se han podido generar los datos aleatorios.");
                    break;
                default:
                    System.out.println("Se han generado los datos aleatorios exitosamente.");
                    break;
            }
        } catch (Throwable ex) {
            System.out.println("Dato con mal formato.");
        }
    }

    private void export_artist() {
        // Exportar artistas a un archivo llamado artistas.col, con formato de columnas.
        // El archivo se escribe en la carpeta ~/Desktop/musicfy/salida/.
        int salida = c.export_artist();
        if (salida == -1) {
            System.out.println("No se han podido exportar los artistas.");
        } else {
            System.out.println("Se han exportado los artistas correctamente.");
        }
    }

    private void export_album() {
        // Exportar álbumes (sin incluir las canciones) a un archivo llamado albumes.html, con formato de tabla html.
        // El archivo se escribe en la carpeta ~/Desktop/musicfy/salida/.
        int salida = c.export_album();
        if (salida == -1) {
            System.out.println("No se han podido exportar los albumes.");
        } else {
            System.out.println("Se han exportado los albumes correctamente.");
        }
    }

    private void new_album() {
        // Añadir un álbum a la colección de álbumes.
        // Habrá que añadir las canciones tanto a la colección del álbum,
        //  como a la colección general de Song de Musicfy.
        // En caso de que no exista el artista en su colección correspondiente se añadirá,
        //  dejando los atributos de los que no se dispone información con sus valores por defecto.
        System.out.printf("Introduzca el título del álbum: ");
        String titulo = sc.nextLine();
        System.out.printf("Introduzca el año de salida del álbum: ");
        try {
            String tmp = sc.nextLine();
            int ano = Integer.parseInt(tmp);
            if (ano <= 0) {
                System.out.println("No se puede introducir un valor nulo o negativo.");
                return;
            }
            System.out.printf("Introduzca la duración del álbum: ");
            try {
                String tmp2 = sc.nextLine();
                float duracion = Float.parseFloat(tmp2);
                if (duracion <= 0) {
                    System.out.println("No se puede introducir un valor nulo o negativo.");
                    return;
                }
                System.out.printf("Introduzca el número de canciones que tendrá: ");
                try {
                    String tmp3 = sc.nextLine();
                    int num_songs = Integer.parseInt(tmp3);
                    if (num_songs <= 0) {
                        System.out.println("No se puede introducir un valor nulo o negativo.");
                        return;
                    }
                    String tipo;
                    if (num_songs == 1) {
                        tipo = "sencillo";
                    } else {
                        tipo = "álbum";
                    }
                    //lista intérpretes
                    List<String> interpretes = new ArrayList<>();
                    String interprete = "";
                    System.out.printf("Introduzca ahora los intérpretes del album (Introduzca 'q' para dejar de escribir).\n");
                    while (!interprete.equals("q") || interprete.isEmpty()) {
                        System.out.printf("\nIntroduzca artista: ");
                        interprete = sc.nextLine();
                        if (interprete.isEmpty()) {
                            System.out.println("El artista no puede estar vacío.");
                        }
                        interpretes.add(interprete);
                    }
                    interpretes.remove(0);
                    interpretes.remove(interpretes.size() - 1);
                    //lista canciones
                    List<Song> canciones = new ArrayList<>();
                    System.out.printf("\n\nIntroduzca ahora las canciones del album.\n");
                    for (int i = 1; i <= num_songs; i++) {
                        System.out.printf("Introduzca cancion %d: ", i);
                        canciones.add(new Song(sc.nextLine(), ano, duracion, interpretes));
                    }
                    canciones.remove(canciones.size() - 1);

                    Album album = new Album(titulo, ano, duracion, interpretes, num_songs, tipo, canciones);
                    c.new_album(album);
                    System.out.println("Se han añadido el álbum y sus datos correctamente.");
                } catch (Throwable ex) {
                    System.out.println("Dato con mal formato.");
                }
            } catch (Throwable ex) {
                System.out.println("Dato con mal formato.");
            }
        } catch (Throwable ex) {
            System.out.println("Dato con mal formato.");
        }
    }

    private void del_album() {
        // Borrar un álbum de la colección de álbumes,
        //  además hay que quitar el nombre del álbum de la colección de títulos del artista
        //  y borrar las canciones de la colección general de Song de Musicfy
        System.out.println("Introduzca el título del álbum:");
        String titulo = sc.nextLine();
        Album album = c.vrfy_album(titulo);
        if (album == null) {
            System.out.println("El álbum introducido no existe.");
            return;
        }
        c.del_album(album);
        System.out.println("El album y sus datos se han eliminado exitosamente.");
    }

    private void mod_album() {
        // Cambia cualquiera de los atributos de un álbum, 
        //  excepto su título, intérpretes y la colección de canciones
        //ano, duracion, num_songs, tipo
        System.out.println("Introduzca el título del álbum a modificar");
        String album = sc.nextLine();
        Album alb = c.vrfy_album(album);
        if (alb == null) {
            System.out.println("El álbum introducido no existe.");
            return;
        }
        System.out.printf("Introduzca dato a cambiar: "
                + "%n 1 - Año"
                + "%n 2 - Duración"
                + "%n 3 - Nº Canciones%n");
        try {
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.printf("Introduzca año: ");
                    try {
                        String tmp = sc.nextLine();
                        int ano = Integer.parseInt(tmp);
                        if (ano <= 0) {
                            System.out.println("No se puede introducir un valor nulo o negativo.");
                            return;
                        }
                        c.mod_album(1, alb, ano);
                    } catch (Throwable ex) {
                        System.out.println("Dato introducido con mal formato.");
                    }
                    break;
                case 2:
                    System.out.printf("Introduzca duracion: ");
                    try {
                        String tmp = sc.nextLine();
                        float duracion = Float.parseFloat(tmp);
                        if (duracion <= 0) {
                            System.out.println("No se puede introducir un valor nulo o negativo.");
                            return;
                        }
                        c.mod_album(2, alb, duracion);
                    } catch (Throwable ex) {
                        System.out.println("Dato introducido con mal formato.");
                    }
                    break;
                case 3:
                    System.out.printf("Introduzca nº canciones: ");
                    try {
                        String tmp = sc.nextLine();
                        int num_songs = Integer.parseInt(tmp);
                        if (num_songs <= 0) {
                            System.out.println("No se puede introducir un valor nulo o negativo.");
                            return;
                        }
                        c.mod_album(3, alb, num_songs);
                    } catch (Throwable ex) {
                        System.out.println("Dato introducido con mal formato.");
                    }
                    break;
                default:
                    System.out.println("No has elegido ninguna opción.");
                    break;
            }
        } catch (Throwable ex) {
            System.out.println("Dato introducido con mal formato.");
        }
    }

    private void consulta() {
        // Se pedirá el título de un álbum por teclado y se mostrarán todos sus datos
        System.out.printf("\n¿Qué album desea consultar?: ");
        String albumConsultado = sc.nextLine();
        Album album = c.consulta(albumConsultado);
        if (album == null) {
            System.out.println("El archivo no ha sido encontrado.");
        } else {
            System.out.println(album.consultaAlbum(album));
        }
    }

    private void new_artist() {
        // Añadir un artista a la colección de artistas,
        //  se añadirán también los títulos de sus álbumes, 
        //  pero no se comprobará si existen, ni se darán de alta.
        System.out.printf("%nDATOS DEL ARTISTA%n-----------------%n%n");
        System.out.println("Nombre: ");
        String nombre = sc.nextLine();
        System.out.printf("Biografía: ");
        String biografia = sc.nextLine();
        System.out.printf("Instagram: ");
        String instagram = sc.nextLine();
        System.out.printf("Twitter: ");
        String twitter = sc.nextLine();
        System.out.printf("Facebook: ");
        String facebook = sc.nextLine();
        System.out.printf("Wikipedia: ");
        String wikipedia = sc.nextLine();
        List<String> albumes = new ArrayList<>();
        String flag = "";
        System.out.println("Ahora introduzca los albumes, introduzca 'q' para terminar la creación del artista.");
        while (!flag.equals("q")) {
            System.out.printf("Introduzca album: ");
            flag = sc.nextLine();
            albumes.add(flag);
        }
        albumes.remove(albumes.size() - 1);
        Artist artista = new Artist(nombre, biografia, instagram, twitter, facebook, wikipedia, albumes);
        c.new_artist(artista);
        System.out.println("Se ha creado correctamente el artista.");
    }

    private void del_artist() {
        // Borrar un artista de la colección de artistas,
        //  no se permitirá borrar si alguno de sus álbumes está dado de alta en la colección de álbumes)
        // Se indicará mediante un mensaje los álbumes que todavía tiene dados de alta
        System.out.println("Introduzca nombre del artista: ");
        String artista = sc.nextLine();
        int salida = c.del_artist(artista);
        if (salida == -1) {
            System.out.println("El artista tiene álbumes dados de alta.");
        } else if (salida == -2) {
            System.out.println("El artista no existe.");
        } else {
            System.out.println("El artista se ha eliminado exitosamente.");
        }
    }

    private void mod_artist() {
        // Cambiar cualquiera de los atributos del artista, excepto su nombre y la colección de álbumes
        //String biografia, String instagram, String twitter, String facebook, String wikipedia
        System.out.printf("Introduzca el nombre del artista: ");
        String artista = sc.nextLine();
        Artist art = c.vrfy_artist(artista);
        if (art == null) {
            System.out.println("El artista introducido no existe.");
            return;
        }
        int opcion;
        System.out.printf("Introduzca dato a cambiar: "
                + "%n 1 - Biografía"
                + "%n 2 - Instagram"
                + "%n 3 - Twitter"
                + "%n 4 - Facebook"
                + "%n 5 - Wikipedia%n");
        opcion = sc.nextInt();
        try {
            String dato = "";
            switch (opcion) {
                case 1:
                    System.out.println("Introduzca biografía: ");
                    dato = sc.nextLine();
                    break;
                case 2:
                    System.out.println("Introduzca Instagram: ");
                    dato = sc.nextLine();
                    break;
                case 3:
                    System.out.println("Introduzca Twitter: ");
                    dato = sc.nextLine();
                    break;
                case 4:
                    System.out.println("Introduzca Facebook: ");
                    dato = sc.nextLine();
                    break;
                case 5:
                    System.out.println("Introduzca link de Wikipedia: ");
                    dato = sc.nextLine();
                    break;
                default:
                    System.out.println("Ha introducido una opción errónea.");
                    break;
            }
            c.mod_artist(opcion, art, dato);
            System.out.println("Se ha cambiado el dato correctamente.");
        } catch (Throwable ex) {
            System.out.println("Ha introducido un dato erróneo.");
        }
    }

    private void albumes() {
        // Álbumes de un artista, cuyo nombre se pedirá por teclado.
        // Sacará un listado por pantalla, en forma de tabla ordenada por año,
        //  de los álbumes, con todos sus atributos, pero sin incluir sus canciones.
        System.out.println("¿De qué artista deseas ver sus albumes?: ");
        String artista = sc.nextLine();
        List<Album> albumes = c.albumes(artista);
        if (albumes == null) {
            System.out.printf("\nEl album no existe o está vacío.\n");
        } else {
            for (Album album : albumes) {
                System.out.println(album.consultaAlbumTabla(album));
            }
        }
    }

    private void new_playlist() {
        // Añadir una playlist a la colección de playlists),
        //  se pedirá el título y el número de canciones que va a tener.
        // Se cogerán aleatoriamente de la colección de canciones de Musicfy,
        //  el número de canciones indicadas y se añadirán a la playlist.
        System.out.printf("Ponle a tu playlist un título: ");
        String titulo = sc.nextLine();
        System.out.printf("¿Cuántas canciones tendrá?: ");
        try {
            String tmp = sc.nextLine();
            int num_songs = Integer.parseInt(tmp);
            if (num_songs <= 0) {
                System.out.println("No se puede introducir un valor nulo o negativo.");
                return;
            }
            c.new_playlist(titulo, num_songs);
        } catch (Throwable ex) {
            System.out.println("Dato con mal formato.");
        }
    }

    private void new_song() {
        // Pedirá el título de una canción, la buscará en la colección de canciones globales y la añadirá a la playlist.
        System.out.printf("Introduce el nombre de la playlist: ");
        String playlist = sc.nextLine();
        System.out.printf("Introduce el nombre de la canción a añadir: ");
        String titulo = sc.nextLine();
        int salida = c.new_song(titulo, playlist);
        if (salida == -1) {
            System.out.println("La canción a añadir no existe.");
        } else if (salida == -2) {
            System.out.println("La playlist introducida no existe.");
        } else {
            System.out.println("La canción ha sido añadida exitosamente.");
        }
    }

    private void del_song() {
        // Mostrará la lista de canciones de la playlist y pedirá el título de la que se quiera eliminar.
        // Sólo se eliminará de la playlist
        System.out.println("Introduzca el nombre de la playlist: ");
        String playlist = sc.nextLine();
        Playlist pla = c.vrfy_playlist(playlist);
        if (pla == null) {
            System.out.println("La playlist introducida no existe.");
            return;
        }
        System.out.println(pla.consultaPlaylist(pla));
        System.out.printf("%n%nIntroduzca canción a eliminar: ");
        String cancion = sc.nextLine();
        int salida = c.del_song(cancion, pla);
        if (salida == -1) {
            System.out.println("La canción introducida no es correcta.");
        } else {
            System.out.println("La canción ha sido eliminada exitosamente.");
        }
    }

    private void list() {
        // Listado general. Saca por pantalla la relación de canciones ordenada por el año y por el título respectivamente.
        List<Song> list_canciones = c.list();

        Comparator<Song> comp = new CompareSong();
        Collections.sort(list_canciones, comp);

        if (list_canciones.isEmpty()) {
            System.out.printf("\nNo se han cargado canciones.\n");
        } else {
            for (Song cancion : list_canciones) {
                System.out.printf("| %40s | %4d | %5.2f |\n", cancion.getTitulo(), cancion.getAno(), cancion.getDuracion());
            }
        }
    }

    private void salir() {
        c.save();

    }
}
