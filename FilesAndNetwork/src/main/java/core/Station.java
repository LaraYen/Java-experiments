package core;

//Объект Station спарсенный из HTML страницы
public class Station
{
    private final Line line;
    private final String name;
    private float depth;

    public Station (Line line, String name){
        this.line = line;
        this.name = name;
    }

    public String getLine(){
        return line.getNumber();
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "Линия " + line.getNumber() + ". " + name + ". Глубина:" + depth ;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }
}
