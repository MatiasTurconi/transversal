package Vistas;

import entidades.Inscripcion;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Usuario
 */
public class viewInscripciones extends javax.swing.JInternalFrame {
    private int idAlumnos[];
    private int idMaterias[];
    /**
     * Creates new form viewInscripciones
     */
    public viewInscripciones() {
        super("INSCRIPCIONES");
        initComponents();
        cargaInscripciones();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTDatos = new javax.swing.JTable();
        jBSalir = new javax.swing.JButton();
        jBBorrar = new javax.swing.JButton();

        jTDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTDatos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jTDatos);

        jBSalir.setText("Salir");
        jBSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSalirActionPerformed(evt);
            }
        });

        jBBorrar.setText("Borrar");
        jBBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBBorrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jBBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jBSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBSalir)
                    .addComponent(jBBorrar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSalirActionPerformed
        idAlumnos=null;
        idMaterias=null;
        this.dispose();
    }//GEN-LAST:event_jBSalirActionPerformed

    private void jBBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBBorrarActionPerformed
        viewUniversidad.inscripD.borrarInscripcionMateriaAlumno(idAlumnos[jTDatos.getSelectedRow()], idMaterias[jTDatos.getSelectedRow()]);
        cargaInscripciones();
    }//GEN-LAST:event_jBBorrarActionPerformed

    private void cargaInscripciones(){
        String colum[]={"ALUMNO","MATERIA","NOTA"};
        viewUniversidad.inscripciones=viewUniversidad.inscripD.obtenerInscripciones();
        String datos[][]=new String[viewUniversidad.inscripciones.size()][3];
        int i=0;
        idAlumnos=new int[viewUniversidad.inscripciones.size()];
        idMaterias=new int[viewUniversidad.inscripciones.size()];
        for (Inscripcion ins : viewUniversidad.inscripciones) {
            datos[i][0]= ins.getAlumno().getNombre()+" "+ins.getAlumno().getApellido();
            idAlumnos[i]=ins.getAlumno().getIdAlumno();
            datos[i][1]= ins.getMateria().getNombre();
            idMaterias[i]=ins.getMateria().getIdMateria();
            datos[i][2]= ins.getNota()+"";
            i++;
            ins=null;
        }
        jTDatos.setModel(new DefaultTableModel(datos,colum));
        jTDatos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        viewUniversidad.inscripciones=null;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBBorrar;
    private javax.swing.JButton jBSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTDatos;
    // End of variables declaration//GEN-END:variables
}