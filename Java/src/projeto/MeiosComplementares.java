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
public class MeiosComplementares extends JFrame{
 
    public int selecionado;
    public int selecionado2;
    public JScrollPane listScrollPane;
    public JScrollPane listScrollPane2;
    ArrayList <Ocorrencia> ocorrencias;
    ArrayList <Ocorrencia> ocorrencias2;
    
    public MeiosComplementares(SqlManager sql, int ID){
    
        GridBagLayout painel = new GridBagLayout(); //layout do painel
        GridBagConstraints c = new GridBagConstraints(); //constraints, ou seja os limites e formato de cada componente
        selecionado=-1;
        selecionado2=-1;
        
        this.setTitle("Meios Complementares"); 
        this.setSize(550,770);  
        this.setVisible(true);
     
        JPanel painel2 = new JPanel(); /*painel que vai ser realmente usado*/
        painel2.setBackground(new Color(216, 229, 243));
        painel2.setLayout(painel);
        this.add(painel2);
        
        JLabel titulo = new JLabel("Inserir Dados");
        
        titulo.setFont(new Font("Calibri", Font.BOLD, 19));
        titulo.setForeground(Color.GRAY);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0;
        c.insets = new Insets(20,10,50,10);
        c.weighty = 0;
        painel2.add(titulo,c);
        
        JTextField utente = new JTextField("ID de Utente");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        c.insets = new Insets(20,10,50,10);
        c.weighty = 0;
        painel2.add(utente,c);
                
        revalidate();
        repaint();
        
        JButton botao = new JButton("Pesquisar");
        
        JList regList  = new JList();
        regList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        regList.setVisibleRowCount(5);
        listScrollPane = new JScrollPane(regList);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(20,10,50,10);
        c.weightx = 3;
        c.weighty = 0;
        painel2.add(listScrollPane,c);
        
        JList regList2  = new JList();
        regList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        regList2.setVisibleRowCount(5);
        listScrollPane2 = new JScrollPane(regList2);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(20,10,50,10);
        c.weightx = 3;
        c.weighty = 0;
        painel2.add(listScrollPane2,c);
        
        botao.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
                                                                              
                if(utente.getText().equals("ID de Utente") || utente.getText().equals("")){
                    
                    JOptionPane.showMessageDialog(null, "ID de Utente inválido!");
                }
                
                else{
                    
                    painel2.remove(listScrollPane);
                    painel2.remove(listScrollPane2);
                                        
                    DefaultListModel lista = new DefaultListModel();
                    ocorrencias = sql.getOcorrenciasEspecificas(Integer.parseInt(utente.getText()),"Análises",ID);
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

                    JList regList  = new JList(lista);

                    regList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    regList.setVisibleRowCount(5);

                    regList.addListSelectionListener( new ListSelectionListener() {

                        public void valueChanged(ListSelectionEvent e) {

                            if (e.getValueIsAdjusting() == false) {

                                selecionado=(regList.getSelectedIndex());                                
                            }
                        }
                    });

                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = 3;
                    c.insets = new Insets(20,10,50,10);
                    c.weightx = 3;
                    c.weighty = 0;

                    listScrollPane = new JScrollPane(regList);
                    painel2.add(listScrollPane,c);

                    DefaultListModel lista2 = new DefaultListModel();
                    ocorrencias2 = sql.getOcorrenciasEspecificas(Integer.parseInt(utente.getText()),"Exame",ID);
                    String s2 = "";

                    for(int i=0; i<ocorrencias2.size(); i++){

                        s2 = ("(" +ocorrencias2.get(i).data +")     " +ocorrencias2.get(i).estado +"     ");

                        if(ocorrencias2.get(i).diagnostico==null || ocorrencias2.get(i).diagnostico.equals("")){

                            s2 = (s2 + "Sem diagnóstico");
                        }

                        else{

                            s2 = (s2 + "'" +ocorrencias2.get(i).diagnostico +"'");
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
                    c.weightx = 3;
                    c.weighty = 0;

                    listScrollPane2 = new JScrollPane(regList2);
                    painel2.add(listScrollPane2,c);
                }
                
                SwingUtilities.updateComponentTreeUI(painel2);
            }            
        });
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.weightx = 0;
        c.insets = new Insets(20,10,50,10);
        c.weighty = 0;
        painel2.add(botao,c);
        
        revalidate();
        repaint();
                       
        JLabel titulo2 = new JLabel("Análises");
        
        titulo2.setFont(new Font("Calibri", Font.BOLD, 19));
        titulo2.setForeground(Color.GRAY);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0;
        c.insets = new Insets(20,10,50,10);
        c.weighty = 0;
        painel2.add(titulo2,c);
        
        JButton botao4 = new JButton("Ver detalhes");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 2;
        c.insets = new Insets(0,10,92,10);
        c.weighty = 0;
        
        botao4.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
                
