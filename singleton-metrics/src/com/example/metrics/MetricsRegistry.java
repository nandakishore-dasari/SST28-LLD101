package com.example.metrics;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MetricsRegistry implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    // volatile ensures that multiple threads handle the INSTANCE correctly
    // when it is being initialized.
    private static volatile MetricsRegistry INSTANCE; 
    private final Map<String, Long> counters = new HashMap<>();

    // 1) Private constructor prevents direct instantiation.
    // 2) Reflection protection: Throw exception if instance already exists.
    private MetricsRegistry() {
        if (INSTANCE != null) {
            throw new IllegalStateException("Instance already exists. Use getInstance().");
        }
    }

    // Double-checked locking for thread-safe lazy initialization.
    public static MetricsRegistry getInstance() {
        if (INSTANCE == null) {
            synchronized (MetricsRegistry.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MetricsRegistry();
                }
            }
        }
        return INSTANCE;
    }

    public synchronized void setCount(String key, long value) {
        counters.put(key, value);
    }

    public synchronized void increment(String key) {
        counters.put(key, getCount(key) + 1);
    }

    public synchronized long getCount(String key) {
        return counters.getOrDefault(key, 0L);
    }

    public synchronized Map<String, Long> getAll() {
        return Collections.unmodifiableMap(new HashMap<>(counters));
    }

    // 3) Preserve singleton on serialization: readResolve is called 
    // during deserialization to replace the object.
    @Serial
    protected Object readResolve() {
        return getInstance();
    }
}