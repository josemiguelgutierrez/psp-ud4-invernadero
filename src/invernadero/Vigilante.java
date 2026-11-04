package invernadero;

public class Vigilante extends Thread {

    private final Thread temperatura;
    private final Thread humedad;
    private final Thread luz;
    private final Thread riego;

    public Vigilante(Thread temperatura, Thread humedad, Thread luz, Thread riego) {
        super("vigilante");
        this.temperatura = temperatura;
        this.humedad = humedad;
        this.luz = luz;
        this.riego = riego;
    }

    // Muestra el estado de los cuatro hilos
    public void mostrarEstados(String momento) {
        System.out.println("[" + momento + "] "
                + "Temperatura=" + temperatura.getState()
                + " | Humedad=" + humedad.getState()
                + " | Luz=" + luz.getState()
                + " | Riego=" + riego.getState());
    }

    @Override
    public void run() {
        while (true) {
            mostrarEstados("vigilante");
            esperar(300);
        }
    }

    private void esperar(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println("vigilante interrumpido");
        }
    }
}