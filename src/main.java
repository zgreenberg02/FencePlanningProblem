import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.List;



public class main {
    public static int cownum;
    public static int connections;
    public static Cow [] cows;
    public static ArrayList<ArrayList<Cow>> networks= new ArrayList<ArrayList<Cow>>();
    public static int [][] mooconnection;
    public static void main (String args[] ){

        try {
            BufferedReader br = new BufferedReader(new FileReader("fenceplan.in"));
            String [] l1 = br.readLine().split(" ");
            cownum = Integer.parseInt(l1[0]);
            connections = Integer.parseInt(l1[1]);
            cows = new Cow [cownum];
            mooconnection = new int[connections][2];
            for(int i =0; i < cownum; i++){
                String [] l = br.readLine().split(" ");
                cows[i] = new Cow(Integer.parseInt(l[0]),Integer.parseInt(l[1]));
            }
            for(int i = 0; i < connections; i++){
                String [] l = br.readLine().split(" ");
                mooconnection[i][0] = Integer.parseInt(l[0])-1;
                mooconnection[i][1] = Integer.parseInt(l[1])-1;
            }
            br.close();
        } catch (IOException var7) {
            System.out.println(var7.getMessage());
        }
        for(int i =0; i < connections; i++){
            addToNetwork(mooconnection[i][0],mooconnection[i][1]);
        }
        int minPerimeter = findPerimeter(networks.get(0));
        for(int i = 1; i < networks.size(); i++){
            if(findPerimeter(networks.get(i)) < minPerimeter){
                minPerimeter = findPerimeter(networks.get(i));
            }
        }

        PrintWriter writer = null;
        try {
            writer = new PrintWriter("fenceplan.out");
        } catch (FileNotFoundException var6) {
            var6.printStackTrace();
        }
        writer.println(minPerimeter);

        writer.close();




    }

    public static void addToNetwork(int num1, int num2){
        if(!networks.isEmpty()){
            for(int i = 0; i < networks.size(); i++){
                //for( int j = 0; j < networks.get(i).size(); j++){
                    if(networks.get(i).contains(cows[num1]) && networks.get(i).contains(cows[num2])){
                        return;
                    }else if(networks.get(i).contains(cows[num1])){
                        networks.get(i).add(cows[num2]);
                        return;
                    }
                    else if(networks.get(i).contains(cows[num2])){
                        networks.get(i).add(cows[num1]);
                        return;
                    }
               // }
            }
        }
        ArrayList<Cow> n = new ArrayList<Cow>();
        n.add(cows[num1]);
        n.add(cows[num2]);
        networks.add(n);
    }
    public static int findPerimeter(ArrayList<Cow> c){
        int minx = c.get(0).getX();
        int miny = c.get(0).getY();
        int maxx = minx;
        int maxy = miny;
        for(int i = 1; i < c.size(); i++ ){
            if(c.get(i).getY() > maxy){
                maxy = c.get(i).getY();
            }else if(c.get(i).getY() < miny){
                miny = c.get(i).getY();
            }
            if(c.get(i).getX() > maxx){
                maxx = c.get(i).getX();
            }else if(c.get(i).getX() < minx){
                minx = c.get(i).getX();
            }
        }
        return 2*(maxx-minx) + 2*(maxy - miny);
    }

}
