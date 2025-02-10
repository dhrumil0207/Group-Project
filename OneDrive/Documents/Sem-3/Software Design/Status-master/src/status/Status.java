/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package status;

import java.util.Scanner;

/**
 * This class takes String input from user and calls method statusDetail to
 * print the status of User-Rejected,Pending,Processing and Approved. if the
 * input is not given in String, program will accept and produce wrong results.
 * change the code to use enums which avoids String input then print the status
 * details.
 *
 * @author srinivsi
 */
public class Status {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the user status code (ZERO, ONE, TWO, THREE, FOUR):");

        String inputCode = scanner.next().toUpperCase();
        StatusEnum status = StatusEnum.fromString(inputCode);

        if (status != null) {
            System.out.println("Status Selected: " + status);
            System.out.println("New status has been successfully created.");
        } else {
            System.out.println("Invalid status code. Please enter a valid code (ZERO, ONE, TWO, THREE, FOUR).");
        }

        scanner.close();
    }
}
