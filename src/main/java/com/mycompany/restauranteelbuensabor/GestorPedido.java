/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restauranteelbuensabor;

/**
 * Responsable de validar y gestionar el estado del pedido
 * 
 * @author alfre
 */
public class GestorPedido {
    
    /**
     * Verifica si hay productos en el pedido actual
     * @return true si hay al menos un producto, false en caso contrario
     */
    public static boolean hayProductosEnPedido() {
        int cantidadProductos = 0;
        int indice = 0;
        
        while (indice < DatosRestaurante.cantidadesPedido.length) {
            if (DatosRestaurante.cantidadesPedido[indice] > 0) {
                cantidadProductos++;
            }
            indice++;
        }
        
        return cantidadProductos > 0;
    }
    
    /**
     * Reinicia el pedido y la mesa para un nuevo cliente
     */
    public static void reiniciar() {
        int indice = 0;
        while (indice < DatosRestaurante.cantidadesPedido.length) {
            DatosRestaurante.cantidadesPedido[indice] = 0;
            indice++;
        }
        DatosRestaurante.totalFactura = 0;
        DatosRestaurante.estadoMesa = "libre";
        DatosRestaurante.numeroMesaActual = 0;
        DatosRestaurante.textoTemporal = "";
    }
    
    /**
     * Limpia los datos temporales después de una facturación
     */
    public static void limpiarDatosTemporales() {
        DatosRestaurante.totalFactura = 0;
        DatosRestaurante.textoTemporal = "";
    }
}