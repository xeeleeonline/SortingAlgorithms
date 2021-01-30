import java.util.Scanner;
import java.util.Random;

public class SortingAlgorithms {
   public static Scanner input = new Scanner (System.in);
   
   public static String [] algorithms = new String[5];
   
   public static boolean arrayExists = false;
   public static int [] initialArray;
   public static int [] finalArray;
   
   public static int swapCount;
   public static int iterationCount;
   
   public static void main (String [] args) {
      algorithms[0] = "Insertion Sort";
      algorithms[1] = "Bubble Sort";
      algorithms[2] = "Selection Sort";
      algorithms[3] = "Quick Sort";
      algorithms[4] = "Merge Sort";
      
      MainMenu();
      
      System.out.println();
      System.out.println("Exiting...");
      System.exit(0);
   }
   public static void SwitchCases(int algorithmChoice) {
      iterationCount = 0;
      swapCount = 0;
      
      switch (algorithmChoice) {
         case 0: InsertionSort(); break;
         case 1: BubbleSort(); break;
         case 2: SelectionSort(); break;
         case 3: QuickSort(); break;
         case 4: MergeSort(); break;
      }
   }
   public static void MergeSort() {
      System.out.println("sup bro");
   }
   public static void MergeSortIteration() {
   }
   public static void QuickSort() {
   }
   public static void SelectionSort() {
      for (int i = 0; i < finalArray.length; i++) {
         iterationCount++;
         int minimumNumber = finalArray[i];
         int minimumIndex = i;
         System.out.print("Iteration (" + iterationCount + ") - ");
         
         for (int j = i; j < finalArray.length; j++) {
            if (finalArray[j] < minimumNumber) {
               minimumNumber = finalArray[j];
               minimumIndex = j;
            }
         }
         
         if (minimumIndex != i) {
            int temp = finalArray[minimumIndex];
            finalArray[minimumIndex] = finalArray[i];
            finalArray[i] = temp;
            
            swapCount++;
            System.out.println("Lowest Value: " + temp + ", moved from index " + minimumIndex + " to " + i);
         } else System.out.println("No Swaps");
         
         PrintArray(finalArray);
         System.out.println();
         System.out.println();
      }
   }
   public static void BubbleSort() {
      boolean passedWithoutSwap = false;
      
      do {
         boolean swapped = false;
         int swapsThisIteration = 0;
         iterationCount++;
         
         // print out array at current iteration
         System.out.println("Iteration (" + iterationCount + ") - Current Array:");
         PrintArray(finalArray);
         System.out.println();
         System.out.println();
         
         for (int i = 1; i < finalArray.length; i++) {
            if (finalArray[i] < finalArray[i - 1]) {
               int temp = finalArray[i - 1];
               finalArray[i - 1] = finalArray[i];
               finalArray[i] = temp;
               
               swapped = true;
               swapCount++;
               swapsThisIteration++;
               System.out.println("Swapped " + finalArray[i - 1] + " and " + finalArray[i] + " at indices " + (i - 1) + " and " + i);
               
               // print out array at current iteration
               PrintArray(finalArray);
               System.out.println();
               System.out.println();
               
            }
         }
         System.out.println("Number of swaps this iteration: " + swapsThisIteration);
         System.out.println();
         
         if (!swapped) passedWithoutSwap = true;
         else {
            System.out.println("-");
            System.out.println();
         }
      } while (!passedWithoutSwap);
   }
   public static void InsertionSort() {
      for (int i = 1; i < finalArray.length; i++) {
         iterationCount++;
         int previousIndex = i - 1;
         
         System.out.print("Iteration (" + iterationCount + ") - ");
         
         // attempt sort only if this number needs to be moved
         if (finalArray[i] < finalArray[previousIndex]) {
            boolean foundNewSpot = false;
            swapCount++;
            
            while (!foundNewSpot) {
                if (finalArray[i] >= finalArray[previousIndex]) {
                  previousIndex++;
                  foundNewSpot = true;
               }
               else {
                  if (previousIndex > 0) previousIndex--;
                  else foundNewSpot = true;
               }
            }
            int temp = finalArray[i];
            for (int j = i; j >= previousIndex; j--) {
               if (j == previousIndex) finalArray[j] = temp;
               else finalArray[j] = finalArray[j - 1];
            }
            System.out.println("Moved number " + temp + " [index " + i + " -> " + previousIndex + "]");
         }
         else System.out.println("No swap");
         
         // print out array at current iteration
         PrintArray(finalArray);
         System.out.println();
         System.out.println();
      }
   }
   public static void MainMenu() {
      boolean correctMenuInput = false;
      int mainMenuChoice = -1;
      
      // while loop that will receive player input, stay in loop until input is valid
      while (mainMenuChoice != 0) {
         System.out.println("MASTER SORTING ALGORITHMS");
         System.out.println("=========================");
         System.out.println();
         
         while (!correctMenuInput) {
            for (int i = 0; i < algorithms.length; i++) {
               System.out.println((i + 1) + ". " + algorithms[i]);
            }
            System.out.println("0. Exit");
            System.out.println();
            System.out.println("Choose your sorting algorithm of choice, or 0 to exit: ");
            
            boolean passedTryCatch = true;
            
            try { mainMenuChoice = input.nextInt(); }
            catch (Exception e) {
               PrintInvalidInput();
               passedTryCatch = false;
               input.nextLine();
            }
            if (passedTryCatch) {
               if (mainMenuChoice >= 0 && mainMenuChoice <= algorithms.length) correctMenuInput = true;
               else PrintInvalidInput();
            }
         }
         if (mainMenuChoice != 0) {
            System.out.println("=========================");
            System.out.println("Your chosen algorithm: " + algorithms[mainMenuChoice - 1]);
            
            DoAlgorithm(mainMenuChoice - 1);
            correctMenuInput = false;
            mainMenuChoice = -1;
         }
      }
   }
   public static void PrintInvalidInput() {
      System.out.println();
      System.out.println("Invalid input!!");
      System.out.println("- - - - - - - -");
   }
   public static void DoAlgorithm(int algorithmChoice) {
      if (arrayExists) AskNewArray();
      else GenerateArray();
      System.out.println("Press Enter to begin " + algorithms[algorithmChoice]);
      
      input.nextLine();
      try { input.nextLine(); }
      catch (Exception e) { }
      
      System.out.println("-------------------");
      System.out.println();
      
      finalArray = initialArray.clone();
      long initialTime = System.currentTimeMillis();
      
      SwitchCases(algorithmChoice);
      
      long finalTime = System.currentTimeMillis();
      double algorithmTime = (finalTime - initialTime);
      
      System.out.println("-------------------");
      System.out.println();
      System.out.println("Finished " + algorithms[algorithmChoice] + ".");
      System.out.println();
      
      PrintReport(algorithmTime);
   }
   public static void PrintReport(double algorithmTime) {
      System.out.println("Initial Unsorted Array: ");
      PrintArray(initialArray);
      System.out.println();
      
      System.out.println();
      
      System.out.println("Final Sorted Array: ");
      PrintArray(finalArray);
      System.out.println();
      
      System.out.println();
      
      System.out.println("Swaps: " + swapCount + " out of " + iterationCount + " iterations");
      System.out.println("Algorithm Time: " + String.format("%.3f",algorithmTime / 1000) + " seconds.");
      
      System.out.println();
      
      System.out.println("Press Enter to return to Main Menu");
      try { input.nextLine(); }
      catch (Exception e) { }
      
      System.out.println("=========================");
   }
   public static void AskNewArray() {
      boolean correctInput = false;
      int newArrayAnswer = -1;
      
      System.out.println();
      System.out.print("Previous unsorted array: ");
      PrintArray(initialArray);
      System.out.println();
      System.out.println("Use this array for this sorting algorithm?");
      System.out.print("(1. Yes, 2. No) : ");
      
      // while loop that will receive player input, stay in loop until input is valid
      while (!correctInput) {
         boolean passedTryCatch = true;
         System.out.print("");
         
         // use try catch, since player input may not be correct variable type
         try { newArrayAnswer = input.nextInt(); }
         catch (Exception e) {
            PrintInvalidInput();
            passedTryCatch = false;
            input.nextLine();
         }
         
         // if input is correct variable type, make sure it falls within range of what we want
         if (passedTryCatch) {
            if (newArrayAnswer == 1 || newArrayAnswer == 2) {
               System.out.println();
               if (newArrayAnswer == 2) {
                  GenerateArray();
               }
               correctInput = true;
            }
            else PrintInvalidInput();
         }
      }
   }
   public static void PrintArray(int [] arrayToPrint) {
      for (int i = 0; i < arrayToPrint.length; i++) {
         System.out.print(arrayToPrint[i]);
         if (i < arrayToPrint.length - 1) System.out.print(", ");
      }
   }
   
   public static void GenerateArray() {
      boolean correctInput = false;
      int customArraySize = 0;
      
      // while loop that will receive player input, stay in loop until input is valid
      while (!correctInput) {
         boolean passedTryCatch = true;
         System.out.print("Define your array size: ");
         
         // use try catch, since player input may not be correct variable type
         try { customArraySize = input.nextInt(); }
         catch (Exception e) {
            PrintInvalidInput();
            passedTryCatch = false;
            input.nextLine();
         }
         
         // if input is correct variable type, make sure it falls within range of what we want
         if (passedTryCatch) {
            if (customArraySize > 1) correctInput = true;
            else PrintInvalidInput();
         }
      }
      
      // once array size is defined, generate random numbers and print out the array
      initialArray = new int[customArraySize];
      Random random = new Random();
      
      System.out.println();
      System.out.println("This is your array:");
      
      for (int i = 0; i < initialArray.length; i++) {
         initialArray[i] = random.nextInt(100);
      }
      PrintArray(initialArray);
      System.out.println();
      System.out.println();
      
      arrayExists = true;
   }
}