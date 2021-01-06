
package crud_tugas;
import java.sql.*;

public class koneksi {
    public Connection cc;
    public Statement ss;
    public ResultSet rr;
            
public void Class (){
    
    try {
        Class.forName("com.mysql.jdbc.Driver");
        cc=DriverManager.getConnection("jdbc:mysql://localhost/dbkasir","root","");
        System.out.println("koneksi okey");
        } catch (Exception e){
            System.out.println(e);
            
    }
}  
}
