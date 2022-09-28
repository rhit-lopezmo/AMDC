package GUI;
import java.util.Scanner;

import backendFeatures.BackendFeatures;

public class main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Please provide the name of this member: ");
		String memName = sc.nextLine();
		System.out.print("Please provide a code length: ");
		String codeLength = sc.nextLine();
		System.out.print("Please provide a member ID: ");
		String memID = sc.nextLine();
		System.out.println("One-Use Unique Code: " + BackendFeatures.randomCodeGen(Integer.parseInt(codeLength), memName, memID));
	}
}
