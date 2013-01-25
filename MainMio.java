
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Nodo A;
		Arco B;
		int max = 100;
		long tiempoInicio = System.currentTimeMillis();
		Graph grafo = llenar(true,true,max);
		long totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out.println("El tiempo de demora para llenar es :" + totalTiempo + " miliseg");

//		for (int i = 0; i < max; i++) {
//		    Nodo n = new Nodo("Nodo_"+i);
////		    grafo.remove(n);
//		    System.out.println("contains nodo:"+n.toString()+" "+grafo.contains(n));
//		}
		
//		for (int i = 0; i < max; i += 2) {
//		    Nodo n1 = new Nodo("Nodo_"+i);
//
//		    for (int j = 1; j < max; j += 2) {
//
//		    Nodo n2 = new Nodo("Nodo_"+j);
//			Arco a = new Arco(n1.toString(), n2.toString());
//			grafo.remove(a);
//			System.out.println("contains arco:"+a.toString()+" "+grafo.contains(a));
//		    }
//		}
		
		tiempoInicio = System.currentTimeMillis();
		Lista<Nodo> listaNodo = grafo.getNodos();
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out.println("El tiempo de demora para getNodos es :" + totalTiempo + " miliseg");
//		ListIterator<Nodo> it = ((MiLista<Nodo>) listaNodo).iterator();
//		for (int i = 0; i < max; i++){
//			A = it.next();
//			System.out.println(A.toString());
//		}
		
		tiempoInicio = System.currentTimeMillis();
		Lista<Arco> listaArco = grafo.getArcos();
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out.println("El tiempo de demora para getArcos es :" + totalTiempo + " miliseg");
//		ListIterator<Arco> it2 = ((MiLista<Arco>) listaArco).iterator();
//		for (int i = 0; i < listaArco.getSize(); i++){
//			B = it2.next();
//			System.out.println(B.toString());
//		}
		
		tiempoInicio = System.currentTimeMillis();
		Lista<Nodo> listaPred = grafo.getPred(new Nodo("Nodo_1"));
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out.println("El tiempo de demora para getPred es :" + totalTiempo + " miliseg");
//		ListIterator<Nodo> it3 = ((MiLista<Nodo>) listaPred).iterator();
//		for (int i = 0; i < listaPred.getSize(); i++){
//			A = it3.next();
//			System.out.println(A.toString());
//		}
		
		tiempoInicio = System.currentTimeMillis();
		Lista<Nodo> listaSuc = grafo.getSuc(new Nodo("Nodo_0"));
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out.println("El tiempo de demora para getSuc es :" + totalTiempo + " miliseg");
//		ListIterator<Nodo> it4 = ((MiLista<Nodo>) listaSuc).iterator();
//		for (int i = 0; i < listaSuc.getSize(); i++){
//			A = it4.next();
//			System.out.println(A.toString());
//		}
		
		tiempoInicio = System.currentTimeMillis();
		Lista<Arco> listaIn = grafo.getIn(new Nodo("Nodo_1"));
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out.println("El tiempo de demora para getIn es :" + totalTiempo + " miliseg");
//		ListIterator<Arco> it5 = ((MiLista<Arco>) listaIn).iterator();
//		for (int i = 0; i < listaIn.getSize(); i++){
//			B = it5.next();
//			System.out.println(B.toString());
//		}
		
		tiempoInicio = System.currentTimeMillis();
		Lista<Arco> listaOut = grafo.getOut(new Nodo("Nodo_0"));
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
//		System.out.println("El tiempo de demora para getOut es :" + totalTiempo + " miliseg");
//		ListIterator<Arco> it6 = ((MiLista<Arco>) listaOut).iterator();
//		for (int i = 0; i < listaOut.getSize(); i++){
//			B = it6.next();
//			System.out.println(B.toString());
//		}
		
		
		tiempoInicio = System.currentTimeMillis();
		String grafoS = grafo.toString();
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out.println("El tiempo de demora para toString es :" + totalTiempo + " miliseg");
//		System.out.println(grafo.toString());
		
		
		tiempoInicio = System.currentTimeMillis();
		Graph nuevoG = (Graph) grafo.clone();
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out.println("El tiempo de demora para clone es :" + totalTiempo + " miliseg");

		
		System.out.println("Arcos: "+grafo.getNumArcos()+" Nodos: "+grafo.getNumNodos()+ " Colisiones: "+grafo.colisiones()+ " "+ (grafo.colisiones()*1.0 / grafo.getNumNodos())*100+"%");
//		long totalTiempo = System.currentTimeMillis() - tiempoInicio;
//		System.out.println("El tiempo de demora es :" + totalTiempo + " miliseg");
		
		
		
		
		
		
		
		
		
		
		
		
		tiempoInicio = System.currentTimeMillis();
		grafo = llenar(false,true,max);
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out.println("El tiempo de demora para llenar es :" + totalTiempo + " miliseg");

//		for (int i = 0; i < max; i++) {
//		    Nodo n = new Nodo("Nodo_"+i);
////		    grafo.remove(n);
//		    System.out.println("contains nodo:"+n.toString()+" "+grafo.contains(n));
//		}
		
