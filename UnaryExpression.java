import java.util.List;
import java.util.Map;
/**
 * @author Dor Huri
 * class represent unary logic expressions that inherite from BaseExpression
 */
public abstract class  UnaryExpression extends BaseExpression {
    public static final int EXPRESSION_WITH_TRUE = 1; //for indicating expression with true val
    public static final int EXPRESSION_WITH_FALSE = 0; //for indicating expression with false val
    public static final int EXPRESSION_WITH_VAR = 3; //for indicating expression with variable
    private Expression expression;
    /**
     * this method is the constructor method for class UnaryExpression.
     * @param expression the Unary Expression
     */
    public UnaryExpression(Expression expression) {
        this.expression = expression;
    }
    /**
     * this method return the  expression of UnaryExpression.
     * @return the expression of UnaryExpression
     */
    public Expression getExpression() {
        return this.expression;
    }
    /**
     * this method return the expression as a string.
     * @return the expression string form
     */
    @Override
    public abstract String toString();
    /**
     * this method return a new expression in which all occurrences of the variable
     * var are replaced with the provided expression.
     * @param var the variable we replace with expression
     * @param expression1 the expression we assign in the variable
     * @return the assign expression
     */
    @Override
    public abstract Expression assign(String var, Expression expression1);
    /**
     * this method Evaluate the Unary expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     * @param assignment map for variable and there value
     @return the evaluated expression or throw an exception
     @throws Exception for an expression we cant evaluate
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            Expression e1 = this.expression;
            //getting the expression variable
            List<String> var = this.getVariables();
            //iterating trow the variable list
            for (String s:var) {
                //recursively assigning the expression
                e1 = e1.assign(s, new Val(assignment.get(s)));
            }
            //returning the evaluate expression if possible
            return getExpression(e1).evaluate();
            //throwing an exception in case of catch
        } catch (Exception e) {
            throw new Exception("Error");
        }
    }
    /**
     * Returns the expression which relate to the unary expressions.
     * @param e1 the expression
     @return the expression base of the expression type
     */
    abstract Expression getExpression(Expression e1);
    /**
     * Returns a list of the variables in the expression.
     @return the expression variables in a list
     */
    public List<String> getVariables() {
        //returning the variable list of the expression
        List<String> list = this.expression.getVariables();
        return list;
    }
    /**
     * this method evaluate the expression and returning the result. If the expression
     * contains a variable an exception is thrown.
     @return the evaluated expression or throw an exception
     @throws Exception for an expression we cant evaluate
     */
    public Boolean evaluate() throws Exception {
        try {
            // taking the value from the expression
            Boolean bool = this.expression.evaluate();
            //returning the not of the expression
            return !bool;
            //throwing an exception in case of catch
        } catch (Exception e) {
            throw new Exception("Error");
        }
    }
    /**
     * this method check if the expression have one of the following:
     * true expression, false expression, expression equal if no one of this doesn't
     * exist we return that the expression has variable.
     * @param e the binary expression we check
     @return the right option of the expression
     */
    public int expressionCheck(UnaryExpression e) {
        //checking if the expression have a true value
        if (e.getExpression().toString().equals("T")) {
            //returning that expression have a true val
            return EXPRESSION_WITH_TRUE;
        }
        //checking if the expression have a false value
        if (e.getExpression().toString().equals("F")) {
            //returning that expression have a false val
            return EXPRESSION_WITH_FALSE;
        }
        //returning that the expression has variable
        return EXPRESSION_WITH_VAR;
    }

}
