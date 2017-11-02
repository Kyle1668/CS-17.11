// Kyle O'Brien
// 9-3-17
// Assignment 1: Palindromes
// CS 17.11
// Summary: The program prompts a user to enter a numerical input.
// The program then recursively traverses through both ends of
// the string comparing if it's a palindrome. If any
// non-numeric characters are found, the operation
// ceases and the program asks for another input.

package edu.srjc.a1.OBrien.Kyle.palindromeTest;
import java.util.Scanner;

public class Main
{
    private static String getStringInput()
    {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Numerical Input: ");
        return input.next();
    }

    private static boolean isPalindrome(String inString)
    {
        int lowerBound = 0;
        int upperBound = inString.length() - 1;
        return isPalindromeSubstring(inString, lowerBound, upperBound);
    }

    private static boolean isPalindromeSubstring(String inString, int lowerBound, int upperBound)
    {
        // lowerChar is the character coming from the left of the string while upperChar comes from the right.
        char lowerChar = inString.charAt(lowerBound);
        char upperChar = inString.charAt(upperBound);

        if (lowerBound == upperBound)
        {
            return true;
        }
        else if (lowerBound > upperBound)
        {
            return false;
        }
        else if (lowerChar != upperChar)
        {
            return false;
        }
        else
        {
            isPalindromeSubstring(inString, ++lowerBound, --upperBound);
        }

        return true;
    }

    private static boolean checkForNonNumericChar(String inString)
    {
        char firstChar = inString.charAt(0);

        if (!Character.isDigit(firstChar))
        {
            return false;
        }
        else
        {
            // If the first char is a digit, iterate through the rest of the input.
            for (int i = 0; i < inString.length() - 1; i++)
            {
                if (!Character.isDigit(inString.charAt(i)))
                {
                    return false;
                }
            }
        }

        return true;
    }

    private static void palindromeTest()
    {
        String input = getStringInput();

        if (input.charAt(0) != '-')
        {
            if (!checkForNonNumericChar(input))
            {
                System.out.print("Non numeric character found.\n");
                palindromeTest();
            }
            else
            {
                String output = isPalindrome(input) ? input + " is a palindrome!" : input + " is not palindrome!";
                System.out.print(output + "\n");
            }
        }
        else
        {
            System.out.print("Program Exited");
        }
    }

    public static void main(String[] args)
    {
        palindromeTest();
    }
}
