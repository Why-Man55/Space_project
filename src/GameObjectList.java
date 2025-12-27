import java.util.function.Consumer;

public class GameObjectList {
    private Node head, tail;
    private int size;
    
    private class Node {
        GameObject data;
        Node next, prev;
        
        Node(GameObject data) {
            this.data = data;
        }
    }
    
    public void add(GameObject obj) {
        Node newNode = new Node(obj);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    public void addAll(GameObjectList otherList) {
        otherList.forEach(obj -> this.add(obj));
    }
    
    public boolean remove(GameObject obj) {
        Node current = head;
        while (current != null) {
            if (current.data == obj) {
                removeNode(current);
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private void removeNode(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next;
        }
        
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev;
        }
        
        size--;
    }
    
    public void forEach(Consumer<GameObject> action) {
        Node current = head;
        while (current != null) {
            action.accept(current.data);
            current = current.next;
        }
    }
    
    public int size() { return size; }

    public void clear() {
        Node current = head;
        while (current != null) {
            Node next = current.next;
            current.data = null;
            current.next = null;
            current.prev = null;
            current = next;
        }
        head = null;
        tail = null;
        size = 0;
    }
}