package ThreadJava;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;




public class AuxiliarJava extends Thread {

	
	private File[] listaArquivo;
	private String nomeArquivo;
	private static final String INICIO = "processando/";
	private static final String FINAL = "final/";
	
	
	public void run() {
		
		while(true) {
			
			if(verificarPasta()) { //chamada do metodo que verifica os arquivos nas pasta
				
			}else {
				try {
					
					System.out.println("Nenhum arquivo encontrado, aguardando..."); 
					 Thread.sleep(5000);
					 
				}catch (InterruptedException ex) {
					 ex.printStackTrace();
				}
			}
		}
		
	}
	
	
	public boolean verificarPasta() { // metodo de verificacao na pasta.
		
		File arqOri = new File (INICIO); 
		File aux;
		
		listaArquivo = arqOri.listFiles();  // cria uma lista com os arquivos existentes na pasta
		
		if(listaArquivo.length!=0) {
			
			for (int i=0; i<listaArquivo.length;i++) { //percorrer essa lista de arquivos
				
				if(!(listaArquivo[i].getName().equalsIgnoreCase("resultado.txt"))) { //verifica se o arquivo nao esta armazenado
					
					leituraArq(listaArquivo[i]); //chamada do metodo para leitura do arquivo
					System.out.println("Arquivos Encontrados: "+ listaArquivo[i]);
					
					aux = new File(INICIO+listaArquivo[i].getName());
					
					// move o arquivo da pasta processando  para a pasta final
					movimentoArq.copiarArquivo(INICIO+listaArquivo[i].getName(), FINAL+listaArquivo[i].getName());				
				}
			
			continue;
			
			}
		
			return true;
		}
		
		return false;
		
	}
	
	public void leituraArq(File arq) {
		try {
			
			FileReader leitor = new FileReader(arq);
			BufferedReader ler = new BufferedReader(leitor);
			
			String linhas;
			
			do {
				/** loop que leia linha por linha do arquivo e realiza a soma  
				 * do seu conteudo e joga no arquivo resultado
				 */
				linhas = ler.readLine();
				
				if(linhas==null) 
					break;
					
					String[] digitos = linhas.split("(?<=\\G.{1})");
					int soma = 0;
					for(int i =0; i<digitos.length;i++) {
						soma = soma+ Integer.parseInt(digitos[i]);
					}
					//chamada do metodo reponsavel pela escrita do resultado no arquivo
					escreverArq("processando/resultado.txt", Integer.toString(soma), arq.getName());
			
			}while(linhas!=null && linhas!=" ");
			
			ler.close();
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
			
		}catch(IOException e) {
			e.printStackTrace();
			
		}
	}
	
	
	public void escreverArq(String pasta, String texto, String nomeArq) throws IOException {
		/** metodo responsavel de escrever o dados de nome
		 * do arquivo e soma no arquivo resultado.txt
		 */
		BufferedWriter escrever = new BufferedWriter(new FileWriter(pasta,true));
		escrever.append("Nome: "+nomeArq+ " / Resultado da soma: " + texto + "\n");
		escrever.close();	
	}
	
	
	
}
