public class Node {
    int data;
    int fila = -1;
    int columna = -1;
    Node next;

    public Node(int theData) {
        this.data = theData;
        this.next = null;
    }

    public Node(int fila, int columna, int data){
        this.fila = fila;
        this.columna = columna;
        this.data = data;
    }
}
