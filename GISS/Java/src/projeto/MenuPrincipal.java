/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author brito
 */
public class MenuPrincipal extends JFrame{

    public int ID;
    
    public MenuPrincipal(SqlManager sql){
        
        GridBagLayout painel = new GridBagLayout(); //layout do painel
        GridBagConstraints c = new GridBagConstraints(); //constraints, ou seja, os limites e formato de cada componente
       
        this.setTitle("GISS 1.0"); 
        this.setSize(400,720);  
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
     
        JPanel painel2 = new JPanel(); /*painel que vai ser realmente usado*/
        painel2.setBackground(new Color(216, 229, 243));
        painel2.setLayout(painel);
        this.add(painel2);
        
        ID=-1;
        
        JLabel titulo = new JLabel("GISS");
        
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 60));
        titulo.setForeground(Color.BLACK);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(50,71,50,1);
        c.weightx = 0;
        c.weighty = 0;
        painel2.add(titulo,c);
        
        revalidate();
        repaint();
        
        JLabel subTitulo = new JLabel("Gestão Integrada de Serviços de Saúde");
        subTitulo.setFont(new Font("Calibri Light", Font.ITALIC, 17));
        subTitulo.setForeground(Color.GRAY);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(1,1,50,1);
        c.weightx = 0;
        c.weighty = 0;
        painel2.add(subTitulo,c);
        
        revalidate();
        repaint();
        
        ArrayList<CentroHospitalar> centros = sql.getCentrosHospitalares();
        DefaultListModel lista  = new DefaultListModel();
        
        for(int i=0; i<centros.size(); i++){
            lista.addElement(centros.get(i).nome);
        }
        
        JList regList = new JList(lista);
        regList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        regList.setVisibleRowCount(5);
        
        regList.addListSelectionListener( new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {

                if (e.getValueIsAdjusting() == false) {

                    ID=regList.getSelectedIndex();
                }
            }
        });
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(1,1,50,1);
        c.weightx = 0;
        c.weighty = 0;
        JScrollPane listScrollPane = new JScrollPane(regList);
        painel2.add(listScrollPane,c);
                
        JButton botao = new JButton("Marcações");
    
        botao.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
                
                revalidate();
                repaint();
                            
                if(ID==-1){
                    
                    JOptionPane.showMessageDialog(null, "ID de Centro Hospitalar inválido!");
                }
                
                else{
                
                    Marcacoes marcacoes = new Marcacoes(sql,centros.get(ID).IdCentroHospitalar);                    
                }
            }
        });
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(1,40,50,40);
        c.weightx = 0;
        c.weighty = 0;
        painel2.add(botao,c);
        
        revalidate();
        repaint();
        
        JButton botao2 = new JButton("Consulta");
    
        botao2.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
            
                if(ID==-1){
                    
                    JOptionPane.showMessageDialog(null, "ID de Centro Hospitalar inválido!");
                }
                
                else{

                    Consulta consulta = new Consulta(sql,centros.get(ID).IdCentroHospitalar);                    
                }
            }
        });
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(1,40,50,40);
        c.weightx = 0;
        c.weighty = 0;
        painel2.add(botao2,c);
        
        revalidate();
        repaint();
        
        JButton botao3 = new JButton("Meios Complementares");
    
        botao3.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
            
                if(ID==-1){
                    
                    JOptionPane.showMessageDialog(null, "ID de Centro Hospitalar inválido!");
                }
                
                else{

                    MeiosComplementares meios = new MeiosComplementares(sql,centros.get(ID).IdCentroHospitalar);                    
                }
            }
        });
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(1,40,50,40);
        c.weightx = 0;
        c.weighty = 0;
        painel2.add(botao3,c);
        
        revalidate();
        repaint();
        
        /*c.gridx = 0;
        c.gridy = 5;
        c.insets = new Insets(10,1,30,1);
        c.weightx = 0;
        c.weighty = 0;
        painel2.add(new JLabel(createImageIcon("apple.png","")),c);
        
        revalidate();
        repaint();*/
    }
    
    protected ImageIcon createImageIcon(String path, String description) {
        
        java.net.URL imgURL = getClass().getResource(path);
        if(imgURL!=null){
            return new ImageIcon(imgURL, description);
        } 
        
        else{
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}