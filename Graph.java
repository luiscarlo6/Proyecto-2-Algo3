
/**
 * Interfaz que define el uso de un grafo.
 * Luiscarlo Rivera, 09-11020
 * Jose Prado, 09-11006
 * 
 * Proyecto 2
 */
public interface Graph {

	/**
	 * Agrega el nodo n. Si el nodo ya existe en el grafo, retorna false.
	 * Si se agrega correctamente el nodo, retorna true.
	 */
	public boolean add(Nodo n);

	/**
	 * Agrega el Arco a. Si los nodos del arco no existen
	 * en el grafo o si ya existe un lado entre dichos nodos,
	 * retorna false. 
	 * Si se agrega correctamente el nodo, retorna true.
	 */
	public boolean add(Arco a);

	/**
	 * Retorna un grafo nuevo que es una copia del grafo actual.
	 */
	public Object clone();

	/**
	 * Retorna true si el grafo contiene un nodo igual a n,
	 * si no retorna false.
	 */
	public boolean contains(Nodo n);

	/**
	 * Retorna true si el grafo contiene un arco igual a a,
	 * si no retorna false.
	 */
	public boolean contains(Arco a);

	/**
	 * Remueve del grafo el nodo n con todos sus arcos relacionados.
	 * Si el grafo se modifica (si el nodo existia en este), retorna true.
	 * Si el grafo se mantiene igual, retorna false.
	 */
	public boolean remove(Nodo n);

	/**
	 * Remueve el arco a del grafo.
	 * Si el grafo se modifica (si el arco existia en este), retorna true.
	 * Si el grafo se mantiene igual, retorna false.
	 */
	public boolean remove(Arco a);

	/**
	 * Devuelve una lista con todos los nodos del grafo.
	 */
	public Lista<Nodo> getNodos();

	/**
	 * Devuelve una lista con todos los arcos del grafo.
	 */
	public Lista<Arco> getArcos();

	/**
	 * Devuelve el numero de nodos que hay en el grafo.
	 */
	public int getNumNodos();

	/**
	 * Devuelve el numero de arcos que hay en el grafo.
	 */
	public int getNumArcos();

	/**
	 * Devuelve una lista con los predecesores del nodo n.
	 */
	public Lista<Nodo> getPred(Nodo n);

	/**
	 * Devuelve una lista con los sucesores del nodo n.
	 */
	public Lista<Nodo> getSuc(Nodo n);

	/**
	 * Devuelve una lista con los arcos que tienen al nodo n como destino.
	 */
	public Lista<Arco> getIn(Nodo n);

	/**
	 * Devuelve una lista con los arcos que tienen al nodo n como fuente.
	 */
	public Lista<Arco> getOut(Nodo n);

	/**
	 * Devuelve una representacion en String del grafo.
	 */
	@Override
	public String toString();

	public int colisiones();

	/**
	 * Retorna el nodo n. Si no existe dicho nodo, 
	 * retorna null.
	 */
	public Nodo get(Nodo n);
}
