package inheritance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author flyman
 */
public class B extends A {

    private String privateName = "B";

    protected String protectedName = "B";

    public String publicName = "B";

    @Override
    public Y overridingReturnCovariance() {
        return new Y(); // return subtype
    }

    /** Overriding: method arguments is invariance, can't be superType or subType **/
    @Override
    public void overriding(X x) {
        System.out.println("B");
    }

    @Override
    public void overridingProtectedToPublic() {
    }

    @Override
    public void overridingPrimitive(int i) {
    }

   // @Override
    public void overridingPrimitive(Integer i) {
        // Overloading
    }

    // @Override
    public void overridingSuperType(X x) {
        // Overriding: method arguments is invariance, can't be superType
    }

    //@Override
    public void overridingSubType(Y y) {
        // Overriding: method arguments is invariance, can't be subType
    }

    //@Override
    private void overridingPrivate() {
    }

    //@Override
   // public final void overridingFinal() {}

    //@Override
    public static void overridingStatic() {
    }

    /**==========  Overloading ==========**/

    public void overloadingSuperSubType(Y y) {
    }

    public void overloadingSuperSubType(X x) {
    }

}
