package com.icai.practicas.model;

import java.util.Arrays;
import java.util.regex.Pattern;

public record DNI(String dniValue) { //record me permite no escribir codigo repetitivo en clases disenyadas para guardar datos

  private static final Pattern REGEXP = Pattern.compile("[0-9]{8}[A-Z]"); //compila del 0 al 9 8 veces y una letra A-Z
  private static final String DIGITO_CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";
  private static final String[] INVALIDOS = new String[]{"00000000T", "00000001R", "99999999R"};

  public boolean validar() {
    return Arrays.binarySearch(INVALIDOS, dniValue) < 0 // (1) menor que 0 si valor en INVALIDOS
            && REGEXP.matcher(dniValue).matches() // (2) comprueba si es una combinacion posible
            && dniValue.charAt(8) == DIGITO_CONTROL.charAt(Integer.parseInt(dniValue.substring(0, 8)) % 23); // (3) comprueba si la letra es valida
  }
}