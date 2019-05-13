package kaica_lib.entities;

public class SearchCommand {
    // default "employer"
    private String type = "title";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}