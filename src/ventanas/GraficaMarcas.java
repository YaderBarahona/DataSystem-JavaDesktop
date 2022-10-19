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
public class GraficaMarcas extends javax.swing.JFrame {

    /**
     * Creates new form GraficaEstatus
     */
    String user;

    //vectores para representar los estatus en la grafica
    int[] vector_marcas_cantidad = new int[11];
    String[] vector_marcas_nombre = new String[11];

    //variables referentes a las 11 marcas disponibles en el software
    int hp, lenovo, dell, toshiba, alienware, acer, apple, brother, samsung, asus, xerox;

    public GraficaMarcas() {
        initComponents();
        user = Login.user;

        setSize(550, 450);
        setResizable(false);
        setTitle("Técnico - sesión de " + user);
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
            PreparedStatement pst = cn.prepareStatement("select marca, count(marca) as Marcas from equipos group by marca");

            ResultSet rs = pst.executeQuery();

            if (rs.next())
            {
                int posicion = 0;

                do
                {
                    vector_marcas_nombre[posicion] = rs.getString(1);
                    vector_marcas_cantidad[posicion] = rs.getInt(2);

                    //estructura condicional anidada para poder vaciar los valores que vienen desde la db hacia los vectores  para poder graficar las marcas en la interfaz                    
                    if (vector_marcas_nombre[posicion].equalsIgnoreCase("Acer"))
                    {
                        acer = vector_marcas_cantidad[posicion];
                    } else if (vector_marcas_nombre[posicion].equalsIgnoreCase("Apple"))
                    {
                        apple = vector_marcas_cantidad[posicion];
                    } else if (vector_marcas_nombre[posicion].equalsIgnoreCase("Alienware"))
                    {
                        alienware = vector_marcas_cantidad[posicion];
                    } else if (vector_marcas_nombre[posicion].equalsIgnoreCase("Asus"))
                    {
                        asus = vector_marcas_cantidad[posicion];
                    } else if (vector_marcas_nombre[posicion].equalsIgnoreCase("Brother"))
                    {
                        brother = vector_marcas_cantidad[posicion];
                    } else if (vector_marcas_nombre[posicion].equalsIgnoreCase("Dell"))
                    {
                        dell = vector_marcas_cantidad[posicion];
                    } else if (vector_marcas_nombre[posicion].equalsIgnoreCase("HP"))
                    {
                        hp = vector_marcas_cantidad[posicion];
                    } else if (vector_marcas_nombre[posicion].equalsIgnoreCase("Lenovo"))
                    {
                        lenovo = vector_marcas_cantidad[posicion];
                    } else if (vector_marcas_nombre[posicion].equalsIgnoreCase("Samsung"))
                    {
                        samsung = vector_marcas_cantidad[posicion];
                    } else if (vector_marcas_nombre[posicion].equalsIgnoreCase("Toshiba"))
                    {
                        toshiba = vector_marcas_cantidad[posicion];
                    } else if (vector_marcas_nombre[posicion].equalsIgnoreCase("Xerox"))
                    {
                        xerox = vector_marcas_cantidad[posicion];
                    }

                    posicion++;
                } while (rs.next());
            }

        } catch (SQLException e)
        {
            System.err.println("Error en conexion a la db");
            JOptionPane.showConfirmDialog(null, "Error en mostrar grafica de marcas, contacte al administrador" + e);
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
        jLabel2.setText("Gráfica de marcas");
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
            java.util.logging.Logger.getLogger(GraficaMarcas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(GraficaMarcas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(GraficaMarcas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(GraficaMarcas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GraficaMarcas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel label_wallpaper;
    // End of variables declaration//GEN-END:variables

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        int total_marcas = acer + apple + dell + hp + alienware + asus + brother + dell + lenovo + samsung + toshiba + xerox;

        //formula para obtener el tamaño de cada seccion 
        int grados_acer = acer * 360 / total_marcas;
        int grados_alienware = alienware * 360 / total_marcas;
        int grados_apple = apple * 360 / total_marcas;
        int grados_hp = hp * 360 / total_marcas;
        int grados_dell = dell * 360 / total_marcas;
        int grados_asus = asus * 360 / total_marcas;
        int grados_brother = brother * 360 / total_marcas;
        int grados_lenovo = lenovo * 360 / total_marcas;
        int grados_sansumg = samsung * 360 / total_marcas;
        int grados_toshiba = toshiba * 360 / total_marcas;
        int grados_xerox = xerox * 360 / total_marcas;

        
       //color morado para la marca acer
        g.setColor(new Color(175, 122, 197));
        //la grafica empieza desde el grado 0 hasta los grados de la marca en este caso acer
        g.fillArc(25, 100, 270, 270, 0, grados_acer);
        g.fillRect(310, 120, 20, 20);
        g.drawString(acer + " de Acer", 340, 135);
        
        //color verde para la marca alienware
        g.setColor(new Color(0, 255, 0));
        //ahora empieza desde los grados de la marca anterior hasta la marca de este arco
        g.fillArc(25, 100, 270, 270, grados_acer, grados_alienware);
        g.fillRect(310, 150, 20, 20);
        g.drawString(alienware + " de Alienware", 340, 165);
        
         //color verde agua para la marca apple
        g.setColor(new Color(0, 255, 255));
         //ahora empieza desde los grados de la marca anterior mas la anterior a esa hasta la marca de este arco
        g.fillArc(25, 100, 270, 270, grados_acer+grados_alienware, grados_apple);
        g.fillRect(310, 180, 20, 20);
        g.drawString(apple + " de Apple", 340, 195);
        
         //color Azul para la marca asus
        g.setColor(new Color(55, 0, 255));
        g.fillArc(25, 100, 270, 270, grados_acer+grados_alienware+grados_apple, grados_asus);
        g.fillRect(310, 210, 20, 20);
        g.drawString(asus + " de Asus", 340, 225);
        
         //color blanco para la marca brother
        g.setColor(new Color(255, 255, 255));
        g.fillArc(25, 100, 270, 270, grados_acer+grados_alienware+grados_apple+grados_asus, grados_brother);
        g.fillRect(310, 240, 20, 20);
        g.drawString(brother + " de Brother", 340, 255);
        
         //color amarillo para la marca dell
        g.setColor(new Color(247, 220, 111));
        g.fillArc(25, 100, 270, 270, grados_acer+grados_alienware+grados_apple+grados_asus+grados_brother, grados_dell);
        g.fillRect(310, 270, 20, 20);
        g.drawString(dell + " de Dell", 340, 285);
        
         //color azul marino para la marca hp
        g.setColor(new Color(21, 42, 160));
        g.fillArc(25, 100, 270, 270, grados_acer+grados_alienware+grados_apple+grados_asus+grados_brother+grados_dell, grados_hp);
        g.fillRect(310, 300, 20, 20);
        g.drawString(hp + " de HP", 340, 315);
        
         //color naranja para la marca lenovo
        g.setColor(new Color(215, 96, 0));
        g.fillArc(25, 100, 270, 270, grados_acer+grados_alienware+grados_apple+grados_asus+grados_brother+grados_dell+grados_hp, grados_lenovo);
        g.fillRect(310, 330, 20, 20);   
        g.drawString(lenovo + " de Lenovo", 340, 345);
        
         //color rosa para la marca samsung
        g.setColor(new Color(215, 96, 140));
        g.fillArc(25, 100, 270, 270, grados_acer+grados_alienware+grados_apple+grados_asus+grados_brother+grados_dell+grados_hp+grados_lenovo, grados_sansumg);
        //segunda columna de marcas
        g.fillRect(430, 120, 20, 20);
        g.drawString(samsung + " de Samsung", 460, 135);
        
         //color amarillo suave para la marca toshiba
        g.setColor(new Color(215, 196, 25));
        g.fillArc(25, 100, 270, 270, grados_acer+grados_alienware+grados_apple+grados_asus+grados_brother+grados_dell+grados_hp+grados_lenovo+grados_sansumg, grados_toshiba);
        g.fillRect(430, 150, 20, 20);
        g.drawString(toshiba + " de Tshiba", 460, 165);
        
         //color celeste para la marca xerox
        g.setColor(new Color(93, 173, 226));
        g.fillArc(25, 100, 270, 270, grados_acer+grados_alienware+grados_apple+grados_asus+grados_brother+grados_dell+grados_hp+grados_lenovo+grados_sansumg+grados_toshiba, grados_xerox);
        g.fillRect(430, 180, 20, 20);
        g.drawString(xerox + " de Xerox", 460, 195);



    }

}
