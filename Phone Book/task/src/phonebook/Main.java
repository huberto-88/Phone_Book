package phonebook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final String directoryPath = "C:\\Users\\Hubert\\Phone Book\\directory.txt";
    private static final String findPath = "C:\\Users\\Hubert\\Phone Book\\find.txt";

    public static void main(String[] args) {
        List<Person> directoryList = new ArrayList<>(readFile(directoryPath));
        List<Person> toFindList = new ArrayList<>(readFile(findPath));

        System.out.println("Start searching (linear search)...");
        long startSearch = System.currentTimeMillis();
        int founded = Algorithms.linearSearch(directoryList, toFindList);
        long stopSearch = System.currentTimeMillis();
        long linearSearchTime = stopSearch - startSearch;
        System.out.printf("Found %d / %d entries. Time taken: %s\n", founded,
                toFindList.size(), timeToReadableForm(stopSearch - startSearch));

        System.out.println("Start searching (bubble sort + jump search)...");
        long startBubbleSorting = System.currentTimeMillis();
        boolean bubbleSortDone = Algorithms.bubbleSort(directoryList, linearSearchTime);
        long stopBubbleSorting = System.currentTimeMillis();
        long bubbleSortingTime = stopBubbleSorting - startBubbleSorting;

        if (bubbleSortDone) {
            founded = 0;
            startSearch = System.currentTimeMillis();
            for (Person person : toFindList) {
                if (Algorithms.jumpSearch(directoryList, person) > -1) {
                    founded++;
                }
            }
            stopSearch = System.currentTimeMillis();
        }

        System.out.printf("Found %d / %d entries. Time taken: %s\n",
                founded, toFindList.size(), timeToReadableForm(
                        stopSearch - startSearch + bubbleSortingTime));

        System.out.printf("Sorting time: %s", timeToReadableForm(stopBubbleSorting - startBubbleSorting));
        System.out.println(bubbleSortDone ? "" : "STOPPED, moved to linear search");
        System.out.printf("Searching time: %s\n", timeToReadableForm(stopSearch - startSearch));


        System.out.println("Start searching (quick sort + binary search)...");
        long startQuickSort = System.currentTimeMillis();
        Algorithms.quickSort(directoryList);
        long stopQuickSort = System.currentTimeMillis();

        startSearch = System.currentTimeMillis();
        founded = 0;
        for (Person toFind : toFindList) {
            if (Algorithms.binarySearch(directoryList, toFind) != -1) {
                founded++;
            }
        }
        stopSearch = System.currentTimeMillis();

        System.out.printf("Found %d / %d entries. Time taken: %s\n", founded, toFindList.size(),
                timeToReadableForm(stopSearch - startQuickSort));
        System.out.printf("Sorting time: %s\n", timeToReadableForm(stopQuickSort - startQuickSort));
        System.out.printf("Searching time: %s\n", timeToReadableForm(stopSearch - startSearch));

    }


    public static List<Person> readFile(String path) {
        File file = new File(path);
        List<Person> array = new ArrayList<>();
        try (FileReader fileReader = new FileReader(file)) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                String[] person = temp.split("\\s+", 2);
                if (person[0].matches("\\d+")) {
                    array.add(new Person(Integer.parseInt(person[0]), person[1]));
                } else if (person.length > 1) {
                    array.add(new Person(person[0] + " " + person[1]));
                } else {
                    array.add(new Person(person[0]));
                }
            }
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
        return array;
    }

    public static String timeToReadableForm(long milliseconds) {
        long seconds = (milliseconds / 1000) % 60;
        long minutes = (milliseconds / 1000) / 60;
        long milli = milliseconds % 1000;
        return minutes + " min. " + seconds + " sec. " + milli + " ms.";
    }
}