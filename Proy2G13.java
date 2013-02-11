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
		BFSImpl bfs = new BFSImpl();
		if (args.length == 2) {
			try{

				archivoIn= new File(args[0]);
				S = new Scanner(archivoIn);

				archivoOut = new FileWriter(args[1]);
				//Nuevo PrintWriter
				pw= new PrintWriter(archivoOut);
				//Escribimos en el Archivo lo deseado
<<<<<<< HEAD
=======
				
				
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




>>>>>>> 2a6df7a927ff0be7513bad2b1ab50d038dcd598c


				numCasos=buscarEntero(S);

				if (numCasos!=-1 && numCasos<=100){
					i = 0;
					while (i!=numCasos){
						if (S.hasNextLine()){
							String lectura = S.nextLine();
							if (lectura.length()<2 || lectura.length()>1000){
								System.out.println("El archivo tiene el formato incorrecto");
								break;
							}
							G = bfs.llenar(lectura);
							if (G.getNumNodos()<2){
								System.out.println("El archivo tiene el formato incorrecto");
								break;
							}
							s = new Nodo("S");
							bfs.BFS(G,s);
							D = G.get(new Nodo("D"));
							escribirResultado(pw,D.horas(),i);
						}
						else{
							if (numCasos!=0){
								System.out.print("El archivo tiene el formato incorrecto");
							}
							break;
						}
						i++;
					}
					//Cerramos el archivo
					archivoOut.close();

				}
				else{
					System.out.print("El archivo tiene el formato incorrecto");
				}

			}
<<<<<<< HEAD
			catch (FileNotFoundException e){
				System.out.println("El archivo de entrada no se encuentra");
=======
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
>>>>>>> 2a6df7a927ff0be7513bad2b1ab50d038dcd598c
			}
			catch(IOException e){
				System.out.println("El archivo no existe pero no puede ser creado");
				System.out.println(" o no puede ser abierto por cualquier razon.");
			}
<<<<<<< HEAD
			catch(java.lang.NullPointerException as){
=======
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
>>>>>>> 2a6df7a927ff0be7513bad2b1ab50d038dcd598c

			}
			finally{
				try{  
					S.close();
				}
				catch(Exception e){
					System.out.println("Error");
				}
			}	
		}
		else{
			System.out.println("Error en la linea de argumentos");
		}	
	}


	public static int buscarEntero(Scanner s){
		int Temp=-1;

		if (!s.hasNextLine()||!s.hasNextInt()){
			return -1;
		}

		String ValorLeido = s.nextLine();
		if (ValorLeido.length() != 0){
			Scanner Aux=new Scanner(ValorLeido);
			while(Aux.hasNextInt()){
				Temp=Aux.nextInt();
			}//fin while
			Aux.close();  
		}
		return Temp;
	}

	public static void escribirResultado(PrintWriter F, int Resultado, int NumCaso){
		F.println(String.format("Caso %d:\t%d", NumCaso+1,Resultado));
	}
}
