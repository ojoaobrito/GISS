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
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author jpcru
 */
public class Medicos extends JFrame{
    
    public int escolhido;
    JList regList;
    
    public Medicos(SqlManager sql, int ID){
        
        GridBagLayout painel = new GridBagLayout(); //layout do painel
        GridBagConstraints c = new GridBagConstraints(); //constraints, ou seja os limites e formato de cada componente
       
        this.setTitle("Consulta"); 
        this.setSize(300,500);  
        this.setVisible(true);
     
        JPanel painel2 = new JPanel(); /*painel que vai ser realmente usado*/
        painel2.setBackground(new Color(216, 229, 243));
        painel2.setLayout(painel);
        this.add(painel2);
        
        JLabel titulo = new JLabel("Médico(as) disponíveis");
        
        titulo.setFont(new Font("Calibri", Font.BOLD, 20));
        titulo.setForeground(Color.GRAY);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(20,10,50,10);
        c.weightx = 0;
        c.weighty = 0;
        painel2.add(titulo,c);
        
        ArrayList <Empregado> medicos = sql.getEmpregados("Médico(a)",ID);
        
        DefaultListModel lista = new DefaultListModel();
        
        for(int i=0; i<medicos.size(); i++){

            lista.addElement(medicos.get(i).nome);
        }

        regList = new JList(lista);

        regList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        regList.setVisibleRowCount(10);
        
        ArrayList selecionado = new ArrayList();

        regList.addListSelectionListener(new ListSelectionListener() {
            
            public void valueChanged(ListSelectionEvent e) {

                if (e.getValueIsAdjusting() == false) {

                    escolhido=(regList.getSelectedIndex());
                    revalidate();
                    repaint();
                }
            }
        });
        
        JScrollPane listScrollPane = new JScrollPane(regList);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(20,10,50,10);
        c.weightx = 0;
        c.weighty = 0;
        painel2.add(listScrollPane,c);  

        revalidate();
        repaint();
        
        JButton escolha = new JButton("Selecionar");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(20,10,50,10);
        c.weightx = 0;
        c.weighty = 0;
        
        escolha.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
            
                dispose();
            }
        });
        
        painel2.add(escolha,c);
    }
}
