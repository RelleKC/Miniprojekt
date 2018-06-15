import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * A utility class that reads and writes multi maps containing strings
 * to a file in the following format: key = value\newline
 * 
 * @author Marcus
 */
public class StringMapIO implements MultiMapIO<String, String>{
    @Override
    public void write(BufferedWriter writer, MultiMap<String, String> map) throws IOException {

        try {
            for (String key : map.getKeys()) {
                for (String value : map.getValues(key)) {
                    writer.write(key + " = " + value + "\n");
                }
            }
        } catch (KeyNotFoundException e) {
            throw new IOException("The MultiMap implementation is erroneous.", e);
        }
        writer.close();


    }

    @Override
    public MultiMap<String, String> read(BufferedReader reader) throws IOException {
        MultiMap<String, String> multiMap = new MultiHashMap<>();
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.contains(" = ")) {
                String[] stringarray = line.split(" = ");
                if (stringarray.length >= 2) {
                    multiMap.addValue(stringarray[0], stringarray[1]);
                }
            } else {
                throw new IOException("<" + line + "> does not contain separator");
            }
        }
        return multiMap;
    }
	
	
}
