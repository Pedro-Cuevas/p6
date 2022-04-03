package com.icai.practicas;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.icai.practicas.model.Telefono;

import org.junit.jupiter.api.Test;

public class TestTelefono {

    @Test
    public void testTlf(){
        Telefono tlf;

        //Fijo nacional
        tlf = new Telefono("947265180");
        assertEquals(true, tlf.validar());
        //Movil nacional
        tlf = new Telefono("633928281");
        assertEquals(true, tlf.validar());
        //Telefonos internacionales
        tlf = new Telefono("+34633928281");
        assertEquals(true, tlf.validar());
        tlf = new Telefono("+930776165184");
        assertEquals(true, tlf.validar());
        tlf = new Telefono("+12025550106");
        assertEquals(true, tlf.validar());

        //Telefonos invalidos
        tlf = new Telefono("+1B025A50106"); //caracteres no admitidos
        assertEquals(false, tlf.validar());
        tlf = new Telefono("633@2A281"); //caracteres no admitidos
        assertEquals(false, tlf.validar());
        tlf = new Telefono("+120255501060000"); //muy largo
        assertEquals(false, tlf.validar());
        tlf = new Telefono("+1202000"); //muy corto
        assertEquals(false, tlf.validar());

    }
    
}
