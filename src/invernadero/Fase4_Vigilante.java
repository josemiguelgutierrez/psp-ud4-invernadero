package invernadero;

public class Fase4_Vigilante {

    public static void main(String[] args) throws InterruptedException {

        Thread hiloTemperatura = new Thread(new Temperatura(), "sensor-Temperatura");
        Thread hiloHumedad = new Thread(new Humedad(), "sensor-Humedad");
        Thread hiloLuz = new Thread(new Luz(), "sensor-Luz");
        ProgramadorRiego riego = new ProgramadorRiego(5);

        Vigilante vigilante = new Vigilante(hiloTemperatura, hiloHumedad, hiloLuz, riego);

        // Muestra los estados antes de arrancar
        vigilante.mostrarEstados("ANTES");

        // El vigilante nunca termina. Con esto el programa se cierra igual cuando acaban los demás hilos.
        vigilante.setDaemon(true);

        hiloTemperatura.start();
        hiloHumedad.start();
        hiloLuz.start();
        riego.start();
        vigilante.start();

        // Main espera a los cuatro
        hiloTemperatura.join();
        hiloHumedad.join();
        hiloLuz.join();
        riego.join();

        // Muestra los estados después de arrancar
        vigilante.mostrarEstados("DESPUÉS");
        System.out.println("main termina y el vigilante (demonio) se cierra con el programa");
    }
}