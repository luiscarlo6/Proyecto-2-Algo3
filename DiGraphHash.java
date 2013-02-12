/**
 * Clase que implementa la interfaz Graph para un 
 * grafo dirigido con tablas de hash.
 * Autores :
 * Luiscarlo Rivera, 09-11020
 * Jose Prado, 09-11006
 * 
 * Proyecto 2
 */
public class DiGraphHash implements Graph{
  
  private int numNodos, numArcos, colisiones;
  private ArrDin<Lista<Nodo>> nodos;
  private ArrDin<Lista<Arco>> arcosIn;
  private ArrDin<Lista<Arco>> arcosOut;
  
  public DiGraphHash() {
	 this.nodos = new ArrDin<Lista<Nodo>>();
	 this.arcosIn = new ArrDin<Lista<Arco>>();
	 this.arcosOut = new ArrDin<Lista<Arco>>();
	 this.numArcos = 0;
	 this.numNodos = 0;
  }
  
  /**
	* Agrega el nodo n. Si el nodo ya existe en el grafo, retorna false.
	* Si se agrega correctamente el nodo, retorna true.
	*/
  @SuppressWarnings("unchecked")
  @Override
  public boolean add(Nodo n){
	 if (n==null){
		return false;
	 }
	 
	 this.ampliar();
	 int pos = this.pos(n);
	 if (pos==-1){
		return false;
	 }
	 
	 //Si no existe colision
	 if (this.nodos.get(pos)==null){
		this.nodos.add(new MiLista<Nodo>(), pos);
		this.arcosIn.add(new MiLista<Arco>(),pos);
		this.arcosOut.add(new MiLista<Arco>(),pos);
	 }
	 else{
		this.colisiones++;
	 }
	 Lista<Nodo> lista = ((MiLista<Nodo>) this.nodos.get(pos));
	 boolean esta = lista.contains(n);
	 if (!esta){
		lista.add(n);			
		this.numNodos++;			
		return true;
	 }
	 return false;
  }
  
  /**
	* Agrega el Arco a. Si los nodos del arco no existen en el grafo 
	* o si ya existe un lado entre dichos nodos, retorna false. 
	* Si se agrega correctamente el nodo, retorna true.
	*/
  @SuppressWarnings("unchecked")
  @Override
  public boolean add(Arco a) {
	 if (a==null){
		return false;
	 }
	 Nodo src = new Nodo(a.getSrc());
	 Nodo dst = new Nodo(a.getDst());
	 int posSrc = this.pos(src);
	 int posDst = this.pos(dst);
	 
	 if (posSrc==-1 || posDst==-1){
		return false;
	 }
	 boolean as=false;
	 
	 if (this.nodos.get(posSrc)!=null && this.nodos.get(posDst)!=null){
		//Obtengo las listas de nodos en las pos del Out e In 
		Lista<Nodo> listaSrc = (MiLista<Nodo>) this.nodos.get(posSrc);
		Lista<Nodo> listaDst = (MiLista<Nodo>) this.nodos.get(posDst);
		
		//Obtengo las listas de arcos en las pos del Out e In
		MiLista<Arco> out =(MiLista<Arco>) this.arcosOut.get(posSrc);
		MiLista<Arco> in =(MiLista<Arco>) this.arcosIn.get(posDst);
		
		boolean estaSrc = listaSrc.contains(src);
		boolean estaDst = listaDst.contains(dst);
		as = !in.contains(a) && !out.contains(a);
		
		//si el grafo contiene los dos nodos y no contiene el arco
		if (estaSrc && estaDst && as){
		  out.add(a);
		  in.add(a);
		  this.numArcos++;
		  return true;
		}
	 }
	 return false;
  }
  
  /**
	* Retorna un grafo nuevo que es una copia del grafo actual.
	*/
  @Override
  public Object clone() {
	 Graph nuevo = new DiGraphHash();
	 
	 //obtengo la lista de nodos, y luego un iterador asociado a ella
	 ListIterator<Nodo> nodos =((MiLista<Nodo>) getNodos()).iterator();
	 
	 int i = 0;
	 while (i!=numNodos) {
		Nodo n = nodos.next();
		nuevo.add((Nodo)n.clone());
		i++;
	 } 
	 
	 //obtengo la lista de arcos, y luego un iterador asociado a ella
	 ListIterator<Arco> arcos = ((MiLista<Arco>) getArcos()).iterator();
	 
	 i = 0;
	 while (arcos.hasNext()) {
		Arco a = arcos.next();
		nuevo.add((Arco)a.clone());
		i++;
	 }       		
	 return nuevo;
  }
  
