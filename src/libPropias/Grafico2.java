

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

import org.jfree.chart.imagemap.*;
import org.jfree.chart.labels.*;
import org.jfree.chart.needle.*;

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



public class Grafico2 extends JFrame{
      public int cantDiscapacitados = 0;
      public int cantPersonaMayor =  0;
      public int cantMujerEmbarazada = 0;
      public int cantClienteCorporativo = 0;
      public int cantClienteRegular = 0;
      public int cantTotal;

  

    JPanel panel;
    public Grafico2(){
        setTitle("Como Hacer Graficos con Java");
        setSize(1000,600);
        setLocationRelativeTo(null);
      //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        init();

        
    }
    private void init() {

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

        panel = new JPanel();
        getContentPane().add(panel);
        // Fuente de Datos
        DefaultPieDataset defaultpiedataset = new DefaultPieDataset();
        defaultpiedataset.setValue("Discapacitados", new Double(total*cantDiscapacitados));
        defaultpiedataset.setValue("Persona Adulta mayor", new Double(total*cantPersonaMayor));
        defaultpiedataset.setValue("Mujer Embarazada", new Double(total*cantMujerEmbarazada));
        defaultpiedataset.setValue("Cliente Corporativo", new Double(total*cantClienteCorporativo));
        defaultpiedataset.setValue("Cliente Regular", new Double(total*cantClienteRegular));
        // Creando el Grafico
        JFreeChart chart = ChartFactory.createPieChart3D("Grafico Clientes", defaultpiedataset, true, true, false);
        PiePlot3D pieplot3d = (PiePlot3D)chart.getPlot();
        pieplot3d.setDepthFactor(0.5);
        pieplot3d.setStartAngle(290D);
        //pieplot3d.setDirection(Ventana.CLOCKWISE);
        pieplot3d.setForegroundAlpha(0.5F);
        // Mostrar Grafico
        ChartPanel chartPanel = new ChartPanel(chart);
        panel.add(chartPanel);

    }
    public static void main(String args[]){
        Grafico2 ven2 = new Grafico2();
        ven2.setVisible(true); 

          

        

   
    }
}


 


