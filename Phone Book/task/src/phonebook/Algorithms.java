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

    //---------------------------------------------------------------------------------------------

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

    //---------------------------------------------------------------------------------------------

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

    //---------------------------------------------------------------------------------------------

    // quicksort
    public static void quickSort(List<Person> list) {
        concreteQuickSort(list, 0, list.size() - 1);
    }
    private static void concreteQuickSort(List<Person> list, int low, int high) {
        if (low < high) {
            // pi is partitioning index
            // now is at right place
            int pi = partition(list, low, high);

            // Separately sort elements before partition and after partition
            concreteQuickSort(list, low, pi - 1);
            concreteQuickSort(list, pi + 1, high);
        }
    }

    private static void swap(List<Person> list, int i, int j) {
        Person temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
    private static int partition(List<Person> list, int low, int high) {
        //pivot
        Person pivot = list.get(high);

        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {
            //if current smaller than pivot
            if (list.get(j).compareTo(pivot) < 0) {
                //increment index of smaller element
                i++;
                swap(list, i, j);

            }
        }
        swap(list, i + 1, high);
        return (i + 1);
    }

    //---------------------------------------------------------------------------------------------

    public static int binarySearch(List<Person> list, Person wanted) {
        return concreteBinarySearch(list, wanted, 0, list.size());
    }
    private static int concreteBinarySearch(List<Person> list, Person wanted,  int start, int end) {
        if (end >= start) {
            int half = (start + end) / 2;

            if (list.get(half).compareTo(wanted) == 0) {
                return half;
            } else if (list.get(half).compareTo(wanted) > 0) {
                //left
                return concreteBinarySearch(list, wanted, start, half - 1);
            } else if (list.get(half).compareTo(wanted) < 0) {
                // right
                return concreteBinarySearch(list, wanted, half + 1, end);
            }
        }
        return -1;
    }

}