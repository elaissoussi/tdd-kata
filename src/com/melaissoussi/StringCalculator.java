package com.melaissoussi;

import java.util.stream.IntStream;

public class StringCalculator 
{
	private static final String NEGATIVES_NOT_ALLOWED = "negatives not allowed : ";
	private static final String SPACE_DELIMITER = " ";
	private static String NOT_DIGIT_REGEX = "[^-?0-9]";
	
	
	public static int add(String numbers)
	{
		int sum = 0 ; 
		
		if(numbers.length() > 0)
		{
			String[] numberStrings= getNumberStrings(numbers);
			
			int[] validNumbers = getValidNumbers(numberStrings);
			
			handleNegatives(validNumbers);
			
			sum = IntStream.of(validNumbers).sum();
		}
		
		return sum ;
	}
	

	private static void handleNegatives(int[] validNumbers) 
	{
		String negatives = "" ;
		
		for (int i = 0; i < validNumbers.length; i++) 
		{
			if(validNumbers[i]< 0)
			{
				negatives +=  validNumbers[i] + " , ";
			}
		}
		
		
		if(negatives != "")
		{
			negatives = negatives.substring(0, negatives.length()-2);
			
			throw new RuntimeException(NEGATIVES_NOT_ALLOWED + negatives );
		}
	}


	private static String[] getNumberStrings(String numbers) 
	{
		String integerStrings = numbers.replaceAll(NOT_DIGIT_REGEX, SPACE_DELIMITER);
		String[] splittedIntegerStrings = integerStrings.split(SPACE_DELIMITER);
		
		return splittedIntegerStrings;
	}

	private static int[] getValidNumbers(String[] numberStrings) 
	{
		int[] numbers = new int[numberStrings.length] ; 
		
		for (int i = 0; i < numbers.length; i++) 
		{
			if(isValidNumber(numberStrings, i))
			{
				numbers[i] = Integer.parseInt(numberStrings[i]);
			}
		}
		return numbers;
	}


	private static boolean isValidNumber(String[] numberStrings, int i) 
	{
		return numberStrings[i] != null && numberStrings[i].length() > 0 && numberStrings[i].length() < 4;
	}

	public static void main(String[] args) 
	{
		String numbers = "//[*][%]\\n1*2%3**-5";

		System.out.println(add(numbers));;
		
		
	}

}
