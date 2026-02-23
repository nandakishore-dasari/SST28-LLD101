import java.util.*;

public class InputParser {
    public Map<String, String> parse(String raw) {
        Map<String, String> data = new LinkedHashMap<>();
        String[] parts = raw.split(";");
        for (String p : parts) {
            String[] pair = p.split("=", 2);
            if (pair.length == 2) {
                data.put(pair[0].trim(), pair[1].trim());
            }
        }
        return data;
    }
}