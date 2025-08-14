public class StringManipulation{
public static void main(String[] args) {
// TODO: Create the same string "Java Programming" using 3 different methods:
// 1. String literal
String name="JAVA PROGRAMMING";
String name2="Java Programming";
String obj = new String("Java Programming");
char nameList[]={'J','a','v','a'};
if(name==name2)
	{
	System.out.println("Equal");
	}
else
	{
	System.out.println("Not Equal");
	}
	if(obj.equalsIgnoreCase(name))
	{
	System.out.println("Equal");
	}
else
	{
	System.out.println("Not Equal");
	}
	System.out.println("Programming Quote:\n Code is poetry Unknown \n Path: C:\\Java\\Projects");
// 2. new String() constructor
// 3. Character array
// TODO: Compare the strings using == and .equals()
// Print the results and explain the difference
// TODO: Create a string with escape sequences that displays:
// Programming Quote:
// "Code is poetry" - Unknown
// Path: C:\Java\Projects
}
}