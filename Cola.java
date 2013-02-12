
/**
 * Clase que implementa el TAD Cola
 * sLuiscarlo Rivera, 09-11020
 * Jose Prado, 09-11006
 * 
 * Proyecto 2
 */
public class Cola<E> {

	private Lista<E> lista;//Lista de elementos de la cola

	/**
	 * Constructor por defecto
	 */
	public Cola() {
		this.lista = new MiLista<E>();
	}

	/**
	 * dice si la cola es vacia
	 */
	public boolean esVacia() {
		return this.lista.getSize() == 0;
	}

	/**
	 * Encola el elemento e
	 */
	public boolean encolar(E e) {
		return this.lista.add(e);
	}

	/**
	 * desencola el primer elemento
	 */
	public boolean desencolar() {
		E e = this.lista.get();
		return this.lista.remove(e);
	}

	/**
	 * retorna el primer elemento de la cola
	 */
	public E primero() {
		return this.lista.get();
	}

	/**
	 * retorna el numero de elementos en la cola 
	 */
	public int tam() {
		return this.lista.getSize();
	}

	/**
	 * retorna una representacion en String de la cola
	 */
	public String toString() {
		return this.lista.toString();
	}

	/**
	 * Vacia la cola, esta queda como recien creada
	 */
	public void clear() {
		this.lista.clear();
	}
}
