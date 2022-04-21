package my.snake;

import java.util.*;

class PointsOfLets {
    private static PointsOfLets pointsOfLets = new PointsOfLets();
    private PointsOfLets(){};
    private Set<Point> points = new HashSet<>();

    static PointsOfLets getInstance(){
        return pointsOfLets;
    }

    void addLetPoint(Point point){
        points.add(point);
    }

    void addLetPoint(List<Point> points){
        points.forEach(this::addLetPoint);
    }

    boolean contains(Point point){
        return points.contains(point);
    }

    boolean contains(List<Point> points){
        return points.stream().allMatch(this::contains);
    }

    void rmLetPoint(Point point){
        points.remove(point);
    }

    void rmLetPoint(List<Point> points){
        points.forEach(this::rmLetPoint);
    }

    void newObject(){ points = new HashSet<>(); }
}
