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
public class Ocorrencia {
    
    public int IdOcorrencia;
    public int IdUtente;
    public int IdCentroHospitalar;
    public String tipoOcorrencia;
    public int hora;
    public String data;
    public String estado;
    public int sala;
    public String diagnostico;
    
    public Ocorrencia(int id,int idU, int idC, String tipo, int hora, String data, String estado, int sala, String diagnostico){
        
        IdOcorrencia=id;
        IdUtente=idU;
        IdCentroHospitalar=idC;
        tipoOcorrencia=tipo;
        this.hora=hora;
        this.data=data;
        this.estado=estado;
        this.sala=sala;
        this.diagnostico=diagnostico;
    }
    
    public Ocorrencia(){
        
        IdOcorrencia=0;
    }
}
