import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @author Dor Huri
 * class represent expression of type var
 */
public class Var implements Expression {
    private final String string; //variable of the expression
    /**
     * this method is the constructor method for class Var.
     * @param string the var Expression
     */
    public Var(String string) {
        this.string = string;
    }
    /**
     * this method return the string of the var expression.
     * @return the var string Expression
     */
    public String getString() {
        return this.string;
    }
    /**
     * this method Evaluate the expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     * @param assignment map for variable and there value
     @return the evaluated expression or throw an exception
     */
    @Override
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            //returning the expression variable
            return assignment.get(this.string);
            //throwing an exception in case of catch
        } catch (Exception e) {
            throw new Exception();
        }
    }
    /**
     * this method evaluate the var expression and returning the result. If the expression
     * contains a variable an exception is thrown.
     @return the evaluated expression or throw an exception
     @throws Exception for an expression we cant evaluate
     */
    @Override
    public Boolean evaluate() throws Exception {
        //throwing an exception
        throw new Exception("Error");
    }
    /**
     * Returns a list of the variables in the expression.
     @return the expression variables in a list
     */
    @Override
    public List<String> getVariables() {
        //creating new list from type string
        ArrayList<String> list = new ArrayList<>();
        //adding variable to list
        list.add(this.string);
        //returning the variable list
        return list;
    }
    /**
     * this method return a new expression in which all occurrences of the variable
     * var are replaced with the provided expression.
     * @param var the variable we replace with expression
     * @param expression the expression we assign in the variable
     * @return the assign expression
     */
    @Override
    public Expression assign(String var, Expression expression) {
        //checking if the variable is in val
        if (var.equals(this.toString())) {
            //returning the expression value
            return expression;
        }
        //returning this var
        return new Var(this.string);
    }
    /**
     * this method return the var expression as a string.
     * @return the expression string form
     */
    @Override
    public String toString() {
        //returning this string variable
       return this.string;
    }
    /**
     * this method return the var expression according to the nand logic gate for var.
     * @return the expression with only nand operator
     */
    public Expression nandify() {
        // Returns the expression tree resulting from converting all the operations to the logical Nand operation.
        return this;
    }
    /**
     * this method return the var expression according to the nor logic gate for var.
     * @return the expression with only nor operator
     */
    public Expression norify() {
        // Returns the expression tree resulting from converting all the operations to the logical nor operation.
        return this;
    }
    /**
     * this method return a simplified version of the current expression if possible.
     * @return the expression in a simplified form
     */
    @Override
    public Expression simplify() {
        //returning the expression
        return this;
    }
}
