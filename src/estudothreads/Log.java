package estudothreads;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;

public class Log{
	private static BufferedWriter bufferedWriter;
	private static FileWriter fileWriter;
	public static void doLog(List<Carro> carros){
		StringBuilder builder = new StringBuilder();
		for(Carro carro : carros){
			builder.append("ID: " + carro.getId() + " DIRECAO: " + carro.getDirecaoCarro() + " ESTADO: " + carro.getEstado() + "\r\n");
		}
		doLog(builder.toString());
	}
	public static void doLog(Carro carro){
		doLog("ID: " + carro.getId() + " DIRECAO: " + carro.getDirecaoCarro() + " ESTADO: " + carro.getEstado() + "\r\n");
	}
	public static void doLog(String texto){
		try {
			fileWriter = new FileWriter("log.txt", true);
			bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write("------------------------------------------------------------------------\r\n" + texto + "\r\n------------------------------------------------------------------------\r\n");
			bufferedWriter.flush();
			bufferedWriter.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("------------------------------------------------------------------------");
		System.out.println(texto);
		System.out.println("------------------------------------------------------------------------");		
	}
}
