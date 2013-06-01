/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mjc.dao;

import com.mycompany.mjc.exception.PersistenceException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.apache.log4j.Logger;

/**
 *
 * @author josediaz
 */
public class ConnectionManager {
    
    
    	//Informacões para conexão com banco de dados HSQLDB.
	private static final String STR_DRIVER = "org.hsqldb.jdbcDriver";
	private static final String DATABASE = "mercaderia";
	private static final String STR_CON = "jdbc:hsqldb:file:" + DATABASE;
	private static final String USER = "sa";
	private static final String PASSWORD = "";
	
	private static Logger log = Logger.getLogger(ConnectionManager.class);
	

	public static Connection getConnection() throws PersistenceException {
		Connection conn = null;
		try {
			Class.forName(STR_DRIVER);
			conn = DriverManager.getConnection(STR_CON, USER, PASSWORD);
			conn.setAutoCommit(false);
			
			log.debug("Abierta una conexion a la base de datos!");
			return conn;
		} catch (ClassNotFoundException e) {
			String errorMsg = "Driver (JDBC) no encontrado";
			log.error(errorMsg, e);
			throw new PersistenceException(errorMsg, e);
		} catch (SQLException e) {
			String errorMsg = "Error al obtener una conexion";
			log.error(errorMsg, e);
			throw new PersistenceException(errorMsg, e);
		}
	}

	public static void closeAll(Connection conn) {
		try {
			if (conn != null) {
				conn.close();
				log.debug("Liberada la conexion a la base de Datos!");
			}
		} catch (Exception e) {
			log.error("No es posible liberar la conexion a la base de datos!",e);
		}
	}

	public static void closeAll(Connection conn, Statement stmt) {
		try {
			if (conn != null) {
				closeAll(conn);
			}
			if (stmt != null) {
				stmt.close();
			}
		} catch (Exception e) {
			log.error("No es posible liberar los recursos!",e);
		}
	}

	public static void closeAll(Connection conn, Statement stmt, ResultSet rs) {
		try {
			if (conn != null || stmt != null) {
				closeAll(conn, stmt);
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			log.error("No es posible liberar el resultset!",e);
		}
	}

}
