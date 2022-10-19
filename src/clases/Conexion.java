package clases;
import java.sql.*;

public class Conexion {
    
    //conexion local a la db
    
    public static Connection db(){
        try{
            
            Connection cn = DriverManager.getConnection( "jdbc:mysql://mysql5025.site4now.net/db_a77861_dbds", "a77861_dbds", "29Dejunio.");
            
           
            
               return  cn;
        
    }catch(SQLException e) {
        
            System.out.println("Error en la conexion a la db"+e);
    }
        return (null);
    }
    
 
    
}
