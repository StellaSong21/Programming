public class StcckOfTrack0 {

    private int[][] stack;
    private int size;
    private static final int CAPACITY = 16;

    StcckOfTrack0() {
        this(CAPACITY);
    }

    private StcckOfTrack0(int capacity) {
        stack = new int[capacity][2];
    }

    void push(int[] trace) {
        if (size >= stack.length) {
            int[][] temp = new int[stack.length * 2][2];
            for (int i = 0; i < stack.length; i++) {
                System.arraycopy(stack[i], 0, temp[i], 0, 2);
            }
            stack = temp;
        }
        stack[size++] = trace;
    }

    int[] peek() {
        if (isEmrty()) {
            return new int[]{1, 1};
        } else
            return stack[size - 1];
    }

    int[] peek1() {
        if (size >= 2) {
            return stack[size - 2];
        } else
            return new int[]{1, 1};
    }

    int[] peek2() {
        if (size > 2) {
            return stack[size - 3];
        } else
            return new int[]{1, 1};
    }

    int[] peek3() {
        if (size > 3) {
            return stack[size - 4];
        } else
            return new int[]{1, 1};
    }

    void pop() {
        size--;
    }

    boolean isEmrty() {
        return size == 0;
    }

}
