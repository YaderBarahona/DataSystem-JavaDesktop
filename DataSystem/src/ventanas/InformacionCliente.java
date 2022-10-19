/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import clases.Conexion;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yader Jr
 */
public class InformacionCliente extends javax.swing.JFrame {

    /**
     * Creates new form InformacionCliente
     */
    String user = "";
    int IDCliente_update;
    public static int ID_equipo = 0;

    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public InformacionCliente() {
        initComponents();
        setSize(630, 450);
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

            PreparedStatement pst = cn.prepareStatement(
                    "select id_equipo, tipo_equipo,marca,estatus from equipos where id_cliente =" + IDCliente_update);

            ResultSet rs = pst.executeQuery();

            //llamamos a la tabla y dentro del constructor metemos el objeto model de la clase DefaultTableModel
            table_clientes_registrados = new JTable(model);
            //ingresamos la tabla en el scrollpane mediante el semtodo setViewportView esto ya que no sabemos cuantos registros contendra dicha tabla y con esto para que genere una barra(scroll) por si tiene muchos registros y no se alcanza a ver
            jScrollPane1.setViewportView(table_clientes_registrados);

            //metodo para añadir columnas y asignar un nombre dentro de la tabla
            model.addColumn("ID equipo");
            model.addColumn("Tipo de equipo");
            model.addColumn("Marca");
            model.addColumn("Estatus ");

            //estructura para llenar los datos en la tabla
            //si el objeto rs con el metodo next  encontró resultados entonces
            while (rs.next())
            {

                //array de tipo object  de 5 espacios referente a las 5 columnas
                Object[] fila = new Object[4];

                //for para mostrar el array con lo que se guarde dentro de este
                for (int i = 0; i < 4; i++)
                {
                    //traemos al vector fila e indicamos que dentro del array en la posicion [i]
                    //será igual al objeto rs con el metodo getObject ya que el array es de tipo Object
                    //i+1 porque el indice del objeto rs comienza en 1
                    fila[i] = rs.getObject(i + 1);
                }
                //agregamos la fila con los datos obtenidos de la db dentro del objeto model
                model.addRow(fila);
            }
            //cerramos la conexion
            cn.close();

        } catch (SQLException e)
        {
            System.err.println("Error en el llenado de la tabla. " + e);
            JOptionPane.showMessageDialog(null, "Error al mostrar la información, contacte al administrador");
        }

        try
        {
            Connection cn2 = Conexion.db();
            //instruccion hacia la db para indicar cual id estamos consultando
            PreparedStatement pst = cn2.prepareStatement("select * from clientes where id_cliente = '" + IDCliente_update + "'");

            ResultSet rs = pst.executeQuery();

            if (rs.next())
            {

                //cambiamos el titulo  mediante el metodo settTitle por el objeto rs y el metodo getString e indicamos la columna de la db(nombre_cliente)
                setTitle("Información del usuario " + rs.getString("nombre_cliente") + " - sesión de " + user);

                label_header.setText("Informacion del cliente " + rs.getString("nombre_cliente"));

                //a cada txt le ponemos el nombre y demas propiedades del registro que haya seleccionado de la tabla esto por el metodo setText y llamando al objeto rs con el metodo getString (campo o columna que se vaya a recuperar de la db)
                txt_nombre.setText(rs.getString("nombre_cliente"));
                txt_email.setText(rs.getString("email_cliente"));
                txt_telefono.setText(rs.getString("tel_cliente"));
                txt_direccion.setText(rs.getString("dir_cliente"));
                txt_ultima_modificacion.setText(rs.getString("ultima_modificacion"));

            }

            cn2.close();

        } catch (SQLException e)
        {
            System.err.println("Error en cargar cliente" + e);
            JOptionPane.showMessageDialog(null, "Error al cargar, contacte al administrador");
        }

        table_clientes_registrados.addMouseListener(new MouseAdapter() {

            @Override
            //metodo mouseCliked 
            //varaible "e" contendra el evento que se esta generando
            public void mouseClicked(MouseEvent e) {

                //variable fila de tipo int esta contendrá la tabla con el metodo rowAtPoint para obtener la fila que se haya seleccionado 
                int fila_point = table_clientes_registrados.rowAtPoint(e.getPoint());
                //variable  columna de tipo int para indicar la columna 2 que contiene el nombre del usuario y recuperarlo
                int columna_point = 0;

                //condicional para cuando se obtenga la fila y la columna
                if (fila_point > -1)
                {
                    //variable user_update ya creada, contendrá mediante un castin de tipo String mediante el objeto model con el metodo getValueAt introducimos las variables que contienen la fila y columna selecionada(fila_point, columna_point) 
                    ID_equipo = (int) model.getValueAt(fila_point, columna_point);

                    //objeto de la interfaz InformacionUsuario para abrir la nueva intefaz
                        InformacionEquipos informacion_equipo = new InformacionEquipos();
                    //lobjeto informacion_usuario con el metodo setVisible con valor true para abrir o mostrar la intefaz
                        informacion_equipo.setVisible(true);
                }
            }
        });

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

