package generic;

import java.util.Objects;

/**
 * @author flyman
 */
public class Node<T> {

    private T value;

    public Node(T value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        else if (obj == this)
            return true;
        else if (obj instanceof Node<?>) { // wildcard
            Node<?> other = (Node<?>) obj;
            return other.value.equals(this.value);
        }
        return false;
    }
}
