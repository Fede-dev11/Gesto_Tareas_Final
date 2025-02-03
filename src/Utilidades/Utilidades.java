/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Odalis_R
 */
public class Utilidades {

    public static boolean validar(String num) {
        boolean band = true;
        if (num.charAt(0) == '-') {
            num = num.substring(1);
        }

        int cont_p = 0;

        for (int i = 0; i < num.length(); i++) {
            if (!Character.isDigit(num.charAt(i)) && num.charAt(i) != '.') {
                band = false;
                break;
            } else if (num.charAt(i) == '.') {
                cont_p++;
            }
        }
        if (cont_p >= 2) {
            band = false;
        }
        return band;
    }

    public static int transformStringInt(String num) {
        int resp = 0;
        if (Utilidades.validar(num)) {
            resp = (int) Utilidades.transformStringFloat(num);
        }
        return resp;
    }

    public static double transformStringDouble(String num) {
        double resp = 0;
        if (Utilidades.validar(num)) {
            resp = Double.parseDouble(num);
        }
        return resp;
    }

    public static float transformStringFloat(String num) {
        float resp = 0;
        if (Utilidades.validar(num)) {
            resp = Float.parseFloat(num);
        }
        return resp;
    }


    public static String validadorEspacios(String txt) {
        String txt_validado = "";

        for (int i = 0; i < txt.length(); i++) {
            char txt_posicion = txt.charAt(i);

            if (txt_posicion != ' ') {
                txt_validado += txt_posicion;
            }
        }

        return txt_validado;
    }

    

    public static Date transformarStringDate(String fecha) {
        Date fechaFormateada = null;
        try {

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            formato.setLenient(false); // No aceptar fechas inválidas (e.g., 2025-02-30)
            fechaFormateada = formato.parse(fecha); //

        } catch (ParseException ex) {
            Logger.getLogger(Utilidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fechaFormateada;
    }
    public static Boolean validarHora(String hora) {
        // Verificar que el formato sea exactamente HH:mm
        if (hora.length() != 5 || hora.charAt(2) != ':') {
            return false;
        }

        // Extraer los valores de hora y minutos
        String horasStr = hora.substring(0, 2);
        String minutosStr = hora.substring(3, 5);

        // Verificar que solo contengan dígitos
        if (!horasStr.matches("\\d{2}") || !minutosStr.matches("\\d{2}")) {
            return false;
        }

        // Convertir a enteros de manera manual (sin try-catch)
        int horas = transformStringInt( hora.substring(0, 2));
        int minutos = transformStringInt( hora.substring(3, 5));

        // Validar rangos de horas (00-23) y minutos (00-59)
        if (horas >= 0 && horas < 24 && minutos >= 0 && minutos < 60) {
            return true;
        } else {
            return false;
        }
    }
    
}
