import java.util.HashSet;
import java.util.Set;

//Class that stores monomials to be represented as a polynomial
public class Monomial
{
    public Set<Integer> vars;
    public int coeff;

    //Constructor for monomial from set of indexed variables
    //Initialize with a coefficient for the monomial
    public Monomial(Set<Integer> myVars, int myCoeff)
    {
        vars = myVars;
        coeff = myCoeff;
    }
    
    //Multiply two monomials to form another larger monomial
    public Monomial multiply(Monomial m2)
    {
        int newcoeff = this.coeff * m2.coeff;
        Set<Integer> newset = new HashSet<Integer>();
        for (Integer i : this.vars)
            newset.add(i);
        for (Integer i : m2.vars)
            newset.add(i);
        return new Monomial(newset, newcoeff);
    }
    
    //Test whether this monomial matches another (sets of variables are equal)
    //Coefficients are not necessarily equal
    public boolean match(Monomial m2)
    {
        if (this.vars.containsAll(m2.vars) && m2.vars.containsAll(this.vars))
            return true;
        else
            return false;
    }   
}
