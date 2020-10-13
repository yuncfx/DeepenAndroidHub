package test.shane.multithread;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author shane
 */
public class VisualComponent {
    private final List<KeyListener> mKeyListeners = new CopyOnWriteArrayList<>();
    private final List<MouseListener> mMouseListeners = new CopyOnWriteArrayList<>();

    public void addKeyListener(KeyListener listener) {
        mKeyListeners.add(listener);
    }

    public void addMouseListener(MouseListener listener) {
        mMouseListeners.add(listener);
    }

    public void removeKeyListener(KeyListener listener) {
        mKeyListeners.remove(listener);
    }

    public void removeMouseListener(MouseListener listener) {
        mMouseListeners.remove(listener);
    }
}
