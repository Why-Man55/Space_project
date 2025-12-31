public class Main {
    public static void main(String[] args) {
        Screen screen = new Screen(90, 30);
        GameContext.setScreen(screen);


        int frames = 50000;
        int frame = 0;

        for (int i = 0; i < 100; i++) {
            createRandomStar();
        }

        GameContext.addUI(new Text(20, 0, "Cosmos sky simulation", GameContext.getWidth() - 2 - 20));
        GameContext.addUI(new Text(0, 0, "v.0.0.1 beta", 20));
        GameContext.addUI(new Text(0, GameContext.getHeight() - 3, "   ", GameContext.getWidth() - 2));

        while (frames > frame) {
            System.out.print("\033[0;0H");
            System.out.print("\033[2J");

            if (Math.random() < 0.008) {
                GameContext.addMain(new Constellation());
            }
            if (Math.random() < 0.4) {
                createRandomParticle();
            }
            if (Math.random() < 0.05) {
                createRandomCometa();
            }
            if (Math.random() < 0.01) {
                GameContext.addMain(Constellation.collectByStar(5, 3f, 10f));
            }

            GameContext.update();
            screen.print();

            Clock.sleep(100);
            frame++;
        }

        System.out.print("\033[?1049l");
    }


    public static void createRandomParticle() {
        GameContext.addMain(new BlinkingParticle(
                (int)(Math.random() * GameContext.getWidth()),
                (int)(Math.random() * GameContext.getHeight())
        ));
    }

    public static void createRandomStar() {
        GameContext.addBackground(new Star(
                (int)(Math.random() * GameContext.getWidth()),
                (int)(Math.random() * GameContext.getHeight())
        ));
    }

    private static void createRandomCometa() {
        float vx = (float)(Math.random() * 1.5f + 0.5f);
        float vy = (float)(Math.random() * 1.5f + 0.5f);
        int x = 0, y = 0;

        switch ((int)(Math.random() * 4)) {
            case 0: x = 0; y = (int)(Math.random() * GameContext.getHeight()); break;
            case 1: x = (int)(Math.random() * GameContext.getWidth()); y = 0; break;
            case 2: x = GameContext.getWidth() - 1; y = (int)(Math.random() * GameContext.getHeight()); vx = -vx; break;
            case 3: x = (int)(Math.random() * GameContext.getWidth()); y = GameContext.getHeight() - 1; vy = -vy; break;
        }

        GameContext.addMain(new Cometa(x, y, new Vector2(vx, vy)));
    }
}