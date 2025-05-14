public class SimpleDoubleLinkedList extends DoubleList {

    @Override
    void addStack(int theData) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addStack'");
    }

    @Override
    void addQueue(int theData) {
        DoubleNode newNode = new DoubleNode(theData);
        if (first == null) {
            first = newNode;
            last = first;
        } else {
            last.setRight(newNode);
            newNode.setLeft(last);
            last = newNode;
        }
    }

    @Override
    void traversalRight() {
        if (first == null)
            System.out.println("Empty list");
        else {
            System.out.println("Elementos en la lista:");
            DoubleNode aux = first;
            while (aux != null) {
                System.out.print(aux.getData() + "|");
                aux = aux.getRight();
            }
            System.out.println();
        }
    }
    @Override
    void traversalLeft() {
        if (first == null)
            System.out.println("Empty list");
        else {
            System.out.println("Elementos en la lista:");
            DoubleNode aux = last;
            while (aux != null) {
                System.out.print(aux.getData() + "|");
                aux = aux.getLeft();
            }
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
        if (first != null) {
            DoubleNode p = first;
            if (p.getData() == theData) {
                if (p != last) {
                    first = p.getRight();
                    p.getRight().setLeft(null);
                    p.setRight(null);
                }else{
                    first = null;
                    last = null;
                }
            }else{
                if (theData == last.getData()) {
                    last = last.getLeft();
                    last.getLeft().setRight(null);
                    last.setLeft(null);
                }else{
                    p = p.getRight();
                    while(p!=last && p.getData() != theData){
                        p = p.getRight();
                    }
                    if (p.getData() == theData) {
                        p.getRight().setLeft(p.getLeft());
                        p.getLeft().setRight(p.getRight());
                        p.setLeft(null);
                        p.setRight(null);
                    }else{
                        System.out.println("Dato no encontrado!");
                    }
                }
            }
        }
        
    }

}
