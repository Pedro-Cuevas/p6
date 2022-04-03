package com.icai.practicas;

import com.icai.practicas.model.DNI;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDNI {

    @Test
    public void testingDNI(){
        DNI test;
        
        //DNIs validos
        test = new DNI("71307593A");
        assertEquals(true, test.validar());
        test = new DNI("41789607X");
        assertEquals(true, test.validar());
        test = new DNI("01301837Z");
        assertEquals(true, test.validar());
      
        //DNIs invalidos
        test = new DNI("00000000T");
        assertEquals(false, test.validar());
        test = new DNI("00000000T");
        assertEquals(false, test.validar());
        test = new DNI("00000000T");
        assertEquals(false, test.validar());
        
        //FORMATO INCORRECTO
        test = new DNI("0020A0500"); //letras y numeros no correspondidos
        assertEquals(false, test.validar());
        test = new DNI("00#00@00T"); //caracteres no validos
        assertEquals(false, test.validar());
        test = new DNI("7593A"); //demasiado corto
        assertEquals(false, test.validar());
        test = new DNI("7130930287593A"); //demasiado largo
        assertEquals(false, test.validar());
    }
    
}
