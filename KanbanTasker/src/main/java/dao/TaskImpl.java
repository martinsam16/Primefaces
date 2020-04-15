package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Task;

public class TaskImpl extends Conexion implements ICrud<Task> {

    public void crear() throws Exception {
        try {
            this.crearTablas();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void registrar(Task modelo) throws Exception {
        try {
            String sql = "INSERT INTO TASK (titulo, descripcion, tipo) VALUES (?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getTitulo());
            ps.setString(2, modelo.getDescripcion());
            ps.setString(3, "0");
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void editar(Task modelo) throws Exception {
        try {
            String sql = "UPDATE TASK SET tipo=? WHERE id=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getTipo());
            ps.setInt(2, modelo.getId());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void eliminar(Task modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Task> listar() throws Exception {
        List<Task> listaTask = null;
        try {
            String sql = "SELECT id, titulo, descripcion, tipo FROM TASK";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            listaTask = new ArrayList<>();

            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt(1));
                task.setTitulo(rs.getString(2));
                task.setDescripcion(rs.getString(3));
                task.setTipo(rs.getString(4));
                listaTask.add(task);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return listaTask;
    }

    @Override
    public List<Task> listar(Task modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Task obtenerModelo(Task modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
