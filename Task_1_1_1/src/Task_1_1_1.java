import java.util.Scanner;
import java.io.File;
import java.io.*;


public class Task_1_1_1 {

    public static void sort(int[] arr) {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heap(arr, n, i);

        for (int i = n - 1; i >= 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heap(arr, i, 0);
        }
    }

    static void heap(int[] arr, int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heap(arr, n, largest);
        }
    }

    public static void main(String[] args) throws IOException {
        String inPath = "C:/java/input.txt";
        String outPath = "C:/java/output.txt";

        File inFile = new File(inPath);
        Scanner in = new Scanner(inFile);
        File outFile = new File(outPath);
        FileWriter out = new FileWriter(outFile);

        int cnt = in.nextInt();
        int ind = 0;

        for (int k = 0; k < cnt; k++) {
            int n = in.nextInt();
            ind = 0;
            int[] arr = new int[n];
            int[] res = new int[n];

            for(int i =0;i<n;i++)
                arr[i] = in.nextInt();
            for(int i =0;i<n;i++)
                res[i] = in.nextInt();

            sort(arr);

            for (int i = 0; i < n; i++)
                if (arr[i] != res[i]) {
                    ind = -1;
                    break;
                }

            if(ind == 0)
            {
                for(int i=0;i<n;i++) {
                    System.out.printf("%d ", arr[i]);
                }
                System.out.println();
            }
            else {
                System.out.println("unsuccessfully");
            }

        }

        out.close();
        in.close();
    }

}
