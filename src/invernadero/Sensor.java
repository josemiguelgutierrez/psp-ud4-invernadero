package invernadero;

public abstract class Sensor implements Runnable {

    // Datos del sensor
    private final String nombre;
    private final String unidad;
    private final int periodo;
    private final int numLecturas;
    private final double minimo;
    private final double maximo;

    // Estadísticas del sensor
    private int lecturas = 0;
    private double suma = 0;
    private double menor = 0;
    private double mayor = 0;
    private int alertas = 0;

    // Constructor: guarda los datos del sensor
    public Sensor(String nombre, String unidad, int periodo, int numLecturas,
                  double minimo, double maximo) {
        this.nombre = nombre;
        this.unidad = unidad;
        this.periodo = periodo;
        this.numLecturas = numLecturas;
        this.minimo = minimo;
        this.maximo = maximo;
    }

    // Cada sensor dirá cómo mide
    public abstract double medir();

    // Lo que hace el hilo
    @Override
    public void run() {
        // Preguntamos qué hilo está ejecutando esto
        String hilo = Thread.currentThread().getName();

        for (int i = 1; i <= numLecturas; i++) {

            // Medir
            double valor = redondear(medir());

            // Apunta en las estadísticas
            lecturas++;
            suma = suma + valor;
            if (lecturas == 1) {
                menor = valor;
                mayor = valor;
            }
            if (valor < menor) {
                menor = valor;
            }
            if (valor > mayor) {
                mayor = valor;
            }

            // Prepara la línea del log
            String linea = java.time.LocalTime.now() + " (" + hilo + ") " + nombre + ": " + valor + " " + unidad;

            // Si está fuera del rango sale una alerta
            if (valor < minimo || valor > maximo) {
                alertas++;
                linea = linea + "  ⚠ ALERTA";
            }

            System.out.println(linea);

            // Esperar hasta la siguiente lectura
            esperar(periodo);
        }
    }

    // Resumen del informe
    public String informe() {
        // Si todavía no ha medido nada
        if (lecturas == 0) {
            return nombre + ": todavía no hay lecturas";
        }

        double media = redondear(suma / lecturas);

        return nombre + ": " + lecturas + " lecturas"
                + " | media " + media + " " + unidad
                + " | mínimo " + menor
                + " | máximo " + mayor
                + " | alertas " + alertas;
    }

    // Igual que en el Cohete: sleep obliga a usar try/catch
    private void esperar(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " ha sido interrumpido");
        }
    }

    // Deja el número con 1 decimal (23.4567 → 23.5)
    private double redondear(double numero) {
        return Math.round(numero * 10) / 10.0;
    }
}