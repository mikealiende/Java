package Client;
import java.util.List;
import ServidorConcurrente.*;

public class Test{

	public static void main(String args[]){ 
		
		Interval intervalo = new Interval(1, 10000);
		System.out.println("Puede realizar " + Runtime.getRuntime().availableProcessors() + " tareas a la vez");
		PerfectNumbersServer perfecto= new PerfectNumbersServer();
		long ini = System.currentTimeMillis();
		List<Long> perfectos = perfecto.getPerfectNumbers(intervalo);
		long end = System.currentTimeMillis();
		System.out.println("Tiempo de ejecución por tarea: " + (end - ini));
		for(int i = 0; i < perfectos.size(); i++){
			System.out.println(" N perfecto:" + perfectos.get(i));
		}
		
		System.out.println("El num tareas por unidad tiempo (throughput) de esta maquina es:\n  " 
				+ Runtime.getRuntime().availableProcessors() + " tareas cada "  + (end - ini) + " ms"
				+ " o " + (Runtime.getRuntime().availableProcessors() * 1000.0) / (end - ini) + " tareas / s");
	}
}
