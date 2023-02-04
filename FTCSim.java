import java.util.*;

public class FTCSim {
    public static int getJunctionPoints(int x, int y){
        int junctionPoints = 0;
        if((x == 0 && (y==0 || y==2 || y==4)) || (x==2 && (y==0 || y==2 || y==4)) || (x==4 && (y==0 || y==2 || y==4))) {
            junctionPoints = 2;
        } else if((x == 0 && (y==1 || y==3)) || (x==1 && (y==0 || y==4)) || (x == 4 && (y==1 || y==3)) || (x==3 && (y==0 || y==4))){
            junctionPoints = 3;
        } else if((x==1 && (y==1 || y==3)) || (x==1 && (y==1 || y==3))){
            junctionPoints=4;
        } else{
            junctionPoints = 5;
        }
        return junctionPoints;
    }
    public static int isCaptured(String prevCone, String color){
        if(!prevCone.equals(color)){
            return 3;
        } else{
            return 0;
        }
    }

    public static int[][] getJunctionValues(String[][]field){
        int[][] points = new int[5][5];
        int cycleTimes=12;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j<5; j++){
                points[i][j] = getJunctionPoints(i, j) + isCaptured(field[i][j], "Red") - cycleTimes;
            }
            cycleTimes-=2;
        }
        return points;
    }
    public static String chooseCoordinates(int[][] points, String[][] field){
        int maxX = 0;
        int maxY = 0;
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(points[maxX][maxY] < points[i][j]){
                    maxX = i;
                    maxY = j;
                }
            }
        }
        String x = Integer.toString(maxX);
        String y = Integer.toString(maxY);
        field[maxX][maxY] = "Red";
        String returnString = x + ", " + y;
        return returnString;
    }
    
    public static void main(String[] args) {
        int cones = 0;
        int simScore = 0;
        int playerScore = 0;
        String[][] field = new String[5][5];
        int[][] points = new int[5][5];
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                field[i][j] = "Blank";
            }
        }
        while(cones < 30){
            Scanner x = new Scanner(System.in);
            System.out.println("What x do you want to score on?");
            int X = Integer.parseInt(x.nextLine());
            Scanner y = new Scanner(System.in);
            System.out.println("What y do you want to score on?");
            int Y = Integer.parseInt(y.nextLine());
            int playerPoints = getJunctionPoints(X-1, Y-1) + isCaptured(field[X][Y], "Blue");
            playerScore += playerPoints;
            //sim stuff
            points = getJunctionValues(field);
            String chosenString = chooseCoordinates(points, field);
            System.out.println(chosenString);            
            Scanner done = new Scanner(System.in);
            System.out.println("Is the game done: ");
            if(done.nextLine().equals("Yes") || done.nextLine().equals("yes")){
                break;
            }
            cones++;
        }
        System.out.println("Player score: " + playerScore + "\n" + "AI Score: " + simScore);
    }
}
