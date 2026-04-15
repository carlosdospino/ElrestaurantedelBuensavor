/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restauranteelbuensabor;

/**
 * Responsable de mostrar información y generar facturas del restaurante
 * 
 * @author alfre
 */
public class FacturaImpresora {
    
    private static final String SEPARADOR = "========================================";
    private static final double IVA = 0.19;
    private static final double PROPINA = 0.10;
    private static final double DESCUENTO = 0.05;
    private static final int UMBRAL_DESCUENTO = 3;
    private static final double UMBRAL_PROPINA = 50000;

    public static void mostrarCarta() {
        imprimirEncabezadoCarta();
        
        for (int indiceProducto = 0; indiceProducto < DatosRestaurante.nombresProductos.length; indiceProducto++) {
            System.out.printf("%d. %-22s $%,.0f%n", 
                (indiceProducto + 1), 
                DatosRestaurante.nombresProductos[indiceProducto], 
                DatosRestaurante.preciosProductos[indiceProducto]);
        }
        
        System.out.println(SEPARADOR);
    }

    public static void mostrarResumenPedido() {
        double subtotal = calcularSubtotal();
        
        System.out.println("--- PEDIDO ACTUAL ---");
        
        for (int indiceProducto = 0; indiceProducto < DatosRestaurante.nombresProductos.length; indiceProducto++) {
            if (DatosRestaurante.cantidadesPedido[indiceProducto] > 0) {
                System.out.printf("%-20s x%-6d $%,.0f%n", 
                    DatosRestaurante.nombresProductos[indiceProducto], 
                    DatosRestaurante.cantidadesPedido[indiceProducto], 
                    (DatosRestaurante.preciosProductos[indiceProducto] * DatosRestaurante.cantidadesPedido[indiceProducto]));
            }
        }
        
        System.out.println("--------------------");
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotal);
    }

    public static void imprimirFacturaCompleta() {
        double subtotalConDescuento = 0;
        double impuestoIVA = 0;
        double totalConIVA = 0;
        double propinaCalculada = 0;
        double totalFinal = 0;
        int cantidadProductosDistintos = 0;
        
        // Calcular subtotal y contar productos
        double subtotal = calcularSubtotal();
        cantidadProductosDistintos = contarProductosEnPedido();
        
        // Aplicar descuento si corresponde
        if (cantidadProductosDistintos > UMBRAL_DESCUENTO) {
            subtotalConDescuento = subtotal - (subtotal * DESCUENTO);
        } else {
            subtotalConDescuento = subtotal;
        }
        
        // Calcular IVA y propina
        if (subtotalConDescuento > UMBRAL_PROPINA) {
            impuestoIVA = subtotalConDescuento * IVA;
            totalConIVA = subtotalConDescuento + impuestoIVA;
            propinaCalculada = totalConIVA * PROPINA;
            totalFinal = totalConIVA + propinaCalculada;
        } else {
            impuestoIVA = subtotalConDescuento * IVA;
            totalConIVA = subtotalConDescuento + impuestoIVA;
            propinaCalculada = 0;
            totalFinal = totalConIVA;
        }
        
        // Imprimir factura
        imprimirEncabezadoFactura();
        
        System.out.printf("FACTURA No. %03d%n", DatosRestaurante.numeroFactura);
        System.out.println("----------------------------------------");
        
        imprimirItemsDelPedido();
        
        System.out.println("----------------------------------------");
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotalConDescuento);
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):", impuestoIVA);
        
        if (propinaCalculada > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%):", propinaCalculada);
        }
        
        System.out.println("----------------------------------------");
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", totalFinal);
        System.out.println(SEPARADOR);
        System.out.println("Gracias por su visita!");
        System.out.println("El Buen Sabor - Valledupar");
        System.out.println(SEPARADOR);
        
        // ACTUALIZAR ESTADO POST-FACTURA (debería estar en otra clase)
        DatosRestaurante.numeroFactura++;
        DatosRestaurante.estadoMesa = "libre";
        DatosRestaurante.totalFactura = totalFinal;
    }

    public static void imprimirFacturaResumida() {
        double subtotal = calcularSubtotal();
        double subtotalConDescuento = 0;
        double impuestoIVA = 0;
        double totalConIVA = 0;
        double propinaCalculada = 0;
        double totalFinal = 0;
        int cantidadProductosDistintos = contarProductosEnPedido();
        
        // Aplicar descuento si corresponde
        if (cantidadProductosDistintos > UMBRAL_DESCUENTO) {
            subtotalConDescuento = subtotal - (subtotal * DESCUENTO);
        } else {
            subtotalConDescuento = subtotal;
        }
        
        // Calcular IVA y propina
        if (subtotalConDescuento > UMBRAL_PROPINA) {
            impuestoIVA = subtotalConDescuento * IVA;
            totalConIVA = subtotalConDescuento + impuestoIVA;
            propinaCalculada = totalConIVA * PROPINA;
            totalFinal = totalConIVA + propinaCalculada;
        } else {
            impuestoIVA = subtotalConDescuento * IVA;
            totalConIVA = subtotalConDescuento + impuestoIVA;
            propinaCalculada = 0;
            totalFinal = totalConIVA;
        }
        
        imprimirEncabezadoFactura();
        
        System.out.printf("FACTURA No. %03d (RESUMEN)%n", DatosRestaurante.numeroFactura);
        System.out.println("----------------------------------------");
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotalConDescuento);
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):", impuestoIVA);
        
        if (propinaCalculada > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%):", propinaCalculada);
        }
        
        System.out.println("----------------------------------------");
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", totalFinal);
        System.out.println(SEPARADOR);
    }
    
    // ========== MÉTODOS PRIVADOS ==========
    
    private static void imprimirEncabezadoCarta() {
        System.out.println(SEPARADOR);
        System.out.println("    RESTAURANTE EL BUEN SABOR");
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(SEPARADOR);
    }
    
    private static void imprimirEncabezadoFactura() {
        System.out.println(SEPARADOR);
        System.out.println("    RESTAURANTE EL BUEN SABOR");
        System.out.println("    Calle 15 #8-32, Valledupar");
        System.out.println("    NIT: 900.123.456-7");
        System.out.println(SEPARADOR);
    }
    
    private static void imprimirItemsDelPedido() {
        for (int indiceProducto = 0; indiceProducto < DatosRestaurante.nombresProductos.length; indiceProducto++) {
            if (DatosRestaurante.cantidadesPedido[indiceProducto] > 0) {
                System.out.printf("%-20s x%-6d $%,.0f%n", 
                    DatosRestaurante.nombresProductos[indiceProducto], 
                    DatosRestaurante.cantidadesPedido[indiceProducto], 
                    (DatosRestaurante.preciosProductos[indiceProducto] * DatosRestaurante.cantidadesPedido[indiceProducto]));
            }
        }
    }
    
    private static double calcularSubtotal() {
        double subtotal = 0;
        for (int indiceProducto = 0; indiceProducto < DatosRestaurante.nombresProductos.length; indiceProducto++) {
            subtotal += DatosRestaurante.preciosProductos[indiceProducto] * DatosRestaurante.cantidadesPedido[indiceProducto];
        }
        return subtotal;
    }
    
    private static int contarProductosEnPedido() {
        int cantidadProductos = 0;
        for (int cantidad : DatosRestaurante.cantidadesPedido) {
            if (cantidad > 0) {
                cantidadProductos++;
            }
        }
        return cantidadProductos;
    }
}