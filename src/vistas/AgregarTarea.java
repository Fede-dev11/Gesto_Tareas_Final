/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controlador.GestorTareas;
import java.util.Date;
import vistas.modelo.MtableTareas;
import javax.swing.JOptionPane;
import controlador.comboboxmaterias;
import javax.swing.JComboBox;

/**
 *
 * @author Francis Valdiviezo
 */
public class AgregarTarea extends javax.swing.JFrame {

    /**
     * Creates new form AgregarTarea
     */
    private GestorTareas gt = new GestorTareas();
    private MtableTareas modeloT = new MtableTareas();
    private Integer fila = -1;
    private comboboxmaterias cbxmat = new comboboxmaterias();

    /* private JTable tablaGeneral;
    private JPanel panelTabla;

    public JTable generalTabla(){
        tablaGeneral = tbl_tablaT;
        return tablaGeneral;
    }
    public JPanel tablaGeneral(){
        panelTabla = jp_tabla;
        return panelTabla;
    }*/
    public AgregarTarea() {
        initComponents();
        this.setLocationRelativeTo(null);
        cargartabla();
        cbxmat.cargarMaterias();
        actualizarmaterias();
        gt.cargarDatos();
    }

    private void actualizarmaterias() {
        // Leer desde el archivo y agregar solo las materias nuevas
        for (String materia : cbxmat.getMaterias()) {
            if (materia != null && !existeEnComboBox(cbx_materia, materia)) {
                cbx_materia.addItem(materia); // Agregar solo si no está en el JComboBox
            }
        }
    }

// Método auxiliar para verificar si una materia ya está en el JComboBox
    private boolean existeEnComboBox(JComboBox<String> comboBox, String valor) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            String item = comboBox.getItemAt(i);
            if (item != null && item.equalsIgnoreCase(valor)) {
                return true; // Ya existe en el JComboBox
            }
        }
        return false; // No existe, se puede agregar
    }

    public void cargartabla() {

        modeloT.setGestorT(gt);
        tbl_tablaT.setModel(modeloT);
        tbl_tablaT.updateUI();
        //tbl_tabla.cargartabla();

    }

    private Boolean verificar() {
        return (lbl_ntarea.getText().trim().isEmpty() || lbl_hora.getText().trim().isEmpty());
    }

    private void limpiar() {

        lbl_ntarea.setText("");
        //lbl_fecha.setText("");
        lbl_hora.setText("");
        cbx_materia.setSelectedItem("---Seleccione---");
        cbx_estado.setSelectedItem("---Seleccione---");

        cargartabla();
        fila = -1;

    }

    //-
    private void registrar() {

        if (lbl_fecha.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fecha antes de registrar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Date datosffecha = lbl_fecha.getDate();
        long d = datosffecha.getTime();
        java.sql.Date fechaD = new java.sql.Date(d);
        String fecha = fechaD.toString();

        if (verificar()) {
            JOptionPane.showMessageDialog(null, "LLene todos los datos");
        } else {

            if (fila == -1) {
                if ((cbx_materia.getSelectedItem().equals("---Seleccione---")) || (cbx_estado.getSelectedItem().equals("---Seleccione---"))) {
                    JOptionPane.showMessageDialog(null, "Seleccione una opcion de materia o estado", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (!Utilidades.Utilidades.validarHora(lbl_hora.getText().trim())) {
                        JOptionPane.showMessageDialog(null, "Ingrese la fecha en un formato HH:mm", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        if (gt.guardar(lbl_ntarea.getText().trim(), cbx_materia, fecha, lbl_hora.getText().trim(), cbx_estado)) {

                            JOptionPane.showMessageDialog(null, "Se a guardao correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);

                            limpiar();

                        } else {
                            JOptionPane.showMessageDialog(null, "Hubo un error al guardar o se lleno los cupos", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
    }

    public void crearArreglos() {

        Integer nro = 100;
        if (nro != null) {
            gt.crear(nro);
            cbxmat.crear();
            cargartabla();
            cbxmat.cargarMaterias();
        }
    }

    // metdoos para agregar materias 
    private void registrarmaterias() {
        if (fila == -1) {
            //System.out.println("Hola" + txtagregarmaterias.getText().trim());
            String nuevaMateria = JOptionPane.showInputDialog(this, "Ingrese el nombre de la Materia nueva:");
            if (cbxmat.guardar_materias(nuevaMateria)) {
                JOptionPane.showMessageDialog(null, "Se a guardado de manera correctamente", "Ok", JOptionPane.INFORMATION_MESSAGE);
                cbx_materia.addItem(nuevaMateria);

            } else {
                JOptionPane.showMessageDialog(null, "Hubo un error al guardar o se lleno los cupos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void Modificarmaterias() {
        int pos = cbx_materia.getSelectedIndex() - 1; // Restar 1 para ignorar la opción "---Seleccione---"
        if (pos >= 0) {
            String nuevaMateria = JOptionPane.showInputDialog(this, "Ingrese el nuevo nombre de la materia:");
            if (nuevaMateria != null && !nuevaMateria.trim().isEmpty()) {
                if (cbxmat.modificar_materias(nuevaMateria, cbx_materia, pos)) {

                    JOptionPane.showMessageDialog(this, "Materia modificada correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cbxmat.cargarMaterias();

                }
            } else {
                JOptionPane.showMessageDialog(this, "El nombre de la materia no puede estar vacío", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione una materia válida", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCalendar1 = new com.toedter.calendar.JCalendar();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btn_regresar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbl_ntarea = new javax.swing.JTextField();
        cbx_materia = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbx_estado = new javax.swing.JComboBox<>();
        btn_agregar = new javax.swing.JButton();
        lbl_hora = new javax.swing.JTextField();
        lbl_fecha = new com.toedter.calendar.JDateChooser();
        btnmat_agre = new javax.swing.JButton();
        btm_modificar = new javax.swing.JButton();
        jp_tabla = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_tablaT = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setToolTipText("Agregar Tareas | Gestor de tareas UNL");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/vistas/bannerUnl1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 987, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Ingresar nueva tarea");

        btn_regresar.setText("Regresar");
        btn_regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_regresarActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Nombre tarea: ");

        jLabel4.setText("Materia: ");

        lbl_ntarea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_ntareaActionPerformed(evt);
            }
        });

        cbx_materia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Seleccione---" }));
        cbx_materia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_materiaActionPerformed(evt);
            }
        });

        jLabel5.setText("Fecha: ");

        jLabel6.setText("Hora: ");

        jLabel7.setText("Estado: ");

        cbx_estado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "---Seleccione---", "Pendiente", "En proceso", "Completado" }));
        cbx_estado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbx_estadoActionPerformed(evt);
            }
        });

        btn_agregar.setText("Agregar");
        btn_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_agregarActionPerformed(evt);
            }
        });

        lbl_hora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lbl_horaActionPerformed(evt);
            }
        });

        btnmat_agre.setText("Agregar Materias");
        btnmat_agre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnmat_agreActionPerformed(evt);
            }
        });

        btm_modificar.setText("Modificar Materias");
        btm_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btm_modificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_ntarea, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(btnmat_agre)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btm_modificar))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addGap(38, 38, 38)
                            .addComponent(cbx_materia, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(lbl_hora, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(cbx_estado, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbl_ntarea, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)
                        .addComponent(jLabel5)
                        .addComponent(jLabel7)
                        .addComponent(cbx_estado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbl_fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(cbx_materia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(lbl_hora, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btm_modificar)
                    .addComponent(btnmat_agre)
                    .addComponent(btn_agregar))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jp_tabla.setBackground(new java.awt.Color(255, 255, 255));
        jp_tabla.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tbl_tablaT.setBackground(new java.awt.Color(255, 255, 255));
        tbl_tablaT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tbl_tablaT);

        javax.swing.GroupLayout jp_tablaLayout = new javax.swing.GroupLayout(jp_tabla);
        jp_tabla.setLayout(jp_tablaLayout);
        jp_tablaLayout.setHorizontalGroup(
            jp_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_tablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jp_tablaLayout.setVerticalGroup(
            jp_tablaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_tablaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(1223, 1223, 1223))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btn_regresar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jp_tabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jp_tabla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_regresar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1036, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_regresarActionPerformed
        // TODO add your handling code here:
        Dashboard db = new Dashboard();
        db.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btn_regresarActionPerformed

    private void btn_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_agregarActionPerformed
        // TODO add your handling code here:

        registrar();
        gt.generarArchivo();


    }//GEN-LAST:event_btn_agregarActionPerformed

    private void lbl_ntareaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_ntareaActionPerformed
        // TODO add your handling code here:
        registrar();
    }//GEN-LAST:event_lbl_ntareaActionPerformed

    private void lbl_horaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lbl_horaActionPerformed
        // TODO add your handling code here:
        registrar();
        gt.generarArchivo();
    }//GEN-LAST:event_lbl_horaActionPerformed

    private void cbx_materiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_materiaActionPerformed
        // TODO add your handling code here:
        cbxmat.cargarMaterias();
        actualizarmaterias();
    }//GEN-LAST:event_cbx_materiaActionPerformed

    private void cbx_estadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbx_estadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbx_estadoActionPerformed

    private void btnmat_agreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnmat_agreActionPerformed
        // TODO add your handling code here:
        registrarmaterias();
        //limpiarmaterias();
        cbxmat.generar_filematerias();
    }//GEN-LAST:event_btnmat_agreActionPerformed

    private void btm_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btm_modificarActionPerformed
        // TODO add your handling code here:
        Modificarmaterias();
        actualizarmaterias();

    }//GEN-LAST:event_btm_modificarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AgregarTarea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarTarea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarTarea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarTarea.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgregarTarea().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btm_modificar;
    private javax.swing.JButton btn_agregar;
    private javax.swing.JButton btn_regresar;
    private javax.swing.JButton btnmat_agre;
    private javax.swing.JComboBox<String> cbx_estado;
    private javax.swing.JComboBox<String> cbx_materia;
    private com.toedter.calendar.JCalendar jCalendar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel jp_tabla;
    private com.toedter.calendar.JDateChooser lbl_fecha;
    private javax.swing.JTextField lbl_hora;
    private javax.swing.JTextField lbl_ntarea;
    private javax.swing.JTable tbl_tablaT;
    // End of variables declaration//GEN-END:variables
}
