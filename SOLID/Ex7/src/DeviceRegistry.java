import java.util.*;

public class DeviceRegistry {
    private final List<Object> devices = new ArrayList<>();

    public void add(Object d) { devices.add(d); }

    @SuppressWarnings("unchecked")
    public <T> T getFirstOf(Class<T> type) {
        for (Object d : devices) {
            if (type.isInstance(d)) return (T) d;
        }
        throw new IllegalStateException("Missing device of type: " + type.getSimpleName());
    }
}