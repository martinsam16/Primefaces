package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Persona;

public class PersonaImpl extends Conexion implements ICrud<Persona> {

    public void crear() throws Exception {
        try {
            this.crearTablas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void registrar(Persona modelo) throws Exception {
        try {
            String sql = "INSERT INTO PERSONA (NOMBRE, APELLIDO, DNI, ESTADO) VALUES (?,?,?,'A')";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, modelo.getId());
            ps.setString(2, modelo.getNombre());
            ps.setString(3, modelo.getApellido());
            ps.setString(4, modelo.getDni());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }

    }

    @Override
    public void editar(Persona modelo) throws Exception {
        try {
            String sql = "UPDATE PERSONA SET NOMBRE=?, APELLIDO=?, DNI=? WHERE ID=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getNombre());
            ps.setString(2, modelo.getApellido());
            ps.setString(3, modelo.getDni());
            ps.setInt(4, modelo.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void eliminar(Persona modelo) throws Exception {
        try {
            String sql = "UPDATE PERSONA SET ESTADO=? WHERE ID=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, modelo.getId());
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
            String sql = "SELECT ID, NOMBRE, APELLIDO, DNI FROM PERSONA";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            listaPersona = new ArrayList<>();

            while (rs.next()) {
                Persona p = new Persona();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setApellido(rs.getString(3));
                p.setDni(rs.getString(4));
                listaPersona.add(p);
            }

            rs.close();
            ps.close();
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
