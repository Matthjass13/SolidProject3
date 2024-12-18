package client.panels;

import java.util.Stack;

/**
 * This class is the caretaker of the memento pattern
 * applied to the AppPanel.
 * When a user searches a path, its source and end node are added here.
 * The data can be restored later to display the path again.
 *  @see AppPanel
 *  @author Matthias Gaillard
 *  @since 18.12.2024
 */
public class PathCaretaker {
    Stack<AppPanel.Memento> snapshots = new Stack<>();
    public void save(AppPanel.Memento state) {
        snapshots.push(state);
    }
    public void revert(AppPanel originator) {
        if (!snapshots.isEmpty())
            originator.setMemento(snapshots.pop());
        else
            System.out.println("Cannot undo !");
    }

}
