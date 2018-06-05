/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class LaboratorioII {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Date fechaActual = new Date();
        Scanner input = new Scanner(System.in);
        
        Admin administrador = new Admin("wiux", "AB123");
        
        Vendedor vendedor1 = new Vendedor("Juan", "XYZ");
        
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        vendedores.add(vendedor1);
        vendedores.add(new Vendedor("Alex", "ZXY"));
        vendedores.add(new Vendedor("Gabo", "123AB"));
        
        ArrayList<CajaRegistradora> CajasRegistradoras = new ArrayList<>();
        
        CajasRegistradoras.add(new CajaRegistradora(1, 15000));
        CajasRegistradoras.add(new CajaRegistradora(2, 20000));
        CajasRegistradoras.add(new CajaRegistradora(3, 30000));
        
        
        Producto producto1 = new Producto("Manzana", "0001", 10, 1000);
        Producto producto2 = new Producto("Pera", "0002", 10, 1000);
        
        ArrayList<Producto> inventario = new  ArrayList<>();
        
        inventario.add(producto1);
        inventario.add(producto2);
        
        Cliente cliente1 = new Cliente("1234", "Pablo", "Cll 130 # 24C");
        Cliente cliente2 = new Cliente("4321", "Maria", "Cra 53 # 14");
        
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        clientes.add(cliente1);
        clientes.add(cliente2); 
        
        Negocio negocio = new Negocio("D1",administrador, vendedores, CajasRegistradoras, inventario, clientes);
        
                
        System.out.println("------------InicioPrograma-----------");
        int opcion;
        for(;;){
        System.out.println("Escoja como desea ingresar");
        System.out.println("1. Vendedor\n2. Admin");
        opcion = input.nextInt();
        switch(opcion){
            case 1:
                System.out.println("Digite su usuario de Vendedor");
                String usuario = input.next();
                                
                for(int i=0; i<negocio.getVendedores().size(); i++){
                    if(negocio.getVendedores().get(i).getUsuario().equals(usuario)){
                        System.out.println("Ingrese clave");
                        String clave = input.next();
                        if(negocio.getVendedores().get(i).getClave().equals(clave)){
                            int numCaja;
                            boolean bool = false;
                            do{
                            System.out.println("Digite el numero de la caja registradora");
                            numCaja= input.nextInt();
                            bool = negocio.getVendedores().get(i).accederCaja(numCaja);
                            }while(bool==false);

                            boolean otroBool = false;
                            do{
                                System.out.println("A continuacion se procedera a venta");
                                System.out.println("---------------------------");
                                System.out.println("");
                                negocio.getVendedores().get(i).getCajaQueOpera().getVentas().HacernuevaVenta();
                                System.out.println("Desea salir? (true/false)");
                                otroBool = input.nextBoolean();
                            }while(otroBool==false);

                            negocio.getVendedores().get(i).liberarCaja();
                            System.out.println("Caja liberada, vuelva pronto");
                            break;
                                                        
                        }else   System.out.print("Clave incorrecta/ ");
                        
                    }       
                }
                System.out.println("Usuario No encontrado");
                break;
            case 2:
                System.out.println("Digite su usuario de Admin");
                String usuario2 = input.next();
                
                if(negocio.getAdministrador().getUsuario().equalsIgnoreCase(usuario2)){
                    System.out.println("Ingrese clave");
                    String clave = input.next();
                    if(negocio.getAdministrador().getClave().equalsIgnoreCase(clave)){
                        int subOpcion;
                        System.out.println("Escoja que desea hacer");
                        System.out.println("1. Administrar Productos del inventario");
                        System.out.println("2. Administrar Vendedores");
                        System.out.println("3. Administrar Clientes");
                        System.out.println("4. Vizualizar dinero en cajas registradoras");
                        System.out.println("5. Reportes ventas");
                        System.out.println("6. Salir");
                        subOpcion = input.nextInt();
                        switch(subOpcion){
                            case 1:
                                int subSubOpcion1;
                                System.out.println("El inventario es: ");
                                negocio.getAdministrador().imprimirInventario();
                                
                                System.out.println("Que desea hacer con los productos?");
                                System.out.println("1. Cambiar nombre");
                                System.out.println("2. Cambiar precio");
                                System.out.println("3. Cambiar cantidad");
                                System.out.println("4. Remover producto");
                                System.out.println("5. Anniadir producto");
                                
                                subSubOpcion1 = input.nextInt();
                                if(subSubOpcion1 != 5){
                                    System.out.println("Ingrese el codigo del producto que desea cambiar");
                                    String codigoIn = input.next();
                                        if(subSubOpcion1==1){
                                            System.out.println("Ingrese el nuevo nombre");
                                            String nuevoNombre = input.next();
                                            if(negocio.getAdministrador().cambiarNombreProductoPorCodigo(codigoIn, nuevoNombre)){
                                                System.out.println("Cambio exitoso del nombre");
                                            }else System.out.println("Error en cambio del nombre");
                                        }if(subSubOpcion1==2){
                                            System.out.println("Ingrese el nuevo precio");
                                            double nuevoPrecio = input.nextDouble();
                                            if(negocio.getAdministrador().cambiarPrecioProductoPorCodigo(codigoIn, nuevoPrecio)){
                                                System.out.println("Cambio precio exitoso");
                                            }else System.out.println("Error en cambio del precio");
                                        }if(subSubOpcion1==3){
                                            System.out.println("Ingrese el nuevo precio");
                                            int nuevaCantidad = input.nextInt();
                                            if(negocio.getAdministrador().cambiarCantidadProductoPorCodigo(codigoIn, nuevaCantidad)){
                                                System.out.println("Cambio cantidad exitoso");
                                            } else System.out.println("Error en cambio de cantidad");
                                        }if(subSubOpcion1==4){
                                            if(negocio.getAdministrador().removerProductoXCodigo(codigoIn)){
                                                System.out.println("Se removio exitosamente");
                                            } else  System.out.println("Error en remover");
                                        }
                                }if(subSubOpcion1==5){
                                    System.out.println("Ingrese el nombre, el codigo, la cantidad y el precio respectivamente");
                                    String nombre = input.next();
                                    String codigo = input.next();
                                    int cantidad = input.nextInt();
                                    double precio = input.nextDouble();
                                    if(negocio.getAdministrador().anadirProducto(nombre, codigo, cantidad, precio)){
                                        System.out.println("Agregacion exitosa");
                                    } else System.out.println("Error en agregacion");
                                }                     
                                break;
                            case 2:
                                 int subSubOpcion2;
                                 System.out.println("Los vendedores son: ");
                                 negocio.getAdministrador().imprimirVendedores();
                                 System.out.println("Que desea hacer con los vendedores?");
                                 System.out.println("1. Annadir Vendedor\n2. Remover Vendedor");
                                 subSubOpcion2 = input.nextInt();
                                 if(subSubOpcion2==1){
                                     System.out.println("Ingrese Usuario y clave respectivamente");
                                     String usuarioNuevo = input.next();
                                     String claveNueva = input.next();
                                     if(negocio.getAdministrador().anadirVendedor(usuarioNuevo, claveNueva)){
                                         System.out.println("Agregacino exitosa");
                                    } else System.out.println("Error en agregacion");
                                 }if(subSubOpcion2==2){
                                     System.out.println("Ingrese el usuario del vendedor a remover");
                                     String usuarioIn= input.next();
                                     if(negocio.getAdministrador().removerVendedorXUsuario(usuarioIn)){
                                         System.out.println("Se removio exitosamente");
                                     } else  System.out.println("Error en remover");
                                 }
                                break;
                            case 3:
                                int subSubOpcion3;
                                System.out.println("Los clientes son: ");
                                 negocio.getAdministrador().imprimirClientes();
                                System.out.println("Que desea hacer?");
                                System.out.println("1. Annadir Cliente\n2. Remover Cliente");
                                subSubOpcion3 = input.nextInt();
                                    if(subSubOpcion3==1){
                                        System.out.println("Ingrese id, nombre y direccion respectivamente");
                                        String idCliente = input.next();
                                        String nombreCliente = input.nextLine();
                                        String direccionCliente = input.nextLine();
                                        negocio.getAdministrador().anadirClientes(idCliente, nombreCliente, direccionCliente);
                                    }if(subSubOpcion3==2){
                                         System.out.println("Ingrese el id del clinte a remover");
                                         String idIn= input.next();
                                         if(negocio.getAdministrador().removerClienteXId(idIn)){
                                             System.out.println("Se removio exitosamente");
                                         } else  System.out.println("Error en remover");
                                    }                                    
                                break;
                            case 4:
                                boolean subSubOpcion4=false;
                                System.out.println("Las cajas registradoras son: ");
                                negocio.getAdministrador().imprimirCajaRegistradora();
                                System.out.println("Desea modificar el dinero de las cajas? (true/false)");
                                subSubOpcion4 = input.nextBoolean();
                                if(subSubOpcion4==true){
                                    System.out.println("Ingrese el numero de la caja a cambiar");
                                    int numCaja = input.nextInt();
                                    if(negocio.getAdministrador().cambiarEfectivoACaja(numCaja)){
                                        System.out.println("cambio de Efectivo exitoso");
                                    } else System.out.println("error en cambio de efectivo ");
                                }                                
                                break;                            
                            case 5:
                                int subSubOpcion5;
                                System.out.println("Que desea vizualizar");
                                System.out.println("1. Porcentaje de Ventas por Producto");
                                System.out.println("2. Informe de Ventas para cada vendedor por periodo");
                                System.out.println("3. Gráfico de Evolución de ventas por mes");
                                System.out.println("4. Semáforo o termómetro de cumplimiento de metas");
                                subSubOpcion5 = input.nextInt();
                                
                                break;
                            case 7:
                                System.out.println("Vuelva pronto");
                                break;
                        }
                    }   else System.out.println("Clave incorrecta");
                }   else  System.out.println("Usuario No encontrado");
                break;
        }
        
        
        }
        
    }
    
}
