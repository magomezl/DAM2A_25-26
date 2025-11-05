package pruebamultihilo;

public class PrincipalMultihilo {

	static class HiloRojo implements Runnable{

		@Override
		public void run() {
			Singleton singleton = Singleton.getInstance("Rojo");
			System.out.println(singleton.getColor());
		}
	}
	
	static class HiloAzul implements Runnable{

		@Override
		public void run() {
			Singleton singleton = Singleton.getInstance("Azul");
			System.out.println(singleton.getColor());
		}
	}
	
	static class HiloAmarillo implements Runnable{

		@Override
		public void run() {
			Singleton singleton = Singleton.getInstance("Amarillo");
			System.out.println(singleton.getColor());
		}
	}
	public static void main(String[] args) {
		Thread hiloRojo = new Thread(new HiloRojo());
		Thread hiloAzul = new Thread(new HiloAzul());
		Thread hiloAmarillo = new Thread(new HiloAmarillo());
		
		hiloRojo.start();
		hiloAzul.start();
		hiloAmarillo.start();
	}
}
