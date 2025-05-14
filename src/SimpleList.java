public class SimpleList extends List {

    @Override
    void addStack(int theData) {
        Node newNode = new Node(theData);
        if (first == null)
            first = newNode;
        else
            newNode.next = first;
        first = newNode;
    }

    @Override
    void addQueue(int theData) {
        Node newNode = new Node(theData);
        if (first == null) {
            first = newNode;
            last = first;
        } else {
            // Node aux = first;
            // while (aux.next != null) {
            // aux = aux.next;
            // }
            // aux.next = newNode;
            last.next = newNode;
            last = newNode;
        }
    }

    void addQueue(int fila, int columna, int valor){
        Node newNode = new Node(fila, columna, valor);
        if (first == null) {
            first = newNode;
            last = first;
        } else {
            // Node aux = first;
            // while (aux.next != null) {
            // aux = aux.next;
            // }
            // aux.next = newNode;
            last.next = newNode;
            last = newNode;
        }
    }

    @Override
    void traversal() {
        if (first == null)
            System.out.println("Empty list");
        else {
            System.out.println("Elementos en la lista:");
            Node aux = first;
            while (aux != null) {
                System.out.print(aux.data + "|");
                aux = aux.next;
            }
            System.out.println();
        }
    }

    void traversalMatriz(){
        if (first == null){
            System.out.println("Empty List");
        } else{
            Node aux = first;
            while (aux != null) {
                if (aux.fila <= -1 && aux.columna <= -1){
                    System.out.print("(no valido)");
                } else{
                    System.out.print("("+aux.fila+", "+aux.columna+", "+aux.data+") -> ");
                }
                aux = aux.next;
            }
            System.out.print("null\n");
        }
        
        
    }

    @Override
    void delete(int theData) {
        if (first != null) {
            Node antp = null;
            if (first.data == theData) {
                antp = first.next;
                first.next = null;
                first = antp;
            } else {
                Node p = first;
                while (p.next != null && p.data != theData) {
                    antp = p;
                    p = p.next;
                }
                if (p.data == theData) {
                    antp.next = p.next;
                    p.next = null;
                }
            }
        }
    }

    @Override
    Node search(int theData) {
        Node aux = first;
        while (aux != null) {
            if (aux.data == theData)
                return aux;
            else
                aux = aux.next;
        }
        return null;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

}
