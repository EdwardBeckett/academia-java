/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mjc.dao;

import com.mycompany.mjc.exception.PersistenceException;
import com.mycompany.mjc.model.Mercaderia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author josediaz
 */
public class MercaderiaDAOJDBC implements MercaderiaDAO {

    //comandos SQL utilizados pelo DAO.
    private final static String CREATE_TABLE = "CREATE TABLE  IF NOT EXISTS mercaderia (id INTEGER IDENTITY PRIMARY KEY NOT NULL, nombre VARCHAR(20) NOT NULL, descripcion varchar(80) NOT NULL, precio REAL NOT NULL, cantidad INTEGER NOT NULL)";
    private final static String INSERT_MERCADERIA = "INSERT INTO mercaderia (nombre,descripcion,precio,cantidad) VALUES (?,?,?,?)";
    private final static String UPDATE_MERCADERIA = "UPDATE mercaderia SET nombre = ?, descripcion = ?, precio = ?, cantidad = ? WHERE id = ?";
    private final static String DELETE_MERCADERIA = "DELETE FROM mercaderia WHERE id = ?";
    private final static String GET_ALL_MERCADERIAS = "SELECT * FROM mercaderia";
    private final static String GET_MERCADERIAS_BY_NOMBRE = "SELECT * FROM mercaderia WHERE nombre like ?";
    private final static String GET_MERCADERIA_BY_ID = "SELECT * FROM mercaderia WHERE id = ?";
    private static Logger log = Logger.getLogger(MercaderiaDAOJDBC.class);

    @Override
    public void init() throws PersistenceException {
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = ConnectionManager.getConnection();
            stmt = conn.createStatement();
            int r = stmt.executeUpdate(CREATE_TABLE);

            if (r > 0) {
                log.info("Crea una tabla 'mercaderia'");
            }
        } catch (SQLException e) {
            log.error(e);
            throw new PersistenceException("No fue posible ejecutar : " + CREATE_TABLE, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt);
        }
    }

    @Override
    public void save(Mercaderia mercaderia) throws PersistenceException {

        if (mercaderia == null) {
            throw new PersistenceException("Mercaderia a grabar en la base de datos!");
        }
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionManager.getConnection();
            if (mercaderia.getId() == null) {
                stmt = getStatementInsert(conn, mercaderia);
            } else {
                stmt = getStatementUpdate(conn, mercaderia);
            }
            stmt.executeUpdate();
            conn.commit();
            log.debug("Mercaderia fue grabada");
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (Exception sx) {
            }
            String errorMsg = "Error al grabar Mercaderia!";
            log.error(errorMsg, e);
            throw new PersistenceException(errorMsg, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt);
        }


    }

    private PreparedStatement getStatementInsert(Connection conn, Mercaderia m) throws SQLException {
        PreparedStatement stmt = createStatementWithLog(conn, INSERT_MERCADERIA);
        stmt.setString(1, m.getNombre());
        stmt.setString(2, m.getDescripcion());
        stmt.setDouble(3, m.getPrecio());
        stmt.setInt(4, m.getCantidad());
        return stmt;
    }

    private PreparedStatement getStatementUpdate(Connection conn, Mercaderia m) throws SQLException {
        PreparedStatement stmt = createStatementWithLog(conn, UPDATE_MERCADERIA);
        stmt.setString(1, m.getNombre());
        stmt.setString(2, m.getDescripcion());
        stmt.setDouble(3, m.getPrecio());
        stmt.setInt(4, m.getCantidad());
        stmt.setInt(5, m.getId());
        return stmt;
    }

    private static PreparedStatement createStatementWithLog(Connection conn, String sql) throws SQLException {
        if (conn == null) {
            return null;
        }

        log.debug("SQL: " + sql);
        return conn.prepareStatement(sql);
    }

    @Override
    public void remove(Mercaderia m) throws PersistenceException {

        if (m == null || m.getId() == null) {
            throw new PersistenceException("Id mercaderia no puede ser nulo!");
        }
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = ConnectionManager.getConnection();
            stmt = createStatementWithLog(conn, DELETE_MERCADERIA);
            stmt.setInt(1, m.getId());
            stmt.executeUpdate();
            conn.commit();
            log.debug("Mercaderia fue eliminada");
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (Exception sx) {
            }
            String errorMsg = "Error al eliminar mercaderia!";
            log.error(errorMsg, e);
            throw new PersistenceException(errorMsg, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt);
        }
    }

    @Override
    public List<Mercaderia> getAll() throws PersistenceException {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = createStatementWithLog(conn, GET_ALL_MERCADERIAS);
            rs = stmt.executeQuery();

            return toMercaderias(rs);
        } catch (SQLException e) {
            String errorMsg = "Error al consultar todas las mercaderias!";
            log.error(errorMsg, e);
            throw new PersistenceException(errorMsg, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt, rs);
        }
    }

    private List<Mercaderia> toMercaderias(ResultSet rs) throws SQLException {
        List<Mercaderia> lista = new ArrayList<Mercaderia>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String descripcion = rs.getString("descripcion");
            int cantidad = rs.getInt("cantidad");
            double precio = rs.getDouble("precio");

            lista.add(new Mercaderia(id, nombre, descripcion, cantidad, precio));
        }
        return lista;
    }

    @Override
    public List<Mercaderia> getMercaderiasByNombre(String nombre) throws PersistenceException {

        if (nombre == null || nombre.isEmpty()) {
            return Collections.EMPTY_LIST;
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = createStatementWithLog(conn, GET_MERCADERIAS_BY_NOMBRE);
            stmt.setString(1, nombre + "%");
            rs = stmt.executeQuery();

            return toMercaderias(rs);
        } catch (SQLException e) {
            String errorMsg = "Error al consultar mercaderias por nombre!";
            log.error(errorMsg, e);
            throw new PersistenceException(errorMsg, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt, rs);
        }
    }

    @Override
    public Mercaderia findById(Integer id) throws PersistenceException{
        if (id == null || id.intValue() < 0) {
            throw new PersistenceException("Buscando por id!");
        }
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Mercaderia m = null;

        try {
            conn = ConnectionManager.getConnection();
            stmt = createStatementWithLog(conn, GET_MERCADERIA_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                int cantidad = rs.getInt("cantidad");
                double precio = rs.getDouble("precio");

                m = new Mercaderia(id, nombre, descripcion, cantidad, precio);
            }
            return m;
        } catch (SQLException e) {
            String errorMsg = "Error al consultar mercaderia por Id!";
            log.error(errorMsg, e);
            throw new PersistenceException(errorMsg, e);
        } finally {
            ConnectionManager.closeAll(conn, stmt, rs);
        }
    }
}