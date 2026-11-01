import java.lang.reflect.Field;
import java.util.*;
public class Repository<T> {
    private final Map<Object, T> data = new HashMap<>();
    private final Field idField;
    public Repository(Class<T> type) {
        Field found = null;
        Class<?> current = type;
        while (current != null && found == null) {
            for (Field field : current.getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    found = field;
                    break;
                }
            }
            current = current.getSuperclass();
        }
        if (found == null) {
            throw new IllegalArgumentException(
                    "No field annotated with @Id");
        }
        found.setAccessible(true);
        idField = found;
    }
    private Object getId(T object) {
        try {
            return idField.get(object);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public void save(T object) {
        data.put(getId(object), object);
    }
    public T findById(Object id) {
        return data.get(id);
    }
    public List<T> findAll() {
        return new ArrayList<>(data.values());
    }
    public boolean delete(Object id) {
        return data.remove(id) != null;
    }
    public int count() {
        return data.size();
    }
}
