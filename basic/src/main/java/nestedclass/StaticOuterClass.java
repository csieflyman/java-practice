package nestedclass;

/**
 * @author flyman
 */
public class StaticOuterClass {

    private int w = 1;
    public static final int x = 1;
    public static final Integer z1 = Integer.valueOf(1);

    static class StaticInnerClass {

        public static final int x = 2;
        private int y = 2;
        public static final Integer z2 = Integer.valueOf(2);

        public void test(){
            System.out.println("===== testInnerClassDefineStaticMember =====");
            testInnerClassDefineStaticMember();
            System.out.println("===== testInnerClassAccessOuterClassMember =====");
            testInnerClassAccessOuterClassMember();
            System.out.println("===== testMemberShadowing =====");
            testMemberShadowing(10);
            System.out.println("===== testInnerClassStaticMethodCallOuterClassMethod =====");
            testInnerClassStaticMethodCallOuterClassMethod();
            System.out.println("===== testInnerClassNonStaticCallOuterClassMethod =====");
            testInnerClassNonStaticCallOuterClassMethod();
        }

        void testInnerClassDefineStaticMember() {
            System.out.println("Define static constant variable in innerClass is ok");
            System.out.println("Define static object reference in innerClass is ok");
        }

        void testInnerClassAccessOuterClassMember() {
            System.out.println("InnerClass can access outerClass's static object " + z1);
            System.out.println("InnerClass cannot access outerClass's non-Static w because w cannot reference from static context");
           // System.out.println(w); // Compile Error! Non-Static y cannot reference from static context
        }

        void testMemberShadowing(int x) {
            System.out.println("inner class method parameter: x = " + x);
            System.out.println("inner class member: this.x = " + this.x);
            System.out.println("outer class member: StaticOuterClass.x = " + StaticOuterClass.x);
        }

        void setYValue(int y) {
            this.y = y;
            System.out.println("inner class member: this.y = " + this.y);
        }

        static void testInnerClassStaticMethodCallOuterClassMethod() {
            System.out.println("InnerClass static/non-static both can call outerClass's static method");
            StaticOuterClass.testStaticMethod();
            //StaticOuterClass.testNonStaticMethod();//Compile Error! cannot reference from static context
            //StaticOuterClass.this.testNonStaticMethod();//Compile Error! cannot reference from static context
        }

        void testInnerClassNonStaticCallOuterClassMethod() {
            System.out.println("InnerClass static/non-static both can call outerClass's static method");
            StaticOuterClass.testStaticMethod();
            //StaticOuterClass.testNonStaticMethod();//Compile Error! cannot reference from static context
            //StaticOuterClass.this.testNonStaticMethod();//Compile Error! cannot reference from static context
        }
    }

    static void testStaticMethod() {

    }

    void testNonStaticMethod() {

    }
}
