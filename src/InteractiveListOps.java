import java.util.Scanner;

// Nodo para la lista doblemente enlazada (PTR1)
class DoubleNode {
    private int data;
    private DoubleNode left, right; // Punteros para el nodo anterior (left) y siguiente (right)

    public DoubleNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }

    // Getters y Setters
    public int getData() {
        return data;
    }

    public DoubleNode getLeft() {
        return left;
    }

    public DoubleNode getRight() {
        return right;
    }

    public void setData(int data) {
        this.data = data;
    }

    public void setLeft(DoubleNode left) {
        this.left = left;
    }

    public void setRight(DoubleNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return String.valueOf(this.data);
    }
}

// Lista doblemente enlazada (PTR1)
class DoublyLinkedList {
    DoubleNode head; // Puntero al inicio de la lista
    DoubleNode tail; // Puntero al final de la lista

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    // Método para buscar un valor en la lista (asume que la lista está ordenada para la optimización)
    public boolean search(int value) {
        DoubleNode current = head;
        while (current != null) {
            if (current.getData() == value) {
                return true; // Valor encontrado
            }
            // Optimización: si la lista está ordenada y el valor actual es mayor que el buscado,
            // no es necesario seguir buscando.
            if (current.getData() > value && head != null) {
                return false;
            }
            current = current.getRight();
        }
        return false; // Valor no encontrado
    }

    // Método para insertar un nodo de forma ordenada
    public void insertSorted(int data) {
        DoubleNode newNode = new DoubleNode(data);
        if (head == null) { // Si la lista está vacía
            head = newNode;
            tail = newNode;
            return;
        }

        if (data <= head.getData()) { // Insertar al inicio
            newNode.setRight(head);
            head.setLeft(newNode);
            head = newNode;
            return;
        }

        DoubleNode current = head;
        // Avanzar hasta encontrar la posición correcta para insertar
        while (current.getRight() != null && current.getRight().getData() < data) {
            current = current.getRight();
        }

        if (current.getRight() == null) { // Insertar al final
            current.setRight(newNode);
            newNode.setLeft(current);
            tail = newNode;
        } else { // Insertar en medio
            newNode.setRight(current.getRight());
            newNode.setLeft(current);
            current.getRight().setLeft(newNode);
            current.setRight(newNode);
        }
    }

    // Método para añadir un elemento al final de la lista (usado para listas auxiliares)
    public void appendSimply(int data) {
        DoubleNode newNode = new DoubleNode(data);
        if (head == null) { // Si la lista está vacía
            head = newNode;
            tail = newNode;
        } else { // Añadir después de la cola actual
            tail.setRight(newNode);
            newNode.setLeft(tail);
            tail = newNode; // El nuevo nodo es ahora la cola
        }
    }

    // Método para eliminar un nodo por su valor
    public boolean deleteByValue(int value) {
        if (head == null) { // Si la lista está vacía
            return false;
        }

        DoubleNode current = head;
        // Buscar el nodo a eliminar
        // Esta búsqueda es genérica y no asume orden para deleteByValue,
        // aunque la optimización de 'search' podría aplicarse si se garantiza orden.
        while (current != null && current.getData() != value) {
             // Optimización potencial si se sabe que la lista está ordenada:
             // if (current.getData() > value) return false;
            current = current.getRight();
        }

        if (current == null) { // Valor no encontrado
            return false;
        }

        // Si se encontró el nodo (current.getData() == value)
        if (current.getLeft() != null) { // Si no es la cabeza
            current.getLeft().setRight(current.getRight());
        } else { // El nodo a eliminar es la cabeza
            head = current.getRight();
        }

        if (current.getRight() != null) { // Si no es la cola
            current.getRight().setLeft(current.getLeft());
        } else { // El nodo a eliminar es la cola
            tail = current.getLeft();
        }

        if (head == null) { // Si la lista quedó vacía después de la eliminación
            tail = null;
        }
        return true; // Nodo eliminado exitosamente
    }

