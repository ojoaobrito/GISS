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
public class Marcacoes extends JFrame{
    
    public String dataI = "";
    public String dataF = "";
    public int selecionado;
    public int selecionado2;
    public int selecionado3;
    public int selecionado4;
    int escolhido;
    int salaS;
    String tipo;
    public JScrollPane listScrollPane;
    public JScrollPane listScrollPane2;
    public JScrollPane listScrollPane3;
    public JScrollPane listScrollPane4;
    public JScrollPane listScrollPane5;
    ArrayList <Empregado> medicos;
    ArrayList <Empregado> enfermeiros;
    ArrayList <Empregado> tecnicos;
    ArrayList <Sala> salas;
    JLabel rotulo;
    JLabel rotulo2;
    ArrayList <Empregado> colaboradores;
    ArrayList <Empregado> equipa;
    
    public Marcacoes(SqlManager sql, int ID){
        
        GridBagLayout painel = new GridBagLayout(); //layout do painel
        GridBagConstraints c = new GridBagConstraints(); //constraints, ou seja os limites e formato de cada componente
       
        this.setTitle("Marcações"); 
        this.setSize(500,1025);  
        this.setVisible(true);
        
        JTabbedPane tp = new JTabbedPane();
        this.add(tp);
     
        JPanel painel2 = new JPanel(); /*painel que vai ser realmente usado*/
        painel2.setBackground(new Color(216, 229, 243));
        painel2.setLayout(painel);
        
        tp.add("Horários",painel2);
        /*tp.setIconAt(0, createImageIcon("1.png", ""));*/
        
        JPanel painel3 = new JPanel(); /*painel que vai ser realmente usado*/
        painel3.setBackground(new Color(216, 229, 243));
        painel3.setLayout(painel);
        
        tp.add("Agendamentos",painel3);
        /*tp.setIconAt(0, createImageIcon("1.png", ""));*/        
               
        selecionado = -1;
        selecionado2 = -1;
        selecionado3 = -1;
        selecionado4 = -1;
        escolhido = -1;
        equipa = new ArrayList<Empregado>();
        salaS=0;
                
        JLabel titulo = new JLabel("Inserir dados");        
        titulo.setFont(new Font("Calibri", Font.BOLD, 19));
        titulo.setForeground(Color.GRAY);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 3;
        c.insets = new Insets(-25,10,70,10);
        c.weighty = 0;
        painel2.add(titulo,c);
        
        JTextField semana = new JTextField("Semana (1-4)");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(50,10,60,10);
        c.weightx = 0;
        c.weighty = 0;
        painel2.add(semana,c);
        
        JTextField mes = new JTextField("Mês (mm)");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(105,10,55,10);
        c.weightx = 0;
        c.weighty = 0;
        painel2.add(mes,c);
        
        JTextField ano = new JTextField("Ano (aaaa)");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(155,10,45,10);
        c.weightx = 0;
        c.weighty = 0;
        painel2.add(ano,c);        
                
        JButton botao = new JButton("Pesquisar");
        
        JList regList = new JList();
        regList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        regList.setVisibleRowCount(5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10,10,0,10);
        c.weightx = 1;
        c.weighty = 0;
        listScrollPane = new JScrollPane(regList);
        painel2.add(listScrollPane,c);
        
        JList regList2 = new JList();
        regList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        regList2.setVisibleRowCount(5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10,10,0,10);
        c.weightx = 1;
        c.weighty = 0;
        listScrollPane2 = new JScrollPane(regList2);
        painel2.add(listScrollPane2,c);
        
        JList regList3 = new JList();
        regList3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        regList3.setVisibleRowCount(5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(10,10,0,10);
        c.weightx = 1;
        c.weighty = 0;
        listScrollPane3 = new JScrollPane(regList3);
        painel2.add(listScrollPane3,c);
        
        JList regList5 = new JList();
        regList5.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        regList5.setVisibleRowCount(5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(10,10,0,10);
        c.weightx = 1;
        c.weighty = 0;
        listScrollPane5 = new JScrollPane(regList5);
        painel2.add(listScrollPane5,c);
        
        botao.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
                
                dataI="";
                dataF="";
                
                SwingUtilities.updateComponentTreeUI(painel2);
                
                if(semana.getText().equals("1")){
                    
                    dataI = "01-"+mes.getText()+"-"+ano.getText();
                    dataF = "07-"+mes.getText()+"-"+ano.getText();
                }

                else if(semana.getText().equals("2")){
                    
                    dataI = "08-"+mes.getText()+"-"+ano.getText();
                    dataF = "14-"+mes.getText()+"-"+ano.getText();
                }

                else if(semana.getText().equals("3")){
                    
                    dataI = "15-"+mes.getText()+"-"+ano.getText();
                    dataF = "21-"+mes.getText()+"-"+ano.getText();
                }

                else if(semana.getText().equals("4")){

                    dataI = "22-"+mes.getText()+"-"+ano.getText();
                    dataF = "31-"+mes.getText()+"-"+ano.getText();
                }
                
                if(semana.getText().equals("Semana (1-4)") || semana.getText().equals("") || Integer.parseInt(semana.getText())<1 || Integer.parseInt(semana.getText())>4 || mes.getText().equals("Mês (mm)") || mes.getText().equals("") || Integer.parseInt(mes.getText())<1 || Integer.parseInt(mes.getText())>12 || ano.getText().equals("Ano (aaaa)") || ano.getText().equals("") || Integer.parseInt(ano.getText())<2018 || Integer.parseInt(ano.getText())>9999){
                    
                    JOptionPane.showMessageDialog(null, "Dados inválidos!");
                }
                
                else{
                                
                    painel2.remove(listScrollPane);
                    painel2.remove(listScrollPane2);
                    painel2.remove(listScrollPane3);
                    painel2.remove(listScrollPane5);
                    
                    DefaultListModel listaM = new DefaultListModel();
                    medicos = sql.getEmpregadosSemanal("Médico(a)",ID,dataI,dataF);

                    for(int i=0; i<medicos.size(); i++){                        
                        
                        listaM.addElement(medicos.get(i).nome);
                    }

                    JList regList = new JList(listaM);

                    regList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    regList.setVisibleRowCount(5);

                    regList.addListSelectionListener( new ListSelectionListener() {

                        public void valueChanged(ListSelectionEvent e) {

                            if (e.getValueIsAdjusting() == false) {

                                selecionado=regList.getSelectedIndex();
                            }
                        }
                    });

                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = 1;
                    c.insets = new Insets(10,10,0,10);
                    c.weightx = 1;
                    c.weighty = 0;

                    listScrollPane = new JScrollPane(regList);
                    painel2.add(listScrollPane,c);
                    
                    DefaultListModel listaE = new DefaultListModel();
                    enfermeiros = sql.getEmpregadosSemanal("Enfermeiro(a)",ID,dataI,dataF);

                    for(int i=0; i<enfermeiros.size(); i++){

                        listaE.addElement(enfermeiros.get(i).nome);
                    }

                    JList regList2 = new JList(listaE);

                    regList2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    regList2.setVisibleRowCount(5);

                    regList2.addListSelectionListener( new ListSelectionListener() {

                        public void valueChanged(ListSelectionEvent e) {

                            if (e.getValueIsAdjusting() == false) {

                                selecionado2=regList2.getSelectedIndex();                                
                            }
                        }
                    });

                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = 2;
                    c.insets = new Insets(10,10,0,10);
                    c.weightx = 1;
                    c.weighty = 0;

                    listScrollPane2 = new JScrollPane(regList2);
                    painel2.add(listScrollPane2,c);
                    
                    DefaultListModel listaT = new DefaultListModel();
                    tecnicos = sql.getEmpregadosSemanal("Técnico(a)",ID,dataI,dataF);

                    for(int i=0; i<tecnicos.size(); i++){

                        listaT.addElement(tecnicos.get(i).nome);
                    }

                    JList regList3 = new JList(listaT);

                    regList3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    regList3.setVisibleRowCount(5);

                    regList3.addListSelectionListener( new ListSelectionListener() {

                        public void valueChanged(ListSelectionEvent e) {

                            if (e.getValueIsAdjusting() == false) {

                                selecionado3=regList3.getSelectedIndex();                                
                            }
                        }
                    });

                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = 3;
                    c.insets = new Insets(10,10,0,10);
                    c.weightx = 1;
                    c.weighty = 0;

                    listScrollPane3 = new JScrollPane(regList3);
                    painel2.add(listScrollPane3,c);
                    
                    DefaultListModel listaS = new DefaultListModel();
                    salas = sql.getSalas(ID,dataI,dataF);
                    
                    for(int i=0; i<salas.size(); i++){

                        Hospital hospital = sql.getHospital(ID,salas.get(i).IdHospital);
                        AreaClinica area = sql.getArea(ID,salas.get(i).IdArea);
                        
                        listaS.addElement(salas.get(i).IdSala +"   " +hospital.nome +"   " +area.nome);
                    }
                    
                    JList regList6 = new JList(listaS);

                    regList6.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                    regList6.setVisibleRowCount(5);

                    regList6.addListSelectionListener( new ListSelectionListener() {

                        public void valueChanged(ListSelectionEvent e) {

                            if (e.getValueIsAdjusting() == false) {

                                selecionado4=regList6.getSelectedIndex();                                
                            }
                        }
                    });

                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = 4;
                    c.insets = new Insets(10,10,0,10);
                    c.weightx = 1;
                    c.weighty = 0;

                    listScrollPane5 = new JScrollPane(regList6);
                    painel2.add(listScrollPane5,c);
                    
                    SwingUtilities.updateComponentTreeUI(painel2);
                    
                }
            }
        });
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        c.insets = new Insets(105,10,55,10);
        c.weightx = 0;
        c.weighty = 0;        
        painel2.add(botao,c);
             
