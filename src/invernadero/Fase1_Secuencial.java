package invernadero;

public class Fase1_Secuencial {

    public static void main(String[] args) {

        // Creamos los objetos
        Temperatura temperatura = new Temperatura();
        Humedad humedad = new Humedad();
        Luz luz = new Luz();
        ProgramadorRiego riego = new ProgramadorRiego(5);

        // Miramos el reloj antes
        long inicio = System.currentTimeMillis();

        // run() a propósito, lo hace main, uno detrás de otro, que es la versión lenta
        temperatura.run();
        humedad.run();
        luz.run();
        riego.run();

        // Miramos el reloj después
        long fin = System.currentTimeMillis();

        // Informe de todos y tiempo total
        System.out.println();
        System.out.println("===== INFORME SECUENCIAL =====");
        System.out.println(temperatura.informe());
        System.out.println(humedad.informe());
        System.out.println(luz.informe());
        System.out.println(riego.informe());
        System.out.println("Tiempo total: " + (fin - inicio) + " ms");
    }
}