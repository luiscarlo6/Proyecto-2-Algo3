/**
 * Clase que implementa la interfaz Graph para un 
 * grafo dirigido de matriz de adyacencia.
 * Luiscarlo Rivera, 09-11020
 * Jose Prado, 09-11006
 * 
 * Proyecto 1
 */
public class DiGraphMatriz implements Graph{
  
  private int numNodos, numArcos;
  private Nodo nodos[];
  private boolean arcos[][];
  private int anterior,actual;
  
  public DiGraphMatriz () {
	 this.numNodos = 0;
	 this.numArcos = 0;
	 this.anterior = 89;
	 this.actual = 144;
	 this.nodos = new Nodo[this.actual];
	 this.arcos = new boolean[this.actual][this.actual];
  }
  
  private  DiGraphMatriz (int anterior, int actual) {
	 this.numNodos = 0;
	 this.numArcos = 0;
	 this.anterior = anterior;
	 this.actual = actual;
	 this.nodos = new Nodo[this.actual];
	 this.arcos = new boolean[this.actual][this.actual];
  }
  
  
  /**
	* Agrega el nodo n. Si el nodo ya existe en el grafo, retorna false.
	* Si se agrega correctamente el nodo, retorna true.
	*/
  @Override
  public boolean add(Nodo n) {
	 
	 //se verifica que el elemento no este contenido y que sea diferente
	 //de null
	 if(this.contains(n) || n==null){
		return false;
	 }
	 this.Ampliar();// Almplia la matriz si se encuentra totalmente llena
	 this.nodos[this.numNodos] = n;
	 this.numNodos++;
	 return true;
  }
  
  /**
	* Agrega el Arco a. Si los nodos del arco no existen en el grafo 
	* o si ya existe un lado entre dichos nodos, retorna false. 
	* Si se agrega correctamente el nodo, retorna true.
	*/
  @Override
  public boolean add(Arco a) {
	 
	 //verifica que el elemento sea diferente de null
	 if(a==null){
		return false;
	 }
	 // se crea un arreglo donde se van a guardar las posiciones
	 // de los nodos si existen
	 int Temp[]=new int[2];
	 Temp[0] = -1;
	 Temp[1] = -1;
	 
	 // se buscan los dos nodos que forman el arco
	 this.Buscar(Temp, a.getSrc(), a.getDst());
	 
	 //se verifica que ambos nodos esten
	 if (Temp[0]>-1 && Temp[1]>-1){
		this.arcos[Temp[0]][Temp[1]]=true;
		this.numArcos++;
		return true;
	 }
	 return false;
  }
  
  /**
	* Retorna un grafo nuevo que es una copia del grafo actual.
	*/
  @Override
  public Object clone() {
	 
	 DiGraphMatriz nuevaMatriz=null;
	 int i=0,j=0,cont=0;
	 // se crea la matriz que sera el clon de this
	 nuevaMatriz=new DiGraphMatriz (this.anterior,this.actual); 
	 
	 //se recorre el arreglo this.nodos y se crea una nueva instancia de 
	 //este para el clon
	 while(i!=this.numNodos){
		nuevaMatriz.add(new Nodo(this.nodos[i].toString()));
		i++;
	 }
	 
	 // como el tipo boolean no es un objeto solo hace falta cambiar
	 // los valores en la nueva matriz
	 i=0;j=0;
	 while(i!=this.arcos.length && cont!=this.numArcos){
		j=0;
		while(j!=this.arcos.length && cont!=this.numArcos){
		  if (this.arcos[i][j]){
			 nuevaMatriz.arcos[i][j]=true;
			 cont++;
		  }
		  j++;
		}
		i++;
	 }
	 nuevaMatriz.numArcos=cont;
	 return nuevaMatriz;
  }
  
  
  /**
	* Retorna true si el grafo contiene un nodo igual a n,
	* si no retorna false.
	*/
  @Override
  public boolean contains(Nodo n) {
	 // como el arreglo no contiene elementos nulos
	 // si n es null no hace falta buscar
	 if(n==null){
		return false;
	 }
	 
	 int i=0;
	 //se recorre todo el arreglo this.nodos de forma lineal
	 while(i!=this.numNodos){
		if(this.nodos[i].equals(n)){
		  return true;
		}
		i++;
	 }
	 return false;
  }
  
