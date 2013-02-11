import java.io.*;
import java.util.*;

public class Prueba {


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
		boolean Comper1=true, Comper2=true,Comper3=true,Comper4=true;
		  Aux = S.nextLine();
//		  System.out.println(Aux);
//		  System.out.println(Aux.length());
		  
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
				
				/*aqui has el agregar para que el programa trabaje*/
				
				G.add(new Nodo((""+Aux.charAt(i)).concat("_"+i)));
				
				
		  }//fin del for
		  return true;
  }//Fin de cargarDatos
  
  
  
  
  public static void escribirResultado(PrintWriter F, int Resultado, int NumCaso){
		F.println(String.format("Caso %d:\t%d", NumCaso,Resultado));
  }
  
  
  
  
  
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

				System.out.println(numCasos);
				i=1;
				
				if (S.hasNextLine()){
					 G= new DiGraphHash();
					 if(cargarDatos(G,S)){
						 //Aqui se ponen los calculos y se manda a imprimir cambiando el -1
						 escribirResultado(pw,-1,i);
						
						 
					 }else{
						 //si por alguna razon los datos no son validos imprime -1 de una
						 escribirResultado(pw,-1,i);
					 }
				  }
				System.out.println(G.toString());
				
				i=2;
				while(i <= numCasos && numCasos>=0){
					G= new DiGraphHash();
				  if (S.hasNextLine()){
					 
					 if(cargarDatos(G,S)){
						 //Aqui se ponen los calculos y se manda a imprimir
						 escribirResultado(pw,-1,i);
						
						 
					 }else{
						 //si por alguna razon los datos no son validos imprime -1 de una
						 escribirResultado(pw,-10,i);
					 }
					 
				  }
				  System.out.println(G.toString());
				  i++;
				}

		  //Cerramos el archivo
				archivoOut.close();
		  
		} catch (FileNotFoundException e){
		  System.out.println("El archivo de entrada no se encuentra");
		}catch(IOException e){
		  System.out.println("El archivo no existe pero no puede ser creado");
		  System.out.println(" o no puede ser abierto por cualquier razon.");
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

}
