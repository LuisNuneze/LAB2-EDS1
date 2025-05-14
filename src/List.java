public abstract class List {
    protected Node first;
    protected Node last;


    abstract void addStack(int theData);

    abstract void addQueue(int theData);

    abstract void traversal();

    abstract Node search(int theData);

    abstract void delete(int theData);

}