    // Método para mostrar la lista
    public void display(String listName) {
        StringBuilder sb = new StringBuilder();
        DoubleNode current = head;
        while (current != null) {
            sb.append(current.getData());
            if (current.getRight() != null) {
                sb.append(" <-> ");
            }
            current = current.getRight();
        }

        if (sb.length() == 0) {
            System.out.println(listName + ": (vacía)");
        } else {
            System.out.println(listName + ": Head -> " + sb.toString() + " <- Tail");
        }
    }
}

// Clase para los nodos de la lista circular simplemente enlazada (PTR2)
class CLLNode {
    int data;
    CLLNode next; // Puntero 'next' para enlace simple. No hay puntero 'prev', cumpliendo "no doblemente enlazada".

    public CLLNode(int data) {
        this.data = data;
        this.next = null; // Se establecerá durante la inserción para formar el enlace.
    }

    @Override
    public String toString() {
        return String.valueOf(this.data);
    }
}

// Clase para la lista circular simplemente enlazada (PTR2)
class CircularList {
    CLLNode head; // Único puntero necesario para la cabeza de la lista.

    public CircularList() {
        this.head = null;
    }

    // Método para añadir un elemento al final de la lista circular.
    public void append(int data) {
        CLLNode newNode = new CLLNode(data);
        if (head == null) { // Si la lista está vacía
            head = newNode;
            newNode.next = head; // El único nodo apunta a sí mismo, manteniendo la circularidad.
        } else {
            CLLNode current = head;
            while (current.next != head) { // Recorre hasta encontrar el último nodo actual (el que apunta a la cabeza).
                current = current.next;
            }
            current.next = newNode; // El último nodo ahora apunta al nuevo nodo.
            newNode.next = head;    // El nuevo nodo (ahora el último) apunta a la cabeza, cerrando el círculo.
        }
    }

    // Método para eliminar un nodo por su valor.
    public boolean deleteByValue(int valueToDelete) {
        if (head == null) { // Si la lista está vacía
            return false;
        }

        // Caso 1: El nodo a eliminar es la cabeza.
        if (head.data == valueToDelete) {
            if (head.next == head) { // Solo hay un nodo en la lista.
                head = null; // La lista queda vacía.
            } else { // Hay más de un nodo.
                CLLNode currentTail = head;
                while (currentTail.next != head) { // Encontrar el último nodo (el que apunta a la cabeza).
                    currentTail = currentTail.next;
                }
                head = head.next; // Mover la cabeza al siguiente nodo.
                currentTail.next = head; // El último nodo ahora apunta a la nueva cabeza, manteniendo la circularidad.
            }
            return true; // Nodo eliminado
        }

        // Caso 2: El nodo a eliminar no es la cabeza.
        CLLNode prevNode = head;
        CLLNode currentNode = head.next;

        while (currentNode != head) { // Recorrer la lista hasta volver a la cabeza.
            if (currentNode.data == valueToDelete) {
                prevNode.next = currentNode.next; // El nodo anterior salta al nodo siguiente al actual, eliminándolo.
                return true; // Nodo eliminado
            }
            prevNode = currentNode;
            currentNode = currentNode.next;
        }
        return false; // Valor no encontrado en el resto de la lista.
    }

    // Método para vaciar la lista (destruirla).
    public void destroy() {
        head = null; // Elimina la referencia a la cabeza. El recolector de basura de Java se encargará de los nodos.
    }

    // Método para mostrar la lista circular.
    public void display(String listName) {
        if (head == null) { // Si la lista está vacía
            System.out.println(listName + ": (vacía)");
            return;
        }

        StringBuilder sb = new StringBuilder();
        CLLNode current = head;

        do { // Usar do-while para asegurar que el primer nodo (cabeza) se procese.
            sb.append(current.data);
            current = current.next;
            if (current != head) { // Añadir flecha si no es el último elemento antes de volver a la cabeza.
                sb.append(" -> ");
            }
        } while (current != head); // Continúa hasta que 'current' vuelva a ser la cabeza.

        // Muestra la lista indicando que el último nodo apunta de nuevo a la cabeza.
        System.out.println(listName + ": Head(" + head.data + ") -> " +
                sb.toString() + " -> (apunta de nuevo a Head: " + head.data + ")");
    }
}

