
/**
 * Clase que implementa las operaciones sobre el grafo
 * Luiscarlo Rivera, 09-11020
 * Jose Prado, 09-11006
 * 
 * Proyecto 2
 */
public class BFSImpl {


	public static void main(String[] args) {
		BFSImpl bfs = new BFSImpl();
//		Graph G = bfs.llenar("S****...*.*.*.**.*****.............*****.*.*.*.*.***.***...........***.***.**.****........***.**.*.***.**.***...........D");
		Graph G = bfs.llenar("S...............**D");
		//		System.out.println(G.toString());
		//		Object sucs[] = G.getSuc(new Nodo("S")).toArray();

		//		Lista<Nodo> nodos = G.getNodos();
		//		ListIterator<Nodo> itN = ((MiLista<Nodo>) nodos).iterator();
		////		Nodo ant = new Nodo("S");
		//		for (int i =1;i!=nodos.getSize();i++){
		//			Nodo a = itN.next();
		//			System.out.println(a.toString() +" "+a.esSelva());
		//		}


		//		Lista<Nodo> sucesores = G.getNodos();
		//		ListIterator<Nodo> it = ((MiLista<Nodo>) sucesores).iterator();
		//		Nodo ant = new Nodo("S");
		//		for (int i =1;i!=8;i++){
		////			System.out.print(ant+" "+i+" ");
		//			int sig = horaFutura(G, ant,i);
		////			System.out.println(sig);
		//		}


		bfs.BFS(G, new Nodo("S"));
		Nodo d = new Nodo("D");
		d = G.get(d);
		System.out.println(d.horas());
	}
	/**
	 * Metodo que dado un nodo, si este pertenece aplica una version
	 * modificada de el algoritmo BFS para hallar el tiempo de alcanzabilidad
	 * segun el problema propuesto
	 */
	public void BFS(Graph grafo, Nodo s) {
		Cola<Nodo> cola = new Cola<Nodo>();
		//Obtengo el nodo del grafo
		s = grafo.get(s);
		//inicializo las condiciones iniciales del BFS
		s.cambiarHoras(0);
		s.setVisitado(true);
		int horasCaminadas = 0;
		cola.encolar(s);

		while (!cola.esVacia()) {
			Nodo n = cola.primero();
			cola.desencolar();
			//Calculo de la hora segun el camino proximo mas corto
			Nodo sig = sig(grafo,n);
			boolean siguienteSelva = sig!=null && sig.esSelva(); 
			int horaActual = (n.horas() + 6) % 24;
			int cotaSelva = Math.abs(horaActual-18);				
			int hora = horaFutura(grafo, n, cotaSelva);
			//bolleano que identifica si sera de noche en el proximo nodo
			boolean nocheFutura = ((hora >= 18 && hora <= 23) || (hora < 6 && hora >= 0));
			boolean noche =((horaActual >= 18 && horaActual <= 23) || (horaActual < 6 && horaActual >= 0));
			int h = 0;
			//ciclo de condiciones para dormir, si no puede dormir el ciclo
			//hara mas de tres iteraciones y gerenerara condiciones de 
			//parada para el resto del algoritmo y la cola
			while (n.esCaminable() && (((horasCaminadas == 16) && n.esLlanura())
					|| (sigSelva(grafo, n,cotaSelva) && nocheFutura)||(siguienteSelva && noche))) {

				//duerme si no sera de noche o si se encuentra en una llanura
				if (!nocheFutura || n.esLlanura()) {
					boolean durmio = dormir(grafo, n);
					if (durmio) {
						horasCaminadas = 0;
						horaActual=(horaActual+8)%24;
					}
				}

				hora = horaFutura(grafo, n,cotaSelva);
				nocheFutura = ((hora >= 18 && hora <= 23) || (hora < 6 && hora >= 0));
				noche =((horaActual >= 18 && horaActual <= 23) || (horaActual < 6 && horaActual >= 0));
				h++;
				//genera condiciones para detener el ciclo de la cola y
				//el resto del algoritmo
				if (h > 3) {
					cola.clear();
					n = n.getAnt();
					break;
				}
			}

			//Obtengo los sucesores del nodo desencolado
			Lista<Nodo> sucesores = grafo.getSuc(n);
			ListIterator<Nodo> it = ((MiLista<Nodo>) sucesores).iterator();

			//itero sobre la lista de sucesores
			int i = 0;
			while (i != sucesores.getSize()) {
				Nodo m = it.next();
				//Obtengo el nodo del grafo
				m = grafo.get(m);

				//si el nodo no esta visitado
				if (!m.visitado()) {
					m.setVisitado(true);//Marcar visitado
					//asignar las horas de alcanzabilidad desde el Nodo s
					m.cambiarHoras(n.horas() + m.horasTipo());
					m.cambiarAnterior(n);//hago que su anterior sea Nodo s
					cola.encolar(m);//Encolar el nodo m				
				}
				i++;
			}
			//si se encuentra en un nodo que no es para dormir
			//aumenta las horas caminadas en 1
			if (n!=null && n.esCaminable()) {
				horasCaminadas++;
			}
		}
	}

