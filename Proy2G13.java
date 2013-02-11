import java.io.*;
import java.util.*;

public class Proy2G13 {



  /**
	 * @param args
	 */
  public static void main(String[] args) {
		
		File archivoIn=null;
		FileWriter archivoOut=null;
		Graph G;
		Scanner S=null;
		int numCasos=-1,i=0;
		PrintWriter pw=null;
		Nodo s,D;
		
		if (args.length == 2) {
		  try{
				G= new DiGraphHash();
				archivoIn= new File(args[0]);
				S = new Scanner(archivoIn);
				
				archivoOut = new FileWriter(args[1]);
				//Nuevo PrintWriter
				pw= new PrintWriter(archivoOut);
				//Escribimos en el Archivo lo deseado
				
				
				  numCasos=buscarEntero(S);
				  
				  if(numCasos>=1 && numCasos<=100){
				  

				System.out.println(numCasos);
				i=1;
				
				if (S.hasNextLine()){

								G=llenar(S.nextLine());
							if(G.getNumNodos()!=0){
								s = new Nodo("S");
								BFS(G,s);
								D = G.get(new Nodo("D"));
								escribirResultado(pw,D.horas(),i);
							}else{
							escribirResultado(pw,-1,i);
							}
				i=2;
				while(i <= numCasos && numCasos>=0){

				  if (S.hasNextLine()){
						 G=llenar(S.nextLine());
						 if(G.getNumNodos()!=0){
						 s = new Nodo("S");
						 BFS(G,s);
						 D = G.get(new Nodo("D"));
						 escribirResultado(pw,D.horas(),i);
					
					}else{
							escribirResultado(pw,-1,i);
							}
				  }
				  i++;
				}
			 
		  //Cerramos el archivo

		  
		}  
		}else{
		  System.out.println("T no es valido");
		
		} 
		
			archivoOut.close();
		}catch (FileNotFoundException e){
		  System.out.println("El archivo de entrada no se encuentra");
		}catch(IOException e){
		  System.out.println("El archivo no existe pero no puede ser creado");
		  System.out.println(" o no puede ser abierto por cualquier razon.");
		}catch(java.lang.NullPointerException as){
		
		}finally{
		  try{  
				
				  S.close();
				}catch(Exception e){
				  System.out.println("Error");
				}
		}	
		  
		  
		}else{
		  System.out.println("Error en la linea de argumentos");
		}	
		
		
		// TODO Auto-generated method stub

  }






	/**
	 * @param args
	 */
/* 
	public static void main(String[] args) {
		String lectura = "S...****.................***D";
//		String lectura = "S.......D";
//		String lectura = "S***********.***********D";
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

	*/
	
  public static int buscarEntero(Scanner s){
	 //int resultado = -1;
	 int Temp=-1;
	 try{

		
		
		while(s.hasNextLine()){
				  String ValorLeido = s.nextLine();
			 		if (ValorLeido.length() == 0){
						  //ValorLeido = s.nextLine();
						  continue;
						  
			 		}else{
						Scanner Aux=new Scanner(ValorLeido);
						
						while(Aux.hasNextInt()){
						  Temp=Aux.nextInt();
						  return Temp;
						}//fin while
						//ValorLeido=s.nextLine();
			 		}
		}//fin while

		return Temp;
	 }catch(java.util.InputMismatchException e){
		  return -1;
	 }
  }
  
  
  public static boolean cargarDatos(Graph G, Scanner S){
		String Aux;
		//boolean Comper1=true, Comper2=true,Comper3=true,Comper4=true;
		  Aux = S.nextLine();
		  
		  System.out.println(Aux);
		  
		  G = llenar(Aux);
		  
		  System.out.println(G.toString());
		  
//		  System.out.println(Aux);
//		  System.out.println(Aux.length());
/*		  
		  if (Aux.length()<2 || Aux.length() >1000)
			 return false;
		  
		  
		  for(int i=0;i!=Aux.length();i++){
				Comper1 = (new String(""+Aux.charAt(i))).equalsIgnoreCase("s");
				Comper2 = (new String(""+Aux.charAt(i))).equalsIgnoreCase(".");
				Comper3 = (new String(""+Aux.charAt(i))).equalsIgnoreCase("*");
				Comper4 = (new String(""+Aux.charAt(i))).equalsIgnoreCase("d");
				
				if (!Comper1 && !Comper2 && !Comper3 && !Comper4){
					G=new DiGraphHash();
					return false;
				}
*/				
				/*aqui has el agregar para que el programa trabaje*/
				
				//G.add(new Nodo((""+Aux.charAt(i)).concat("_"+i)));
				
			return true;	
		  }//fin del for
		  
  //}//Fin de cargarDatos
  
  
  
  
  public static void escribirResultado(PrintWriter F, int Resultado, int NumCaso){
		F.println(String.format("Caso %d:\t%d", NumCaso,Resultado));
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

		System.out.println(entr);
		int len = entr.length();
		System.out.println(len);
		if(len!=0 && len>=2 && len<=1000){
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
		}

		return grafo;
	}
	
	@SuppressWarnings("rawtypes")
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
	

	@SuppressWarnings("rawtypes")
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
	
	@SuppressWarnings("rawtypes")
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
