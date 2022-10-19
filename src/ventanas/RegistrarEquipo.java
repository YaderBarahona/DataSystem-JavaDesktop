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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

//libreria para la fecha y hora
import java.util.Calendar;

/**
 *
 * @author Yader Jr
 */
public class RegistrarEquipo extends javax.swing.JFrame {

    /**
     * Creates new form RegistrarEquipo
     */
     int IDCliente_update;
    String user = "", nom_cliente = "";
   
    
    public RegistrarEquipo() {
        initComponents();
        
          setSize(630, 445);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        
         

        user = Login.user;
        IDCliente_update = GestionarClientes.IDCliente_update;

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        ImageIcon wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");

        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(label_wallpaper.getWidth(), label_wallpaper.getHeight(),
                Image.SCALE_DEFAULT));

        label_wallpaper.setIcon(icono);
        this.repaint();
        
        
        
        try
        {
            Connection cn = Conexion.db();
            //instruccion hacia la db para indicar cual id estamos consultando
            PreparedStatement pst = cn.prepareStatement("select nombre_cliente from clientes where id_cliente = '" + IDCliente_update + "'");

            ResultSet rs = pst.executeQuery();

            if (rs.next())
            {
                nom_cliente = rs.getString("nombre_cliente");

            }

            cn.close();

        } catch (SQLException e)
        {
            System.err.println("Error en cargar cliente" + e);
            JOptionPane.showMessageDialog(null, "Error al cargar, contacte al administrador");
        }
        
        txt_nombre.setText(nom_cliente);
         setTitle("Registrar nuevo equipo para " + nom_cliente);
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

