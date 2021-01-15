class Stack {

    private static int size = 12;
    private static int[][] trace = new int[size][2];


    public static int[][] push(int x, int y) {
        if (size >= trace.length) {
            int[][] temp = new int[trace.length * 2][2];
            System.arraycopy(trace, 0, temp, 0, trace.length);
            trace = temp;
        }
        trace[size++][0] = x;
        trace[size++][1] = y;
        return trace;
    }

    static int[] pop() {
        return trace[--size];
    }

    static int[] peek() {
        if (!isEmpty())
            return trace[size - 1];
        else
            return new int[]{};
    }

    private static boolean isEmpty() {
        return size == 0;
    }

}
