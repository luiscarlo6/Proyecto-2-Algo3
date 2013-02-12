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


				numCasos=buscarEntero(S);//se obtiene 


				//se chequea que t sea valido
				if (numCasos!=-1 && numCasos<=100 && numCasos>=0){
					i = 0;
					//se itera el numero T sin importar que hallan mas lineas
					while (i!=numCasos){
						if (S.hasNextLine()){
							String lectura = S.nextLine();
							if (lectura.length()<2 || lectura.length()>1000){
								escribirResultado(pw,-1,i);
								i++;
								continue;
							}
							G=bfs.llenar(lectura);
							if(!(G.getNumNodos()<2)){
								s = new Nodo("S");
								bfs.BFS(G,s); //se realiza bfs sobre el caso
								D = G.get(new Nodo("D"));
								if (D!=null){
									escribirResultado(pw,D.horas(),i);
								}
								else{
									escribirResultado(pw,-1,i);
								}
							}
							else{
								escribirResultado(pw,-1,i);
							}
						}
						else{
							escribirResultado(pw,-1,i);
						}
						i++;
					}
					//Cerramos el archivo
					archivoOut.close();

				}
				else{
					System.out.println("El archivo tiene el formato incorrecto");
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



	/**
	 *	Funcion que optiene el entero del archivo
	 *	@return el entero o -1 si el archivo no tiene dicho entero
	 */

	private static int buscarEntero(Scanner s){
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

	/**
	 *	Realiza la escritura sobre el archivo F
	 */

	private static void escribirResultado(PrintWriter F, int Resultado,
			int NumCaso){
		F.println(String.format("Caso %d:\t%d", NumCaso+1,Resultado));
	}
}
