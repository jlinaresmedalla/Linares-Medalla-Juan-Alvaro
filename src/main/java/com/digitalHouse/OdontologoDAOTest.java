package com.digitalHouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OdontologoDAOTest {
    private OdontologoDAO odontologoDAO;

    @BeforeEach
    public void setUp() {
        odontologoDAO = new OdontologoDAOCollection();
        odontologoDAO.guardar(new Odontologo(1, "Juan", "Perez"));
        odontologoDAO.guardar(new Odontologo(2, "Ana", "Gomez"));
        odontologoDAO.guardar(new Odontologo(3, "Luis", "Martinez"));
    }

    @Test
    public void testListarTodos() {
        List<Odontologo> odontologos = odontologoDAO.listarTodos();
        assertEquals(3, odontologos.size());
    }
}
