/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Utilizador
 */
public class SqlManager {
    
    
    private String connectionUrl;

    private String host, username, database, password;
    private int port;
    
    Connection con;

    public SqlManager() {
        
        host = "localhost";
        port = 1433;
        username = "bases";
        password = "Bases2018";
        database = "Projeto";
        
        openConnection();
    }
    
    private void openConnection() {
        
        connectionUrl = "jdbc:sqlserver://" + host + ":" + port + ";user=" + username + ";password=" + password + ";databaseName=" + database + ";integratedSecurity=true;";
        
        try {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.con = DriverManager.getConnection(connectionUrl);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void closeConnection() {
        closeConnection(this.con);
    }
    
    public void closeConnection(Connection con) {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.con;
    }
    
    public ArrayList<Empregado> getEmpregadosSemanal(String funcao, int ID, String inicio, String fim) {
        
        Connection c = null;
        
        try {
            c = getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT DISTINCT P.IdEmpregado, P.IdCentroHospitalar, P.Nome, P.Funcao FROM Empregado P, Equipa E WHERE P.IdEmpregado=E.IdEmpregado AND P.Funcao='" +funcao +"' AND P.IdCentroHospitalar=" +ID +" AND E.IdOcorrencia IN (SELECT IdOcorrencia FROM Ocorrencia WHERE DataOcorrencia>='" +inicio +"' AND DataOcorrencia <='" +fim +"' AND IdCentroHospitalar=" +ID +")");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Empregado> empregados = new ArrayList();
            while(resultSet.next()) {
                Empregado empTemp = new Empregado(resultSet.getInt("IdEmpregado"),resultSet.getInt("IdCentroHospitalar"),resultSet.getString("Nome"), resultSet.getString("Funcao"));
                empregados.add(empTemp);
            }
            
            resultSet.close();
            preparedStatement.close();
            return empregados;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            //closeConnection(c);
        }
        return null;
    }
    
    public ArrayList<Empregado> getEmpregados(String funcao, int ID) {
        
        Connection c = null;
        
        try {
            c = getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM Empregado WHERE IdCentroHospitalar=" +ID +" AND Funcao='" +funcao +"'");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Empregado> empregados = new ArrayList();
            while(resultSet.next()) {
                Empregado empTemp = new Empregado(resultSet.getInt("IdEmpregado"),resultSet.getInt("IdCentroHospitalar"),resultSet.getString("Nome"), resultSet.getString("Funcao"));
                empregados.add(empTemp);
            }
            resultSet.close();
            preparedStatement.close();
            return empregados;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            //closeConnection(c);
        }
        return null;
    }
    
    public ArrayList<Ocorrencia> getOcorrenciasEmpregado(int empID, int ID, String inicio, String fim) {
        
        Connection c = null;
        
        try {
            c = getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT O.IdOcorrencia, O.IdUtente, O.IdCentroHospitalar, O.TipoOcorrencia, O.Hora, O.DataOcorrencia, O.Estado, O.IdSala, O.Diagnostico FROM Equipa E, Ocorrencia O WHERE O.IdOcorrencia=E.IdOcorrencia AND O.IdCentroHospitalar=" +ID +" AND E.IdEmpregado=" +empID +" AND E.IdOcorrencia IN (SELECT IdOcorrencia FROM Ocorrencia WHERE DataOcorrencia>='" +inicio +"' AND DataOcorrencia<='" +fim +"' AND IdCentroHospitalar=" +ID +") ORDER BY O.DataOcorrencia DESC");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Ocorrencia> ocorrencias = new ArrayList();
            while(resultSet.next()) {
                Ocorrencia ocoTemp = new Ocorrencia(resultSet.getInt("IdOcorrencia"),resultSet.getInt("IdUtente"),resultSet.getInt("IdCentroHospitalar"),resultSet.getString("TipoOcorrencia"), resultSet.getInt("Hora"),resultSet.getString("DataOcorrencia"),resultSet.getString("Estado"),resultSet.getInt("IdSala"),resultSet.getString("Diagnostico"));
                ocorrencias.add(ocoTemp);
            }
            resultSet.close();
            preparedStatement.close();
            return ocorrencias;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            //closeConnection(c);
        }
        return null;
    }
    
    public ArrayList<Ocorrencia> getOcorrenciasSala(int salaID, int ID, String inicio, String fim) {
        
        Connection c = null;
        
        try {
            c = getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM Ocorrencia O WHERE O.IdCentroHospitalar=" +ID +" AND O.IdSala=" +salaID +" AND DataOcorrencia>='" +inicio +"' AND DataOcorrencia<='" +fim +"'");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Ocorrencia> ocorrencias = new ArrayList();
            while(resultSet.next()) {
                Ocorrencia ocoTemp = new Ocorrencia(resultSet.getInt("IdOcorrencia"),resultSet.getInt("IdUtente"),resultSet.getInt("IdCentroHospitalar"),resultSet.getString("TipoOcorrencia"), resultSet.getInt("Hora"),resultSet.getString("DataOcorrencia"),resultSet.getString("Estado"),resultSet.getInt("IdSala"),resultSet.getString("Diagnostico"));
                ocorrencias.add(ocoTemp);
            }
            resultSet.close();
            preparedStatement.close();
            return ocorrencias;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            //closeConnection(c);
        }
        return null;
    }
    
    public void insertOcorrencia(Ocorrencia ocorrencia, int ID, ArrayList<Empregado> equipa, int IDoriginal, String motivo) {
        Connection c = null;
        try {
            
            c = getConnection();
            PreparedStatement statementTemp = c.prepareStatement("SELECT * FROM Ocorrencia ORDER BY IdOcorrencia DESC");
            ResultSet resultSetTemp = statementTemp.executeQuery();
            
            resultSetTemp.next();
            int total=resultSetTemp.getInt("IdOcorrencia");
            System.out.println(total);
            resultSetTemp.close();
            statementTemp.close();
            
            PreparedStatement statement = c.prepareStatement("SELECT * FROM Ocorrencia WHERE IdOcorrencia=" +ocorrencia.IdOcorrencia +"AND IdCentroHospitalar=" +ID);
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                PreparedStatement preparedStatement = c.prepareStatement("UPDATE Ocorrencia SET IdOcorrencia=" +ocorrencia.IdOcorrencia +", IdUtente=" +ocorrencia.IdUtente +", IdCentroHospitalar=" +ocorrencia.IdCentroHospitalar +", TipoOcorrencia='" +ocorrencia.tipoOcorrencia +"', Hora=" +ocorrencia.hora +", DataOcorrencia='" +ocorrencia.data +"', Estado='" +ocorrencia.estado +"', IdSala=" +ocorrencia.sala +", Diagnostico='" +ocorrencia.diagnostico +"' WHERE IdOcorrencia=" +ocorrencia.IdOcorrencia +"AND IdCentroHospitalar=" +ocorrencia.IdCentroHospitalar +";");
                preparedStatement.executeUpdate();
                preparedStatement.close();
                
                if(IDoriginal>0){
                    
                    preparedStatement = c.prepareStatement("INSERT INTO Liga(IdOcorrenciaInicial, IdOcorrenciaDerivada, Motivo) VALUES(" +IDoriginal +", "+ocorrencia.IdOcorrencia +", '" +motivo +"');");
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                }            
                
                if(equipa!=null){
                    for(int i=0; i<equipa.size(); i++){

                        statementTemp = c.prepareStatement("INSERT INTO Equipa(IdOcorrencia, IdEmpregado) VALUES("+(total+1) +", " +equipa.get(i).IdEmpregado +");");
                        statementTemp.executeUpdate();
                        statementTemp.close();                
                    }
                }
            } 
            
            else {
                PreparedStatement preparedStatement = c.prepareStatement("INSERT INTO Ocorrencia(IdOcorrencia, IdUtente, IdCentroHospitalar, TipoOcorrencia, Hora, DataOcorrencia, Estado, IdSala, Diagnostico) VALUES(" +(total+1) +", "+ocorrencia.IdUtente +", " +ocorrencia.IdCentroHospitalar +", '" +ocorrencia.tipoOcorrencia +"', " +ocorrencia.hora +", '" +ocorrencia.data +"', '" +ocorrencia.estado +"', " +ocorrencia.sala +", '" +ocorrencia.diagnostico +"');");
                preparedStatement.executeUpdate();
                preparedStatement.close();
                
                if(IDoriginal>0){
                    
                    preparedStatement = c.prepareStatement("INSERT INTO Liga(IdOcorrenciaInicial, IdOcorrenciaDerivada, Motivo) VALUES(" +IDoriginal +", "+(total+1) +", '" +motivo +"');");
                    preparedStatement.executeUpdate();
                    preparedStatement.close();
                }
                
                if(equipa!=null){
                    for(int i=0; i<equipa.size(); i++){

                        statementTemp = c.prepareStatement("INSERT INTO Equipa(IdOcorrencia, IdEmpregado) VALUES("+(total+1) +", " +equipa.get(i).IdEmpregado +");");
                        statementTemp.executeUpdate();
                        statementTemp.close();                
                    }
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //closeConnection(c);
        }
    }
    
    public ArrayList<CentroHospitalar> getCentrosHospitalares(){
        
        Connection c = null;
        
        try {
            c = getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM CentroHospitalar");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<CentroHospitalar> centros = new ArrayList();
            while(resultSet.next()) {
                CentroHospitalar centroTemp = new CentroHospitalar(resultSet.getInt("IdCentroHospitalar"),resultSet.getString("Nome"),resultSet.getString("Regiao"));
                centros.add(centroTemp);
            }
            resultSet.close();
            preparedStatement.close();
            return centros;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            //closeConnection(c);
        }
        return null;
    }
    
    public ArrayList<Sala> getSalas(int ID, String dataI, String dataF){
        
        Connection c = null;
        
        try {
            c = getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM Sala WHERE IdSala IN (SELECT DISTINCT IdSala FROM Ocorrencia WHERE IdCentroHospitalar=" +ID +" AND DataOcorrencia>='" +dataI +"' AND DataOcorrencia<='" +dataF +"')");
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Sala> salas = new ArrayList();
            while(resultSet.next()) {
                Sala salaTemp = new Sala(resultSet.getInt("IdSala"),resultSet.getInt("IdHospital"),resultSet.getInt("IdArea"),resultSet.getString("TipoSala"));
                salas.add(salaTemp);
            }
            resultSet.close();
            preparedStatement.close();
            return salas;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            //closeConnection(c);
        }
        return null;
    }
    
    public Hospital getHospital(int ID, int hospID){
        
        Connection c = null;
        
        try {
            c = getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT DISTINCT * FROM Hospital WHERE IdHospital=" +hospID);
            ResultSet resultSet = preparedStatement.executeQuery();
            Hospital hospTemp = new Hospital();
            while(resultSet.next()) {
                hospTemp = new Hospital(resultSet.getInt("IdHospital"),resultSet.getInt("IdCentroHospitalar"),resultSet.getString("Nome"));
            }
            resultSet.close();
            preparedStatement.close();
            return hospTemp;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            //closeConnection(c);
        }
        return null;
    }
    
    public AreaClinica getArea(int ID, int areaID){
        
        Connection c = null;
        
        try {
            c = getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT DISTINCT * FROM AreaClinica WHERE IdArea=" +areaID);
            ResultSet resultSet = preparedStatement.executeQuery();
            AreaClinica areaTemp = new AreaClinica();
            while(resultSet.next()) {
                areaTemp = new AreaClinica(resultSet.getInt("IdArea"),resultSet.getString("Nome"));
            }
            resultSet.close();
            preparedStatement.close();
            return areaTemp;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            //closeConnection(c);
        }
        return null;
    }
    
    public ArrayList<Ocorrencia> getOcorrencias(int ID, int IDcentro){
        
        Connection c = null;
        
        try {
            c = getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM Ocorrencia WHERE IdUtente="+ID +"AND IdCentroHospitalar="+IDcentro);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Ocorrencia> ocorrencias = new ArrayList();
            while(resultSet.next()) {
                Ocorrencia ocoTemp = new Ocorrencia(resultSet.getInt("IdOcorrencia"),resultSet.getInt("IdUtente"),resultSet.getInt("IdCentroHospitalar"),resultSet.getString("TipoOcorrencia"), resultSet.getInt("Hora"),resultSet.getString("DataOcorrencia"),resultSet.getString("Estado"),resultSet.getInt("IdSala"),resultSet.getString("Diagnostico"));
                ocorrencias.add(ocoTemp);
            }
            resultSet.close();
            preparedStatement.close();
            return ocorrencias;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            //closeConnection(c);
        }
        return null;
    }
    
    public ArrayList<Ocorrencia> getOcorrenciasEspecificas(int ID, String tipo, int IDcentro){
        
        Connection c = null;
        
        try {
            c = getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM Ocorrencia WHERE IdUtente=" +ID +" AND TipoOcorrencia='" +tipo +"'" +" AND IdCentroHospitalar="+IDcentro);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Ocorrencia> ocorrencias = new ArrayList();
            while(resultSet.next()) {
                Ocorrencia ocoTemp = new Ocorrencia(resultSet.getInt("IdOcorrencia"),resultSet.getInt("IdUtente"),resultSet.getInt("IdCentroHospitalar"),resultSet.getString("TipoOcorrencia"), resultSet.getInt("Hora"),resultSet.getString("DataOcorrencia"),resultSet.getString("Estado"),resultSet.getInt("IdSala"),resultSet.getString("Diagnostico"));
                ocorrencias.add(ocoTemp);
            }
            resultSet.close();
            preparedStatement.close();
            return ocorrencias;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            //closeConnection(c);
        }
        return null;
    }
    
    public ArrayList<Ocorrencia> getOcorrenciasLigadas(int ID, String tipo, int IDcentro, int IDoriginal){
        
        Connection c = null;
        
        try {
            c = getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT O.IdOcorrencia, O.IdUtente, O.IdCentroHospitalar, O.TipoOcorrencia, O.Hora, O.DataOcorrencia, O.Estado, O.IdSala, O.Diagnostico FROM Ocorrencia O, Liga L WHERE O.IdUtente=" +ID +" AND O.TipoOcorrencia='" +tipo +"'" +" AND O.IdCentroHospitalar="+IDcentro +"AND L.IdOcorrenciaInicial=" +IDoriginal +" AND L.IdOcorrenciaDerivada=O.IdOcorrencia" );
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Ocorrencia> ocorrencias = new ArrayList();
            while(resultSet.next()) {
                Ocorrencia ocoTemp = new Ocorrencia(resultSet.getInt("IdOcorrencia"),resultSet.getInt("IdUtente"),resultSet.getInt("IdCentroHospitalar"),resultSet.getString("TipoOcorrencia"), resultSet.getInt("Hora"),resultSet.getString("DataOcorrencia"),resultSet.getString("Estado"),resultSet.getInt("IdSala"),resultSet.getString("Diagnostico"));
                ocorrencias.add(ocoTemp);
            }
            resultSet.close();
            preparedStatement.close();
            return ocorrencias;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            //closeConnection(c);
        }
        return null;
    }
    
    public Ocorrencia getOcorrenciaOriginal(int ID, int IDcentro){
        
        Connection c = null;
        
        try {
            c = getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT O.IdOcorrencia, O.IdUtente, O.IdCentroHospitalar, O.TipoOcorrencia, O.Hora, O.DataOcorrencia, O.Estado, O.IdSala, O.Diagnostico FROM Ocorrencia O, Liga L WHERE O.IdOcorrencia=L.IdOcorrenciaInicial AND L.IdOcorrenciaDerivada="+ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            Ocorrencia ocorrencia = new Ocorrencia();
            while(resultSet.next()) {
                ocorrencia = new Ocorrencia(resultSet.getInt("IdOcorrencia"),resultSet.getInt("IdUtente"),resultSet.getInt("IdCentroHospitalar"),resultSet.getString("TipoOcorrencia"), resultSet.getInt("Hora"),resultSet.getString("DataOcorrencia"),resultSet.getString("Estado"),resultSet.getInt("IdSala"),resultSet.getString("Diagnostico"));
            }
            resultSet.close();
            preparedStatement.close();
            return ocorrencia;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            //closeConnection(c);
        }
        return null;
    }
    
    public Ocorrencia getOcorrenciaEspecifica(int ID, String tipo, int IDcentro){
        
        Connection c = null;
        
        try {
            c = getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM Ocorrencia WHERE IdOcorrencia=" +ID +" AND TipoOcorrencia='" +tipo +"'" +" AND IdCentroHospitalar="+IDcentro);
            ResultSet resultSet = preparedStatement.executeQuery();
            Ocorrencia ocorrencia = new Ocorrencia();
            
            while(resultSet.next()) {
                ocorrencia = new Ocorrencia(resultSet.getInt("IdOcorrencia"),resultSet.getInt("IdUtente"),resultSet.getInt("IdCentroHospitalar"),resultSet.getString("TipoOcorrencia"), resultSet.getInt("Hora"),resultSet.getString("DataOcorrencia"),resultSet.getString("Estado"),resultSet.getInt("IdSala"),resultSet.getString("Diagnostico"));
            }
            resultSet.close();
            preparedStatement.close();
            return ocorrencia;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            //closeConnection(c);
        }
        return null;
    }
    
    public Utente getUtente(int ID, int IDcentro){
        
        Connection c = null;
        
        try {
            c = getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM Utente WHERE IdUtente=" +ID+" AND IdCentroHospitalar="+IDcentro);
            ResultSet resultSet = preparedStatement.executeQuery();
            Utente utente = new Utente();
            
            while(resultSet.next()) {
                utente = new Utente(resultSet.getInt("IdUtente"),resultSet.getInt("IdCentroHospitalar"),resultSet.getString("Nome"),resultSet.getString("DataRegisto"));
            }
            resultSet.close();
            preparedStatement.close();
            return utente;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            //closeConnection(c);
        }
        return null;
    }
    
    public Empregado getEmpregado(int ID, int IDcentro){
        
        Connection c = null;
        
        try {
            c = getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM Empregado WHERE IdEmpregado=" +ID+" AND IdCentroHospitalar="+IDcentro);
            ResultSet resultSet = preparedStatement.executeQuery();
            Empregado empregado = new Empregado();
            
            while(resultSet.next()) {
                empregado = new Empregado(resultSet.getInt("IdEmpregado"),resultSet.getInt("IdCentroHospitalar"),resultSet.getString("Nome"),resultSet.getString("Funcao"));
            }
            resultSet.close();
            preparedStatement.close();
            return empregado;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            //closeConnection(c);
        }
        return null;
    }
    
    public ArrayList getEspecializacoes(int ID){
        
        Connection c = null;
        
        try {
            c = getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT Especializacao FROM Especializacao WHERE IdEmpregado=" +ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList especializacoes = new ArrayList();
            
            while(resultSet.next()) {
                especializacoes.add(resultSet.getString("Especializacao"));
            }
            resultSet.close();
            preparedStatement.close();
            return especializacoes;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            //closeConnection(c);
        }
        return null;
    }
    
    public CentroHospitalar getCentroHospitalar(int ID){
        
        Connection c = null;
        
        try {
            c = getConnection();
            PreparedStatement preparedStatement = c.prepareStatement("SELECT * FROM CentroHospitalar WHERE IdCentroHospitalar=" +ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            CentroHospitalar centro = new CentroHospitalar();
            
            while(resultSet.next()) {
                centro = new CentroHospitalar(resultSet.getInt("IdCentroHospitalar"),resultSet.getString("Nome"),resultSet.getString("Regiao"));
            }
            resultSet.close();
            preparedStatement.close();
            return centro;
            
        } catch (SQLException e) {
            e.printStackTrace();
            
        } finally {
            //closeConnection(c);
        }
        return null;
    }
}
