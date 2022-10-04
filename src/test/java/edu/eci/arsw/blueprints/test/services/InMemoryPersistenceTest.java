/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.test.services;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.impl.InMemoryBlueprintPersistence;

import java.util.HashSet;
import java.util.Set;

import edu.eci.arsw.blueprints.services.BlueprintsServices;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.*;

/**
 *
 * @author hcadavid
 */
public class InMemoryPersistenceTest {

    @Test
    public void saveNewAndLoadTest() throws BlueprintPersistenceException, BlueprintNotFoundException{
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts0=new Point[]{new Point(40, 40),new Point(15, 15)};
        Blueprint bp0=new Blueprint("mack", "mypaint",pts0);

        ibpp.saveBlueprint(bp0);

        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);

        ibpp.saveBlueprint(bp);

        assertNotNull("Loading a previously stored blueprint returned null.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()));

        assertEquals("Loading a previously stored blueprint returned a different blueprint.",ibpp.getBlueprint(bp.getAuthor(), bp.getName()), bp);

    }


    @Test
    public void saveExistingBpTest() {
        InMemoryBlueprintPersistence ibpp=new InMemoryBlueprintPersistence();

        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);

        try {
            ibpp.saveBlueprint(bp);
        } catch (BlueprintPersistenceException ex) {
            fail("Blueprint persistence failed inserting the first blueprint.");
        }

        Point[] pts2=new Point[]{new Point(10, 10),new Point(20, 20)};
        Blueprint bp2=new Blueprint("john", "thepaint",pts2);

        try{
            ibpp.saveBlueprint(bp2);
            fail("An exception was expected after saving a second blueprint with the same name and autor");
        }
        catch (BlueprintPersistenceException ex){

        }

    }

    @Test
    public void deberiaObtenerBlueprintsPorAutor() throws BlueprintPersistenceException, BlueprintNotFoundException {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = app.getBean(BlueprintsServices.class);
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp=new Blueprint("john", "thepaint",pts);
        bps.addNewBlueprint(bp);
        Point[] pts2=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp2=new Blueprint("john", "thepaint2",pts);
        bps.addNewBlueprint(bp2);
        Point[] pts3=new Point[]{new Point(0, 0),new Point(10, 10)};
        Blueprint bp3=new Blueprint("john", "thepaint3",pts);
        bps.addNewBlueprint(bp3);
        Set<Blueprint> set = new HashSet<>();
        set.add(bp);
        set.add(bp2);
        set.add(bp3);
        assertEquals(bps.getBlueprintsByAuthor("john"), set);
    }

    @Test
    public void deberiaObtenerBlueprintsPorAutorYNombre() throws BlueprintPersistenceException, BlueprintNotFoundException {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = app.getBean(BlueprintsServices.class);
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp=new Blueprint("John bien esccrito", "Jhon bien esccrito ",pts);
        bps.addNewBlueprint(bp);
        assertEquals(bps.getBlueprint("John bien esccrito","Jhon bien esccrito "), bp);
    }

    @Test
    public void deberiaFuncionarFiltroRedundante() throws BlueprintNotFoundException {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = app.getBean(BlueprintsServices.class);
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10),new Point(10, 10),new Point(11, 10)};
        Blueprint bp=new Blueprint("John bien esccrito", "Jhon bien esccrito ",pts);
        bps.filter(bp);
        assertEquals(bp.getPoints().toString(), "[0 0, 10 10, 11 10]");
    }

    @Test
    public void deberiaFuncionarFiltroMuestreo() throws BlueprintNotFoundException {
        ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
        BlueprintsServices bps = app.getBean(BlueprintsServices.class);
        Point[] pts=new Point[]{new Point(0, 0),new Point(10, 10),new Point(10, 10),new Point(11, 10)};
        Blueprint bp=new Blueprint("John bien esccrito", "Jhon bien esccrito ",pts);
        bps.filter(bp);
        assertEquals(bp.getPoints().toString(), "[0 0, 10 10]");
    }

}
