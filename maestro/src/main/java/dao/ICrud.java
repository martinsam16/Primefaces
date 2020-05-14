package dao;

import java.util.List;

/**
 * Operaciones necesarias para cualquier modelo dentro de una tabla en Base de
 * Datos
 *
 * @version 0.1
 * @author Martín Samán
 * @param <T> modelo (POJO)
 */
public interface ICrud<T> {

    /**
     * Realiza un registro
     *
     * @param modelo POJO
     * @throws Exception
     */
    void registrar(T modelo) throws Exception;

    /**
     * Modifica un registro
     *
     * @param modelo POJO
     * @throws Exception
     */
    void editar(T modelo) throws Exception;

    /**
     * Inactiva un registro
     *
     * @param modelo
     * @throws Exception
     */
    void eliminar(T modelo) throws Exception;

    /**
     * Lista todos los datos
     *
     * @return lista del modelo
     * @throws Exception
     */
    List<T> listar() throws Exception;

    /**
     * Lista dependiendo de condiciones dadas por el modelo
     *
     * @param modelo condiciones
     * @return lista de datos
     * @throws Exception
     */
    List<T> listar(T modelo) throws Exception;

    /**
     * Obtiene el modelo mandando unos parametros embebidos en el mmodelo
     * (findOne)
     *
     * @param modelo condiciones
     * @return
     * @throws Exception
     */
    T obtenerModelo(T modelo) throws Exception;

}
