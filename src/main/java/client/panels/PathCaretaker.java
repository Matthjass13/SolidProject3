package client.panels;

import java.util.Stack;

public class PathCaretaker {
    Stack<AppClientPanel.Memento> snapshots = new Stack<AppClientPanel.Memento>();
    public void save(AppClientPanel.Memento state) {
        snapshots.push(state);
    }
    public void revert(AppClientPanel originator) {
        if (!snapshots.isEmpty())
            originator.setMemento(snapshots.pop());
        else
            System.out.println("Cannot undo !");
    }
}
