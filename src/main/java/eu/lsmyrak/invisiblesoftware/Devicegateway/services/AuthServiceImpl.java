package eu.lsmyrak.invisiblesoftware.Devicegateway.services;

import org.springframework.stereotype.Service;

import eu.lsmyrak.invisiblesoftware.Devicegateway.application.auth.command.dtos.AuthResult;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.auth.command.dtos.LoginDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.application.auth.command.dtos.RegisterDto;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.model.ApplicationUser;
import eu.lsmyrak.invisiblesoftware.Devicegateway.domain.repository.ApplicationUserRepository;

@Service
public class AuthServiceImpl implements  AuthService{

    private final ApplicationUserRepository applicationUserRepository;
    
    public AuthServiceImpl(ApplicationUserRepository applicationUserRepository) {
        this.applicationUserRepository = applicationUserRepository;
    }
    @Override
    public AuthResult register(RegisterDto registerDto) {
       var user = applicationUserRepository.findByEmail(registerDto.getEmail());
       if(user.isPresent()){
        return new AuthResult(false,"User with this email already exists",null);
       }
         var newUser = new ApplicationUser();
            newUser.setEmail(registerDto.getEmail());
            newUser.setUserName(registerDto.getUsername());
            newUser.setPasswordHash(registerDto.getPassword()); 
        applicationUserRepository.save(newUser);
        return new AuthResult(true,"User registered successfully",null);
    }

    @Override
    public AuthResult login(LoginDto loginDto) {
        var user = applicationUserRepository.findByEmail(loginDto.getEmail());
        if(user.isEmpty()){
            return new AuthResult(false,"User with this email does not exist",null);
        }
        var existingUser = user.get();
        if(!existingUser.getPasswordHash().equals(loginDto.getPassword())){
            return new AuthResult(false,"Invalid password",null);
        }
        return new AuthResult(true,"User logged in successfully",null);
    }

    @Override
    public String generateTokken(ApplicationUser user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generateTokken'");
    }

}
