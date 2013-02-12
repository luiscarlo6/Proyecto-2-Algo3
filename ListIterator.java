
/**
 * Interfaz que describe un iterador sobre listas.
 * Es una interface parametrizada con tipo (clase) E.
 * 
 * Luiscarlo Rivera, 09-11020
 * Jose Prado, 09-11006
 * 
 * Proyecto 2
 */
public interface ListIterator<E> {

	/**
	 * Comprueba que exista un proximo elemento.
	 */
	public boolean hasNext();

	/**
	 * Comprueba que exista un proximo elemento.
	 */
	public boolean hasPrev();

	/**
	 * Devuelve el elemento asociado y avanza el iterador.
	 */
	public E next();

	/**
	 * Devuelve el elemento asociado y retrocede el iterador.
	 */
	public E prev();

	/**
	 * Remueve de la lista el ultimo elemento retornado
	 */
	public void unlink();
}

// End ListIterator.