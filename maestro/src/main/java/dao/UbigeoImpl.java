package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Ubigeo;

public class UbigeoImpl extends Conexion {

    public UbigeoImpl() {
        try {
            this.crearTablas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Ubigeo> listar() throws Exception {
        List<Ubigeo> listaUbigeo = null;
        try {
            String sql = "SELECT ubigeo.CODUBI, ubigeo.DEPUBI, ubigeo.PROVUBI, ubigeo.DISTUBI FROM UBIGEO ubigeo ";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            listaUbigeo = new ArrayList<>();
            while (rs.next()) {
                Ubigeo ubigeo = new Ubigeo(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)
                );
                listaUbigeo.add(ubigeo);
            }
            rs.clearWarnings();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return listaUbigeo;
    }

}