  /**
	* Retorna true si el grafo contiene un nodo igual a n,
	* si no retorna false.
	*/
  @SuppressWarnings("unchecked")
  @Override
  public boolean contains(Nodo n) {
	 if (this.numNodos==0||n==null){
		return false;
	 }
	 int pos = this.pos(n);
	 if (pos==-1){
		return false;
	 }
	 
	 if (this.nodos.get(pos)==null){
		return false;
	 }
	 
	 //obtener lista de arcos en la pos del nodo n
	 Lista<Nodo> nodos = (MiLista<Nodo>) this.nodos.get(pos);
	 boolean esta = nodos.contains(n);
	 return esta;
  }
  
  /**
	* Retorna true si el grafo contiene un arco igual a a,
	* si no retorna false.
	*/
  @Override
  @SuppressWarnings("unchecked")
  public boolean contains(Arco a) {
	 if (a==null||this.numArcos==0){
		return false;
	 }
	 
	 Nodo src = new Nodo(a.getSrc());
	 Nodo dst = new Nodo(a.getDst());
	 int posSrc = this.pos(src);
	 int posDst = this.pos(dst);
	 
	 if (posSrc==-1 || posDst==-1){
		return false;
	 }
	 
	 if (this.nodos.get(posSrc)!=null && this.nodos.get(posDst)!=null){
		//obtiene las listas de arcos de las posiciones de los nodos In Out
		MiLista<Arco> out =(MiLista<Arco>) this.arcosOut.get(posSrc);
		MiLista<Arco> in =(MiLista<Arco>) this.arcosIn.get(posDst);
		
		boolean estaArco = in.contains(a) && out.contains(a);
		return estaArco;
	 }
	 return false;
  }
  
  /**
	* Remueve del grafo el nodo n con todos sus arcos relacionados.
	* Si el grafo se modifica (si el nodo existia en este), retorna true.
	* Si el grafo se mantiene igual, retorna false.
	*/
  @Override
  @SuppressWarnings("unchecked")
  public boolean remove(Nodo n) {
	 if (n==null||this.numNodos==0){
		return false;
	 }
	 int pos = this.pos(n);
	 if (pos==-1){
		return false;
	 }
	 
	 if (this.nodos.get(pos)==null){
		return false;
	 }
	 
	 Lista<Nodo> lista = (MiLista<Nodo>) this.nodos.get(pos);
	 //De las listas de arcos en las pos In Out del nodo, obtengo sus array
	 Object[] arcosIn = ((MiLista<Arco>) this.arcosIn.get(pos)).toArray();
	 Object[] arcosOut = ((MiLista<Arco>) this.arcosOut.get(pos)).toArray();
	 int i = 0;
	 //itero para remover los arcos del nodo
	 while (i!=arcosIn.length){
		Arco arc = (Arco) arcosIn[i];
		if(arc.getDst().equalsIgnoreCase(n.toString())){
		  this.remove(arc);	
		}
		i++;
	 }
	 i=0;
	 while (i!=arcosOut.length){
		Arco arc = (Arco) arcosOut[i];
		if(arc.getSrc().equalsIgnoreCase(n.toString())){
		  this.remove(arc);	
		}
		i++;
	 }
	 
	 boolean salio = lista.remove(n);
	 this.numNodos--;
	 if(lista.isEmpty()){
		this.nodos.add(null, pos);
		this.arcosIn.add(null,pos);
		this.arcosOut.add(null,pos);
	 }
	 return salio;
  }
  
