package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_SP26 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        JsonArray records = new JsonArray();
        
        try {
        
            if (rs != null) {

                // Get Metadata
                ResultSetMetaData md = rs.getMetaData();
                int columnCount = md.getColumnCount();

                // Iterate through each row of the ResultSet
                while (rs.next()) {
                    
                    JsonObject obj = new JsonObject();

                    // For each column in the current row
                    for (int i = 1; i <= columnCount; i++) {
                        
                        String key = md.getColumnLabel(i);
                        Object value = rs.getObject(i);

                        // convert all values to Strings.
          
                        if (value != null) {
                            obj.put(key, value.toString());
                        } else {
                            obj.put(key, null);
                        }
                    }

                    // Add the row as a JsonObject to our array
                    records.add(obj);
                }
            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}