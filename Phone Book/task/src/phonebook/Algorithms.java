package phonebook;

import java.util.List;

public class Algorithms {

    public static int linearSearch(List<Person> directory, List<Person> find) {
        int founded = 0;
        for (Person toFind : find) {
            for (Person dir : directory) {
                if (dir.getName().contains(toFind.getName())) {
                    founded++;
                    break;
                }
            }
        }
        return founded;
    }

    public static boolean bubbleSort(List<Person> list, long linearSearchTime) {
        long startSearch = System.currentTimeMillis();

        for (int i = 0; i < list.size(); i++) {
            if (System.currentTimeMillis() - startSearch > linearSearchTime * 10) {
                return false;
            }
            for (int j = 0; j < list.size() - i - 1; j++) {
                if (list.get(j).compareTo(list.get(j + 1)) > 0) {
                    Person temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
        return true;
    }

    public static int jumpSearch(List<Person> directory, Person toFind) {
        int n = directory.size();
        int step = (int) Math.floor(Math.sqrt(n));
        int prev = 0;

        while (directory.get(Math.min(step, n) - 1).compareTo(toFind) < 0) {
            prev = step;
            step += (int) Math.floor(Math.sqrt(n));
            if (prev >= n) {
                return -1;
            }
        }

        while (directory.get(prev).compareTo(toFind) < 0) {
            prev++;
            if (prev == Math.min(step, n)) {
                return -1;
            }

            if (directory.get(prev).compareTo(toFind) == 0) {
                return prev;
            }
        }
        return -1;
    }
}