	/**
	 * Genera un grafo segun el modelo propuesto a partir de una cadena
	 * de caracteres que cumpla el formato establecido en el problema
	 */
	public Graph llenar(String entr) {
		Graph grafo = new DiGraphHash();

		int len = entr.length();//tamaño del String
		Nodo A, B;
		char lect[] = entr.toCharArray();//convierto el string en arreglo
		//crear Nodo del primer elemento, deberia ser una "S"
		A = new Nodo(java.lang.Character.toString(lect[0]));
		//si no es S, el archivo no cumple el formato del enunciado
		if (!A.toString().equalsIgnoreCase("S")) {
			return grafo;
		}
		grafo.add(A);
		B = A;
		for (int i = 1; i != len - 1; i++) {
			String tipo = "";
			String caracter = java.lang.Character.toString(lect[i]);
			//si en el resto del String hay un caracter que no es "*" o "."
			//el archivo no cumple con el formato del enunciado
			if (!(caracter.equalsIgnoreCase("*")
					|| caracter.equalsIgnoreCase("."))) {
				return new DiGraphHash();
			}

			//asigno los atributos a los nodos, Llanura si leo "."
			//y Selva si leo "*"
			if (caracter.equalsIgnoreCase(".")) {
				tipo = "Llanura";
			}
			if (caracter.equalsIgnoreCase("*")) {
				tipo = "Selva";
			}

			//creo los nodos segun el tipo y los enumero segun su pos
			A = new Nodo(tipo + i);
			//Agrego el nodo y creo los tres para dormir (Ver informe)
			grafo.add(A);
			Nodo A1, A2, A3;
			A1 = new Nodo(tipo + i + "_d1");
			A2 = new Nodo(tipo + i + "_d2");
			A3 = new Nodo(tipo + i + "_d3");

			grafo.add(A1);
			grafo.add(A2);
			grafo.add(A3);

			//Genero y agrego todos los arcos correspondientes (Ver informe)

			Arco arco;
			arco = new Arco(B.toString(), A.toString());
			grafo.add(arco);

			arco = new Arco(B.toString(), A1.toString());
			grafo.add(arco);

			arco = new Arco(B.toString(), A2.toString());
			grafo.add(arco);

			arco = new Arco(A2.toString(), A3.toString());
			grafo.add(arco);

			arco = new Arco(A1.toString(), A.toString());
			grafo.add(arco);

			arco = new Arco(A3.toString(), A.toString());
			grafo.add(arco);

			B = A;
		}

		//finalmente genero el Nodo "D", que deberia ser el ultimo 
		//caracter de la entrada, si el ultimo no es "D"
		//el archivo no cumple con el formato del enunciado

		A = new Nodo(java.lang.Character.toString(lect[len - 1]));
		if (!A.toString().equalsIgnoreCase("D")) {
			return new DiGraphHash();
		}

		grafo.add(A);
		Nodo A1, A2, A3;
		A1 = new Nodo(java.lang.Character.toString(lect[len - 1]) + "_d1");
		A2 = new Nodo(java.lang.Character.toString(lect[len - 1]) + "_d2");
		A3 = new Nodo(java.lang.Character.toString(lect[len - 1]) + "_d3");

		grafo.add(A1);
		grafo.add(A2);
		grafo.add(A3);

		//Genero y agrego todos los arcos correspondientes (Ver informe)
		Arco arco;
		arco = new Arco(B.toString(), A.toString());
		grafo.add(arco);

		arco = new Arco(B.toString(), A1.toString());
		grafo.add(arco);

		arco = new Arco(B.toString(), A2.toString());
		grafo.add(arco);

		arco = new Arco(A2.toString(), A3.toString());
		grafo.add(arco);

		arco = new Arco(A1.toString(), A.toString());
		grafo.add(arco);

		arco = new Arco(A3.toString(), A.toString());
		grafo.add(arco);

		B = A;

		return grafo;
	}

