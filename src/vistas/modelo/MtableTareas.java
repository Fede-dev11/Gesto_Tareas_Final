/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelo;


import controlador.GestorTareas;
import javax.swing.event.EventListenerList;
import javax.swing.table.AbstractTableModel;


/**
 *
 * @author FernandoP, FrancisV, OdalisR, RichardCh
 */
public class MtableTareas extends AbstractTableModel {
    
    private GestorTareas gestorT;
    

    public GestorTareas getGestorT() {
        return gestorT;
    }

    public void setGestorT(GestorTareas gestorT) {
        this.gestorT = gestorT;
    }
    
    
    
    /**
     * Metodo que devuelve el nro de filas
     * @return  Int nro de filas
     */
    @Override
    public int getRowCount() {
        return gestorT.matriz_tareas.length;
    }
    /**
     * Metodo que devuelve el nro de columnas
     * @return  Int nro de columnas
     */
    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        switch (i1) {
            case 0: return (i + 1);
            case 1: return gestorT.matriz_tareas[i][0];
            case 2: return gestorT.matriz_tareas[i][1];
            case 3: return gestorT.matriz_tareas[i][2];
            case 4: return gestorT.matriz_tareas[i][3];
            case 5: return gestorT.matriz_tareas[i][4];
            default: return null;
        }
    }

    @Override
    public String getColumnName(int i) {
       switch (i) {
           case 0: return "Nro";
           case 1: return "Tarea";
           case 2: return "Materia";
           case 3: return "Fecha";
           case 4: return "Hora";
           case 5: return "Estado";
           default: return null;
        }
    }
    
    
    
}