        txt_nombre = new javax.swing.JTextField();
        txt_modelo = new javax.swing.JTextField();
        txt_numeroserie = new javax.swing.JTextField();
        combobox_tipoequipo = new javax.swing.JComboBox<>();
        combobox_marca = new javax.swing.JComboBox<>();
        label_registrar = new javax.swing.JButton();
        label_header = new javax.swing.JLabel();
        label_observaciones = new javax.swing.JLabel();
        label_footer = new javax.swing.JLabel();
        label_nombre = new javax.swing.JLabel();
        label_tipoequipo = new javax.swing.JLabel();
        label_marca = new javax.swing.JLabel();
        label_modelo = new javax.swing.JLabel();
        label_numeroserie = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane_observaciones = new javax.swing.JTextPane();
        label_wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_nombre.setEditable(false);
        txt_nombre.setBackground(new java.awt.Color(153, 153, 255));
        txt_nombre.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txt_nombre.setForeground(new java.awt.Color(0, 0, 0));
        txt_nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt_nombre.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 200, 25));

        txt_modelo.setBackground(new java.awt.Color(153, 153, 255));
        txt_modelo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txt_modelo.setForeground(new java.awt.Color(0, 0, 0));
        txt_modelo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 200, 25));

        txt_numeroserie.setBackground(new java.awt.Color(153, 153, 255));
        txt_numeroserie.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txt_numeroserie.setForeground(new java.awt.Color(0, 0, 0));
        txt_numeroserie.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_numeroserie, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 200, 25));

        combobox_tipoequipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Laptop", "Desktop", "Impresora", "Multifuncional" }));
        getContentPane().add(combobox_tipoequipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 100, -1));

        combobox_marca.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Acer", "alienware", "Apple", "Asus", "Brother", "Dell ", "HP", "Lenovo" }));
        getContentPane().add(combobox_marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 195, 70, -1));

        label_registrar.setBackground(new java.awt.Color(153, 153, 255));
        label_registrar.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        label_registrar.setForeground(new java.awt.Color(255, 255, 255));
        label_registrar.setText("Registrar equipo");
        label_registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                label_registrarActionPerformed(evt);
            }
        });
        getContentPane().add(label_registrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(405, 310, 200, 35));

        label_header.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        label_header.setForeground(new java.awt.Color(255, 255, 255));
        label_header.setText("Registro de equipo");
        getContentPane().add(label_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 5, -1, -1));

        label_observaciones.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        label_observaciones.setForeground(new java.awt.Color(255, 255, 255));
        label_observaciones.setText("Daño reportado y observaciones");
        getContentPane().add(label_observaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 50, -1, -1));

        label_footer.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        label_footer.setForeground(new java.awt.Color(0, 0, 0));
        label_footer.setText("Creado por Yader Barahona");
        getContentPane().add(label_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, -1, -1));

        label_nombre.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        label_nombre.setForeground(new java.awt.Color(255, 255, 255));
        label_nombre.setText("Nombre del cliente");
        getContentPane().add(label_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 55, -1, -1));

        label_tipoequipo.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        label_tipoequipo.setForeground(new java.awt.Color(255, 255, 255));
        label_tipoequipo.setText("Tipo de equipo");
        getContentPane().add(label_tipoequipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, -1, -1));

        label_marca.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        label_marca.setForeground(new java.awt.Color(255, 255, 255));
        label_marca.setText("Marca");
        getContentPane().add(label_marca, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 175, -1, -1));

        label_modelo.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        label_modelo.setForeground(new java.awt.Color(255, 255, 255));
        label_modelo.setText("Modelo");
        getContentPane().add(label_modelo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        label_numeroserie.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        label_numeroserie.setForeground(new java.awt.Color(255, 255, 255));
        label_numeroserie.setText("Número de serie");
        getContentPane().add(label_numeroserie, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));

        jTextPane_observaciones.setBackground(new java.awt.Color(255, 255, 255));
        jTextPane_observaciones.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jTextPane_observaciones.setForeground(new java.awt.Color(0, 0, 0));
        jScrollPane2.setViewportView(jTextPane_observaciones);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(255, 70, 350, 230));
        getContentPane().add(label_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 445));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void label_registrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_label_registrarActionPerformed
       
        int validacion = 0;
        String modelo, numero_serie, dia_ingreso, mes_ingreso,annio_ingreso,estatus,obervaciones;
        int tipo_equipo_combo, marca_combo;
        String tipo_equpo="", marca= "";
        
        
        modelo = txt_modelo.getText().trim();
        numero_serie = txt_numeroserie.getText().trim();
        
        obervaciones = jTextPane_observaciones.getText();
        
        tipo_equipo_combo = combobox_tipoequipo.getSelectedIndex()+1;
        marca_combo = combobox_marca.getSelectedIndex()+1;
        
        estatus = "Nuevo ingreso";
        
        //instanciamiento del objeto calendario de tipo de la clase Calendar
        Calendar calendario = Calendar.getInstance();
        
        //mediante la variable dia_ingreso hacemos un parseo de String a int y luego a cadena de caracteres con el metodo toString() para poder obtener el dia 
        //dentro del toString llamamos a la variable calendario con el metodo get y dentro de este hacemos uso del import de este y traemos a DATE (de la clase Calendar traemos al valor DATE)
        dia_ingreso = Integer.toString(calendario.get(Calendar.DATE));
           mes_ingreso = Integer.toString(calendario.get(Calendar.MONTH));
              annio_ingreso = Integer.toString(calendario.get(Calendar.YEAR));
                
        if (modelo.equals(""))
        {
            txt_modelo.setBackground(Color.red);
            validacion++;
        }
        
        if (numero_serie.equals(""))
        {
         txt_numeroserie.setBackground(Color.red);
         validacion++;
        }
        
        if (obervaciones.equals(""))
        {
        jTextPane_observaciones.setText("Sin observaciones");
        }
        
        if (tipo_equipo_combo==1)
        {
            tipo_equpo = "Laptop";
            
        } else if (tipo_equipo_combo==2)
        {
          tipo_equpo = "Desktop";   
        } else if (tipo_equipo_combo==3)
        {
             tipo_equpo = "Impresora";
        } else if (tipo_equipo_combo == 4)
        {
             tipo_equpo = "Multifuncional";
        }
        
        
            if (marca_combo==1)
        {
             marca = "Acer";
        } else if (marca_combo==2)
        {
             marca = "Allenware";
        } else if (marca_combo==3)
        {
             marca = "Apple";
        } else if (marca_combo==4)
        {
             marca = "Asus";
        } else if (marca_combo==5)
        {
             marca = "Brother";
        }else if (marca_combo==6)
        {
             marca = "Dell";
        }else if (marca_combo==7)
        {
             marca = "HP";
        }else if (marca_combo==8)
        {
             marca = "Lenovo";
        }
            
          
           
        
            if (validacion == 0)
        {
             try{
                
                Connection cn = Conexion.db();
                PreparedStatement pst = cn.prepareStatement("insert into equipos values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
                
               pst.setInt(1, 0);
               pst.setInt(2, IDCliente_update);
               pst.setString(3, tipo_equpo);
               pst.setString(4, marca);
               pst.setString(5, modelo);
               pst.setString(6, numero_serie);
               pst.setString(7, dia_ingreso);
               pst.setString(8, mes_ingreso);
               pst.setString(9, annio_ingreso);
               pst.setString(10, obervaciones);
               pst.setString(11, estatus); 
               pst.setString(12, user);
               pst.setString(13, "");
               pst.setString(14, "");
               
               pst.executeUpdate();
               cn.close();

               txt_nombre.setBackground(Color.green);
               txt_modelo.setBackground(Color.green);
               txt_numeroserie.setBackground(Color.green);
               
               JOptionPane.showMessageDialog(null, "Equipo registrado con exito");
               
                this.dispose();
                          
    
              }catch(HeadlessException | SQLException e){
                  System.err.println("Error en ingresar equipo");
                JOptionPane.showMessageDialog(null, "Error en ingresar equipo");
            }
            
        } else  {
               
                JOptionPane.showMessageDialog(null, "Por favor Rellene todos los campos");
            }
        
          
    }//GEN-LAST:event_label_registrarActionPerformed

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
            java.util.logging.Logger.getLogger(RegistrarEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(RegistrarEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(RegistrarEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(RegistrarEquipo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarEquipo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> combobox_marca;
    private javax.swing.JComboBox<String> combobox_tipoequipo;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane_observaciones;
    private javax.swing.JLabel label_footer;
    private javax.swing.JLabel label_header;
    private javax.swing.JLabel label_marca;
    private javax.swing.JLabel label_modelo;
    private javax.swing.JLabel label_nombre;
    private javax.swing.JLabel label_numeroserie;
    private javax.swing.JLabel label_observaciones;
    private javax.swing.JButton label_registrar;
    private javax.swing.JLabel label_tipoequipo;
    private javax.swing.JLabel label_wallpaper;
    private javax.swing.JTextField txt_modelo;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_numeroserie;
    // End of variables declaration//GEN-END:variables
}