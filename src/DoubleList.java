public abstract class DoubleList {
    protected DoubleNode first;
    protected DoubleNode last;
    protected DoubleNode left;
    protected DoubleNode right;


    abstract void addStack(int theData);

    abstract void addQueue(int theData);

    abstract void traversalRight();

    abstract void traversalLeft();

    abstract Node search(int theData);

    abstract void delete(int theData);

}
