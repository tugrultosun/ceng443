import java.util.Random;

/**
 * This class is just created for assigning strategies to Knights
 */
public class helper
{
    public static Strategy nextStrategy() {
        //System.out.println("strategy assigning");
        Random ran = new Random();
        int r=ran.nextInt(100) ;
        if (r<20){
            //System.out.println("attack closest");
            return new AttackClosest();
        }
        else if (r<55){
            //System.out.println("attack weakest");
            return new AttackWeakest();
        }
        else if (r<90){
            //System.out.println("random");
            return new RandomMove();
        }
        else return new AttackWeakest();
    }}
