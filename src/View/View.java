package View;

import Controller.InterpreterController;
import Model.ADT.MyDictionary;
import Model.ADT.MyList;
import Model.ADT.MyStack;
import Model.Command.ExitCommand;
import Model.Command.RunExample;
import Model.Expression.ArithmeticExpression;
import Model.Expression.ConstantExpression;
import Model.Expression.VariableExpression;
import Model.ProgramState;
import Model.Statement.*;
import Repository.*;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.util.Scanner;

public class View {

    private static void displayMenu(){
        System.out.println("Choose a program:");
        System.out.println("1: v = 2; print(v)");
        System.out.println("2: a = 2 + 3 * 5; b = a + 1; print(b)");
        System.out.println("3: a=2-2; (if a then v=2 else v=3); print(v)");
    }

    public static void main(String args[]) throws Exception {

//        v = 2; print(v);
        MyDictionary<String, Integer> symDict1 = new MyDictionary<>();
        MyList<Integer> out1 = new MyList<>();
        MyStack<IStatement> exeStack1 = new MyStack<>();
        MyDictionary<Integer, Pair<String, BufferedReader>> fileTable1 = new MyDictionary<>();

        IStatement ex1 = new CompoundStatement(new AssignmentStatement("v",new ConstantExpression(2)), new PrintStatement(new
                    VariableExpression("v")));
        ProgramState programState1 = new ProgramState(symDict1, exeStack1, out1, ex1, fileTable1);
        IRepository repo1 = new Repository("test.txt");
        repo1.add(programState1);
        InterpreterController ctrl1 = new InterpreterController(repo1);

//         a=2+3*5;b=a+1; print(b) is represented as:
        MyDictionary<String, Integer> symDict2 = new MyDictionary<>();
        MyList<Integer> out2 = new MyList<>();
        MyStack<IStatement> exeStack2 = new MyStack<>();
        MyDictionary<Integer, Pair<String, BufferedReader>> fileTable2 = new MyDictionary<>();
        IStatement ex2 = new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression(new ConstantExpression(2),'+',new
                    ArithmeticExpression(new ConstantExpression(3),'*' ,new ConstantExpression(5)))),
                    new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression(new VariableExpression("a"),'+', new
                            ConstantExpression(1))), new PrintStatement(new VariableExpression("b"))));
        ProgramState programState2 = new ProgramState(symDict2, exeStack2, out2, ex2, fileTable2);
        IRepository repo2 = new Repository("test2.txt");
        repo2.add(programState2);
        InterpreterController ctrl2 = new InterpreterController(repo2);

////      a=2-2;(If a Then v=2 Else v=3);Print(v) is represented as
        MyDictionary<String, Integer> symDict3 = new MyDictionary<>();
        MyList<Integer> out3 = new MyList<>();
        MyStack<IStatement> exeStack3 = new MyStack<>();
        MyDictionary<Integer, Pair<String, BufferedReader>> fileTable3 = new MyDictionary<>();
        IStatement ex3 = new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression( new ConstantExpression(2),'-', new
                    ConstantExpression(2))), new CompoundStatement(new IfStatement(new VariableExpression("a"), new AssignmentStatement("v", new ConstantExpression(2)), new
                    AssignmentStatement("v", new ConstantExpression(3))), new PrintStatement(new VariableExpression("v"))));
        ProgramState programState3 = new ProgramState(symDict3, exeStack3, out3, ex3, fileTable3);
        IRepository repo3 = new Repository("test3.txt");
        repo3.add(programState3);
        InterpreterController ctrl3 = new InterpreterController(repo3);

////      openRFile(var_f,"test.in");
////      readFile(var_f,var_c);print(var_c);
////      (if var_c then readFile(var_f,var_c);print(var_c)
////      else print(0));
////      closeRFile(var_f)
        MyDictionary<String, Integer> symDict4 = new MyDictionary<>();
        MyList<Integer> out4 = new MyList<>();
        MyStack<IStatement> exeStack4 = new MyStack<>();
        MyDictionary<Integer, Pair<String, BufferedReader>> fileTable4 = new MyDictionary<>();
        IStatement ex4 = new CompoundStatement(
                new OpenRFile("test.in", "var_f"),
                new CompoundStatement(
                        new ReadFile(new VariableExpression("var_f"), "var_c"),
                        new CompoundStatement(
                                new PrintStatement(new VariableExpression("var_c")),
                                new CompoundStatement(
                                        new IfStatement(
                                                new VariableExpression("var_c"),
                                                new CompoundStatement(
                                                        new ReadFile(new VariableExpression("var_f"), "var_c"),
                                                        new PrintStatement(new VariableExpression("var_c"))
                                                ),
                                                new PrintStatement(new ConstantExpression(0))
                                        ),
                                        new CloseRFile(new VariableExpression("var_f"))
                                )
                        )
                )
        );
        ProgramState programState4 = new ProgramState(symDict4, exeStack4, out4, ex4, fileTable4);
        IRepository repo4 = new Repository("test4.txt");
        repo4.add(programState4);
        InterpreterController ctrl4 = new InterpreterController(repo4);

//      openRFile(var_f,"test.in");
//      readFile(var_f+2,var_c);print(var_c);
//      (if var_c then readFile(var_f,var_c);print(var_c)
//      else print(0));
//      closeRFile(var_f)
        MyDictionary<String, Integer> symDict5 = new MyDictionary<>();
        MyList<Integer> out5 = new MyList<>();
        MyStack<IStatement> exeStack5 = new MyStack<>();
        MyDictionary<Integer, Pair<String, BufferedReader>> fileTable5 = new MyDictionary<>();
        IStatement ex5 = new CompoundStatement(
                new OpenRFile("test.in", "var_f"),
                new CompoundStatement(
                        new ReadFile(new ArithmeticExpression(new VariableExpression("var_f"), '+', new ConstantExpression( 2)), "var_c"),
                        new CompoundStatement(
                                new PrintStatement(new VariableExpression("var_c")),
                                new CompoundStatement(
                                        new IfStatement(
                                                new VariableExpression("var_c"),
                                                new CompoundStatement(
                                                        new ReadFile(new VariableExpression("var_f"), "var_c"),
                                                        new PrintStatement(new VariableExpression("var_c"))),
                                                new PrintStatement(new ConstantExpression(0))),
                                        new CloseRFile(new VariableExpression("var_f")))
                        )
                )
        );
        ProgramState programState5 = new ProgramState(symDict5, exeStack5, out5, ex5, fileTable5);
        IRepository repo5 = new Repository("test5.txt");
        repo5.add(programState5);
        InterpreterController ctrl5 = new InterpreterController(repo5);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctrl1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctrl2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctrl3));
        menu.addCommand(new RunExample("4", ex4.toString(), ctrl4));
        menu.addCommand(new RunExample("5", ex5.toString(), ctrl5));
        menu.show();

    }
}