	/**
	 * A partir de un grafo y un nodo, elimina el camino mas corto hacia el
	 * proximo nodo que sea Llanura, Selva o "D".
	 * retorna true si lo elimina y false si el grafo no cambia
	 */
	private static boolean dormir(Graph grafo, Nodo n) {
		//Obtengo los sucesores del nodo
		Lista<Nodo> sucesores = grafo.getSuc(n);
		ListIterator<Nodo> it = ((MiLista<Nodo>) sucesores).iterator();
		boolean sal = false;
		int i = 0;

		//Itero sobre los sucesores
		while (i != sucesores.getSize()) {
			//Obtengo algun sucesor
			Nodo A = it.next();

			//Si tengo tres sucesores (Nunca tendra mas de tres, Ver informe)
			//y estoy en el sucesor que es Selva Llanura
			if (sucesores.getSize() == 3 && A.esCaminable()) {
				//remuevo el arco que hay el Nodo n y el sucesor Llanura o Selva
				sal = grafo.remove(new Arco(n.toString(), A.toString()));
			}

			//Si tengo dos sucesores y estoy en el sucesor para dormir "d1"
			if (sucesores.getSize() == 2 && A.toString().contains("d1")) {
				//Obtengo el unico sucesor de "d1", segun el modelo
				//solo puede haber un sucesor en "d1", y este tiene que ser
				//Llanura, Selva o D (Ver Informe)
				Lista<Nodo> sucesor_d1 = grafo.getSuc(A);
				ListIterator<Nodo> it_d1 = ((MiLista<Nodo>) sucesor_d1).iterator();
				Nodo B = it_d1.next();
				if (B == null) {
					break;
				}
				//remuevo el arco que hay el Nodo n y el sucesor "d1"
				//y el sucesor "d1" y el siguiente nodo Llanura, Selva o "D"
				sal = grafo.remove(new Arco(n.toString(), A.toString()))
						&& grafo.remove(new Arco(A.toString(), B.toString()));
			}

			//Si tengo un sucesor, este tiene que ser el sucesir para dormir "d2
			if (sucesores.getSize() == 1 && A.toString().contains("d2")) {
				//Obtengo el unico sucesor de "d2", segun el modelo
				//solo puede haber un sucesor en "d2", y este tiene que ser
				//el nodo para dormir "d3"
				Lista<Nodo> sucesor_d2 = grafo.getSuc(A);
				ListIterator<Nodo> it_d2 = ((MiLista<Nodo>) sucesor_d2).iterator();
				Nodo B = it_d2.next();
				if (B == null) {
					break;
				}

				//Obtengo el unico sucesor de "d3", segun el modelo
				//solo puede haber un sucesor en "d3", y este tiene que ser
				//Llanura, Selva o D (Ver Informe)
				Lista<Nodo> sucesor_d3 = grafo.getSuc(B);
				ListIterator<Nodo> it_d3 = ((MiLista<Nodo>) sucesor_d3).iterator();
				Nodo C = it_d3.next();
				if (C == null) {
					break;
				}

				//remuevo el arco que hay el Nodo n y el sucesor "d2",
				//el sucesor "d1" y el sucesor "d3" , y  entre "d3" 
				//y  el siguiente nodo Llanura, Selva o "D"
				sal = grafo.remove(new Arco(n.toString(), A.toString()))
						&& grafo.remove(new Arco(A.toString(), B.toString()))
						&& grafo.remove(new Arco(B.toString(), C.toString()));
			}
			i++;
		}
		//Si se removio algo del grafo retorna true
		return sal;
	}

