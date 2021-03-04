//============================================================================
// Name        : ExpressionTree.cpp
// Author      : CHOI, Hong Joon
// Version     :
// Copyright   : Your copyright notice
// Description : Hello World in C++, Ansi-style
//============================================================================

#include <iostream>
#include <istream>
#include <string>
#include <vector>
#include "stack.h"
#include "Token.h"
using namespace std;
int operation(char op, int a, int b)
{
	switch (op)
	{
	case '*':
		return a*b;
	case '/':
		return a/b;
	case '+':
		return a+b;
	case '-':
		return a-b;
	}
}



int main() {
	//test routine
	Stack<char*> a;
	a.push(new char('a'));
	a.push(new char('b'));
	a.push(new char('c'));
	a.push(new char('d'));
	cout << a.size() << endl;
	while (a.size()>=1)
	{
		cout << *a.top() << endl;
		a.pop();
	}
	string inString = "(1213.132+31-341.231)*33.33";
	for (string::iterator it=inString.begin();it!=inString.end(); it++)
	{
		if (isStartOfNumber(*it))
		{
			cout << "numDetected"<<endl;
			cout << readOperandSequence(it ,inString.end()) << endl;
		}
	}
	while(true)
	{
		cout << "Enter arithmetic expression."<<endl;

		Stack< Token* > expression;
		string input;
		getline(cin, input);
		vector< Token* > rpnExpression;
		//cout << input << endl;
		// procedure to convert infix expression to postfix expression
		for (string::iterator it=input.begin();it!=input.end(); it++)
		{
			if (*it==' ')
				continue;
			//cout << "iteration->"<<(*it) << endl;
			if (operatorPriority(*it)==0)	// opening bracket
			{
				expression.push(new Operator(*it));
			}
			if (operatorPriority(*it)==1)	// closing bracket => pop and display
			{
				// while expressionStack.top() != openingBracket
				while (!expression.top()->isOpeningBracket() && !expression.isEmpty())
				{
					if (expression.top()->isOperator())
					{
						//cout<<(dynamic_cast<Operator*>(expression.top())->getSigniture());
						rpnExpression.push_back(expression.top());
					}
					else// if (expression.top()->isOperand())
					{
						//cout<<(dynamic_cast<Operand*>(expression.top())->getValue());
						rpnExpression.push_back(expression.top());
						//rpnExpression.append(dynamic_cast<Operand*>(expression.top())->getValueString());
					}
					expression.pop();
				}
				expression.pop();
			}
			else if (1<operatorPriority(*it) && operatorPriority(*it)<100)	// operator
			{
				if (expression.isEmpty() || expression.top()->isOpeningBracket() || operatorPriority(dynamic_cast<Operator*>(expression.top())->getSigniture())>operatorPriority(*it))	// push
				{
					//cout<<"pushed"<<endl;
					expression.push(new Operator(*it));
				}
				else	// pop and display.		// TODO : needs clarification in algorithm. Should I Iterate here???? Working so far.
				{
					//cout << "popped"<<endl;
					while (true)
					{
						if (expression.top()->isOperator())
						{
							//cout << (dynamic_cast<Operator*>(expression.top())->getSigniture());
							rpnExpression.push_back(expression.top());
						}
						else// if (expression.top()->isOperand())
						{
							//cout<<(dynamic_cast<Operand*>(expression.top())->getValue());
							rpnExpression.push_back(expression.top());
						}
						expression.pop();
						if (expression.isEmpty() || expression.top()->isOpeningBracket() || operatorPriority(dynamic_cast<Operator*>(expression.top())->getSigniture())>operatorPriority(*it))
							break;
					}
					expression.push(new Operator(*it));
				}
			}
			else if (isStartOfNumber(*it))	// display operand
			{
				//cout <<"operand"<<endl;
				string str = readOperandSequence(it,input.end());
				rpnExpression.push_back(new Operand(str));
				//cout << str;
			}
		}
		while (!expression.isEmpty())//print all the remaining ones.
		{
			if (expression.top()->isOperator())
			{
				//cout << (dynamic_cast<Operator*>(expression.top())->getSigniture());
				rpnExpression.push_back(expression.top());
			}
			else
			{
				//cout<<(dynamic_cast<Operand*>(expression.top())->getValue());
				rpnExpression.push_back(expression.top());
			}
			expression.pop();
		}
		cout<<endl;
		// END OF the procedure.



		// SECOND PROCEDURE: RPN evaluation (postfix expression evaluation)
		for (vector<Token*>::iterator it=rpnExpression.begin();it!=rpnExpression.end(); it++)
		{
			//cout<<*it;
			if ((*it)->isOperand())
			{
				expression.push(*it);
			}
			if ((*it)->isOperator())
			{
				double operandSecond = (dynamic_cast<Operand*>(expression.pop())->getValue());
				double operandFirst = (dynamic_cast<Operand*>(expression.pop())->getValue());
				//cout << "o"<<operandFirst<<endl;
				//cout << "p"<<operandSecond<<endl;
				expression.push(new Operand(dynamic_cast<Operator*>(*it)->operation(operandFirst,operandSecond)));
				//cout<<"a"<<dynamic_cast<Operator*>(*it)->operation(operandFirst,operandSecond)<<endl;
			}
		}
		//cout<<endl;

		cout << input << " = "<<dynamic_cast<Operand*>(expression.pop())->getValue()<<endl<<endl;
	}
	return 0;
}//((3.2-2)*2)/2+1
