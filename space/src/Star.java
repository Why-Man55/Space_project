class Star extends GameObject {
    static char[] STAR_SPITES = {'*', '`', '~', '-'};
    char sprite;
    long time0;
    long timedelta;

    Star(int x, int y) {
        this.x = x;
        this.y = y;
        this.time0 = System.currentTimeMillis();
        this.timedelta = 1000;
        this.sprite = STAR_SPITES[(int)(Math.random() * (STAR_SPITES.length - 1))];
    }

    public void render() {
        if (System.currentTimeMillis() - time0 > this.timedelta) {
            this.time0 = System.currentTimeMillis();
            this.timedelta = (long)(Math.random() * (1500 - 500) + 500);
            this.sprite = STAR_SPITES[(int)(Math.random() * (STAR_SPITES.length - 1))];
        }
        Point point = GameContext.getCanvas()[(int)this.y][(int)this.x];
        point.sprite = this.sprite;
        point.hasSprite = true;
    }
}