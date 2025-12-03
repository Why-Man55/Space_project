import java.util.function.Consumer;

class GameContext {
    private static GameObjectList uiObjects = new GameObjectList();
    private static GameObjectList mainObjects = new GameObjectList();
    private static GameObjectList backgroundObjects = new GameObjectList();
    private static GameObjectList toAddUI = new GameObjectList();
    private static GameObjectList toAddMain = new GameObjectList();
    private static GameObjectList toAddBackground = new GameObjectList();

    public static void addMain(GameObject obj) {
        toAddMain.add(obj);
    }

    public static void addUI(GameObject obj) {
        toAddUI.add(obj);
    }

    public static void addBackground(GameObject obj) {
        toAddBackground.add(obj);
    }

    public static GameObjectList getObjects() {
        GameObjectList objs = new GameObjectList();
        objs.addAll(backgroundObjects);
        objs.addAll(mainObjects);
        objs.addAll(uiObjects);
        return objs;
    }

    public static void update() {
        toAddUI.forEach(obj -> uiObjects.add(obj));
        toAddMain.forEach(obj -> mainObjects.add(obj));
        toAddBackground.forEach(obj -> backgroundObjects.add(obj));

        toAddUI.clear();
        toAddMain.clear();
        toAddBackground.clear();

        renderLayer(backgroundObjects);
        renderLayer(mainObjects);
        renderLayer(uiObjects);
    }

    public static void renderLayer(GameObjectList objs) {
        objs.forEach(obj -> {
            try {
                obj.render();
            } catch (Exception e) {
                objs.remove(obj);
            }
        });
    }
}

class Vector2 {
    float x, y;
    
    Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    Vector2 add(Vector2 other) {
        return new Vector2(this.x + other.x, this.y + other.y);
    }
    
    Vector2 subtract(Vector2 other) {
        return new Vector2(this.x - other.x, this.y - other.y);
    }
    
    Vector2 multiply(float scalar) {
        return new Vector2(this.x * scalar, this.y * scalar);
    }
    
    float length() {
        return (float)Math.sqrt(x * x + y * y);
    }
    
    Vector2 normalize() {
        float len = length();
        if (len == 0) return new Vector2(0, 0);
        return new Vector2(x / len, y / len);
    }
    
    float distanceTo(Vector2 other) {
        return this.subtract(other).length();
    }

    static float maxX(Vector2[] vectors) {
        float max = vectors[0].x;
        for (Vector2 v: vectors) {
            if (max <= v.x) {
                max = v.x;
            }
        }
        return max;
    }

    static float maxY(Vector2[] vectors) {
        float max = vectors[0].y;
        for (Vector2 v: vectors) {
            if (max <= v.y) {
                max = v.y;
            }
        }
        return max;
    }

    static float minX(Vector2[] vectors) {
        float min = vectors[0].x;
        for (Vector2 v: vectors) {
            if (min >= v.x) {
                min = v.x;
            }
        }
        return min;
    }

    static float minY(Vector2[] vectors) {
        float min = vectors[0].y;
        for (Vector2 v: vectors) {
            if (min >= v.y) {
                min = v.y;
            }
        }
        return min;
    }
}

class Clock {
    public static void sleep(long time) {
        long time0 = System.currentTimeMillis();
        while (System.currentTimeMillis() - time0 < time) {
        }
    }
}

abstract class GameObject {
    float x, y;
    abstract public void render();
}

class GameObjectList { //пока для отладки, далее перепишу без Consumer
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
            action.accept(current.data);g
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

public class Main {
    public static void main(String[] args) {

        int frames = 50000;
        int frame = 0;

        while (frames > frame) {

            GameContext.update();

            Clock.sleep(100);
            frame++;
        }
    }


    public static void createRandomParticle() {}

    public static void createRandomStar() {}

    private static void createRandomCometa() {}
}