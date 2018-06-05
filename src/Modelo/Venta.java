/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Venta {
    private Date fecha;
    //para acceder al inventario y las cajas registradoras
    
    private ArrayList<Producto> productosVendidos;
    private CajaRegistradora caja;
    private Vendedor vendedor;
    private double dineroEnSistema;
    private Cliente cliente;
    
    private static ArrayList<String> carritocompraXcodigo;

    public Venta(Date fecha, CajaRegistradora caja, Vendedor vendedor) {
        this.fecha = fecha;
        this.caja = caja;
        this.vendedor = vendedor;
        this.productosVendidos = new ArrayList<>();
        Venta.carritocompraXcodigo = new ArrayList<>();
    }



    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public CajaRegistradora getCaja() {
        return caja;
    }

    public void setCaja(CajaRegistradora caja) {
        this.caja = caja;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public double getDineroEnSistema() {
        return dineroEnSistema;
    }

    public void setDineroEnSistema(double dineroEnSistema) {
        this.dineroEnSistema = dineroEnSistema;
    }

    public ArrayList<Producto> getProductosVendidos() {
        return productosVendidos;
    }

    public void setProductosVendidos(ArrayList<Producto> productosVendidos) {
        this.productosVendidos = productosVendidos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    

    public static ArrayList<String> getCarritocompraXcodigo() {
        return carritocompraXcodigo;
    }

    public static void setCarritocompraXcodigo(ArrayList<String> carritocompraXcodigo) {
        Venta.carritocompraXcodigo = carritocompraXcodigo;
    }
    
    
    
    
    public void anadiraCarrito(String codigo){
        carritocompraXcodigo.add(codigo);
    }
    
    public void removerUltimoCodigodelCarrito(){
        int ultimaPosicion = carritocompraXcodigo.size() - 1;
        carritocompraXcodigo.remove(ultimaPosicion);
    }

    
    public Producto productoAdquirible(String codigo){
        for(int i=0; i<this.getVendedor().getNegocio().getInventario().size(); i++){
            if(this.getVendedor().getNegocio().getInventario().get(i).getCodigo().equalsIgnoreCase(codigo)){
                if(this.getVendedor().getNegocio().getInventario().get(i).getCantidad()>0){
                    Producto adquirible = this.getVendedor().getNegocio().getInventario().get(i);
                    return adquirible;
                }
                else{
                    System.out.println("El producto" 
                            + this.vendedor.getNegocio().getInventario().get(i).getNombre()
                            +  " no esta disponible");
                }
            }
        }
        return null;
    }
    
    public ArrayList<Producto> productosAdquiribles(){
        ArrayList<Producto> adquiribles = null;
        for(int i=0; i<carritocompraXcodigo.size(); i++){
            Producto p= this.productoAdquirible(carritocompraXcodigo.get(i).intern());
            if(p != null){
                adquiribles.add(p);
            }
        }
        return adquiribles;
    }
    
    public void vender(ArrayList<Producto> adquiribles){
        ArrayList<Producto> inventario = this.vendedor.getNegocio().getInventario();
        
        for(int i=0; i<adquiribles.size(); i++){
            int match=0;
            for(int j=0; j< inventario.size(); i++){
                if(inventario.get(i).getCodigo().equals(adquiribles.get(j).getCodigo())){
                     match++;
                } 
            }
            int nuevaCantidad = this.vendedor.getNegocio().getInventario().get(i).getCantidad()-match;
            this.vendedor.getNegocio().getInventario().get(i).setCantidad(nuevaCantidad);
        }
        
        this.productosVendidos = adquiribles;
    }
    
    public double totalAPagar(ArrayList<Producto> adquiribles){
        double totalAPagar=0;
        if(adquiribles != null){
           for(int i=0; i<adquiribles.size(); i++){
               totalAPagar+= adquiribles.get(i).getPrecio();
           }
           return totalAPagar;
        }
        return -1;   
    }
    
    public double vueltos(double totalaPagar){
        Scanner input = new Scanner(System.in);
        if(totalaPagar > 0){   
            System.out.println("Ingrese su efectivo");
            double dineroIn = input.nextDouble();
            if(dineroIn>=totalaPagar){
            double vueltos = dineroIn-totalaPagar;
            if(vueltos <= this.caja.getEfectivo()){
                double nuevoEfectivo = this.caja.getEfectivo()-vueltos;
                this.caja.setEfectivo(nuevoEfectivo);
                return vueltos;
            }
            System.out.println("Lo sentimos no hay suficiente cambio");
            }
            System.out.println("Dinero insuficiente");
        }
        return -1;
    }




    public Cliente accederCliente(){
        Cliente select = null;
        Scanner input = new Scanner(System.in);
        System.out.println("La persona se encuentra afilida? (true/false)");
        boolean respuesta = input.nextBoolean();
        if(respuesta == true){
            System.out.println("Cual es el id?");
            String idCliente = input.next();
            if(this.vendedor.getNegocio().getClientes().size()==0){
                System.out.println("no se encontro Cliente");
            }
            for(int i = 0; i< this.vendedor.getNegocio().getClientes().size(); i++){
                if(this.vendedor.getNegocio().getClientes().get(i).getId().equalsIgnoreCase(idCliente)){
                    select = this.vendedor.getNegocio().getClientes().get(i);
                    System.out.println("Bienvenido" + select.getNombre());
                }else{
                    System.out.println("no se encontro Cliente");
                }
            }
        }
        return select;
    }
    
    public boolean agregarPuntos(double dinero){
        if(dinero >= 0){
            int p = (int) dinero/10000;
            int nuevaCantidad = this.cliente.getPuntos() + p;
            this.cliente.setPuntos(nuevaCantidad);
            return true;
        }
        return false;
    }
    
    public void imprimirRecibo(){
        System.out.println("Formato Recibo");
    }
    
    public boolean HacerVenta(){
        
        Scanner input = new Scanner(System.in);
        boolean opcion = false;
        String producto;
        double totalAPagar = 0;
        
        this.cliente = this.accederCliente();

        System.out.println("A continuacion annadira todos los productos que diga");
        
        System.out.println("Ingrese codigo producto");
        producto = input.next();
        Venta.carritocompraXcodigo.add(producto);
        do{
            System.out.println("Desea agregar producto? (true/false)");
            opcion = input.nextBoolean();
            if(opcion == true){
                producto = input.next();
                Venta.carritocompraXcodigo.add(producto);
            }   
        }while(opcion==true);
        
        
        ArrayList<Producto> adquiribles = this.productosAdquiribles();
        totalAPagar = this.totalAPagar(adquiribles);
        double vueltos = this.vueltos(totalAPagar);
        
        if(totalAPagar != -1 && vueltos != -1){
            System.out.println("sus vueltos son " + vueltos);
            this.dineroEnSistema=totalAPagar;
            this.vender(adquiribles);
            if(this.cliente != null){
                this.agregarPuntos(this.dineroEnSistema);
            }
            this.imprimirRecibo();
        }        
        return false;
    }
    
}
