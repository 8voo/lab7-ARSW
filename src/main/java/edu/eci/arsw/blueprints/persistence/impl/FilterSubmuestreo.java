package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.FiltersPersitence;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilterSubmuestreo implements FiltersPersitence {
    @Override
    public Blueprint filterBlueprint(Blueprint bp) {
        List<Point> points = bp.getPoints();
        List<Point> newPoints = new ArrayList<Point>();
        for (int i = 0; i < points.size(); i++){
            if (i%2 == 0){
                newPoints.add(points.get(i));
            }
        }
        bp.setPoints(newPoints);
        return bp;
    }
}
