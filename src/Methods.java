public class Methods {
    public static SimpleList multiplicarMatriz(SimpleList list1, SimpleList list2, int dimension) {
        SimpleList listAux = new SimpleList();

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                int s = 0;

                // para cada k buscamos list1[i,k] y list2[k,j]
                for (int k = 0; k < dimension; k++) {
                    int valorF = 0, valorC = 0;

                    // recorremos list1 para (i,k)
                    Node nodo = list1.first;
                    while (nodo != null) {
                        if (nodo.fila == i && nodo.columna == k) {
                            valorF = nodo.data;
                            break;
                        }
                        nodo = nodo.next;
                    }

                    // recorremos list2 para (k,j)
                    nodo = list2.first;
                    while (nodo != null) {
                        if (nodo.fila == k && nodo.columna == j) {
                            valorC = nodo.data;
                            break;
                        }
                        nodo = nodo.next;
                    }

                    s += valorF * valorC;
                }

                if (s != 0) {
                    listAux.addQueue(i, j, s);
                }
            }
        }

        return listAux;
    }

}
