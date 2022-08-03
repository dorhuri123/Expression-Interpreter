/**
 * @author Dor Huri
 * class represent binary expression of type or
 */
public class Or extends BinaryExpression {
    /**
     * this method is the constructor method for class or.
     * @param ex1 the first expression
     * @param ex2 the second expression
     */
    public Or(Expression ex1, Expression ex2) {
        //calling the father constructor method
        super(ex1, ex2);
    }
    /**
     * Returns the expression which both expressions relate to.
     * @param e1 the first expression
     * @param e2 the second expression
     @return the or expression
     */
    @Override
    Expression getExpression(Expression e1, Expression e2) {
        //returning the expression
        return new Or(e1, e2);
    }
    /**
     * this method evaluate the or expression and returning the result. If the expression
     * contains a variable an exception is thrown.
     @return the evaluated expression or throw an exception
     @throws Exception for an expression we cant evaluate
     */
    public Boolean evaluate() throws Exception {
        try {
            // taking the value from the expressions
            Boolean ex1 = super.getEx1().evaluate();
            Boolean ex2 = super.getEx2().evaluate();
            //returning the or of the expression
            return ex1 || ex2;
            //throwing an exception in case of catch
        } catch (Exception e) {
            throw new Exception();
        }
    }
    /**
     * this method return the or expression as a string.
     * @return the expression string form
     */
    public String toString() {
        //printing the binary expression
        return "(" + super.getEx1() + " | " + super.getEx2() + ")";
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
        Expression e1 = super.getEx1().assign(var, expression);
        Expression e2 = super.getEx2().assign(var, expression);
        //returning the or expression value
        return new Or(e1, e2);
    }
    /**
     * this method return the or expression according to the nand logic gate for or operator.
     * @return the expression with only nand operator
     */
    @Override
    public Expression nandify() {
        //returning the expression with only nand operator according to nand logic or gate
        return new Nand(new Nand(super.getEx1().nandify(), super.getEx1().nandify()),
                new Nand(super.getEx2().nandify(), super.getEx2().nandify()));
    }
    /**
     * this method return the or expression according to the nor logic gate for or operator.
     * @return the expression with only nor operator
     */
    @Override
    public Expression norify() {
        //returning the expression with only nor operator according to nor logic or gate
        return new Nor(new Nor(super.getEx1().norify(), super.getEx2().norify()), new Nor(super.getEx1().norify(),
                super.getEx2().norify()));
    }
    /**
     * this method return a simplified version of the current expression if possible.
     * @return the expression in a simplified form
     */
    @Override
    public Expression simplify() {
        //creating a new binary expression of type or from the simplified expression
        BinaryExpression exp = new Or(super.getEx1().simplify(), super.getEx2().simplify());
        try {
            //returning the simplified expression if possible
            return new Val(exp.evaluate());
            //in case of catch
        } catch (Exception e) {
            //checking which state has the expression and assigning to res
            int res = exp.expressionCheck(exp);
            //checking if expression have true value
            if (res == EXPRESSION_WITH_TRUE) {
                //returning true val expression
                return new Val(true);
            } else if (res == EXPRESSION_EQUAL) {
                    return exp.getEx1().simplify();
                //checking if expression have false value
            } else if (res == EXPRESSION_WITH_FALSE) {
                //if the first expression is false
                if (exp.getEx1().toString().equals("F")) {
                    //return the second expression
                    return exp.getEx2().simplify();
                } else {
                    //return the first expression
                    return exp.getEx1().simplify();
                }
                //checking if expression have variable
            } else if (res == EXPRESSION_WITH_VAR) {
                return exp;
            }
            //returning the expression
            return exp;
        }
    }
}
