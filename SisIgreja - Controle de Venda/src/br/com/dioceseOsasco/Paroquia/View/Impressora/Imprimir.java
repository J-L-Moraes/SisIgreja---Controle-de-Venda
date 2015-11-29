package br.com.dioceseOsasco.Paroquia.View.Impressora;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Imprimir {
	
	private Scanner sc;

	public void imprimirLPT1(File arquivo){
	        
		try {
	             java.io.InputStream is = new FileInputStream(arquivo);
	             sc = new Scanner(is);
	             FileOutputStream fs = new FileOutputStream("LPT1:");
	             PrintStream ps = new PrintStream(fs);

	             while(sc.hasNextLine()){
	                 String linhas = sc.nextLine();
	                 ps.println(linhas);
	             }
	             fs.close();
	         } catch (IOException ex) {
	             JOptionPane.showMessageDialog(null, "Erro encontrado ao imprimir.\n"+ex.getMessage());
	         }        
	    }

}
