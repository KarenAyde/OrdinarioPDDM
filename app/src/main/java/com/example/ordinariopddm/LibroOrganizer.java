package com.example.ordinariopddm;

import java.util.ArrayList;
import java.util.List;

public class LibroOrganizer {
    private List<Libro> listaLibros;

    public LibroOrganizer() {
        //Se crea la lista los libros y sus respectivos datos
        listaLibros = new ArrayList<>();

        // Agregar libros a la lista
        listaLibros.add(new Libro("6975207754473", "Cien años de soledad", 448, "cienAniosDeSoledad.gif", "Sudamericana", "Gabriel García Márquez"));
        listaLibros.add(new Libro("9788408132202", "El amor en los tiempos del cólera", 368, "elAmorEnTiemposDeColera.jpg", "Diana", "Gabriel García Márquez"));
        listaLibros.add(new Libro("9786073100425", "Rayuela", 733, "Rayuela.png", "Alfaguara", "Julio Cortázar"));
        listaLibros.add(new Libro("9788420400312", "1984", 368, "portada2.jpg", "Alianza Editorial", "George Orwell"));
        listaLibros.add(new Libro("9788433920143", "Don Quijote de la Mancha", 1008, "DonQuijote.jpg", "Editorial 1", "Miguel de Cervantes Saavedra"));
        listaLibros.add(new Libro("9786073162676", "Los detectives salvajes", 832, "LosDetectivesSalvajes.jpg", "Anagrama", "Roberto Bolaño"));
        listaLibros.add(new Libro("9788497592208", "El nombre de la rosa", 592, "el-nombre-de-la-rosa-umberto-eco.jpg", "Debolsillo", "Umberto Eco"));
        listaLibros.add(new Libro("9780307744593", "1Q84", 1157, "1Q84.jpg", "Vintage Books", "Haruki Murakami"));
        listaLibros.add(new Libro("9788408149323", "Fahrenheit 451", 224, "ray-bradbury-fahrenheit-451.jpg", "Debolsillo", "Ray Bradbury"));
        listaLibros.add(new Libro("9788408076859", "El Hobbit", 320, "elHobbit.jpg", "Minotauro", "J.R.R. Tolkien"));
    }

    //Método que permite buscar el libro y mostrar si se encuentra o no, retornando si fue encontrado o no
    public Libro buscarLibroPorISBN(String isbn) {
        for (Libro libro : listaLibros) {
            if (libro.getIsbn().equals(isbn)) {
                return libro; // El libro ha sido encontrado
            }
        }
        return null; // El libro no ha sido encontrado
    }
}

