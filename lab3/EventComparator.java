import java.util.Comparator;

class EventComparator implements Comparator<Event> {
    @Override 
    public int compare(Event e1, Event e2) {
        if (e1.getTime() < e2.getTime()) {
            return -1;
        } else if (e1.getTime() > e2.getTime()) {
            return 1;
        } else if (e1.getCust().getID() < e2.getCust().getID()) {
            return -1;
        } else if (e1.getCust().getID() > e2.getCust().getID()) {
            return 1;
        } else {
            return 0;
        }
    }
}

