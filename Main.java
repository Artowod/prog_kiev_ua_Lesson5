package ua.prog.java.lesson5;

public class Main {

	public static void main(String[] args) {
		FileOperation operations = new FileOperation();
		operations.copyFiles("C:/Java/SourceFolder", "C:/Java/DestinationFolder", new String[] { "jar", "zip" });

		String firstFilePath = "C:/Java/SourceFolder/1.txt";
		String secondFilePath = "C:/Java/SourceFolder/2.txt";
		String resultedFile = "C:/Java/SourceFolder/compare_result.txt";
		operations.compareTwoTextFiles(firstFilePath, secondFilePath, resultedFile);
	}
}
