package ThreadJava;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;


public class verificadorJava extends Thread{
	
	private File[] lista;
	private String arquivoNome;
	private static final String INICIO = "origem/";
	private static final String FINAL = "processando/";
	
	public void run() {
		//loop de verificao da pasta
		while(true) {
			if(arquivoPasta()) {
				AuxiliarJava aux = new AuxiliarJava();
				aux.start();
			}else {
				try {
					System.out.println("This System is Sleep"); //sem arquivos a thread criada fica esperando
					Thread.sleep(5000);
				}catch(InterruptedException ex) {
					Logger.getLogger(verificadorJava.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
	}
	
	
	public boolean arquivoPasta() {
		File arquivo = new File(INICIO); // arquivo inicial é gerado
		lista = arquivo.listFiles(); // lista os arquivos encontrados
		
			if(lista.length!=0) { //vericando se existe arquivo
				for(int i=0; i<lista.length; i++) {
					movimentoArq.copiarArquivo(INICIO+lista[i].getName(),FINAL+lista[i].getName()); // move o arquivo para a pasta de processamento
				}
					return true;
			}
			
			return false;
			
		
	}
	

}
