package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Persona;
import model.Ubigeo;

public class PersonaImpl extends Conexion implements ICrud<Persona> {
    
    @Override
    public void registrar(Persona modelo) throws Exception {
        try {
            String sql = "INSERT INTO PERSONA (NOMPER,APEPATPER,APEMATPER,DNIPER,CELPER,FECNACPER,CODUBI,GENPER) "
                    + "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNOMPER());
            ps.setString(2, modelo.getAPEPATPER());
            ps.setString(3, modelo.getAPEMATPER());
            ps.setString(4, modelo.getDNIPER());
            ps.setString(5, modelo.getCELPER());
            ps.setDate(6, new java.sql.Date(modelo.getFECNACPER().getTime()));
            ps.setString(7, modelo.getUbigeo().getCODUBI());
            ps.setString(8, modelo.getGENPER());
            ps.executeUpdate();
            System.out.println(ps.getWarnings());
            ps.close();
        } catch (Exception e) {
        } finally {
            this.desconectar();
        }
    }
    
    @Override
    public void editar(Persona modelo) throws Exception {
        try {
            eliminar(modelo);
            registrar(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void eliminar(Persona modelo) throws Exception {
        try {
            String sql = "UPDATE PERSONA SET ESTPER=? WHERE IDPER=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, modelo.getIDPER());
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }
    
    @Override
    public List<Persona> listar() throws Exception {
        List<Persona> listaPersona = null;
        try {
            String sql = "SELECT p.IDPER, p.NOMPER, p.APEPATPER, p.APEMATPER, p.DNIPER, p.CELPER, p.FECNACPER, p.GENPER, p.ESTPER,"
                    + "u.CODUBI, u.DEPUBI, u.PROVUBI, u.DISTUBI "
                    + "FROM PERSONA p "
                    + "INNER JOIN UBIGEO u "
                    + "ON p.CODUBI = u.CODUBI "
                    + "WHERE p.ESTPER = 'A' ";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            listaPersona = new ArrayList<>();
            while (rs.next()) {
                Persona persona = new Persona(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getString(8),
                        rs.getString(9),
                        new Ubigeo(
                                rs.getString(10),
                                rs.getString(11),
                                rs.getString(12),
                                rs.getString(13)
                        ));
                listaPersona.add(persona);
            }
            rs.clearWarnings();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return listaPersona;
    }
    
    @Override
    public List<Persona> listar(Persona modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Persona obtenerModelo(Persona modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
