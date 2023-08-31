package engine.management;

import dto.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public interface Engine {

    void loadWorldDataFromXml(String xmlFilePath ) throws FileNotFoundException;

    WorldDetailsDto getSimulationDetails();

    List<PropertyDetailsDto> getEnvPropertiesDetails();

    void setEnvironment(String envName, Object value);

    EndingSimulationDetails startSimulation();

    List<SimulationIdAndDateDto> getPastSimulationIdentifiers();

    SimulationDto getSimulationDto(int id);

    Map<String,String> getEnvInstancesDetails();

    boolean isRanSimulation();
}
