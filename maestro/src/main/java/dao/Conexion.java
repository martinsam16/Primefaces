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
                    "jdbc:h2:~/maestro",
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
        try {
            DeleteDbFiles.execute("~", "maestro", true);
            Statement stm = conectar().createStatement();
            stm.execute("CREATE TABLE UBIGEO "
                    + "( "
                    + "  CODUBI VARCHAR(6) NOT NULL,"
                    + "  DEPUBI VARCHAR(13),"
                    + "  PROVUBI VARCHAR(25),"
                    + "  DISTUBI VARCHAR(36)"
                    + ") ");
            stm.execute("CREATE TABLE PERSONA "
                    + "( "
                    + "  IDPER INT NOT NULL auto_increment, "
                    + "  NOMPER VARCHAR(50), "
                    + "  APEPATPER VARCHAR(50), "
                    + "  APEMATPER VARCHAR(50), "
                    + "  DNIPER VARCHAR(8) NOT NULL, "
                    + "  CELPER VARCHAR(9), "
                    + "  FECNACPER DATE, "
                    + "  GENPER VARCHAR(1), "
                    + "  ESTPER VARCHAR(1) default ('A'), "
                    + "  CODUBI VARCHAR(6) "
                    + ") ");

            stm.execute("ALTER TABLE UBIGEO ADD CONSTRAINT UBIGEO_pk PRIMARY KEY(CODUBI);");

            stm.execute("ALTER TABLE PERSONA ADD CONSTRAINT PERSONA_pk PRIMARY KEY(IDPER);");
            stm.execute("ALTER TABLE PERSONA ADD CONSTRAINT UBIGEO_PERSONA_CODUBI FOREIGN KEY (CODUBI) REFERENCES UBIGEO(CODUBI);");

            stm.execute("INSERT INTO UBIGEO (CODUBI, DEPUBI, PROVUBI, DISTUBI) VALUES ('150501', 'LIMA', 'CAÑETE', 'SAN VICENTE DE CAÑETE');");
            stm.execute("INSERT INTO UBIGEO (CODUBI, DEPUBI, PROVUBI, DISTUBI) VALUES ('150502', 'LIMA', 'CAÑETE', 'ASIA');");
            stm.execute("INSERT INTO UBIGEO (CODUBI, DEPUBI, PROVUBI, DISTUBI) VALUES ('150503', 'LIMA', 'CAÑETE', 'CALANGO');");
            stm.execute("INSERT INTO UBIGEO (CODUBI, DEPUBI, PROVUBI, DISTUBI) VALUES ('150504', 'LIMA', 'CAÑETE', 'CERRO AZUL');");
            stm.execute("INSERT INTO UBIGEO (CODUBI, DEPUBI, PROVUBI, DISTUBI) VALUES ('150505', 'LIMA', 'CAÑETE', 'CHILCA');");
            stm.execute("INSERT INTO UBIGEO (CODUBI, DEPUBI, PROVUBI, DISTUBI) VALUES ('150506', 'LIMA', 'CAÑETE', 'COAYLLO');");
            stm.execute("INSERT INTO UBIGEO (CODUBI, DEPUBI, PROVUBI, DISTUBI) VALUES ('150507', 'LIMA', 'CAÑETE', 'IMPERIAL');");
            stm.execute("INSERT INTO UBIGEO (CODUBI, DEPUBI, PROVUBI, DISTUBI) VALUES ('150508', 'LIMA', 'CAÑETE', 'LUNAHUANA');");
            stm.execute("INSERT INTO UBIGEO (CODUBI, DEPUBI, PROVUBI, DISTUBI) VALUES ('150509', 'LIMA', 'CAÑETE', 'MALA');");
            stm.execute("INSERT INTO UBIGEO (CODUBI, DEPUBI, PROVUBI, DISTUBI) VALUES ('150510', 'LIMA', 'CAÑETE', 'NUEVO IMPERIAL');");
            stm.execute("INSERT INTO UBIGEO (CODUBI, DEPUBI, PROVUBI, DISTUBI) VALUES ('150511', 'LIMA', 'CAÑETE', 'PACARAN');");
            stm.execute("INSERT INTO UBIGEO (CODUBI, DEPUBI, PROVUBI, DISTUBI) VALUES ('150512', 'LIMA', 'CAÑETE', 'QUILMANA');");
            stm.execute("INSERT INTO UBIGEO (CODUBI, DEPUBI, PROVUBI, DISTUBI) VALUES ('150513', 'LIMA', 'CAÑETE', 'SAN ANTONIO');");
            stm.execute("INSERT INTO UBIGEO (CODUBI, DEPUBI, PROVUBI, DISTUBI) VALUES ('150514', 'LIMA', 'CAÑETE', 'SAN LUIS');");
            stm.execute("INSERT INTO UBIGEO (CODUBI, DEPUBI, PROVUBI, DISTUBI) VALUES ('150515', 'LIMA', 'CAÑETE', 'SANTA CRUZ DE FLORES');");
            stm.execute("INSERT INTO UBIGEO (CODUBI, DEPUBI, PROVUBI, DISTUBI) VALUES ('150516', 'LIMA', 'CAÑETE', 'ZUÑIGA');");
            desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
