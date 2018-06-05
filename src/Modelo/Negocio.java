/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Negocio {
    private String nombre;
    private Admin administrador;
    private ArrayList<Vendedor> vendedores;
    private ArrayList<CajaRegistradora> CajasRegistradoras;
    private ArrayList<Producto> inventario;
    private ArrayList<Cliente> clientes;

    public Negocio(String nombre, Admin administrador, 
            ArrayList<Vendedor> vendedores,  
            ArrayList<CajaRegistradora> CajasRegistradoras, 
            ArrayList<Producto> inventario, ArrayList<Cliente> clientes) {
        
        this.nombre = nombre;
        this.administrador = administrador;
        this.vendedores = vendedores;
        this.CajasRegistradoras = CajasRegistradoras;
        this.inventario = inventario;
        this.clientes = clientes;
        
        this.administrador.setNegocio(this);
        
        for(int i = 0; i < this.vendedores.size(); i++){
            this.vendedores.get(i).setNegocio(this);
        }
         
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
     
    public Admin getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Admin administrador) {
        this.administrador = administrador;
    }

    public ArrayList<Vendedor> getVendedores() {
        return vendedores;
    }

    public void setVendedores(ArrayList<Vendedor> vendedores) {
        this.vendedores = vendedores;
    }

    public ArrayList<CajaRegistradora> getCajasRegistradoras() {
        return CajasRegistradoras;
    }

    public void setCajasRegistradoras(ArrayList<CajaRegistradora> CajasRegistradoras) {
        this.CajasRegistradoras = CajasRegistradoras;
    }

    public ArrayList<Producto> getInventario() {
        return inventario;
    }

    public void setInventario(ArrayList<Producto> inventario) {
        this.inventario = inventario;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }
   
    
}
