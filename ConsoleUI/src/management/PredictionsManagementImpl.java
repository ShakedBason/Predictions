package management;
import dto.*;
import engine.exceptions.EntityDetailsNotValid;
import engine.exceptions.EnvironmentDetailsNotValid;
import engine.exceptions.RuleDetailsNotValid;
import engine.exceptions.XmlFileNameNotValid;
import engine.management.EngineImpl;
import menu.MenuImpl;
import menu.MenuItem;
import engine.management.Engine;

import java.io.FileNotFoundException;
import java.util.*;

public class PredictionsManagementImpl implements PredictionsManagement{

    MenuItem menu;
    Engine engine;
    boolean isLoadedWorld;
    boolean isRunning;

    public PredictionsManagementImpl() {
        this.menu = new MenuImpl();
        engine=new EngineImpl();
        isLoadedWorld=false;
        isRunning=true;

    }

    @Override
    public void run() {
        System.out.println("Welcome to Predictions!");
        while (isRunning){
        int userChoice= getUserOperationChoice();
        if(userChoice>=2&&userChoice<=4&&!isLoadedWorld)
            System.out.println("Error: please load an xml file first");
        else{
            switch (userChoice){
                case 1:
                    loadXmlRequest();
                    break;
                case 2:
                    printSimulationDetails();
                    break;
                case 3:
                    startSimulation();
                    break;
                case 4:
                    ShowPastSimulationDetails();
                    System.out.println("---End of past simulation display---");
                    break;
                case 5:
                    isRunning=false;
                    break;
                default:System.out.println("Invalid operation choice, please try again");
            }
        }}
    }

    private int getUserOperationChoice(){
        while (true)
        {
            try {
                menu.showMenu();
                Scanner scanner=new Scanner(System.in);
                int userChoice = scanner.nextInt();
                if (userChoice >= 1 && userChoice <= 5)
                    return userChoice;
                else {
                    System.out.println("Error: invalid choice number,please try again choice number should be between 1-5");
                }
            }
            catch (InputMismatchException e){
                System.out.println("Error: choice should be a decimal number");
        }}
    }


    private void loadXmlRequest(){

        boolean isFinished=false;
        while (!isFinished){
        try{
        menu.printLoadDataMessage();
        Scanner scanner=new Scanner(System.in);
        String fileName=scanner.nextLine();
        //if the user wanted to get back to menu
        if(fileName.equals("0"))
            return;

        engine.loadWorldDataFromXml(fileName);
        isLoadedWorld=true;
        isFinished=true;
        }
        catch(XmlFileNameNotValid | FileNotFoundException | EntityDetailsNotValid| EnvironmentDetailsNotValid | RuleDetailsNotValid|IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
        }}
        System.out.println("Loading xml file succeeded!");
    }


    public void printSimulationDetails(){
        WorldDetailsDto worldDetailsDto=engine.getSimulationDetails();
        List<EntityDetailsDto> entityDetailsDto=worldDetailsDto.getEntitiesDetails();
        List<RuleDetailsDto> ruleDetailsDtos=worldDetailsDto.getRulesDetails();
        printEntityDetails(entityDetailsDto);
        printRulesDetails(ruleDetailsDtos);
        printTerminationConditions(worldDetailsDto.getTerminationDetails());
        System.out.println("----End of simulation details-----");
    }


    private void printEntityDetails(List<EntityDetailsDto> entityDetailsDto){
        System.out.println("--------Entities Details:---------");
        for(EntityDetailsDto entity:entityDetailsDto)
        {
            String entitiesDetails=String.format("Entity name: %s\nPopulation:  %d",entity.getName(),entity.getPopulation());
            System.out.println(entitiesDetails);
            List<PropertyDetailsDto> properties=entity.getEntityProperties();
            System.out.println("----Entity Properties Details:----");
            int propNumber=0;
            for(PropertyDetailsDto property:properties)
            {
                propNumber++;
                String propertyDetailsString=String.format(propNumber+")  Property name: %s\n\tType:         %s\n\tis random:    %b",property.getPropertyName(),property.getType(),property.isRandom());
                if(property.getTo()!=null&&property.getFrom()!=null)
                {
                    propertyDetailsString=String.format( "%s\n\tRange:        from %s to %s",propertyDetailsString,property.getFrom().toString(),property.getTo().toString());
                }
                System.out.println(propertyDetailsString+"\n");
                //C:\Users\97254\IdeaProjects\Predictions\engine\src\resources\ex1-cigarets.xml
            }
        }
    }