	/**
	 * Metodo que a partir de un grafo y un nodo
	 * indica si el siguiente nodo que sea Selva, Llanura o D
	 * es una Selva 
	 * retorna true si es Selva, false en otro caso
	 */
	private static boolean sigSelva(Graph grafo, Nodo n, int NumerodeSelvas) {
		if (n.equals(new Nodo("D"))){
			return false;
		}
		
		boolean sal = false;
		Nodo siguiente = sig(grafo,n);
		if (siguiente==null){
			return false;
		}
		int i =0;
		while(i!=NumerodeSelvas){			
			if (siguiente.esLlanura()||siguiente.equals(new Nodo("D"))){
				return false;
			}
			sal = siguiente.esSelva();
			Nodo ant = siguiente;
			siguiente = sig(grafo,ant);
			i++;
		}
		return sal;
	}

	/**
	 * A partir de un grafo y un nodo calcula la hora en que se encontrara
	 * si recorre el camino mas corto hasta el proximo nodo Selva, Llanura o "D"
	 * retorna un entero que representa la hora en formato 24 horas
	 */
	private static int horaFutura(Graph grafo, Nodo n, int NumerodeSelvas) {
		Lista<Nodo> sucesores = grafo.getSuc(n);
		int hora = (n.horas() + 6) % 24;

		Nodo siguiente = n;
		//itero sobre los sucesores
		for (int u =0; u!=NumerodeSelvas;u++){
			if (sucesores.getSize() == 3) {
				//calculo la nueva hora
				hora = (siguiente.horasTipo() + hora) % 24;
			}

			if (sucesores.getSize() == 2) {
				//calculo la nueva hora
				hora = (siguiente.horasTipo() + hora+8) % 24;
			}

			if (sucesores.getSize() == 1) {
				//calculo la nueva hora
				hora = (siguiente.horasTipo() + hora+16) % 24;
			}
			siguiente = sig(grafo,siguiente);
			sucesores = grafo.getSuc(siguiente);

		}
		return hora;
	}

	private static Nodo sig(Graph grafo,Nodo n){
		Nodo siguiente = null;
		//Obtengo los sucesores del nodo n
		Lista<Nodo> sucesores = grafo.getSuc(n);
		ListIterator<Nodo> it = ((MiLista<Nodo>) sucesores).iterator();
		int i = 0;
		while (i != sucesores.getSize()) {
			Nodo A = it.next();
			//si tiene tres sucesores el camino mas corto es 
			//directamente hacia el nodo Llanura,Selva o "D"
			if (sucesores.getSize() == 3 && A.esCaminable()) {
				//calculo la nueva hora
				siguiente = A;
			}

			//si tiene dos sucesores, el camino mas corto es pasar por
			//"d1", y luego hacia el nodo Llanura, Selva o "D"
			if (sucesores.getSize() == 2 && A.toString().contains("d1")) {
				Lista<Nodo> sucesor_d1 = grafo.getSuc(A);
				ListIterator<Nodo> it_d1 = ((MiLista<Nodo>) sucesor_d1).iterator();
				Nodo B = it_d1.next();
				if (B == null) {
					break;
				}
				//Calculo la nueva hora
				siguiente = B;
			}

			//Si tiene un sucesor solo esta el camino de ir a "d2",
			//pasar por "d3" y despues hacia el nodo Llanura, Selva o "D"
			if (sucesores.getSize() == 1 && A.toString().contains("d2")) {
				Lista<Nodo> sucesor_d1 = grafo.getSuc(A);
				ListIterator<Nodo> it_d1 = ((MiLista<Nodo>) sucesor_d1).iterator();
				Nodo B = it_d1.next();
				if (B == null) {
					break;
				}

				Lista<Nodo> sucesor_d2 = grafo.getSuc(B);
				ListIterator<Nodo> it_d2 = ((MiLista<Nodo>) sucesor_d2).iterator();
				Nodo C = it_d2.next();
				if (C == null) {
					break;
				}
				//calculo la nueva hora

				siguiente = C;
			}


			i++;
		}
		return siguiente;
	}
}
