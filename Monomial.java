import java.util.HashSet;
import java.util.Set;
public class Monomial
{
    public Set<Integer> vars;
    public int coeff;

    public Monomial(Set<Integer> myVars, int myCoeff)
    {
        vars = myVars;
        coeff = myCoeff;
    }
    
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
    
    public boolean match(Monomial m2)
    {
        if (this.vars.containsAll(m2.vars) && m2.vars.containsAll(this.vars))
            return true;
        else
            return false;
    }   
}
