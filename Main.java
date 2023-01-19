import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) {
        try {
            File tmFile = new File(args[0]);
            Scanner myReader = new Scanner(tmFile);
            myReader.nextLine(); // number of states
            myReader.nextLine(); // input alphabet
            myReader.nextLine(); // tape alphabet
            int startState = Integer.parseInt(myReader.nextLine());
            int endState = Integer.parseInt(myReader.nextLine());
            Map<DeltaIn,DeltaOut> delta = new HashMap<>();
            while (myReader.hasNextLine()) {
                String[] line = myReader.nextLine().split(" ");
                DeltaIn in = new DeltaIn(Integer.parseInt(line[0]), line[1].charAt(0));
                DeltaOut out = new DeltaOut(Integer.parseInt(line[2]), line[3].charAt(0), line[4].charAt(0));
                delta.put(in, out);
            }
            myReader.close();
            String word = "";
            if (args.length>1)
                word = args[1];
            TuringMachine tm = new TuringMachine(startState, endState, delta, word);
            tm.run();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    }
}