package core;

public class StationsJson {
    private String depth;
    private String name;

    //Объект Station спарсенный из CSV файла
    public StationsJson(String depth, String name) {
        this.depth = depth;
        this.name = name;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Глубина:" + depth + ", название станции: " + name;
    }
}
