package edu.eci.arsw.blueprints.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) throws BlueprintPersistenceException, BlueprintNotFoundException {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = app.getBean(BlueprintsServices.class);

        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Point[] pts2=new Point[]{new Point(0, 0),new Point(10, 10)};
        Point[] pts3=new Point[]{new Point(0, 0),new Point(10, 10)};
        Point[] pts4=new Point[]{new Point(0, 0),new Point(10, 10),new Point(10, 10),new Point(11, 10)};

        Blueprint bp=new Blueprint("John bien esccrito", "Jhon bien esccrito ",pts);
        Blueprint bp2=new Blueprint("Enrique", "thepaint2",pts2);
        Blueprint bp3=new Blueprint("Enrique", "thepaint3",pts3);
        Blueprint bp4=new Blueprint("Sergio", "thepaint4",pts4);

        bps.addNewBlueprint(bp);
        bps.addNewBlueprint(bp2);
        bps.addNewBlueprint(bp3);

        System.out.println("Get Blueprint: " + bps.getBlueprint("John bien esccrito", "Jhon bien esccrito "));
        System.out.println("Get All Blueprints: " + bps.getAllBlueprints());
        System.out.println("Get Blueprints By Author: " + bps.getBlueprintsByAuthor("Enrique"));
        bps.filter(bp4);

        System.out.println(bp4.getPoints());
    }
}
