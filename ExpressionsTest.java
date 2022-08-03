import java.util.Map;
import java.util.TreeMap;
/**
 * @author Dor Huri
 * class is for the self test as provided in the assigmant
 */
public class ExpressionsTest {
    /**
     * this is the main method for the ExpressionsTest class.
     * @param args command line argument
     * @throws Exception for an expression we cant evaluate
     */
    public static void main(String[] args) throws Exception {
        //create an expression
        Expression e = new Not(new Xor(new And(new Var("x"), new Var("y")), new Or(new Var("y"), new Var("z"))));
        //printing the expression
        System.out.println(e);
        //creating map and storing variable with value
        Map<String, Boolean> assignment = new TreeMap<>();
        assignment.put("x", true);
        assignment.put("y", false);
        assignment.put("z", true);
        //printing the expression after assignment
        Boolean value = e.evaluate(assignment);
        //printing as required in the assignment
        System.out.println(value);
        System.out.println(e.nandify());
        System.out.println(e.norify());
        System.out.println(e.simplify());

    }
}
