/**
 * Clase que implementa la interfaz Graph para un 
 * grafo dirigido con tablas de hash.
 */
public class DiGraphHash implements Graph{
	
	private int numNodos, numArcos;
	public int colisiones;
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
	
	@Override
	public int colisiones(){
		return this.colisiones;
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

		if(this.numNodos >= this.nodos.tam()){
			this.ampliar();
//			System.out.println("GRAFO LLENO "+this.numNodos+" Nodos"+ this.nodos.tam());
//			return false;
		}

//		int pos = n.hashCode()%this.nodos.tam();
		int pos = this.pos(n);
		if (pos==-1){
			return false;
		}

		if (this.nodos.get(pos)==null){
			this.nodos.add(new MiLista<Nodo>(), pos);
			this.arcosIn.add(new MiLista<Arco>(),pos);
			this.arcosOut.add(new MiLista<Arco>(),pos);
		}
		else{
			this.colisiones++;
		}
		Lista<Nodo> lista = (MiLista<Nodo>) this.nodos.get(pos);
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
        	Lista<Nodo> listaSrc = (MiLista<Nodo>) this.nodos.get(posSrc);
        	Lista<Nodo> listaDst = (MiLista<Nodo>) this.nodos.get(posDst);
        	
    		MiLista<Arco> out =(MiLista<Arco>) this.arcosOut.get(posSrc);
			MiLista<Arco> in =(MiLista<Arco>) this.arcosIn.get(posDst);
			
			boolean estaSrc = listaSrc.contains(src);
        	boolean estaDst = listaDst.contains(dst);
			as = !in.contains(a) && !out.contains(a);			
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

		ListIterator<Nodo> nodos =((MiLista<Nodo>) getNodos()).iterator();

		int i = 0;
		while (i!=numNodos) {
		    Nodo n = nodos.next();
		    nuevo.add((Nodo)n.clone());
		    i++;
		}       

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
		
		Object[] arcosIn = ((MiLista<Arco>) this.arcosIn.get(pos)).toArray();
		Object[] arcosOut = ((MiLista<Arco>) this.arcosOut.get(pos)).toArray();
		int i = 0;
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
    		MiLista<Arco> out =((MiLista<Arco>) this.arcosOut.get(posSrc));
			MiLista<Arco> in =((MiLista<Arco>) this.arcosIn.get(posDst));
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
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Lista<Nodo> getNodos() {
		Lista<Nodo> lista = new MiLista();
		int i = 0;

		
		while(i!=this.nodos.tam()){
			Lista<Nodo> nodos =(MiLista<Nodo>) this.nodos.get(i);
			if (nodos!=null){
				ListIterator<Nodo> it = ((MiLista<Nodo>) nodos).iterator();
				
				int j = 0;
				while (j!=nodos.getSize()){
					Nodo n = (Nodo) it.next();
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
        	
        	if (this.nodos.get(i)!=null){
        			
        		MiLista<Arco> listaIn =(MiLista<Arco>) this.arcosIn.get(i);
        		MiLista<Arco> listaOut = ((MiLista<Arco>) this.arcosOut.get(i));
        		ListIterator<Arco> it = listaIn.iterator();
        		k = 0;
        		
        		while (k!=listaIn.getSize()) {
            		a = it.next();
            		if (!lista.contains(a)){
            			lista.add(a);
            		}
            		k++;
            	}
        		
        		
        		it = listaOut.iterator();
        		k = 0;
        		while (k!=listaOut.getSize()) {
            		a = it.next();
            		if (!lista.contains(a)){
            			lista.add(a);
            		}
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
        Lista<Nodo> nodos = (MiLista<Nodo>) this.nodos.get(pos);
        boolean esta = nodos!=null && nodos.contains(n);
       
    	if (esta){
    		MiLista<Arco> listaIn = ((MiLista<Arco>) this.arcosIn.get(pos));
			ListIterator<Arco> it = listaIn.iterator();
    		int k = 0;

    		while (k!=listaIn.getSize()) {
    			Arco a = it.next();
        		Nodo src = new Nodo(a.getSrc());
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
    	
        Lista<Nodo> nodos = (MiLista<Nodo>) this.nodos.get(pos);
        boolean esta = nodos!=null && nodos.contains(n);
       
    	if (esta){
    		MiLista<Arco> listaOut = ((MiLista<Arco>) this.arcosOut.get(pos));
			ListIterator<Arco> it = listaOut.iterator();
    		int k = 0;
    		while (k!=listaOut.getSize()) {
    			Arco a = (Arco) it.next();
        		Nodo dst = new Nodo(a.getDst());
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
        Lista<Nodo> nodos = (MiLista<Nodo>) this.nodos.get(pos);
        boolean esta = nodos!=null && nodos.contains(n);
       
    	if (esta){
    		MiLista<Arco> listaIn = ((MiLista<Arco>) this.arcosIn.get(pos));
			ListIterator<Arco> it = listaIn.iterator();
    		int k = 0;

    		while (k!=listaIn.getSize()) {
    			Arco a = it.next();
        		if (a.getDst().equalsIgnoreCase(n.toString())){
        			lista.add(a);
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
        Lista<Nodo> nodos = (MiLista<Nodo>) this.nodos.get(pos);
        boolean esta = nodos!=null && nodos.contains(n);
       
    	if (esta){
    		MiLista<Arco> listaOut = ((MiLista<Arco>) this.arcosOut.get(pos));
			ListIterator<Arco> it = listaOut.iterator();
    		int k = 0;

    		while (k!=listaOut.getSize()) {
    			Arco a = it.next();
        		if (a.getSrc().equalsIgnoreCase(n.toString())){
        			lista.add(a);
        		}
        		k++;
        	}
		}
    	return lista;
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
	
	
	private void ampliar(){
		Lista<Nodo> nodos = this.getNodos();
		Lista<Arco> arcos = this.getArcos();
		this.nodos.resize();
		this.arcosIn.resize();
		this.arcosOut.resize();
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