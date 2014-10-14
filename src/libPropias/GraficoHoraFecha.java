import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.jfree.chart.*;
import org.jfree.chart.annotations.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.block.*;
import org.jfree.chart.demo.*;
import org.jfree.chart.editor.*;
import org.jfree.chart.encoders.*;
import org.jfree.chart.entity.*;
import org.jfree.chart.event.*;
import java.awt.event.ActionListener;
import org.jfree.chart.imagemap.*;
import org.jfree.chart.labels.*;
import org.jfree.chart.needle.*;
import javax.swing.JOptionPane;
import org.jfree.chart.plot.*;
import org.jfree.chart.plot.dial.*;
import org.jfree.chart.renderer.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.chart.renderer.xy.*;
import org.jfree.chart.resources.*;
import org.jfree.chart.servlet.*;
import org.jfree.chart.title.*;
import org.jfree.chart.urls.*;
import org.jfree.chart.util.*;
import org.jfree.data.*;
import org.jfree.data.category.*;
import org.jfree.data.contour.*;
import org.jfree.data.function.*;
import org.jfree.data.gantt.*;
import org.jfree.data.general.*;
import org.jfree.data.io.*;
import org.jfree.data.jdbc.*;
import org.jfree.data.resources.*;
import org.jfree.data.statistics.*;
import org.jfree.data.time.*;
import org.jfree.data.time.ohlc.*;
import org.jfree.data.xml.*;
import org.jfree.data.xy.*;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;


public class GraficoHoraFecha extends JFrame{
      public int cantFechas = 0;
      public JPanel panel;
      public String [] fechas;
      public JComboBox combo;
      public String [] datos;
      public int tamaño = 0;
      public int ocho = 0;
      public int nueve = 0;
      public int diez =0;
      public int once = 0;
      public int doce = 0;
      public int una = 0;
      public int dos = 0;
      public int tres = 0;





    public GraficoHoraFecha(){
        setTitle("Grafico Hora y Fecha");
        setSize(800,600);
       setLocationRelativeTo(null);
        setVisible(true);

        init();
    }

    private void init() {

        panel = new JPanel();
        getContentPane().add(panel);
  
        JScrollPane scroll = new JScrollPane(panel);  
        this.getContentPane().add(scroll, BorderLayout.CENTER);  

  try{
      FileReader lector = new FileReader(new File ("").getAbsolutePath()+"/datos/datos.txt");
      BufferedReader contenido=new BufferedReader(lector);
      String dato;
      while((dato=contenido.readLine())!=null){
        tamaño++;
    } 
    datos = new String[tamaño];
      FileReader lector2 = new FileReader(new File ("").getAbsolutePath()+"/datos/datos.txt");
    BufferedReader contenido2=new BufferedReader(lector2);
    String dato2;
    int ind = 0;
    while((dato=contenido2.readLine())!=null){
      datos[ind] = dato;
      ind++;
    }

   }catch(Exception e){
      System.out.println("Error al leer");
    }
   int cont =3;
   String fecha;
   String compararFecha = "";

   while(cont<datos.length){
    if(datos[cont].equals(compararFecha)){
      cont= cont +5;
    }
    else{
      cantFechas++;
      compararFecha = datos[cont];
      cont = cont + 5;
    }
   }

   fechas = new String[cantFechas];

   int cnt =3;
   String compararFecha2 = "";
   int j = 0;


   while(cnt<datos.length){
    if(datos[cnt].equals(compararFecha2)) {
      cnt = cnt +5;
    }
    else{
      fechas[j] = datos[cnt];
      compararFecha2 = datos[cnt];
      cnt = cnt + 5;
      j++;
    }

   }
   combo = new JComboBox(fechas);
   panel.add(combo);

    combo.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent evento) {  
         combo.setVisible(false);
        String seleccionado=(String)combo.getSelectedItem();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        int cont = 0;
        while(cont<datos.length){
          if(seleccionado.equals(datos[cont])){
            String h = datos[cont+1];
            char t = h.charAt(0);
            char n = h.charAt(1);
            String hora = String.valueOf(t) + String.valueOf(n); 
            if (hora.equals("08")){
              ocho++;
            }
            if (hora.equals("09")){
              nueve++;
            }
            if (hora.equals("10")){
              diez++;
            }
            if (hora.equals("11")){
              once++;
            }
            if (hora.equals("12")){
              doce++;
            }
            if (hora.equals("13")){
              una++;
            }
            if (hora.equals("14")){
              dos++;
            }
            if (hora.equals("15")){
              tres++;
            }
          }
          cont ++;
        }

       int totalHoras = ocho + nueve + diez + once + doce + una + dos + tres ;
       System.out.println(totalHoras);
       double por = (double)100/totalHoras;

        dataset.setValue(por*ocho, "8 am", "Hora");
        dataset.setValue(por*nueve, "9 am", "Hora");
        dataset.setValue(por*diez, "10 am", "Hora");
        dataset.setValue(por*once, "11 am", "Hora");
        dataset.setValue(por*doce, "12 pm", "Hora");
        dataset.setValue(por*una, "1 pm", "Hora");
        dataset.setValue(por*dos, "2 pm", "Hora");
        dataset.setValue(por*tres, "3 pm", "Hora");


        JFreeChart chart = ChartFactory.createBarChart3D
        ("Clientes Atendidos por hora","Hora", "Porcentaje",
        dataset, PlotOrientation.VERTICAL, true,true, false);
        chart.setBackgroundPaint(Color.gray);
        chart.getTitle().setPaint(Color.black);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.red);
        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.add(chartPanel);
        setVisible(true);


             
    }});

    
 
       
    
    }

}