        label_header = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        button_registrar_equipo = new javax.swing.JButton();
        button_actualizar_cliente = new javax.swing.JButton();
        button_imprimir = new javax.swing.JButton();
        label_footer = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        txt_telefono = new javax.swing.JTextField();
        txt_direccion = new javax.swing.JTextField();
        txt_ultima_modificacion = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_clientes_registrados = new javax.swing.JTable();
        label_wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        label_header.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        label_header.setForeground(new java.awt.Color(255, 255, 255));
        label_header.setText("Información del cliente");
        getContentPane().add(label_header, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 5, -1, -1));

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Email");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Teléfono");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("DIrección");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, 20));

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Ultima modificación");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, -1, -1));

        button_registrar_equipo.setBackground(new java.awt.Color(153, 153, 255));
        button_registrar_equipo.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        button_registrar_equipo.setText("Registrar equipo");
        button_registrar_equipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_registrar_equipoActionPerformed(evt);
            }
        });
        getContentPane().add(button_registrar_equipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 260, 180, 35));

        button_actualizar_cliente.setBackground(new java.awt.Color(153, 153, 255));
        button_actualizar_cliente.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        button_actualizar_cliente.setText("Actualizar cliente");
        button_actualizar_cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_actualizar_clienteActionPerformed(evt);
            }
        });
        getContentPane().add(button_actualizar_cliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 310, 180, 35));

        button_imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/impresora.png"))); // NOI18N
        button_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_imprimirActionPerformed(evt);
            }
        });
        getContentPane().add(button_imprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 250, 130, 100));

        label_footer.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        label_footer.setForeground(new java.awt.Color(0, 0, 0));
        label_footer.setText("Creado por Yader Barahona");
        getContentPane().add(label_footer, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 390, -1, -1));

        txt_nombre.setBackground(new java.awt.Color(153, 153, 255));
        txt_nombre.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txt_nombre.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 200, 25));

        txt_email.setBackground(new java.awt.Color(153, 153, 255));
        txt_email.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txt_email.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 200, 25));

        txt_telefono.setBackground(new java.awt.Color(153, 153, 255));
        txt_telefono.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txt_telefono.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 200, 25));

        txt_direccion.setBackground(new java.awt.Color(153, 153, 255));
        txt_direccion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txt_direccion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_direccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 200, 25));

        txt_ultima_modificacion.setEditable(false);
        txt_ultima_modificacion.setBackground(new java.awt.Color(209, 219, 218));
        txt_ultima_modificacion.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txt_ultima_modificacion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        getContentPane().add(txt_ultima_modificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 200, 25));

        table_clientes_registrados.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(table_clientes_registrados);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 65, 380, 180));
        getContentPane().add(label_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_registrar_equipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_registrar_equipoActionPerformed
        RegistrarEquipo registro_equipo = new RegistrarEquipo();

        registro_equipo.setVisible(true);
    }//GEN-LAST:event_button_registrar_equipoActionPerformed

    private void button_actualizar_clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_actualizar_clienteActionPerformed

        int validacion = 0;
        String nombre, email, telefono, direccion,ultima_modificacion;

        nombre = txt_nombre.getText().trim();
        email = txt_email.getText().trim();
        telefono = txt_telefono.getText().trim();
        direccion = txt_direccion.getText().trim();
        ultima_modificacion = txt_ultima_modificacion.getText().trim();

        if (nombre.equals(""))
        {
            txt_nombre.setBackground(Color.red);
            validacion++;
        }

        if (email.equals(""))
        {
            txt_email.setBackground(Color.red);
            validacion++;
        }

        if (telefono.equals(""))
        {
            txt_telefono.setBackground(Color.red);
            validacion++;
        }

        if (direccion.equals(""))
        {
            txt_direccion.setBackground(Color.red);
            validacion++;
        }

        if (validacion == 0)
        {

            try
            {
                Connection cn = Conexion.db();
                PreparedStatement pst = cn.prepareStatement("update clientes set nombre_cliente = ?, email_cliente = ?, tel_cliente = ?, dir_cliente = ?, ultima_modificacion = ? where id_cliente =" + IDCliente_update);

                pst.setString(1, nombre);
                pst.setString(2, email);
                pst.setString(3, telefono);
                pst.setString(4, direccion);
                pst.setString(5, user);

                pst.executeUpdate();

                cn.close();
                
                Limpiar();

                txt_nombre.setBackground(Color.green);
                txt_email.setBackground(Color.green);
                txt_telefono.setBackground(Color.green);
                txt_direccion.setBackground(Color.green);

                JOptionPane.showMessageDialog(null, "Cliente actualizado con éxito");

                this.dispose();

            } catch (HeadlessException | SQLException e)
            {
                System.err.println("Error en la instruccion actualizar");
            }

        } else
        {
            JOptionPane.showMessageDialog(null, "Por favor, reellena todo los campos");
        }
    }//GEN-LAST:event_button_actualizar_clienteActionPerformed

    private void button_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_imprimirActionPerformed
        Document documento = new Document();

        try
        {

            // (user.home) donde se generará el documento ya que no se sabe en que computadora se ejecturá que recuperé la ruta del usuario(nombre del usuario) que esta utilizando el programa
            String ruta = System.getProperty("user.home");

            //primer parametro el objeto documento, luego creamos un objeto de la clase FOS y dentro del constructor añadimos la variable ruta + la direccion donde se guardará el archivo con extension pdf
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/" + txt_nombre.getText().trim() + ".pdf"));

            //metodo para colocar la imagen del banner del archivo pdf (com.itextpdf.text.Image) ya que hay otra libreria con el mismo nombre aunque sean de paquetes diferentes"Image" entonces causaria un conflicto al llamarla desde arriba
            com.itextpdf.text.Image banner = com.itextpdf.text.Image.getInstance("src/images/BannerPDF.jpg");

            //largo, escala de visualizacion
            banner.scaleToFit(650, 1000);

            //alineacion centro mediante la libreria chunk
            banner.setAlignment(Chunk.ALIGN_CENTER);

            //objeto de la clase Parapraph para darle fomato al texto
            Paragraph parrafo = new Paragraph();
            //alineacion del texto 
            parrafo.setAlignment(Paragraph.ALIGN_CENTER);
            //añadir una leyenda
            parrafo.add("Información del cliente\n\n");
            //tipo de letra, tamaño, estilo, color
            parrafo.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY));

            //  parrafo.add("Equipos registrados\n");
            documento.open();

            //añadimos el objeto que contiene la imagen del banner al objeto documento
            documento.add(banner);
            documento.add(parrafo);

            //objeto de la clase PdfPTable para añadir el numero de columnas de la tabla donde se mostraran los datos
            PdfPTable tabla_cliente = new PdfPTable(5);

            tabla_cliente.addCell("ID");
            tabla_cliente.addCell("Nombre");
            tabla_cliente.addCell("Email");
            tabla_cliente.addCell("Teléfono");
            tabla_cliente.addCell("DIrección");

            try
            {
                Connection cn = Conexion.db();
                PreparedStatement pst = cn.prepareStatement("Select * from clientes where id_cliente= " + IDCliente_update);

                ResultSet rs = pst.executeQuery();

                if (rs.next())
                {

                    //estructura do while ya que no se sabe cuantos valores hay, el ciclo se ciclará hasta que ya no haya mas registros se cumpla la instruccion a la db
                    do
                    {

                        tabla_cliente.addCell(rs.getString(1));
                        tabla_cliente.addCell(rs.getString(2));
                        tabla_cliente.addCell(rs.getString(3));
                        tabla_cliente.addCell(rs.getString(4));
                        tabla_cliente.addCell(rs.getString(5));

                    } while (rs.next());

                    documento.add(tabla_cliente);
                }
                
            

                //leyenda 2 de la segunda tabla de los equipo registrados del cliente
                Paragraph parrafo2 = new Paragraph();

                parrafo2.setAlignment(Paragraph.ALIGN_CENTER);
                parrafo2.add("\n\n Equipos registrado\n\n");
                parrafo2.setFont(FontFactory.getFont("Tahoma", 14, Font.BOLD, BaseColor.DARK_GRAY));

                documento.add(parrafo2);

                PdfPTable tabla_equipos = new PdfPTable(4);

                tabla_equipos.addCell("ID equipo");
                tabla_equipos.addCell("Tipo");
                tabla_equipos.addCell("Marca");
                tabla_equipos.addCell("Estatus");

                try
                {
                    Connection cn2 = Conexion.db();
                    PreparedStatement pst2 = cn.prepareStatement("select id_equipo, tipo_equipo, marca, estatus from equipos where id_cliente = " + IDCliente_update);

                    ResultSet rs2 = pst2.executeQuery();

                    if (rs2.next())
                    {

                        do
                        {

                            tabla_equipos.addCell(rs2.getString(1));
                            tabla_equipos.addCell(rs2.getString(2));
                            tabla_equipos.addCell(rs2.getString(3));
                            tabla_equipos.addCell(rs2.getString(4));

                        } while (rs2.next());
                        documento.add(tabla_equipos);
                    }
                    
                    cn.close();
                    cn2.close();

                } catch (SQLException e)
                {
                    System.err.println("Error al cargar equipos"+e);
                }

            } catch (SQLException e)
            {
                System.out.println("Error en obtener los datos del cliente" + e);
            }
            documento.close();
            JOptionPane.showMessageDialog(null, "Reporte creado");

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
            java.util.logging.Logger.getLogger(InformacionCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(InformacionCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(InformacionCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(InformacionCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InformacionCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_actualizar_cliente;
    private javax.swing.JButton button_imprimir;
    private javax.swing.JButton button_registrar_equipo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel label_footer;
    private javax.swing.JLabel label_header;
    private javax.swing.JLabel label_wallpaper;
    private javax.swing.JTable table_clientes_registrados;
    private javax.swing.JTextField txt_direccion;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_telefono;
    private javax.swing.JTextField txt_ultima_modificacion;
    // End of variables declaration//GEN-END:variables

    public void Limpiar(){
        txt_nombre.setText("");
        txt_email.setText("");
        txt_telefono.setText("");
        txt_direccion.setText("");
        txt_ultima_modificacion.setText("");
        
    }

}
