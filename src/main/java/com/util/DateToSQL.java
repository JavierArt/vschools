package com.util;

import java.sql.Date;
import java.util.Scanner;

public class DateToSQL {

	public static Date transformDateToSQL()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("insert day");
		int day = scan.nextInt();
		while(day>31)day = scan.nextInt();
		System.out.println("insert month");
		int month = scan.nextInt();
		while(month>12)month = scan.nextInt();
		System.out.println("insert year");
		int year = scan.nextInt();
		while(String.valueOf(year).length()<4) year = scan.nextInt();
		
		String strdate = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);
		System.out.println(strdate);
		Date date=Date.valueOf(strdate);
		return date;
	}
}
