import java.util.Scanner;
import java.io.IOException;

class ClassifyHonours{
  // Native methods
  private native void classifyDiploma(double cgpa);
  private native void classifyDegree(double cgpa);

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
    
    // clear the terminal
    ClassifyHonours.clearScreen();
    
    displayTitle();
    System.out.println("1. Diploma");
    System.out.println("2. Degree");
    System.out.print("Please select your qualification: ");
    // get the user's input as selection
    try{
      selection = Integer.parseInt(scanner.nextLine());
    }
    // if user input is not a number
    catch(NumberFormatException e){
      selection = 0;
    }
    

    // if the selection is not 1 or 2, ask the user to re-enter it again until a valid selection is made
    while(selection != 1 && selection != 2){
      System.out.println();
      System.out.println("Invalid selection!");
      System.out.print("Please select your qualification (1|2): ");
      // get the user's input as selection
      try{
        selection = Integer.parseInt(scanner.nextLine());
      }
      // if user input is not a number
      catch(NumberFormatException e){
        selection = 0;
      }
    }

    return selection;
  }

  // Get CGPA from user input
  private double getCgpa(Scanner scanner){
    double cgpa;

    System.out.print("Please enter your CGPA: ");
    // get user's input as cgpa
    try{
      cgpa = Double.parseDouble(scanner.nextLine());
    }
    // if user input is not a number
    catch(NumberFormatException e){
      cgpa = -1;
    }

    // if the cgpa is not in the valid range, ask the user to re-enter again, until a valid input is inserted
    while(cgpa < 0 || cgpa > 4){
      System.out.println();
      System.out.println("Invalid selection!");
      System.out.print("Please enter your CGPA (0.00 - 4.00): ");

      // get user's input as cgpa
      try{
        cgpa = Double.parseDouble(scanner.nextLine());
      }
      // if user input is not a number
      catch(NumberFormatException e){
        cgpa = -1;
      }
    }

    return cgpa;
  }

  public static void main(String[] args){
    ClassifyHonours classification = new ClassifyHonours();

    Scanner scanner = new Scanner(System.in);

    // Get user input for qualification
    int selection = classification.getSelection(scanner);
    // Get user inpur as CGPA
    double cgpa = classification.getCgpa(scanner);

    // avoid resource leak
    scanner.close();

    switch(selection){
      case 1:
        classification.classifyDiploma(cgpa);
        break;
      case 2:
        classification.classifyDegree(cgpa);
        break;
    }
  }

  // Load library files
  static{
    System.loadLibrary("DiplomaClassification");
    System.loadLibrary("DegreeClassification");
  }
}