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

  private int getSelection(){
    int selection;
    Scanner scanner = new Scanner(System.in);
    
    ClassifyHonours.clearScreen();
    
    displayTitle();
    System.out.println("1. Diploma");
    System.out.println("2. Degree");
    System.out.print("Please select your qualification: ");

    if(scanner.hasNextInt()){
      selection = scanner.nextInt();
    }
    else{
      scanner.next();
      selection = 0;
    }

    while(selection != 1 && selection != 2){
      System.out.println();
      System.out.println("Invalid selection!");
      System.out.print("Please select your qualification (1/2): ");

      if(scanner.hasNextInt()){
        selection = scanner.nextInt();
      }
      else{
        scanner.next();
        selection = 0;
      }
    }
    
    scanner.close();

    return selection;
  }

  public static void main(String[] args){
    ClassifyHonours classification = new ClassifyHonours();

    int selection = classification.getSelection();

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