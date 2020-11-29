import java.util.Arrays;
import java.util.Collections;


public class HavelHakimi {
    public static void main(String[] args) {
        
        HavelHakimi hh = new HavelHakimi();

        System.out.println(hh.havelHakimi(new int[]{5, 3, 0, 2, 6, 2, 0, 7, 2, 5}));
        System.out.println(hh.havelHakimi(new int[]{3, 1, 2, 3, 1, 0}));
    }

    public int[] removeZeroes(int[] in) {
        int length = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] != 0) {
                length++;
            }
        }
        int[] out = new int[length];
        int pos = 0;
        for (int j = 0; j < in.length; j++) {
            if (in[j] != 0) {
                out[pos] = in[j];
                pos++;
            }
        }
        return out;
    }

    public int[] frontElimination(int[] in, int n) {
        for (int i = 0; i < n; i++) {
            in[i]--;
        }
        return in;
    }
    
    public int[] removeFirst(int[] in) {
        if (in.length == 0) {
            return in;
        }
        int[] out = new int[in.length-1];
        for (int i = 1; i < in.length; i++) {
            out[i-1] = in[i];
        }
        return out;
    }

    public int[] reverseArray(int[] in) {
        int[] out = new int[in.length];
        for (int i = 0; i < out.length; i++) {
            out[i] = in[in.length-i-1];
        }
        return out;
    }

    public void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+", ");
        }
        System.out.println("");
    }

    public boolean havelHakimi(int[] in) {
        in = removeZeroes(in);
        if (in.length == 0) {
            return true;
        }
        Arrays.sort(in);
        in = reverseArray(in);
        if (in[0] >= in.length) {
            return false;
        }
        int N = in[0];
        in = removeFirst(in);
        in = frontElimination(in, N);
        return havelHakimi(in);
    }

}
