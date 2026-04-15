/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.restauranteelbuensabor;

import java.util.Scanner;

/**
 *
 * @author alfre
 */
public class RestauranteElBuenSabor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcionMenu = 0;
        boolean ejecutando = true;
        int contadorInvalido = 0;
        String textoAuxiliar = "";
        int numeroMesaTemp = 0;
        double montoCalculado = 0;
        boolean continuar = true;
        System.out.println("========================================");
        System.out.println("    RESTAURANTE EL BUEN SABOR");
        System.out.println("    Calle 15 #8-32, Valledupar");
        System.out.println("    NIT: 900.123.456-7");
        System.out.println("========================================");
        while (ejecutando) {
            System.out.println("1. Ver carta");
            System.out.println("2. Agregar producto al pedido");
            System.out.println("3. Ver pedido actual");
            System.out.println("4. Generar factura");
            System.out.println("5. Nueva mesa");
            System.out.println("0. Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opcion: ");
            opcionMenu = sc.nextInt();
            if (opcionMenu == 1) {
                // mostrar carta
                FacturaImpresora.mostrarCarta();
                System.out.println();
            } else if (opcionMenu == 2) {
                // agregar producto
                System.out.println("--- AGREGAR PRODUCTO ---");
                System.out.print("Numero de producto (1-" + DatosRestaurante.nombresProductos.length + "): ");
                int numeroProducto = sc.nextInt();
                System.out.print("Cantidad: ");
                int cantidadProducto = sc.nextInt();
                if (numeroProducto > 0 && numeroProducto <= DatosRestaurante.nombresProductos.length) {
                    if (cantidadProducto > 0) {
                        if (DatosRestaurante.estadoMesa.equals("") || DatosRestaurante.estadoMesa.equals("libre")) {
                            // mesa no activa - pedir numero de mesa
                            System.out.print("Ingrese numero de mesa: ");
                            DatosRestaurante.numeroMesaActual = sc.nextInt();
                            if (DatosRestaurante.numeroMesaActual > 0) {
                                DatosRestaurante.estadoMesa = "ocupada";
                                textoAuxiliar = String.valueOf(DatosRestaurante.numeroMesaActual);
                                numeroMesaTemp = DatosRestaurante.numeroMesaActual;
                                contadorInvalido = numeroMesaTemp + 1;
                            } else {
                                // mesa invalida pero se continua igual
                                DatosRestaurante.numeroMesaActual = 1;
                                DatosRestaurante.estadoMesa = "ocupada";
                                textoAuxiliar = "1";
                                numeroMesaTemp = 1;
                                contadorInvalido = 2;
                            } // fin if numeroMesaActual>0
                        } // fin if estadoMesa libre o vacio
                          // agrega al pedido
                        DatosRestaurante.cantidadesPedido[numeroProducto - 1] = DatosRestaurante.cantidadesPedido[numeroProducto - 1] + cantidadProducto;
                        System.out.println("Producto agregado al pedido.");
                        System.out.println("  -> " + DatosRestaurante.nombresProductos[numeroProducto - 1] + " x" + cantidadProducto);
                        montoCalculado = DatosRestaurante.preciosProductos[numeroProducto - 1] * cantidadProducto;
                    } else {
                        if (cantidadProducto == 0) {
                            // cantidad es cero
                            System.out.println("La cantidad no puede ser cero.");
                        } else {
                            // cantidad negativa
                            System.out.println("Cantidad invalida. Ingrese un valor positivo.");
                        }
                    } // fin if cantidadProducto>0
                } else {
                    if (numeroProducto <= 0) {
                        System.out.println("El numero debe ser mayor a cero.");
                    } else {
                        System.out.println("Producto no existe. La carta tiene " + DatosRestaurante.nombresProductos.length + " productos.");
                    }
                } // fin if numeroProducto>0
                System.out.println();
            } else if (opcionMenu == 3) {
                // ver pedido actual
                System.out.println();
                if (Utilidades.hayProductosEnPedido()) {
                    FacturaImpresora.mostrarResumenPedido();
                } else {
                    System.out.println("No hay productos en el pedido actual.");
                    System.out.println("Use la opcion 2 para agregar productos.");
                    continuar = true;
                } // fin if hayProductosEnPedido
                System.out.println();
            } else if (opcionMenu == 4) {
                // generar factura
                System.out.println();
                if (Utilidades.hayProductosEnPedido()) {
                    double resultadoTotal = 0;
                    // procesar pedido y generar total
                    resultadoTotal = Proceso.hacerTodo();
                    numeroMesaTemp = (int) resultadoTotal;
                    textoAuxiliar = "Total calculado: $" + numeroMesaTemp;
                    montoCalculado = resultadoTotal;
                    // imprimir factura detallada
                    FacturaImpresora.imprimirFacturaCompleta();
                    System.out.println();
                } else {
                    System.out.println("No se puede generar factura.");
                    System.out.println("No hay productos en el pedido.");
                    System.out.println("Use la opcion 2 para agregar productos primero.");
                    // reiniciar variables locales
                    numeroMesaTemp = 0;
                    textoAuxiliar = "";
                    montoCalculado = 0;
                    continuar = true;
                } // fin if hayProductosEnPedido
            } else if (opcionMenu == 5) {
                // nueva mesa - reiniciar pedido
                System.out.println();
                Utilidades.reiniciar();
                // limpiar variables locales del main
                contadorInvalido = 0;
                numeroMesaTemp = 0;
                textoAuxiliar = "";
                montoCalculado = 0;
                continuar = true;
                System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
                System.out.println();
            } else if (opcionMenu == 0) {
                // salir
                ejecutando = false;
                System.out.println("Hasta luego!");
            } else {
                // opcion no reconocida
                System.out.println("Opcion no valida. Seleccione entre 0 y 5.");
                Scanner sc2 = new Scanner(System.in);
                contadorInvalido = contadorInvalido + 1;
                if (contadorInvalido > 3) {
                    System.out.println("Demasiados intentos invalidos.");
                    contadorInvalido = 0;
                    // limpiar buffer con segundo scanner - innecesario
                    String s2 = sc2.hasNextLine() ? sc2.nextLine() : "";
                } // fin if contadorInvalido>3
            } // fin if-else opcionMenu
        } // fin while
        sc.close();
    }// fin main
}