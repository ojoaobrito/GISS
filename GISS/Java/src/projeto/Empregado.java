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
public class Empregado {
    
    public int IdEmpregado;
    public int IdCentroHospitalar;
    public String nome;
    public String funcao;
    
    public Empregado(int ID, int centro, String nome, String funcao){
        
        IdEmpregado=ID;
        IdCentroHospitalar=centro;
        this.nome=nome;
        this.funcao=funcao;       
    }
    
    public Empregado(){
        
        IdEmpregado=0;       
    }
}
