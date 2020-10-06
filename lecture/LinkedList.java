import java.util.Iterator;

public class LinkedList implements Iterable<T> {
    private Node<T> head;

    public LinkedList() {
        head = null;
    }

    public Iterator<T> iterator() {
        return new LinkedLit(this);
    }

    private class Node<T> {
        private T data;
        private Node<T> next;

        private Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
        private Node(T data) {
            this(data, null);
        }
    }

    private class LinkedLit implements Iterator<T> {
        private Node<T> curr;
        private LinkedLit () {
            curr = head;
        }
        public boolean hasNext() {
            return curr != null;
        }
        public T next() {
            Node<T> tmp = null;
            if (hasNext()) {
                temp = curr;
                curr = curr.next;
                return tmp.data;
            } else {
                return null;
            }
        }
    }
}
