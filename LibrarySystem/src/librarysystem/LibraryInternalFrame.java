/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package librarysystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JInternalFrame;


import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.*;

/**
 *
 * @author tylnesh
 */
public class LibraryInternalFrame extends javax.swing.JInternalFrame{

    Connection con;
    String url="jdbc:mysql://localhost/library";
    String user="root";
    String pass="19091992";
    String cmd;
    String tab;
    MySimpleTableModel model;
    
    JDesktopPane desktop;
    
    public LibraryInternalFrame(String itab) {
        super("",true, // resizable
					true, // closable
					true, // maximizable
					true);// iconifiable
        
        
        if (isConnected()){
            
            initComponents();
            setVisible(true);
            setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
           tab = itab;
           cmd=getCmd(tab);
            model=new MySimpleTableModel(con);
            //jTable1.setModel(model);
            
        }
    }
    
    private String getCmd(String itab){
        switch(itab){
            case "Vypozicky": return "Select v.idVypozicky,concat(c.Meno,' ',c.Priezvisko) as Citatel,k.Nazov,v.DatumVypozicania,v.DatumVracania,v.Poznamky from Vypozicky as v inner join Citatelia as c on"
                    + "(v.Citatelia_idCitatelia=c.idCitatelia) inner join Knihy as k on (v.Knihy_idKnihy=k.idKnihy)";
            case "Vydavatel": return "Select v.idVydavatel,v.Nazov,concat(m.Nazov,' ') as Mesto from Vydavatel as v inner join Mesto as m on"
                    + "(v.Mesto_idMesto=m.idMesto)"; 
            case "Citatelia":return "Select c.idCitatelia,c.Meno,c.Priezvisko,c.RodneCislo,c.Adresa,concat(m.Nazov,' ') as Mesto,"
                    + "c.TelefoneCislo,c.Email from Citatelia as c inner join Mesto as m on (c.Mesto_idMesto=m.idMesto)"; 
            case "Knihy": return "Select k.idKnihy,k.Nazov,k.ISBN,concat(v.Nazov,' ') as Vydavatel,concat(z.Nazov,' ') as Zaner,"
                    + "k.PocetStran,k.DostupnyPocet,concat(a.Meno,' ',a.Priezvisko) as Autor from Knihy as k inner join Vydavatel as v on (k.Vydavatel_idVydavatel=v.idVydavatel) "
                    + "inner join Zaner as z on (k.Zaner_idZaner=z.idZaner) inner join AutoriKnih on (AutoriKnih.Knihy_idKnihy=k.idKnihy) "
                    + "inner join Autori as a on (AutoriKnih.Autori_idAutori=a.idAutori)";
            
            default: return "Select * from "+itab;
        }
    } 
    
    
    private boolean isConnected(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            return true;
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.toString(), "BadDriver", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(rootPane, ex.toString(), "Connection Failure", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private void initComponents() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
