/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

/**
 *
 * @author jpcru
 */
public class Hospital {
    
    public int IdHospital;
    public int IdCentro;
    public String nome;
    
    public Hospital(int ID, int centro, String nome){
        
        IdHospital=ID;
        IdCentro=centro;
        this.nome=nome;
    }
    
    public Hospital(){
        
        IdHospital=0;       
    }
}
