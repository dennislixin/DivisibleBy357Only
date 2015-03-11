import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class DivisibleBy357Only
{
  private static final int NUM1 = 3;
  private static final int NUM2 = 5;
  private static final int NUM3 = 7;

  private static final int MAX_NUM = 1000000;
  public static void main(String[] args)
  {
    Set<Integer> expected = findDivisibleNumDumb();
    Set<Integer> result1 = findDivisibleNumSmart();
    Set<Integer> result2 = findDivisibleNumSmarter();


    if(!result1.equals(expected))
    {
      System.out.println("expected = " + Arrays.toString(expected.toArray()));
      System.out.println("result1 = " + Arrays.toString(result1.toArray()));
    }

    if(!result2.equals(expected))
    {
      System.out.println("expected = " + Arrays.toString(expected.toArray()));
      System.out.println("result2 = " + Arrays.toString(result2.toArray()));
    }
  }

  public static Set<Integer> findDivisibleNumDumb()
  {
    Set<Integer> divisibleNum = new HashSet<Integer>();
    for(int i = 2; i <= MAX_NUM; i++)
    {
      if(isDivisibleBruteForce(i))
        divisibleNum.add(i);
    }
    return divisibleNum;
  }

  public static boolean isDivisibleBruteForce(int num)
  {
    if(num == 1)
      return false;

    while( num % NUM1 == 0)
    {
      num = num / NUM1;
    }
    while( num % NUM2 == 0)
    {
      num = num / NUM2;
    }
    while( num % NUM3 == 0)
    {
      num = num / NUM3;
    }
    return num == 1;
  }

  public static Set<Integer> findDivisibleNumSmart()
  {
    int cur1 = 1;
    int cur2 = 1;
    int cur3 = 1;
    Set<Integer> divisibleNum = new HashSet<Integer>();

    do
    {
      cur2 = cur1;

      do
      {
        cur3 = cur2;

        do
        {
          divisibleNum.add(cur3);
          cur3 *= NUM3;
        } while( cur3 < MAX_NUM);

        cur2 *= NUM2;
      } while( cur2 < MAX_NUM);

      cur1 *= NUM1;
    } while(cur1 < MAX_NUM);

    divisibleNum.remove(1);
    return divisibleNum;
  }

  public static Set<Integer> findDivisibleNumSmarter()
  {
    Set<Integer> divisibleNum = new HashSet<>();
    Queue<Integer> ququeNum = new LinkedList<Integer>();
    ququeNum.add(1);

    Integer curQueueNum, product;

    int[] baseNum = {NUM1, NUM2, NUM3};

    while( (curQueueNum = ququeNum.poll()) != null)
    {
      for(int i=0; i < baseNum.length; i++)
      {
        product = curQueueNum * baseNum[i];
        if( product < MAX_NUM )
        {
          ququeNum.add(product);
          divisibleNum.add(product);
        }
      }
    }
    return divisibleNum;
  }
}