public class InteractiveListOps {

    // Método principal para procesar las dos listas
    public static void processLists(DoublyLinkedList ptr1, CircularList ptr2) {
        System.out.println("\n----- ESTADO INICIAL DE LAS LISTAS INGRESADAS -----");
        ptr1.display("PTR1");
        ptr2.display("PTR2"); // ptr2 es una CircularList
        System.out.println("----------------------------------------");

        // Se crea una lista doblemente enlazada temporal para almacenar valores únicos de ptr2.
        DoublyLinkedList ptr2UniqueValuesList = new DoublyLinkedList();
        if (ptr2.head != null) {
            CLLNode iterP2 = ptr2.head;
            do { // Recorrer la lista circular ptr2
                if (!ptr2UniqueValuesList.search(iterP2.data)) { // Si el valor no está ya en la lista de únicos
                    ptr2UniqueValuesList.insertSorted(iterP2.data); // Insertarlo ordenadamente
                }
                iterP2 = iterP2.next;
            } while (iterP2 != ptr2.head); // Continuar hasta volver al inicio de ptr2
        }

        DoublyLinkedList commonElementsList = new DoublyLinkedList(); // Lista para elementos comunes
        DoublyLinkedList elementsToInsertList = new DoublyLinkedList(); // Lista para elementos de ptr2 que no están en ptr1

        // Identificar elementos comunes y elementos a insertar
        DoubleNode currentUnique = ptr2UniqueValuesList.head;
        while (currentUnique != null) {
            if (ptr1.search(currentUnique.getData())) { // Si el elemento único de ptr2 está en ptr1
                commonElementsList.appendSimply(currentUnique.getData()); // Añadir a comunes
            } else { // Si no está en ptr1
                elementsToInsertList.appendSimply(currentUnique.getData()); // Añadir a la lista de inserción
            }
            currentUnique = currentUnique.getRight();
        }

        // Mostrar los elementos identificados
        System.out.print("Elementos comunes a eliminar de ambas listas: ");
        StringBuilder commonStr = new StringBuilder();
        DoubleNode tempCommon = commonElementsList.head;
        String Sseparator = "";
        while (tempCommon != null) {
            commonStr.append(Sseparator).append(tempCommon.getData());
            Sseparator = ", ";
            tempCommon = tempCommon.getRight();
        }
        System.out.println(commonStr.length() == 0 ? "(ninguno)" : commonStr.toString());

        System.out.print("Elementos de PTR2 (no en PTR1) para insertar en PTR1: ");
        StringBuilder insertStr = new StringBuilder();
        DoubleNode tempInsert = elementsToInsertList.head;
        Sseparator = "";
        while (tempInsert != null) {
            insertStr.append(Sseparator).append(tempInsert.getData());
            Sseparator = ", ";
            tempInsert = tempInsert.getRight();
        }
        System.out.println(insertStr.length() == 0 ? "(ninguno)" : insertStr.toString());
        System.out.println("----------------------------------------");

        // Eliminar elementos comunes de PTR1
        System.out.println("Procesando eliminaciones de comunes en PTR1...");
        DoubleNode commonNodeToDel = commonElementsList.head;
        while (commonNodeToDel != null) {
            ptr1.deleteByValue(commonNodeToDel.getData());
            commonNodeToDel = commonNodeToDel.getRight();
        }

        // Eliminar elementos comunes de PTR2
        // Es importante iterar la eliminación en ptr2 en caso de duplicados.
        System.out.println("Procesando eliminaciones de comunes en PTR2...");
        commonNodeToDel = commonElementsList.head;
        while (commonNodeToDel != null) {
            int val = commonNodeToDel.getData();
            while (ptr2.deleteByValue(val)) { 
                // Continuar eliminando mientras el valor se encuentre (maneja duplicados en ptr2)
            }
            commonNodeToDel = commonNodeToDel.getRight();
        }

        // Insertar elementos de PTR2 (que no estaban en PTR1) en PTR1 de forma ordenada
        System.out.println("Procesando inserciones en PTR1...");
        DoubleNode nodeToInsert = elementsToInsertList.head;
        while (nodeToInsert != null) {
            ptr1.insertSorted(nodeToInsert.getData());
            nodeToInsert = nodeToInsert.getRight();
        }
        System.out.println("Operaciones completadas.");
        System.out.println("----------------------------------------");

        // Destruir PTR2
        System.out.println("Destruyendo PTR2...");
        ptr2.destroy(); // Llamar al método destroy de CircularList
        ptr2.display("PTR2 después de la destrucción");
        System.out.println("----------------------------------------");

        // Mostrar estado final de PTR1
        System.out.println("----- ESTADO FINAL DE PTR1 -----");
        ptr1.display("PTR1");
        System.out.println("========================================");
    }

