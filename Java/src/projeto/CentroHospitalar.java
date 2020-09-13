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
public class CentroHospitalar {
    
    public int IdCentroHospitalar;
    public String nome;
    public String regiao;
    
    public CentroHospitalar (int id, String nome, String regiao){
        
        IdCentroHospitalar=id;
        this.nome=nome;
        this.regiao=regiao;
    }
    
    public CentroHospitalar(){
        
        IdCentroHospitalar=0;
    }
}
