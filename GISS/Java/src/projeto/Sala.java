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
public class Sala {
    
    public int IdSala;
    public int IdHospital;
    public int IdArea;
    public String tipo;
    
    public Sala(int ID, int hospital, int area, String tipo){
        
        IdSala=ID;
        IdHospital=hospital;
        IdArea=area;
        this.tipo=tipo;
    }
    
    public Sala(){
        
        IdSala=0;       
    }
}
