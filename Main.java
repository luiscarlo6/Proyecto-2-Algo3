import java.util.*;
import java.io.*;
import java.lang.Integer;

public class Main {
    //    public final int maxN = 10;

    public Main(PrintStream ps) {
	System.setOut(ps);
    }

    private boolean iguales(Graph d1, Graph d2) {
	Lista<Nodo> ns2 = d2.getNodos();
	Lista<Nodo> ns1 = d1.getNodos();
	boolean iguales = true;

	if (ns1.getSize() != ns2.getSize())
	    return false;

	for (Object o : ns1.toArray()) {
	    System.out.print(".");
	    System.out.flush();
	    if (!ns2.contains((Nodo)o)) {
		iguales = false;
		break;
	    }
	}

	if (! iguales)
	    return false;

	System.out.println("--");

	Lista<Arco> as1 = d1.getArcos();
	for (int j = 0;j!=as1.getSize();j++){
		System.out.print("*");
	}
	System.out.println("--"+ as1.getSize());

	Lista<Arco> as2 = d2.getArcos();
	for (int j = 0;j!=as2.getSize();j++){
		System.out.print("*");
	}
	System.out.println("--" + as1.getSize() +":"+ as2.getSize());

	if (as1.getSize() != as2.getSize())
	    return false;

	System.out.println("--");

	for (Object o : as1.toArray()) {
	    System.out.print(".");

	    if (!as2.contains((Arco)o)) {
		iguales = false;
		break;
	    }
	}
	System.out.println("--");


	return iguales;
    }

