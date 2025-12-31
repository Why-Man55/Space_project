public class CometaSpawnRule implements SpawnRule {
    private long lastTime = 0;
    private long delay = 2000;

    CometaSpawnRule() {
        SpawnRegistry.register(this);
    }

    @Override
    public void update() {
        long time = System.currentTimeMillis();
        if (time - lastTime > delay) {
            GameContext.add(new Cometa(0, (float)(Math.random()*GameContext.getHeight()), Cometa.max_v * (float) Math.random(), Cometa.max_v * (float)Math.random()));
            lastTime = time;
        }
    }
}
