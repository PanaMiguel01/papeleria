package com.mycompany.papeleria;
import java.util.Scanner;

public class Papeleria {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int tam = 0;
        Producto[] inventario = new Producto[tam];
        int a = 0;
        int codigo;
        String nombre;
        int cantidad;
        double precio;
        boolean menuOpcion = false;

        while (a < 5) {
            do {

                System.out.println("\n ----Menu Inventario----");
                System.out.println("\n 1. Agregar");
                System.out.println("\n 2. Vender");
                System.out.println("\n 3. Cambiar informacion");
                System.out.println("\n 4. Ver inventario");
                System.out.println("\n 5. Salir");
                a = scanner.nextInt();

                if (a <= 0 || a > 5) {
                    menuOpcion = true;
                    System.out.println("Ingrese un numero valido (1-5)");
                } else {

                    switch (a) {
                        case 1:
                            System.out.println("Cantidad de productos a ingresar");
                            int ingresos = scanner.nextInt();
                            int newtam = tam + ingresos;

                            Producto[] productos = new Producto[newtam];
                            System.arraycopy(inventario, 0, productos, 0, tam);

                            for (int i = tam; i < newtam; i++) {
                                Producto x = new Producto();
                                boolean codigoRepetido;

                                System.out.println("\n Ingrese los datos del producto");
                                do {
                                    System.out.println("Código (numerico):");
                                    codigo = scanner.nextInt();

                                    codigoRepetido = false;

                                    // Verificar si el código ya existe en el inventario
                                    for (Producto productoExistente : inventario) {
                                        if (productoExistente != null && productoExistente.getCodigo() == codigo) {
                                            System.out.println("El código ya existe en el inventario Ingrese un código diferente.");
                                            codigoRepetido = true;
                                            break;
                                        }
                                    }
                                } while (codigoRepetido);

                                x.setCodigo(codigo);
                                productos[i] = x;
                                inventario = productos;
                                System.out.println("\n Nombre:");
                                nombre = scanner.next();
                                System.out.println("\n Cantidad:");
                                cantidad = scanner.nextInt();
                                System.out.println("\n Precio unitario:");
                                precio = scanner.nextDouble();

                                x = new Producto(codigo, nombre, cantidad, precio);

                                productos[i] = x;
                            }
                            inventario = productos;
                            tam = newtam;
                            break;
                        case 2:
                            int opcion;
                            int opcion2;
                            boolean codigoVerificar;
                            System.out.println("Escriba el codigo del producto que desea vender");
                            for (Producto producto : inventario) {
                                System.out.println("Código: " + producto.getCodigo());
                                System.out.println("Nombre: " + producto.getNombre());
                                System.out.println("Cantidad: " + producto.getStock());
                                System.out.println("Precio unitario: " + producto.getPrecio());
                                System.out.println("--------------------------------");
                            }
                            opcion = scanner.nextInt();
                            codigoVerificar = false;

                            // Verificar si el código ya existe en el inventario
                            for (Producto productoExistente : inventario) {
                                if (productoExistente != null && productoExistente.getCodigo() == opcion) {
                                    codigoVerificar = true;

                                    System.out.println("Producto " + productoExistente.getNombre() + " encontrado");
                                    System.out.println("Cuantos elementos desea vender.");
                                    opcion2 = scanner.nextInt();

                                    if (opcion2 > 0 && opcion2 <= productoExistente.getStock()) {
                                        productoExistente.setStock(productoExistente.getStock() - opcion2);
                                        System.out.println(opcion2 + " productos fueron vendidos con exito");
                                    } else {
                                        System.out.println("Stock  insuficiente");
                                    }
                                    break;
                                }
                            }

                            if (!codigoVerificar) {
                                System.out.println("El codigo no existe");
                            }
                            break;
                        case 3:
                            boolean codigoVerificado;

                            System.out.println("\n Ingrese el codigo del producto");
                            for (Producto producto : inventario) {
                                System.out.println("Código: " + producto.getCodigo());
                                System.out.println("Nombre: " + producto.getNombre());
                                System.out.println("Cantidad: " + producto.getStock());
                                System.out.println("Precio unitario: " + producto.getPrecio());
                                System.out.println("--------------------------------");
                            }
                            do {
                                codigo = scanner.nextInt();
                                codigoVerificado = false;

                                // Verificar si el código ya existe en el inventario
                                for (Producto productoExistente : inventario) {
                                    if (productoExistente != null && productoExistente.getCodigo() == codigo) {
                                        System.out.println("El código existe en el inventario. Que desea cambiar.");
                                        codigoVerificado = true;
                                        int aa = 0;
                                        while (aa != 4) {
                                            System.out.println("1. Nombre");
                                            System.out.println("2. Cantidad");
                                            System.out.println("3. Precio unitario");
                                            System.out.println("4. Regresar");

                                            aa = scanner.nextInt();

                                            switch (aa) {
                                                case 1:
                                                    System.out.println("Ingrese el nuevo nombre");
                                                    nombre = scanner.next();
                                                    productoExistente.setNombre(nombre);
                                                    break;
                                                case 2:
                                                    int sum = productoExistente.getStock();
                                                    System.out.println("Ingrese la cantidad que desea agregar o eliminar");
                                                    cantidad = scanner.nextInt();

                                                    if (productoExistente.getStock() + (cantidad) >= 0) {
                                                        productoExistente.setStock(sum + (cantidad));

                                                        System.out.println("El stock ha sido cambiado con exito");

                                                    } else {
                                                        System.out.println("El stock del producto no puede ser menor a 0");
                                                    }

                                                    break;
                                                case 3:
                                                    System.out.println("Ingrese el nuevo precio");
                                                    precio = scanner.nextDouble();
                                                    productoExistente.setPrecio(precio);
                                                    break;
                                                case 4:
                                                    break;
                                                default:
                                                    System.out.println("Opcion invalida");
                                                    break;
                                            }
                                        }
                                    }
                                }
                                if (!codigoVerificado) {
                                    System.out.println("El codigo no existe, pruebe de nuevo");
                                }

                            } while (!codigoVerificado);
                            break;
                        case 4:
                            System.out.println("Inventario:");
                            for (Producto producto : inventario) {
                                System.out.println("Código: " + producto.getCodigo());
                                System.out.println("Nombre: " + producto.getNombre());
                                System.out.println("Cantidad: " + producto.getStock());
                                System.out.println("Precio unitario: " + producto.getPrecio());
                                System.out.println("--------------------------------");
                            }
                            break;

                    }
                }

            } while (menuOpcion);
        }
    }
}