public class binarySearch {
    public static int op() {
        int[] ints = new int[]{22, 4, 5, 66, 4323};
        int low = 0;
        int high = ints.length - 1;
        int key = 324;
        while (high > low) {
            int mid = (low + high) / 2;
            if (key < ints[mid]) {
                high = mid - 1;
            } else if (key > ints[mid]) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(Stringm[] args) {
        System.out.println(op());
    }
}
