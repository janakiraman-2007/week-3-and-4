import java.util.*;

class Transaction {
    String id;
    double fee;
    String timestamp; // format: HH:mm

    public Transaction(String id, double fee, String timestamp) {
        this.id = id;
        this.fee = fee;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return id + ": fee=" + fee + ", ts=" + timestamp;
    }
}

public class TransactionSorter {

    // Comparator: sort by fee, then timestamp
    private static int compare(Transaction a, Transaction b) {
        if (a.fee != b.fee) {
            return Double.compare(a.fee, b.fee);
        }
        return a.timestamp.compareTo(b.timestamp); // stable tie-break
    }

    // Bubble Sort (for small batches ≤ 100)
    public static void bubbleSort(List<Transaction> list) {
        int n = list.size();
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (compare(list.get(j), list.get(j + 1)) > 0) {
                    Collections.swap(list, j, j + 1);
                    swapped = true;
                }
            }

            // Early termination if already sorted
            if (!swapped) break;
        }
    }

    // Insertion Sort (stable, for medium batches 100–1000)
    public static void insertionSort(List<Transaction> list) {
        for (int i = 1; i < list.size(); i++) {
            Transaction key = list.get(i);
            int j = i - 1;

            // Shift elements to the right
            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    // Flag high-fee outliers (> $50)
    public static List<Transaction> findOutliers(List<Transaction> list) {
        List<Transaction> outliers = new ArrayList<>();
        for (Transaction t : list) {
            if (t.fee > 50.0) {
                outliers.add(t);
            }
        }
        return outliers;
    }

    // Main sorting controller
    public static void sortTransactions(List<Transaction> list) {
        int size = list.size();

        if (size <= 100) {
            bubbleSort(list);
        } else if (size <= 1000) {
            insertionSort(list);
        } else {
            // For large datasets, fallback to built-in stable sort
            list.sort(TransactionSorter::compare);
        }
    }

    // Demo
    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();

        transactions.add(new Transaction("id1", 10.5, "10:00"));
        transactions.add(new Transaction("id2", 25.0, "09:30"));
        transactions.add(new Transaction("id3", 5.0, "10:15"));

        // Sort
        sortTransactions(transactions);

        System.out.println("Sorted Transactions:");
        for (Transaction t : transactions) {
            System.out.println(t);
        }

        // Outliers
        List<Transaction> outliers = findOutliers(transactions);
        System.out.println("\nHigh-fee outliers:");
        if (outliers.isEmpty()) {
            System.out.println("None");
        } else {
            for (Transaction t : outliers) {
                System.out.println(t);
            }
        }
    }
}