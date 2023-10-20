import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class BubbleSortApp {
    public static int[] createRandomArray(int arrayLength) {
        int[] randomArray = new int[arrayLength];
        Random random = new Random();

        for (int i = 0; i < arrayLength; i++) {
            randomArray[i] = random.nextInt(101);
        }

        return randomArray;
    }

    public static void writeArrayToFile(int[] array, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (int num : array) {
                writer.write(Integer.toString(num));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int[] readFileToArray(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)) {
            String line;
            int lineCount = 0;

            while ((line = reader.readLine()) != null) {
                lineCount++;
            }

            int[] result = new int[lineCount];
            
            reader.close();
            reader = new BufferedReader(new FileReader(filename));

            int index = 0;
            while ((line = reader.readLine()) != null) {
                result[index] = Integer.parseInt(line);
                index++;
            }

            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;

        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bubble Sort Command Line App");
        System.out.println("1. Generate an array of random integers and store it in a file");
        System.out.println("2. Read an existing file containing a list of integers, sort it, and store the sorted array in another file");
        System.out.print("Enter your choice (1 or 2): ");

        int choice = scanner.nextInt();

        if (choice == 1) {
            System.out.print("Enter the length of the array: ");
            int arrayLength = scanner.nextInt();

            int[] randomArray = createRandomArray(arrayLength);

            System.out.print("Enter the filename to write the array: ");
            String filename = scanner.next();
            writeArrayToFile(randomArray, filename);
            System.out.println("Array has been written to " + filename);
        } else if (choice == 2) {
            System.out.print("Enter the filename to read the unsorted array: ");
            String unsortedFileName = scanner.next();
            int[] unsortedArray = readFileToArray(unsortedFileName);
            bubbleSort(unsortedArray);

            System.out.print("Enter the filename to write the sorted array: ");
            String sortedFileName = scanner.next();
            writeArrayToFile(unsortedArray, sortedFileName);
            System.out.println("Sorted array has been written to " + sortedFileName);
        } else {
            System.out.println("Invalid choice. Please enter 1 or 2.");
        }

        scanner.close();
    }
}

