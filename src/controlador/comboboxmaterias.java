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
public class comboboxmaterias {

    private String[] materias = new String[15];

    public String[] getMaterias() {
        return materias;
    }

    public void crear() {
        materias = new String[15];
    }

    public Integer validar() {
        if (materias == null) {// para verificar si las maetrias on nulas en validar
            crear();
        }
        Integer pos = -1;
        for (int i = 0; i < materias.length; i++) {
            if (materias[i] == null) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    public Boolean guardar_materias(String Nuevamaterias) {
        // Verifica si la materia ya existe
        for (String materia : materias) {
            if (materia != null && materia.equalsIgnoreCase(Nuevamaterias)) {
                JOptionPane.showMessageDialog(null, "La materia ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }

        // Busca una posición vacía en el arreglo
        for (int i = 0; i < materias.length; i++) {
            if (materias[i] == null) { // Encuentra el primer espacio vacío
                materias[i] = Nuevamaterias;
                return true;
            }
        }

        // Si no hay espacio disponible, muestra mensaje de error
        JOptionPane.showMessageDialog(null, "No hay espacio disponible", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public Boolean modificar_materias(String Nuevamaterias, JComboBox<String> cbx_materia, Integer pos) {
        // Verificar si la materia ya existe en la lista (excepto la que se está modificando)
        for (int i = 0; i < materias.length; i++) {
            if (i != pos && materias[i] != null && materias[i].equalsIgnoreCase(Nuevamaterias)) {
                JOptionPane.showMessageDialog(null, "La materia ya existe", "Error", JOptionPane.ERROR_MESSAGE);
                return false; // No modificar si ya existe
            } 
        }

        // Modificar la materia si la posición es válida
        if (pos >= 0 && pos < materias.length && materias[pos] != null) {
            // Verificar si la nueva materia ya existe en el JComboBox (evita duplicados)
            for (int i = 0; i < cbx_materia.getItemCount(); i++) {
                if (cbx_materia.getItemAt(i).equalsIgnoreCase(Nuevamaterias)) {
                    JOptionPane.showMessageDialog(null, "La materia ya existe en la lista", "Error", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }

            // Actualizar el arreglo de materias
            materias[pos] = Nuevamaterias;

            // Actualizar el archivo
            generar_filematerias();

            // **Reemplazar directamente el elemento en el JComboBox sin duplicarlo**
            cbx_materia.removeItemAt(pos + 1); // Eliminar la anterior para evitar duplicados
            cbx_materia.insertItemAt(Nuevamaterias, pos + 1); // Insertar en la posición correcta
            cbx_materia.setSelectedIndex(pos + 1); // Mantener selección en la materia modificada

            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Posición inválida o materia no existente", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public Boolean generar_filematerias() {
        if (materias != null) {
            String pathmaterias = "datos" + File.separatorChar + "materias.txt";
            // System.out.println("hola");
            try {
                FileWriter file_materias = new FileWriter(pathmaterias);
                for (int i = 0; i < materias.length; i++) {
                    if (materias[i] != null) {
                        file_materias.write(materias[i] + "\n");
                        file_materias.flush(); //borra el bufer
                    }
                }
                file_materias.close();
                return true;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al exportar", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return false;
    }

    public Boolean cargarMaterias() {
        String pathmaterias = "datos" + File.separatorChar + "materias.txt";
        File archivo = new File(pathmaterias);
        
        try ( BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            int i = 0;
            while ((linea = br.readLine()) != null && i < materias.length) {
                materias[i] = linea;
                i++;
            }
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar materias: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /**
     * public static void MateriaS(JComboBox cbx_materia) {
     *
     * cbx_materia.addItemListener(aListener); }*
     */
}
