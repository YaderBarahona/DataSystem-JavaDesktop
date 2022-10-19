package clases;
import java.sql.*;

public class Conexion {
    
    //conexion local a la db
    
    public static Connection db(){
        try{
            
            Connection cn = DriverManager.getConnection( "jdbc:mysql://localhost/db_ds", "root", "");
            
           
            
               return  cn;
        
    }catch(SQLException e) {
        
            System.out.println("Error en la conexion a la db");
    }
        return (null);
    }
    
 
    
}
