package invernadero;

public class Fase3_Experimentos {

    public static void main(String[] args) throws InterruptedException {

        // Informe sin esperar y después esperando
        System.out.println("--- EXPERIMENTO A ---");
        Temperatura temperatura = new Temperatura();
        Thread hiloA = new Thread(temperatura, "sensor-Temperatura");
        hiloA.start();
        System.out.println("Sin esperar: " + temperatura.informe());
        hiloA.join();
        System.out.println("Después de join(): " + temperatura.informe());

        // Aquí ponemos run() en vez de start()
        System.out.println();
        System.out.println("--- EXPERIMENTO B ---");
        ProgramadorRiego riegoB = new ProgramadorRiego(3);
        long inicio = System.currentTimeMillis();
        riegoB.run();
        long fin = System.currentTimeMillis();
        System.out.println("Tiempo: " + (fin - inicio) + " ms");
        System.out.println("Estado final: " + riegoB.getState());

        // Aquí se pone start() dos veces sobre el mismo objeto
        System.out.println();
        System.out.println("--- EXPERIMENTO C ---");
        ProgramadorRiego riegoC = new ProgramadorRiego(2);
        riegoC.start();
        riegoC.join();
        try {
            riegoC.start();
        } catch (IllegalThreadStateException e) {
            System.out.println("Excepción capturada: " + e);
        }
    }
}