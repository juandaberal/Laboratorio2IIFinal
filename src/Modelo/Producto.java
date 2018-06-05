/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Usuario
 */
public class Producto {
    private String nombre;
    private String codigo;
    private int cantidad;
    private double precio;

    public Producto(String nombre, String codigo, int cantidad, double precio) {
        this.nombre = nombre;
        this.codigo = codigo;
        if(cantidad >=0) {this.cantidad = cantidad;}
        if(precio >=0){this.precio = precio;}
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    public void imprimir(){
        System.out.println("Nombre: " + this.nombre
                + " Codigo: " + this.codigo 
                + " Cantidad: " + this.cantidad
                + " Precio: " + this.precio);
    }
  
}
