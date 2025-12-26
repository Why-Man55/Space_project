public class GameContext {
    private static Screen currentScreen;
    private static GameObjectList uiObjects = new GameObjectList();
    private static GameObjectList mainObjects = new GameObjectList();
    private static GameObjectList backgroundObjects = new GameObjectList();
    private static GameObjectList toAddUI = new GameObjectList();
    private static GameObjectList toAddMain = new GameObjectList();
    private static GameObjectList toAddBackground = new GameObjectList();

    public static int getWidth() {
        return currentScreen.w;
    }
    
    public static int getHeight() {
        return currentScreen.h;
    }

    public static boolean isInBounds(int x, int y) {
        return x >= 0 && x < getWidth() && y >= 0 && y < getHeight();
    }

    public static Point[][] getCanvas() {
        return currentScreen.canvas;
    }

    public static void addMain(GameObject obj) {
        toAddMain.add(obj);
    }

    public static void addUI(GameObject obj) {
        toAddUI.add(obj);
    }

    public static void addBackground(GameObject obj) {
        toAddBackground.add(obj);
    }

    public static void addParticle(Particle particle) {
        addMain(particle);
    }

    public static GameObjectList getObjects() {
        GameObjectList objs = new GameObjectList();
        objs.addAll(backgroundObjects);
        objs.addAll(mainObjects);
        objs.addAll(uiObjects);
        return objs;
    }

    public static Screen getScreen() {
        return currentScreen;
    }

    public static void setScreen(Screen screen) {
        currentScreen = screen;
    }

    public static void update() {
        toAddUI.forEach(obj -> uiObjects.add(obj));
        toAddMain.forEach(obj -> mainObjects.add(obj));
        toAddBackground.forEach(obj -> backgroundObjects.add(obj));

        toAddUI.clear();
        toAddMain.clear();
        toAddBackground.clear();

        currentScreen.refresh();

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