  /**
	* Remueve el arco a del grafo.
	* Si el grafo se modifica (si el arco existia en este), retorna true.
	* Si el grafo se mantiene igual, retorna false.
	*/
  @Override
  @SuppressWarnings("unchecked")
  public boolean remove(Arco a) {
	 if (a==null||this.numArcos==0){
		return false;
	 }
	 Nodo src = new Nodo(a.getSrc());
	 Nodo dst = new Nodo(a.getDst());
	 
	 int posSrc = this.pos(src);
	 int posDst = this.pos(dst);
	 if (posSrc==-1 || posDst==-1){
		return false;
	 }
	 
	 if (this.nodos.get(posSrc)!=null && this.nodos.get(posDst)!=null){
		//Obtengo las listas de los nodos Out e In del nodo
		MiLista<Arco> out =((MiLista<Arco>) this.arcosOut.get(posSrc));
		MiLista<Arco> in =((MiLista<Arco>) this.arcosIn.get(posDst));
		//remuevo de las listas
		boolean quitado = out.remove(a)&&in.remove(a);
		if (quitado)
		  this.numArcos--;
		return quitado;
	 }
	 return false;
  }
  
  /**
	* Devuelve una lista con todos los nodos del grafo.
	*/
  @SuppressWarnings("unchecked")
  @Override
  public Lista<Nodo> getNodos() {
	 Lista<Nodo> lista = new MiLista<Nodo>();
	 int i = 0;
	 
	 while(i!=this.nodos.tam()){
		//Obtengo la lista de nodos asociada a la pos de cada iteracion
		Lista<Nodo> nodos =(MiLista<Nodo>) this.nodos.get(i);
		
		//si la lista existe
		if (nodos!=null){
		  //Obtengo su iterador
		  ListIterator<Nodo> it = ((MiLista<Nodo>) nodos).iterator();
		  
		  int j = 0;
		  while (j!=nodos.getSize()){
			 //agrego los nodos a la nueva lista en cada iteracion
			 Nodo n = (Nodo) it.next().clone();
			 lista.add(n);
			 j++;
		  }
		}
		i++;
	 }
	 return lista;
  }
  
  /**
	* Devuelve una lista con todos los arcos del grafo.
	*/
  @Override
  @SuppressWarnings("unchecked")
  public Lista<Arco> getArcos() {
	 Lista<Arco> lista = new MiLista<Arco>();
	 Arco a = null;
	 
	 if (this.numArcos==0){
		return lista;
	 }        
	 
	 int i = 0;
	 int k = 0;
	 while (i!=this.nodos.tam()){
		//itero en cada pos, y si existe la lista, agrego todos sus arcos 
		if (this.nodos.get(i)!=null){
		  //obtengo la lista de arcos
		  //Solo obtengo la lsita In para evitar arcos repetidos
		  MiLista<Arco> listaIn =(MiLista<Arco>) this.arcosIn.get(i);
		  ListIterator<Arco> it = listaIn.iterator();
		  k = 0;
		  
		  while (k!=listaIn.getSize()) {
			 a = it.next();
			 lista.add((Arco)a.clone());
			 k++;
		  }
		}
		i++;
	 }
	 return lista;
  }
  
  /**
	* Devuelve el numero de nodos que hay en el grafo.
	*/
  @Override
  public int getNumNodos() {
	 return this.numNodos;
  }
  
  /**
	* Devuelve el numero de arcos que hay en el grafo.
	*/
  @Override
  public int getNumArcos() {
	 return this.numArcos;
  }
  
  /**
	* Devuelve una lista con los predecesores del nodo n.
	*/
  @Override
  @SuppressWarnings("unchecked")
  public Lista<Nodo> getPred(Nodo n) {
	 Lista<Nodo> lista = new MiLista<Nodo>();
	 if (this.numNodos==0 || n==null){
		return lista;
	 }
	 
	 int pos = this.pos(n);
	 if (pos==-1){
		return lista;
	 }
	 //obtengo la lista de nodos asociada al Nodo n
	 Lista<Nodo> nodos = (MiLista<Nodo>) this.nodos.get(pos);
	 boolean esta = nodos!=null && nodos.contains(n);
	 
	 //si el nodo esta
	 if (esta){
		//obtengo su lista de arcos
		MiLista<Arco> listaIn = ((MiLista<Arco>) this.arcosIn.get(pos));
		ListIterator<Arco> it = listaIn.iterator();
		int k = 0;
		
		//itero para recorrer la lista 
		while (k!=listaIn.getSize()) {
		  Arco a = it.next();
		  Nodo src = new Nodo(a.getSrc());
		  //si el nodo destino es el mismo que el nodo n
		  if (a.getDst().equalsIgnoreCase(n.toString())){
			 lista.add(src);
		  }
		  k++;
		}
	 }
	 return lista;
  }
  
