/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

/**
 *
 * @author brito
 */
public class Utente {
    
    public int IdUtente;
    public int IdCentroHospitalar;
    public String nome;
    public String dataRegisto;
    
    public Utente(int id, int idCentro, String nome, String data){
        
        IdUtente=id;
        IdCentroHospitalar=idCentro;
        this.nome=nome;
        dataRegisto=data;
    }
    
    public Utente(){
        
        IdUtente=0;
    }
}
