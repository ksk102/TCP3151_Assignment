import java.util.Scanner;
import java.io.IOException;

class ClassifyHonours{
  private native void classifyDiploma();
  private native void classifyDegree();

  public static void clearScreen(){  
    try {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } catch (InterruptedException | IOException e) {
      e.printStackTrace();
    }
  }

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

  public static void main(String[] args){
    ClassifyHonours classification = new ClassifyHonours();

    Scanner scanner = new Scanner(System.in);

    int selection = classification.getSelection(scanner);
    scanner.close();

    switch(selection){
      case 1:
        classification.classifyDiploma();
        break;
      case 2:
        classification.classifyDegree();
        break;
    }
  }

  static{
    System.loadLibrary("DiplomaClassification");
    System.loadLibrary("DegreeClassification");
  }
}