import management.PredictionsManagement;
import management.PredictionsManagementImpl;

public class Main {
    public static void main(String[] args) {

        PredictionsManagement predictionsManagement=new PredictionsManagementImpl();
        predictionsManagement.run();
        }
    }
