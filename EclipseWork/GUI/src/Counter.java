
public class Counter {
    public static int count = 0;
    public int self;

    public Counter() {
        ++count;
        self = 0;
    }

    public Counter(int i) {
        ++count;
        self = i;
    }

    public void addMe(int i) {
        self += i;
    }

    public void runMe() {
        System.out.println("The Value of Count is " + count + "\n");
        System.out.println("The Value of Self is " + self + "\n");
    }

    public static void main(String args[]) {
        Counter a = new Counter();
        Counter b = new Counter();
        Counter c = new Counter(100);
        a.addMe(12);
        b.addMe(7);
        System.out.println("RUN A ");
        a.runMe();
        System.out.println("RUN B ");
        b.runMe();
        System.out.println("RUN C ");
        c.runMe();
    }
}
