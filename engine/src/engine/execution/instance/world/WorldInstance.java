package engine.execution.instance.world;

import engine.Simulation.SimulationImpl;

import java.util.Map;

public interface WorldInstance {
    SimulationImpl runSimulation(int simulationNumber);

    void setEnvironment(String envName, Object value);

    Map<String,String> getEnvDetailsAndResetEnvironment();
}
