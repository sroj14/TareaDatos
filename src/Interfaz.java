
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;                    
import java.awt.image.BufferedImage;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JComboBox;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;
/**__________________________________________INICIO CLASE INTERFAZ______________________________________________________________*/


public class Interfaz extends JFrame {

	//Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
    private File archivo=new File("datos.txt");

/**________________________________________ATRIBUTOS DE LA CLASE INTERFAZ_____________________________________________________*/
    

    /**_____________________________________Etiquetas utilizadas____________________________*/

    private final JLabel jlTitulo = new JLabel("Bienvenido a BAC San Jose");
    private final JLabel jlCajeros = new JLabel("Cajeros Disponibles");
    private static JLabel imagen = new JLabel();
    private final JLabel jlAgregarUsuario = new JLabel("Ingrese los Datos del usuario");
    private final JLabel jlNombreUsuario = new JLabel("Ingrese el nombre: ");
    private final JLabel jlCorreoUsuario = new JLabel("Ingrese el correo: ");
    private final JLabel jlTipoUsuario = new JLabel("Tipo de usuario: ");
    private final JLabel jlCajerosDisponibles = new JLabel("Cajas Disponibles para clientes:");
    private final JLabel jlCajerosAtendiendo = new JLabel("Cajas Ocupadas:");
    private final JLabel jlDatosCliente = new JLabel("Datos del Cliente:");
    private final JLabel jlNombreCliente = new JLabel("Nombre del Cliente:");
    private final JLabel jlTipoCliente = new JLabel("Tipo de Cliente:");
    private final JLabel jlCorreoCliente = new JLabel("correo del Cliente:");
    private final JLabel jlReportes = new JLabel("Ver reportes estadísticos:");
    private final JLabel jlGrafico = new JLabel("Ver Grafico de clientes atendidos por tipo:");
    private final JLabel jlGraficoHora = new JLabel("Ver Grafico de clientes atendidos por Hora:");

    /**__________________Campos de texto utilizados_____________________*/

    private final JTextField jtCantidadCajeros = new JTextField(20);
    private final JTextField jtNombre = new JTextField(23);
    private final JTextField jtCorreo = new JTextField(23);
    private final JTextField jtNombreCliente = new JTextField(16);
    private final JTextField jtCorreoCliente = new JTextField(16);
    private final JTextField jtTipoCliente = new JTextField(16);


    /**___________________Botones utilizados____________________________*/

    private final JButton btHistorial = new JButton("Historial");
    private final JButton btAgregarUsuario = new JButton("Agregar");
    private final JButton btAgregarCajero = new JButton("Aceptar");
    private final JButton btLiberarCaja = new JButton("Liberar Caja");
    private final JButton btGraficoBarras = new JButton("Grafico Barras");
    private final JButton btGraficoPastel = new JButton("Grafico Pastel");
    private final JButton btGraficoHora = new JButton("Grafico por Dia y Hora");

    /**_________________________ComboBox___________________________________*/

    String [] usuarios = {"Discapacitado","Adulto Mayor","Mujer Embarazada",
                                               "Cliente Corporativo", "Cliente Regular"};
    JComboBox combo = new JComboBox(usuarios);
    private static JList<String> listBox;
    private static JList<String> listClientesAtendidos;
    private DefaultListModel<String> listClientes;     
    private DefaultListModel<String> listModel;

    /**__________________Colas de los Usuarios__________________________*/

    ListaClientes personaDiscapacidad = new ListaClientes();
    ListaClientes personaMayor = new ListaClientes();
    ListaClientes mujerEmbarazada = new ListaClientes();
    ListaClientes clienteCorporativo = new ListaClientes();
    ListaClientes clienteRegular = new ListaClientes();
    ListaCajas cajas = new ListaCajas();
    

    /**________________________Metodo para validar Correos_____________________________*/
  
  public static boolean validateEmail(String email) {
  try{
      String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
      Pattern pattern = Pattern.compile(EMAIL_PATTERN);
      Matcher matcher = pattern.matcher(email);
      return matcher.matches();
  }catch(Exception e){
    e.printStackTrace();
  }
  return false;
}
/**_____________________________________FIN ATRIBUTOS DE LA CLASE INTERFAZ_____________________________________________________*/
   
/**_____________________________________CONSTRUCTOR DE INTERFAZ________________________________________________________________*/
   
