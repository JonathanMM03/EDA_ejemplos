import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ColaConMenu {
    public static void main(String[] args) {
        // Este código simula el funcionamiento de una cola (FIFO: First In, First Out) utilizando la clase Queue de Java.
        // El programa permite al usuario encolar, desencolar y ver el contenido de la cola mediante un menú interactivo en consola.

        // Creamos una cola utilizando LinkedList, que implementa la interfaz Queue de Java.
        Queue<Integer> cola = new LinkedList<>();
        // Creamos un objeto Scanner para leer la entrada del usuario.
        Scanner scanner = new Scanner(System.in);
        int opcion;

        // Bucle principal que se repite mientras el usuario no seleccione la opción de salir.
        do {
            // Menú de opciones que el usuario puede elegir.
            System.out.println("Menú de opciones:");
            System.out.println("1. Encolar");
            System.out.println("2. Desencolar");
            System.out.println("3. Mostrar Cola");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            // Leemos la opción elegida por el usuario.
            opcion = scanner.nextInt();

            // Usamos un switch para ejecutar diferentes acciones según la opción seleccionada.
            switch (opcion) {
                case 1:  // Si el usuario elige "Encolar".
                    System.out.print("Ingrese el valor a encolar: ");
                    // Leemos el valor que el usuario desea encolar y lo agregamos a la cola.
                    int valor = scanner.nextInt();
                    cola.add(valor);  // Añadimos el valor al final de la cola.
                    System.out.println("\u001B[34m" + "|---> " + valor + "\u001B[0m");  // Azul para cuando entra el valor
                    imprimirCola(cola);  // Imprimimos el estado actual de la cola.
                    break;
                case 2:  // Si el usuario elige "Desencolar".
                    // Verificamos si la cola está vacía antes de intentar desencolar.
                    if (cola.isEmpty()) {
                        System.out.println("La cola está vacía.");
                    } else {
                        // Desencolamos el primer elemento de la cola y lo mostramos.
                        System.out.println("\u001B[31m" + "<---| " + cola.poll() + "\u001B[0m");  // Rojo para cuando sale el valor
                        imprimirCola(cola);  // Imprimimos el estado actualizado de la cola.
                    }
                    break;
                case 3:  // Si el usuario elige "Mostrar Cola".
                    // Verificamos si la cola está vacía antes de mostrar su contenido.
                    if (cola.isEmpty()) {
                        System.out.println("La cola está vacía.");
                    } else {
                        // Mostramos el contenido completo de la cola.
                        System.out.println("Contenido de la cola: " + cola);
                    }
                    break;
                case 4:  // Si el usuario elige "Salir".
                    System.out.println("Saliendo...");
                    break;
                default:  // Si el usuario ingresa una opción inválida.
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 4);  // El bucle se repite hasta que el usuario seleccione salir.

        // Cerramos el objeto scanner al finalizar el programa.
        scanner.close();
    }

    // Método para imprimir la cola en formato ASCII con colores.
    public static void imprimirCola(Queue<Integer> cola) {
        if (cola.isEmpty()) {
            System.out.println("La cola está vacía.");
        } else {
            // Usamos un bucle para mostrar la cola desde el primer elemento (frente) hasta el último (final).
            System.out.println("Estado actual de la cola:");
            for (Integer valor : cola) {
                // Imprimir la cola sin color, solo los valores.
                System.out.println("|--- " + valor);
            }
        }
        System.out.println("-".repeat(20));  // Separador visual para la cola.
    }
}