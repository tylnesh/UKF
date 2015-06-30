/*
 * TODO: Moct upravovať udaje + tabulku s vysledkami (internal frame)
 * 
 */
package librarysystem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Michal Kohútek
 */
public class MySimpleTableModel extends AbstractTableModel {

    ResultSet rs;
    ResultSetMetaData md;
    Connection con;
    private int numCol, numRow;
    String URL, username, pass, database;
    Statement stmt;

    /*public MySimpleTableModel(Connection con) {
        this.con = con;
        try {
            this.stmt = this.con.createStatement();
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        }

    }
*/
   public MySimpleTableModel(Connection con) {
         this.con = con;
        try {
            this.stmt = this.con.createStatement();
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        }
    }

    public void refreshRS(String SQLcmd) {
        //ziskam udaje
        try {
            rs = stmt.executeQuery(SQLcmd);

            //precitam metadata
            md = rs.getMetaData();

            //nastavim premenne 
            numCol = md.getColumnCount();
            rs.last();
            numRow = rs.getRow();


        

        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        }
    }

    @Override
    public int getRowCount() {
        // throw new UnsupportedOperationException("Not supported yet.");
        return numRow;
    }

    @Override
    public int getColumnCount() {
        return numCol;
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
     
            rs.absolute(rowIndex + 1);
            return rs.getObject(columnIndex+1);
        } catch (SQLException ex) {
            System.out.println("SQL Exception: " + ex);
        }
        return null;
    }
    
    @Override
    public String getColumnName(int column) {
        try {
            return md.getColumnName(column+1);
        } catch (Exception e) { System.out.println( e ); }
         return "";
    }
}