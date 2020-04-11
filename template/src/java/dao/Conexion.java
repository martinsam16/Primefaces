package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.h2.tools.DeleteDbFiles;

/**
 *
 * @author MartinSaman
 */
public class Conexion {

    protected static Connection conexion = null;

    public static Connection conectar() throws Exception {
        try {

            //H2
            Class.forName("org.h2.Driver");
            conexion = DriverManager.getConnection(
                    "jdbc:h2:~/demo",
                    "admin",
                    "admin");

            //SQL Server
            /*
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexion = DriverManager.getConnection(
                    "jdbc:sqlserver://ipServer;database=NombreDatabase",
                    "usuario",
                    "psswrd");
             */
            //Oracle 
            /*
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            conexion = DriverManager.getConnection(
                    "jdbc:oracle:thin:@ipServer:1521:XE",
                    "usuario",
                    "psswrd");
                     
             */
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conexion;
    }

    public static boolean estado() throws SQLException {
        try {
            return !conexion.isClosed();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void desconectar() throws SQLException {
        try {
            if (conexion.isClosed() == false) {
                conexion.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        conectar();
        System.out.println("[STATUS CONNECTION]: " + estado());
        desconectar();
        crearTablas();
    }

    @Deprecated
    public static void crearTablas() throws Exception {
        DeleteDbFiles.execute("~", "demo", true);
        Statement stm = conectar().createStatement();
        stm.execute("CREATE TABLE PERSONA(ID INT PRIMARY KEY auto_increment, NOMBRE VARCHAR(50), APELLIDO VARCHAR(50), DNI VARCHAR(8), ESTADO VARCHAR(1))");
        stm.execute("INSERT INTO PERSONA (NOMBRE, APELLIDO, DNI, ESTADO) VALUES ('Martin','SAMAN ARATA','72720455','A')");
        ResultSet rs = stm.executeQuery("SELECT * FROM PERSONA");
        while (rs.next()) {
            System.out.println(rs.getInt(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            System.out.println(rs.getString(4));
            System.out.println(rs.getString(5));
        }
        desconectar();
    }
}
