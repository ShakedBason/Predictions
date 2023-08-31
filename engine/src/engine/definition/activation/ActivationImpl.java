package engine.definition.activation;

import java.util.Random;

public class ActivationImpl implements Activation {
    private final int ticks;
    private final double probability;
    private Random random;

    //static validProb(?)
    public ActivationImpl(Integer reqTicks,Double reqProbability)
    {
        random=new Random();

        if(reqTicks!=null)
            ticks=reqTicks;
        else
            ticks=1;
        if(reqProbability!=null)
            probability=reqProbability;
        else
            probability=1;
    }
    public ActivationImpl(){
        random=new Random();
        ticks=1;
        probability=1;
    }

    @Override
    public boolean isActive(int ticks) {
        float randomProbability=random.nextFloat();
        return (randomProbability < probability) && (ticks % this.ticks == 0);
    }

    @Override
    public int getTicks() {
        return ticks;
    }

    @Override
    public double getProbability() {
        return probability;
    }
}
