public class DoublyLinkedList<T> {
    private class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        private Node(T data, Node<T> next, Node<T> prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
        private Node(T data) {
            this(data, null, null);
        }
    }
}