package com.hospital.HospitalManagementSystem;

import java.util.Scanner;

import com.hospital.HospitalManagementSystem.login.Login;
import com.hospital.HospitalManagementSystem.login.LoginImpl;
import com.hospital.HospitalManagementSystem.model.user.userAuth.UserAuth;
import com.hospital.HospitalManagementSystem.panel.execute.Execute;

public class App {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	Login login = null;
    	Execute execute = null;

    	boolean exit = false;
    	int choice = -1;
    	
        System.out.println("-----Welcome to the Hospital-----");
        
        do {
        	System.out.println("1. Login");
        	System.out.println("2. Exit the Application");
        	System.out.println("Enter your choice:");
        	if (sc.hasNextInt()) {
        		choice = sc.nextInt(); 		        		        		
        	} else {
        		System.err.println("Enter a number!");
        		sc.nextLine();
        		continue;
        	}
        	
        	sc.nextLine();
        	
        	if (choice == 1) {
        		login = new LoginImpl();
            	System.out.println("1. Enter the username:");
        		String userName = sc.nextLine();
        		System.out.println("2. Enter the password");
            	String password = sc.nextLine();
            	
            	UserAuth userAuth = new UserAuth(userName, password);
            	execute = login.login(userAuth);
            	
            	if (execute == null) {
            		System.out.println("Wrong Username or Password!");
            		continue;
            	}
            	
            	execute.execute();
            	
            	System.out.println("----------------------------------------");
        	} else if (choice == 2) {
        		System.out.println("Thanks for using our application");
        		exit = true;
        	} else {
        		System.out.println("Invalid choice!");
        	}
        	
        } while (!exit);
        
        sc.close();
    }
}
