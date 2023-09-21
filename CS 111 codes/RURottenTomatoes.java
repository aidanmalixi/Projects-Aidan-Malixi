 //*RURottenTomatoes creates a 2 dimensional array of movie ratings 
/* from the command line arguments and displays the index of the movie 
 /* that has the highest sum of ratings.
 *
 *  java RURottenTomatoes 3 2 5 2 3 3 4 1
 *  0
 *************************************************************************/

public class RURottenTomatoes {

    public static void main(String[] args) {
int reviewers = Integer.parseInt(args[0]);
int movies = Integer.parseInt(args[1]);

int [][] rating = new int [reviewers][movies];
int count = 2;
for (int i = 0; i < reviewers; i++ ){
        for (int j = 0; j < movies; j++){
            rating [i][j] = Integer.parseInt(args[count++]);

        }
        // WRITE YOUR CODE HERE
    }
    int n = 0;
    int total2 = 0;
    for (int j = 0; j < movies; j++){
        int total1 = 0;
        for (int i = 0; i < reviewers; i++)
        {
            total1 += rating[i][j];
        }
        if (total1 > total2){
            total2 = total1;
            n = j;
        }
    }
    System.out.println(n);
}
}