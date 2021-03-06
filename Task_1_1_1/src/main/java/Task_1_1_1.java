public class Task_1_1_1 {
    /**
     * @param arr массив, который нужно отсортировать
     *
     * Метод вызывает метод Heap для балансировки с правильными параметрами, уменьшая изначальный массив
     */
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

    /**
     * @param arr - Массив для сортировки
     * @param n - количество неотсортированных элементом массива
     * @param i - индекс наибольшего элемента оставшегося массива
     *
     * Метод балансирует делево на массиве, поднимая наибольший элемент в корень дерева
     */
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
}
