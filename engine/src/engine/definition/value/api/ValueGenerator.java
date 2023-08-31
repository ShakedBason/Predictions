package engine.definition.value.api;

//T is int/bool....
public interface ValueGenerator<T> {
    T generateValue();
}
