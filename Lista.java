/**
 * Interfaz que define el comportamiento de una lista
 * 
 * Esta es una clase parametrizada con tipo (clase) E; i.e., la
 * lista contiene elementos de tipo E.
 * 
 * Luiscarlo Rivera, 09-11020
 * Jose Prado, 09-11006
 * 
 * Proyecto 1
 */
public interface Lista<E> {

	/**
	 * Agrega un elemento a la lista.
	 */
	public boolean add(E element);

	/**
	 *  Retorna el primer de la lista.
	 */
	public E get();

	/**
	 * Determina si el elemento dado esta en la lista.
	 */
	public boolean contains(E element);

	/**
	 * Determina si la lista dada es igual a la lista.
	 */
	public boolean equals(Lista<E> list);

	/**
	 * Determina si la lista es vacia.
	 */
	public boolean isEmpty();

	/**
	 * Elimina el elemento dado de la lista. Si la lista cambia,
	 * retorna true, sino retorna false.
	 */
	public boolean remove(E element);

	/**
	 * Retorna el numero de elementos en la lista
	 */
	public int getSize();

	/**
	 * Retorna un arreglo que contiene todos los elementos
	 * en esta lista.
	 */
	public Object[] toArray();

	public String toString();

	public void clear();

}
