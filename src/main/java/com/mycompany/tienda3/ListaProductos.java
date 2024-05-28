/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tienda3;

/**
 *
 * @author Mati
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListaProductos {
    private List<Producto> productos;
    private List<Compra> compras;

    public ListaProductos() {
        productos = new ArrayList<>();
        compras = new ArrayList<>();
    }

    public void leerProductos(String archivoProductos) {
   File fichero = new File(archivoProductos);
        if (!fichero.exists()) {
            System.out.println("El archivo " + archivoProductos + " no existe.");
            return;
        }

        try (BufferedReader buffer = new BufferedReader(new FileReader(fichero))) {
            String linea;
            buffer.readLine(); // Saltar la cabecera
            while ((linea = buffer.readLine()) != null) {
                System.out.println("Leyendo linea de productos: " + linea); // Depuración
                String[] campos = linea.split(",");
                if (campos.length > 1) { // Verificar que la línea tiene al menos dos columnas
                    String nombre = campos[0];
                    double precio = Double.parseDouble(campos[1]);
                    productos.add(new Producto(nombre, precio));
                } else {
                    System.out.println("Error en formato de línea de productos: " + linea);
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR al leer productos: " + e.getMessage());
            e.printStackTrace();
        }
    }



    public void leerCompras(String archivoCompras) {
    File fichero = new File(archivoCompras);
    if (!fichero.exists()) {
        System.out.println("El archivo " + archivoCompras + " no existe.");
        return;
    }

    try (BufferedReader buffer = new BufferedReader(new FileReader(fichero))) {
        String linea;
        buffer.readLine(); // Saltar la cabecera
        while ((linea = buffer.readLine()) != null) {
            System.out.println("Leyendo línea de compras: " + linea); // Depuración
            String[] campos = linea.split(",");
            if (campos.length == 3) { // Verificar que la línea tiene exactamente tres columnas
                String nombreCliente = campos[0].trim();
                String nombreProducto = campos[1].trim();
                int cantidad = Integer.parseInt(campos[2].trim());
                compras.add(new Compra(nombreCliente, nombreProducto, cantidad));
            } else {
                System.out.println("Error en formato de línea de compras: " + linea);
            }
        }
    } catch (Exception e) {
        System.out.println("ERROR al leer compras: " + e.getMessage());
        e.printStackTrace();
    }
}

   public void calcularGanancias() {
    if (productos.isEmpty()) {
        System.out.println("No hay productos para calcular ganancias.");
        return;
    }

    if (compras.isEmpty()) {
        System.out.println("No hay compras para calcular ganancias.");
        return;
    }

    // Crear mapas para almacenar las ganancias, la cantidad vendida y las cantidades compradas por cliente por producto
    Map<String, Double> gananciasPorProducto = new HashMap<>();
    Map<String, Integer> cantidadVendidaPorProducto = new HashMap<>();
    Map<String, Map<String, Integer>> cantidadesPorClientePorProducto = new HashMap<>();

    // Inicializar mapas
    for (Producto producto : productos) {
        String nombreProducto = producto.getNombre();
        gananciasPorProducto.put(nombreProducto, 0.0);
        cantidadVendidaPorProducto.put(nombreProducto, 0);
        cantidadesPorClientePorProducto.put(nombreProducto, new HashMap<>());
    }

    // Calcular las ganancias, la cantidad vendida y las cantidades compradas por cliente basándose en las compras
    for (Compra compra : compras) {
        String nombreCliente = compra.getNombreCliente();
        String nombreProducto = compra.getNombreProducto();
        int cantidad = compra.getCantidad();
        Producto producto = obtenerProductoPorNombre(nombreProducto);

        if (producto != null) {
            double totalGanancia = cantidad * producto.getPrecio();
            gananciasPorProducto.put(nombreProducto, gananciasPorProducto.get(nombreProducto) + totalGanancia);
            int cantidadActualVendida = cantidadVendidaPorProducto.get(nombreProducto);
            cantidadVendidaPorProducto.put(nombreProducto, cantidadActualVendida + cantidad);

            // Obtener el mapa de cantidades por cliente para este producto
            Map<String, Integer> cantidadesPorCliente = cantidadesPorClientePorProducto.get(nombreProducto);
            // Incrementar la cantidad comprada por este cliente para este producto
            int cantidadActualCliente = cantidadesPorCliente.getOrDefault(nombreCliente, 0);
            cantidadesPorCliente.put(nombreCliente, cantidadActualCliente + cantidad);
        }
    }

    // Imprimir las ganancias, la cantidad vendida y las cantidades compradas por cliente por producto
    for (Map.Entry<String, Double> entry : gananciasPorProducto.entrySet()) {
        String nombreProducto = entry.getKey();
        double gananciaTotal = entry.getValue();
        int cantidadVendida = cantidadVendidaPorProducto.get(nombreProducto);
        Map<String, Integer> cantidadesPorCliente = cantidadesPorClientePorProducto.get(nombreProducto);

        System.out.println("Producto: " + nombreProducto + ", Ganancia Total: " + gananciaTotal + ", Cantidad Vendida: " + cantidadVendida);

        // Imprimir las cantidades compradas por cliente
        System.out.println("Cantidades compradas por cliente:");
        for (Map.Entry<String, Integer> clienteEntry : cantidadesPorCliente.entrySet()) {
            String nombreCliente = clienteEntry.getKey();
            int cantidadCompradaPorCliente = clienteEntry.getValue();
            System.out.println("- Cliente: " + nombreCliente + ", Cantidad Comprada: " + cantidadCompradaPorCliente);
        }
    }
}
    private Producto obtenerProductoPorNombre(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().equals(nombre)) {
                return producto;
            }
        }
        return null;
    }
}