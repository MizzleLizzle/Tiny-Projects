import java.util.Scanner;
import java.lang.Math;
import java.util.ArrayList;

public class Sylow{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Size of Group:");
        int groupSize = in.nextInt();
        System.out.println("|G|="+groupSize);
        ArrayList<primeFactor> factors = primeDecomposition(groupSize);
        for (int i = 0; i < factors.size(); i++) {
            int p = factors.get(i).getPrime();
            int r = factors.get(i).getExponent();
            int m = 0;
            for (int j = 0; j < i; j++) {
                m += pow(factors.get(j).getPrime(), factors.get(j).getExponent());
            }
            for (int k = i+1; k < factors.size(); k++) {
                m += pow(factors.get(k).getPrime(), factors.get(k).getExponent());
            }
            ArrayList<Integer> pamounts = amountOfSpU(p, r, m);
            String set = "{1";
            for (int l = 1; l < pamounts.size(); l++) {
                set += ","+pamounts.get(l);
            }
            set += "}";

            System.out.println("There is a S-"+p+"-S of order "+ pow(p, r)+" the Amount of the S-"+p+"-S is in "+set);
        }
        in.close();
    }

    public static ArrayList<primeFactor> primeDecomposition(int n) {
        ArrayList<primeFactor> factors = new ArrayList<primeFactor>();
        for (int i = n; i > 1; i--) {
            if (isPrime(i) && n%i==0) {
                int exponent = 1;
                while ((n / pow(i,exponent))%i == 0) {
                    exponent++;
                }
                factors.add(new primeFactor(i, exponent));
            }
        }
        return factors;
    }

    public static boolean isPrime(int n) {
        if ((n % 2 == 0 && n != 2) || n == 1) {
            return false;
        }
        for (int i = 3; i < (int) Math.sqrt(n)+1; i++) {
            if (n%i == 0) {
                return false;
            }
        }
        return true;
    }

    public static ArrayList<Integer> amountOfSpU(int p, int r, int m) {
        ArrayList<Integer> amounts = new ArrayList<Integer>();
        for (int i = 1; i <= m; i++) {
            if (m%i == 0) {
                amounts.add(i);
            }
        }
        for (int i = 0; i < amounts.size(); i++) {
            if (amounts.get(i)%pow(p, r) != 1) {
                amounts.remove(i);
                i--;
            }
        }
        return amounts;
    }

    public static int pow(int a, int b) {
        int c = 1;
        for (int i = 1; i <= b; i++) {
            c *= a;
        }
        return c;
    }
}

