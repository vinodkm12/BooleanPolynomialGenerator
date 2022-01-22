import java.util.HashSet;
import java.util.ArrayList;
class Driver
{
    public static void main(String[] args)
    {
        //EXAMPLE TEST CODE
        
        /*
        boolean[] test = {false, true, true, false, false, true, true, true, false, true, true, true, true, false, true, true,
                           true, false, false, true, true, true, false, true, true, false, false, true, true, false, true, false   };
        boolean[] test2 = new boolean[32];
        for (int i = 0; i < 32; i++)
        {
            if (test[i])
                test2[i] = false;
            else
                test2[i] = true;
        }
        Polynomial x = new Polynomial(test2, 5);
        System.out.println(x);     
        */
        
       
       
       boolean[] test = {false, false, true, false, false, false, false, true};
       Polynomial x = new Polynomial(test, 3);
       System.out.println(x);
       /*
       HashSet<Integer> myhashset = new HashSet<Integer>();
       myhashset.add(new Integer(1));
       Monomial minusx1 = new Monomial(myhashset, -1);
       Monomial one = new Monomial(new HashSet<Integer>(), 1);
       assert(one.match(one));
       ArrayList<Monomial> myList = new ArrayList<Monomial>();
       myList.add(one);
       myList.add(minusx1);
       Polynomial p = new Polynomial(myList);
       System.out.println(p.add(p));
       */
    }
}
