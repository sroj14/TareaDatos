/**Clase que contine los cajas disponibles*/
public class ListaCajas{
  /*Atributos*/
	public Caja primerCaja;
	public Caja ultimaCaja;
	public Caja actual;
	public int size;
/*CLASE CAJA*/
public class Caja{
  private Caja siguiente;
	private String estado;
	private String nombre;
  private String nombreCliente;
  private String correo;
  private String tipo;
  /*Constructor*/
	public Caja(String estado, String nombre, String nombreCliente, String correo, String tipo){
        this.siguiente = null;
        this.estado = estado;
        this.nombre = nombre;
        this.nombreCliente = nombreCliente;
        this.correo = correo;
        this.tipo = tipo;
	  }
  /*Metodos de la clase Caja*/
  public void establecerNombreCliente(String nombreCliente){
    this.nombreCliente = nombreCliente;
  }

  public String obtenerNombreCliente(){
    return this.nombreCliente;
  }

  public void establecerTipo(String tipo){
    this.tipo = tipo;
  }

  public String obtenerTipo(){
    return this.tipo;
  }

  public void establecerCorreo(String correo){
    this.correo = correo;
  }

  public String obtenerCorreo(){
    return this.correo;
  }

	public String obtenerEstado(){
		return this.estado;
	}

	public void establecerEstado(String estado){
		this.estado = estado;
	}

	public void establecerSiguiente(Caja siguiente){
		this.siguiente = siguiente;
	}

  public Caja obtenerSiguiente(){
    return this.siguiente;
  }

  public String obtenerNombre(){
    return this.nombre;
  }
}/*Fin de la clase Caja*/

/*CONSTRUCTOR DE LA CLASE LISTACLIENTES*/
public ListaCajas(){
    this.primerCaja = null;
    this.ultimaCaja = null;
    this.actual = null; 
}

public void establecerActual(){
    this.actual = this.primerCaja;
}

public Caja obtenerActual(){
     return this.actual;
}

public void establecerActualSig(){
     this.actual = this.actual.obtenerSiguiente();
}

public int obtenerTamaño(){
     return this.size;
}
/*Inserta el elemento*/
public void  insertar(String estado,String nombre,String nombreCliente, String correo, String tipo){
     Caja caja = new Caja(estado,nombre,nombreCliente,correo,tipo);

	if(primerCaja == null){
		this.primerCaja = caja;
		this.ultimaCaja = caja;
		this.actual = caja;
	}
	else{
		this.ultimaCaja.establecerSiguiente(caja);
		this.ultimaCaja = caja;
		this.actual = caja;
	}
	size++;
}
/**Busca la caja*/
public Caja obtenerPorNombre(String nombre){
  this.establecerActual();
  while(this.actual != null){
    if(this.actual.obtenerNombre() == nombre){
      return this.actual;
    }
    else{
       this.establecerActualSig();
    }
  }
  throw new IllegalArgumentException("No encontrado"); 
}

}
   