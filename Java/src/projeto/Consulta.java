/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author brito
 */
public class Consulta extends JFrame{
    
    public int selecionado;
    public int selecionado2;
    public JScrollPane listScrollPane;
    public JScrollPane listScrollPane2;
    ArrayList <Ocorrencia> ocorrencias;
    ArrayList <Ocorrencia> ocorrencias2;
    ArrayList <Ocorrencia> ocorrencias3;
    Ocorrencia ocoTemp;
    JLabel rotulo = new JLabel();
    JLabel rotulo2 = new JLabel();
    JLabel rotulo3 = new JLabel();
    JLabel rotulo4 = new JLabel();
    
    public Consulta(SqlManager sql, int ID){
        
        GridBagLayout painel = new GridBagLayout(); //layout do painel
        GridBagConstraints c = new GridBagConstraints(); //constraints, ou seja os limites e formato de cada componente
       
        selecionado=-1;
        selecionado2=-1;
        
        this.setTitle("Consulta"); 
        this.setSize(550,800);  
        this.setVisible(true);
        
        JTabbedPane tp = new JTabbedPane();
        this.add(tp);
        
        JPanel painel2 = new JPanel(); /*painel que vai ser realmente usado*/
        painel2.setBackground(new Color(216, 229, 243));
        painel2.setLayout(painel);
        tp.add(painel2,"Dados de Consulta");
        
        JPanel painel3 = new JPanel(); /*painel que vai ser realmente usado*/
        painel3.setBackground(new Color(216, 229, 243));
        painel3.setLayout(painel);
        tp.add(painel3,"Histórico de Utente");
        
        JLabel titulo = new JLabel("Inserir dados de utente");        
        titulo.setFont(new Font("Calibri", Font.BOLD, 20));
        titulo.setForeground(Color.GRAY);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.insets = new Insets(20,10,50,10);
        c.weighty = 0;
        painel3.add(titulo,c);
        
        JTextField texto = new JTextField("ID de Utente");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(20,10,50,10);
        c.weightx = 0;
        c.weighty = 0;
        painel3.add(texto,c);
        
        JButton botao = new JButton("Pesquisar");
        
        JList regList = new JList();
        regList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        regList.setVisibleRowCount(5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(20,10,50,10);
        c.weightx = 2;
        c.weighty = 0;
        listScrollPane = new JScrollPane(regList);
        painel3.add(listScrollPane,c);
        
        JList regList2 = new JList();
        regList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        regList2.setVisibleRowCount(5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(20,10,50,10);
        c.weightx = 2;
        c.weighty = 0;
        listScrollPane2 = new JScrollPane(regList2);
        painel3.add(listScrollPane2,c);
        
        botao.addActionListener(new ActionListener(){
                                 
            public void actionPerformed(ActionEvent e){
                                
                if(texto.getText().equals("ID de Utente") || texto.getText().equals("")){
                
                    JOptionPane.showMessageDialog(null, "ID de Utente inválido!");
                }
                
                else{
                    
                    painel3.remove(listScrollPane);
                    
                    revalidate();
                    repaint();
                
                    DefaultListModel lista = new DefaultListModel();
                    ocorrencias = sql.getOcorrenciasEspecificas(Integer.parseInt(texto.getText()),"Consulta",ID);
                    String s = "";

                    for(int i=0; i<ocorrencias.size(); i++){

                        s = ("(" +ocorrencias.get(i).data +")     " +ocorrencias.get(i).estado +"     ");

                        if(ocorrencias.get(i).diagnostico==null || ocorrencias.get(i).diagnostico.equals("")){

                            s = (s + "Sem diagnóstico");
                        }

                        else{

                            s = (s + "'" +ocorrencias.get(i).diagnostico +"'");
                        }

                        lista.addElement(s);
                    }

                    JList regList = new JList(lista);

                    regList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    regList.setVisibleRowCount(5);

                    regList.addListSelectionListener( new ListSelectionListener() {

                    public void valueChanged(ListSelectionEvent e) {

                        if (e.getValueIsAdjusting() == false) {

                            selecionado=(regList.getSelectedIndex());
                            
                            if(selecionado>=0){                                
                            
                                painel3.remove(listScrollPane2);
                                DefaultListModel lista2 = new DefaultListModel();
                                ocorrencias2 = sql.getOcorrenciasLigadas(Integer.parseInt(texto.getText()),"Análises",ID,ocorrencias.get(selecionado).IdOcorrencia);
                                String s2 = "";

                                for(int i=0; i<ocorrencias2.size(); i++){

                                    s2 = ("(" +ocorrencias2.get(i).data +")     " +ocorrencias2.get(i).tipoOcorrencia +"     " +ocorrencias2.get(i).estado +"     ");

                                    if(ocorrencias2.get(i).diagnostico==null || ocorrencias2.get(i).diagnostico.equals("")){

                                        s2 = (s2 + "Sem diagnóstico");
                                    }

                                    else{

                                        s2 = (s2 + "'" +ocorrencias2.get(i).diagnostico +"'");
                                    }

                                    lista2.addElement(s2);
                                }

                                ocorrencias3 = sql.getOcorrenciasLigadas(Integer.parseInt(texto.getText()),"Exame",ID,ocorrencias.get(selecionado).IdOcorrencia);


                                for(int i=0; i<ocorrencias3.size(); i++){

                                    ocorrencias2.add(ocorrencias3.get(i));
                                }

                                for(int i=0; i<ocorrencias3.size(); i++){

                                    s2 = ("(" +ocorrencias3.get(i).data +")     " +ocorrencias3.get(i).tipoOcorrencia +"     " +ocorrencias3.get(i).estado +"     ");

                                    if(ocorrencias3.get(i).diagnostico==null || ocorrencias3.get(i).diagnostico.equals("")){

                                        s2 = (s2 + "Sem diagnóstico");
                                    }

                                    else{

                                        s2 = (s2 + "'" +ocorrencias3.get(i).diagnostico +"'");
                                    }

                                    lista2.addElement(s2);
                                }

                                JList regList2 = new JList(lista2);

                                regList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                                regList2.setVisibleRowCount(5);

                                regList2.addListSelectionListener( new ListSelectionListener() {

                                public void valueChanged(ListSelectionEvent e) {

                                    if (e.getValueIsAdjusting() == false) {

                                        selecionado2=(regList2.getSelectedIndex());
                                    }
                                }

                                });

                                c.fill = GridBagConstraints.HORIZONTAL;
                                c.gridx = 0;
                                c.gridy = 5;
                                c.insets = new Insets(20,10,50,10);
                                c.weightx = 2;
                                c.weighty = 0;

                                listScrollPane2 = new JScrollPane(regList2);
                                painel3.add(listScrollPane2,c);
                                SwingUtilities.updateComponentTreeUI(painel3);

                            }
                        }
                    }

                    });

                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = 3;
                    c.insets = new Insets(20,10,50,10);
                    c.weightx = 2;
                    c.weighty = 0;

                    listScrollPane = new JScrollPane(regList);
                    painel3.add(listScrollPane,c);
                    SwingUtilities.updateComponentTreeUI(painel3);
                                   
                }
                
                SwingUtilities.updateComponentTreeUI(painel3);
                
            }
        });
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(20,10,50,10);
        c.weightx = 0;
        c.weighty = 0;        
        painel3.add(botao,c);
        
        revalidate();
        repaint();
        
        JLabel titulo2 = new JLabel("Histórico de consultas");
        
        titulo2.setFont(new Font("Calibri", Font.BOLD, 19));
        titulo2.setForeground(Color.GRAY);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        c.insets = new Insets(20,10,50,10);
        c.weighty = 0;
        painel3.add(titulo2,c);
        
        JButton botao3 = new JButton("Ver detalhes");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 0;
        c.insets = new Insets(29,10,61,10);
        c.weighty = 0;
        
        botao3.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
                
                if(selecionado>=0){
                
                    String aux = ("ID: "+ocorrencias.get(selecionado).IdOcorrencia +" (" +ocorrencias.get(selecionado).tipoOcorrencia +")\nData e Hora: " +ocorrencias.get(selecionado).data +" - " +ocorrencias.get(selecionado).hora +"h\nSala: " +ocorrencias.get(selecionado).sala +"\nEstado: " +ocorrencias.get(selecionado).estado +"\nDiagnóstico: ");

                    if(ocorrencias.get(selecionado).diagnostico==null || ocorrencias.get(selecionado).diagnostico.equals("")){

                        aux = (aux + "Sem diagnóstico");
                    }

                    else{

                        aux = (aux +"'" +ocorrencias.get(selecionado).diagnostico +"'");
                    }

                    JOptionPane.showMessageDialog(null, aux);
                }
            }
        });
        
