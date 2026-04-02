import java.util.*;

public class AccountLookup {

    static int linearFirst(String[] arr, String target) {
        int comparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear First Index: " + i + " Comparisons: " + comparisons);
                return i;
            }
        }
        System.out.println("Not Found Comparisons: " + comparisons);
        return -1;
    }

    static int linearLast(String[] arr, String target) {
        int comparisons = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            comparisons++;
            if (arr[i].equals(target)) {
                System.out.println("Linear Last Index: " + i + " Comparisons: " + comparisons);
                return i;
            }
        }
        System.out.println("Not Found Comparisons: " + comparisons);
        return -1;
    }

    static int binarySearch(String[] arr, String target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid].equals(target)) {
                System.out.println("Binary Found Index: " + mid + " Comparisons: " + comparisons);
                return mid;
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Not Found Comparisons: " + comparisons);
        return -1;
    }

    static int firstOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                high = mid - 1;
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    static int lastOccurrence(String[] arr, String target) {
        int low = 0, high = arr.length - 1, result = -1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid].equals(target)) {
                result = mid;
                low = mid + 1;
            } else if (arr[mid].compareTo(target) < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    static int countOccurrences(String[] arr, String target) {
        int first = firstOccurrence(arr, target);
        int last = lastOccurrence(arr, target);

        if (first == -1) return 0;
        return last - first + 1;
    }

    public static void main(String[] args) {
        String[] logs = {"accB", "accA", "accB", "accC"};

        linearFirst(logs, "accB");
        linearLast(logs, "accB");

        Arrays.sort(logs);

        int index = binarySearch(logs, "accB");
        int count = countOccurrences(logs, "accB");

        System.out.println("Count of accB: " + count);
    }
}