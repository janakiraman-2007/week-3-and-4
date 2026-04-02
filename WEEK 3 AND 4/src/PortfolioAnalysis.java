import java.util.*;

class Asset {
    String name;
    double returnRate;
    double volatility;

    Asset(String name, double returnRate, double volatility) {
        this.name = name;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }
}

public class PortfolioAnalysis {

    static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    static void merge(Asset[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];

        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    static void quickSort(Asset[] arr, int low, int high) {
        if (high - low <= 10) {
            insertionSort(arr, low, high);
            return;
        }
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    static int partition(Asset[] arr, int low, int high) {
        int pivotIndex = medianOfThree(arr, low, high);
        Asset pivotValue = arr[pivotIndex];
        swap(arr, pivotIndex, high);

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(arr[j], pivotValue) < 0) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    static int medianOfThree(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;

        if (arr[low].returnRate < arr[mid].returnRate)
            swap(arr, low, mid);
        if (arr[low].returnRate < arr[high].returnRate)
            swap(arr, low, high);
        if (arr[mid].returnRate < arr[high].returnRate)
            swap(arr, mid, high);

        return mid;
    }

    static int compare(Asset a, Asset b) {
        if (a.returnRate != b.returnRate)
            return Double.compare(b.returnRate, a.returnRate);
        return Double.compare(a.volatility, b.volatility);
    }

    static void insertionSort(Asset[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Asset key = arr[i];
            int j = i - 1;

            while (j >= low && compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }
    }

    static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    static void printAssets(Asset[] arr) {
        for (Asset a : arr) {
            System.out.print(a.name + ":" + a.returnRate + "% ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Asset[] assets = {
                new Asset("AAPL", 12, 5),
                new Asset("TSLA", 8, 9),
                new Asset("GOOG", 15, 4)
        };

        Asset[] mergeSorted = assets.clone();
        mergeSort(mergeSorted, 0, mergeSorted.length - 1);
        printAssets(mergeSorted);

        Asset[] quickSorted = assets.clone();
        quickSort(quickSorted, 0, quickSorted.length - 1);
        printAssets(quickSorted);
    }
}