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
public class AreaClinica {
    
    public int IdArea;
    public String nome;
    
    public AreaClinica(int ID, String nome){
        
        IdArea=ID;
        this.nome=nome;
    }
    
    public AreaClinica(){
        
        IdArea=0;       
    }
}
