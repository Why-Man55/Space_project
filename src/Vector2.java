public class Vector2 {
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