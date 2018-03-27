package ua.prog.java.lesson5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class FileOperation {

	// 1. Напишите программу, которая скопирует файлы с заранее
	// определенным расширением(например, только doc) из
	// каталога источника в каталог приемник.

	// 2. Напишите программу, которая примет на вход два
	// текстовых файла, а вернет один. Содержимым этого файла
	// должны быть слова, которые одновременно есть и в первом и
	// во втором файле.

	public FileOperation() {

	}

	private String textFileInOneString(String firstFilePath) {
		String textFileInOneString = "";
		try (BufferedReader buffer = new BufferedReader(new FileReader(firstFilePath))) {
			String str = "";
			int wordsCounter = 0;
			System.out.print("Количество слов в файле " + firstFilePath + ": ");
			for (; (str = buffer.readLine()) != null;) {
				textFileInOneString += str + " ";
				wordsCounter += 1;
			}
			System.out.println(wordsCounter);
		} catch (IOException e) {
			System.out.println("Some IO Error in textFileInOneString module");
			System.out.println(e.getMessage());
		}
		return textFileInOneString;
	}

	private String compareTwoStrings(String firstString, String secondString) {
		String comparedString = "";
		List<String> firstStringArray = Arrays.asList(firstString.split(" "));
		List<String> secondStringArray = Arrays.asList(secondString.split(" "));
		Set<String> firstStringSet = new LinkedHashSet<String>(firstStringArray);
		Set<String> secondStringSet = new LinkedHashSet<String>(secondStringArray);

		int matchedWordsCounter = 0;
		for (String eachWordFirstArray : firstStringSet) {
			for (String eachWordSecondArray : secondStringSet) {
				if (eachWordFirstArray.equals(eachWordSecondArray)) {
					comparedString += eachWordFirstArray + " ";
					matchedWordsCounter += 1;
					break;
				}
			}
		}
		System.out.print("Количество одинаковых слов в файлах: " + matchedWordsCounter);
		return comparedString;
	}

	private void putStringToFile(String filePath, String stringData) {
		try (BufferedWriter buffer = new BufferedWriter(new FileWriter(filePath))) {
			buffer.write(stringData);
		} catch (IOException e) {
			System.out.println("Some IO Error in putStringToFile module");
		}
	}

	public void compareTwoTextFiles(String firstFilePath, String secondFilePath, String compareResultFilePath) {
		String firstFileToString = textFileInOneString(firstFilePath);
		String secondFileToString = textFileInOneString(secondFilePath);
		String comparedFilesToString = compareTwoStrings(firstFileToString, secondFileToString);
		putStringToFile(compareResultFilePath, comparedFilesToString);

	}

	private File[] filesListWithDefiledExtensions(String sourceFolderPath, String[] filesExtensions) {
		MyFileFilter mFF = new MyFileFilter(filesExtensions);
		File folder = new File(sourceFolderPath);
		File[] filesList = folder.listFiles(mFF);
		for (File file : filesList) {
			System.out.println(file);
			System.out.println(file.getName());
		}
		return filesList;
	}

	public void copyFiles(String sourceFolderPath, String DestinationFolderPatch, String[] filesExtensions) {
		File[] filesListSourceFolder = filesListWithDefiledExtensions(sourceFolderPath, filesExtensions);
		for (File sourceFile : filesListSourceFolder) {

			try (FileInputStream fis = new FileInputStream(sourceFile.getAbsolutePath());
					FileOutputStream fos = new FileOutputStream(DestinationFolderPatch + "/" + sourceFile.getName())) {
				byte[] bufer = new byte[1024];
				int byteread = 0;
				for (; (byteread = fis.read(bufer)) > 0;) {
					fos.write(bufer, 0, byteread);
				}
			} catch (IOException e) {
				System.out.println("Something wrong with CopyFiles " + e);
			}

		}
	}
}
