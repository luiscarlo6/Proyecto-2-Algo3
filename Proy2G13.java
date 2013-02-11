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
			catch (FileNotFoundException e){
				System.out.println("El archivo de entrada no se encuentra");
			}
			catch(IOException e){
				System.out.println("El archivo no existe pero no puede ser creado");
				System.out.println(" o no puede ser abierto por cualquier razon.");
			}
			catch(java.lang.NullPointerException as){

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
