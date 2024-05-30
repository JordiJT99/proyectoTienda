/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tienda3;

import java.util.Scanner;

/**
 *
 * @author Mati
 */
public class Tienda3 {

    public static void main(String[] args) {
        ListaProductos listaProductos = new ListaProductos();
        boolean cond = true;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Pulse la opcion que quiere realizar: 1.LISTAR PRODUCTOS 2.LISTAR COMPRAS 3.EXTRAER GANANCIAS EN ARCHIVO CSV 4.AÑADIR PRODUCTO 5.SALIR");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1 -> {
                    listaProductos.leerProductos("productos.csv");
                }
                case 2 -> {
                    listaProductos.leerCompras("compras.csv");
                }
                case 3 -> {
                    listaProductos.calcularGanancias("resultados.csv");
                }
                case 4 -> {
                    Scanner scanner = new Scanner(System.in);
                    System.out.print("¿Desea agregar un nuevo producto? (s/n): ");
                    String respuesta = scanner.nextLine();

                    if (respuesta.equalsIgnoreCase("s")) {

                        listaProductos.agregarProductoConsola("productos.csv");
                    }
                }
                case 5->{
                   cond = false;
                }
                
            }
        } while (cond == true);
    }
}
/*
        // Leer los archivos CSV
        listaProductos.leerProductos("productos.csv");
        listaProductos.leerCompras("compras.csv");

        // Calcular y mostrar las ganancias por producto
        listaProductos.calcularGanancias("resultados.csv");
        Scanner scanner = new Scanner(System.in);
        System.out.print("¿Desea agregar un nuevo producto? (s/n): ");
        String respuesta = scanner.nextLine();

        if (respuesta.equalsIgnoreCase("s")) {

            listaProductos.agregarProductoConsola("productos.csv");
        }

        listaProductos.calcularGanancias("resultados.csv");
    }
}*/
