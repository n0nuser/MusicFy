package musicfy;

import View.View;

/**
 * @author Pablo Jesús González Rubio
 * @serial 70894492M
 */
public class MusicFy {

    public static void main(String[] args) {
        View v = new View();

        v.vrfy_files();

        v.runMenu("\n\n\nMUSICFY\n"
                + "=======\n"
                + "\n1.- Generación Aleatoria\n" //Hecho
                + "\nARCHIVOS\n"
                + "--------"
                + "\n  2.- Exportar Artistas\n" //Hecho
                + "\n  3.- Exportar Álbumes\n" //Hecho
                + "\nÁLBUM\n"
                + "-----"
                + "\n  4.- Altas\n" //Hecho
                + "\n  5.- Bajas\n" //Hecho
                + "\n  6.- Modificaciones\n" //Hecho
                + "\n  7.- Consulta\n" //Hecho
                + "\nARTISTA\n"
                + "-------"
                + "\n  8.- Altas\n" //Hecho
                + "\n  9.- Bajas\n" //Hecho
                + "\n  10.- Modificaciones\n" //Hecho
                + "\n  11.- Álbumes\n" //Hecho
                + "\nPLAYLIST\n"
                + "--------"
                + "\n  12.- Altas\n" //Hecho
                + "\n  13.- Añadir Canción\n" //Hecho
                + "\n  14.- Eliminar Canción\n" //Hecho pero no borra
                + "\nCANCIONES\n"
                + "---------"
                + "\n  15.- Listado\n" //Hecho
                + "\nq.- Salir\n\n"); //Hecho
    }

}
