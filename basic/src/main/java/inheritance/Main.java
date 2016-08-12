package inheritance;

/**
 * @author flyman
 */
public class Main {

    public static void main(String[] args) {
        A a = new B();
        System.out.println(a.protectedName); // A
        System.out.println(a.publicName);// A
        a.overriding(new X()); // B polymorphism, virtual method

        B b = new B();
        System.out.println(b.protectedName); // B
        System.out.println(b.publicName); // B
        b.overriding(new Y()); // B
    }
}