   	public Interfaz (){

/**_____________________________Agregando propiedades de ventana y otros elementos____________________________________________*/	

       setTitle("BAC San Jose");
       setSize(265,170);
       setLocation(0,0);
       setResizable(true);        
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
       btAgregarCajero.setPreferredSize(new Dimension(90,25));
       btHistorial.setPreferredSize(new Dimension(280,25));
       btGraficoBarras.setPreferredSize(new Dimension(280,25));
       btGraficoPastel.setPreferredSize(new Dimension(280,25));
       btLiberarCaja.setPreferredSize(new Dimension(198,25));
       btGraficoHora.setPreferredSize(new Dimension(280,25));

       imagen.setPreferredSize(new Dimension(520,320)); 
       combo.setPreferredSize(new Dimension(255,20));
      

       jlTitulo.setForeground(Color.RED);
       jlTitulo.setFont(new Font("Arial", Font.BOLD, 15));

       jlCajeros.setForeground(Color.RED);
       jlCajeros.setFont(new Font("Arial", Font.BOLD, 15));
   
/**_________________________FIN Agregando propiedades de ventana y otros elementos________________________________________*/

/**______________________________________COLOCANDO LOGO DE INTERFAZ_______________________________________________________*/
  
  ImageIcon iid = new ImageIcon(this.getClass().getResource("/imagenes/logo2.jpg"));
      /**establecer imagen como icono*/
      setIconImage( iid.getImage());  
      imagen.setIcon(new ImageIcon( iid.getImage()));  

/**____________________________________FIN DE COLOCANDO LOGO DE INTERFAZ__________________________________________________*/


/**_________________________Creando contenedor de elementos en la interfaz________________________________________________*/
      
       Container  contenedor = getContentPane();
       SpringLayout layout =  new SpringLayout();
       contenedor.setLayout(layout); 

       listModel = new DefaultListModel<>();
       listBox = new JList<>(listModel);
       listBox.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       contenedor.add(listBox);
       JScrollPane scroll= new JScrollPane(listBox);
       scroll.setPreferredSize(new Dimension(200,200));

       listClientes = new DefaultListModel<>();
       listClientesAtendidos = new JList<>(listClientes);
       listClientesAtendidos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
       contenedor.add(listClientesAtendidos);
       JScrollPane scrollClientes = new JScrollPane(listClientesAtendidos);
       scrollClientes.setPreferredSize(new Dimension(200,200));

       layout.putConstraint(SpringLayout.WEST, scrollClientes, 660, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, scrollClientes, 60, SpringLayout.NORTH, contenedor);     
 
       layout.putConstraint(SpringLayout.WEST, scroll, 400, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, scroll, 60, SpringLayout.NORTH, contenedor);     
       
       layout.putConstraint(SpringLayout.WEST, jlTitulo, 10, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, jlTitulo, 7, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, jlCajeros, 20, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, jlCajeros, 47, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, imagen,970, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, imagen,-52, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, jtCantidadCajeros, 20 , SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, jtCantidadCajeros, 77, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, btHistorial, 10, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, btHistorial, 417, SpringLayout.NORTH, contenedor);


       layout.putConstraint(SpringLayout.WEST, btAgregarUsuario, 140, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, btAgregarUsuario, 125, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, jtNombre, 140 , SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, jtNombre, 60, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, jtCorreo, 140 , SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, jtCorreo, 80, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, jlNombreUsuario, 10 , SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, jlNombreUsuario, 60, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, jlCorreoUsuario, 10 , SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, jlCorreoUsuario, 80, SpringLayout.NORTH, contenedor);


       layout.putConstraint(SpringLayout.WEST, jlAgregarUsuario, 10, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, jlAgregarUsuario, 30, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, combo, 140, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, combo, 100, SpringLayout.NORTH, contenedor);
       
       layout.putConstraint(SpringLayout.WEST, jlTipoUsuario, 10, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, jlTipoUsuario, 100, SpringLayout.NORTH, contenedor);
       
       layout.putConstraint(SpringLayout.WEST, btAgregarCajero, 100, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, btAgregarCajero, 105, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, jlCajerosDisponibles, 400, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, jlCajerosDisponibles, 30, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, jlCajerosAtendiendo, 660, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, jlCajerosAtendiendo, 30, SpringLayout.NORTH, contenedor);
   
       layout.putConstraint(SpringLayout.WEST, btLiberarCaja, 660, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, btLiberarCaja, 265, SpringLayout.NORTH, contenedor);    
       
       layout.putConstraint(SpringLayout.WEST, jlDatosCliente, 517, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, jlDatosCliente, 320, SpringLayout.NORTH, contenedor);   

       layout.putConstraint(SpringLayout.WEST, jlNombreCliente, 517, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, jlNombreCliente, 350, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, jlTipoCliente, 517, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, jlTipoCliente, 380, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, jlCorreoCliente, 517, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, jlCorreoCliente, 410, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, jtNombreCliente, 672, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, jtNombreCliente, 350, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, jtTipoCliente, 672, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, jtTipoCliente, 380, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, jtCorreoCliente, 672, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, jtCorreoCliente, 410, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, jlReportes, 10, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, jlReportes, 320, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, btGraficoPastel, 10, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, btGraficoPastel, 342, SpringLayout.NORTH, contenedor);

       layout.putConstraint(SpringLayout.WEST, btGraficoBarras, 10, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, btGraficoBarras, 367, SpringLayout.NORTH, contenedor);  

       layout.putConstraint(SpringLayout.WEST, btGraficoHora, 10, SpringLayout.WEST, contenedor);
       layout.putConstraint(SpringLayout.NORTH, btGraficoHora, 392, SpringLayout.NORTH, contenedor);

       contenedor.add(btGraficoHora);      
       contenedor.add(jlCajerosAtendiendo);
       contenedor.add(scroll);
       contenedor.add(scrollClientes);
       contenedor.add(jlTitulo);
       contenedor.add(jlCajeros);
       contenedor.add(imagen);
       contenedor.add(btHistorial);
       contenedor.add(jtCantidadCajeros);
       contenedor.add(btAgregarUsuario);
       contenedor.add(jtNombre);
       contenedor.add(jtCorreo);
       contenedor.add(jlCorreoUsuario);
       contenedor.add(jlNombreUsuario);
       contenedor.add(jlAgregarUsuario);
       contenedor.add(combo);
       contenedor.add(jlTipoUsuario);
       contenedor.add(btAgregarCajero);
       contenedor.add(jlCajerosDisponibles);
       contenedor.add(btLiberarCaja);
       contenedor.add(jlDatosCliente);
       contenedor.add(jlTipoCliente);
       contenedor.add(jlNombreCliente);
       contenedor.add(jlCorreoCliente);
        contenedor.add(jtTipoCliente);
       contenedor.add(jtNombreCliente);
       contenedor.add(jtCorreoCliente);
       contenedor.add(btGraficoBarras);
       contenedor.add(btGraficoPastel);
       contenedor.add(jlReportes);    

       jlCajerosAtendiendo.setVisible(false);
       scroll.setVisible(false);
       scrollClientes.setVisible(false);
       btHistorial.setVisible(false);
       btAgregarUsuario.setVisible(false);
       jtNombre.setVisible(false);
       jtCorreo.setVisible(false);
       jlCorreoUsuario.setVisible(false);
       jlNombreUsuario.setVisible(false);
       jlAgregarUsuario.setVisible(false);
       combo.setVisible(false);
       jlTipoUsuario.setVisible(false);
       jlCajerosDisponibles.setVisible(false);
       btLiberarCaja.setVisible(false);
       jlDatosCliente.setVisible(false);
       jlNombreCliente.setVisible(false);
       jlTipoCliente.setVisible(false);
       jlCorreoCliente.setVisible(false);
       jtNombreCliente.setVisible(false);
       jtTipoCliente.setVisible(false);
       jtCorreoCliente.setVisible(false);
       btGraficoBarras.setVisible(false);
       btGraficoPastel.setVisible(false);
       jlReportes.setVisible(false);
/**_____________________________________________FIN DE CONTENEDOR__________________________________________________*/

/**_____________________________________________ACCIONES DE LOS ELEMENTOS__________________________________________*/

/**_____________________________________________Accion Del ListBox_________________________________________________*/

 listClientesAtendidos.addListSelectionListener(new ListSelectionListener() {
 	public void valueChanged(ListSelectionEvent e) {
 		if (listClientesAtendidos.getSelectedIndex()!=-1){
 			if (!e.getValueIsAdjusting()) {
 				cajas.obtenerPorNombre(listClientesAtendidos.getSelectedValuesList().get(0));
 			    jtNombreCliente.setText(cajas.obtenerActual().obtenerNombreCliente());
 			    jtCorreoCliente.setText(cajas.obtenerActual().obtenerCorreo());
 			    jtTipoCliente.setText(cajas.obtenerActual().obtenerTipo());
           }}}}); 

/**___________________________________________Accion de los BOTONES__________________________________________________*/

    /**Muestra una tabla con el historial de clientes*/
 	btHistorial.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evento) {  
                         Tabla tabla = new Tabla();      
    }});
    btGraficoBarras.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evento) {  
                         Grafico barras = new Grafico();      
    }});
    btGraficoPastel.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evento) {  
                         Grafico2 pastel = new Grafico2();      
    }});
                   /**Libera una caja ocupa*/
  btLiberarCaja.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evento) {  

           if (listClientesAtendidos.getSelectedIndex()!= -1){
              String nombreCaja = listClientesAtendidos.getSelectedValuesList().get(0);
                 EnviarCorreo notificacion;
                 cajas.obtenerPorNombre(nombreCaja).establecerEstado("Desocupada");
                 listClientes.removeElementAt(listClientesAtendidos.getSelectedIndex());
                 jtNombreCliente.setText("");
                 jtCorreoCliente.setText("");
                 jtTipoCliente.setText("");
                 
                 while(true){
                 	if (personaDiscapacidad.obtenerTamaño() != 0){
                 		personaDiscapacidad.asignarCaja();
                        notificacion = new EnviarCorreo(personaDiscapacidad.obtenerActual().obtenerCorreo(),cajas.obtenerActual().obtenerNombre(),
              	        personaDiscapacidad.obtenerActual().obtenerNombre(),personaDiscapacidad.obtenerActual().obtenerTipo(),
              	        personaDiscapacidad.obtenerActual().obtenerFecha(),personaDiscapacidad.obtenerActual().obtenerHora());
                 		cajas.obtenerActual().establecerEstado("Ocupada");
                        cajas.obtenerActual().establecerNombreCliente(personaDiscapacidad.obtenerActual().obtenerNombre());
                        cajas.obtenerActual().establecerTipo("Persona Discapacitada");
                        cajas.obtenerActual().establecerCorreo(personaDiscapacidad.obtenerActual().obtenerCorreo());
                 		listClientes.addElement(cajas.obtenerActual().obtenerNombre());
              		    break;
              	     }
              	    if(personaMayor.obtenerTamaño() != 0){
              	    	personaMayor.asignarCaja();
                        notificacion = new EnviarCorreo(personaMayor.obtenerActual().obtenerCorreo(),cajas.obtenerActual().obtenerNombre(),
              	        personaMayor.obtenerActual().obtenerNombre(),personaMayor.obtenerActual().obtenerTipo(),
              	        personaMayor.obtenerActual().obtenerFecha(),personaMayor.obtenerActual().obtenerHora());
                 		cajas.obtenerActual().establecerEstado("Ocupada");
                 		cajas.obtenerActual().establecerNombreCliente(personaMayor.obtenerActual().obtenerNombre());
                        cajas.obtenerActual().establecerTipo("Persona Mayor");
                        cajas.obtenerActual().establecerCorreo(personaMayor.obtenerActual().obtenerCorreo());
                 		listClientes.addElement(cajas.obtenerActual().obtenerNombre());
              		    break;
              	     }
              	    if (mujerEmbarazada.obtenerTamaño() != 0){
              		    mujerEmbarazada.asignarCaja();
                 		cajas.obtenerActual().establecerEstado("Ocupada");
                 		cajas.obtenerActual().establecerNombreCliente(mujerEmbarazada.obtenerActual().obtenerNombre());
                        cajas.obtenerActual().establecerTipo("Mujer Embarazada");
                        cajas.obtenerActual().establecerCorreo(mujerEmbarazada.obtenerActual().obtenerCorreo());
                        notificacion = new EnviarCorreo(mujerEmbarazada.obtenerActual().obtenerCorreo(),cajas.obtenerActual().obtenerNombre(),
              	        mujerEmbarazada.obtenerActual().obtenerNombre(),mujerEmbarazada.obtenerActual().obtenerTipo(),
              	        mujerEmbarazada.obtenerActual().obtenerFecha(),mujerEmbarazada.obtenerActual().obtenerHora());
                        listClientes.addElement(cajas.obtenerActual().obtenerNombre());
              		    break;
              	     }
              	    if (clienteCorporativo.obtenerTamaño() != 0){
              		    clienteCorporativo.asignarCaja();
                 		cajas.obtenerActual().establecerEstado("Ocupada");
                 		cajas.obtenerActual().establecerNombreCliente(clienteCorporativo.obtenerActual().obtenerNombre());
                        cajas.obtenerActual().establecerTipo("Cliente Corporativo");
                        cajas.obtenerActual().establecerCorreo(clienteCorporativo.obtenerActual().obtenerCorreo());
                        notificacion = new EnviarCorreo(clienteCorporativo.obtenerActual().obtenerCorreo(),cajas.obtenerActual().obtenerNombre(),
              	        clienteCorporativo.obtenerActual().obtenerNombre(),clienteCorporativo.obtenerActual().obtenerTipo(),
              	        clienteCorporativo.obtenerActual().obtenerFecha(),clienteCorporativo.obtenerActual().obtenerHora());
                        listClientes.addElement(cajas.obtenerActual().obtenerNombre());
              		    break;
              	     }
              	    if(clienteRegular.obtenerTamaño() != 0){
              		    clienteRegular.asignarCaja();
                 		cajas.obtenerActual().establecerEstado("Ocupada");
                        cajas.obtenerActual().establecerTipo("Cliente Regular");
                        cajas.obtenerActual().establecerCorreo(clienteRegular.obtenerActual().obtenerCorreo());
                 		cajas.obtenerActual().establecerNombreCliente(clienteRegular.obtenerActual().obtenerNombre());
                        notificacion = new EnviarCorreo(clienteRegular.obtenerActual().obtenerCorreo(),cajas.obtenerActual().obtenerNombre(),
              	        clienteRegular.obtenerActual().obtenerNombre(),clienteRegular.obtenerActual().obtenerTipo(),
              	        clienteRegular.obtenerActual().obtenerFecha(),clienteRegular.obtenerActual().obtenerHora());
                        listClientes.addElement(cajas.obtenerActual().obtenerNombre());
              		    break;
              	     }
              	     break;
                    }
                   }

            else{
            JOptionPane.showMessageDialog( null, "Seleccione la caja a Liberar","Error",JOptionPane.INFORMATION_MESSAGE );  
            }
}});

      btGraficoHora.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evento) {  
                         GraficoHoraFecha horaFecha = new GraficoHoraFecha();      
    }});
   /**Agrega la cantidad de cajeros que estaran disponibles*/
  btAgregarCajero.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent evento) {  
          if(jtCantidadCajeros.getText().equals("")){
             JOptionPane.showMessageDialog( null, "Ingrese la cantidad de cajeros","Dato Invalido",JOptionPane.INFORMATION_MESSAGE );
          }
          else{
            try{
              if(Integer.parseInt(jtCantidadCajeros.getText()) < 0){
             JOptionPane.showMessageDialog( null, "Numero Invalido","Dato Invalido",JOptionPane.INFORMATION_MESSAGE );
             return;
             }
            int tamaño = Integer.parseInt(jtCantidadCajeros.getText());
            for(int i = 0; tamaño>i;i++){
              String numero = Integer.toString(i);
              cajas.insertar("Desocupada","caja"+numero, "","","");
            }
            cajas.establecerActual();
           for(int j = 0;j<cajas.obtenerTamaño();j++){
            listModel.addElement(cajas.obtenerActual().obtenerNombre());
            cajas.establecerActualSig();
           }

           btAgregarCajero.setVisible(false);
           jlCajeros.setVisible(false);
           jtCantidadCajeros.setVisible(false);
           jlCajerosAtendiendo.setVisible(true);
           scroll.setVisible(true);
           scrollClientes.setVisible(true);
           btHistorial.setVisible(true);
           btAgregarUsuario.setVisible(true);
           jtNombre.setVisible(true);
           jtCorreo.setVisible(true);
           jlCorreoUsuario.setVisible(true);
           jlNombreUsuario.setVisible(true);
           jlAgregarUsuario.setVisible(true);
           combo.setVisible(true);
           jlTipoUsuario.setVisible(true);
           jlCajerosDisponibles.setVisible(true);
           btLiberarCaja.setVisible(true);
           jlDatosCliente.setVisible(true);
           jlNombreCliente.setVisible(true);
           jlTipoCliente.setVisible(true);
           jlCorreoCliente.setVisible(true);
           jtNombreCliente.setVisible(true);
           jtTipoCliente.setVisible(true);
           jtCorreoCliente.setVisible(true);
           jlGrafico.setVisible(true);
           jlGraficoHora.setVisible(true);
           btGraficoBarras.setVisible(true);
           btGraficoHora.setVisible(true);
           btGraficoPastel.setVisible(true);
           setSize(1200,700);
          }catch(Exception e){JOptionPane.showMessageDialog( null, "Numero no entero","Dato Invalido",JOptionPane.INFORMATION_MESSAGE );}
    }}});
   /**Asigna una caja al cliente*/
  btAgregarUsuario.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evento) { 
          if(!validateEmail(jtCorreo.getText()) || jtNombre.getText().equals("") ) {
             JOptionPane.showMessageDialog( null, "Datos Invalidos","Datos Invalidos",JOptionPane.INFORMATION_MESSAGE );
          }

          else{
          if(combo.getSelectedItem() == "Discapacitado"){
             personaDiscapacidad.insertar("Discapacitado",jtNombre.getText(),jtCorreo.getText());
          }
          if(combo.getSelectedItem() == "Adulto Mayor"){
             personaMayor.insertar("Adulto Mayor",jtNombre.getText(),jtCorreo.getText());
          }
          if(combo.getSelectedItem() == "Mujer Embarazada"){
             mujerEmbarazada.insertar("Mujer Embarazada",jtNombre.getText(),jtCorreo.getText());
          }         
          if(combo.getSelectedItem() == "Cliente Corporativo"){
             clienteCorporativo.insertar("Cliente Corporativo",jtNombre.getText(),jtCorreo.getText());
          }     
          if(combo.getSelectedItem() == "Cliente Regular"){
             clienteRegular.insertar("Cliente Regular",jtNombre.getText(),jtCorreo.getText());
          }
    
          EnviarCorreo notificacion;
          cajas.establecerActual();
          while(personaDiscapacidad.obtenerTamaño() != 0 && cajas.obtenerActual() != null){
            if(cajas.obtenerActual().obtenerEstado() == "Desocupada"){
              personaDiscapacidad.asignarCaja();
              notificacion = new EnviarCorreo(personaDiscapacidad.obtenerActual().obtenerCorreo(),cajas.obtenerActual().obtenerNombre(),
              personaDiscapacidad.obtenerActual().obtenerNombre(),personaDiscapacidad.obtenerActual().obtenerTipo(),
              personaDiscapacidad.obtenerActual().obtenerFecha(),personaDiscapacidad.obtenerActual().obtenerHora());
              listClientes.addElement(cajas.obtenerActual().obtenerNombre());
              cajas.obtenerActual().establecerEstado("Ocupada");
              cajas.obtenerActual().establecerNombreCliente(personaDiscapacidad.obtenerActual().obtenerNombre());
              cajas.obtenerActual().establecerTipo("Persona Discapacitada");
              cajas.obtenerActual().establecerCorreo(personaDiscapacidad.obtenerActual().obtenerCorreo());
              cajas.establecerActualSig();
              continue;
            }
            else{
                cajas.establecerActualSig();
            }
          }
 
          cajas.establecerActual();
          while(personaMayor.obtenerTamaño() != 0 && cajas.obtenerActual() != null){
            if(cajas.obtenerActual().obtenerEstado() == "Desocupada"){
              personaMayor.asignarCaja();
              notificacion = new EnviarCorreo(personaMayor.obtenerActual().obtenerCorreo(),cajas.obtenerActual().obtenerNombre(),
              personaMayor.obtenerActual().obtenerNombre(),personaMayor.obtenerActual().obtenerTipo(),
              personaMayor.obtenerActual().obtenerFecha(),personaMayor.obtenerActual().obtenerHora());
              listClientes.addElement(cajas.obtenerActual().obtenerNombre());
              cajas.obtenerActual().establecerNombreCliente(personaMayor.obtenerActual().obtenerNombre());
              cajas.obtenerActual().establecerEstado("Ocupada");
              cajas.obtenerActual().establecerTipo("Persona Mayor");
              cajas.obtenerActual().establecerCorreo(personaMayor.obtenerActual().obtenerCorreo());
              cajas.establecerActualSig();
              continue;
            }
            else{
                cajas.establecerActualSig();
            }
          }

          cajas.establecerActual();
          while(mujerEmbarazada.obtenerTamaño() != 0 && cajas.obtenerActual() != null){
            if(cajas.obtenerActual().obtenerEstado() == "Desocupada"){
              mujerEmbarazada.asignarCaja();
                notificacion = new EnviarCorreo(mujerEmbarazada.obtenerActual().obtenerCorreo(),cajas.obtenerActual().obtenerNombre(),
              	mujerEmbarazada.obtenerActual().obtenerNombre(),mujerEmbarazada.obtenerActual().obtenerTipo(),
              	mujerEmbarazada.obtenerActual().obtenerFecha(),mujerEmbarazada.obtenerActual().obtenerHora());
              listClientes.addElement(cajas.obtenerActual().obtenerNombre());
              cajas.obtenerActual().establecerEstado("Ocupada");
              cajas.obtenerActual().establecerNombreCliente(mujerEmbarazada.obtenerActual().obtenerNombre());
              cajas.obtenerActual().establecerTipo("Mujer Embarazada");
              cajas.obtenerActual().establecerCorreo(mujerEmbarazada.obtenerActual().obtenerCorreo());
              cajas.establecerActualSig();
              continue;
            }
            else{
                cajas.establecerActualSig();
            }
          }

          cajas.establecerActual();
          while(clienteCorporativo.obtenerTamaño() != 0 && cajas.obtenerActual() != null){
            if(cajas.obtenerActual().obtenerEstado() == "Desocupada"){
              clienteCorporativo.asignarCaja();
              listClientes.addElement(cajas.obtenerActual().obtenerNombre());
                notificacion = new EnviarCorreo(clienteCorporativo.obtenerActual().obtenerCorreo(),cajas.obtenerActual().obtenerNombre(),
              	clienteCorporativo.obtenerActual().obtenerNombre(),clienteCorporativo.obtenerActual().obtenerTipo(),
              	clienteCorporativo.obtenerActual().obtenerFecha(),clienteCorporativo.obtenerActual().obtenerHora());
              cajas.obtenerActual().establecerEstado("Ocupada");
              cajas.obtenerActual().establecerTipo("Cliente Corporativo");
              cajas.obtenerActual().establecerCorreo(clienteCorporativo.obtenerActual().obtenerCorreo());
              cajas.obtenerActual().establecerNombreCliente(clienteCorporativo.obtenerActual().obtenerNombre());
              cajas.establecerActualSig();
              continue;
            }
            else{
                cajas.establecerActualSig();
            }
          }

          cajas.establecerActual();
          while(clienteRegular.obtenerTamaño() != 0 && cajas.obtenerActual() != null){
            if(cajas.obtenerActual().obtenerEstado() == "Desocupada"){
              clienteRegular.asignarCaja();
              listClientes.addElement(cajas.obtenerActual().obtenerNombre());
                notificacion = new EnviarCorreo(clienteRegular.obtenerActual().obtenerCorreo(),cajas.obtenerActual().obtenerNombre(),
              	clienteRegular.obtenerActual().obtenerNombre(),clienteRegular.obtenerActual().obtenerTipo(),
              	clienteRegular.obtenerActual().obtenerFecha(),clienteRegular.obtenerActual().obtenerHora());
              cajas.obtenerActual().establecerEstado("Ocupada");
              cajas.obtenerActual().establecerNombreCliente(clienteRegular.obtenerActual().obtenerNombre());
              cajas.obtenerActual().establecerTipo("Cliente Regular");
              cajas.obtenerActual().establecerCorreo(clienteRegular.obtenerActual().obtenerCorreo());
              cajas.establecerActualSig();
              continue;
            }
            else{
                cajas.establecerActualSig();
            }
          }     
   
         JOptionPane.showMessageDialog( null, "Su operacion se ha realizado con exito, por favor revise su correo y espere ser atendido ","Datos Invalidos",JOptionPane.INFORMATION_MESSAGE ); 
         


    }}});
/**______________________________________FIN ACCIONES DE LOS ELEMENTOS_______________________________________________*/


}//CIERRA CONSTRUCTOR


/**____________________________________________FIN DE CONSTRUCTOR_____________________________________________________*/

/**_______________________________________________METODO MAIN__________________________________________________________*/

 public static void main(String []args){

        Interfaz ventana = new Interfaz(); 
        ventana.setVisible(true);        		
  }
/**_______________________________________________________FIN METODO MAIN______________________________________________________________*/  

 }//CIERRA CLASE INTERFA

/**_______________________________________________________CIERRA INTERFAZ______________________________________________________________*/
