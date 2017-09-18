package com.cloudleaf.webautomation;

import java.util.Random;

public class RandomNumber {

	public static void main(String[] args) {
		Random random = new Random();
		String id = String.format("%04d", random.nextInt(10000));
		System.out.println(id);

	}

}
