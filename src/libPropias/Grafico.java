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


public class Grafico extends JFrame{
      public int cantDiscapacitados = 0;
      public int cantPersonaMayor =  0;
      public int cantMujerEmbarazada = 0;
      public int cantClienteCorporativo = 0;
      public int cantClienteRegular = 0;
      public int cantTotal;


    JPanel panel;

    public Grafico(){
        setTitle("Grafico");
        setSize(800,600);
       setLocationRelativeTo(null);
        setVisible(true);
        init();

        
        

    }

    private void init() {
        panel = new JPanel();
        getContentPane().add(panel);
        
   try{
        
      FileReader lector = new FileReader(new File ("").getAbsolutePath()+"/datos/datos.txt");
      BufferedReader contenido=new BufferedReader(lector);


      String linea;

      while((linea=contenido.readLine())!=null){
      	if(linea.equals("Discapacitado")){
      		cantDiscapacitados= cantDiscapacitados+1;
      	}
      	if(linea.equals("Adulto Mayor")){
      		cantPersonaMayor = cantPersonaMayor +1;
      	}
      	if(linea.equals("Mujer Embarazada")){
      		cantMujerEmbarazada++;
      	}
      	if(linea.equals("Cliente Corporativo")){
      		cantClienteCorporativo++;
      	}
      	if(linea.equals("Cliente Regular")){
      		cantClienteRegular++;
      	}
      }
         } catch(Exception e){
      System.out.println("Error al leer");
    }   
    int cantTotal = cantDiscapacitados+cantClienteRegular+cantClienteCorporativo+cantMujerEmbarazada+cantPersonaMayor;

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    double  total = (double)100/cantTotal;

        dataset.setValue(total*cantDiscapacitados, "Discapacitado", "Cliente");
        dataset.setValue(total*cantPersonaMayor, "Persona Mayor", "Cliente");
        dataset.setValue(total*cantMujerEmbarazada, "Mujer Embarazada", "Cliente");
        dataset.setValue(total*cantClienteCorporativo, "Cliente Corporativo", "Cliente");
        dataset.setValue(total*cantClienteRegular, "Cliente Regular", "Cliente");


        JFreeChart chart = ChartFactory.createBarChart3D
        ("Clientes Atendidos","Tipo de Cliente", "Porcentaje",
        dataset, PlotOrientation.VERTICAL, true,true, false);
        chart.setBackgroundPaint(Color.gray);
        chart.getTitle().setPaint(Color.black);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.red);
        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.add(chartPanel);
        

       
    
    }

}
