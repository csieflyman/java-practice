package enumclass;

/**
 * @author flyman
 */
public enum Action implements Command{

    START(100){
        @Override // Value-Specific Class Bodies
        public void individualExecute() {
            System.out.println("start");
        }
    },
    STOP(200){
        @Override
        public void individualExecute() {
            System.out.println("stop");
        }
    };

    public int getValue() {
        return value;
    }

    private int value;

    private Action(int value) {
        this.value = value;
    }

    @Override
    public void switchExecute() {
        switch(this) {
            case START:
                System.out.println("start");
                break;
            case STOP:
                System.out.println("stop");
                break;
        }
    }
}
