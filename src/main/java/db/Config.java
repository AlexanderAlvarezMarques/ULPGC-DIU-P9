package db;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * @author Alexander Álvarez Marques
 * @date 2020-11-30
 * @version 0.1
 */
public abstract class Config {

    private final String nameServer = "mozart.dis.ulpgc.es";
    private final String dbName = " DIU_BD";
    private final String userName; // estudiante-DIU
    private final String password; // DIU-aed56-noi
    private final String url;
    private Connection con;

    public Config(String userName, String password) {
        this.userName = userName;
        this.password = password;
        url = "jdbc:mysql://" + nameServer + "/" + dbName + "?useSSL=true";
    }

    public void startConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, password);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            con  = null;
            showErrorMessage("Credentials not valid.");
        }
    }

    public void closeConnection() {
        try {
            con.close();
            con = null;
        } catch (SQLException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage("Error closing connection.");
        }
    }

    public DatabaseMetaData getMetaData() {
        try {
            return con.getMetaData();
        } catch (SQLException ex) {
            Logger.getLogger(Config.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage("Error getting data from database.");
        }

        return null;
    }

    public boolean isConnected() {
        return con != null;
    }
    
    protected void showErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * @return Return the names of tables in database.
     */
    public abstract List<String> getTables();
    
    /**
     * @param table Table name
     * @return Columns of table.
     */
    public abstract List<String> getColumnsTable (String table);
    
    /**
     * @return A Map where the key is the name of table and the 
     * value is a List with his column names
     */
    public abstract Map<String, List<String>> getDBStructure();
}
