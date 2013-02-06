
public class MainMio {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Nodo A;

//		String lectura = "S...****.................***D";
		String lectura = "S.......D";
		long tiempoInicio = System.currentTimeMillis();
		Graph grafo = llenar(lectura);
		long totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out.println("El tiempo de demora para llenar es :" + totalTiempo + " miliseg");
		Cola<Nodo> cola = new Cola<Nodo>();
		
		
		BFS(grafo);
		Nodo D = grafo.get(new Nodo("D"));
		try{
			System.out.println(D.horas());}catch(java.lang.NullPointerException as){
			
		}
		
//		tiempoInicio = System.currentTimeMillis();
//		Lista<Nodo> listaNodo = grafo.getNodos();
//		totalTiempo = System.currentTimeMillis() - tiempoInicio;
//		System.out.println("El tiempo de demora para getNodos es :" + totalTiempo + " miliseg");
//		ListIterator<Nodo> it = ((MiLista<Nodo>) listaNodo).iterator();
//		for (int i = 0; i < listaNodo.getSize(); i++){
//			A = it.next();
////			System.out.println(A.toString()+" "+A.horasTipo());
//			cola.encolar(A);
//		}

		System.out.println("Arcos: "+grafo.getNumArcos()+" Nodos: "+grafo.getNumNodos()+ " Colisiones: "+grafo.colisiones()+ " "+ (grafo.colisiones()*1.0 / grafo.getNumNodos())*100+"%");
//		long totalTiempo = System.currentTimeMillis() - tiempoInicio;
//		System.out.println("El tiempo de demora es :" + totalTiempo + " miliseg");
		
		
	}


	
	
	public static void BFS(Graph grafo){
		Cola<Nodo> cola = new Cola<Nodo>();
		Nodo S = grafo.get(new Nodo("S"));
		S.cambiarHoras(0);
		int hora = 6;
		int horasCaminadas = 0;
		cola.encolar(S);
		
		while (!cola.esVacia()){
			Nodo n = cola.primero();
			cola.desencolar();
			
			Lista<Nodo> sucesores = grafo.getSuc(n);
			ListIterator it = ((MiLista<Nodo>) sucesores).iterator();
			
//			while ((horasCaminadas == 16 || nocheYProxBosque(grafo,n))&&n.esCaminable()){
//				
//				quitarMasCercano(grafo,n);
//				horasCaminadas = 0;
//			}
			
			int i = 0;
			while (i!=sucesores.getSize()){
				Nodo m = (Nodo)it.next();
				m = grafo.get(m);
				
				if (!m.visitado()){
					m.setVisitado(true);
					m.cambiarHoras(n.horas()+m.horasTipo());
					m.cambiarAnterior(n);
					cola.encolar(m);				
				}				
				i++;
			}
			if (n.esCaminable()&&!n.equals(S)){
				hora = (n.horas()+6)%24;
				
				if (n.horas()-n.getAnt().horas()>1){
					horasCaminadas = 0;
				}
				else{
					horasCaminadas++;
				}
				
			}
		}
		
	}
	
	private static Graph llenar(String entr) {
		Graph grafo = new DiGraphHash();

		int len = entr.length();
		Nodo A,B;
		char lect[] = entr.toCharArray();
		A = new Nodo(java.lang.Character.toString(lect[0]));
		grafo.add(A);
		B = A;
		for (int i=1;i!=len-1;i++){
			String tipo = "";
			if (java.lang.Character.toString(lect[i]).equalsIgnoreCase(".")){
				tipo = "Llanura";
			}
			if (java.lang.Character.toString(lect[i]).equalsIgnoreCase("*")){
				tipo = "Selva";
				
			}
			A = new Nodo(tipo+i);
			grafo.add(A);
			Nodo A1,A2,A3;
			A1 = new Nodo(tipo+i+"_d1");
			A2 = new Nodo(tipo+i+"_d2");
			A3 = new Nodo(tipo+i+"_d3");
			
			grafo.add(A1);
			grafo.add(A2);
			grafo.add(A3);
			
			Arco arco;
			arco = new Arco(B.toString(),A.toString());
			grafo.add(arco);
			
			arco = new Arco(B.toString(),A1.toString());
			grafo.add(arco);
			
			arco = new Arco(B.toString(),A2.toString());
			grafo.add(arco);
			
			arco = new Arco(A2.toString(),A3.toString());
			grafo.add(arco);
			
			arco = new Arco(A1.toString(),A.toString());
			grafo.add(arco);
			
			arco = new Arco(A3.toString(),A.toString());
			grafo.add(arco);
			
			B = A;
		}
		
		A = new Nodo(java.lang.Character.toString(lect[len-1]));
		grafo.add(A);
		Nodo A1,A2,A3;
		A1 = new Nodo(java.lang.Character.toString(lect[len-1])+"d1");
		A2 = new Nodo(java.lang.Character.toString(lect[len-1])+"d2");
		A3 = new Nodo(java.lang.Character.toString(lect[len-1])+"d3");
		
		grafo.add(A1);
		grafo.add(A2);
		grafo.add(A3);
		
		Arco arco;
		arco = new Arco(B.toString(),A.toString());
		grafo.add(arco);
		
		arco = new Arco(B.toString(),A1.toString());
		grafo.add(arco);
		
		arco = new Arco(B.toString(),A2.toString());
		grafo.add(arco);
		
		arco = new Arco(A2.toString(),A3.toString());
		grafo.add(arco);
		
		arco = new Arco(A1.toString(),A.toString());
		grafo.add(arco);
		
		arco = new Arco(A3.toString(),A.toString());
		grafo.add(arco);
		
		B = A;

		return grafo;
	}
	
	private static void sort(Object arr[]) {
		// // yo si puedo hacer burbuja :-D

		// for (int i = 0; i < arr.length - 1; i++) {
		//     for (int j = i + 1; j < arr.length; j++) {
		// 	if (arr[i].toString().compareTo(arr[j].toString()) > 0) {
		// 	    Object o = null;
		// 	    o = arr[i];
		// 	    arr[i]= arr[j];
		// 	    arr[j]= o;
		// 	}
		//     }
		// }

		quick_srt(arr, 0, arr.length-1);
		
	    }
	
	
	public static void quick_srt(Object array[],int low, int n){
		int lo = low;
		int hi = n;
		if (lo >= n) {
		    return;
		}
		Object mid = array[(lo + hi) / 2];
		while (lo < hi) {
		    while (lo<hi && array[lo].toString().compareTo(mid.toString()) < 0) {
			lo++;
		    }
		    while (lo<hi && array[hi].toString().compareTo(mid.toString()) > 0) {
			hi--;
		    }
		    if (lo < hi) {
			Object T = array[lo];
			array[lo] = array[hi];
			array[hi] = T;
		    }
		}
		if (hi < lo) {
		    int T = hi;
		    hi = lo;
		    lo = T;
		}
		quick_srt(array, low, lo);
		quick_srt(array, lo == low ? lo+1 : lo, n);
    }
	
	public static boolean nocheYProxBosque(Graph grafo, Nodo n){
		int hora = (n.horas()+6)%24;
		boolean noche = hora>=18 && hora<6;
		Nodo B;
		if (!noche){
			return false;
		}
		boolean proxBosque = false;
		Lista<Nodo> sucesores = grafo.getSuc(n);
		ListIterator it = ((MiLista<Nodo>) sucesores).iterator();
		
		if(sucesores.getSize()==3){
			int i = 0;
			while (i!=sucesores.getSize()){
				Nodo A = (Nodo) it.next();
				if (A.esCaminable()&&A.toString().contains("Bosque")){
					return true;
				}
				i++;
			}
				
		}
		
		if(sucesores.getSize()==2){
			hora = (n.horas()+8+6)%24;
			noche = hora>=18 && hora<6;
			int i = 0;
			while (i!=sucesores.getSize()){
				Nodo A = (Nodo) it.next();
				if (noche && A.toString().contains("Bosque")&&A.toString().contains("d1")){
					return true;
				}
				i++;
			}
				
		}
		
		if(sucesores.getSize()==1){
			hora = (n.horas()+16+6)%24;
			noche = hora>=18 && hora<6;
			int i = 0;
			while (i!=sucesores.getSize()){
				Nodo A = (Nodo) it.next();
				if (noche && A.toString().contains("Bosque")&&A.toString().contains("d2")){
					return true;
				}
				i++;
			}
				
		}
		
		return false;
	}
	
	public static void quitarMasCercano(Graph grafo,Nodo n){
		Lista<Nodo> sucesores = grafo.getSuc(n);
		ListIterator it = ((MiLista<Nodo>) sucesores).iterator();

		
		if(sucesores.getSize()==3){
			int i = 0;
			while (i!=sucesores.getSize()){
				Nodo A = (Nodo) it.next();
				if (A.esCaminable()){
					grafo.remove(new Arco(n.toString(),A.toString()));
				}
				i++;
			}
				
		}
		
		if(sucesores.getSize()==2){
			int i = 0;
			while (i!=sucesores.getSize()){
				Nodo A = (Nodo) it.next();
				if (A.toString().contains("d1")){
					grafo.remove(new Arco(n.toString(),A.toString()));
				}
				i++;
			}
				
		}
		
		if(sucesores.getSize()==1){
			int i = 0;
			while (i!=sucesores.getSize()){
				Nodo A = (Nodo) it.next();
				if (A.toString().contains("d2")){
					grafo.remove(new Arco(n.toString(),A.toString()));
				}
				i++;
			}
				
		}
	}
}

