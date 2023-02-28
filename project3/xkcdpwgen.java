
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class xkcdpwgen {
  public static void main(String[] args) {
    int numWords = 4; // Default number of words
    int numCaps = 0; // Default number of capital letters
    int numNum = 0;
    int numSymbol = 0;

    // Parse command line arguments
    for (int i = 0; i < args.length; i += 1) {
      if (args[i].equals("-h") || args[i].equals("--help")) {
        System.out.println("usage: xkcdpwgen [-h] [-w WORDS] [-c CAPS] [-n NUMBERS] [-s SYMBOLS]");
        System.out.println("\n Generate a secure, memorable password using the XKCD method");
        System.out.println("\n optional arguments:");
        System.out.println("\n     -h, --help            show this help message and exit");
        System.out.println("    -w WORDS, --words WORDS");
        System.out.println("                          include WORDS words in the password (default=4)");
        System.out.println("    -c CAPS, --caps CAPS  capitalize the first letter of CAPS random words");
        System.out.println("                          (default=0)");
        System.out.println("    -n NUMBERS, --numbers NUMBERS");
        System.out.println("                          insert NUMBERS random numbers in the password");
        System.out.println("                          (default=0)");
        System.out.println("    -s SYMBOLS, --symbols SYMBOLS");
        System.out.println("                          insert SYMBOLS random symbols in the password");
        System.out.println("                          (default=0)");
        System.exit(0);
      }
      else if ((args[i].equals("-w") || args[i].equals("--words")) && i < args.length - 1) {
        // Parse number of words parameter

        numWords = Integer.parseInt(args[i + 1]);

      }
      else if ((args[i].equals("-c") || args[i].equals("--caps")) && i < args.length - 1) {
        // Parse number of capital letters parameter

        numCaps = Integer.parseInt(args[i + 1]);

      }

      else if ((args[i].equals("-n") || args[i].equals("--numbers")) && i < args.length - 1) {
        // Parse number of capital letters parameter

        numNum = Integer.parseInt(args[i + 1]);

      }

      else if ((args[i].equals("-s") || args[i].equals("--symbols")) && i < args.length - 1) {
        // Parse number of capital letters parameter

        numSymbol = Integer.parseInt(args[i + 1]);

      }
    }

    // Read words from file
    ArrayList<String> words = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader("words.txt"))) {
      String line;
      while ((line = br.readLine()) != null) {
        words.add(line);
      }
    }
    catch (IOException e) {
      System.err.println("Error reading words file: " + e.getMessage());
      return;
    }

    // Generate the words or strings of the specified length and with the specified
    // number of capital letters
    Random random = new Random();
    String password = "";
    ArrayList<String> passwordArr = new ArrayList<>();
    
    //adds the correct amount of words and the words are random in the Arraylist of words
    
  
    for(int i =0; i < numWords; i += 1 ) {
      passwordArr.add(words.get(random.nextInt(words.size()-1)));
    }
    
    // loop to capitalize numCaps amounts of the first letter in a word in the arr
    for(int i =0; i < numCaps; i += 1 ) {
      int capAt = random.nextInt(passwordArr.size()-1);
      
      // loop to make sure the first letter isn't already upper case
      while(Character.isUpperCase(passwordArr.get(capAt).charAt(0))) {
        capAt = random.nextInt(passwordArr.size()-1);
      }
      
      String capitalVersion =
          Character.toUpperCase(passwordArr.get(capAt).charAt(0)) 
          + passwordArr.get(capAt).substring(1);
      passwordArr.set(capAt, capitalVersion);
    }
    
    
    for(int i =0; i < passwordArr.size(); i += 1) {
      password = password + passwordArr.get(i);
    }
    
    
    for(int i = 0; i < numNum; i += 1 ) {
      int numAt = random.nextInt(password.length() -1);
      int numInsert = random.nextInt(9);
      
      password = password.substring(0, numAt) + numInsert + password.substring(numAt);
    }
    
    String symbols = "~!@#$%^&*.:;";
    for(int i = 0; i < numSymbol; i += 1 ) {
      int symbolAt = random.nextInt(password.length() -1);
      int symbolIndex = random.nextInt(symbols.length());
      String symbolInsert = Character.toString(symbols.charAt(symbolIndex));
      
      password = password.substring(0, symbolAt) + symbolInsert + password.substring(symbolAt);
    }
    
    System.out.println(password);
  }
}

// use a contains method to find the arguments init
// to add in a random number or symbol use a random function and do it from 0 to length and 
// dubstring 0   o random and then add in and the nsubstring the random to l
