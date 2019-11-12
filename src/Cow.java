import java.util.*;

public class Cow {
    int x;
    int y;
    //static int cnum = 0;
   // int num;
    List<Integer> connections= new ArrayList<Integer>();
    public Cow(int x, int y){
        //num = cnum;
        //cnum ++;
        this.x = x;
        this.y = y;
    }
    public void addConnection(int c){
        connections.add(c);
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
//    public int getNum() {
//        return num;
//    }
}
