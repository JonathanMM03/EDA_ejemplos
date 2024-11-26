import java.util.Scanner;

public class ArbolBinario {
    // Definimos la estructura del nodo del árbol binario.
    static class Nodo {
        int valor;  // Valor del nodo.
        Nodo izquierdo, derecho;  // Referencias a los nodos hijo izquierdo y derecho.

        // Constructor para inicializar el nodo con un valor.
        public Nodo(int valor) {
            this.valor = valor;
            izquierdo = derecho = null;
        }
    }

    // Método para insertar un nuevo nodo en el árbol con seguimiento didáctico.
    public static Nodo insertar(Nodo raiz, int valor) {
        if (raiz == null) {
            raiz = new Nodo(valor);
            System.out.println("\u001B[33m" + "Inserción: Nodo creado con valor " + valor + "\u001B[0m");
            imprimirArbol(raiz, "", "raiz");
            System.out.println("-".repeat(40));  // Separador después de la inserción
        } else {
            if (valor < raiz.valor) {
                System.out.println("\u001B[33m" + "Insertando " + valor + " en el subárbol izquierdo de " + raiz.valor + "\u001B[0m");
                raiz.izquierdo = insertar(raiz.izquierdo, valor);
            } else {
                System.out.println("\u001B[33m" + "Insertando " + valor + " en el subárbol derecho de " + raiz.valor + "\u001B[0m");
                raiz.derecho = insertar(raiz.derecho, valor);
            }
            imprimirArbol(raiz, "", "raiz"); // Mostrar el árbol después de cada inserción.
            System.out.println("-".repeat(40));  // Separador después de la inserción
        }
        return raiz;
    }

    // Método para eliminar un nodo en el árbol binario de búsqueda.
    public static Nodo eliminar(Nodo raiz, int valor) {
        if (raiz == null) {
            return null;
        }

        // Si el valor a eliminar es menor que el valor del nodo actual, buscamos en el subárbol izquierdo.
        if (valor < raiz.valor) {
            raiz.izquierdo = eliminar(raiz.izquierdo, valor);
        }
        // Si el valor a eliminar es mayor, buscamos en el subárbol derecho.
        else if (valor > raiz.valor) {
            raiz.derecho = eliminar(raiz.derecho, valor);
        }
        // Si encontramos el valor, eliminamos el nodo.
        else {
            // Caso 1: El nodo tiene solo un hijo o no tiene hijos.
            if (raiz.izquierdo == null) {
                return raiz.derecho;
            } else if (raiz.derecho == null) {
                return raiz.izquierdo;
            }

            // Caso 2: El nodo tiene dos hijos, necesitamos encontrar el sucesor (el valor más pequeño en el subárbol derecho).
            raiz.valor = minValue(raiz.derecho);

            // Eliminar el sucesor.
            raiz.derecho = eliminar(raiz.derecho, raiz.valor);
        }
        return raiz;
    }

    // Método para encontrar el valor mínimo en un subárbol (para eliminar nodos con dos hijos).
    public static int minValue(Nodo raiz) {
        int minValue = raiz.valor;
        while (raiz.izquierdo != null) {
            minValue = raiz.izquierdo.valor;
            raiz = raiz.izquierdo;
        }
        return minValue;
    }

    // Método para realizar un recorrido en Inorden.
    public static void recorridoInorden(Nodo raiz) {
        if (raiz != null) {
            recorridoInorden(raiz.izquierdo);
            System.out.print(raiz.valor + " ");
            recorridoInorden(raiz.derecho);
        }
    }

    // Método para imprimir el árbol en formato ASCII con símbolos |, -, > y colores.
    public static void imprimirArbol(Nodo raiz, String espacio, String direccion) {
        if (raiz == null) {
            return;
        }

        // Llamada recursiva para imprimir el subárbol derecho.
        imprimirArbol(raiz.derecho, espacio + "   ", "derecha");

        // Imprimir el valor del nodo con la conexión hacia el nodo superior.
        if (direccion.equals("raiz")) {
            System.out.println(espacio + "\u001B[32m> " + raiz.valor + "\u001B[0m");  // Raíz en verde
        } else if (direccion.equals("izquierda")) {
            System.out.println(espacio + "\u001B[31m|--- " + raiz.valor + "\u001B[0m");  // Subárbol izquierdo en rojo
        } else if (direccion.equals("derecha")) {
            System.out.println(espacio + "\u001B[34m|--- " + raiz.valor + "\u001B[0m");  // Subárbol derecho en azul
        }

        // Llamada recursiva para imprimir el subárbol izquierdo.
        imprimirArbol(raiz.izquierdo, espacio + "   ", "izquierda");
    }

    // Método para limpiar la consola dependiendo del sistema operativo
    public static void limpiarConsola() {
        String sistemaOperativo = System.getProperty("os.name").toLowerCase();
        try {
            if (sistemaOperativo.contains("win")) {
                // Comando para Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Comando para sistemas Unix (Linux, macOS)
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para ejecutar el código.
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Nodo raiz = null;
        int opcion, valor;

        // Bucle principal que se repite mientras el usuario no seleccione la opción de salir.
        do {
            limpiarConsola();  // Limpiar la consola antes de mostrar el menú
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Insertar número");
            System.out.println("2. Eliminar número");
            System.out.println("3. Recorrido Inorden (Ordenado)");
            System.out.println("4. Imprimir árbol en formato ASCII");
            System.out.println("5. Ver inserción paso a paso");
            System.out.println("6. Salir");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:  // Si el usuario elige "Insertar número".
                    System.out.print("Ingrese el número a insertar: ");
                    valor = scanner.nextInt();
                    raiz = insertar(raiz, valor);
                    System.out.println("Número insertado.");
                    break;
                case 2:  // Si el usuario elige "Eliminar número".
                    System.out.print("Ingrese el número a eliminar: ");
                    valor = scanner.nextInt();
                    raiz = eliminar(raiz, valor);
                    System.out.println("Número eliminado.");
                    break;
                case 3:  // Si el usuario elige "Recorrido Inorden".
                    System.out.println("Recorrido Inorden (Ordenado):");
                    recorridoInorden(raiz);
                    System.out.println();
                    break;
                case 4:  // Si el usuario elige "Imprimir árbol en formato ASCII".
                    System.out.println("Árbol en formato ASCII:");
                    imprimirArbol(raiz, "", "raiz");  // Se inicia como "raiz" para que no tenga conexión previa.
                    break;
                case 5:  // Si el usuario elige "Ver inserción paso a paso".
                    System.out.println("Viendo cómo se inserta un número en el árbol...");
                    System.out.print("Ingrese el número a insertar: ");
                    valor = scanner.nextInt();
                    raiz = insertar(raiz, valor);  // Mostrará los pasos de inserción.
                    break;
                case 6:  // Si el usuario elige "Salir".
                    System.out.println("Saliendo...");
                    break;
                default:  // Si la opción no es válida.
                    System.out.println("Opción inválida.");
            }
        } while (opcion != 6);  // El bucle se repite hasta que el usuario seleccione salir.

        scanner.close();
    }
}