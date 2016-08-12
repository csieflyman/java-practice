package enumclass;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * @author flyman
 */
public class Main {

    public static void main(String[] args) {
        Action[] actions = Action.values();
        for (Action action : actions) {
            action.individualExecute();
            action.switchExecute();
        }

        // show EnumSet, EnumMap
        EnumSet<Action> es = EnumSet.allOf(Action.class);
        EnumMap<Action, Integer> em = new EnumMap<>(Action.class);
        for (Action action : es) {
            em.put(action, action.getValue());
        }

    }
}
