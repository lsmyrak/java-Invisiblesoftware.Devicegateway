package eu.lsmyrak.invisiblesoftware.Devicegateway.services;

import eu.lsmyrak.invisiblesoftware.Devicegateway.application.auth.command.dtos.AuthResult;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.auth.command.dtos.LoginDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.auth.command.dtos.RegisterDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.ApplicationUser;

public interface AuthService {
    
    AuthResult register(RegisterDto registerDto);
    AuthResult login(LoginDto loginDto);
    String generateTokken(ApplicationUser user);
}
