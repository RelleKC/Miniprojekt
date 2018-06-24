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
        if (writer == null) {
            throw new IOException("BufferedWriter cannot be null.");
        }
        if (map == null) {
            throw new NullPointerException("Map cannot be null.");
        }

        for (String key : map.getKeys()) {
            String parsedKey = key.replace("=", "==");

            try {

                for (String value : map.getValues(key)) {
                    String parsedValue = value.replace("=", "==");
                    writer.write(parsedKey + " = " + parsedValue + "\n");
                }

            } catch (KeyNotFoundException e) {
                throw new IOException("The MultiMap implementation is erroneous.", e);
            }

        }
        writer.close();


    }

    @Override
    public MultiMap<String, String> read(BufferedReader reader) throws IOException {
        if (reader == null) {
            throw new IOException("BufferedReader cannot be null.");
        }

        MultiMap<String, String> multiMap = new MultiHashMap<>();
        String line;

        while ((line = reader.readLine()) != null) {

            if (line.contains(" = ")) {
                String[] stringarray = line.split(" = ");

                if (stringarray.length >= 2) {
                    String key = stringarray[0].replace("==", "=");
                    String value = stringarray[1].replace("==", "=");

                    multiMap.addValue(key, value);
                }

            } else {
                throw new IOException("line <" + line + "> does not contain separator");
            }
        }
        return multiMap;
    }
	
	
}
