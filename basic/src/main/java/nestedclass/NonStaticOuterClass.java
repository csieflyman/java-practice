package nestedclass;

/**
 * @author flyman
 */
public class NonStaticOuterClass {

    private static final int x = 1;
    private int y = 1;
    private static final Integer z = Integer.valueOf(1);

    class InnerClass {

        public void testInnerClass() {
            System.out.println("===== testInnerClassDefineStaticMember =====");
            testInnerClassDefineStaticMember();
            System.out.println("===== testInnerClassAccessStaticMember =====");
            testInnerClassAccessOuterClassStaticMember();
            System.out.println("===== testMemberShadowing =====");
            testMemberShadowing(10);
            System.out.println("===== testMethodNameConflict =====");
            testMethodNameConflict();
            System.out.println("===== testAccessFinalMember =====");
            testAccessFinalMember();
        }

        private static final int x = 2;
        //private static final Integer si = Integer.valueOf(1); // Compile Error!

        void testInnerClassDefineStaticMember() {
            System.out.println("Define static constant variable in innerClass is ok");
            System.out.println("Define static object reference in innerClass is illegal");
        }

        void testInnerClassAccessOuterClassStaticMember() {
            System.out.println("InnerClass can access outerClass's static object " + z);
        }

        void testMemberShadowing(int x) {
            System.out.println("inner class method parameter: x = " + x);
            System.out.println("inner class member: this.x = " + this.x);
            System.out.println("outer class member: NonStaticOuterClass.this.x = " + NonStaticOuterClass.this.x);
        }

        void testMethodNameConflict() {
            NonStaticOuterClass.this.testMethodNameConflict();
        }

        void testAccessFinalMember() {
            y = 2;
            System.out.println("We can change outerClass y value to " + y);
        }
    }

    private void testMethodNameConflict() {
        System.out.println("outer class method: NonStaticOuterClass.this.testMethodNameConflict()");
    }

    public void testLocalClass() {
        int x = 2;
        int y = 2;
        //static final Calendar nowTime = Calendar.getInstance();  static is not allowed here

        class LocalClass {

            private static final int x = 3;
            //private static final Integer si = Integer.valueOf(1); // Compile Error!

            private void test() {
                System.out.println("===== testLocalClassDefineStaticMember =====");
                testLocalClassDefineStaticMember();

                System.out.println("===== No testLocalClassAccessEnclosingMethodStaticMember case =====");
                System.out.println("===== because static is not allowed in enclosing method");

                System.out.println("===== testLocalClassAccessOuterClassStaticMember =====");
                testLocalClassAccessOuterClassStaticMember();

                System.out.println("===== testMemberShadowing =====");
                testMemberShadowing(10);

                System.out.println("===== No testMethodNameConflict case =====");
                System.out.println("===== because method is not allowed in enclosing method");

                System.out.println("===== testAccessFinalMember =====");
                testAccessFinalMember();
            }

            void testLocalClassDefineStaticMember() {
                System.out.println("Define static constant variable in LocalClass is ok");
                System.out.println("Define static object reference in LocalClass is illegal");
            }

            void testLocalClassAccessOuterClassStaticMember() {
                System.out.println("LocalClass can access outerClass's member z (not recommend) z = " + z);
            }

            void testMemberShadowing(int x) {
                System.out.println("inner class method parameter: x = " + x);
                System.out.println("inner class member: this.x = " + this.x);
                System.out.println("we can't access variable x in enclosing method if x is shadowed by LocalClass");
                System.out.println("outer class member: NonStaticOuterClass.this.x = " + NonStaticOuterClass.this.x);
            }

            void testAccessFinalMember() {
                //y = 3; // Compile Error!
                System.out.println("We can't modify y value in enclosing method block");
            }
        }

        // Use LocalClass before define it
        LocalClass lc = new LocalClass();
        lc.test();

    }

    interface AnonymousClass {
        void test();
    }

    public void testAnonymousClass() {
        AnonymousClass ac = new AnonymousClass() {
            @Override
            public void test() {
                System.out.println("The testing result of AnonymousClass is the same as LocalClass");
            }
        };
        ac.test();
    }
}
