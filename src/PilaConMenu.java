import java.util.Stack;
import java.util.Scanner;

public class PilaConMenu {
    public static void main(String[] args) {
        // Creamos una pila utilizando la clase Stack de Java.
        Stack<Integer> pila = new Stack<>();
        // Creamos un objeto Scanner para leer la entrada del usuario.
        Scanner scanner = new Scanner(System.in);
        int opcion;

        // Bucle principal que se repite mientras el usuario no seleccione la opción de salir.
        do {
            // Menú de opciones que el usuario puede elegir.
            System.out.println("Menú de opciones:");
            System.out.println("1. Apilar");
            System.out.println("2. Desapilar");
            System.out.println("3. Mostrar Pila");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            // Leemos la opción elegida por el usuario.
            opcion = scanner.nextInt();

            // Usamos un switch para ejecutar diferentes acciones según la opción seleccionada.
            switch (opcion) {
                case 1:  // Si el usuario elige "Apilar".
                    System.out.print("Ingrese el valor a apilar: ");
                    // Leemos el valor que el usuario desea apilar y lo agregamos a la pila.
                    int valor = scanner.nextInt();
                    pila.push(valor);  // Apilamos el valor (lo agregamos al tope de la pila).
                    System.out.println("\u001B[34m" + "|---> " + valor + "\u001B[0m");  // Azul para cuando entra el valor
                    imprimirPila(pila);  // Imprimimos el estado actual de la pila.
                    break;
                case 2:  // Si el usuario elige "Desapilar".
                    // Verificamos si la pila está vacía antes de intentar desapilar.
                    if (pila.isEmpty()) {
                        System.out.println("La pila está vacía.");
                    } else {
                        // Desapilamos el valor en el tope de la pila y lo mostramos.
                        System.out.println("\u001B[31m" + "<---| " + pila.pop() + "\u001B[0m");  // Rojo para cuando sale el valor
                        imprimirPila(pila);  // Imprimimos el estado actualizado de la pila.
                    }
                    break;
                case 3:  // Si el usuario elige "Mostrar Pila".
                    // Verificamos si la pila está vacía antes de mostrar su contenido.
                    if (pila.isEmpty()) {
                        System.out.println("La pila está vacía.");
                    } else {
                        // Mostramos el contenido completo de la pila.
                        System.out.println("Contenido de la pila: " + pila);
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

    // Método para imprimir la pila en formato ASCII con colores.
    public static void imprimirPila(Stack<Integer> pila) {
        if (pila.isEmpty()) {
            System.out.println("La pila está vacía.");
        } else {
            // Usamos un bucle para mostrar la pila de arriba hacia abajo.
            System.out.println("Estado actual de la pila:");
            for (int i = pila.size() - 1; i >= 0; i--) {
                // Imprimir la pila en formato visual.
                System.out.println("|--- " + pila.get(i));  // Mostrar los elementos apilados sin color.
            }
        }
        System.out.println("-".repeat(20));  // Separador visual para la pila.
    }
}