    private void sort(Object arr[]) {
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

    private Graph llenar(boolean lista, boolean hacerEq, int maxN) {
	Graph d1 = null;

	if (lista)
	    d1 = new DiGraphHash();
	else
	    d1 = new DiGraphMatriz();

	for (int i = 0; i < maxN; i++) {
	    Nodo n = new Nodo("Nodo_"+i);
	    d1.add(n);
	}
	
	for (int i = 0; i < maxN; i += 2) {
	    Nodo n1 = new Nodo("Nodo_"+i);

	    for (int j = 1; j < maxN; j += 2) {
		Nodo n2 = new Nodo("Nodo_"+j);
		Arco a = new Arco(n1.toString(), n2.toString());

		boolean b1 = false;
		boolean b2 = false;

		if (lista)
		    b2 = d1.add((Arco) a.clone());
		else if (hacerEq)
		    b2 = d1.add((Arco) a.clone());
		else
		    if ((i!=2) || (j != 3))
			b2 = d1.add((Arco) a.clone());
		
//				System.out.printf("Agregando %s %s %s\n" , a, b1, b2);
	    }
	}

	return d1;
    }

    public void prueba1(boolean hacerEq, int n) {
//    	m.prueba1(true, numNodos);
//	    m.prueba1(false, numNodos);
	Graph d1 = llenar(false, hacerEq, n);
	Graph d2 = llenar(false, hacerEq, n);

	System.out.println("Prueba1");

	System.out.printf("Iguales: %s %s\n", iguales(d1,d2), iguales(d2,d1));

    }

    public void prueba2(boolean hacerEq, int n) {
//	    m.prueba2(true, numNodos);
//	    m.prueba2(false, numNodos);

 	System.out.println("Prueba2");
	Graph d1 = llenar(true, hacerEq, n);
	Graph d2 = llenar(false, hacerEq, n);
	Object[] nodos1 = d1.getNodos().toArray();
	Object[] nodos2 = d2.getNodos().toArray();

	sort(nodos1);
	sort(nodos2);

	for (Object o1 : nodos1) {
	    Object arcos1[] = d1.getIn((Nodo)o1).toArray();
	    sort(arcos1);
	
	    for (Object o2 : arcos1) {
		System.out.println(o1 + ":" + o2);
	    }
	}
	System.out.println();
	for (Object o1 : nodos2) {
	    Object arcos2[] = d2.getIn((Nodo)o1).toArray();
	    sort(arcos2);

	    for (Object o2 : arcos2) {
		System.out.println(o1 + ":" + o2);
	    }
	}

    }

    public void prueba3(boolean hacerEq, int n) {
 	System.out.println("Prueba3");
	Graph d1 = llenar(true, hacerEq, n);
	Graph d2 = llenar(false, hacerEq, n);
	Object[] nodos1 = d1.getNodos().toArray();
	Object[] nodos2 = d2.getNodos().toArray();

	sort(nodos1);
	sort(nodos2);

	for (Object o1 : nodos1) {
	    Object arcos1[] = d1.getOut((Nodo)o1).toArray();
	    sort(arcos1);
	
	    for (Object o2 : arcos1) {
		System.out.println(o1 + ":" + o2);
	    }
	}
	System.out.println();
	for (Object o1 : nodos2) {
	    Object arcos2[] = d2.getOut((Nodo)o1).toArray();
	    sort(arcos2);

	    for (Object o2 : arcos2) {
		System.out.println(o1 + ":" + o2);
	    }
	}

    }

    public void prueba4(boolean hacerEq, int n) {
 	System.out.println("Prueba4");
	Graph d1 = llenar(true, hacerEq, n);
	Graph d2 = llenar(false, hacerEq, n);
	Object[] nodos1 = d1.getNodos().toArray();
	Object[] nodos2 = d2.getNodos().toArray();

	sort(nodos1);
	sort(nodos2);

	for (Object o1 : nodos1) {
	    Object masnodos1[] = d1.getPred((Nodo)o1).toArray();
	    System.out.println("Nodos: " + o1 + " Predes "+masnodos1.length);
	    sort(masnodos1);
	
	    for (Object o2 : masnodos1) {
		System.out.println("  "+o1 + ":" + o2);
	    }
	}
	System.out.println();
	for (Object o1 : nodos2) {
	    Object masnodos2[] = d2.getPred((Nodo)o1).toArray();
	    System.out.println("Nodos: " + o1 + " Predes "+masnodos2.length);
	    sort(masnodos2);

	    for (Object o2 : masnodos2) {
		System.out.println("  "+o1 + ":" + o2);
	    }
	}


    }

    public void prueba5(boolean hacerEq, int n) {
	System.out.println("Prueba5");
	Graph d1 = llenar(true, hacerEq, n);
	Graph d2 = llenar(false, hacerEq, n);
	Object[] nodos1 = d1.getNodos().toArray();
	Object[] nodos2 = d2.getNodos().toArray();

	sort(nodos1);
	sort(nodos2);

	for (Object o1 : nodos1) {
	    Object masnodos1[] = d1.getSuc((Nodo)o1).toArray();
	    System.out.println("Nodos: " + o1 + " Suces "+masnodos1.length);
	    sort(masnodos1);
	
	    for (Object o2 : masnodos1) {
		System.out.println("  "+o1 + ":" + o2);
	    }
	}
	System.out.println();
	for (Object o1 : nodos2) {
	    Object masnodos2[] = d2.getSuc((Nodo)o1).toArray();
	    System.out.println("Nodos: " + o1 + " Suces "+masnodos2.length);
	    sort(masnodos2);

	    for (Object o2 : masnodos2) {
		System.out.println("  "+o1 + ":" + o2);
	    }
	}
    }

    public static void main(String args[]) {
	long tiempoInicio = System.currentTimeMillis();
	int caso = 0;
	int numNodos = 10;

	Main m = null;

	if (args.length == 2) {
	    try{
		BufferedReader in = new BufferedReader(new FileReader(args[0]));
		Scanner inScan = new Scanner(in);
		caso = inScan.nextInt();
		numNodos = inScan.nextInt();

		PrintStream out = new PrintStream(args[1]);

		m = new Main(out);

	    } catch(FileNotFoundException e){
		System.out.println(e.getMessage());
	    }
	}

	System.out.printf("Caso %d NumNodos %d\n", caso, numNodos);

	switch(caso) {
	    // igualdad de grafo
	case 1:
	    m.prueba1(true, numNodos);
	    m.prueba1(false, numNodos);
	    break;

	case 2: // inArcs
	    m.prueba2(true, numNodos);
	    m.prueba2(false, numNodos);
	    break;

	case 3: // outAcrs
	    m.prueba3(true, numNodos);
	    m.prueba3(false, numNodos);
	    break;

	case 4: // preds
	    m.prueba4(true, numNodos);
	    m.prueba4(false, numNodos);
	    break;

	case 5: // sucs
	    m.prueba5(true, numNodos);
	    m.prueba5(false, numNodos);
	    break;
	}
	long totalTiempo = System.currentTimeMillis() - tiempoInicio;
//	System.out.println("El tiempo de demora es :" + totalTiempo + " miliseg");
    }

    public void quick_srt(Object array[],int low, int n){
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

}