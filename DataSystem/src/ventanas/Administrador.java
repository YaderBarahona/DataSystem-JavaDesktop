/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.sql.*;
import clases.Conexion;
import javax.swing.WindowConstants;

/**
 *
 * @author Yader Jr
 */
public class Administrador extends javax.swing.JFrame {

    //variables para recuperar informacion de otras variables
    String user, nombre_usuario;
    //variable para enviar datos entre interfaces (de tipo static)
    public static int sesion_usuario;

    /**
     * Creates new form Administrador
     */
    public Administrador() {
        initComponents();

        //recuperamos mediante la varaible user la otra variable llamada igualmente user introducida en la clase login y la llamamos mediante la clase login y el nombre de dicha variable
        user = Login.user;
        //varaiable con funcion bandera para ser utilizada en las otras interfaces de capturista y tecnico
        //valor de  1 = administrador
        sesion_usuario = 1;
        
        setSize(650, 430);
        setResizable(false);
        setTitle("Administrador - sesión de " + user);
        setLocationRelativeTo(null);

        //metodo para evitar que el programa se ejecute en segundo plano cuando se cierre el mismo
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        ImageIcon wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
        
       Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(label_wallpaper.getWidth(), label_wallpaper.getHeight(),
                Image.SCALE_DEFAULT));
        
       label_wallpaper.setIcon(icono);
        
        this.repaint();

        //metodo
        try
        {
            //llamado de la clase conexion con el metodo db que contiene la conexion de a  la db
            Connection cn = Conexion.db();
            
            PreparedStatement pst = cn.prepareStatement(
                    "Select nombre_usuario from  usuarios where username = '" + user + "'");
            
            ResultSet rs = pst.executeQuery();
            
            if (rs.next())
            {
                nombre_usuario = rs.getString("nombre_usuario");
                label_usuario.setText("Bienvenido " + nombre_usuario);
            }
            
        } catch (SQLException e)
        {
            System.err.println("Error en conexion desde la interfaz administrador ");
        }
        
    }
    
    @Override
    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/icon.png"));
        return retValue;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        label_usuario = new javax.swing.JLabel();
        button_registrar_usuario = new javax.swing.JButton();
        button_gestion_usuario = new javax.swing.JButton();
        button_creatividad = new javax.swing.JButton();
        button_vista_capturista = new javax.swing.JButton();
        button_vista_tecnico = new javax.swing.JButton();
        button_acercade = new javax.swing.JButton();
        label_registrar_usuario = new javax.swing.JLabel();
        label_gestionar_usuarios = new javax.swing.JLabel();
        label_creatividad = new javax.swing.JLabel();
        label_panel_vista_capturista = new javax.swing.JLabel();
        label_panel_vista_tecnico = new javax.swing.JLabel();
        label_acercade = new javax.swing.JLabel();
        label_footer = new javax.swing.JLabel();
        label_wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_usuario.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        label_usuario.setForeground(new java.awt.Color(255, 255, 255));
        label_usuario.setText("jLabel1");
        getContentPane().add(label_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        button_registrar_usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/addUser.png"))); // NOI18N
        button_registrar_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_registrar_usuarioActionPerformed(evt);
            }
        });
        getContentPane().add(button_registrar_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 120, 100));

        button_gestion_usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/informationuser.png"))); // NOI18N
        button_gestion_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_gestion_usuarioActionPerformed(evt);
            }
        });
        getContentPane().add(button_gestion_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 70, 120, 100));

        button_creatividad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/creatividad.png"))); // NOI18N
        button_creatividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_creatividadActionPerformed(evt);
            }
        });
        getContentPane().add(button_creatividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 70, 120, 100));

        button_vista_capturista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/capturista.png"))); // NOI18N
        button_vista_capturista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_vista_capturistaActionPerformed(evt);
            }
        });
        getContentPane().add(button_vista_capturista, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 120, 100));

        button_vista_tecnico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tecnico.png"))); // NOI18N
        button_vista_tecnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_vista_tecnicoActionPerformed(evt);
            }
        });
        getContentPane().add(button_vista_tecnico, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, 120, 100));

        button_acercade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tecnico.png"))); // NOI18N
        getContentPane().add(button_acercade, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 230, 120, 100));

        label_registrar_usuario.setForeground(new java.awt.Color(255, 255, 255));
        label_registrar_usuario.setText("Registrar usuario");
        label_registrar_usuario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().add(label_registrar_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(68, 170, -1, -1));

        label_gestionar_usuarios.setForeground(new java.awt.Color(255, 255, 255));
        label_gestionar_usuarios.setText("Gestionar usuarios");
        getContentPane().add(label_gestionar_usuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(273, 170, -1, -1));

        label_creatividad.setForeground(new java.awt.Color(255, 255, 255));
        label_creatividad.setText("En proceso...");
        getContentPane().add(label_creatividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 170, -1, -1));

        label_panel_vista_capturista.setForeground(new java.awt.Color(0, 0, 0));
        label_panel_vista_capturista.setText("Panel vista capturista");
        getContentPane().add(label_panel_vista_capturista, new org.netbeans.lib.awtextra.AbsoluteConstraints(57, 330, -1, -1));

        label_panel_vista_tecnico.setForeground(new java.awt.Color(0, 0, 0));
        label_panel_vista_tecnico.setText("Panel vista técnico");
        getContentPane().add(label_panel_vista_tecnico, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 330, -1, -1));

        label_acercade.setForeground(new java.awt.Color(0, 0, 0));
        label_acercade.setText("Acerca de");
        getContentPane().add(label_acercade, new org.netbeans.lib.awtextra.AbsoluteConstraints(505, 330, -1, -1));

        label_footer.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label_footer.setForeground(new java.awt.Color(0, 0, 0));
        label_footer.setText("Creado por Yader Barahona");
        getContentPane().add(label_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 370, -1, -1));
        getContentPane().add(label_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 430));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_registrar_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_registrar_usuarioActionPerformed

        //objeto de la interfaz registrarusuarios
        RegistrarUsuarios registrar_usuarios = new RegistrarUsuarios();
        //mediante el objeto llamamos al metodo setVisible para abrir la interfaz de registrar usuarios
        //esto para que se abra sobre la interfaz admin
        registrar_usuarios.setVisible(true);

