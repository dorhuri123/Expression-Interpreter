/**
 * @author Dor Huri
 * class represent binary expression of type not
 */
public class Not extends UnaryExpression {
    /**
     * this method is the constructor method for class not.
     * @param ex1 the first expression
     */
    public Not(Expression ex1) {
        //calling the father constructor method
        super(ex1);
    }
    /**
     * Returns the expression which relate to the unary expressions.
     * @param e1 the expression
     @return the not expression
     */
    @Override
     Expression getExpression(Expression e1) {
        //returning the expression
        return new Not(e1);
    }
    /**
     * this method return the not expression as a string.
     * @return the expression string form
     */
    public String toString() {
        //printing the expression
        return "~(" + super.getExpression().toString() + ")";
    }
    /**
     * this method return a new expression in which all occurrences of the variable
     * var are replaced with the provided expression.
     * @param var the variable we replace with expression
     * @param expression the expression we assign in the variable
     * @return the assign expression
     */
    public Expression assign(String var, Expression expression) {
        //recursively assigning the expression value
        Expression e1 = super.getExpression().assign(var, expression);
        //returning the not expression value
        return new Not(e1);
    }
    /**
     * this method return the not expression according to the nand logic gate for not operator.
     * @return the expression with only nand operator
     */
    @Override
    public Expression nandify() {
        //returning the expression with only nand operator according to nand logic not gate
        return new  Nand(super.getExpression().nandify(), super.getExpression().nandify());

    }
    /**
     * this method return the not expression according to the nor logic gate for not operator.
     * @return the expression with only nor operator
     */
    @Override
    public Expression norify() {
        //returning the expression with only nor operator according to nor logic not gate
        return new Nor(super.getExpression().norify(), super.getExpression().norify());
    }
    /**
     * this method return a simplified version of the current expression if possible.
     * @return the expression in a simplified form
     */
    @Override
    public Expression simplify() {
        //creating a new binary expression of type xnor from the simplified expression
        UnaryExpression exp = new Not(super.getExpression().simplify());
        try {
            //returning the simplified expression if possible
            return new Val(exp.evaluate());
            //in case of catch
        } catch (Exception e) {
            //checking which state has the expression and assigning to res
            int res = exp.expressionCheck(exp);
            //checking if expression are equal
            if (res == EXPRESSION_WITH_FALSE) {
                //returning true val expression
                return new Val(true);
                //checking if expression have variable
            } else if (res == EXPRESSION_WITH_TRUE) {
                //returning true val expression
                return new Val(false);
            }
            //returning the expression
            return exp;
        }
    }
}
