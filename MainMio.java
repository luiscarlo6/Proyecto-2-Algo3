
public class MainMio {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		String lectura = "S...****.................***D";
//		String lectura = "S.......D";
		String lectura = "S***********.***********D";
		long tiempoInicio = System.currentTimeMillis();
		Graph grafo = llenar(lectura);
		long totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out.println("El tiempo de demora para llenar es :" + totalTiempo + " miliseg");
		Nodo s = new Nodo("S");

		BFS(grafo,s);
		Nodo D = grafo.get(new Nodo("D"));
		try{
			System.out.println(D.horas());}catch(java.lang.NullPointerException as){
			
		}

		System.out.println(grafo.getNumNodos()+" Nodos, "+grafo.getNumArcos()+" Arcos, "+grafo.colisiones()+ " Colisiones, "+ (grafo.colisiones()*1.0 / grafo.getNumNodos())*100+"%");
		
		
	}


	
	
	@SuppressWarnings("rawtypes")
	public static void BFS(Graph grafo, Nodo s){
		Cola<Nodo> cola = new Cola<Nodo>();
		s = grafo.get(s);
		s.cambiarHoras(0);
		s.setVisitado(true);
		int horasCaminadas = 0;
		cola.encolar(s);
		
		while (!cola.esVacia()){
			Nodo n = cola.primero();
			cola.desencolar();
			int hora = horaFutura(grafo,n);
			boolean nocheFutura = ((hora>=18 &&hora<=23)|| (hora<6 && hora>=0));
			int h = 0;
			while (n.esCaminable()&&((horasCaminadas == 16) && n.esLlanura())||
									(sigBosque(grafo,n) && nocheFutura)){

				if (!nocheFutura || n.esLlanura()){
					boolean durmio = dormir(grafo,n);
					if (durmio){
						horasCaminadas = 0;	
					}
				}

				hora = horaFutura(grafo,n);
				nocheFutura = ((hora>=18 &&hora<=23)||(hora<6 && hora>=0));
				h++;
				if (h>3){
					cola.clear();
					n = n.getAnt();
					break;
				}
			}
			
			
			
			Lista<Nodo> sucesores = grafo.getSuc(n);
			ListIterator it = ((MiLista<Nodo>) sucesores).iterator();
			
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
			if (n.esCaminable()&&!n.equals(s)){
				horasCaminadas++;
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
		A1 = new Nodo(java.lang.Character.toString(lect[len-1])+"_d1");
		A2 = new Nodo(java.lang.Character.toString(lect[len-1])+"_d2");
		A3 = new Nodo(java.lang.Character.toString(lect[len-1])+"_d3");
		
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
	
	public static boolean dormir(Graph grafo, Nodo n){
		Lista<Nodo> sucesores = grafo.getSuc(n);
		ListIterator it = ((MiLista<Nodo>) sucesores).iterator();
		boolean sal = false;
		int i = 0;

		while (i!=sucesores.getSize()){
			Nodo A = (Nodo) it.next();
			if(sucesores.getSize()==3 && A.esCaminable()){
				sal = grafo.remove(new Arco (n.toString(),A.toString()));
			}
			
			if(sucesores.getSize()==2 && A.toString().contains("d1")){
				Lista<Nodo> sucesor_d1 = grafo.getSuc(A);
				ListIterator it_d1 = ((MiLista<Nodo>) sucesor_d1).iterator();
				Nodo B = (Nodo) it_d1.next();
				if (B==null){
					break;
				}
				
				sal = grafo.remove(new Arco (n.toString(),A.toString())) && 
					  grafo.remove(new Arco(A.toString(),B.toString()));
			}
			
			if(sucesores.getSize()==1 && A.toString().contains("d2")){
				Lista<Nodo> sucesor_d1 = grafo.getSuc(A);
				ListIterator it_d1 = ((MiLista<Nodo>) sucesor_d1).iterator();
				Nodo B = (Nodo) it_d1.next();
				if (B==null){
					break;
				}
				
				Lista<Nodo> sucesor_d2 = grafo.getSuc(B);
				ListIterator it_d2 = ((MiLista<Nodo>) sucesor_d2).iterator();
				Nodo C = (Nodo) it_d2.next();
				if (C==null){
					break;
				}
				
				sal = grafo.remove(new Arco (n.toString(),A.toString())) && 
						grafo.remove(new Arco(A.toString(),B.toString()))&&
						grafo.remove(new Arco(B.toString(),C.toString()));
			}
			
			
			i++;
		}
		return sal;
		
	}
	

	public static boolean sigBosque(Graph grafo, Nodo n){
		Lista<Nodo> sucesores = grafo.getSuc(n);
		ListIterator it = ((MiLista<Nodo>) sucesores).iterator();
		boolean sal = false;
		int i = 0;
		while (i!=sucesores.getSize()){
			Nodo A = (Nodo) it.next();
			if(sucesores.getSize()==3 && A.esCaminable()){
				
				sal = A.toString().contains("Selva");
			}
			
			if(sucesores.getSize()==2 && A.toString().contains("d1")){
				Lista<Nodo> sucesor_d1 = grafo.getSuc(A);
				ListIterator it_d1 = ((MiLista<Nodo>) sucesor_d1).iterator();
				Nodo B = (Nodo) it_d1.next();
				if (B==null){
					break;
				}
				sal = B.toString().contains("Selva");

			}
			
			if(sucesores.getSize()==1 && A.toString().contains("d2")){
				Lista<Nodo> sucesor_d1 = grafo.getSuc(A);
				ListIterator it_d1 = ((MiLista<Nodo>) sucesor_d1).iterator();
				Nodo B = (Nodo) it_d1.next();
				if (B==null){
					break;
				}
				
				Lista<Nodo> sucesor_d2 = grafo.getSuc(B);
				ListIterator it_d2 = ((MiLista<Nodo>) sucesor_d2).iterator();
				Nodo C = (Nodo) it_d2.next();
				if (C==null){
					break;
				}
				sal = C.toString().contains("Selva");
			}
			i++;
		}
		return sal;
	}
	
	public static int horaFutura(Graph grafo,Nodo n){
		Lista<Nodo> sucesores = grafo.getSuc(n);
		ListIterator it = ((MiLista<Nodo>) sucesores).iterator();
		int hora = (n.horas()+6)%24 ;
		int i = 0;
		while (i!=sucesores.getSize()){
			Nodo A = (Nodo) it.next();
			if(sucesores.getSize()==3 && A.esCaminable()){
				
				hora = (A.horasTipo()+ hora)%24;
			}
			
			if(sucesores.getSize()==2 && A.toString().contains("d1")){
				Lista<Nodo> sucesor_d1 = grafo.getSuc(A);
				ListIterator it_d1 = ((MiLista<Nodo>) sucesor_d1).iterator();
				Nodo B = (Nodo) it_d1.next();
				if (B==null){
					break;
				}
				hora = (A.horasTipo()+ B.horasTipo() + hora)%24;

			}
			
			if(sucesores.getSize()==1 && A.toString().contains("d2")){
				Lista<Nodo> sucesor_d1 = grafo.getSuc(A);
				ListIterator it_d1 = ((MiLista<Nodo>) sucesor_d1).iterator();
				Nodo B = (Nodo) it_d1.next();
				if (B==null){
					break;
				}
				
				Lista<Nodo> sucesor_d2 = grafo.getSuc(B);
				ListIterator it_d2 = ((MiLista<Nodo>) sucesor_d2).iterator();
				Nodo C = (Nodo) it_d2.next();
				if (C==null){
					break;
				}
				hora = (A.horasTipo()+ B.horasTipo()+C.horasTipo() + hora)%24;
			}
			i++;
		}
		return hora;
	}
}