        JLabel titulo2 = new JLabel("Médicos(as)");
        
        titulo2.setFont(new Font("Calibri", Font.BOLD, 19));
        titulo2.setForeground(Color.GRAY);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(10,10,150,10);
        c.weightx = 0;
        c.weighty = 0;
        painel2.add(titulo2,c);        
                
        JLabel titulo3 = new JLabel("Enfermeiros(as)");
        
        titulo3.setFont(new Font("Calibri", Font.BOLD, 19));
        titulo3.setForeground(Color.GRAY);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10,10,150,10);
        c.weightx = 0;
        c.weighty = 0;
        painel2.add(titulo3,c);        
               
        JLabel titulo4 = new JLabel("Técnicos(as)");
        
        titulo4.setFont(new Font("Calibri", Font.BOLD, 19));
        titulo4.setForeground(Color.GRAY);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.insets = new Insets(10,10,150,10);
        c.weightx = 0;
        c.weighty = 0;
        painel2.add(titulo4,c);
        
        JLabel titulo8 = new JLabel("Salas");
        
        titulo8.setFont(new Font("Calibri", Font.BOLD, 19));
        titulo8.setForeground(Color.GRAY);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.insets = new Insets(10,10,150,10);
        c.weightx = 0;
        c.weighty = 0;
        painel2.add(titulo8,c);
        
        JButton botao2 = new JButton("Ver detalhes");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(10,10,0,10);
        c.weightx = 0;
        c.weighty = 0;
        
        botao2.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
                    
                if(selecionado>=0){
                    
                    ArrayList <Ocorrencia> ocorrencias = sql.getOcorrenciasEmpregado(medicos.get(selecionado).IdEmpregado, ID, dataI, dataF);
                    String s = "";
                    
                    for(int i=0; i<ocorrencias.size(); i++){
                        
                        Utente utente = sql.getUtente(ocorrencias.get(i).IdUtente, ID);
                        s = (s +"(" +ocorrencias.get(i).data +" - " +ocorrencias.get(i).hora +"h)   "+ocorrencias.get(i).tipoOcorrencia +" (ID " +ocorrencias.get(i).IdOcorrencia +")   " +utente.nome +" (ID " +utente.IdUtente +")   Sala " +ocorrencias.get(i).sala  +"   -   " +ocorrencias.get(i).estado +"\n");
                    }                                    
                     
                    JOptionPane.showMessageDialog(null, s);
                }
            }
        });
        
        painel2.add(botao2,c);
        
        JButton botao3 = new JButton("Ver detalhes");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(10,10,0,10);
        c.weightx = 0;
        c.weighty = 0;
        
        botao3.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
                    
                if(selecionado2>=0){
                    
                    ArrayList <Ocorrencia> ocorrencias = sql.getOcorrenciasEmpregado(enfermeiros.get(selecionado2).IdEmpregado, ID, dataI, dataF);
                    String s = "";
                    
                    for(int i=0; i<ocorrencias.size(); i++){
                        
                        Utente utente = sql.getUtente(ocorrencias.get(i).IdUtente, ID);
                        s = (s +"(" +ocorrencias.get(i).data +" - " +ocorrencias.get(i).hora +"h)   "+ocorrencias.get(i).tipoOcorrencia +" (ID " +ocorrencias.get(i).IdOcorrencia +")   " +utente.nome +" (ID " +utente.IdUtente +")   Sala " +ocorrencias.get(i).sala  +"   -   " +ocorrencias.get(i).estado +"\n");
                    }                                    
                     
                    JOptionPane.showMessageDialog(null, s);
                }
            }
        });
        
        painel2.add(botao3,c);
        
        JButton botao4 = new JButton("Ver detalhes");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        c.insets = new Insets(10,10,0,10);
        c.weightx = 0;
        c.weighty = 0;
        
        botao4.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
                    
                if(selecionado3>=0){
                    
                    ArrayList <Ocorrencia> ocorrencias = sql.getOcorrenciasEmpregado(tecnicos.get(selecionado3).IdEmpregado, ID, dataI, dataF);
                    String s = "";
                    
                    for(int i=0; i<ocorrencias.size(); i++){
                        
                        Utente utente = sql.getUtente(ocorrencias.get(i).IdUtente, ID);
                        s = (s +"(" +ocorrencias.get(i).data +" - " +ocorrencias.get(i).hora +"h)   "+ocorrencias.get(i).tipoOcorrencia +" (ID " +ocorrencias.get(i).IdOcorrencia +")   " +utente.nome +" (ID " +utente.IdUtente +")   Sala " +ocorrencias.get(i).sala  +"   -   " +ocorrencias.get(i).estado +"\n");
                    }                                    
                     
                    JOptionPane.showMessageDialog(null, s);
                }
            }
        });
        
        painel2.add(botao4,c);
        
        JButton botao5 = new JButton("Ver detalhes");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        c.insets = new Insets(10,10,0,10);
        c.weightx = 0;
        c.weighty = 0;
        
        botao5.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e){
                    
                if(selecionado4>=0){
                    
                    ArrayList <Ocorrencia> ocorrencias = sql.getOcorrenciasSala(salas.get(selecionado4).IdSala, ID, dataI, dataF);
                    String s = "";
                    
                    for(int i=0; i<ocorrencias.size(); i++){
                        
                        Utente utente = sql.getUtente(ocorrencias.get(i).IdUtente, ID);
                        s = (s +"(" +ocorrencias.get(i).data +" - " +ocorrencias.get(i).hora +"h)   "+ocorrencias.get(i).tipoOcorrencia +" (ID " +ocorrencias.get(i).IdOcorrencia +")   " +utente.nome +" (ID " +utente.IdUtente +")   -   " +ocorrencias.get(i).estado +"\n");
                    }                                    
                     
                    JOptionPane.showMessageDialog(null, s);
                }
            }
        });
        
        painel2.add(botao5,c);
                    
        JLabel titulo5 = new JLabel("Tipo");
        
        titulo5.setFont(new Font("Calibri", Font.BOLD, 19));
        titulo5.setForeground(Color.GRAY);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(-80,-50,200,10);
        c.weightx = 0;
        c.weighty = 0;
        painel3.add(titulo5,c);
        
        rotulo = new JLabel("");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(200,-50,200,10);
        c.weightx = 0;
        c.weighty = 0;
        painel3.add(rotulo,c);
        
        JList regList4 = new JList();
        regList4.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        regList4.setVisibleRowCount(5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(-156,-50,-30,10);
        c.weightx = 0;
        c.weighty = 1;
        listScrollPane4 = new JScrollPane(regList4);
        painel3.add(listScrollPane4,c);
        
        JButton consulta = new JButton("Consulta");
                       
        consulta.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                
                painel3.remove(listScrollPane4);
                painel3.remove(rotulo);
                painel3.remove(rotulo2);
                rotulo2 = new JLabel("Equipa:");
                rotulo2.setFont(new Font("Calibri", Font.BOLD, 12));
                rotulo2.setForeground(Color.GRAY);
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = 1;
                c.insets = new Insets(20,-50,-30,10);
                c.weightx = 0;
                c.weighty = 0;
                painel3.add(rotulo2,c);
                
                revalidate();
                repaint();
                
                equipa=new ArrayList<Empregado>();
                
                tipo="Consulta";
                rotulo = new JLabel("Selecionado:   Consulta");
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = 0;
                c.insets = new Insets(200,-50,200,10);
                c.weightx = 0;
                c.weighty = 0;
                painel3.add(rotulo,c);
                                                
                colaboradores = sql.getEmpregados("Médico(a)",ID);        
                DefaultListModel lista = new DefaultListModel();

                for(int i=0; i<colaboradores.size(); i++){

                    lista.addElement("Médico(a)   " +colaboradores.get(i).nome + "                                                ");
                }
                
                ArrayList <Empregado> enfermeiros = sql.getEmpregados("Enfermeiro(a)",ID);
                
                for(int i=0; i<enfermeiros.size(); i++){

                    colaboradores.add(enfermeiros.get(i));
                    lista.addElement("Enfermeiro(a)   " +enfermeiros.get(i).nome + "                                            ");
                }
                
                JList regList = new JList(lista);

                regList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                regList.setVisibleRowCount(5);

                regList.addListSelectionListener(new ListSelectionListener() {

                    public void valueChanged(ListSelectionEvent e) {

                        if (e.getValueIsAdjusting() == false) {

                            escolhido=(regList.getSelectedIndex());
                            revalidate();
                            repaint();
                        }
                    }
                });

                listScrollPane4 = new JScrollPane(regList);
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = 1;
                c.insets = new Insets(-156,-50,-30,10);
                c.weightx = 0;
                c.weighty = 1;
                listScrollPane4 = new JScrollPane(regList);
                painel3.add(listScrollPane4,c);
                SwingUtilities.updateComponentTreeUI(painel3);
            }
        });
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(20,-50,200,10);
        c.weightx = 0;
        c.weighty = 0;
        painel3.add(consulta,c);
        
        JLabel titulo6 = new JLabel("Colaboradores disponíveis");
        
        titulo6.setFont(new Font("Calibri", Font.BOLD, 19));
        titulo6.setForeground(Color.GRAY);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(200,-50,0,10);
        c.weightx = 0;
        c.weighty = 0;
        painel3.add(titulo6,c);
        
        JButton detalhes = new JButton("Ver detalhes");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(-100,10,-30,-60);
        c.weightx = 0;
        c.weighty = 0;
        
        detalhes.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                
                if(escolhido>=0){
                    String s="";
                    int k=1;
                    ArrayList especializacoes = sql.getEspecializacoes(colaboradores.get(escolhido).IdEmpregado);
                    s=("ID:   " +colaboradores.get(escolhido).IdEmpregado +"\nNome:   " +colaboradores.get(escolhido).nome +"\nFunção:   " +colaboradores.get(escolhido).funcao);
                    
                    for(int i=0; i<especializacoes.size(); i++){

                        if(k==1){

                            s = (s + "\nEspecializações:   ");
                            k=0;
                        }

                        s = (s + especializacoes.get(i));
                    }

                    JOptionPane.showMessageDialog(null, s);
                    
                }
            }
        });
        
        painel3.add(detalhes,c);
        
        JButton adicionar = new JButton("Adicionar");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(-220,10,-30,-60);
        c.weightx = 0;
        c.weighty = 0;
                
        adicionar.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                
                if(escolhido<0){
                    
                    JOptionPane.showMessageDialog(null, "Escolha corretamente um colaborador!");
                }
                
                else{
                    
                    revalidate();
                    repaint();
                
                    Empregado empregado = sql.getEmpregado(colaboradores.get(escolhido).IdEmpregado, ID);

                    if(equipa.contains(empregado)){

                        JOptionPane.showMessageDialog(null, "Colaborador já adicionado!");
                    }

                    else{
                        
                        equipa.add(empregado);
                    }
                    
                    painel3.remove(rotulo2);
                    String s = "Equipa:   ";
                    
                    for(int i=0; i<equipa.size(); i++){
                        
                        s = (s + "(" +equipa.get(i).funcao.charAt(0) +") " +equipa.get(i).nome +"   ");
                    }
                    
                    rotulo2 = new JLabel(s);
                    rotulo2.setFont(new Font("Calibri", Font.BOLD, 12));
                    rotulo2.setForeground(Color.GRAY);
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = 1;
                    c.insets = new Insets(20,-50,-30,10);
                    c.weightx = 0;
                    c.weighty = 0;
                    painel3.add(rotulo2,c);
                    SwingUtilities.updateComponentTreeUI(painel2);
                }
            }
        });
        
        painel3.add(adicionar,c);
        
        rotulo2 = new JLabel("Equipa:");
        rotulo2.setFont(new Font("Calibri", Font.BOLD, 14));
        rotulo2.setForeground(Color.GRAY);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.insets = new Insets(20,-50,-30,10);
        c.weightx = 0;
        c.weighty = 0;
        painel3.add(rotulo2,c);
        
        JButton remover = new JButton("Remover");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        c.insets = new Insets(-160,10,-30,-60);
        c.weightx = 0;
        c.weighty = 0;
        
        remover.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                
                if(escolhido<0){
                    
                    JOptionPane.showMessageDialog(null, "Escolha corretamente um colaborador!");
                }
                
                else{
                
                    revalidate();
                    repaint();
                    
                    Empregado empregado = sql.getEmpregado(colaboradores.get(escolhido).IdEmpregado, ID);
                    
                    if(!(equipa.contains(empregado))){
                        
                        System.out.println(equipa.get(escolhido).nome);
                        JOptionPane.showMessageDialog(null, "Este colaborador ainda não foi adicionado!");
                    }

                    else{

                        equipa.remove(empregado);
                    }
                    
                    painel3.remove(rotulo2);
                    String s = "Equipa:   ";
                    
                    for(int i=0; i<equipa.size(); i++){
                        
                        s = (s + "(" +equipa.get(i).funcao.charAt(0) +") " +equipa.get(i).nome +"   ");
                    }
                    
                    rotulo2 = new JLabel(s);
                    rotulo2.setFont(new Font("Calibri", Font.BOLD, 12));
                    rotulo2.setForeground(Color.GRAY);
                    c.fill = GridBagConstraints.HORIZONTAL;
                    c.gridx = 0;
                    c.gridy = 1;
                    c.insets = new Insets(50,-50,-30,10);
                    c.weightx = 0;
                    c.weighty = 0;
                    painel3.add(rotulo2,c);
                    SwingUtilities.updateComponentTreeUI(painel2);
                }
            }
        });
        
        painel3.add(remover,c);
        
        JButton analises = new JButton("Análises");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(80,-50,200,10);
        c.weightx = 0;
        c.weighty = 0;
               
        analises.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                
                painel3.remove(listScrollPane4);
                painel3.remove(rotulo);
                painel3.remove(rotulo2);
                rotulo2 = new JLabel("Equipa:");
                rotulo2.setFont(new Font("Calibri", Font.BOLD, 12));
                rotulo2.setForeground(Color.GRAY);
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = 1;
                c.insets = new Insets(20,-50,-30,10);
                c.weightx = 0;
                c.weighty = 0;
                painel3.add(rotulo2,c);
                
                revalidate();
                repaint();
                
                equipa=new ArrayList<Empregado>();
                
                tipo="Análises";
                rotulo = new JLabel("Selecionado:   Análises");
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = 0;
                c.insets = new Insets(200,-50,200,10);
                c.weightx = 0;
                c.weighty = 0;
                painel3.add(rotulo,c);
                
                colaboradores = sql.getEmpregados("Técnico(a)",ID);        
                DefaultListModel lista = new DefaultListModel();

                for(int i=0; i<colaboradores.size(); i++){

                    lista.addElement("Técnico(a)   " +colaboradores.get(i).nome + "                                               ");
                }

                JList regList = new JList(lista);

                regList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                regList.setVisibleRowCount(5);

                regList.addListSelectionListener(new ListSelectionListener() {

                    public void valueChanged(ListSelectionEvent e) {

                        if (e.getValueIsAdjusting() == false) {

                            escolhido=(regList.getSelectedIndex());
                            revalidate();
                            repaint();
                        }
                    }
                });

                listScrollPane4 = new JScrollPane(regList);
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = 1;
                c.insets = new Insets(-140,-50,-30,10);
                c.weightx = 0;
                c.weighty = 1;
                listScrollPane4 = new JScrollPane(regList);
                painel3.add(listScrollPane4,c);
                SwingUtilities.updateComponentTreeUI(painel3);
            }
        });
        
        painel3.add(analises,c);
        
        JButton exame = new JButton("Exame");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(140,-50,200,10);
        c.weightx = 0;
        c.weighty = 0;
                
        exame.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
                
                painel3.remove(listScrollPane4);
                painel3.remove(rotulo);
                painel3.remove(rotulo2);
                rotulo2 = new JLabel("Equipa:");
                rotulo2.setFont(new Font("Calibri", Font.BOLD, 12));
                rotulo2.setForeground(Color.GRAY);
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = 1;
                c.insets = new Insets(20,-50,-30,10);
                c.weightx = 0;
                c.weighty = 0;
                painel3.add(rotulo2,c);
                
                revalidate();
                repaint();
                
                equipa=new ArrayList<Empregado>();
                
                tipo="Exame";
                rotulo = new JLabel("Selecionado:   Exame");
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = 0;
                c.insets = new Insets(200,-50,200,10);
                c.weightx = 0;
                c.weighty = 0;
                painel3.add(rotulo,c);
                
                colaboradores = sql.getEmpregados("Técnico(a)",ID);       
                DefaultListModel lista = new DefaultListModel();

                for(int i=0; i<colaboradores.size(); i++){

                    lista.addElement("Técnico(a)   " +colaboradores.get(i).nome + "                                               ");
                }

                JList regList = new JList(lista);

                regList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                regList.setVisibleRowCount(5);

                regList.addListSelectionListener(new ListSelectionListener() {

                    public void valueChanged(ListSelectionEvent e) {

                        if (e.getValueIsAdjusting() == false) {

                            escolhido=(regList.getSelectedIndex());
                            revalidate();
                            repaint();
                        }
                    }
                });

                listScrollPane4 = new JScrollPane(regList);
                c.fill = GridBagConstraints.HORIZONTAL;
                c.gridx = 0;
                c.gridy = 1;
                c.insets = new Insets(-140,-50,-30,10);
                c.weightx = 0;
                c.weighty = 1;
                listScrollPane4 = new JScrollPane(regList);
                painel3.add(listScrollPane4,c);
                SwingUtilities.updateComponentTreeUI(painel3);
            }
        });
        
        painel3.add(exame,c);
        
        JLabel titulo7 = new JLabel("Dados adicionais");
        
        titulo7.setFont(new Font("Calibri", Font.BOLD, 19));
        titulo7.setForeground(Color.GRAY);
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(-180,-50,50,10);
        c.weightx = 0;
        c.weighty = 0;
        painel3.add(titulo7,c);
                        
        JTextField data = new JTextField("Data (dd-mm-aaaa)");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(-60,-50,50,10);
        c.weightx = 0;
        c.weighty = 0;
        painel3.add(data,c);
        
        JTextField hora = new JTextField("Hora");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(10,-50,50,10);
        c.weightx = 0;
        c.weighty = 0;
        painel3.add(hora,c);
        
        JTextField id = new JTextField("ID de Utente");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(80,-50,50,10);
        c.weightx = 0;
        c.weighty = 0;
        painel3.add(id,c);
        
        JTextField sala = new JTextField("Sala");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(150,-50,50,10);
        c.weightx = 0;
        c.weighty = 0;
        painel3.add(sala,c);
        
        JTextField idConsulta = new JTextField("ID de Consulta (opcional)");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(220,-50,50,10);
        c.weightx = 0;
        c.weighty = 0;
        painel3.add(idConsulta,c);
        
        JTextField liga = new JTextField("ID de Consulta Original (opcional)");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(290,-50,50,10);
        c.weightx = 0;
        c.weighty = 0;
        painel3.add(liga,c);
        
        JTextField motivo = new JTextField("Motivo (opcional)");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.insets = new Insets(360,-50,50,10);
        c.weightx = 0;
        c.weighty = 0;
        painel3.add(motivo,c);
        
        JButton guardar = new JButton("Guardar");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        c.insets = new Insets(98,10,0,-60);
        c.weightx = 0;
        c.weighty = 0;
        
        guardar.addActionListener(new ActionListener(){
            
            public void actionPerformed(ActionEvent e){
            
                if(data.getText().equals("Data (dd-mm-aaaa)") || data.getText().equals("") || hora.getText().equals("Hora") || hora.getText().equals("") || id.getText().equals("ID de Utente") || id.getText().equals("")){
                    
                    JOptionPane.showMessageDialog(null, "Dados inválidos!");
                }
                
                else{
                
                    if(sala.getText().equals("Sala")==false || sala.getText().equals("")==false){

                        salaS=(Integer.parseInt(sala.getText()));
                    }

                    if(idConsulta.getText().equals("ID de Consulta (opcional)")==false && idConsulta.getText().equals("")==false){                                     

                        if(liga.getText().equals("ID de Consulta Original (opcional)")==false && liga.getText().equals("")==false && motivo.getText().equals("Motivo")==false && motivo.getText().equals("")==false){

                            Ocorrencia ocorrencia = new Ocorrencia(Integer.parseInt(idConsulta.getText()),Integer.parseInt(id.getText()),ID,tipo,Integer.parseInt(hora.getText()),data.getText(),"Agendada",salaS,"");
                            sql.insertOcorrencia(ocorrencia, ID, equipa, Integer.parseInt(liga.getText()), motivo.getText());
                        }

                        else{

                            Ocorrencia ocorrencia = new Ocorrencia(Integer.parseInt(idConsulta.getText()),Integer.parseInt(id.getText()),ID,tipo,Integer.parseInt(hora.getText()),data.getText(),"Agendada",salaS,"");
                            sql.insertOcorrencia(ocorrencia, ID, equipa, 0, null);
                        }
                    }

                    else if(idConsulta.getText().equals("ID de Consulta (opcional)") || idConsulta.getText().equals("")){

                        if(liga.getText().equals("ID de Consulta Original (opcional)")==false && liga.getText().equals("")==false && motivo.getText().equals("Motivo")==false && motivo.getText().equals("")==false){

                            Ocorrencia ocorrencia = new Ocorrencia(0,Integer.parseInt(id.getText()),ID,tipo,Integer.parseInt(hora.getText()),data.getText(),"Agendada",salaS,"");
                            sql.insertOcorrencia(ocorrencia, ID, equipa, Integer.parseInt(liga.getText()), motivo.getText());
                        }

                        else{

                            Ocorrencia ocorrencia = new Ocorrencia(0,Integer.parseInt(id.getText()),ID,tipo,Integer.parseInt(hora.getText()),data.getText(),"Agendada",salaS,"");
                            sql.insertOcorrencia(ocorrencia, ID, equipa, 0, null);
                        }
                    }

                    else{

                        Ocorrencia ocorrencia = new Ocorrencia(0,Integer.parseInt(id.getText()),ID,tipo,Integer.parseInt(hora.getText()),data.getText(),"Agendada",salaS,"");
                        sql.insertOcorrencia(ocorrencia, ID, equipa, 0, null);
                    }
                }
            }            
        });
        
        painel3.add(guardar,c);
               
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
