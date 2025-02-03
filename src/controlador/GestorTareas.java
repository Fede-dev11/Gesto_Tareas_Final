/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author FernandoP, FrancisV, OdalisR, RichardCh
 */
public class GestorTareas {

    public String[][] matriz_tareas = new String[100][5];
    private String nombreTarea;
    private String materia;
    private String fecha;
    private String hora;
    private String estado;

    public String[][] getMatriz_tareas() {
        return matriz_tareas;
    }

    public void setMatriz_tareas(String[][] matriz_tareas) {
        this.matriz_tareas = matriz_tareas;

    }

    public void crear(Integer nro) {

        this.matriz_tareas = new String[nro][5];

    }

    public Integer verificar() {
        Integer pos = -1;
        for (int i = 0; i < matriz_tareas.length; i++) {
            for (int j = 0; j < matriz_tareas[0].length; j++) {
                if (matriz_tareas[i][j] == null) {
                    pos = i;
                    break;
                }
            }
            if (pos != -1) {
                break;
            }
        }
        return pos;
    }

    /*public void crearSiNoExiste() {
        if (matriz_tareas == null) {
            crear(100);
        }
    }*/
    public Boolean guardar(String nombreTarea, JComboBox<String> cbx_materia, String fecha, String hora, JComboBox<String> cbx_estado) {

        int pos = verificar();
        if (pos >= 0) {
            matriz_tareas[pos][0] = nombreTarea;
            matriz_tareas[pos][1] = cbx_materia.getSelectedItem().toString();
            matriz_tareas[pos][2] = fecha;
            matriz_tareas[pos][3] = hora;
            matriz_tareas[pos][4] = cbx_estado.getSelectedItem().toString();
            return true;
        } else {

            return false;
        }

    }

    public Boolean modificar(String nombreTarea, JComboBox<String> cbx_materia, String fecha, String hora, JComboBox<String> cbx_estado, Integer pos) {
        String materias = cbx_materia.getSelectedItem().toString();
        String Estados = cbx_estado.getSelectedItem().toString();
        if (pos >= 0) {
            matriz_tareas[pos][0] = nombreTarea;
            matriz_tareas[pos][1] = materias;
            matriz_tareas[pos][2] = fecha;
            matriz_tareas[pos][3] = hora;
            matriz_tareas[pos][4] = Estados;
            return true;
        } else {
            return false;
        }
    }

    public Boolean eliminar(int pos) {
        if (pos >= 0 && pos < matriz_tareas.length && matriz_tareas[pos][0] != null) {
            // Eliminar la tarea estableciendo los valores como null
            for (int j = 0; j < matriz_tareas[0].length; j++) {
                matriz_tareas[pos][j] = null;
            }

            // Reordenar la matriz para eliminar filas vacías
            for (int i = pos; i < matriz_tareas.length - 1; i++) {
                matriz_tareas[i] = matriz_tareas[i + 1];
            }

            // Borrar la última fila (ya está duplicada)
            matriz_tareas[matriz_tareas.length - 1] = new String[5];

            return true;
        }
        return false;
    }

    public Boolean generarArchivo() {
        if (matriz_tareas != null) {
            String pathTareas = "data" + File.separatorChar + "Gtareas.txt";
            try {
                FileWriter file_gtareas = new FileWriter(pathTareas);

                for (int i = 0; i < matriz_tareas.length; i++) {
                    String datos = "";
                    for (int j = 0; j < matriz_tareas[0].length; j++) {
                        datos += matriz_tareas[i][j] + "\t"; //separador por tabuladores 

                    }
                    file_gtareas.write(datos.toString() + "\n");
                    file_gtareas.flush(); //Borra el bufer
                }
                file_gtareas.close();

                return true;

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al exportar", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return false;
    }

    public Boolean cargarDatos() {

        String pathTareas = "data" + File.separatorChar + "Gtareas.txt";
        try {
            FileReader frn = new FileReader(pathTareas);
            BufferedReader file_tareas = new BufferedReader(frn);

            FileReader fra1 = new FileReader(pathTareas);
            BufferedReader size = new BufferedReader(fra1);
            //crear
            //crear(Integer.parseInt(String.valueOf(size.lines().count())));
            //
            String linea;
            int cont = 0;

            while ((linea = file_tareas.readLine()) != null) {

                String[] aux = linea.split("\t");
                if (aux[0].equalsIgnoreCase("null")) {
                    matriz_tareas[cont][0] = null;
                    matriz_tareas[cont][1] = null;
                    matriz_tareas[cont][2] = null;
                    matriz_tareas[cont][3] = null;
                    matriz_tareas[cont][4] = null;

                } else {
                    matriz_tareas[cont][0] = aux[0];
                    matriz_tareas[cont][1] = aux[1];
                    matriz_tareas[cont][2] = aux[2];
                    matriz_tareas[cont][3] = aux[3];
                    matriz_tareas[cont][4] = aux[4];
                }
                cont++;
            }
            frn.close();
            file_tareas.close();
            fra1.close();
            size.close();

            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los datos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    /*
    public String validarHora(String horaIngresada) {
        if (!Utilidades.Utilidades.validar(horaIngresada)) {
            JOptionPane.showMessageDialog(null, "Error, solo ingresar números", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return "";
        }

        String horaFormateada = horaIngresada.replace(":", ""); // Eliminar ':' si existe
        int longitud = horaFormateada.length();
        
        if (longitud < 3 || longitud > 4) {
            JOptionPane.showMessageDialog(null, "Formato de hora incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
            return "";
        }

        int horas, minutos;
        try {
            if (longitud == 4) {
                horas = Integer.parseInt(horaFormateada.substring(0, 2));
                minutos = Integer.parseInt(horaFormateada.substring(2, 4));
            } else { // longitud == 3
                horas = Integer.parseInt(horaFormateada.substring(0, 1));
                minutos = Integer.parseInt(horaFormateada.substring(1, 3));
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error en el formato de la hora", "Error", JOptionPane.ERROR_MESSAGE);
            return "";
        }

        if (horas < 0 || horas >= 24) {
            JOptionPane.showMessageDialog(null, "Ingrese una hora válida (00-23)", "Error", JOptionPane.ERROR_MESSAGE);
            return "";
        }
        
        if (minutos < 0 || minutos >= 60) {
            JOptionPane.showMessageDialog(null, "Ingrese minutos válidos (00-59)", "Error", JOptionPane.ERROR_MESSAGE);
            return "";
        }
        
        return String.format("%02d:%02d", horas, minutos);
    }*/
    }
