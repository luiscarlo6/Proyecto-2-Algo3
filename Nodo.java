/**
 * Clase que almacena la informacion de las aristas en el grafo.
 * 
 * Luiscarlo Rivera, 09-11020
 * Jose Prado, 09-11006
 * 
 * Proyecto 1
 */

public class Nodo {
  
  //id es unico
  private String id;
  private int horas;
  private boolean visitado;
  private Nodo anterior;
  
  /**
	* Constructor por defecto
	**/
  
  public Nodo(){
	 this.id = "";
  }
  
  
  /**
	* Crea un nodo con id i.
	*/
  public Nodo (String i) {
	 this.id = new String(i);
	 this.horas = 2147483647;
  }
  
  /**
	* Retorna un nuevo nodo que es copia de this.
	*/
  @Override
  protected Object clone() {
	 return new Nodo(new String(this.id));
  }
  
  
  
  /**
	* Retorna la representacion en String del nodo.
	*/
  @Override
  public String toString() {
	 
	 return new String(new String(this.id));
  }
  
  
  /**
	* Indica si el nodo de entrada es igual a this.
	*/
  @Override
  public boolean equals(Object o) {
	 Nodo n;
	 
	 if (o == null)
		return false;
	 
	 if (!(o instanceof Nodo))
		return false;
	 
	 n = (Nodo) o;
	 
	 if (this.id.equalsIgnoreCase(n.id))
		return true;
	 
	 return false;
  }
  
  /**
	* Retorna el codigo hash para un nodo.
	*/
  @Override
  public int hashCode() {
	 return this.id.hashCode();
  }
  
  public int horas(){
	  return this.horas;
  }
  
  public void cambiarHoras(int h){
	  this.horas = h;
  }
  
  public int horasTipo(){
	  int horasTipo;
	  
	  if (this.id.contains("d1")||this.id.contains("d2")||this.id.contains("d3")){
		  horasTipo = 8;
	  }
	  else{
		  horasTipo = 1;
	  }
	  
	  return horasTipo;
  }
  
  public boolean visitado(){
	  return this.visitado;
  }
  
  public void setVisitado(boolean v){
	  this.visitado = v;
  }
  
  public boolean esCaminable(){
	  if (this.id.contains("d1")||this.id.contains("d2")||this.id.contains("d3")){
		  return false;
	  }
	  return true;
  }
  
  public void cambiarAnterior(Nodo n){
	  this.anterior = n;
  }
  
  public Nodo getAnt(){
	  return this.anterior;
  }
} /*Fin de nodo*/

