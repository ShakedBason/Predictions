package engine.Simulation;

import dto.*;
import engine.execution.instance.entity.api.EntityInstance;
import engine.execution.instance.property.PropertyInstance;

import java.util.*;
import java.util.stream.Collectors;

public class SimulationImpl {

    int id;
    String runningSimulationDate;
    Map<String,Integer> startingEntitiesSimulationState;
    Map<String,Integer> endingNameAndPopulation;
    List<EntityInstance> endingInstanceList;
    EndingTerminationReason terminationReason;

    public SimulationImpl(int id, String runningSimulationDate, Map<String, Integer> startingState,List<EntityInstance> ending,EndingTerminationReason terminationReason) {
        this.id = id;
        this.runningSimulationDate = runningSimulationDate;
        startingEntitiesSimulationState = startingState;
        endingInstanceList=ending;
        endingNameAndPopulation=createEndingEntityToPopulation();
        this.terminationReason=terminationReason;
    }

    public void setEndingInstanceList(List<EntityInstance> entityInstances)
    {
        endingInstanceList=entityInstances;
    }

    public SimulationDto createSimulationDto(){

        return new SimulationDto(id,runningSimulationDate,new EntityNamesAndPopulationDto(startingEntitiesSimulationState),new EntityNamesAndPopulationDto(endingNameAndPopulation),createEntitiesDto());
    }

    public List<EntityInstanceDTO> createEntitiesDto(){
        List<EntityInstanceDTO> entityInstanceDTOS=new ArrayList<>();
        //first group all instances by name
        Map<String, List<EntityInstance>> nameToInstances=endingInstanceList.stream().collect(Collectors.groupingBy(EntityInstance::getName));

        for(String entityName:nameToInstances.keySet())
        {
            //now for each entity's property  group all properties by name
            Map<String, List<PropertyInstance>> propertyMap = nameToInstances.get(entityName).
                    stream()
                    .flatMap(entity -> entity.getProps().stream())
                    .collect(Collectors.groupingBy(
                            PropertyInstance::getName,
                            Collectors.toList()));
            entityInstanceDTOS.add(new EntityInstanceDTO(entityName,createPropertyInstancesDtos(propertyMap)));
        }
        return entityInstanceDTOS;
    }

    public List<PropertyInstanceDTO> createPropertyInstancesDtos(Map<String, List<PropertyInstance>> propertyMap){
        List<PropertyInstanceDTO> propertyInstanceDTOS=new ArrayList<>();
        Map<String, Integer> valueCountMap = new HashMap<>();
        for(String propName:propertyMap.keySet()) {
            //now we want to get the propety name and how many of it there is.
                propertyMap.get(propName).forEach(property -> {
                    String value = property.getPropertyValue().toString();
                    valueCountMap.put(value, valueCountMap.getOrDefault(value, 0) + 1);
                });
            propertyInstanceDTOS.add(new PropertyInstanceDTO(propName, new HashMap<String,Integer>(valueCountMap)));
            valueCountMap.clear();
        }
        return propertyInstanceDTOS;
    }

    private Map<String,Integer> createEndingEntityToPopulation(){

        Map<String,Integer> instanceCountMap=new HashMap<>();
        for (EntityInstance instance : endingInstanceList) {
            String name = instance.getName();
            instanceCountMap.put(name, instanceCountMap.getOrDefault(name, 0) + 1);
        }

        return instanceCountMap;
    }

    public String getRunningSimulationDate() {
        return runningSimulationDate;
    }

    public EndingSimulationDetails createEndingDetails (){
        return new EndingSimulationDetails(id,terminationReason);

    }
}
