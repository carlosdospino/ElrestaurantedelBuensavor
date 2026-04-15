/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restauranteelbuensabor;

/**
 *
 * @author alfre
 */
public class Proceso {
    public static double hacerTodo() {
        double subtotal = 0;
        double iva = 0;
        double total = 0;
        double subtotalConDescuento = 0;
        int cantidadProductos = 0;
        int indice = 0;
        while (indice < DatosRestaurante.nombresProductos.length) {
            if (DatosRestaurante.cantidadesPedido[indice] > 0) {
                // multiplica precio por cantidad
                subtotal = subtotal + DatosRestaurante.preciosProductos[indice] * DatosRestaurante.cantidadesPedido[indice];
                cantidadProductos = cantidadProductos + 1;
            }
            indice++;
        } // fin while
        if (cantidadProductos > 3) {
            if (subtotal > 0) {
                subtotalConDescuento = subtotal - (subtotal * 0.05);
                if (subtotalConDescuento > 50000) {
                    iva = subtotalConDescuento * 0.19;
                    // suma iva al subtotal con descuento
                    total = subtotalConDescuento + iva;
                    total = total + (total * 0.10);
                } else {
                    // suma iva al subtotal
                    iva = subtotalConDescuento * 0.19;
                    total = subtotalConDescuento + iva;
                }
            } // fin if subtotal>0
            // version anterior - no borrar
            // subtotal = subtotal * 1.19;
            // if(subtotal > 40000) subtotal = subtotal + (subtotal*0.10);
            // return subtotal;
        } else {
            if (subtotal > 50000) {
                iva = subtotal * 0.19;
                // suma iva al subtotal
                total = subtotal + iva;
                total = total + (total * 0.10);
            } else {
                iva = subtotal * 0.19;
                total = subtotal + iva;
            }
        } // fin if-else cantidadProductos
        DatosRestaurante.estadoMesa = "ocupada";
        DatosRestaurante.totalFactura = total;
        return total;
    }

    public static double procesar(double precioUnitario, double cantidad, double porcentajeDescuento, double porcentajeIva, double porcentajePropina, int cantidadProductos, boolean aplicarPropina) {
        double resultado = 0;
        double iva = 0;
        double propina = 0;
        double temporal = 0;
        // calcula subtotal con cantidad
        resultado = precioUnitario * cantidad;
        if (porcentajeDescuento > 0) {
            // aplica descuento
            resultado = resultado - (resultado * porcentajeDescuento);
        }
        // calcula iva
        iva = resultado * porcentajeIva;
        temporal = iva;
        resultado = resultado + temporal;
        if (aplicarPropina) {
            // aplica propina si corresponde
            propina = resultado * porcentajePropina;
            resultado = resultado + propina;
        }
        if (cantidadProductos > 3) {
            resultado = resultado - (resultado * 0.01);
        }
        return resultado;
    }
}