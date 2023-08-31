package dto;

public class SimulationIdAndDateDto {

    int id;
    String date;

    public SimulationIdAndDateDto(int id, String date) {
        this.id = id;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getDate() {
        return date;
    }
}
