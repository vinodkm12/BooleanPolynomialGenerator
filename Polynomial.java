import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

/*
Class for multilinear, 0-1 valued polynomials that represent boolean functions
Used as tool in proof of the Log-Rank conjecture for AND matrices
Inspiration for project from Dr. Kaave Hosseini, Carnegie Mellon University
*/
public class Polynomial
{
    //Stores Monomial terms in the polynomial
    public List<Monomial> myVars;
    /**
     * Constructor for objects of class Polynomial
     * int n: number of variables
     * vals: required to be of size 2^n
     * vals contains values of the polynomial on all 2^n inputs
     * order of the inputs is FFF, FFT, FTF, FTT, TFF, ... (most natural order)
     */
    public Polynomial(boolean[] vals, int n)
    {
        Polynomial tempbuild;
        Polynomial temp;
        Polynomial build = new Polynomial(new ArrayList<Monomial>());
        int i_copy;
        for (int i = 0; i < vals.length; i++)
        {
            if (vals[i])
            {
                i_copy = i;

                Monomial mymono = new Monomial(new HashSet<Integer>(), 1);
                ArrayList<Monomial> mymono2 = new ArrayList<Monomial>();
                mymono2.add(mymono);      
                tempbuild = new Polynomial(mymono2);
                
                
                for (int j = 0; j < n; j++)
                {
                    if (i_copy % 2 == 0)
                    {
                        List<Monomial> monolist = new ArrayList<Monomial>();
                        HashSet<Integer> myhashset = new HashSet<Integer>();
                        myhashset.add(new Integer(j+1));
                        monolist.add(new Monomial(myhashset, 1));
                        temp = new Polynomial(monolist);
                    }
                    else
                    {
                        List<Monomial> monolist = new ArrayList<Monomial>();
                        monolist.add(new Monomial(new HashSet<Integer>(), 1));
                        HashSet<Integer> myhashset = new HashSet<Integer>();
                        myhashset.add(new Integer(j+1));
                        monolist.add(new Monomial(myhashset, -1));
                        temp = new Polynomial(monolist);
                    }
                    tempbuild = tempbuild.multiply(temp);
                    i_copy = i_copy/2;
                }
                
                build = build.add(tempbuild);
            }            
        }
        this.myVars = build.myVars;
    }
    
    //Constructor for Polynomial from List of Monomials
    public Polynomial(List<Monomial> x)
    {   
        myVars = x;
    }
    
    //Add two polynomials together
    public Polynomial add(Polynomial p2)
    {
        boolean inflag = true;
        List<Monomial> build = new ArrayList<Monomial>();
        for (Monomial m : this.myVars)
        {
            for (Monomial m_prime : build)
            {
                if (m.match(m_prime))
                {
                    m_prime.coeff += m.coeff;
                    if (m_prime.coeff == 0)
                        build.remove(m_prime);                    
                    inflag = false;
                    break;
                }
            }
            if (inflag)
                build.add(m);
                
            inflag = true;
        }
        for (Monomial m : p2.myVars)
        {
            for (Monomial m_prime : build)
            {
                if (m.match(m_prime))
                {
                    m_prime.coeff += m.coeff;
                    if (m_prime.coeff == 0)
                        build.remove(m_prime);
                    inflag = false;
                    break;
                }

            }
            if (inflag)
                build.add(m);
            inflag = true;
        }
        return new Polynomial(build);
    }
    
    //Multiply two polynomials together
    public Polynomial multiply(Polynomial p2)
    {
        Monomial temp;
        List<Monomial> newlist = new ArrayList<Monomial>();
        boolean inflag = true;
        for (Monomial m : this.myVars)
        {
            for (Monomial m2 : p2.myVars)
            {
                temp = m.multiply(m2);
                
                for (Monomial m_prime : newlist)
                {
                    if (temp.match(m_prime))
                    {
                        m_prime.coeff = temp.coeff + m_prime.coeff;
                        if (m_prime.coeff == 0)
                            newlist.remove(m_prime);
                        inflag = false;
                    }
                }
                if (inflag)
                    newlist.add(temp);
                inflag = true;
            }
        }
        return new Polynomial(newlist);
    }
    
    //Convert polynomial to simple string representation
    public String toString()
    {
        String build = "";
        assert(myVars != null);
        if (myVars.size() == 0)
            return "0";
        for (Monomial m : myVars)
        {
            if (m.coeff < 0)
                build += " - ";
            else
                build += " + ";
            Integer coeff = new Integer(m.coeff);
            if (coeff < 0)
                coeff = coeff * (-1);
            build += coeff.toString();
            for (Integer i : m.vars)
                build += ("x_" + i.toString());
        }
        return build;
    }

}
