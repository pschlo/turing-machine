public class TapeNode {
    
    private char data;
    private TapeNode pred;
    private TapeNode succ;
    private final char BLANK = '_';

    public TapeNode() {
        data = BLANK;
    }

    public void set(char data) {
        this.data = data;
    }

    public char get() {
        return this.data;
    }

    public TapeNode left() {
        if (pred == null) {
            pred = new TapeNode();
            pred.succ = this;
        }
        return pred;
    }

    public TapeNode right() {
        if (succ == null) {
            succ = new TapeNode();
            succ.pred = this;
        }
        return succ;
    }

    // public void setPred(TapeNode pred) {
    //     this.pred = pred;
    // }

    // public void setSucc(TapeNode succ) {
    //     this.succ = succ;
    // }


}