  /**
	* Devuelve una lista con los sucesores del nodo n.
	*/
  @Override
  @SuppressWarnings("unchecked")
  public Lista<Nodo> getSuc(Nodo n) {
	 Lista<Nodo> lista = new MiLista<Nodo>();
	 if (this.numArcos==0 || n==null){
		return lista;
	 }
	 
	 int pos = this.pos(n);
	 if (pos==-1){
		return lista;
	 }
	 //obtengo la lista de nodos asociada al Nodo n
	 Lista<Nodo> nodos = (MiLista<Nodo>) this.nodos.get(pos);
	 boolean esta = nodos!=null && nodos.contains(n);
	 
	 //si el nodo esta	
	 if (esta){
		//obtengo su lista de arcos
		MiLista<Arco> listaOut = ((MiLista<Arco>) this.arcosOut.get(pos));
		ListIterator<Arco> it = listaOut.iterator();
		int k = 0;
		//itero para recorrer la lista
		
		while (k!=listaOut.getSize()) {
		  Arco a = it.next();
		  Nodo dst = new Nodo(a.getDst());
		  //si el nodo fuente es el mismo que el nodo n
		  if(a.getSrc().equalsIgnoreCase(n.toString())){
			 lista.add(dst);
		  }
		  k++;
		}
	 }
	 return lista;
  }
  
  /**
	* Devuelve una lista con los arcos que tienen al nodo n como destino.
	*/
  @SuppressWarnings("unchecked")
  @Override
  public Lista<Arco> getIn(Nodo n) {
	 Lista<Arco> lista = new MiLista<Arco>();
	 if (this.numNodos==0 || n==null){
		return lista;
	 }
	 
	 int pos = this.pos(n);
	 if (pos==-1){
		return lista;
	 }
	 //obtengo la lista de nodos asociada al Nodo n
	 Lista<Nodo> nodos = (MiLista<Nodo>) this.nodos.get(pos);
	 boolean esta = nodos!=null && nodos.contains(n);
	 //si el nodo esta	
	 if (esta){
		//obtengo su lista de arcos
		MiLista<Arco> listaIn = ((MiLista<Arco>) this.arcosIn.get(pos));
		ListIterator<Arco> it = listaIn.iterator();
		int k = 0;
		//itero para recorrer la lista
		while (k!=listaIn.getSize()) {
		  Arco a = it.next();
		  //si el nodo destino es el mismo que el nodo n
		  if (a.getDst().equalsIgnoreCase(n.toString())){
			 lista.add((Arco)a.clone());
		  }
		  k++;
		}
	 }
	 return lista;
  }
  
  /**
	* Devuelve una lista con los arcos que tienen al nodo n como fuente.
	*/
  @SuppressWarnings("unchecked")
  @Override
  public Lista<Arco> getOut(Nodo n) {
	 Lista<Arco> lista = new MiLista<Arco>();
	 if (this.numNodos==0 || n==null){
		return lista;
	 }
	 
	 int pos = this.pos(n);
	 if (pos==-1){
		return lista;
	 }
	 //obtengo la lista de nodos asociada al Nodo n
	 Lista<Nodo> nodos = (MiLista<Nodo>) this.nodos.get(pos);
	 boolean esta = nodos!=null && nodos.contains(n);
	 
	 //si el nodo esta	
	 if (esta){
		//obtengo su lista de arcos
		MiLista<Arco> listaOut = ((MiLista<Arco>) this.arcosOut.get(pos));
		ListIterator<Arco> it = listaOut.iterator();
		int k = 0;
		//itero para recorrer la lista
		while (k!=listaOut.getSize()) {
		  Arco a = it.next();
		  //si el nodo destino es el mismo que el nodo n
		  if (a.getSrc().equalsIgnoreCase(n.toString())){
			 lista.add((Arco)a.clone());
		  }
		  k++;
		}
	 }
	 return lista;
  }
  
