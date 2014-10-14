import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;


/**__________________________________________INICIO CLASE TABLA________________________________________________________*/

public class Tabla extends JFrame{

/**________________________________________ATRIBUTOS DE LA CLASE TABLA________________________________________________*/
    
/**__________________Etiquetas utilizadas____________________________*/
     
  private static String[] datos;
  private static String linea = "";
  private String [] orden = {"Tipo de Cliente","Nombre de Cliente","Correo Electrónico","Fecha", "Hora"};
  private JComboBox<String> combo = new JComboBox<String>(orden);
  private static int tamano = 0;
  private static Vector<Vector> filas;
  private static Vector<Vector> filasRespaldo;
  private static JTable tbl;
  private static Vector<String> columnas;
  private static JScrollPane panel;
/**____________________________________FIN ATRIBUTOS DE LA CLASE TABLA________________________________________________*/


/**_________________________________________CONSTRUCTOR DE INTERFAZ_____________________________________________________*/

  public Tabla() {
        
    super("Historial");
    this.setSize(1200,700);
                
    columnas = new Vector<String>();/* Se crea una instancia de la clase Vector llamada 'columnas' */
        
    /* Agregar datos al vector, estos datos vendrán a ser las columnas de la tabla.*/
    columnas.add("Tipo de Cliente");
    columnas.add("Nombre");
    columnas.add("Correo Electrónico");
    columnas.add("Fecha");
    columnas.add("Hora");
       
    obtenerDatos();
         
    /** Crear una instancia de la clase Vector llamada 'filas' , este vector tendrá todas las filas de la tabla.*/
             
    /**Agregar datos a las filas*/
    filas = new Vector<Vector>();
    filasRespaldo = new Vector<Vector>();
    
    if(datos.length>=5){
      int i = 0;
      while( i<datos.length){  
        Vector<String> fila = new Vector<String>();  
        fila.add(datos[i]);
        fila.add(datos[i + 1]);
        fila.add(datos[i + 2]);
        fila.add(datos[i + 3]);
        fila.add(datos[i + 4]);
        filas.add(fila);
        filasRespaldo.add(fila);
        i+=5;
      //  tamano++;
      }
    }  
    
    /**Crear fila con los datos agregados*/
             
    tbl = new JTable(filas,columnas);
    JScrollPane panel =new JScrollPane(tbl);
    panel.setPreferredSize(new Dimension(1100,600));
       
         
    Container  contenedor = getContentPane();
    SpringLayout layout =  new SpringLayout();
    contenedor.setLayout(layout);

    layout.putConstraint(SpringLayout.WEST, panel, 20, SpringLayout.WEST, contenedor);
    layout.putConstraint(SpringLayout.NORTH, panel, 20, SpringLayout.NORTH, contenedor);     
    layout.putConstraint(SpringLayout.WEST, combo, 530, SpringLayout.WEST, contenedor);
    layout.putConstraint(SpringLayout.NORTH, combo, 620, SpringLayout.NORTH, contenedor);  
       
    contenedor.add(panel);
    contenedor.add(combo);
    this.setVisible(true);

    combo.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evento) {  
      String seleccionado=(String)combo.getSelectedItem();
      setVisible(false);
      ordenar(seleccionado);
      setVisible(true);
      //  ordenar(seleccionado);  
    }});
      
  }

  public static < T extends Comparable< T > > int maximo( T x, T y){
  
    if (x.equals("discapacitado")){
      x =(T)"a";
    }
    if (x.equals("adulto mayor")){
      x =(T)"b";
    }
    if (x.equals("mujer embarazada")){
      x =(T)"c";
    }
    if (x.equals("cliente corporativo")){
      x =(T)"d";
    }
    if (x.equals("cliente regular")){
      x =(T)"e";
    }
      if (y.equals("discapacitado")){
      y =(T)"a";
    }
    if (y.equals("adulto mayor")){
      y =(T)"b";
    }
    if (y.equals("mujer embarazada")){
      y =(T)"c";
    }
    if (y.equals("cliente corporativo")){
      y =(T)"d";
    }
    if (x.equals("cliente regular")){
      y =(T)"e";
    }
    return  x.compareTo( y );
  } //    


  public static void ordenar(String seleccionado){

    int pos = 0;
  
    if (seleccionado == "Nombre de Cliente"){

      pos=1;
    }
    if(seleccionado == "Correo Electrónico"){
      pos = 2;
    }
    boolean cambios=false;
    
    if (seleccionado == "Fecha" || seleccionado == "Hora"){
    
      for(int i =0; i<tamano;i++){
        filas.add(i,filasRespaldo.elementAt(i));
        filas.remove(i+1);
      }
    }
    else{
      
      for(int i=0; i<tamano; i++){
        
        for(int j=0; j<tamano-1-i; j++){
   
          if(maximo(filas.elementAt(j).elementAt(pos).toString().toLowerCase(),filas.elementAt(j+1).elementAt(pos).toString().toLowerCase())>0){
              
            cambios=true;
            Vector<String> temp = filas.elementAt(j);

            filas.add(j,filas.elementAt(j+1));
            filas.remove(j+1);
            filas.add(j+1,temp);
            filas.remove(j+2);
          }
        }
    
        if (cambios==false){
          break;
        }
      }

      tbl= new JTable(filas,columnas);
      panel =new JScrollPane(tbl);
    }
  }

  public static void obtenerDatos(){
    try{
      
      FileReader lector=new FileReader(new File ("").getAbsolutePath()+"/datos/datos.txt");
      BufferedReader contenido=new BufferedReader(lector);

      int maxSize = 0;
      while((linea=contenido.readLine())!=null){
        maxSize++;
      }

      FileReader lector2=new FileReader(new File ("").getAbsolutePath()+"/datos/datos.txt");    
      BufferedReader contenido2=new BufferedReader(lector2);

      datos= new String[maxSize];
      for(int indice =0; indice<datos.length; indice++){
        linea=contenido2.readLine();
        datos[indice]=linea;
      } 
      tamano = datos.length/5;     
    }

    catch(Exception e){
      JOptionPane.showMessageDialog( null, "Error al leer datos","Error",JOptionPane.INFORMATION_MESSAGE );
    }    
  }
       
                
} 
  
