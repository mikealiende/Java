package Client;
import java.io.Serializable;

// Interval.java
public class Interval implements Serializable{
    private int lowerBound;
    private int upperBound;

    Interval(int lowerBound, int upperBound){
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public int getLowerBound(){
        return this.lowerBound;
    }
    
    public int getUpperBound(){
        return this.upperBound;
    }
}

