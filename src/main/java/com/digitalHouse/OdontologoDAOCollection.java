package com.digitalHouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOCollection implements OdontologoDAO {
    private static final Logger logger = LogManager.getLogger(OdontologoDAOCollection.class);
    private List<Odontologo> odontologos = new ArrayList<>();

    @Override
    public void guardar(Odontologo odontologo) {
        odontologos.add(odontologo);
        logger.info("Odontologo guardado: " + odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        logger.info("Listando todos los odontologos");
        return new ArrayList<>(odontologos);
    }
}
