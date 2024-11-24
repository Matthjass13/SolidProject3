package graphStructure;

/**
 * This class will be used as a data structure
 * to store all edges incident with a given vertex
 * in our graph.
 * @author Matthias Gaillard
 * @since 24.11.2024
 */
public class File {

    private int length;
    private Road first;
    private Road last;

    public File() {
        length = 0;
        first = null;
        last = null;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Road getFirst() {
        return first;
    }

    public void setFirst(Road first) {
        this.first = first;
    }

    public Road getLast() {
        return last;
    }

    public void setLast(Road last) {
        this.last = last;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public void add(Road road) {
        if (isEmpty())
            first = road;
        else
            last.setNext(road);
        last = road;
        ++length;
    }

    /**
     * This method deletes the first Road of the file.
     * @return first Road of the file
     */
    public Road remove() {
        if (length == 0)
            return null;

        Road start = first;
        first = first.getNext();

        if (length == 1)
            last = null;
        else
            start.setNext(null);

        --length;
        return first;
    }


    public void concat(File file2) {
        if (file2.isEmpty())
            return;

        if (isEmpty())
            first = file2.getFirst();
        else
            last.setNext(file2.getFirst());

        last = file2.getLast();
        length += file2.getLength();

        file2.setLength(0);
        file2.setFirst(null);
        file2.setLast(null);
    }

    public void display() {
        Road current = first ;
        while (current != null) {
            System.out.print(current.toString()+ " ");
            current = current.getNext();
            System.out.println();
        }
        System.out.println();
    }

    public String toString() {
        String string = "";
        Road current = first;
        while (current != null) {
            string += current.toString() + " ";
            current = current.getNext();
        }
        return string;
    }

}
