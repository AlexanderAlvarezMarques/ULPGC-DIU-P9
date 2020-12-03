package db;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Alexander Álvarez Marques
 * @date 2020-11-30
 * @version 0.1
 */
public class Utils extends Config {

    public Utils(String userName, String password) {
        super(userName, password);
    }

    @Override
    public List<String> getTables() {
        
        List<String> tables = new ArrayList<>();
        
        DatabaseMetaData md = getMetaData();
        
        try {
            ResultSet tablesSet = md.getTables(null, null, "%", new String [] {"TABLE"});
            
            while(tablesSet.next()) {
                tables.add(tablesSet.getString("TABLE_NAME"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage("Error loading tables.");
        }
        
        return tables;
    }

    @Override
    public List<String> getColumnsTable (String table) {
        
        List<String> columns = new ArrayList<>();
        
        DatabaseMetaData md = getMetaData();
        
        try {
            ResultSet columnsSet = md.getColumns(null, null, table, null);
            
            while(columnsSet.next()) {
                columns.add(columnsSet.getString("COLUMN_NAME"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Utils.class.getName()).log(Level.SEVERE, null, ex);
            showErrorMessage("Error loading columns.");
        }
        
        return columns;
        
    }
    
    @Override
    public Map<String, List<String>> getDBStructure() {
        
        Map<String, List<String>> data = new HashMap<>();
        
        getTables().forEach(table -> {
            data.put(table, getColumnsTable(table));
        });
        
        return data;
    }
}
