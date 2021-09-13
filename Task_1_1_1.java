import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Task_1_1_1{

    public static void sort(int[] arr)
    {
        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--)
            heap(arr, n, i);

        for (int i=n-1; i>=0; i--)
        {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heap(arr, i, 0);
        }
    }

    static void heap(int[] arr, int n, int i)
    {
        int largest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;

        if (l < n && arr[l] > arr[largest])
            largest = l;

        if (r < n && arr[r] > arr[largest])
            largest = r;

        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heap(arr, n, largest);
        }
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        String path = "C:/java/input.txt";

        File file = new File(path);
        Scanner in = new Scanner(file);

        int n = in.nextInt();

        System.out.println(n);

        int[] arr = new int[n];
        int j =0;
        while(in.hasNextInt()) {
            arr[j]=in.nextInt();
            j++;
        }
        in.close();

        for (int i =0;i<n;i++)
            System.out.printf("%d ",arr[i]);

        sort(arr);

        System.out.println();
        for (int i =0;i<n;i++)
            System.out.printf("%d ",arr[i]);
    }
}
