class Constellation extends Poligon {
    Star[] stars;
    Text text;
    long time0;
    long timedelta;

    static ConstellationS ursaMajor = new ConstellationS(new Star[]{
            new Star(0, 0), new Star(0, 2), new Star(2, 3), new Star(3, 4),
            new Star(4, 5), new Star(5, 6), new Star(7, 7)}, "Большая Медведица");

    static ConstellationS cassiopeia = new ConstellationS(new Star[]{
            new Star(2, 0), new Star(1, 1), new Star(0, 2),
            new Star(1, 3), new Star(3, 4)}, "Царица на троне");

    static ConstellationS cygnus = new ConstellationS(new Star[]{
            new Star(0, 2), new Star(2, 1), new Star(4, 0),
            new Star(6, 1), new Star(7, 3)}, "Лебедь в полете");

    static ConstellationS scorpius = new ConstellationS(new Star[]{
            new Star(0, 5), new Star(1, 4), new Star(2, 3),
            new Star(3, 2), new Star(4, 1), new Star(3, 0)}, "Скорпион");

    static ConstellationS lyra = new ConstellationS(new Star[]{
            new Star(2, 1), new Star(1, 2),
            new Star(0, 1), new Star(1, 0)}, "Лира");

    static ConstellationS canisMajor = new ConstellationS(new Star[]{
            new Star(3, 4), new Star(1, 3), new Star(0, 2),
            new Star(2, 1), new Star(3, 0)}, "Большой Пес");

    static ConstellationS andromeda = new ConstellationS(new Star[]{
            new Star(0, 0), new Star(4, 4)}, "Андромеда");

    static ConstellationS gemini = new ConstellationS(new Star[]{
            new Star(0, 1), new Star(0, 3), new Star(1, 0),
            new Star(1, 4), new Star(2, 2)}, "Близнецы");

    static ConstellationS taurus = new ConstellationS(new Star[]{
            new Star(0, 0), new Star(1, 1), new Star(2, 2),
            new Star(3, 1), new Star(4, 0)}, "Телец");

    static ConstellationS crux = new ConstellationS(new Star[]{
            new Star(0, 2), new Star(1, 1),
            new Star(2, 2), new Star(1, 1)}, "Южный Крест");

    static ConstellationS[] STARS =  {
            ursaMajor, cassiopeia, cygnus, scorpius, lyra,
            canisMajor, andromeda, gemini, taurus, crux,
    };

    {
        this.time0 = System.currentTimeMillis();
        this.timedelta = 5000;
    }

    Constellation(Star[] stars, String type) {
        super(convertStarsToVectors(stars), type);
        this.stars = new Star[stars.length];
        for (int i = 0; i < stars.length; i++) {
            this.stars[i] = new Star((int)stars[i].x, (int)stars[i].y);
        }
    }

    Constellation(Star[] stars) {
        this(stars, "BLINK");
    }

    Constellation() {
        ConstellationS original = STARS[(int)(Math.random() * STARS.length)];
        Star[] stars = original.stars;
        this.stars = new Star[original.stars.length];
        for (int i = 0; i < original.stars.length; i++) {
            this.stars[i] = new Star((int)stars[i].x, (int)stars[i].y);
        }
        this.type = "BLINK";
        this.resize(10 + 2 + 2, 0);
        this.text = new Text(2 + (int)this.x + (int)(Vector2.maxX(this.convertStarsToVectors(this.stars)) - Vector2.minX(this.convertStarsToVectors(this.stars))), (int)this.y, original.description, Math.min(original.description.length(), 10));
    }

    public void resize() {
        int x, y, maxX, maxY;
        Vector2[] vs = this.convertStarsToVectors(this.stars);
        maxX = (int)(Vector2.maxX(this.convertStarsToVectors(this.stars)) - Vector2.minX(this.convertStarsToVectors(this.stars)));
        maxY = (int)Vector2.maxY(vs);
        this.x = (int)(Math.random() * (GameContext.getWidth() - maxX));
        this.y = (int)(Math.random() * (GameContext.getWidth() - maxY));
        for (Star s: this.stars) {
            s.x += this.x;
            s.y += this.y;
        }
    }

    public void resize(int xx, int yy) {
        int x, y, maxX, maxY;
        Vector2[] vs = this.convertStarsToVectors(this.stars);
        maxX = (int)(Vector2.maxX(this.convertStarsToVectors(this.stars)) - Vector2.minX(this.convertStarsToVectors(this.stars)));
        maxY = (int)Vector2.maxY(vs);
        this.x = (int)(Math.random() * (GameContext.getWidth() - maxX - xx));
        this.y = (int)(Math.random() * (GameContext.getHeight() - maxY - yy));
        for (Star s: this.stars) {
            s.x += this.x;
            s.y += this.y;
        }
    }

    public static Vector2[] convertStarsToVectors(Star[] stars) {
        Vector2[] vectors = new Vector2[stars.length];
        for (int i = 0; i < stars.length; i++) {
            vectors[i] = new Vector2(stars[i].x, stars[i].y);
        }
        return vectors;
    }

    private static Star[] getAllStars() {
        GameObjectList objs = GameContext.getObjects();

        final int[] count = {0};
        objs.forEach(obj -> {
            if (obj instanceof Star) {
                count[0]++;
            }
        });

        if (count[0] == 0) {
            return new Star[0];
        }

        Star[] stars = new Star[count[0]];
        final int[] index = {0};
        objs.forEach(obj -> {
            if (obj instanceof Star) {
                stars[index[0]++] = (Star) obj;
            }
        });

        return stars;
    }

    public static Constellation collectByStar(int maxStars, float minDistance, float maxDistance) {
        Star[] allStars = getAllStars();
        if (allStars.length == 0) {
            return null;
        }
        Star initStar = allStars[(int)(Math.random() * allStars.length)];
        Vector2 center = new Vector2(initStar.x, initStar.y);

        Star[] collected = new Star[Math.min(maxStars, allStars.length)];
        collected[0] = initStar;
        int collectedCount = 1;

        for (int i = 0; i < allStars.length && collectedCount < maxStars; i++) {
            Star star = allStars[i];
            if (star == initStar) continue;

            Vector2 starPos = new Vector2(star.x, star.y);
            float distance = center.distanceTo(starPos);

            if (distance >= minDistance && distance <= maxDistance) {
                collected[collectedCount++] = star;
            }
        }

        if (collectedCount >= 3) {
            Star[] finalStars = new Star[collectedCount];
            for (int i = 0; i < collectedCount; i++) {
                finalStars[i] = collected[i];
            }
            return new Constellation(finalStars);
        }

        return null;
    }

    public void render() {
        if (this.text != null) {
            this.text.render();
        }

        GameContext.getScreen().drawLines(convertStarsToVectors(this.stars), this.type);

        if (System.currentTimeMillis() - this.time0 > timedelta) {
            throw new RuntimeException("as");
        }
    }
}