  /**
	* Retorna true si el grafo contiene un arco igual a a,
	* si no retorna false.
	*/
  @Override
  public boolean contains(Arco a) {
	 // como el arreglo no contiene elementos nulos
	 // si n es null no hace falta buscar
	 if (a==null){
		return false;
	 }
	 
	 int Temp[]=new int[2];
	 Temp[0] = -1;
	 Temp[1] = -1;
	 //se busca la existencia de los nodos
	 this.Buscar(Temp, a.getSrc(), a.getDst());
	 
	 //se verifica que efectivamente los nodos existen
	 if (Temp[0]>-1 && Temp[1]>-1){
		// como arcos es un arreglo boolean el mismo funciona de condicion
		if(this.arcos[Temp[0]][Temp[1]]){
		  return true;
		}
	 }
	 return false;
  }
  
  /**
	* Remueve del grafo el nodo n con todos sus arcos relacionados.
	* Si el grafo se modifica (si el nodo existia en este), retorna true.
	* Si el grafo se mantiene igual, retorna false.
	*/
  @Override
  public boolean remove(Nodo n) {
	 
	 //verifica que n no sea null
	 if(n==null){
		return false;
	 }
	 
	 int Pos=-1;
	 //busca la posicion del elemento a eliminar
	 Pos= this.Buscar(n.toString());
	 
	 // si pos es valida procede a eliminar el nodo y los arcos relacionados
	 // con n
	 if (Pos!=-1){
		//elimina todas las instancia del nodo con todos los arcos que lo
		//contenian 
		this.Reajustar(Pos);
		
		this.numNodos--;
		return true;
	 }
	 return false;
  }
  
  /**
	* Remueve el arco a del grafo.
	* Si el grafo se modifica (si el arco existia en este), retorna true.
	* Si el grafo se mantiene igual, retorna false.
	*/
  @Override
  public boolean remove(Arco a) {
	 
	 if (a==null){
		return false;
	 }	
	 
	 int Temp[]=new int[2];
	 Temp[0] = -1;
	 Temp[1] = -1;
	 
	 //busca los nodos que componen al arco
	 this.Buscar(Temp, a.getSrc(), a.getDst());
	 
	 // verifica que las posiciones sean validas
	 if (Temp[0]>-1 && Temp[1]>-1){
		
		if(this.arcos[Temp[0]][Temp[1]]){
		  
		  this.arcos[Temp[0]][Temp[1]]=false;
		  this.numArcos--;
		  return true;
		}
	 }		
	 return false;
  }
  
  /**
	* Devuelve una lista con todos los nodos del grafo.
	*/
  @Override
  public Lista<Nodo> getNodos() {
	 int i=0;
	 if(this.numNodos==0){
		return null;
	 }
	 // se crea la lista
	 Lista<Nodo> LisNodo=new MiLista<Nodo>();
	 
	 //se insertan todos los nodos a la lista
	 while(i!=this.numNodos){
		LisNodo.add((Nodo)this.nodos[i].clone());
		i++;
	 }
	 return LisNodo;
  }
  