    private void printRulesDetails(List<RuleDetailsDto> ruleDetailsDtos)
    {
        System.out.println("----------Rules details:----------");
        int ruleNum=0;
        for(RuleDetailsDto ruleDetails:ruleDetailsDtos)
        {
            ruleNum++;
            List<String> actionsNames= ruleDetails.getActionsNames();
            String rule=String.format(ruleNum+")  Rule name:     %s\n\tTicks:         %d\n\tProbability:   %.3f\n\tactions count: %d",ruleDetails.getName(),ruleDetails.getTicks(),ruleDetails.getProbability(),ruleDetails.getActionsNames().size());;
            System.out.println(rule);
            System.out.println("\tRule actions:");
            actionsNames.forEach(actionsName-> System.out.println("\t"+actionsName+ "\n"));
        }
    }

    private void printTerminationConditions(TerminationDetailsDto terminationDetailsDto){
        System.out.println("------Termination conditions------");
        System.out.println(String.format("\tTicks:   %d\n\tSeconds: %d",terminationDetailsDto.getTicks(),terminationDetailsDto.getSeconds()));
    }

    public void startSimulation(){
        if(!isLoadedWorld)
            System.out.println("Please load an xml file first");
        else {
        setEnvironments();
        EndingSimulationDetails details=engine.startSimulation();
        showEnvValues();
        showEndingSimulationDetails(details);
        }

    }

