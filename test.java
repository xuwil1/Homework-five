import java.io.*;
import java.util.*;

public class test {
public static void main(String[]args) {
	try {
	Scanner sc= new Scanner(System.in);
	 System.out.print("Enter name of of output file: ");
	 String output=sc.nextLine();
	 PrintWriter writer= new PrintWriter(output); 
	 writer.println("<!DOCTYPE html>\n<html>\n<head>\n"
	 + "<title>Results of Markdown Translation</title>\n</head>\n<body>");
	}catch(FileNotFoundException e) {}
}
}
