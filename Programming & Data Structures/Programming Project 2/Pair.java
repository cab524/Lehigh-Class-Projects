public class Pair<E1,E2>{
    private E1 first;
    private E2 second;

    public Pair(){
        first = null;
        second = null;
    }
    Pair(E1 f, E2 s){
        first = f;
        second = s;
    }
    public E1 getFirst(){
        return first;
    }
    public E2 getSecond(){
        return second;
    }
    public void setFirst(E1 f){
        first = f;
    }
    public void setSecond(E2 s){
        second = s;
    }
    public String toString(){
        return "(" + first.toString() + ", " + second.toString() + ")";
    }
    public boolean equals(Object o){
        // downcast o to Pair
        if(o instanceof Pair){//safe downcasting
            Pair<E1,E2> pair = (Pair) o;// downcasting
            return this.getFirst().equals(pair.getFirst()) && // equals for type E1
                   this.getSecond().equals(pair.getSecond()); // equals() for type E2
        }
        return false;
    }
}