    private void setEnvironments(){

        List<PropertyDetailsDto> envToSet=engine.getEnvPropertiesDetails();
        Map<String,Object> envNameToValue;
        boolean isDone=false;
        while (!isDone){
            Scanner scanner=new Scanner(System.in);
            printEnvNames(envToSet);
            try {
                int choice=scanner.nextInt();
                scanner.nextLine();
                if(choice==0)
                    isDone = true;
                else {
                PropertyDetailsDto chosenEnv=envToSet.get(choice-1);
                System.out.println("Please enter a value for "+chosenEnv.getPropertyName());
                Object value=scanner.nextLine();
                engine.setEnvironment(chosenEnv.getPropertyName(),value);
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid input, please enter a number.");
            }
            catch (IndexOutOfBoundsException e)
            {
                System.out.println("Invalid choice, there is no option with that choice number please try again.");
            }
            catch (IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

    private void printEnvNames( List<PropertyDetailsDto> envToSet){
        System.out.println("Please enter the environment variable number of the environment variable you want to set ");
        System.out.println("0)i am done setting the environment variables,continue");
        int count=1;
        for(PropertyDetailsDto envName:envToSet) {
            System.out.println(count+")"+envName.getPropertyName());
            count++;
        }
        //System.out.println("Enter L to Load new xml file instead");
    }

    private void showEnvValues(){
        Map<String,String> envDetails=engine.getEnvInstancesDetails();
        for(String envName:envDetails.keySet()){
            System.out.println("Environment name:  "+envName);
            System.out.println("Environment value: "+envDetails.get(envName));
        }
    }

    private void showEndingSimulationDetails(EndingSimulationDetails endingSimulationDetails){
        System.out.println("-----Simulation ended!-----");
        System.out.println("Simulation ID: "+endingSimulationDetails.getId());
        System.out.println("Termination reason: reached "+endingSimulationDetails.getTerminationDetailsDto().getValue()+" "+endingSimulationDetails.getTerminationDetailsDto().getName());
    }

    private int getUserChoice(int max){
        Scanner scanner=new Scanner(System.in);
        int choice=scanner.nextInt();
        if(choice>=1&&choice<=max)
            return choice;
        else
            throw new IllegalArgumentException();
    }

    private int getPastSimulationUserChoice(){
        while(true){
        try{
        int count=1;
        List<SimulationIdAndDateDto> simulationsIdentifiers=engine.getPastSimulationIdentifiers();
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please choose a past simulation number:");
        for(SimulationIdAndDateDto identifier:simulationsIdentifiers){
            System.out.println(count+")Simulation ID: "+identifier.getId());
            System.out.println("Running simulation date: "+identifier.getDate());
            count++;
        }
        return getUserChoice(simulationsIdentifiers.size());
        }
        catch (InputMismatchException |IllegalArgumentException e)
        {
            System.out.println("Invalid input, please enter a number.");
        }
        }
    }

    private int getPastSimulationRepresentationOption(){
        while(true){
        try{
        System.out.println("please choose past simulation display option: ");
        System.out.println("1)By entities amount\n2)By Properties histogram");
            return getUserChoice(2);
        }
        catch (InputMismatchException |IllegalArgumentException e)
        {
            System.out.println("Invalid choice, please try again");
        }}
    }


    private void ShowPastSimulationDetails(){
        if(!engine.isRanSimulation())
        {
            System.out.println("There are no past simulations, run a simulation first");
            return;
        }
        int userChoice=getPastSimulationUserChoice();
        SimulationDto simulation=engine.getSimulationDto(userChoice);
        int representationChoice=getPastSimulationRepresentationOption();
        if(representationChoice==1)
            showPastSimulationByEntities(simulation);
        else
            showPastSimulationByProperty(simulation);
    }

    private void showPastSimulationByEntities(SimulationDto simulationDto){
        System.out.println("--Past simulation details--");
        Map<String,Integer>starting=simulationDto.getStartingState().getNameToPopulation();
        Map<String,Integer> ending=simulationDto.getEndingState().getNameToPopulation();
        int count=1;
        for(String entityName: starting.keySet())
        {
            System.out.println("1)Entity name:     "+entityName);
            System.out.println("\tStarting amount: "+starting.get(entityName));
            int endingAmount= ending.getOrDefault(entityName, 0);
            System.out.println("\tEnding amount:   "+endingAmount);
        }
    }

    private int getPastSimulationEntityChoice(List<EntityInstanceDTO> endingStateEntities){
        while(true) {
            try {
                int count = 1;
                System.out.println("Please choose an entity");
                for (EntityInstanceDTO entityInstanceDTO : endingStateEntities) {
                    System.out.println(count + ")" + entityInstanceDTO.getName());
                    count++;
                }
                return getUserChoice(endingStateEntities.size());
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.println("Invalid choice, please try again");
            }
        }
    }

    private int getDesiredPropertyToDisplay(List<PropertyInstanceDTO> propertyDetailsDtos)
    {
        while(true) {
            try {
                int count = 1;
                System.out.println("Please choose a property: ");
                for (PropertyInstanceDTO propertyInstanceDTO : propertyDetailsDtos) {
                    System.out.println(count + ")" + propertyInstanceDTO.getName());
                    count++;
                }
                return getUserChoice(propertyDetailsDtos.size());
            } catch (InputMismatchException | IllegalArgumentException e) {
                System.out.println("Invalid choice, please try again");
            }
        }
    }


    private void showPastSimulationByProperty(SimulationDto simulationDto)
    {
        List<EntityInstanceDTO> endingStateEntities=simulationDto.getEndingStateEntities();
        if(endingStateEntities.isEmpty()) {
            System.out.println("There is no entity who survived");
            return;
        }
        int choice=getPastSimulationEntityChoice(endingStateEntities);
        EntityInstanceDTO chosenEntity=endingStateEntities.get(choice-1);
        if(chosenEntity.getProperties().isEmpty()){
            System.out.println("Chosen entity doesn't have any properties ");
            return;
        }
        int chosenPropertyIndex=getDesiredPropertyToDisplay(chosenEntity.getProperties());
        PropertyInstanceDTO chosenProperty=chosenEntity.getProperties().get(chosenPropertyIndex-1);
        Map<String,Integer> chosenPropDetails=chosenProperty.getValueToPopulation();
        System.out.println("---Past simulation property details---");
        for(String value:chosenPropDetails.keySet())
        {
            System.out.println(String.format("There are %d property instances with the value %s",chosenPropDetails.get(value),value));
        }
    }


}
