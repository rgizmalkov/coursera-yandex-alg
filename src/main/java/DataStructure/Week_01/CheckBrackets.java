package DataStructure.Week_01;

import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

/**
 Problem Description
    Task. Your friend is making a text editor for programmers. He is currently working on a feature that will
 find errors in the usage of different types of brackets. Code can contain any brackets from the set
 []{}(), where the opening brackets are [,{, and ( and the closing brackets corresponding to them
 are ],}, and ).
    For convenience, the text editor should not only inform the user that there is an error in the usage
 of brackets, but also point to the exact place in the code with the problematic bracket. First priority
 is to find the first unmatched closing bracket which either doesn’t have an opening bracket before it,
 like ] in ](), or closes the wrong opening bracket, like } in ()[}. If there are no such mistakes, then
 it should find the first unmatched opening bracket without the corresponding closing bracket after it,
 like ( in {}([]. If there are no mistakes, text editor should inform the user that the usage of brackets
 is correct.
    Apart from the brackets, code can contain big and small latin letters, digits and punctuation marks.
 More formally, all brackets in the code should be divided into pairs of matching brackets, such that in
 each pair the opening bracket goes before the closing bracket, and for any two pairs of brackets either
 one of them is nested inside another one as in (foo[bar]) or they are separate as in f(a,b)-g[c].
 The bracket [ corresponds to the bracket ], { corresponds to }, and ( corresponds to ).
 Input Format. Input contains one string 𝑆 which consists of big and small latin letters, digits, punctuation
 marks and brackets from the set []{}().
    Constraints. The length of 𝑆 is at least 1 and at most 105.
    Output Format. If the code in 𝑆 uses brackets correctly, output “Success" (without the quotes). Otherwise,
 output the 1-based index of the first unmatched closing bracket, and if there are no unmatched closing
 brackets, output the 1-based index of the first unmatched opening bracket.
    Time Limits. C: 1 sec, C++: 1 sec, Java: 1 sec, Python: 1 sec. C#: 1.5 sec, Haskell: 2 sec, JavaScript: 3
 sec, Ruby: 3 sec, Scala: 3 sec.
 Memory Limit. 512MB.
 */
public class CheckBrackets {
    private Stack<Character> array;

    public CheckBrackets(){
        this.array = new Stack<Character>();
    }

    public int method(String line){

        for (int i = 0; i < line.length(); i++) {
            Character ch = line.charAt(i);
            if( '(' == ch  || '{' == ch  || '[' == ch){
                array.add(ch);
            }else if(')' == ch  || '}' == ch  || ']' == ch){
                Character popped = array.pop();

            }

        }


        clean();
        return 0;
    }

    private void clean(){
        if(array != null) array.clear();
    }

    public static void main(String[] args) {
        Character ch = '[';
        Character ch1 = ']';
        Character ch2 = '{';
        Character ch3 = '}';
        Character ch4 = '(';
        Character ch5 = ')';
        System.out.println(ch.hashCode());
        System.out.println(ch1.hashCode());
        System.out.println(ch2.hashCode());
        System.out.println(ch3.hashCode());
        System.out.println(ch4.hashCode());
        System.out.println(ch5.hashCode());
    }
}
