package dao;

import java.sql.Connection;
import java.sql.DriverManager;
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
        stm.execute("CREATE TABLE TASK(id INT PRIMARY KEY auto_increment, titulo VARCHAR(50), descripcion VARCHAR(500), tipo VARCHAR(1))");
//        stm.execute("INSERT INTO TASK (titulo, descripcion, tipo) VALUES ('backlog','backlogqw xd','0')"); //Backlog
//        stm.execute("INSERT INTO TASK (titulo, descripcion, tipo) VALUES ('backlog2','backloga xd','0')"); //Backlog
//        stm.execute("INSERT INTO TASK (titulo, descripcion, tipo) VALUES ('backlog3','backlogs sxd','0')"); //Backlog
//        stm.execute("INSERT INTO TASK (titulo, descripcion, tipo) VALUES ('TODO','TODO xd','1')"); //TODO
//        stm.execute("INSERT INTO TASK (titulo, descripcion, tipo) VALUES ('InProgress','InProgress xd','2')"); //InProgress
//        stm.execute("INSERT INTO TASK (titulo, descripcion, tipo) VALUES ('Review','Review xd','3')"); //Review
//        stm.execute("INSERT INTO TASK (titulo, descripcion, tipo) VALUES ('Completed','Completed xd','4')"); //Completed
        desconectar();
    }
}
