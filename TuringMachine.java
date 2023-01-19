import java.util.*;


public class TuringMachine {

    private TapeNode tapeHead;
    private TapeNode leftmostNode;
    private TapeNode rightmostNode;
    // private int tapeLength;
    // private int outputWidth = 100;
    Map<DeltaIn,DeltaOut> delta;
    int state;
    int endState;
    final int DELAY = 0;
    final int INIT_OFFSET_LEFT = 10;
    final int INIT_OFFSET_RIGHT = 10;
    final int DYNAMIC_OFFSET_LEFT = 10;
    final int DYNAMIC_OFFSET_RIGHT = 10;

    public TuringMachine(int startState, int endState, Map<DeltaIn,DeltaOut> delta, String word) {
        this.state = startState;
        this.endState = endState;
        this.delta = delta;
        tapeHead = new TapeNode();
        leftmostNode = tapeHead;

        // write word on tape, from leftmost to rightmost
        char[] wordArr = word.toCharArray();
        TapeNode tmpNode = leftmostNode;
        for (char c : wordArr) {
            tmpNode.set(c);
            tmpNode = tmpNode.right();
        }
        rightmostNode = tmpNode.left();
        
        // add offset
        for (int i=0; i < INIT_OFFSET_LEFT; i++)
            leftmostNode = leftmostNode.left();
        for (int i=0; i < INIT_OFFSET_RIGHT; i++)
            rightmostNode = rightmostNode.right();
    }

    public void run() {
        DeltaOut deltaRes;
        System.out.println(getConfig());
        // String space = "";
        // for (int i=0; i < 20; i++)
        //     space += "\n";
        while (state != endState) {
            sleep(DELAY);
            deltaRes = delta.get(new DeltaIn(state, read()));
            write(deltaRes.b);
            state = deltaRes.q;
            move(deltaRes.d);
            // System.out.println(space);
            System.out.println(getConfig());
        }
        // System.out.println(getConfig());
    }

    public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			System.out.println("Thread is interrupted");
		}
    }

    public char read() {
        return tapeHead.get();
    }

    public void write(char c) {
        tapeHead.set(c);
    }

    private void move(char dir) {
        if (dir == 'R') {
            tapeHead = tapeHead.right();
            if (tapeHead == rightmostNode) {
                for (int i=0; i < DYNAMIC_OFFSET_RIGHT; i++)
                    rightmostNode = rightmostNode.right();
            }
        }
        else if (dir == 'L') {
            tapeHead = tapeHead.left();
            if (tapeHead == leftmostNode) {
                for (int i=0; i < DYNAMIC_OFFSET_LEFT; i++)
                    leftmostNode = leftmostNode.left();
            }
        }
    }

    public String getConfig() {
        String config = "";
        // for (int i=0; i < (outputWidth-(tapeLength+3))/2; i++)
        //     config += " ";
        // go from leftmost to rightmost, including
        for (TapeNode node = leftmostNode; node != rightmostNode.right(); node = node.right()) {
            if (node.equals(tapeHead))
                config += "[" + state + "]";
            config += node.get();
        }
        // for (int i=0; i < (outputWidth-(tapeLength+3))/2; i++)
        //     config += " ";
        return config;
    }

}