                if(selecionado>=0){
                
                    String aux = ("ID: "+ocorrencias.get(selecionado).IdOcorrencia +" (" +ocorrencias.get(selecionado).tipoOcorrencia +")\nData e Hora: " +ocorrencias.get(selecionado).data +" - " +ocorrencias.get(selecionado).hora +"h\nSala: " +ocorrencias.get(selecionado).sala +"\nEstado: " +ocorrencias.get(selecionado).estado +"\nDiagnóstico: ");

                    if(ocorrencias.get(selecionado).diagnostico==null || ocorrencias.get(selecionado).diagnostico.equals("")){

                        aux = (aux + "Sem diagnóstico");
                    }

                    else{

                        aux = (aux +"'" +ocorrencias.get(selecionado).diagnostico +"'");
                    }
                    
                    Ocorrencia ocoOriginal = sql.getOcorrenciaOriginal(ocorrencias.get(selecionado).IdOcorrencia, ID);
                    
                    if(ocoOriginal.IdOcorrencia!=0){
                        
                        aux = (aux +"\nOriginada por: " +ocoOriginal.IdOcorrencia + " (" +ocoOriginal.tipoOcorrencia +")");
                    }

                    JOptionPane.showMessageDialog(null, aux);
                }
            }
        });
        
        painel2.add(botao4,c);
        
        JTextField diagnostico = new JTextField("Diagnóstico");
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(20,10,50,10);
        c.weightx = 2;
        c.weighty = 0;
        painel2.add(diagnostico,c);
    
        JButton botao2 = new JButton("Adicionar");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.weightx = 2;
        c.insets = new Insets(80,10,50,10);
        c.weighty = 0;
        
        botao2.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
                
                if(diagnostico.getText().equals("Diagnóstico")){
                    
                    JOptionPane.showMessageDialog(null, "Diagnóstico inválido!");
                }
                
                else{
                    
                    ocorrencias.get(selecionado).diagnostico=diagnostico.getText();
                    sql.insertOcorrencia(ocorrencias.get(selecionado),ID,null,0,null);
                }
            }
        });
        
        painel2.add(botao2,c);
                       
        JLabel titulo3 = new JLabel("Exames");
        
        titulo3.setFont(new Font("Calibri", Font.BOLD, 19));
        titulo3.setForeground(Color.GRAY);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.weightx = 0;
        c.insets = new Insets(20,10,50,10);
        c.weighty = 0;
        painel2.add(titulo3,c);
        
        JButton botao5 = new JButton("Ver detalhes");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        c.weightx = 2;
        c.insets = new Insets(0,10,92,10);
        c.weighty = 0;
        
        botao5.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
                
                if(selecionado2>=0){
                
                    String aux = ("ID: "+ocorrencias2.get(selecionado2).IdOcorrencia +" (" +ocorrencias2.get(selecionado2).tipoOcorrencia +")\nData e Hora: " +ocorrencias2.get(selecionado2).data +" - " +ocorrencias2.get(selecionado2).hora +"h\nSala: " +ocorrencias2.get(selecionado2).sala +"\nEstado: " +ocorrencias2.get(selecionado2).estado +"\nDiagnóstico: ");

                    if(ocorrencias2.get(selecionado2).diagnostico==null || ocorrencias2.get(selecionado2).diagnostico.equals("")){

                        aux = (aux + "Sem diagnóstico");
                    }

                    else{

                        aux = (aux + ocorrencias2.get(selecionado2).diagnostico);
                    }
                    
                    Ocorrencia ocoOriginal = sql.getOcorrenciaOriginal(ocorrencias2.get(selecionado2).IdOcorrencia, ID);
                    
                    if(ocoOriginal.IdOcorrencia!=0){
                        
                        aux = (aux +"\nOriginada por: " +ocoOriginal.IdOcorrencia + " (" +ocoOriginal.tipoOcorrencia +")");
                    }

                    JOptionPane.showMessageDialog(null, aux);
                }
            }
        });
        
        painel2.add(botao5,c);
                              
        JTextField diagnostico2 = new JTextField("Diagnóstico");
        c.gridx = 1;
        c.gridy = 5;
        c.insets = new Insets(20,10,50,10);
        c.weightx = 2;
        c.weighty = 0;
        painel2.add(diagnostico2,c);
    
        JButton botao3 = new JButton("Adicionar");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 5;
        c.weightx = 2;
        c.insets = new Insets(80,10,50,10);
        c.weighty = 0;
        
        botao3.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
                
                if(diagnostico2.getText().equals("Diagnóstico")){
                    
                    JOptionPane.showMessageDialog(null, "Diagnóstico inválido!");
                }
                
                else{
                    
                    ocorrencias2.get(selecionado2).diagnostico=diagnostico2.getText();
                    sql.insertOcorrencia(ocorrencias2.get(selecionado2),ID,null,0,null);
                }
            }
        });
        
        painel2.add(botao3,c);    
    }
    
}
