import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
/**
 * @author Dor Huri
 * class represent binary logic expressions that inherite from BaseExpression
 */
public abstract class  BinaryExpression extends BaseExpression {
    public static final int EXPRESSION_WITH_TRUE = 1; //for indicating expression with true val
    public static final int EXPRESSION_WITH_FALSE = 0; //for indicating expression with false val
    public static final int EXPRESSION_EQUAL = 2; //for indicating expression are equal
    public static final int EXPRESSION_WITH_VAR = 3; //for indicating expression with variable
    private Expression ex1; //the first expression of the binary expression
    private Expression ex2; //the second expression of the binary expression
    /**
     * this method is the constructor method for class BinaryExpression.
     * @param ex1 the first expression
     * @param ex2 the second expression
     */
    public BinaryExpression(Expression ex1, Expression ex2) {
        this.ex1 = ex1;
        this.ex2 = ex2;
    }
    /**
     * this method return the first expression of BinaryExpression.
     * @return the first expression of BinaryExpression
     */
    public Expression getEx1() {
        return this.ex1;
    }
    /**
     * this method return the second expression of BinaryExpression.
     * @return the second expression of BinaryExpression
     */
    public Expression getEx2() {
        return this.ex2;
    }
    /**
     * this method Evaluate the binary expression using the variable values provided
     * in the assignment, and return the result. If the expression
     * contains a variable which is not in the assignment, an exception
     * is thrown.
     * @param assignment map for variable and there value
     @return the evaluated expression or throw an exception
     @throws Exception for an expression we cant evaluate
     */
    public Boolean evaluate(Map<String, Boolean> assignment) throws Exception {
        try {
            Expression e1 = this.ex1;
            Expression e2 = this.ex2;
            //getting the expression variable
            List<String> var = this.getVariables();
            //iterating trow the variable list
            for (String s:var) {
                //recursively assigning the expression
                e1 = e1.assign(s, new Val(assignment.get(s)));
                e2 = e2.assign(s, new Val(assignment.get(s)));
            }
            //returning the evaluate expression if possible
            return getExpression(e1, e2).evaluate();
            //throwing an exception in case of catch
        } catch (Exception e) {
            throw new Exception();
        }
    }
    /**
     * Returns the expression which both expressions relate to.
     * @param e1 the first expression
     * @param e2 the second expression
     @return the expression base of the expression type
     */
     abstract Expression getExpression(Expression e1, Expression e2);
    /**
     * Returns a list of the variables in the expression.
     @return the expression variables in a list
     */
    public List<String> getVariables() {
        //getting the variable from both expression
        List<String> list1 = this.ex1.getVariables();
        List<String> list2 = this.ex2.getVariables();
        //creating the list that take variable for both list
        List<String> list = new ArrayList<>();
        //creating the set for cutting duplication
        LinkedHashSet hashSet = new LinkedHashSet();
        //if list1 isn't empty
        if (!list1.isEmpty()) {
            //adding variable to hashset
            hashSet.addAll(list1);
        }
        //if list1 isn't empty
        if (!list2.isEmpty()) {
            //adding variable to hashset
            hashSet.addAll(list2);
        }
        //adding hashset to the list
        list.addAll(hashSet);
        //returning the list
        return list;
    }
    /**
     * this method check if the expression have one of the following:
     * true expression, false expression, expression equal if no one of this doesn't
     * exist we return that the expression has variable.
     * @param e the binary expression we check
     @return the right option of the expression
     */
    public int expressionCheck(BinaryExpression e) {
        //checking if the expression are equal
        if (e.getEx1().toString().equals(e.getEx2().toString())) {
            //returning that expression are equal
            return EXPRESSION_EQUAL;
        }
        //checking if the expression have a true value
        if (e.getEx1().toString().equals("T") || (e.getEx2().toString().equals("T"))) {
            //returning that expression have a true val
            return EXPRESSION_WITH_TRUE;
        }
        //checking if the expression have a false value
        if (e.getEx1().toString().equals("F") || (e.getEx2().toString().equals("F"))) {
            //returning that expression have a false val
            return EXPRESSION_WITH_FALSE;
        }
        //returning that the expression has variable
        return EXPRESSION_WITH_VAR;
    }
}