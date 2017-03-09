
package assignment1;

/**
 *
 * @author Jagmeet Singh Oberoi
 */
import java.util.Scanner;
 
public class tQ1
{
    public static final int NCHARS = 256;
 
    public static int getNextState(char[] pat, int M, int state, int x)
    {
        
        if (state < M && x == pat[state])
            return state + 1;
        int ns, i;
       
        for (ns = state; ns > 0; ns--)
        {
            if (pat[ns - 1] == x)
            {
                for (i = 0; i < ns - 1; i++)
                {
                    if (pat[i] != pat[state - ns + 1 + i])
                        break;
                }
                if (i == ns - 1)
                    return ns;
            }
        }
        return 0;
    }
 
   
    public static void compute(char[] pat, int M, int[][] tf)
    {
        int state, x;
        for (state = 0; state <= M; ++state)
            for (x = 0; x < NCHARS; ++x)
                tf[state][x] = getNextState(pat, M, state, x);
    }
 
    
    public static void search(char[] pat, char[] text)
    {
        int M = pat.length;
        int N = text.length;
        int[][] tf = new int[M + 1][NCHARS];
        compute(pat, M, tf);
      
        int i, state = 0;
        for (i = 0; i < N; i++)
        {
            state = tf[state][text[i]];
            if (state == M)
            {
                System.out.print(pat);
                System.out.print(" String Found at Position " + (i - M + 1));
            }
        }
    }
 
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the String: ");
        String main = input.nextLine();
        System.out.println("Enter the Pattern you want to search For : ");
        String pattern = input.nextLine();
        search(pattern.toCharArray(), main.toCharArray());
        input.close();
    }
}