        painel3.add(botao3,c);
        
        JLabel titulo4 = new JLabel("Anexos");
        
        titulo4.setFont(new Font("Calibri", Font.BOLD, 19));
        titulo4.setForeground(Color.GRAY);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 0;
        c.insets = new Insets(20,10,50,10);
        c.weighty = 0;
        painel3.add(titulo4,c);
        
        JButton botao4 = new JButton("Ver detalhes");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        c.weightx = 0;
        c.insets = new Insets(29,10,61,10);
        c.weighty = 0;
        
        botao4.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
                
                if(selecionado2>=0){
                
                    String aux = ("ID: "+ocorrencias2.get(selecionado2).IdOcorrencia +" (" +ocorrencias2.get(selecionado2).tipoOcorrencia +")\nData e Hora: " +ocorrencias2.get(selecionado2).data +" - " +ocorrencias2.get(selecionado2).hora +"h\nSala: " +ocorrencias2.get(selecionado2).sala +"\nEstado: " +ocorrencias2.get(selecionado2).estado +"\nDiagnóstico: ");

                    if(ocorrencias2.get(selecionado2).diagnostico==null || ocorrencias2.get(selecionado2).diagnostico.equals("")){

                        aux = (aux + "Sem diagnóstico");
                    }

                    else{

                        aux = (aux +"'" +ocorrencias2.get(selecionado2).diagnostico +"'");
                    }
                    
                    Ocorrencia ocoOriginal = sql.getOcorrenciaOriginal(ocorrencias2.get(selecionado2).IdOcorrencia, ID);
                    
                    if(ocoOriginal.IdOcorrencia!=0){
                        
                        aux = (aux +"\nOriginada por: " +ocoOriginal.IdOcorrencia + " (" +ocoOriginal.tipoOcorrencia +")");
                    }

                    JOptionPane.showMessageDialog(null, aux);
                }
            }
        });
        
        painel3.add(botao4,c);
                      
        JLabel titulo3 = new JLabel("Inserir dados de consulta");
        
        titulo3.setFont(new Font("Calibri", Font.BOLD, 19));
        titulo3.setForeground(Color.GRAY);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 2;
        c.insets = new Insets(20,10,50,10);
        c.weighty = 0;
        painel2.add(titulo3,c);
        
        JTextField texto2 = new JTextField("ID de consulta");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(5,10,35,10);
        c.weightx = 0;
        c.weighty = 1;
        painel2.add(texto2,c);
        
        JButton botao2 = new JButton("Pesquisar");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(5,10,35,10);
        c.weightx = 0;
        c.weighty = 0;
                
        botao2.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                
                if(texto2.getText().equals("ID de consulta") || texto2.getText().equals("")){
                    
                    JOptionPane.showMessageDialog(null, "ID de consulta inválido!");
                }
                
                else{
                
                    ocoTemp = sql.getOcorrenciaEspecifica(Integer.parseInt(texto2.getText()),"Consulta",ID);

                    if(ocoTemp.IdOcorrencia==0){

                        JOptionPane.showMessageDialog(null, "ID de consulta inválido!");
                    }

                    else if(ocoTemp.estado.equals("Terminada")){

                        JOptionPane.showMessageDialog(null, "Consulta já terminada!");
                    }

                    else{
                        
                        painel2.remove(rotulo);
                        painel2.remove(rotulo2);
                        painel2.remove(rotulo3);
                        painel2.remove(rotulo4);

                        Utente utente = sql.getUtente(ocoTemp.IdOcorrencia,ID);
                        rotulo = new JLabel("Consulta");
                        rotulo.setFont(new Font("Calibri", Font.BOLD, 19));
                        rotulo.setForeground(Color.GRAY);
                        c.fill = GridBagConstraints.HORIZONTAL;
                        c.gridx = 0;
                        c.gridy = 2;
                        c.insets = new Insets(20,10,50,10);
                        c.weightx = 0;
                        c.weighty = 0;
                        painel2.add(rotulo,c);
                        
                        Utente utente2 = sql.getUtente(ocoTemp.IdUtente, ID);
                        
                        rotulo2 = new JLabel("Utente:   " +utente2.nome +"   (ID " +utente2.IdUtente +")");
                        c.fill = GridBagConstraints.HORIZONTAL;
                        c.gridx = 0;
                        c.gridy = 3;
                        c.insets = new Insets(0,10,90,10);
                        c.weightx = 0;
                        c.weighty = 2;
                        painel2.add(rotulo2,c);
                        
                        rotulo3 = new JLabel("Data e Hora:   " +ocoTemp.data +"   -   " +ocoTemp.hora +"h");
                        c.fill = GridBagConstraints.HORIZONTAL;
                        c.gridx = 0;
                        c.gridy = 3;
                        c.insets = new Insets(30,10,60,10);
                        c.weightx = 0;
                        c.weighty = 2;
                        painel2.add(rotulo3,c);
                        
                        rotulo4 = new JLabel("Sala:   " +ocoTemp.sala);
                        c.fill = GridBagConstraints.HORIZONTAL;
                        c.gridx = 0;
                        c.gridy = 3;
                        c.insets = new Insets(60,10,30,10);
                        c.weightx = 0;
                        c.weighty = 2;
                        painel2.add(rotulo4,c);

                        revalidate();
                        repaint();
                    }
                }
            }
        });
        
        painel2.add(botao2,c);
        
        JLabel rotulo5 = new JLabel("Atualizar dados");
        rotulo5.setFont(new Font("Calibri", Font.BOLD, 19));
        rotulo5.setForeground(Color.GRAY);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(30,10,40,10);
        c.weightx = 0;
        c.weighty = 0;
        painel2.add(rotulo5,c);
        
        JTextField estado = new JTextField("Estado");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(30,10,40,10);
        c.weightx = 2;
        c.weighty = 0;
        painel2.add(estado,c);
        
        JTextField diagnostico = new JTextField("Diagnóstico");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 6;
        c.insets = new Insets(30,10,40,10);
        c.weightx = 2;
        c.weighty = 0;
        painel2.add(diagnostico,c);
        
        JButton inserir = new JButton("Guardar");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 7;
        c.insets = new Insets(30,10,40,10);
        c.weightx = 2;
        c.weighty = 0;
        
        inserir.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
        
                if(estado.getText().equals("Estado") || estado.getText().equals("") || diagnostico.getText().equals("Diagnóstico") || diagnostico.getText().equals("")){
                    
                    JOptionPane.showMessageDialog(null, "Dados inválidos");
                }
                
                else{
                    
                    ocoTemp.estado=estado.getText();
                    ocoTemp.diagnostico=diagnostico.getText();

                    sql.insertOcorrencia(ocoTemp,ID,null,0,null);
                }
            }
        });
        
        painel2.add(inserir,c);
        revalidate();
        repaint();
    }
}
