package server.graphStructure;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will store snapshots of the state
 * of paths, to retrieve them later.
 * It is a participant of the memento pattern.
 * @see Path
 * @author Matthias Gaillard
 * @since 07.12.2024
 */
public class PathCaretaker {

    private Path path;
    private List<Path.Memento> history = new ArrayList<>();

    public PathCaretaker(Network network) {
        this.path = path;
    }

    public void save() {
        history.add(path.save());
    }
    public void undo(){
        (history.remove(history.size()-1)).restore();
    }

}
