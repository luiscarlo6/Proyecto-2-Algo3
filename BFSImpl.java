/**
 * Clase que implementa las operaciones sobre el grafo
 * Luiscarlo Rivera, 09-11020
 * Jose Prado, 09-11006
 * 
 * Proyecto 2
 */
public class BFSImpl {

	/**
	 * Metodo que dado un nodo, si este pertenece aplica una version
	 * modificada de el algoritmo BFS para hallar el tiempo de alcanzabilidad
	 * segun el problema propuesto
	 */
	public void BFS(Graph grafo, Nodo s){
		Cola<Nodo> cola = new Cola<Nodo>();
		//Obtengo el nodo del grafo
		s = grafo.get(s);
		//inicializo las condiciones iniciales del BFS
		s.cambiarHoras(0);
		s.setVisitado(true);
		int horasCaminadas = 0;
		cola.encolar(s);

		while (!cola.esVacia()){
			Nodo n = cola.primero();
			cola.desencolar();
			//Calculo de la hora segun el camino proximo mas corto
			int hora = horaFutura(grafo,n);
			//bolleano que identifica si sera de noche en el proximo nodo
			boolean nocheFutura = ((hora>=18 &&hora<=23)|| (hora<6 && hora>=0));
			int h = 0;
			//ciclo de condiciones para dormir, si no puede dormir el ciclo
			//hara mas de tres iteraciones y gerenerara condiciones de 
			//parada para el resto del algoritmo y la cola
			while (n.esCaminable()&&((horasCaminadas == 16) && n.esLlanura())||
					(sigSelva(grafo,n) && nocheFutura)){

				//duerme si no sera de noche o si se encuentra en una llanura
				if (!nocheFutura || n.esLlanura()){
					boolean durmio = dormir(grafo,n);
					if (durmio){
						horasCaminadas = 0;	
					}
				}

				hora = horaFutura(grafo,n);
				nocheFutura = ((hora>=18 &&hora<=23)||(hora<6 && hora>=0));
				h++;
				//genera condiciones para detener el ciclo de la cola y
				//el resto del algoritmo
				if (h>3){
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
			while (i!=sucesores.getSize()){
				Nodo m = (Nodo)it.next();
				//Obtengo el nodo del grafo
				m = grafo.get(m);

				//si el nodo no esta visitado
				if (!m.visitado()){
					m.setVisitado(true);//Marcar visitado
					//asignar las horas de alcanzabilidad desde el Nodo s
					m.cambiarHoras(n.horas()+m.horasTipo());
					m.cambiarAnterior(n);//hago que su anterior sea Nodo s
					cola.encolar(m);//Encolar el nodo m				
				}				
				i++;
			}
			//si se encuentra en un nodo que no es para dormir
			//aumenta las horas caminadas en 1
			if (n.esCaminable()&&!n.equals(s)){
				horasCaminadas++;
			}
		}

	}

	/**
	 * Genera un grafo segun el modelo propuesto a partir de una cadena
	 * de caracteres que cumpla el formato establecido en el problema
	 */
public static Graph llenar(String entr, int NumCaso) {
		Graph grafo = new DiGraphHash();
		int Pos=-1;
		int len = entr.length();
		
		Pos=buscarS(entr); 
		if(len!=0 && len>=2 && len<=1000 && Pos!=-1){
		Nodo A,B;
		char lect[] = entr.toCharArray();
		A = new Nodo(java.lang.Character.toString(lect[Pos]));
		if(validarEntrada(java.lang.Character.toString(lect[Pos]))){
				grafo = new DiGraphHash();
				return grafo;
		  }
		grafo.add(A);
		B = A;
		for (int i=Pos+1;i!=len-1;i++){

		  
		  if(validarEntrada(java.lang.Character.toString(lect[i]))){
				grafo = new DiGraphHash();
				return grafo;
		  }
		
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
		
		if(validarEntrada(java.lang.Character.toString(lect[len-1]))){
				grafo = new DiGraphHash();
				return grafo;
		  }
		
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
		}

		if(Pos>0){
		  System.out.println("El caracter S no esta de primero en el String");
		}
		
		switch(len){
		  case 0 : Mensaje(1,NumCaso+1);
					  break;
					  
		  case 1: Mensaje(2,NumCaso+1);
					 break;
		}
	
		
		return grafo;
	}

	/**
	 * A partir de un grafo y un nodo, elimina el camino mas corto hacia el
	 * proximo nodo que sea Llanura, Selva o "D".
	 * retorna true si lo elimina y false si el grafo no cambia
	 */
	private static boolean dormir(Graph grafo, Nodo n){
		//Obtengo los sucesores del nodo
		Lista<Nodo> sucesores = grafo.getSuc(n);
		ListIterator<Nodo> it = ((MiLista<Nodo>) sucesores).iterator();
		boolean sal = false;
		int i = 0;

		//Itero sobre los sucesores
		while (i!=sucesores.getSize()){
			//Obtengo algun sucesor
			Nodo A = (Nodo) it.next();

			//Si tengo tres sucesores (Nunca tendra mas de tres, Ver informe)
			//y estoy en el sucesor que es Selva Llanura
			if(sucesores.getSize()==3 && A.esCaminable()){
				//remuevo el arco que hay el Nodo n y el sucesor Llanura o Selva
				sal = grafo.remove(new Arco (n.toString(),A.toString()));
			}

			//Si tengo dos sucesores y estoy en el sucesor para dormir "d1"
			if(sucesores.getSize()==2 && A.toString().contains("d1")){
				//Obtengo el unico sucesor de "d1", segun el modelo
				//solo puede haber un sucesor en "d1", y este tiene que ser
				//Llanura, Selva o D (Ver Informe)
				Lista<Nodo> sucesor_d1 = grafo.getSuc(A);
				ListIterator<Nodo> it_d1 = ((MiLista<Nodo>) sucesor_d1).iterator();
				Nodo B = (Nodo) it_d1.next();
				if (B==null){
					break;
				}
				//remuevo el arco que hay el Nodo n y el sucesor "d1"
				//y el sucesor "d1" y el siguiente nodo Llanura, Selva o "D"
				sal = grafo.remove(new Arco (n.toString(),A.toString())) && 
						grafo.remove(new Arco(A.toString(),B.toString()));
			}

			//Si tengo un sucesor, este tiene que ser el sucesir para dormir "d2
			if(sucesores.getSize()==1 && A.toString().contains("d2")){
				//Obtengo el unico sucesor de "d2", segun el modelo
				//solo puede haber un sucesor en "d2", y este tiene que ser
				//el nodo para dormir "d3"
				Lista<Nodo> sucesor_d2 = grafo.getSuc(A);
				ListIterator<Nodo> it_d2 = ((MiLista<Nodo>) sucesor_d2).iterator();
				Nodo B = (Nodo) it_d2.next();
				if (B==null){
					break;
				}

				//Obtengo el unico sucesor de "d3", segun el modelo
				//solo puede haber un sucesor en "d3", y este tiene que ser
				//Llanura, Selva o D (Ver Informe)
				Lista<Nodo> sucesor_d3 = grafo.getSuc(B);
				ListIterator<Nodo> it_d3 = ((MiLista<Nodo>) sucesor_d3).iterator();
				Nodo C = (Nodo) it_d3.next();
				if (C==null){
					break;
				}

				//remuevo el arco que hay el Nodo n y el sucesor "d2",
				//el sucesor "d1" y el sucesor "d3" , y  entre "d3" 
				//y  el siguiente nodo Llanura, Selva o "D"
				sal = grafo.remove(new Arco (n.toString(),A.toString())) && 
						grafo.remove(new Arco(A.toString(),B.toString()))&&
						grafo.remove(new Arco(B.toString(),C.toString()));
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
	private static boolean sigSelva(Graph grafo, Nodo n){
		//Obtengo los sucesores de n
		Lista<Nodo> sucesores = grafo.getSuc(n);
		ListIterator<Nodo> it = ((MiLista<Nodo>) sucesores).iterator();
		boolean sal = false;
		int i = 0;
		
		//itero sobre los sucesores
		while (i!=sucesores.getSize()){
			Nodo A = (Nodo) it.next();
			
			//si tiene tres sucesores, reviso en el sucesor que
			//no es para dormir si es selva
			if(sucesores.getSize()==3 && A.esCaminable()){
				sal = A.toString().contains("Selva");
			}
			
			//Si tiene dos sucesores, busco el sucesor para dormir "d1"
			//y verico si su sucesor (que es Selva Llanura o "D", segun el
			//modelo ) es Selva
			if(sucesores.getSize()==2 && A.toString().contains("d1")){
				Lista<Nodo> sucesor_d1 = grafo.getSuc(A);
				ListIterator<Nodo> it_d1 = ((MiLista<Nodo>) sucesor_d1).iterator();
				Nodo B = (Nodo) it_d1.next();
				if (B==null){
					break;
				}
				sal = B.toString().contains("Selva");

			}

			//Si tiene un sucesor, busco el sucesor para dormir "d2" el sucesor
			// de este que es "d3" segun el modelo (Ver informe)
			//y reviso el sucesor de de "d3" para ver si es selva
			if(sucesores.getSize()==1 && A.toString().contains("d2")){
				Lista<Nodo> sucesor_d1 = grafo.getSuc(A);
				ListIterator<Nodo> it_d1 = ((MiLista<Nodo>) sucesor_d1).iterator();
				Nodo B = (Nodo) it_d1.next();
				if (B==null){
					break;
				}

				Lista<Nodo> sucesor_d2 = grafo.getSuc(B);
				ListIterator<Nodo> it_d2 = ((MiLista<Nodo>) sucesor_d2).iterator();
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

	/**
	 * A partir de un grafo y un nodo calcula la hora en que se encontrara
	 * si recorre el camino mas corto hasta el proximo nodo Selva, Llanura o "D"
	 * retorna un entero que representa la hora en formato 24 horas
	 */
	private static int horaFutura(Graph grafo,Nodo n){
		//Obtengo los sucesores del nodo n
		Lista<Nodo> sucesores = grafo.getSuc(n);
		ListIterator<Nodo> it = ((MiLista<Nodo>) sucesores).iterator();
		//calculo la hora actual, comienza a las 6 y le sumo
		//las horas transcurridas para alcanzar a el nodo n
		int hora = (n.horas()+6)%24 ;
		//itero sobre los sucesores
		int i = 0;
		while (i!=sucesores.getSize()){
			Nodo A = (Nodo) it.next();
			//si tiene tres sucesores el camino mas corto es 
			//directamente hacia el nodo Llanura,Selva o "D"
			if(sucesores.getSize()==3 && A.esCaminable()){
				//calculo la nueva hora
				hora = (A.horasTipo()+ hora)%24;
			}

			//si tiene dos sucesores, el camino mas corto es pasar por
			//"d1", y luego hacia el nodo Llanura, Selva o "D"
			if(sucesores.getSize()==2 && A.toString().contains("d1")){
				Lista<Nodo> sucesor_d1 = grafo.getSuc(A);
				ListIterator<Nodo> it_d1 = ((MiLista<Nodo>) sucesor_d1).iterator();
				Nodo B = (Nodo) it_d1.next();
				if (B==null){
					break;
				}
				//Calculo la nueva hora
				hora = (A.horasTipo()+ B.horasTipo() + hora)%24;

			}

			//Si tiene un sucesor solo esta el camino de ir a "d2",
			//pasar por "d3" y despues hacia el nodo Llanura, Selva o "D"
			if(sucesores.getSize()==1 && A.toString().contains("d2")){
				Lista<Nodo> sucesor_d1 = grafo.getSuc(A);
				ListIterator<Nodo> it_d1 = ((MiLista<Nodo>) sucesor_d1).iterator();
				Nodo B = (Nodo) it_d1.next();
				if (B==null){
					break;
				}

				Lista<Nodo> sucesor_d2 = grafo.getSuc(B);
				ListIterator<Nodo> it_d2 = ((MiLista<Nodo>) sucesor_d2).iterator();
				Nodo C = (Nodo) it_d2.next();
				if (C==null){
					break;
				}
				//calculo la nueva hora
				hora = (A.horasTipo()+ B.horasTipo()+C.horasTipo() + hora)%24;
			}
			i++;
		}
		return hora;
	}
	
	
	
	
  private static boolean validarEntrada(String caracter){
		boolean Comper1=true, Comper2=true,Comper3=true,Comper4=true;
				Comper1 = caracter.equalsIgnoreCase("s");
				Comper2 = caracter.equalsIgnoreCase(".");
				Comper3 = caracter.equalsIgnoreCase("*");
				Comper4 = caracter.equalsIgnoreCase("d");
			return (!Comper1 && !Comper2 && !Comper3 && !Comper4);
  }
	
	
	  
  private static int buscarS (String entrada){
	 char lect[] = entrada.toCharArray();
		for(int i=0; i!= entrada.length();i++){
		  if (java.lang.Character.toString(lect[i]).equalsIgnoreCase("s")){
				  return i;
			 }
		}
		return -1;
  }
	
	private static void Mensaje(int Tipo, int Nso){
		switch(Tipo){
		  case 1: System.out.println(String.format("La linea esta vacia, caso %d etiquetado con -1",Nso ));
					 break;
			
		  case 2: System.out.println(String.format("El string no respeta el formato, caso %d etiquetado con -1",Nso ));
					 break;
		}
	
		
	}
	
	
}

