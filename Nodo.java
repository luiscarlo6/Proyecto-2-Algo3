
/**
 * Clase que almacena la informacion de las aristas en el grafo.
 * 
 * Luiscarlo Rivera, 09-11020
 * Jose Prado, 09-11006
 * 
 * Proyecto 2
 */
public class Nodo {

	//id es unico
	private String id;
	//unidades de alcanzablidad, inicializado en -1
	private int horas;
	private boolean visitado;
	private Nodo anterior;

	/**
	 * Constructor por defecto
	 **/
	public Nodo() {
		this.id = "";
	}

	/**
	 * Crea un nodo con id i.
	 */
	public Nodo(String i) {
		this.id = new String(i);
		this.horas = -1;
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

		if (o == null) {
			return false;
		}

		if (!(o instanceof Nodo)) {
			return false;
		}

		n = (Nodo) o;

		if (this.id.equalsIgnoreCase(n.id)) {
			return true;
		}

		return false;
	}

	/**
	 * Retorna el codigo hash para un nodo.
	 */
	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	/**
	 * retorna las horas de alcanzabilidad de nodos
	 */
	public int horas() {
		return this.horas;
	}

	/**
	 * cambia las horas de alcanzabilidad el nodo por h
	 */
	public void cambiarHoras(int h) {
		this.horas = h;
	}

	/**
	 * retorna las horas segun el tipo de nodo
	 * si es nodo para dormir (Ver Informe) "d1", "d2", "d3", retorna 8 
	 * en otro caso retorna 1
	 */
	public int horasTipo() {
		int horasTipo;

		if (this.id.contains("d1") || this.id.contains("d2") || this.id.contains("d3")) {
			horasTipo = 8;
		} else {
			horasTipo = 1;
		}

		return horasTipo;
	}

	/**
	 * devuelve el estado de visita de un nodo
	 * true si esta visitado, false si no lo esta
	 */
	public boolean visitado() {
		return this.visitado;
	}

	/**
	 * cambia el estado de visita del nodo
	 */
	public void setVisitado(boolean v) {
		this.visitado = v;
	}

	/**
	 * dice si un nodo es caminable, esto es
	 * si un nodo es Llanura, Selva, "S" o "D"
	 */
	public boolean esCaminable() {
		//Si el string del nodo contiene "_", no es caminable (Ver informe)
		if (this.id.contains("_")) {
			return false;
		}
		return true;
	}

	/**
	 * Cambia el nodo anterior a este nodo por n
	 */
	public void cambiarAnterior(Nodo n) {
		this.anterior = n;
	}

	/**
	 * devuelve el nodo anterior a este nodo
	 */
	public Nodo getAnt() {
		return this.anterior;
	}

	/**
	 * dice si el nodo es Llanura
	 */
	public boolean esLlanura() {		
		return this.esCaminable() && this.id.contains("Llanura");
	}

	/**
	 * dice si el nodo es Selva
	 */
	public boolean esSelva() {
		return this.esCaminable() && this.id.contains("Selva");
	}
} /*Fin de nodo*/
