package lab3.utils;

import lab3.entity.Shot;

public class AreaHitChecker {
    public static boolean isHit(Shot aShot) {
        return isCircleZone(aShot) || isTriangleZone(aShot) || isRectangleZone(aShot);
    }

    public static boolean isRectangleZone(Shot aShot) {
        double x = aShot.getX();
        double y = aShot.getY();
        double r = aShot.getR();
        return  (x >= 0) && (y <= 0) && (x <= r) && (y >= (-1)*r/2);
    }

    public static boolean isCircleZone(Shot aShot) {
        double x = aShot.getX();
        double y = aShot.getY();
        double r = aShot.getR();
        return (x * x + y * y <= r  * r) && (x <= 0) && (y >= 0);
    }

    public static boolean isTriangleZone(Shot aShot) {
        double x = aShot.getX();
        double y = aShot.getY();
        double r = aShot.getR();
        return (x<=0) && (y<=0) && (y >= -x - r);
    }
}
