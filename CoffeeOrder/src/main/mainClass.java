package main;

import singleton.Singleton;

public class mainClass {
	public static void main(String[] args) {
		Singleton singleton = Singleton.getInstance();
		singleton.memCtrl.login();
	}
}