    // Método auxiliar para obtener una entrada entera del usuario
    private static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line.trim()); // Intentar convertir la entrada a entero
            } catch (NumberFormatException e) { // Capturar error si no es un número válido
                System.out.println("Error: Por favor, ingrese un número entero válido.");
            }
        }
    }

    // Método para poblar PTR1 (lista doblemente enlazada ordenada) desde la entrada del usuario
    private static DoublyLinkedList populatePtr1FromUser(Scanner scanner) {
        DoublyLinkedList ptr1 = new DoublyLinkedList();
        System.out.println("\n--- Configuración de PTR1 (Lista Doblemente Enlazada Ordenada) ---");
        int numElements = -1;
        while (numElements < 0) { // Asegurar que el número de elementos no sea negativo
            numElements = getIntInput(scanner, "¿Cuántos elementos desea ingresar para PTR1? (0 o más): ");
            if (numElements < 0) {
                System.out.println("El número de elementos no puede ser negativo.");
            }
        }

        for (int i = 0; i < numElements; i++) {
            int element = getIntInput(scanner, "Ingrese el elemento #" + (i + 1) + " para PTR1: ");
            ptr1.insertSorted(element); // Insertar de forma ordenada
        }
        return ptr1;
    }

    // Método para poblar PTR2 (lista circular simplemente enlazada no ordenada) desde la entrada del usuario
    private static CircularList populatePtr2FromUser(Scanner scanner) {
        CircularList ptr2 = new CircularList(); // Se instancia la CircularList correcta
        System.out.println("\n--- Configuración de PTR2 (Lista Circular Simplemente Enlazada No Ordenada) ---");
        int numElements = -1;
        while (numElements < 0) { // Asegurar que el número de elementos no sea negativo
            numElements = getIntInput(scanner, "¿Cuántos elementos desea ingresar para PTR2? (0 o más): ");
            if (numElements < 0) {
                System.out.println("El número de elementos no puede ser negativo.");
            }
        }

        for (int i = 0; i < numElements; i++) {
            int element = getIntInput(scanner, "Ingrese el elemento #" + (i + 1) + " para PTR2: ");
            ptr2.append(element); // Añadir al final (append) de la lista circular
        }
        return ptr2;
    }

    // Método main del programa
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al programa de procesamiento de listas enlazadas.");

        // Poblar las listas con datos del usuario
        DoublyLinkedList ptr1Usuario = populatePtr1FromUser(scanner);
        CircularList ptr2Usuario = populatePtr2FromUser(scanner); // ptr2Usuario es del tipo CircularList

        // Procesar las listas
        processLists(ptr1Usuario, ptr2Usuario);

        System.out.println("\nFin del programa.");
        //scanner.close(); // Cerrar el scanner
    }
}