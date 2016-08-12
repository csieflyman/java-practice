package nestedclass;

/**
 * @author flyman
 */
public class Main {

    public static void main(String[] args) {
        NonStaticOuterClass nsoc = new NonStaticOuterClass();

        System.out.println("===== Start InnerClass Test =====");
        NonStaticOuterClass.InnerClass ic = nsoc.new InnerClass();
        ic.testInnerClass();
        System.out.println("===== End InnerClass Test =====");

        System.out.println("===== Start LocalClass Test =====");
        nsoc.testLocalClass();
        System.out.println("===== End LocalClass Test =====");

        System.out.println("===== Start AnonymousClass Test =====");
        nsoc.testAnonymousClass();
        System.out.println("===== End AnonymousClass Test =====");

        System.out.println("===== Start StaticNestedClass Test =====");
        StaticOuterClass.StaticInnerClass sic = new StaticOuterClass.StaticInnerClass();
        sic.test();
        StaticOuterClass.StaticInnerClass sic2 = new StaticOuterClass.StaticInnerClass();
        sic2.setYValue(3);
        sic2.setYValue(4);
        System.out.println("===== End StaticNestedClass Test =====");
    }
}
