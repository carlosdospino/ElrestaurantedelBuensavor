/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restauranteelbuensabor;

/**
 * Almacena los datos del restaurante: carta, pedidos activos y estado de
 * facturación
 * 
 * @author alfre
 */
public class DatosRestaurante {
    // ========== CARTA DEL RESTAURANTE ==========
    public static String[] nombresProductos = {
            "Bandeja Paisa", "Sancocho de Gallina", "Arepa con Huevo", "Jugo Natural", "Gaseosa",
            "Cerveza Poker", "Agua Panela", "Arroz con Pollo"
    };

    public static double[] preciosProductos = {
            32000, 28000, 8000, 7000, 4500, 6000, 3500, 25000
    };

    // ========== PEDIDO ACTIVO ==========
    public static int[] cantidadesPedido = {
            0, 0, 0, 0, 0, 0, 0, 0
    };

    // ========== ESTADO DE MESA ==========
    public static int numeroMesaActual = 0;
    public static String estadoMesa = ""; // "libre", "ocupada", "pagando"

    // ========== FACTURACIÓN ==========
    public static double totalFactura = 0;
    public static int numeroFactura = 1;
    public static String nombreRestaurante = "El Buen Sabor";

    // ========== TEMPORALES ==========
    public static String textoTemporal = "";
}