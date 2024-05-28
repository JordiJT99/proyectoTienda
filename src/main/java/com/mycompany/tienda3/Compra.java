/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tienda3;

/**
 *
 * @author Mati
 */
public class Compra {
private String nombreProducto;
    private String nombreCliente;
    private int cantidad;

    public Compra( String nombreCliente,String nombreProducto, int cantidad) {
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.nombreCliente= nombreCliente;
    }
   


    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }
    
    public String getNombreCliente(){
        return nombreCliente;
    }
    @Override
    public String toString() {
        return "Compra{" + "nombreProducto='" + nombreProducto + '\'' + ", cantidad=" + cantidad + '}';
    }
}


