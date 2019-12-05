package com.itbjx.oauth2;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBCcrypr {
	public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("admin"));
	}
}
