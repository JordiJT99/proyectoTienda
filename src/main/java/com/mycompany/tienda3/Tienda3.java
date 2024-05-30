/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tienda3;

/**
 *
 * @author Mati
 */
public class Tienda3 {
    
    public static void main(String[] args) {
        ListaProductos listaProductos = new ListaProductos();

        // Leer los archivos CSV
        listaProductos.leerProductos("productos.csv");
        listaProductos.leerCompras("compras.csv");

        // Calcular y mostrar las ganancias por producto
        listaProductos.calcularGanancias("resultados.csv");
    }
}