  /**
	* Devuelve una representacion en String del grafo.
	*/
  @SuppressWarnings("unchecked")
  @Override
  public String toString() {
	 String ret ="NumNodos:"+ this.numNodos + ":" +"NumArcos:"+ this.numArcos ;
	 
	 int i = 0;
	 while(i!=this.nodos.tam()){
		//Obtengo la lista de nodos asociada a la pos de cada iteracion
		Lista<Nodo> nodos =(MiLista<Nodo>) this.nodos.get(i);
		
		//si la lista existe
		if (nodos!=null){
		  //Obtengo su iterador
		  ListIterator<Nodo> it = ((MiLista<Nodo>) nodos).iterator();				
		  int j = 0;
		  while (j!=nodos.getSize()){
			 //agrego los nodos a la nueva lista en cada iteracion
			 ret = ret + "\n"+it.next().toString();
			 j++;
		  }
		}
		i++;
	 }
	 
	 i = 0;
	 int k = 0;
	 while (i!=this.nodos.tam()){
		//itero en cada pos, y si existe la lista, agrego todos sus arcos 
		if (this.nodos.get(i)!=null){
		  //obtengo la lista de arcos
		  //Solo obtengo la lsita In para evitar arcos repetidos
		  MiLista<Arco> listaIn =(MiLista<Arco>) this.arcosIn.get(i);
		  ListIterator<Arco> it = listaIn.iterator();
		  k = 0;
		  while (k!=listaIn.getSize()) {
			 ret = ret + "\n"+it.next().toString();
			 k++;
		  }
		}
		i++;
	 }
	 return ret;
  }
  /**
	* Devuelve la posicion de n en un arreglo
	* dependiendo del tamanio del mismo
	*/
  private int pos(Nodo n){
	 if (n==null){
		return -1;
	 }
	 int pos = n.hashCode()%this.nodos.tam();
	 if (pos<0){
		pos = pos + this.nodos.tam();
	 }
	 return pos;
  }
  
  /**
	* Devuelve el numero de colisiones de la tabla de hash
	*/
  @Override
  public int colisiones(){
	 return this.colisiones;
  }
  
  /**
	* Amplia la tabla de hash dado un factor de carga
	* de numero de nodos dividido entre numero de elementos
	*/
  private void ampliar(){
	 //Si el factor de carga (Numero de nodos)/(numero de espacios) es mayor
	 if (this.numNodos*1.0/this.nodos.tam()>=0.75){
		
		Lista<Nodo> nodos = this.getNodos();
		Lista<Arco> arcos = this.getArcos();
		
		//redimensiono y borro los arreglos
		this.nodos.resize();
		this.arcosIn.resize();
		this.arcosOut.resize();
		
		//hago los otros atributos cero
		this.numArcos = 0;
		this.numNodos = 0;
		this.colisiones = 0;
		
		ListIterator<Nodo> it1 = ((MiLista<Nodo>)nodos).iterator();
		int i = 0;
		while (i!=nodos.getSize()){
		  Nodo n = it1.next();
		  this.add(n);
		  i++;
		}
		
		ListIterator<Arco> it2 = ((MiLista<Arco>)arcos).iterator();
		i = 0;
		
		while (i!=arcos.getSize()){
		  Arco a = it2.next();
		  this.add(a);
		  i++;
		}
	 }
  }
  
  /**
	* Retorna el nodo n. Si no existe dicho nodo, 
	* retorna null.
	*/	
  @SuppressWarnings("unchecked")
  public Nodo get(Nodo n){
	 if (n==null){
		return null;
	 }
	 int pos = this.pos(n); 
	 Nodo out = null;
	 
	 if (this.nodos.get(pos)==null){
		return null;
	 }
	 MiLista<Nodo> nodos = (MiLista<Nodo>)this.nodos.get(pos);
	 ListIterator<Nodo> it = nodos.iterator();
	 int i=0;
	 boolean esta = false;
	 while(i!=nodos.getSize() && !esta){
		Nodo A = it.next();
		esta = n.equals(A);
		if (esta){
		  out = A;
		}
		i++;
	 }
	 return out;      
  }
  
}
