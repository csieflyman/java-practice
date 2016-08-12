package reflection;

/**
 * @author flyman
 */
public class Bird implements Fly {

    @Override
    public String fly(String a, String b) {
        System.out.println("bird fly...");
        return "bird result";
    }
}
