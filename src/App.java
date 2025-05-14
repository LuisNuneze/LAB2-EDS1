import java.util.Scanner;

public class App {
    public static String verificarBalanceo(String expresion) {
        char[] pila = new char[expresion.length()];
        int tope = -1;
        String simbolosApertura = "([{";
        String simbolosCierre = ")]}";

        for (int i = 0; i < expresion.length(); i++) {
            String sub = expresion.substring(i, i + 1);

            boolean esApertura = false;
            for (int j = 0; j < simbolosApertura.length(); j++) {
                if (sub.equals(simbolosApertura.substring(j, j + 1))) {
                    esApertura = true;
                }
            }

            if (esApertura) {
                pila[++tope] = sub.charAt(0);
            } else {
                boolean esCierre = false;
                for (int j = 0; j < simbolosCierre.length(); j++) {
                    if (sub.equals(simbolosCierre.substring(j, j + 1))) {
                        esCierre = true;
                    }
                }

                if (esCierre) {
                    if (tope == -1) {
                        return "Resultado: No balanceada (cierre inesperado)";
                    }

                    char caracterTope = pila[tope--];
                    char aperturaEsperada = ' ';
                    if (sub.equals(")"))
                        aperturaEsperada = '(';
                    else if (sub.equals("]"))
                        aperturaEsperada = '[';
                    else if (sub.equals("}"))
                        aperturaEsperada = '{';

                    if (caracterTope != aperturaEsperada) {
                        return "Resultado: No balanceada (desajuste de símbolos)";
                    }
                } else {
                    if (!sub.equals(" ")) {
                        return "Error: La expresión contiene un carácter no esperado ('" + sub
                                + "'). Programa finalizado.";
                    }
                }
            }
        }

        if (tope == -1) {
            return "Resultado: Balanceada";
        } else {
            return "Resultado: No balanceada (aperturas sin cierre)";
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner leer = new Scanner(System.in);
        int op = 0;

        // Menu
        while (op != 4) {
            System.out.println("\n***LISTCALCULATOR***");
            System.out.println("\n¡Bienvenido! Escoga una de las siguientes opciones:");
            System.out.println("\t1. Elevar a la 3 matriz");
            System.out.println("\t2. Unión de lista");
            System.out.println("\t3. Revisión de balance");
            System.out.println("\t4. Salir");

            try {
                System.out.print("\nIngrese la opción: ");
                op = leer.nextInt();
                leer.nextLine(); // Consumir el salto de línea sobrante
            } catch (Exception e) {
                System.out.print("Entrada inválida. Por favor ingrese un número: ");
                leer.nextLine(); // Limpiar el buffer
            }

            while (op < 1 || op > 4) {
                System.out.print("Por favor ingrese una opción válida: ");
                try {
                    op = leer.nextInt();
                } catch (Exception e) {
                    leer.nextLine();
                }
            }

            switch (op) {
                case 1:
                    int i = 0, fila = 0, columna = 0, valor, m;
                    SimpleList matrizList = new SimpleList();
                    System.out.print("\nIngrese la dimensión: ");
                    m = leer.nextInt();
                    System.out.println("Ingrese el valor correspondiente a cada posición");
                    while (i < m*m) {
                        try {
                            System.out.print(
                                    String.format("\nIngrese el valor de la posición [%d][%d]: ", fila, columna));
                            valor = leer.nextInt();
                            if (valor != 0) {
                                matrizList.addQueue(fila, columna, valor);
                            }

                            if (columna >= m-1) {
                                columna = 0;
                                fila++;
                            } else {
                                columna++;
                            }
                            i++;
                        } catch (Exception e) {
                            System.out.print("Ingrese un valor válido por favor: ");
                            leer.nextLine();
                        }
                    }

                    System.out.print("Ingrese la potencia de n:");
                    int n = leer.nextInt();

                    System.out.println("\nLa matriz dispersa A:");
                    matrizList.traversalMatriz();

                    System.out.println("\nElevada a la n es igual a:");
                    SimpleList originalList = matrizList;
                    for (i = 0; i<n-1; i++){
                        matrizList = Methods.multiplicarMatriz(matrizList, originalList, m);
                    }
                    matrizList.traversalMatriz();
                    break;

                case 2:
                    InteractiveListOps.main(args);
                    break;

                case 3:
                    System.out.print("Ingrese una expresión para verificar balanceo: ");
                    String expresion = leer.nextLine();
                    String resultado = verificarBalanceo(expresion);
                    System.out.println(resultado);
                    break;

                default:
                    break;
            }
        }
    }
}
