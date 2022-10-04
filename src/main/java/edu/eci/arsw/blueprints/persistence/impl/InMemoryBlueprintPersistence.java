/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.model.Point;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author hcadavid
 */
@Component
public class InMemoryBlueprintPersistence implements BlueprintsPersistence{

    private final ConcurrentHashMap<Tuple<String,String>,Blueprint> blueprints=new ConcurrentHashMap<>();

    public InMemoryBlueprintPersistence() {
        //load stub data
        Point[] pts=new Point[]{new Point(140, 140),new Point(115, 115)};
        Blueprint bp=new Blueprint("_authorname_", "_bpname_ ",pts);

        Point[] pts2=new Point[]{new Point(1, 1),new Point(10, 10)};
        Blueprint bp2=new Blueprint("enrique", "lala",pts2);
        Point[] pts3=new Point[]{new Point(20, 50),new Point(11, 21)};
        Blueprint bp3=new Blueprint("enrique", "thepaint3",pts3);

        Point[] pts4=new Point[]{new Point(30, 12),new Point(11, 20)};
        Blueprint bp4=new Blueprint("sergio", "thepaint4",pts4);

        blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        blueprints.put(new Tuple<>(bp2.getAuthor(),bp2.getName()), bp2);
        blueprints.put(new Tuple<>(bp3.getAuthor(),bp3.getName()), bp3);
        blueprints.put(new Tuple<>(bp4.getAuthor(),bp4.getName()), bp4);
    }    
    
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {
        if (blueprints.containsKey(new Tuple<>(bp.getAuthor(),bp.getName()))){
            throw new BlueprintPersistenceException("The given blueprint already exists: "+bp);
        }
        else{
            blueprints.put(new Tuple<>(bp.getAuthor(),bp.getName()), bp);
        }        
    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return blueprints.get(new Tuple<>(author, bprintname));
    }

    @Override
    public Set<Blueprint> getBluePrintByAuthor(String author) throws BlueprintNotFoundException {
        Set<Tuple<String,String>> tuplas = blueprints.keySet();
        Set<Blueprint> bluePrints2 = new HashSet<>();
        for(Tuple<String,String> t: tuplas){
            if (Objects.equals(t.getElem1(), author)){
                bluePrints2.add(blueprints.get(t));
            }
        }
        return bluePrints2;
    }

    @Override
    public Set<Blueprint> getAllBlueprints() {
        Set<Tuple<String,String>> tuplas = blueprints.keySet();
        Set<Blueprint> bluePrints2 = new HashSet<>();
        for(Tuple<String,String> t: tuplas){
            bluePrints2.add(blueprints.get(t));
        }
        return bluePrints2;
    }

}