//        dispose();
        //      new RegistrarUsuarios().setVisible(true);
    }//GEN-LAST:event_button_registrar_usuarioActionPerformed

    private void button_gestion_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_gestion_usuarioActionPerformed
        GestionarUsuarios gestionar_usuarios = new GestionarUsuarios();
        
        gestionar_usuarios.setVisible(true);
    }//GEN-LAST:event_button_gestion_usuarioActionPerformed

    private void button_vista_capturistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_vista_capturistaActionPerformed
     Capturista capturista = new Capturista();
     
     capturista.setVisible(true);
    }//GEN-LAST:event_button_vista_capturistaActionPerformed

    private void button_vista_tecnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_vista_tecnicoActionPerformed
       Tecnico tecnico = new Tecnico();
       
       tecnico.setVisible(true);
    }//GEN-LAST:event_button_vista_tecnicoActionPerformed

    private void button_creatividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_creatividadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_creatividadActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Administrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_acercade;
    private javax.swing.JButton button_creatividad;
    private javax.swing.JButton button_gestion_usuario;
    private javax.swing.JButton button_registrar_usuario;
    private javax.swing.JButton button_vista_capturista;
    private javax.swing.JButton button_vista_tecnico;
    private javax.swing.JLabel label_acercade;
    private javax.swing.JLabel label_creatividad;
    private javax.swing.JLabel label_footer;
    private javax.swing.JLabel label_gestionar_usuarios;
    private javax.swing.JLabel label_panel_vista_capturista;
    private javax.swing.JLabel label_panel_vista_tecnico;
    private javax.swing.JLabel label_registrar_usuario;
    private javax.swing.JLabel label_usuario;
    private javax.swing.JLabel label_wallpaper;
    // End of variables declaration//GEN-END:variables

   
}
