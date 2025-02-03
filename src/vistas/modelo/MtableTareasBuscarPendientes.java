/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelo;



import javax.swing.table.AbstractTableModel;
import vistas.TareasPendientes;


/**
 *
 * @author FernandoP, FrancisV, OdalisR, RichardCh
 */
public class MtableTareasBuscarPendientes extends AbstractTableModel {
    
    
    private TareasPendientes tp;

    public TareasPendientes getTp() {
        return tp;
    }

    public void setTp(TareasPendientes tp) {
        this.tp = tp;
    }

    
    
    
    
    
    /**
     * Metodo que devuelve el nro de filas
     * @return  Int nro de filas
     */
    @Override
    public int getRowCount() {
        if (tp.getNuevaTablaBusqueda() == null) {
        return 0; 
    }
    return tp.getNuevaTablaBusqueda().length;
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
        if (tp.getNuevaTablaBusqueda() == null) {
        return null;
    }
        switch (i1) {
            case 0: return (i + 1);
            case 1: return tp.getNuevaTablaBusqueda()[i][0];
            case 2: return tp.getNuevaTablaBusqueda()[i][1];
            case 3: return tp.getNuevaTablaBusqueda()[i][2];
            case 4: return tp.getNuevaTablaBusqueda()[i][3];
            case 5: return tp.getNuevaTablaBusqueda()[i][4];
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
