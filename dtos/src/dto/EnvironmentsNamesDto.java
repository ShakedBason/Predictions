package dto;

import java.util.List;

public class EnvironmentsNamesDto {

    List<String> names;

    public EnvironmentsNamesDto(List<String> names) {
        this.names = names;
    }

    public List<String> getNames() {
        return names;
    }
}
