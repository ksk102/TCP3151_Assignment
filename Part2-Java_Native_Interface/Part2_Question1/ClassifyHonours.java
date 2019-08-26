import java.util.Scanner;
import java.io.IOException;

class ClassifyHonours{
  // Native methods
  private native String classifyDiploma(double cgpa);
  private native String classifyDegree();
  private native void estimateNextGPA(double cgpa);
  
  private double jCgpa;

  // To clear the terminal
  public static void clearScreen(){  
    try {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } catch (InterruptedException | IOException e) {
      e.printStackTrace();
    }
  }

  // Header line for the CLI program
  private void displayTitle(){
    System.out.println("Classification of Honours");
    System.out.println("----------------------------------------------------");
  }

  // get selection of qualification from user
  private int getSelection(Scanner scanner){
    int selection;
    boolean error;
    
    // clear the terminal
    ClassifyHonours.clearScreen();
    
    displayTitle();
    System.out.println("1. Diploma");
    System.out.println("2. Degree");
    System.out.print("Please select your qualification: ");

    do{
      error = false;

      // get the user's input as selection
      try{
        selection = Integer.parseInt(scanner.nextLine());
      }
      // if user input is not a number
      catch(NumberFormatException e){
        selection = 0;
      }

      // if the selection is not 1 or 2, ask the user to re-enter it again until a valid selection is made
      if(selection != 1 && selection != 2){
        System.out.println();
        System.out.println("Invalid selection!");
        System.out.print("Please select your qualification (1|2): ");

        error = true;
      }
    } while(error);

    return selection;
  }

  // Get CGPA from user input
  private double getCgpa(Scanner scanner){
    double cgpa;
    boolean error;
    String decimalError;

    System.out.print("Please enter your current CGPA: ");

    do{
      error = false;
      decimalError = "";

      // get user's input as cgpa
      try{
        cgpa = Double.parseDouble(scanner.nextLine());
      }
      // if user input is not a number
      catch(NumberFormatException e){
        cgpa = -1;
      }

      // if the cgpa is not in the valid range, ask the user to re-enter again, until a valid input is inserted
      if(cgpa < 0 || cgpa > 4){
        error = true;
      }
      // if the cgpa inserted is not 2 decimal places
      else if(!validDecimalPlaces(cgpa, 2)){
        decimalError = " CGPA must be within 2 decimal places";
        error = true;
      }
      
      // display error message
      if(error){
        System.out.println();
        System.out.println("Invalid input!" + decimalError);
        System.out.print("Please enter your current CGPA (0.00 - 4.00): ");
      }

    } while(error);

    return cgpa;
  }

  private boolean validDecimalPlaces(double number, int places){
    String text = Double.toString(Math.abs(number));
    int integerPlaces = text.indexOf('.');
    int decimalPlaces = text.length() - integerPlaces - 1;

    return (decimalPlaces <= places);
  }

  // To calculate the GPA to get for current trimester, given the target CGPA
  private boolean calculateNextGPA(Scanner scanner){
    String input;

    System.out.println();
    System.out.print("Do you wish to calculate the target GPA to get for current trimester? (Y|N): ");
    // get user's input
    input = scanner.nextLine();
    input = input.toUpperCase();

    // if input is not "Y" or "YES", will be evaluate as "N" or false
    if (input.equals("Y") || input.equals("YES")){
      return true;
    }
    else{
      return false;
    }
  }

  public static void main(String[] args){
    String honour = "";
    boolean calculateNext = false;
    ClassifyHonours classification = new ClassifyHonours();

    Scanner scanner = new Scanner(System.in);
    // Get user input for qualification
    int selection = classification.getSelection(scanner);
    // Get user inpur as CGPA
    double cgpa = classification.getCgpa(scanner);

    // if the cgpa is fail
    if(cgpa < 2){
      System.out.println("Your are not qualified to get any certificate with your current CGPA of " + cgpa + ".");
    }
    else{
      switch(selection){
        case 1:
          // return the classification of honour
          honour = classification.classifyDiploma(cgpa);
          break;
        case 2:
          // just to demostrate another way to get cgpa'value
          // assign cgpa to instance variable 'jCgpa'
          classification.jCgpa = cgpa;
          // return the classification of honour
          honour = classification.classifyDegree();
          break;
      }

      // Display the result in command prompt
      System.out.println("Your are qualified to get \"" + honour + "\" certificate with your current CGPA of " + cgpa + ".");
    }

    // To calculate the GPA to get for current trimester, given the target CGPA
    calculateNext = classification.calculateNextGPA(scanner);
    if(calculateNext){
      classification.estimateNextGPA(cgpa);
    }

    // avoid resource leak
    scanner.close();
  }

  // Load library files
  static{
    System.loadLibrary("DiplomaClassification");
    System.loadLibrary("DegreeClassification");
    System.loadLibrary("EstimateNextGPA");
  }
}