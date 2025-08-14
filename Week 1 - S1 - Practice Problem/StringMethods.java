import java.util.Scanner;
public class StringMethods{
public static void main(String[] args) {
Scanner SC = new Scanner(System.in);
System.out.println("Enter your full name(first and last name)");
String name=SC.nextLine();
System.out.println("Enter your favorite programming language");
String language=SC.nextLine();
System.out.println("Enter a sentence about your programming experience");
String experience=SC.nextLine();
String[] firstNdLast =name.trim().split("\\s+");
System.out.println("First Name = "+firstNdLast[0]);
System.out.println("Last Name = "+firstNdLast[firstNdLast.length-1]);
int length=language.length(); int count=0;
for(int x=0;x<length;x++)
{	
	char letter=language.charAt(x);
	if(!Character.isWhitespace(letter))
	{
		count++;
	}
	
}
String UpperLanguage=language.toUpperCase();
System.out.println("SUMMARY");
System.out.println("First Name = "+firstNdLast[0]);
System.out.println("Last Name = "+firstNdLast[firstNdLast.length-1]);
System.out.println("Total Characters = "+count);
System.out.println("Programming language after UpperCase = "+UpperLanguage);
// TODO: Ask user for their full name (first and last name)
// TODO: Ask user for their favorite programming language
// TODO: Ask user for a sentence about their programming experience
// TODO: Process the input:
// 1. Extract first and last name separately
// 2. Count total characters in the sentence (excluding spaces)
// 3. Convert programming language to uppercase
// 4. Display a formatted summary
SC.close();
}
}