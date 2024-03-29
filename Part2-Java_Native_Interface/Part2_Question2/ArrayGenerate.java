import java.util.Scanner;
import java.io.IOException;

class ArrayGenerate{
  private native int[] arrayGenerator(int length);
  private native int arrayMinMaxDiff(int[] arrayNumber);
  private native void arrayMiscellaneous(int[] arrayNumber);

  // To clear the terminal
  public static void clearScreen(){
    try {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } 
    catch (InterruptedException | IOException e) {
      e.printStackTrace();
    }
  }

  // Header line for the CLI program
  private void displayTitle(){
    // clear the terminal
    ArrayGenerate.clearScreen();

    System.out.println("Array Generation");
    System.out.println("----------------------------------------------------");
  }

  // get user input as length for array
  private int inputArrayLength(Scanner scanner){
    int length;
    boolean error;

    do{
      error = false;
      
      // get user input as length
      System.out.print("Size of array to generate: ");
      try{
        length = Integer.parseInt(scanner.nextLine());
      }
      // if user input is not a number
      catch(NumberFormatException e){
        length = 0;
      }

      // if the length is not valid, ask the user to re-enter again, until a valid input is inserted
      if(length < 1){
        System.out.println();
        System.out.println("Invalid input! Size of array must be equal or larger than 1");
        error = true;
      }
    } while(error);

    return length;
  }

  public static void main(String[] args){
    int arrayLength;
    int diff;
    int arrayNum[];
    ArrayGenerate arrayGenerate = new ArrayGenerate();
    
    Scanner scanner = new Scanner(System.in);
    // display the header line
    arrayGenerate.displayTitle();
    // get the length of the array from user
    arrayLength = arrayGenerate.inputArrayLength(scanner);

    // generate the array with random numbers
    arrayNum = arrayGenerate.arrayGenerator(arrayLength);

    // calculate the different between the largest and smallest number
    diff = arrayGenerate.arrayMinMaxDiff(arrayNum);
    System.out.println("The difference between min and max number is " + diff + ".");
    System.out.println();

    // extra features for the array
    arrayGenerate.arrayMiscellaneous(arrayNum);

    System.out.println();
    System.out.print("Press any key to continue...");
    scanner.nextLine();

    // avoid resource leak
    scanner.close();
  }

  static{
    System.loadLibrary("ArrayGenerator");
    System.loadLibrary("ArrayMinMaxDiff");
    System.loadLibrary("ArrayMiscellaneous");
  }
}