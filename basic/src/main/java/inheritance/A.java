package inheritance;

/**
 * @author flyman
 */
public class A {

    private String privateName = "A";

    protected String protectedName = "A";

    public String publicName = "A";

    public X overridingReturnCovariance() {
        return new X();
    }

    public void overriding(X x) {
        System.out.println("A");
    }

    protected void overridingProtectedToPublic() {
    }

    public void overridingPrimitive(int i) {
    }

    public void overridingSuperType(Y y) {
    }

    public void overridingSubType(X x) {
    }

    private void overridingPrivate() {
    }

    public final void overridingFinal() {
    }

    public static void overridingStatic() {
    }
}
