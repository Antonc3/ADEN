
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
public class ExpressionEvaluator extends Application {
	
	/** The data stack. */
	private GenericStack<Double> dataStack;
	
	/** The operator stack. */
	private GenericStack<String>  operStack;
	
	/** The output results that will be displayed to the user */
	private Text outputResults;
	private BorderPane main = new BorderPane();
	private TextField inp;
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
		Scene s = new Scene(main,500,100);
		HBox hb = new HBox();
		hb.setAlignment(Pos.TOP_CENTER);
		Button eval = new Button("Evaluate Expression");
		HBox hb1 = new HBox();
		outputResults = new Text();
		outputResults.setFont(new Font("Verdana",20));
		hb1.getChildren().add(outputResults);
		hb1.setAlignment(Pos.TOP_CENTER);
		eval.setOnAction(e ->{
			String op = evaluateExpression(inp.getText());
			outputResults.setText(op);
		});
		eval.setPrefHeight(30);
		inp = new TextField();
		inp.setPrefWidth(350);
		inp.setPrefHeight(30);
		hb.getChildren().addAll(inp,eval);
		main.setCenter(hb);
		main.setBottom(hb1);
		primaryStage.setScene(s);
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
	public String evaluateExpression(String str) {
		if(str.length() == 0) {
			return "Data Error: " ;
		}
		str = str.replaceAll("(\\+|-|/|\\(|\\)|\\*)", " $1 ");
		
		String[] tokens = str.split("\\s+");
//		for(int i = 0; i < tokens.length; i++) {
//			System.out.print(tokens[i] + " ||| ");
//		}
		dataStack = new GenericStack();
		operStack = new GenericStack();
		String processed = "";
		boolean first = true;
		boolean lastLeftParen = false;
		int amntOp = 0;
		int curParens = 0;
		String lastToken = "";
		boolean negNum = false;
		boolean hasToken =false;
		boolean lastRightParen = false;
		for(int i = 0; i < tokens.length; i++) {
			if(tokens[i].length() == 0) {
				continue;
			}
			processed += tokens[i] + " ";
			try {
				
				double cur = Double.parseDouble(tokens[i]);
				hasToken = true;
				if(amntOp == 0 && !first && !lastRightParen) {
					return "Data Error: " + processed;
				}
				if(!tokens[i].matches("[0-9|.]+")) {
					return "Data Error: " + processed;
				}
				amntOp = 0;
				lastLeftParen = false;
				lastRightParen = false;
			}
			catch (Exception e) {
				if(tokens[i].length() >1) {
					return "Data Error: " + processed;
				}
				boolean matched = tokens[i].matches("[+-/*\\(\\)]");
				if(!matched) {
					return "Data Error: " + processed;
				}

				if(first &&tokens[i].charAt(0) != '-' && tokens[i].charAt(0) != '(' ) {
					if(')' == tokens[i].charAt(0)) {
						return "Paren Error: " + processed;
					}
					return "Op Error: " + processed;
				}
				if(amntOp >= 2) {
					return "Op Error: " + processed;
				}
				if(tokens[i].charAt(0) == '(') {
					curParens++;
					amntOp = 0;
					lastLeftParen = true;
				}
				else if(tokens[i].charAt(0) == ')') {
					System.out.println(curParens);
					lastRightParen = true;
					if(--curParens < 0) {
						return "Paren Error: " + processed;
					}
					if(amntOp == 1) {
						return "Op Error: " + processed;
					}
					
					if(lastLeftParen) {
						return "Paren Error: " + processed;
					}
					amntOp=-1;
				}
				else if(tokens[i].charAt(0) != '-' && amntOp ==1) {
					return "Op Error: " + processed;
				}
				else {
					lastLeftParen=false;
				}
				hasToken = true;
				amntOp++;
			}
			first = false;
			lastToken = tokens[i];
		}
		if(curParens != 0) {
			return "Paren Error: " + processed;
		}
		if(!hasToken) {
			return "Data Error: ";
		}
		if(amntOp > 0 && lastToken.charAt(0) != ')') {
			return "Op Error: " + processed;
		}
		
		amntOp = 1;
		lastRightParen = false;
		for(int i = 0; i < tokens.length; i++){
//			System.out.println(dataStack.getSize());
			String cur = tokens[i];
			if(cur.length() == 0) {
				continue;
			}
//			operStack.print();
//			dataStack.print();
			try {
				double curDouble= Double.parseDouble(cur);
//				System.out.println("lastRightParen: " +lastRightParen);
				if(lastRightParen) {
					operStack.push("*");
					lastRightParen = false;
				}
				dataStack.push(curDouble);
				amntOp = 0;
			}
			catch(Exception e){
				if(dataStack.getSize() <2 && cur.charAt(0) != ')') {
					if(amntOp >= 1 && cur.charAt(0) == '-') {
						operStack.push("n");
						continue;
					}
					if(cur.charAt(0)=='(' && amntOp == 0 && dataStack.getSize() >= 1) {
						operStack.push("*");
					}
					operStack.push(cur);
					lastRightParen = false;
					amntOp++;
					continue;
				}
				char c = cur.charAt(0);
				if(amntOp >= 1 && c == '-') {
					operStack.push("n");
					lastRightParen = false;
					continue;
				}
				else if(c=='(' ) {
					if(amntOp == 0&& dataStack.getSize() >= 1) {
//						System.out.println("error> \\\\");
						operStack.push("*");
					}
					operStack.push(cur);
					lastRightParen = false;
				}
				else if(c == ')') {
					String top = operStack.pop();
//					System.out.println(top);
					lastRightParen = true;

					while(top.charAt(0) != '(') {
//						System.out.println(top);
						double i1 = dataStack.pop();
						if(top.charAt(0) == 'n') {
							i1 = -i1;
							dataStack.push(i1);
							top = operStack.pop();
							continue;
						}
						double i2 = dataStack.pop();
						if(operStack.getSize() >0 ) {
							if(operStack.peek().charAt(0) == 'n') {
								operStack.pop();
								i2 = -i2;
							}
						}
						if(i1 == 0 && top.charAt(0) == '/') {
							return "Div0 Error: " + processed;
						}
						dataStack.push(preformOperation(i2,i1,top.charAt(0)));
						top = operStack.pop();
//						System.out.println(top);
					}
					amntOp = 0;
					continue;
				}
				else if(Prec(c) > Prec(operStack.peek().charAt(0))) {
					lastRightParen = false;
					operStack.push(cur);
				}
				else if(Prec(c) <= Prec(operStack.peek().charAt(0))) {
					lastRightParen = false;
					String top = operStack.peek();
					
//					System.out.println("Looping through prec(c)");
					while(Prec(c) <= Prec(top.charAt(0)) && dataStack.getSize() >=2) {
						top = operStack.pop();

//						System.out.println(top);
//						dataStack.print();
//						operStack.print();
						double i1 = dataStack.pop();
						if(top.charAt(0) == 'n') {
							i1 = -i1;
							dataStack.push(i1);
							top = operStack.pop();
							continue;
						}
						double i2 = dataStack.pop();
						if(operStack.getSize() >0 ) {
							if(operStack.peek().charAt(0) == 'n') {
								operStack.pop();
								i2 = -i2;
							}
						}
						if(i1 == 0 && top.charAt(0) == '/') {
							return "Div0 Error: " + processed;
						}
						dataStack.push(preformOperation(i2,i1,top.charAt(0)));
						if(operStack.getSize() > 0) {
							top = operStack.peek();
						}
						else {
							break;
						}
					}
					if(c != ')') {
						lastRightParen = false;
					}
					operStack.push(cur);
//					System.out.println("Done looping");
//					dataStack.print();
//					operStack.print();
				}
				amntOp++;
			}
		}
		while(!operStack.isEmpty()) {
//			System.out.println("stack sizes: " + dataStack.getSize()+ ", oper: " + operStack.getSize());
//			System.out.println("stack info: " + dataStack.peek()+ ", oper: " + operStack.peek());
			
			String top = operStack.pop();
			double i1 = dataStack.pop();
			if(top.charAt(0) == 'n') {
				i1 = -i1;
				dataStack.push(i1);
				continue;
			}
			double i2 = dataStack.pop();
			if(operStack.getSize() >0 ) {
				if(operStack.peek().charAt(0) == 'n') {
					operStack.pop();
					i2 = -i2;
				}
			}
			if(i1 == 0 && top.charAt(0) == '/') {
				return "Div0 Error: " + processed;
			}
			dataStack.push(preformOperation(i2,i1,top.charAt(0)));
		}
		return processed + " = " +dataStack.pop();
	}
	private double preformOperation(double a, double b, char op) {
		if(op == '*') {
			return a*b;
		}
		if(op == '/') {
			return a/b;
		}
		if(op=='+') {
			return a+b;
		}
		if(op == '-') {
			return a-b;
		}
		return 0;
	}
	private int Prec(char c) {
		if(c == '(' || c==')') {
			return 0;
		}
		if(c == '/' || c=='*') {
			return 2;
		}
		if(c=='+' || c=='-') {
			return 1;
		}
		return 0;
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
