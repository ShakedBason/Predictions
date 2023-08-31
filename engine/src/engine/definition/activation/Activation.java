package engine.definition.activation;

public interface Activation {

    boolean isActive(int ticks);

     int getTicks();
     double getProbability();
}
