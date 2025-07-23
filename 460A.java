import java.util.Scanner;

public class VasyaAndSocks {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // initial number of socks
        int m = scanner.nextInt(); // every m-th day mom buys new socks

        int days = 0;
        int socks = n;

        while (socks > 0) {
            days++;
            socks--; // Vasya wears one sock each day

            if (days % m == 0) {
                socks++; // mom buys 1 new sock every m-th day
            }
        }

        System.out.println(days);
    }
}
