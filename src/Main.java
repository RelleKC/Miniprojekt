import java.io.*;

public class Main {

    public static void main(String[] args) {
        MultiHashMap<String, String> multiMap = new MultiHashMap<>();
        multiMap.addValue("A","B");
        multiMap.addValue("A","C");
        multiMap.addValue("A","D");
        multiMap.addValue("B","A");
        multiMap.addValue("B","C");
        multiMap.addValue("B","D");
        multiMap.addValue("C","A");
        multiMap.addValue("C","B");
        multiMap.addValue("C","D");
        System.out.println(multiMap.toString());
        System.out.println("-------------------------------------------------------");

        try {
            multiMap.removeKey("B");
        } catch (KeyNotFoundException e) {
            System.err.println("Key not found");
        }

        System.out.println(multiMap.toString());
        System.out.println("-------------------------------------------------------");

        try {
            multiMap.removeValue("A","B");
            multiMap.removeValue("A","C");
            multiMap.removeValue("A","D");
        } catch (KeyNotFoundException e) {
            System.err.println("Key not found");
        }

        System.out.println(multiMap.toString());
        System.out.println("-------------------------------------------------------");

        StringMapIO stringMapIO = new StringMapIO();
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(new File("test.config")));
            stringMapIO.write(writer, multiMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        MultiMap<String, String> copy = new MultiHashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("test.config")));
            copy = stringMapIO.read(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
