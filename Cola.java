/**
 * 
 */

/**
 * @author luiscarlo
 *
 */
public class Cola<E> {
	
	private Lista<E> lista;
	
	public Cola(){
		this.lista = new MiLista<E>();
	}
	
	public boolean esVacia(){
		return this.lista.getSize()==0;
	}
	
	public boolean encolar(E e){
		return this.lista.add(e);
	}
	
	public boolean desencolar(){
		E e = this.lista.get();
		return this.lista.remove(e);
	}
	
	public E primero(){
		return this.lista.get();
	}
	
	public int tam(){
		return this.lista.getSize();
	}
	
	public String toString(){
		return this.lista.toString();
	}
	
	public void clear(){
		this.lista.clear();
	}
}
