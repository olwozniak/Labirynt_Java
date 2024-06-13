package Main;

import Main.ActionObservers.ActionObserver;
import Main.ActionObservers.FileReadObserver;

import java.util.ArrayList;
import java.util.HashMap;

public final class CustomEventManager {
    private static CustomEventManager instance = null;
    private final HashMap<EventType, ArrayList<ActionObserver>> registeredObservers;

    private CustomEventManager() {
        registeredObservers = new HashMap<>();
    }

    public static synchronized CustomEventManager getInstance() {
        if (instance == null) {
            instance = new CustomEventManager();
        }
        return instance;
    }

    public static void initialize() {
        getInstance();
    }

    public synchronized void registerObserver(EventType type, ActionObserver observer) {
        ArrayList<ActionObserver> observers = registeredObservers.get(type);
        if (observers == null) {
            observers = new ArrayList<>();
            registeredObservers.put(type, observers);
        }
        observers.add(observer);
    }

    public synchronized void callEvent(EventType type) {
        ArrayList<ActionObserver> observers = registeredObservers.get(type);
        if (observers != null) {
            for (ActionObserver observer : observers) {
                observer.call();
            }
        }
    }
}
