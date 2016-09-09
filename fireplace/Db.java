 

import java.sql.*;
import java.sql.ResultSetMetaData;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
/**
 *
 *@author Paul Love
 *@version 1.0
 * 
 * This class provides a database connection. It is inherited by Classes that
 * represent the tables in the database.
 */
abstract public class Db{
    
    protected String message;
    protected PreparedStatement pStatement;
    protected Statement cStatement;
    protected Connection connection;
    protected ResultSet resultSet;
    /**
     * constructor class that sets some of the variables to null to suppress unwanter errors.
     */
    public Db (){
        message = null;
        resultSet = null;
        connection = null;
    }
    /**
     * trys to locate the database and make a connection with it.
     */
    protected void setConnection(){
        try{
            String driver = "sun.jdbc.odbc.JdbcOdbcDriver";
            String url = "jdbc:odbc:Fire";
            String username = "";
            String password = "";
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        }catch(Exception ex){
            message = "Couldn't connect to db: " + ex.getMessage();
            showError();
        }
    }
    
    /**
     * 
     * @param sql  - a query that is a create, update or delete statement
     * @return - boolean returns whether the query was successful
     */
    
    protected boolean executeUpdate(String sql){
        //boolean update = false;
        try{
            cStatement = connection.createStatement();
            cStatement.executeUpdate(sql); 
            //update = true;
            return true;
        }catch(Exception ex){
            message = "<HTML>Couldn't update db: " + ex.getMessage() + "<br />Last Query: " + sql;
            showError();
            return false;
        }
    }
    
    /**
     * 
     * @param sql - a query for the database using a select statement
     * @return - boolean that returns whether the select was successful.
     */
    
    protected boolean executeQuery(String sql){
        try{
            pStatement = connection.prepareStatement(sql);
            resultSet = pStatement.executeQuery();
            return true;
        }catch(Exception ex){
            message = "<HTML>Couldn't execute query: " + ex.getMessage() + "<br />Last query: " + sql;
            showError();
            return false;
        }
    }
    
    /**
     * 
     * @return - gets the first integer in the resultSet array. he resultSet
     * is a complex return type. This call only requires one piece of data from the database.
     */
    protected int getFirstInt(){
        int number = 0;
        try{
            resultSet.next();
            return resultSet.getInt(1);
        }catch(Exception ex){
            return number;
        }
    }
    
    /**
     * 
     * @return - gets the first string in the resultSet array. The resultSet
     * is a complex return type. This call only requires one piece of data from the database.
     */
    protected String getFirstString(){
        try{
            resultSet.next();
            return resultSet.getString(1);
        }catch(Exception ex){
            message = "<HTML>Couldn't get first string " + ex.getMessage();
            showError();
            return "";
        }
    }
    /**
     * 
     * @return - gets any message stored in the message attribute
     */
    
    protected String getMessage(){
        return message;
    }
    /**
     * attempts to close the connection to the database and catches any errors
     */
    protected void closeConnection(){
        if(connection != null){
            try{
               connection.close();
            }catch(Exception ex){
                message = new String("Couldn't close connection" + ex.getMessage());
                showError();
            }
        }
    }
    
    /**
     * creates a JOptionPane with an error icon and returns the String from the message attribute
     */
    protected void showError(){
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * creates a JOptionPane with the string stored in the message variable
     */
    
    protected void showInformation(){
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * 
     * @param sql - query string that gets one column of strings from the database
     * @return - List object with the return from the database. One of its functions
     * is to return the list of the suppliers from the database.
     */
    protected List<String> getStringArrayOfResultSet(String sql){
        List<String> array = new ArrayList();
        
        this.setConnection();
        this.executeQuery(sql);
        try{
            while(resultSet.next()){
                array.add(resultSet.getString(1));
            }     
            
        }catch(Exception ex){
            array = null;
            this.message = "<HTML>Could get string array: " + ex.getMessage() + "<br>Last Query: " + sql;
            this.showError();
        }
        this.closeConnection();
        return array;
    }
    /**
     * 
     * @param sql - a query string that will be populated into a JTable
     * @return - JTable a basic table with the data returned from the database
     */
    protected JTable getTable(String sql){
        
        JTable table = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel();
        table.setModel(tableModel);
        
        setConnection();
        if(executeQuery(sql)){
            //if true, it means that the resultSet is loaded.
            try{
                //supplierObjectArray
                ResultSetMetaData meta = resultSet.getMetaData();
                int columns = meta.getColumnCount();
                String[] colNames = new String[columns];
                
                for (int i = 1; i <= columns; i++) {
                  colNames[i - 1] = meta.getColumnName(i);
                }
                tableModel.setColumnIdentifiers(colNames);
                tableModel.addRow(colNames);
                while(resultSet.next()){
                    
                    String[] rowData = new String[columns];
                    for (int i = 1; i <= columns; i++) {
                      rowData[i - 1] = resultSet.getString(i);
                    }
                    tableModel.addRow(rowData);
                }
                
            }catch(Exception ex){
                message = "Couldn't make a table model " + ex.getMessage();
                showError();
            }
        }else{
            message = new String("Couldn't execute query");
            showError();
        }
        
        return table;
    }
    
}
