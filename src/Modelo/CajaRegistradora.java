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
public class CajaRegistradora {
    private int numeroCaja;
    private double Efectivo;
    private Vendedor operador;
    private ConjuntoVentas ventas;

    public CajaRegistradora(int numeroCaja, double Efectivo) {
        this.numeroCaja = numeroCaja;
        this.Efectivo = Efectivo;
        this.operador = null;
        this.ventas = new ConjuntoVentas();
        ventas.setCaja(this);
    }

    public int getNumeroCaja() {
        return numeroCaja;
    }

    public void setNumeroCaja(int numeroCaja) {
        this.numeroCaja = numeroCaja;
    }

    public double getEfectivo() {
        return Efectivo;
    }

    public void setEfectivo(double Efectivo) {
        this.Efectivo = Efectivo;
    }

    public Vendedor getOperador() {
        return operador;
    }

    public void setOperador(Vendedor operador) {
        this.operador = operador;
        operador.setCajaQueOpera(this);
    }

    public ConjuntoVentas getVentas() {
        return ventas;
    }

    public void setVentas(ConjuntoVentas ventas) {
        this.ventas = ventas;
    }
    
    public void imprimir(){
        System.out.println("caja numero: " + this.numeroCaja
                          +" Efectivo: " + this.Efectivo);
    }
    
}
