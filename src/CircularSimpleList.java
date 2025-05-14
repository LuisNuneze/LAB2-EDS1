public class CircularSimpleList extends List {

    @Override
    void addStack(int theData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addStack'");
    }

    @Override
    void addQueue(int theData) {
        Node newNode = new Node(theData);
        if (first == null) {
            first = newNode;
            last = first;
        } else {
            last.next = newNode;
            last = newNode;

        }
        last.next = first;
    }

    @Override
    void traversal() {
        if (first == null)
            System.out.println("Empty list");
        else {
            System.out.println("Elementos en la lista:");
            Node aux = first;
            do {
                System.out.print(aux.data + "|");
                aux = aux.next;
            } while (aux != first);
            System.out.println();
        }
    }

    @Override
    Node search(int theData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'search'");
    }

    @Override
    void delete(int theData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

}
