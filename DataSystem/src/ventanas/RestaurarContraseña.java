/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import clases.Conexion;
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.WindowConstants;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Yader Jr
 */
public class RestaurarContraseña extends javax.swing.JFrame {

    String user, user_update;
    int ID;
  

    /**
     * Creates new form RestaurarContraseña
     */
    public RestaurarContraseña() {
        initComponents();

        user = Login.user;

        user_update = GestionarUsuarios.user_update;

        setSize(350, 280);
        setTitle("Cambio de contraseña de " + user_update);
        setResizable(false);
        setLocationRelativeTo(null);

        //metodo para que no se cierre todo el programa cuando se cierra esta interfaz
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        ImageIcon wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");

        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(label_wallpaper.getWidth(), label_wallpaper.getHeight(),
                Image.SCALE_DEFAULT));

        label_wallpaper.setIcon(icono);

        this.repaint();

        try
        {
            Connection cn = Conexion.db();
            PreparedStatement pst = cn.prepareStatement("select * from usuarios where username = '" + user_update + "'");

            ResultSet rs = pst.executeQuery();

            if (rs.next())
            {
                //variable ID para recuperar el id del usuario de la db llamando al objeto rs por el metodo getInt (columna o campo que se va a recuperar en este caso "id_usuario" de la db)
                ID = rs.getInt("id_usuario");
            }

            cn.close();

        } catch (SQLException e)
        {

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

        label_titulo = new javax.swing.JLabel();
        label_pass = new javax.swing.JLabel();
        label_confirmar_pass = new javax.swing.JLabel();
        button_restaurar_pass = new javax.swing.JButton();
        txt_pass = new javax.swing.JTextField();
        txt_confirmar_pass = new javax.swing.JPasswordField();
        label_wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_titulo.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        label_titulo.setForeground(new java.awt.Color(255, 255, 255));
        label_titulo.setText("Cambio de contraseña");
        getContentPane().add(label_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, -1, -1));

        label_pass.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label_pass.setForeground(new java.awt.Color(255, 255, 255));
        label_pass.setText("Contraseña nueva");
        getContentPane().add(label_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));

        label_confirmar_pass.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        label_confirmar_pass.setForeground(new java.awt.Color(255, 255, 255));
        label_confirmar_pass.setText("Confirmar contraseña");
        getContentPane().add(label_confirmar_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        button_restaurar_pass.setBackground(new java.awt.Color(153, 153, 255));
        button_restaurar_pass.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        button_restaurar_pass.setForeground(new java.awt.Color(255, 255, 255));
        button_restaurar_pass.setText("Restaurar");
        button_restaurar_pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_restaurar_passActionPerformed(evt);
            }
        });
        getContentPane().add(button_restaurar_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 160, -1));

        txt_pass.setBackground(new java.awt.Color(153, 153, 255));
        txt_pass.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        txt_pass.setForeground(new java.awt.Color(0, 0, 0));
        txt_pass.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 160, 25));

        txt_confirmar_pass.setBackground(new java.awt.Color(153, 153, 255));
        txt_confirmar_pass.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        getContentPane().add(txt_confirmar_pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 160, 25));
        getContentPane().add(label_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 280));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_restaurar_passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_restaurar_passActionPerformed
        String pass_nueva = "", confirmar_pass = "";
          int validacion = 0;

        pass_nueva = txt_pass.getText().trim();
        confirmar_pass = txt_confirmar_pass.getText().trim();

        if (pass_nueva.equals(""))
        {

            txt_pass.setBackground(Color.red);
            validacion++;
        }

        if (confirmar_pass.equals(""))
        {
            txt_confirmar_pass.setBackground(Color.red);
            validacion++;
        }

        try
        {

            if (pass_nueva.equals(confirmar_pass))
            {
                
                if (validacion==0)
                {
           
                Connection cn2 = Conexion.db();
                PreparedStatement pst2 = cn2.prepareCall("update usuarios set password = ? where id_usuario =" + ID);

                pst2.setString(1, confirmar_pass);

                pst2.executeUpdate();
                cn2.close();

                txt_pass.setText("");
                txt_confirmar_pass.setText("");

                txt_pass.setBackground(Color.green);
                txt_confirmar_pass.setBackground(Color.green);

                JOptionPane.showMessageDialog(null, "Contraseña restaurada con exito");

                this.dispose();
                
                } else {
                     JOptionPane.showMessageDialog(null, "Por favor, rellene todos los campos");
                }
                
            } else
            {
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden, vuelva a intentarlo");
            }

        } catch (HeadlessException | SQLException e)
        {
            System.err.println("Error en restaurar contraseña");
            JOptionPane.showMessageDialog(null, "Error en confirmar contraseña, contacte al administrador");
        }

    }//GEN-LAST:event_button_restaurar_passActionPerformed

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
            java.util.logging.Logger.getLogger(RestaurarContraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(RestaurarContraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(RestaurarContraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(RestaurarContraseña.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RestaurarContraseña().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_restaurar_pass;
    private javax.swing.JLabel label_confirmar_pass;
    private javax.swing.JLabel label_pass;
    private javax.swing.JLabel label_titulo;
    private javax.swing.JLabel label_wallpaper;
    private javax.swing.JPasswordField txt_confirmar_pass;
    private javax.swing.JTextField txt_pass;
    // End of variables declaration//GEN-END:variables
}
