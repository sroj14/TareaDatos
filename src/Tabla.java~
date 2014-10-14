import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;                    
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;


/**__________________________________________INICIO CLASE TABLA________________________________________________________*/

public class Tabla extends JFrame{

/**________________________________________ATRIBUTOS DE LA CLASE TABLA________________________________________________*/
    
    /**__________________Etiquetas utilizadas____________________________*/
     private static JLabel imagen = new JLabel();


/**____________________________________FIN ATRIBUTOS DE LA CLASE TABLA________________________________________________*/
    

/**_________________________________________CONSTRUCTOR DE INTERFAZ_____________________________________________________*/

    public Tabla() {
        
        super("Historial");
        this.setSize(1200,700);
         
        
        Vector<String> columnas = new Vector<String>();/* Se crea una instancia de la clase Vector llamada 'columnas' */
        
        /* Agregar datos al vector, estos datos vendrán a ser las columnas de la tabla.*/
        
        columnas.add("Nombre");
        columnas.add("Correo Electrónico");
        columnas.add("Fecha");
        columnas.add("Hora");
        columnas.add("Tipo de Cliente");
 
         
        /** Crear una instancia de la clase Vector llamada 'filas' , este vector tendrá todas las filas de la tabla.*/
        
        Vector<Vector> filas = new Vector<Vector>();
        Vector<String> fila = new Vector<String>();
         
        /**Agregar datos a las filas*/
        
        int cont = 5; 
        
       while (cont!=0){

            fila.add("Jose Daniel");
            fila.add("ccc@live.com");
            fila.add("15-09-2014");
            fila.add("3:00 pm");
            fila.add("adulto");  
            cont--;
            filas.add(fila);
        }
        
        
        
        
        /**Crear fila con los datos agregados*/
             
        JTable tbl = new JTable(filas,columnas);
        JScrollPane panel =new JScrollPane(tbl);
       
        this.getContentPane().add(panel);
        this.setVisible(true);

/**______________________________________COLOCANDO LOGO DE TABLA_______________________________________________________*/
  
 
      
       // BufferedImage image = new ImageIcon(getClass().getResource("/imagenes/history.jpg"));

      //  BufferedImage image  = null;
      
     //   try { 
     //      image= ImageIO.read(new File("/imagenes/history.jpg"));/**obtener imagen*/

      ///} catch (IOException e) {  
     //       e.printStackTrace();
      ///  }
    ImageIcon iid = new ImageIcon(this.getClass().getResource("/imagenes/history.jpg"));

       setIconImage( iid.getImage());  /**establecer imagen como icono*/
      imagen.setIcon(new ImageIcon( iid.getImage()));     

/**______________________________________COLOCANDO LOGO DE TABLA_______________________________________________________*/
     

    }

public static void main(String []args){
        Tabla tabla = new Tabla(); 
}
                
  } 
  