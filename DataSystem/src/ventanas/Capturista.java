/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import clases.Conexion;
import java.sql.*;

//librerias para la impresion en pdf con el driver itextpdf
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

/**
 *
 * @author Yader Jr
 */
public class Capturista extends javax.swing.JFrame {

    /**
     * Creates new form capturista
     */
    String user, nombre_usuario;

    //variable bandera para saber si la sesion es de un admonistrador capturista o tecnico
    int sesion_usuario;

    public Capturista() {
        initComponents();
        setLocationRelativeTo(null);

        user = Login.user;
        sesion_usuario = Administrador.sesion_usuario;

        setSize(550, 300);
        setResizable(false);
        setTitle("Capturista - sesión de " + user);
        this.setLocationRelativeTo(null);

        //condicional para controlar cuando se cierra o no por completo la interfaz de capturista o tecnico estando en administradore y estando en capturista o tecnico
        if (sesion_usuario == 1)
        {
            setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        } else
        {
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }

        ImageIcon wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");

        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(label_wallpaper.getWidth(), label_wallpaper.getHeight(),
                Image.SCALE_DEFAULT));

        label_wallpaper.setIcon(icono);
        this.repaint();

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
                label_titulo.setText("Bienvenido " + nombre_usuario);
            }

        } catch (SQLException e)
        {
            System.err.println("Error en conexion desde la interfaz capturista ");
        }

        //   label_titulo.setText("Bienvenido " + user);
    }

    @Override
    public Image getIconImage() {
        //no necesita especificar la carpeta src el metodo ya sabe que se va a usar un archivo de esta carpeta
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

        label_footer = new javax.swing.JLabel();
        button_registrar_clientes = new javax.swing.JButton();
        button_gestionar_clientes = new javax.swing.JButton();
        button_imprimir = new javax.swing.JButton();
        label_registrar_cliente = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        label_titulo = new javax.swing.JLabel();
        label_wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_footer.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label_footer.setForeground(new java.awt.Color(0, 0, 0));
        label_footer.setText("Creado por Yader Brahona");
        getContentPane().add(label_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 240, -1, -1));

        button_registrar_clientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/add.png"))); // NOI18N
        button_registrar_clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_registrar_clientesActionPerformed(evt);
            }
        });
        getContentPane().add(button_registrar_clientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 120, 100));

        button_gestionar_clientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/informationuser.png"))); // NOI18N
        button_gestionar_clientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_gestionar_clientesActionPerformed(evt);
            }
        });
        getContentPane().add(button_gestionar_clientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 80, 120, 100));

        button_imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/impresora.png"))); // NOI18N
        button_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_imprimirActionPerformed(evt);
            }
        });
        getContentPane().add(button_imprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 120, 100));

        label_registrar_cliente.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        label_registrar_cliente.setForeground(new java.awt.Color(255, 255, 255));
        label_registrar_cliente.setText("Registrar cliente");
        getContentPane().add(label_registrar_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 180, -1, -1));

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Gestionar clientes");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 180, -1, -1));

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Imprimir clientes");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(415, 180, -1, -1));

        label_titulo.setFont(new java.awt.Font("Arial", 1, 20)); // NOI18N
        label_titulo.setForeground(new java.awt.Color(255, 255, 255));
        label_titulo.setText("jLabel1");
        getContentPane().add(label_titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        getContentPane().add(label_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_registrar_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_registrar_clientesActionPerformed
        RegistrarClientes registrar_clientes = new RegistrarClientes();

        registrar_clientes.setVisible(true);
    }//GEN-LAST:event_button_registrar_clientesActionPerformed

    private void button_gestionar_clientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_gestionar_clientesActionPerformed
        GestionarClientes gestionar_clientes = new GestionarClientes();

        gestionar_clientes.setVisible(true);
    }//GEN-LAST:event_button_gestionar_clientesActionPerformed

    private void button_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_imprimirActionPerformed

        Document documento = new Document();

        try
        {

            String ruta = System.getProperty("user.home");

            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Lista_clientes.pdf"));

            com.itextpdf.text.Image banner = com.itextpdf.text.Image.getInstance("src/images/BannerPDF.jpg");

            banner.scaleToFit(650, 1000);

            banner.setAlignment(Chunk.ALIGN_CENTER);

            Paragraph parrafo = new Paragraph();

            parrafo.setAlignment(Paragraph.ALIGN_CENTER);

            parrafo.add("Clientes registrados \n\n");

            parrafo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY));

            documento.open();

            documento.add(banner);
            documento.add(parrafo);

            PdfPTable tabla_clientes = new PdfPTable(4);

            tabla_clientes.addCell("ID");
            tabla_clientes.addCell("Nombre");
            tabla_clientes.addCell("Email");
            tabla_clientes.addCell("Teléfono");
            tabla_clientes.addCell("DIrección");

            try
            {

                Connection cn = Conexion.db();
                PreparedStatement pst = cn.prepareStatement("Select * from clientes");

                ResultSet rs = pst.executeQuery();

                if (rs.next())
                {

                    do
                    {
                        tabla_clientes.addCell(rs.getString(1));
                        tabla_clientes.addCell(rs.getString(2));
                        tabla_clientes.addCell(rs.getString(3));
                        tabla_clientes.addCell(rs.getString(4));
                        tabla_clientes.addCell(rs.getString(5));

                    } while (rs.next());
                }

                documento.add(tabla_clientes);

            } catch (DocumentException | SQLException e)
            {
                System.err.println("Error al cargar clientes" + e);
            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Lista de clientes creada con éxito");

        } catch (DocumentException | IOException e)
        {
            System.err.println("Error en la ruta de la imagen del pdf" + e);
            JOptionPane.showMessageDialog(null, "Error en el pdf, contacte al administrador");
        }

    }//GEN-LAST:event_button_imprimirActionPerformed

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
            java.util.logging.Logger.getLogger(Capturista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Capturista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Capturista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Capturista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Capturista().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_gestionar_clientes;
    private javax.swing.JButton button_imprimir;
    private javax.swing.JButton button_registrar_clientes;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel label_footer;
    private javax.swing.JLabel label_registrar_cliente;
    private javax.swing.JLabel label_titulo;
    private javax.swing.JLabel label_wallpaper;
    // End of variables declaration//GEN-END:variables
}
