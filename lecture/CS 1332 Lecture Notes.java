CS 1332 Lecture Notes:

1/9/2019 (Lecture 2)

    Public vs. Private:

        - Parent classes/other instances can see private variables

    Arrays:

        - Store objects, primitives, references, etc.
        - If you know the index, you can access the array in O(1) time
        - Cost of resizing the array is O(n) time, because all elements from old array need to be copied over
        - LOOK AT BIG O SLIDES
        - Adding at the front occurs in O(n) time
        - Benefical to have 'size' and 'capacity' varibles
                - 'size' is the current number of elements
                - 'capacity' is the max number of elements

    ArrayList:

        - Size automatically initialized to 10
        - When it reaches capacity, size (capacity) becomes 2n + 1
        - Cant directly store primitives, must use wrapper class
        - "Ameretized"? Adding when capacity is reached requires a resize (O(n)), but the add itself after the resize occurs in O(1) time

1/11/19 (Lecture 3)

    Arrays:

        - Static memeory allocation
        - Flexible with datatypes
        - Must write methods

    ArrayLists:

        - Dynamic data structure
        - An ADT (Abstract Data Structures), really a List backed by an array
        - Need wrapper classes for primatives
        - Initial capacity of 10
        - Methods provided for you

    LinkedList:

        - Nodes = Objects, pointer(s)

        - Singly Linked List:

            - Head reference
            - When adding a new node after the head, have the new head point to its next node, then
              have the head point to the new node. Happens in O(1)
            - Adding to the back is O(n)
            - Removing from front is O(1), removing from back is O(n)


        public class SinglyLinkedList {
            private Node head;
            public SLL() {
                head = null;
            }
        }

        private class Node {
            private int data;
            private Node next;
            private Node(int data, Node next) {
                this.data = data;
                this.next = next;
            }
            private Node(int data) {
                Node(data, null);
            }
            public String toString() {
                return Integer.toString(data);
            }
        }

1/14/19 (Lecture 4)

    public class SinglyLinkedList {

            private Node head;
            public SLL() {
                head = null;
            }

            public void addToFront(int data) {}

            public String toString() {
                String answer = "";
                Node current = head;
                while (current != null) {
                    answer += current + "-";
                    current = current.next;
                }
                return answer;
            }

            public void addToBack(int data) {
                Node newnode = new Node(data);
                if (head == null) {
                    head = newnode;
                } else {
                    Node curr = head;
                    while (curr.next != null) {
                        curr = curr.next;
                    }
                    curr.next = newnode;
                }
            }
        }

        private class Node {
            private int data;
            private Node next;
            private Node(int data, Node next) {
                this.data = data;
                this.next = next;
            }
            private Node(int data) {
                Node(data, null);
            }
            public String toString() {
                return Integer.toString(data);
            }
        }

