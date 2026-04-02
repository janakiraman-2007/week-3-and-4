import java.util.*;

class Client {
    String name;
    int riskScore;
    double accountBalance;

    public Client(String name, int riskScore, double accountBalance) {
        this.name = name;
        this.riskScore = riskScore;
        this.accountBalance = accountBalance;
    }

    @Override
    public String toString() {
        return name + "(risk=" + riskScore + ", balance=" + accountBalance + ")";
    }
}

public class ClientRiskRanking {

    private static int compare(Client a, Client b) {
        if (a.riskScore != b.riskScore) {
            return Integer.compare(b.riskScore, a.riskScore);
        }
        return Double.compare(a.accountBalance, b.accountBalance);
    }

    public static void bubbleSortAsc(List<Client> list) {
        int n = list.size();
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).riskScore > list.get(j + 1).riskScore) {
                    System.out.println("Swapping: " + list.get(j) + " <-> " + list.get(j + 1));
                    Collections.swap(list, j, j + 1);
                    swaps++;
                }
            }
        }
        System.out.println("Total swaps: " + swaps);
    }

    public static void insertionSort(List<Client> list) {
        for (int i = 1; i < list.size(); i++) {
            Client key = list.get(i);
            int j = i - 1;

            while (j >= 0 && compare(list.get(j), key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }
            list.set(j + 1, key);
        }
    }

    public static List<Client> top10(List<Client> list) {
        return list.subList(0, Math.min(10, list.size()));
    }

    public static void main(String[] args) {
        List<Client> clients = new ArrayList<>();

        clients.add(new Client("clientC", 80, 5000));
        clients.add(new Client("clientA", 20, 2000));
        clients.add(new Client("clientB", 50, 3000));

        System.out.println("Bubble Sort (Ascending Risk):");
        bubbleSortAsc(clients);
        System.out.println(clients);

        System.out.println("\nInsertion Sort (DESC Risk + Balance):");
        insertionSort(clients);
        System.out.println(clients);

        System.out.println("\nTop Risk Clients:");
        for (Client c : top10(clients)) {
            System.out.println(c);
        }
    }
}