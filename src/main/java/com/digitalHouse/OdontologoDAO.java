package com.digitalHouse;

import java.util.List;

public interface OdontologoDAO {
    void guardar(Odontologo odontologo);

    List<Odontologo> listarTodos();
}
