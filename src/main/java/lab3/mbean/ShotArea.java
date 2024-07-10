package lab3.mbean;

import lab3.entity.Shot;
import lab3.utils.AreaHitChecker;
import java.util.List;

public class ShotArea implements ShotAreaMBean {
    private List<Shot> shots;

    public ShotArea(List<Shot> shots) {
        this.shots = shots;
    }

    @Override
    public double getArea() {
        double area = 0;

        for (Shot shot : shots) {
            double r = shot.getR();
            if (AreaHitChecker.isCircleZone(shot)) {
                area += Math.PI * Math.pow(r, 2) / 4;  // Четверть круга
            } else if (AreaHitChecker.isRectangleZone(shot)) {
                area += r * r / 2;  // Прямоугольник
            } else if (AreaHitChecker.isTriangleZone(shot)) {
                area += r * r / 2;  // Треугольник
            }
        }
        return area;
    }
}
