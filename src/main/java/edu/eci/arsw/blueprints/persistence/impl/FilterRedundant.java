package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.FiltersPersitence;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
public class FilterRedundant implements FiltersPersitence {
    @Override
    public Blueprint filterBlueprint(Blueprint bp) {
        List<Point> points = bp.getPoints();
        List<Point> newPoints = new ArrayList<Point>();
        int pointsx;
        int pointsy;
        int nextPointsx;
        int nextPointsy;
        if (!(points.size() <= 1 )) {
            for (int i = 0; i < points.size() - 1; i++) {
                pointsx = points.get(i).getX();
                pointsy = points.get(i).getY();
                nextPointsx = points.get(i + 1).getX();
                nextPointsy = points.get(i + 1).getY();
                if (!(pointsx == nextPointsx && pointsy == nextPointsy)) {
                    newPoints.add(points.get(i));
                }
                if (i == points.size() - 2) {
                    newPoints.add(points.get(i + 1));
                }
            }
            bp.setPoints(newPoints);
        }
        return bp;
    }
}
