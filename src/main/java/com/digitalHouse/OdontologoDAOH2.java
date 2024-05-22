package com.digitalHouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements OdontologoDAO {
    private static final Logger logger = LogManager.getLogger(OdontologoDAOH2.class);
    private static final String JDBC_URL = "jdbc:h2:tcp://localhost:9092/odontologos";
    private static final String JDBC_USER = "sa";
    private static final String JDBC_PASSWORD = "admin";

    static {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement stmt = connection.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS Odontologo (" +
                    "numeroMatricula INT PRIMARY KEY," +
                    "nombre VARCHAR(255)," +
                    "apellido VARCHAR(255))";
            stmt.execute(sql);
        } catch (SQLException e) {
            logger.error("Error creando la tabla de Odontologo en H2", e);
        }
    }

    @Override
    public void guardar(Odontologo odontologo) {
        String sql = "INSERT INTO Odontologo (numeroMatricula, nombre, apellido) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, odontologo.getNumeroMatricula());
            pstmt.setString(2, odontologo.getNombre());
            pstmt.setString(3, odontologo.getApellido());
            pstmt.executeUpdate();
            logger.info("Odontologo guardado: " + odontologo);
        } catch (SQLException e) {
            logger.error("Error guardando odontologo en H2", e);
        }
    }

    @Override
    public List<Odontologo> listarTodos() {
        List<Odontologo> odontologos = new ArrayList<>();
        String sql = "SELECT * FROM Odontologo";
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int numeroMatricula = rs.getInt("numeroMatricula");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                odontologos.add(new Odontologo(numeroMatricula, nombre, apellido));
            }
            logger.info("Listando todos los odontologos");
        } catch (SQLException e) {
            logger.error("Error listando odontologos en H2", e);
        }
        return odontologos;
    }
}
