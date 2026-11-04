package invernadero;

public class ProgramadorRiego extends Thread {

    // Datos del riego (no cambian)
    private final double LITROS_POR_RIEGO = 2.5;
    private final int PERIODO = 700;
    private final int ciclos;          // cuántos ciclos tiene que hacer

    // Estadísticas del riego (van cambiando)
    private int ciclosHechos = 0;
    private double litros = 0;
    private boolean abierta = false;   // la válvula empieza cerrada

    // Constructor: como el Cohete, le da nombre al hilo
    public ProgramadorRiego(int ciclos) {
        super("riego");
        this.ciclos = ciclos;
    }

    // Lo que hace el hilo
    @Override
    public void run() {
        String hilo = Thread.currentThread().getName();

        for (int i = 1; i <= ciclos; i++) {

            if (abierta) {
                // Si está abierta se cierra
                abierta = false;
                System.out.println("(" + hilo + ") Ciclo " + i + ": válvula CERRADA (total: " + litros + " L)");
            } else {
                // Si está cerrada se abre y se riega
                abierta = true;
                litros = litros + LITROS_POR_RIEGO;
                System.out.println("(" + hilo + ") Ciclo " + i + ": válvula ABIERTA (total: " + litros + " L)");
            }

            ciclosHechos++;
            esperar(PERIODO);
        }
    }

    // Resumen del riego en una línea
    public String informe() {
        return "Riego: " + ciclosHechos + " ciclos | " + litros + " litros";
    }

    private void esperar(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " ha sido interrumpido");
        }
    }
}