//		for (int i = 0; i < max; i += 2) {
//		    Nodo n1 = new Nodo("Nodo_"+i);
//
//		    for (int j = 1; j < max; j += 2) {
//
//		    Nodo n2 = new Nodo("Nodo_"+j);
//			Arco a = new Arco(n1.toString(), n2.toString());
//			grafo.remove(a);
//			System.out.println("contains arco:"+a.toString()+" "+grafo.contains(a));
//		    }
//		}
		
		tiempoInicio = System.currentTimeMillis();
		listaNodo = grafo.getNodos();
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out.println("El tiempo de demora para getNodos es :" + totalTiempo + " miliseg");
//		ListIterator<Nodo> it = ((MiLista<Nodo>) listaNodo).iterator();
//		for (int i = 0; i < max; i++){
//			A = it.next();
//			System.out.println(A.toString());
//		}
		
		tiempoInicio = System.currentTimeMillis();
		listaArco = grafo.getArcos();
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out.println("El tiempo de demora para getArcos es :" + totalTiempo + " miliseg");
//		ListIterator<Arco> it2 = ((MiLista<Arco>) listaArco).iterator();
//		for (int i = 0; i < listaArco.getSize(); i++){
//			B = it2.next();
//			System.out.println(B.toString());
//		}
		
		tiempoInicio = System.currentTimeMillis();
		listaPred = grafo.getPred(new Nodo("Nodo_1"));
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out.println("El tiempo de demora para getPred es :" + totalTiempo + " miliseg");
//		ListIterator<Nodo> it3 = ((MiLista<Nodo>) listaPred).iterator();
//		for (int i = 0; i < listaPred.getSize(); i++){
//			A = it3.next();
//			System.out.println(A.toString());
//		}
		
		tiempoInicio = System.currentTimeMillis();
		listaSuc = grafo.getSuc(new Nodo("Nodo_0"));
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out.println("El tiempo de demora para getSuc es :" + totalTiempo + " miliseg");
//		ListIterator<Nodo> it4 = ((MiLista<Nodo>) listaSuc).iterator();
//		for (int i = 0; i < listaSuc.getSize(); i++){
//			A = it4.next();
//			System.out.println(A.toString());
//		}
		
		tiempoInicio = System.currentTimeMillis();
		listaIn = grafo.getIn(new Nodo("Nodo_1"));
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out.println("El tiempo de demora para getIn es :" + totalTiempo + " miliseg");
//		ListIterator<Arco> it5 = ((MiLista<Arco>) listaIn).iterator();
//		for (int i = 0; i < listaIn.getSize(); i++){
//			B = it5.next();
//			System.out.println(B.toString());
//		}
		
		tiempoInicio = System.currentTimeMillis();
		listaOut = grafo.getOut(new Nodo("Nodo_0"));
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
//		System.out.println("El tiempo de demora para getOut es :" + totalTiempo + " miliseg");
//		ListIterator<Arco> it6 = ((MiLista<Arco>) listaOut).iterator();
//		for (int i = 0; i < listaOut.getSize(); i++){
//			B = it6.next();
//			System.out.println(B.toString());
//		}
		
		
		tiempoInicio = System.currentTimeMillis();
		grafoS = grafo.toString();
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out.println("El tiempo de demora para toString es :" + totalTiempo + " miliseg");
//		System.out.println(grafo.toString());
		
		
		tiempoInicio = System.currentTimeMillis();
		nuevoG = (Graph) grafo.clone();
		totalTiempo = System.currentTimeMillis() - tiempoInicio;
		System.out.println("El tiempo de demora para clone es :" + totalTiempo + " miliseg");

		
		System.out.println("Arcos: "+grafo.getNumArcos()+" Nodos: "+grafo.getNumNodos()+ " Colisiones: "+grafo.colisiones()+ " "+ (grafo.colisiones()*1.0 / grafo.getNumNodos())*100+"%");
//		long totalTiempo = System.currentTimeMillis() - tiempoInicio;
//		System.out.println("El tiempo de demora es :" + totalTiempo + " miliseg");
	}
	
	
	private static Graph llenar(boolean lista, boolean hacerEq, int maxN) {
		Graph d1 = null;

		if (lista)
		    d1 = new DiGraphHash();
		else
		    d1 = new DiGraphMatriz();

		for (int i = 0; i < maxN; i++) {
		    Nodo n = new Nodo("Nodo_"+i);
		    d1.add(n);
//		    System.out.println("add "+n.toString()+" "+d1.add(n));
		    
		}
		int p=0;
		for (int i = 0; i < maxN; i += 2) {
		    Nodo n1 = new Nodo("Nodo_"+i);

		    for (int j = 1; j < maxN; j += 2) {
			Nodo n2 = new Nodo("Nodo_"+j);
			Arco a = new Arco(n1.toString(), n2.toString());
			p++;
			boolean b1 = false;
			boolean b2 = false;

			if (lista)
			    b2 = d1.add((Arco) a.clone());
			else if (hacerEq)
			    b2 = d1.add((Arco) a.clone());
			else
			    if ((i!=2) || (j != 3))
				b2 = d1.add((Arco) a.clone());
			
//					System.out.printf("Agregando %s %s\n" , a, b2);
		    }
		}
		System.out.println(p);
		return d1;
	    }

}

