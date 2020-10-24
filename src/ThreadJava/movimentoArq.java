package ThreadJava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class movimentoArq {

	public static final void copiarArquivo(String arq1, String arq2) {
		
		InputStream entrada; //captura os dados
		OutputStream saida;	//escreve os dados no arquivo destino
		
		System.out.println(arq1+"\n"+arq2);
		
		try {
			File inFile = new File(arq1); //arquivo a ser copiado
			File outFile = new File(arq2);// recorte para envio do arquivo
			
			entrada = new FileInputStream(inFile);
	        saida = new FileOutputStream(outFile);
	        
	        byte[] buffer = new byte[1024]; //variavel para receber os dados dos arquvivos
	        int leitor;
	        
	        while ((leitor = entrada.read(buffer))>0) { //loop de leitura, e recebimento no buffer
	        	
	        	saida.write(buffer, 0, leitor);// escreve os dados coletados no novo arq
	        	
	        }
	        
	        entrada.close(); // fecha o arquivo inicial
	        saida.close();	// fecha o arquivo final
	        
	        inFile.delete(); // apaga o arquivo criado
			
		} catch(IOException e) {
			e.printStackTrace(); // excecao de erro ao encontrar/escrever/apagar arquivo
		}
		
		
	}
}
