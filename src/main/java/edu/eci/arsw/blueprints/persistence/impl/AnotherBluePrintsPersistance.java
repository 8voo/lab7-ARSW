package edu.eci.arsw.blueprints.persistence.impl;

import edu.eci.arsw.blueprints.model.Blueprint;
import edu.eci.arsw.blueprints.persistence.BlueprintNotFoundException;
import edu.eci.arsw.blueprints.persistence.BlueprintPersistenceException;
import edu.eci.arsw.blueprints.persistence.BlueprintsPersistence;

import java.util.Set;

public class AnotherBluePrintsPersistance implements BlueprintsPersistence {
    @Override
    public void saveBlueprint(Blueprint bp) throws BlueprintPersistenceException {

    }

    @Override
    public Blueprint getBlueprint(String author, String bprintname) throws BlueprintNotFoundException {
        return null;
    }

    @Override
    public Set<Blueprint> getBluePrintByAuthor(String author) throws BlueprintNotFoundException {
        return null;
    }

    @Override
    public Set<Blueprint> getAllBlueprints() {
        return null;
    }
}
