package invernadero;

public class Fase2_Concurrente {

    public static void main(String[] args) throws InterruptedException {

        // Se crean los objetos
        Temperatura temperatura = new Temperatura();
        Humedad humedad = new Humedad();
        Luz luz = new Luz();
        ProgramadorRiego riego = new ProgramadorRiego(5);

        // Aquí, cada sensor se lo damos a un hilo con nombre
        Thread hiloTemperatura = new Thread(temperatura, "sensor-Temperatura");
        Thread hiloHumedad = new Thread(humedad, "sensor-Humedad");
        Thread hiloLuz = new Thread(luz, "sensor-Luz");
        // Ahora el riego ya es un hilo

        // Reloj ANTES
        long inicio = System.currentTimeMillis();

        // Arrancamos los cuatro en el que cada uno tiene su propio trabajador
        hiloTemperatura.start();
        hiloHumedad.start();
        hiloLuz.start();
        riego.start();

        // Aquí main espera a que terminen los cuatro
        hiloTemperatura.join();
        hiloHumedad.join();
        hiloLuz.join();
        riego.join();

        // Reloj DESPUÉS
        long fin = System.currentTimeMillis();

        // Informe
        System.out.println();
        System.out.println("===== INFORME CONCURRENTE =====");
        System.out.println(temperatura.informe());
        System.out.println(humedad.informe());
        System.out.println(luz.informe());
        System.out.println(riego.informe());
        System.out.println("Tiempo total: " + (fin - inicio) + " ms");
    }
}