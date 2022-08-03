import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * @author Dor Huri
 * class represent expression of type val
 */
public class Val implements Expression {
    private final Boolean aBoolean; //boolean value of expression
    /**
     * this method is the constructor method for class Val.
     * @param aBoolean the val Expression
     */
    public Val(Boolean aBoolean) {
        this.aBoolean = aBoolean;
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
            //returning the expression boolean value
            return this.aBoolean;
            //throwing an exception in case of catch
        } catch (Exception e) {
            throw new Exception();
        }
    }
    /**
     * this method evaluate the val expression and returning the result. If the expression
     * contains a variable an exception is thrown.
     @return the evaluated expression or throw an exception
     @throws Exception for an expression we cant evaluate
     */
    @Override
    public Boolean evaluate() throws Exception {
        try {
            //returning the value of the expression
            return this.aBoolean;
            //throwing an exception in case of catch
        } catch (Exception e) {
        throw new Exception();
    }
    }
    /**
     * Returns a list of the variables in the expression.
     @return the expression variables in a list
     */
    @Override
    public List<String> getVariables() {
        //returning an empty list
        return new ArrayList<>();
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
        //returning the value
        return this;
    }
    /**
     * this method return the val expression as a string.
     * @return the expression string form
     */
    @Override
    public String toString() {
        //if value is true return "T"
        if (this.aBoolean) {
            return "T";
        }
        //if value is false return "F"
        return "F";
    }
    /**
     * this method return the val expression according to the nand logic gate for val.
     * @return the expression with only nand operator
     */
    public Expression nandify() {
        // Returns the expression tree resulting from converting all the operations to the logical Nand operation.
        return this;
    }
    /**
     * this method return the val expression according to the nor logic gate for val.
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
