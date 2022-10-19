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
import javax.swing.WindowConstants;

import java.sql.*;
import clases.Conexion;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;

/**
 *
 * @author Yader Jr
 */
public class GraficaEstatus extends javax.swing.JFrame {

    /**
     * Creates new form GraficaEstatus
     */
    
    String user;
    int nuevo_ingreso, no_reparado, en_revision, reparado, entregado;
    
    //vectores para representar los estatus en la grafica
    String [] vector_estatus_nombre = new String [5];
    int [] vector_estatus_cantidad = new int [5];
    
    public GraficaEstatus() {
        initComponents();
        user= Login.user;
        
        setSize(550,450);
        setResizable(false);
        setTitle("Técnico - sesión de "+user);
        setLocationRelativeTo(null);
        
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        ImageIcon wallpaper = new ImageIcon("src/images/wallpaperPrincipal.jpg");
        
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(label_wallpaper.getWidth(), label_wallpaper.getHeight(),
                Image.SCALE_DEFAULT));
        
        label_wallpaper.setIcon(icono);
        
        this.repaint();
        
        try
        {
            
            Connection cn = Conexion.db();
            //instruccion hacia la db para saber la cantidad de equipos por el estatus
            PreparedStatement pst = cn.prepareStatement("select estatus, count(estatus) as Cantidad from equipos group by estatus");
            
            ResultSet rs = pst.executeQuery();
            
            if (rs.next())
            {
                //variable para recorrer las posiciones de los vectores
                int posicion = 0;
                
                do
                {

                    //llamamos a los vectores y en el primer vector se guardaran los estatus de tipo string con el numero de columna 1 de la instruccion a la db
                    //en el segundo vector se guardaran lasc cantidades de tipo int con el numero 2 de la columna de la instruccion a la db
                    vector_estatus_nombre[posicion] = rs.getString(1);
                    vector_estatus_cantidad[posicion] = rs.getInt(2);
                    
                    //condicional para guardar en variables de tipo int  la cantidad de equipos asignadoa cada uno de los estatus
                    if (vector_estatus_nombre[posicion].equalsIgnoreCase("En revision"))
                    {
                        en_revision = vector_estatus_cantidad[posicion];
                        
                    } else  if (vector_estatus_nombre[posicion].equalsIgnoreCase("Entregado"))
                    {
                        entregado = vector_estatus_cantidad[posicion];
                        
                    } else  if (vector_estatus_nombre[posicion].equalsIgnoreCase("No reparado"))
                    {
                        no_reparado = vector_estatus_cantidad[posicion];
                        
                    } else  if (vector_estatus_nombre[posicion].equalsIgnoreCase("Nuevo ingreso"))
                    {
                        nuevo_ingreso = vector_estatus_cantidad[posicion];
                        
                    } else  if (vector_estatus_nombre[posicion].equalsIgnoreCase("Reparado"))
                    {
                        reparado = vector_estatus_cantidad[posicion];
                        
                    }
                    
                    posicion++;
                    
                } while (rs.next());
                
            }
            
        } catch (SQLException e)
        {
            System.err.println("Error en conexion a la db");
            JOptionPane.showConfirmDialog(null,"Error en mostrar grafica de estatus, contacte al administrador" +e);
        }
        
        repaint();
        
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

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        label_wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(getIconImage());
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Gráfica de estatus");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel3.setText("Creado por Yader Barahona");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, -1, -1));
        getContentPane().add(label_wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(GraficaEstatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(GraficaEstatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(GraficaEstatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(GraficaEstatus.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraficaEstatus().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel label_wallpaper;
    // End of variables declaration//GEN-END:variables

    //metodo con parametros de tipo int para recibirlos dentro del algoritmo
    public int estatus_mas_repetido(int NuevoIngreso, int NoReparado, int EnRevision, int Reparado, int Entregado){
        
      if(  NuevoIngreso > NoReparado && NuevoIngreso > EnRevision && NuevoIngreso > Reparado && NuevoIngreso > Entregado ){
            return NuevoIngreso;
      } else if (NoReparado>EnRevision && NoReparado>Reparado && NoReparado>Entregado){
            return NoReparado;
      } else if (EnRevision>Reparado&&EnRevision>Entregado){
            return EnRevision;
      } else if(Reparado>Entregado){
          return Reparado; 
      } else {
          return Entregado;
      }
      
    }
    
    
    @Override
    public void paint (Graphics g){
        super.paint(g);
        
        //dentro de la varariable de tipo int(estatus_mas_repetido) se guardará el estatus mas repetido mediante el parametro del metodo(estatus_mas_repetido) y este valor en cantidad(int) a su vez proveniente de la db
        int estatus_mas_repetido = estatus_mas_repetido(nuevo_ingreso, no_reparado, en_revision, reparado, entregado);
        
        //formula para poder graficar las barras
        //dentro de la variable largo_nuevoIngreso guardamos la formula de la variable nuevo_ingreso * 400(este 400 para que las barras no superen los 400px dentro de la interfaz) /(entre) la variable estatus_mas_repetido que guarda al metodo con el parametro del estatus mas repetido
        int largo_NuevoIngreso = nuevo_ingreso * 400 / estatus_mas_repetido;
        int largo_NoReparado = no_reparado * 400 / estatus_mas_repetido;
        int largo_EnRevision = en_revision * 400 / estatus_mas_repetido;
        int largo_Reparado = reparado * 400 / estatus_mas_repetido;
        int largo_Entregado = entregado * 400 / estatus_mas_repetido;
        
        //color amarillo para la barra NuevoIngreso
        g.setColor(new Color(20,248,0));
        //metodo para crear el rectangulo de la barra
        g.fillRect(100, 100, largo_NuevoIngreso, 40);
        //metodo para escribir un texto referente a la barra
        g.drawString("Nuevo ingreso", 10, 118);
        g.drawString("Cantidad: "+nuevo_ingreso, 10, 133);
        
          //color rojo para la barra Noreparado
        g.setColor(new Color(255,0,0));
        g.fillRect(100, 150, largo_NoReparado, 40);
        g.drawString("No reparado", 10, 168);
        g.drawString("Cantidad: "+no_reparado, 10, 183);
        
          //color negro para la barra Enrevision
        g.setColor(new Color(0,0,0));
        g.fillRect(100, 200, largo_EnRevision, 40);
        g.drawString("En revisión", 10, 218);
        g.drawString("Cantidad: "+en_revision, 10, 233);
        
         //color blanco para la barra Enrevision
        g.setColor(new Color(255,255,255));
        g.fillRect(100, 250, largo_Reparado, 40);
        g.drawString("Reparado", 10, 268);
        g.drawString("Cantidad: "+reparado, 10, 283);
        
         //color verde para la barra Entregado
        g.setColor(new Color(0,85,0));  
        g.fillRect(100, 300, largo_Entregado, 40);
        g.drawString("Entregado", 10, 318);
        g.drawString("Cantidad: "+entregado, 10, 333);
        
    }

}