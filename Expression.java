import java.util.List;
import java.util.Map;
/**
 * @author Dor Huri
 * this interface represent expression and their method that every
 * class that implement the interface
 */
public interface Expression {
    /**
     * this method Evaluate the expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     * @param assignment map for variable and there value
     @return the evaluated expression or throw an exception
     @throws Exception for an expression we cant evaluate
     */
    Boolean evaluate(Map<String, Boolean> assignment) throws Exception;
    /**
     * this method evaluate the and expression and returning the result. If the expression
     * contains a variable an exception is thrown.
     @return the evaluated expression or throw an exception
     @throws Exception for an expression we cant evaluate
     */
    Boolean evaluate() throws Exception;
    /**
     * Returns a list of the variables in the expression.
     @return the expression variables in a list
     */
    List<String> getVariables();
     /**
     * this method return the expression as a string.
     * @return the expression string form
     */
    String toString();
    /**
     * this method return a new expression in which all occurrences of the variable
     * var are replaced with the provided expression.
     * @param var the variable we replace with expression
     * @param expression the expression we assign in the variable
     * @return the assign expression
     */
    Expression assign(String var, Expression expression);
    /**
     * this method return the expression according to the nand logic gate for every operator.
     * @return the expression with only nand operator
     */
    Expression nandify();
    /**
     * this method return the  expression according to the nor logic gate for every operator.
     * @return the expression with only nor operator
     */
    Expression norify();
    // Returned a simplified version of the current expression.
    /**
     * this method return a simplified version of the current expression if possible.
     * @return the expression in a simplified form
     */
    Expression simplify();
}
