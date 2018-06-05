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
public class Vendedor extends Empleado{
    
    private CajaRegistradora cajaQueOpera;
    
    public Vendedor(String usuario, String clave) {
        super(usuario, clave);
    }

    public CajaRegistradora getCajaQueOpera() {
        return cajaQueOpera;
    }

    public void setCajaQueOpera(CajaRegistradora cajaQueOpera) {
        this.cajaQueOpera = cajaQueOpera;
    }
    
    
    
    public boolean accederCaja(int numCajaRegistradora){
        for(int i=0; i<this.getNegocio().getCajasRegistradoras().size(); i++){
            if(this.getNegocio().getCajasRegistradoras().get(i).getNumeroCaja() == numCajaRegistradora){
                if(this.getNegocio().getCajasRegistradoras().get(i).getOperador()==null){
                  this.getNegocio().getCajasRegistradoras().get(i).setOperador(this);
                  return true;
                }
            } 
    }
    return false;
    }
    
    public void liberarCaja(){
    
        this.cajaQueOpera.setOperador(null);
    
    }
    
    public void imprimir(){
        System.out.println("usuario: " + this.getUsuario()
                          + " clave: " + this.getClave());
    }
}
