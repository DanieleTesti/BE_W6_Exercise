package main.ex.spring.service;

import main.ex.spring.payload.LoginDto;
import main.ex.spring.payload.RegisterDto;

public interface AuthService {
    
	String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
    
}
