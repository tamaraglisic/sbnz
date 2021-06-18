package com.project.sellerapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.sellerapp.dto.LoginEvent;
import com.project.sellerapp.dto.UserLoginDTO;
import com.project.sellerapp.dto.UserTokenStateDTO;
import com.project.sellerapp.model.User;
import com.project.sellerapp.repository.UserRepository;
import com.project.sellerapp.security.TokenUtils;
import com.project.sellerapp.service.AdminService;
import com.project.sellerapp.service.CustomUserDetailsService;
import com.project.sellerapp.service.KieService;
import com.project.sellerapp.service.RegisteredUserService;

//Kontroler zaduzen za autentifikaciju korisnika
@RestController
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {

    @Autowired
    private TokenUtils tokenUtils;

    @Autowired 
	private AdminService adminService;
    @Autowired 
   	private RegisteredUserService regUserService;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
	private ApplicationEventPublisher eventPublisher;
  
    @Autowired
    private KieService kieService;

    // Prvi endpoint koji pogadja korisnik kada se loguje.
    // Tada zna samo svoje korisnicko ime i lozinku i to prosledjuje na backend.
    @RequestMapping(value = "/log-in", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserLoginDTO authenticationRequest,
                                                                    HttpServletResponse response) {
    	try {
    		Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword()));
           
            // Ubaci korisnika u trenutni security kontekst
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            // Kreiraj token za tog korisnika
            User user = (User) authentication.getPrincipal();
            User dbUser = userRepository.findByEmail(user.getUsername());
            dbUser.setActive(true);
            userRepository.save(dbUser);
      
            
            String jwt = tokenUtils.generateToken(user); // prijavljujemo se na sistem sa email adresom
            int expiresIn = tokenUtils.getExpiredIn();
                    
            // Vrati token kao odgovor na uspesnu autentifikaciju
            return ResponseEntity.ok(new UserTokenStateDTO(jwt, expiresIn));
    	}
    	catch(Exception e) {
    		LoginEvent event = new LoginEvent(authenticationRequest.getUsername());
    		kieService.getCepSession().insert(event);
    		kieService.getCepSession().getAgenda().getAgendaGroup("login").setFocus();
    		kieService.getCepSession().fireAllRules();
    		System.out.println("login event");
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    	}
    }

    @RequestMapping(value = "/sign-out", method = RequestMethod.GET)
    public ResponseEntity<?> signOut() throws Exception {   	 
    	SecurityContextHolder.getContext().setAuthentication(null);
        return new ResponseEntity<>(HttpStatus.OK); 
    }


	// U slucaju isteka vazenja JWT tokena, endpoint koji se poziva da se token osvezi
    @RequestMapping(value = "/refresh", method = RequestMethod.POST)
    public ResponseEntity<UserTokenStateDTO> refreshAuthenticationToken(HttpServletRequest request) {
        String token = tokenUtils.getToken(request);
        String username = this.tokenUtils.getUsernameFromToken(token);
        User user = (User) this.userDetailsService.loadUserByUsername(username);

        if (this.tokenUtils.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = tokenUtils.refreshToken(token);
            int expiresIn = tokenUtils.getExpiredIn();

            return ResponseEntity.ok(new UserTokenStateDTO(refreshedToken, expiresIn));
        } else {
            UserTokenStateDTO userTokenState = new UserTokenStateDTO();
            return ResponseEntity.badRequest().body(userTokenState);
        }
    }
    
}
