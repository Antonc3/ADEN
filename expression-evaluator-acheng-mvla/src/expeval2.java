
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

// TODO: Auto-generated Javadoc
/**
 * The Class ExpressionEvaluator.
 */
public class expeval2 extends Application {
	 private BorderPane main = new BorderPane();	
	 private TextField expr;

	/** The data stack. */
	private GenericStack<Double> dataStack;
	
	/** The operator stack. */
	private GenericStack<Character>  operStack;
	
	/** The output results that will be displayed to the user */
	private Text outputResults;
	
	/**
	 * Start.
	 *
	 * @param primaryStage the primary stage
	 * @throws Exception the exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Expression Evaluator");	
    	Scene scene = new Scene(main,400,400);
    	Button enter = new Button("Enter");
    	expr = new TextField("");
    	outputResults = new Text("Answer:");
    	enter.setOnAction(e->{
    		String temp  = expr.getText();
    		String ans = evaluateExpression(temp);
    		outputResults.setText("Answer: " + ans);
    	});
    	main.setRight(enter);
    	main.setCenter(expr);
    	main.setBottom(outputResults);
    	
    	primaryStage.setTitle("Expression Evaluator");
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	/**
	 * Evaluates the expression by first massaging the string, and then splitting it
	 * into "tokens" that are either operations or data operands. 
	 *
	 * @param str this is the string that the user typed in the text field
	 * @return the string that is the result of the evaluation. It should include the original
	 *         expression and the value that it equals, or indicate if some error occurred.
	 */
	protected String evaluateExpression(String str) {
		dataStack = new GenericStack();
		operStack = new GenericStack();
        String[] tok = str.split("\\s+");
        /*
        for(int i=1;i<tok.length;i++) {
        	if (tok[i].matches("-?\\d+(\\.\\d+)?") && tok[i-1].matches("-?\\d+(\\.\\d+)?")) {
    			return "Data Error: ";
    		}
        }
        */
		str = str.replaceAll("  ", "");
		
        str = str.replaceAll("(\\+|-|/|\\(|\\)|\\*)", " $1 ");
        //Supposed to be  (\\+|-|/|\\(|\\)|\\*) for decimals
        str = str.trim();
        
        tok = str.split("\\s+");
        ArrayList<String> arr = new ArrayList();
        for (int i=0;i<tok.length;i++) {
        	arr.add(tok[i]);
        	//System.out.print(arr.get(i) + " ");
        }
        String curr = "";
        //paren error
        int left=0;
        for(int i=0;i<arr.size();i++) {
        	if(arr.get(i).charAt(0)=='(') {
        		left++;
        	} 
        	if(arr.get(i).charAt(0)==')') {
        		if (left==0) {
        			return "Paren Error: " + curr;
        		} else {
            		left--;        			
        		}
        	}
        	curr+=arr.get(i);
        }
        if (left!=0) {
			return "Paren Error: " + curr;
        }
        //not a double
        curr="";
        for(int i=0;i<arr.size();i++) {
        	char currOp = arr.get(i).charAt(0);
        	if (currOp!='*' && currOp!='/' && currOp!='+'
					&& currOp!='-' && currOp!='(' && currOp!=')' ) {
        		boolean numeric = arr.get(i).matches("-?\\d+(\\.\\d+)?");
        		if(!numeric) {
        			return "Data Error: " + curr + currOp;
        		}
        	}
        	
        	curr+=arr.get(i);
        }
        //mult data toks
        curr = "";
        for (int i=0;i<arr.size()-1;i++) {
        	String currOp = arr.get(i);
        	if (!currOp.equals("*") && !currOp.equals("/") && !currOp.equals("+")
					&& !currOp.equals("-") && !currOp.equals("(") && !currOp.equals(")") ) {
        		String nextOp = arr.get(i+1);
        		if (!nextOp.equals("*") && !nextOp.equals("/") && !nextOp.equals("+")
    					&& !nextOp.equals("-") && !nextOp.equals("(") && !nextOp.equals(")")) {
        			return "Data Error: " + curr + currOp+nextOp;
        		}        	
        	}
        	curr+=currOp;
        }
        //negation
        for(int i=1;i<arr.size();i++) {
        	char currOp = arr.get(i).charAt(0);
        	if (currOp!='*' && currOp!='/' && currOp!='+'
					&& currOp!='-') {
        		if (arr.get(i-1).length() == 1 && arr.get(i-1).charAt(0)=='-') {
        			if(i-1==0 || (arr.get(i-2).length()==1 && (arr.get(i-2).charAt(0)=='+'|| arr.get(i-2).charAt(0)=='*' 
        					|| arr.get(i-2).charAt(0)=='-' || arr.get(i-2).charAt(0)=='/'|| arr.get(i-2).charAt(0)=='('))) {
        				//put negative in the end
        				if (currOp == '(') {
            				int l = 1;
            				int ind = i;
            				while(l!=0) {
            					ind++;
            					if(arr.get(ind).charAt(0)=='(') {
            						l++;
            					} 
            					if(arr.get(ind).charAt(0)==')') {
            						l--;
            					}
            				}
            				arr.remove(i-1);
            				arr.add(ind,"*");
            				arr.add(ind+1,"-1");
            				i--;
        				} else {
            				arr.set(i-1,arr.get(i));
            				arr.set(i,"*");
            				arr.add(i+1,"-1");
            				i++;
        				}
        				
        			}
        		}
        	}
        }
        for(int i=0;i<arr.size();i++) {
        	System.out.println(arr.get(i));
        }
        //mult operations(1 max,neg handled),incomplete ops
        curr="";
        if(arr.get(0).charAt(0)=='+' || arr.get(0).charAt(0)=='/' || arr.get(0).charAt(0)=='*') {

        	return "Op Error: " + arr.get(0).charAt(0);
        }
        for(int i=0;i<arr.size()-1;i++) {
        	if (arr.get(i).length()==1 && (arr.get(i).charAt(0)=='+'|| arr.get(i).charAt(0)=='*' 
					|| arr.get(i).charAt(0)=='-' || arr.get(i).charAt(0)=='/'|| arr.get(i).charAt(0)=='(')) {
        		if (arr.get(i+1).length()==1 && (arr.get(i+1).charAt(0)=='+'|| arr.get(i+1).charAt(0)=='*' 
    					|| arr.get(i+1).charAt(0)=='-' || arr.get(i+1).charAt(0)=='/'|| arr.get(i+1).charAt(0)==')')) {
        			if (arr.get(i).charAt(0)=='('&&arr.get(i+1).charAt(0)=='-') {
        				
        			} else {
        				System.out.println("here");
            			return "Op Error: " + curr + arr.get(i);
        			}
            	}
        	}
        	curr+=arr.get(i);
        }
        if (arr.get(arr.size()-1).length() == 1 && (arr.get(arr.size()-1).charAt(0)=='-' || arr.get(arr.size()-1).charAt(0)=='+' 
        		|| arr.get(arr.size()-1).charAt(0)=='/' || arr.get(arr.size()-1).charAt(0)=='*')) {
        	return "Op Error: " + curr;
        }
        
        //implicit mult, both sides
        for(int i=0;i<arr.size()-1;i++) {
        	char last = arr.get(i).charAt(arr.get(i).length()-1);
        	char next = arr.get(i+1).charAt(0);
        	if (last != '+' && last!='-'&&last!='/'&&last!='*' && next != '+' && next!='-'&&next!='/'&&next!='*') {
        		if ((next=='('||last==')') && next != last) {
        			arr.add(i+1,"*");
        			i++;
        		}
        		
        	}
        }
        for(int i=0;i<arr.size();i++) {
        	System.out.print(arr.get(i)+" ");
        }
        //1 bug 6/-5, left to right, 6/-(5+2)
        //check operator between every two numbers
        curr="";
        for(int i=1;i<arr.size();i++) {
        	if(arr.get(i).matches("-?\\d+(\\.\\d+)?") && arr.get(i-1).matches("-?\\d+(\\.\\d+)?")) {
        		return "Data Error: " + curr + arr.get(i);
        	}
        	curr+=arr.get(i);
        }
		for (int i=0;i<arr.size();i++) {
			/*
			if (arr.get(i).length()==0) {
				continue;
			}
			*/
			if (arr.get(i).length()==1) {
				char currOp = arr.get(i).charAt(0);
				if (currOp=='*' || currOp=='/' || currOp=='+'
						|| currOp=='-' || currOp=='(' || currOp==')') {
					if (currOp=='(') {
						operStack.push((currOp));
					} else if (currOp == ')') {
						while (operStack.peek() != '(') {
							double d2 = dataStack.pop();
							double d1 = dataStack.pop();
							char op = operStack.pop();
							if (op == '/'&&d2==0) {
								return "Div0 Error: " + curr;
							}
							dataStack.push(eval(d1,d2,op));
						}
						operStack.pop();
					} else {
						if (operStack.getSize()==0) {
							operStack.push(currOp);
						} else {
							if (Prec(currOp) > Prec(operStack.peek()) || Prec(operStack.peek())==2) {
								operStack.push(currOp);
							} else {
								while (operStack.getSize() > 0 && (Prec(operStack.peek()) >= Prec(currOp)) && Prec(operStack.peek())!=2) {
									double d2 = dataStack.pop();
									double d1 = dataStack.pop();
									char op = operStack.pop();
									if (op == '/'&&d2==0) {
										return "Div0 Error: " + curr;
									}
									dataStack.push(eval(d1,d2,op));
								}
								operStack.push(currOp);
							}
						}
					}
				} else {
					dataStack.push(Double.parseDouble(arr.get(i)));
				}
			} else {
				dataStack.push((Double.parseDouble(arr.get(i))));
			}
			curr+=arr.get(i);
		}
		while (operStack.getSize() >0 ) {
			char op = operStack.pop();
			//System.out.println(op);
			double d2 = dataStack.pop();
			//System.out.println(d2);
			double d1 = dataStack.pop();
			//System.out.println(d1);
			if (op == '/'&&d2==0) {
				return "Div0 Error: " + curr;
			}
			dataStack.push(eval(d1,d2,op));
		}
		double ans = dataStack.pop();
		return (str + "=" + ans);
	}
	private double eval(double d1,double d2,char op) {
		double ans =0;
		if (op=='+') {
			ans=d1+d2;
		} else if (op=='-') {
			ans=d1-d2;
		} else if (op=='/') {
			ans=d1/d2;
		} else if (op=='*') {
			ans=d1*d2;
		}
		return ans;
	}
	private int Prec(char a) {
		int ans = 0;
		if (a == '*'||a=='/') {
			ans = 1;
		} 
		if (a == '(' || a == ')') {
			ans = 2;
		}
		return ans;
	}
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Application.launch(args);

	}

}
