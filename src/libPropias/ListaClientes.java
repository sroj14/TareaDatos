import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;
import java.io.FileWriter;
/**Clase que contiene la lista de los cliente*/
public class ListaClientes{
	      /**Atributos*/
	public Cliente primerCliente;
	public Cliente ultimoCliente;
	public int size;
	public Cliente actual;
/**Clase Cliente*/
public class Cliente{
	/**Atributos*/
	private String nombre;
	private String correo;
	private String fecha;
	private String hora;
	private String tipo;
	private Cliente siguiente;
	/**Constructor de la clase*/
	public Cliente(String tipo, String nombre, String correo){

	    this.nombre = nombre;
	    this.correo = correo;
	    this.tipo = tipo;
        Date date = new Date();
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        this.hora = hourFormat.format(date);
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.fecha = dateFormat.format(date);
        this.siguiente = null;
	}
    /**Metodos de la clase Clientre*/
	public String obtenerFecha(){
		return this.fecha;
	}
	public String obtenerHora(){
		return this.hora;
	}
	public String obtenerCorreo(){
		return this.correo;
	}
	public String obtenerNombre(){
		return this.nombre;
	}
	public String obtenerTipo(){
		return this.tipo;
	}
	public void establecerSiguiente(Cliente siguiente){
		this.siguiente = siguiente;
	}
    public Cliente obtenerSiguiente(){
        return this.siguiente;
     }

} /**_________FIN DE LA CLASE CLIENTE_______________*/
  /**_________Constructor de la clase ListaClientes_______*/
    public ListaClientes(){
    	this.primerCliente = null;
        this.ultimoCliente = null;
     }
    /**Metodos de la clase*/
    public void establecerActual(){
    	this.actual = this.primerCliente;
    }

    public Cliente obtenerActual(){
    	return this.actual;
    }

    public void establecerActualSig(){
        this.actual = this.actual.obtenerSiguiente();
    }

    public int obtenerTamaño(){
     return this.size;
    }
    /**Inserta un elemento al final de la lista*/
    public void  insertar(String cal, String nombre, String correo){

        Cliente usuario = new Cliente(cal,nombre,correo);
        guardarDatos(usuario.obtenerTipo(), usuario.obtenerNombre(), usuario.obtenerCorreo(), usuario.obtenerFecha(), usuario.obtenerHora());
        if(primerCliente == null){
        	this.primerCliente = usuario;
		    this.ultimoCliente = usuario;
	    }
	    else{
	    	this.ultimoCliente.establecerSiguiente(usuario);
		    this.ultimoCliente = usuario;
	    }
	    size++;
	}
	/**Elimina y retorna el primer elemento de la lista*/
    public void asignarCaja(){

    	if(this.primerCliente == null){
            throw new IllegalArgumentException("Lista Vacia"); 
        }
        Cliente usuario = this.primerCliente;
        if(this.obtenerTamaño() ==1){
            this.primerCliente = null;
            this.ultimoCliente = null;
        }
        else{
            Cliente temp = this.primerCliente;
            this.primerCliente = this.primerCliente.obtenerSiguiente();
            temp.establecerSiguiente(null);
        }
        size--;
        this.actual = usuario;
}
public static void guardarDatos(String tipo, String nombre, String correo, String fecha, String hora){
    
try
{
//Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
File archivo=new File(new File ("").getAbsolutePath()+"/datos/datos.txt");

//Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
FileWriter escribir=new FileWriter(archivo,true);

//Escribimos en el archivo con el metodo write 
escribir.write(tipo+"\n");
escribir.write(nombre+"\n");
escribir.write(correo+"\n");
escribir.write(fecha+"\n");
escribir.write(hora+"\n");

//Cerramos la conexion
escribir.close();
}

catch(Exception e)
{
System.out.println("Error al escribir");
}
}   
}