/**
 * @author Dor Huri
 * class represent binary expression of type nand
 */
public class Nand extends BinaryExpression {
    /**
     * this method is the constructor method for class nand.
     * @param ex1 the first expression
     * @param ex2 the second expression
     */
    public Nand(Expression ex1, Expression ex2) {
        //calling the father constructor method
        super(ex1, ex2);
    }
    /**
     * Returns the expression which both expressions relate to.
     * @param e1 the first expression
     * @param e2 the second expression
     @return the nand expression
     */
    @Override
    Expression getExpression(Expression e1, Expression e2) {
        //returning the expression
        return new Nand(e1, e2);
    }
    /**
     * this method evaluate the nand expression and returning the result. If the expression
     * contains a variable an exception is thrown.
     @return the evaluated expression or throw an exception
     @throws Exception for an expression we cant evaluate
     */
    public Boolean evaluate() throws Exception {
        try {
            // taking the value from the expressions
            Boolean ex1 = super.getEx1().evaluate();
            Boolean ex2 = super.getEx2().evaluate();
            //returning the nand of the expression
            return !ex1 || !ex2;
            //throwing an exception in case of catch
        } catch (Exception e) {
            throw new Exception();
        }
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
        Expression e2 =  super.getEx2().assign(var, expression);
        //returning the nand expression value
        return new Nand(e1, e2);
    }
    /**
     * this method return the nand expression as a string.
     * @return the expression string form
     */
    public String toString() {
        //printing the binary expression
        return "(" + super.getEx1().toString() + " A " + super.getEx2().toString() + ")";
    }
    /**
     * this method return the nand expression according to the nand logic gate for nand operator.
     * @return the expression with only nand operator
     */
    @Override
    //returning the expression with only nand operator according to nand logic nand gate
    public Expression nandify() {
        return new Nand(super.getEx1().nandify(), super.getEx2().nandify());
    }
    /**
     * this method return the nand expression according to the nor logic gate for nand operator.
     * @return the expression with only nor operator
     */
    @Override
    public Expression norify() {
        //returning the expression with only nor operator according to nor logic nand gate
        return new Nor(new Nor(new Nor(super.getEx1().norify(), super.getEx1().norify()),
                new Nor(super.getEx2().norify(), super.getEx2().norify())), new Nor(new Nor(super.getEx1().norify(),
                super.getEx1().norify()), new Nor(super.getEx2().norify(), super.getEx2().norify())));
    }
    /**
     * this method return a simplified version of the current expression if possible.
     * @return the expression in a simplified form
     */
    public Expression simplify() {
        //creating a new binary expression of type nand from the simplified expression
        BinaryExpression exp = new Nand(super.getEx1().simplify(), super.getEx2().simplify());
        try {
            //returning the simplified expression if possible
            return new Val(exp.evaluate());
            //in case of catch
        } catch (Exception e) {
            //checking which state has the expression and assigning to res
            int res = exp.expressionCheck(exp);
            //checking if expression have false value
            if (res == EXPRESSION_WITH_FALSE) {
                //if one of the expression is false
                if (exp.getEx1().toString().equals("F") || exp.getEx2().toString().equals("F")) {
                    //returning true val expression
                    return new Val(true);
                }
                //checking if expression are equal
            } else if (res == EXPRESSION_EQUAL) {
                return new Not(exp.getEx1()).simplify();
                //checking if expression have true value
            } else if (res == EXPRESSION_WITH_TRUE) {
                //if the first expression is true
                if (exp.getEx1().toString().equals("T")) {
                    //returning the not expression of the second expression
                    return new Not(exp.getEx2()).simplify();
                } else {
                    //returning the not expression of the first expression
                    return new Not(exp.getEx1()).simplify();
                }
                //checking if expression have variable
            } else if (res == EXPRESSION_WITH_VAR) {
                 return exp;
             }
            //returning expression
            return exp;
        }
    }
}
