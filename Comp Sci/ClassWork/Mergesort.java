public class Mergesort {
    //source: http://www.vogella.com/tutorials/JavaAlgorithmsMergesort/article.html
    private static int[] numbers;
    private static int[] helper;

    private static int number;

    public static int[] mergesort(int[] values) {
        numbers = values;
        number = values.length;
        helper = new int[number];
        sort.changeStep(3);
        mergesort(0, number - 1);
        
        return numbers;
    }

    private static void mergesort(int low, int high) {
        // check if low is smaller then high, if not then the array is sorted
        if (low < high) {
            // Get the index of the element which is in the middle
            int middle = low + (high - low) / 2;
                sort.changeStep(1);
            // Sort the left side of the array
            mergesort(low, middle);
            sort.changeStep(1);
            // Sort the right side of the array
            mergesort(middle + 1, high);
            sort.changeStep(1);
            // Combine them both
            merge(low, middle, high);
            sort.changeStep(1);
        }
    }

    private static void merge(int low, int middle, int high) {

        // Copy both parts into the helper array
        for (int i = low; i <= high; i++) {
            helper[i] = numbers[i];
            sort.changeStep(1);
        }

        int i = low;
        int j = middle + 1;
        int k = low;
        sort.changeStep(3);
        // Copy the smallest values from either the left or the right side back
        // to the original array
        while (i <= middle && j <= high) {
            if (helper[i] <= helper[j]) {
                numbers[k] = helper[i];
                i++;
            } else {
                numbers[k] = helper[j];
                j++;
            }
            k++;
            sort.changeStep(4);
        }
        // Copy the rest of the left side of the array into the target array
        while (i <= middle) {
            numbers[k] = helper[i];
            k++;
            i++;
            sort.changeStep(3);
        }

    }
} 