  /**
	* Devuelve una lista con todos los arcos del grafo.
	*/
  @Override
  public Lista<Arco> getArcos() {
	 int i=0,j=0,Cont=0;;
	 if(this.numNodos==0){
		return null;
	 }
	 Lista<Arco> LisArcos=new MiLista<Arco>();
	 
	 // se insertan todos los arcos a la lista
	 i=0;
	 while(i!=this.arcos.length && Cont!=this.numArcos){
		j=0;
		while(j!=this.arcos.length && Cont!=this.numArcos){
		  if(this.arcos[i][j]){
			 LisArcos.add(new Arco(this.nodos[i].toString(),
										  this.nodos[j].toString()));
			 Cont++;
		  }
		  j++;
		}
		i++;
	 }
	 return LisArcos;
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
  public Lista<Nodo> getPred(Nodo n) {
	 
	 int i=0,Pos=-1;
	 if (n ==null || this.numNodos==0){
		return null;
	 }
	 
	 Pos=this.Buscar(n.toString());
	 
	 if (Pos==-1){
		return null;
	 }
	 
	 Lista<Nodo> LisPredecesores = new MiLista<Nodo>();
	 
	 //se agregan todos los predecesores del nodo n
	 i=0;
	 while(i!=this.arcos.length){
		if (this.arcos[i][Pos]){
		  LisPredecesores.add(new Nodo(this.nodos[i].toString()));
		}
		i++;
	 }
	 return LisPredecesores;
	 
  }
  
  /**
	* Devuelve una lista con los sucesores del nodo n.
	*/
  @Override
  public Lista<Nodo> getSuc(Nodo n) {
	 
	 int i=0,Pos=-1;
	 if (n ==null || this.numNodos==0){
		return null;
	 }
	 
	 Pos=this.Buscar(n.toString());
	 
	 if (Pos==-1){
		return null;
	 }
	 
	 Lista<Nodo> LisSucesores = new MiLista<Nodo>();
	 
	 i=0;
	 while(i!=this.arcos.length){
		if (this.arcos[Pos][i]){
		  LisSucesores.add(new Nodo(this.nodos[i].toString()));
		}
		i++;
	 }
	 return LisSucesores;
  }
  
  /**
	* Devuelve una lista con los arcos que tienen al nodo n como destino.
	*/
  @Override
  public Lista<Arco> getIn(Nodo n) {
	 int i,Pos=-1;
	 if (n ==null || this.numNodos==0){
		return null;
	 }
	 
	 Pos=this.Buscar(n.toString());
	 
	 if (Pos==-1){
		return null;
	 }
	 
	 Lista<Arco> LisArco = new MiLista<Arco>();
	 
	 i=0;
	 while(i!=this.arcos.length){
		if (this.arcos[i][Pos]){
		  LisArco.add(new Arco(this.nodos[i].toString(),
									  this.nodos[Pos].toString()));
		}
		i++;
	 }
	 return LisArco;
  }
  
  /**
	* Devuelve una lista con los arcos que tienen al nodo n como fuente.
	*/
  @Override
  public Lista<Arco> getOut(Nodo n) {
	 
	 int i=0,Pos=-1;
	 if (n ==null || this.numNodos==0){
		return null;
	 }
	 
	 Pos=this.Buscar(n.toString());
	 
	 if (Pos==-1){
		return null;
	 }
	 
	 Lista<Arco> LisArco = new MiLista<Arco>();
	 
	 i=0;
	 while(i!=this.arcos.length){
		if (this.arcos[Pos][i]){
		  LisArco.add(new Arco(this.nodos[Pos].toString(),
									  this.nodos[i].toString()));
		}
		i++;
	 }
	 return LisArco;
  }
  
  /**
	* Devuelve una representacion en String del grafo.
	*/
  @Override
  public String toString() {
	 
	 String ret = numNodos + ":" + numArcos ;
	 
	 ListIterator<Nodo> nodos =((MiLista<Nodo>) getNodos()).iterator();
	 
	 int i = 0;
	 while (i!=numNodos) {
		Nodo n = nodos.next();
		ret += "\n" + n.toString();
		i++;
	 }       
	 
	 ListIterator<Arco> arcos = ((MiLista<Arco>) getArcos()).iterator();
	 
	 i = 0;
	 while (arcos.hasNext()) {
		Arco a = arcos.next();
		ret += "\n" + a.toString();
		i++;
	 }       
	 
	 return ret;
  }
  
  
  @Override
  public int colisiones() {
	 return 0;
  }
  
  
  /**
	* Redimensiona el arreglo y la matriz cuando numNodos >= nodos.length
	*/
  
  private final void Ampliar(){
	 
	 if (this.numNodos >= this.nodos.length){
		System.gc();//Invocacion del recolector de basura
		Nodo NodoNuevo[];
		boolean ArcosNuevos[][];
		int Temp;
		
		//recalculo el tamano del nuevo arreglo
		// calculando un fibonacci
		Temp = this.actual;
		this.actual = this.actual + this.anterior;
		this.anterior = Temp;
		
		//se crean el nuevo arreglo y matriz
		NodoNuevo = new Nodo[this.actual];
		ArcosNuevos = new boolean[this.actual][this.actual];
		
		//Se copia el arreglo
		System.arraycopy(this.nodos, 0, NodoNuevo, 0,
							  this.nodos.length);
		
		//se copian los arcos
		for (int i = 0; i != this.nodos.length; i++) {
		  System.arraycopy(this.arcos[i], 0, ArcosNuevos[i],
								 0, this.nodos.length);
		}
		
		this.nodos = NodoNuevo;
		this.arcos = ArcosNuevos;
		System.gc();
	 }
  }	
  
  
  /**
	*	Busca la existencia de los id de los nodos que componen a un arco
	*	y almacena las posiciones de estos en el arreglo Al
	*
	**/
  private final void Buscar(int[] Al, String N1, String N2){
	 int i=0,j=0;
	 
	 //se revisa el arreglo de nodos
	 while (i!=this.numNodos && j!=2){
		
		if(this.nodos[i].toString().equals(N1)){
		  Al[0] = i;
		  j++;
		}
		if (this.nodos[i].toString().equals(N2)){
		  Al[1] = i;
		  j++;
		}
		i++;
	 }
  }
  
  
  /**
	* Elimina las ocurrencias del nodo que se encuentra en la posicion Pos 
	* Pre: Pos >=0
	*
	**/
  
  
  private final void Reajustar(int Pos){
	 
	 System.gc();//Invocacion del recolector de basura
	 Nodo NodoNuevo[];
	 boolean ArcosNuevos[][];
	 
	 //Como no interesa perder el tamano en funcion del numero de 
	 //fibonacci se crean arreglo y matriz del mismo tamano
	 NodoNuevo = new Nodo[this.actual];
	 ArcosNuevos = new boolean[this.actual][this.actual];
	 
	 //ciclo que copia los elemento de this.nodo y this.arcos
	 //excepto el elemento en Pos
	 int i=0,j=0,k=0,l=0;
	 boolean Aux=false,Aux1=false,Aux2=false,Aux3=false;
	 while(i!=this.numNodos){
		j=0;
		if(i==Pos && !Aux3){
		  k=i+1;
		  Aux2=true;
		}else if(i!=Pos) {
		  k=i;
		}
		
		while(j!=this.numNodos-1){
		  
		  if(j==Pos && !Aux1){
			 l=j+1;
			 Aux =true;
		  }else if(j!=Pos && !Aux1){
			 l=j;
		  }
		  if(Aux){
			 l=j+1;
			 Aux1=true;
		  }
		  
		  ArcosNuevos[i][j]= this.arcos[k][l];//copia los elementos
		  j++;
		}
		
		if (Aux2){
		  k=i+1;
		  Aux3=true;
		}
		NodoNuevo[i]=this.nodos[k];
		i++;
	 }
	 this.nodos = NodoNuevo;
	 this.arcos = ArcosNuevos;
	 System.gc();
  }
  
  /**
	* Busca Que N1 Sea igual a algun id de los nodos 
	* post: retorna un int >=0 si el elemento existe o -1 en otros casos
	*
	**/
  
  private final int Buscar(String N1){
	 
	 int i=0,Pos=-1;
	 
	 while (i!=this.numNodos && !(Pos>-1)){
		
		if(this.nodos[i].toString().equals(N1)){
		  Pos = i;
		}
		i++;
	 }
	 return Pos;
  }
  
  public Nodo get(Nodo n){
	  return null;
  }
  
}//Fin
