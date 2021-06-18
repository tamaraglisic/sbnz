package com.project.sellerapp;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.project.sellerapp.dto.LoginEvent;
import com.project.sellerapp.dto.ReservationEvent;

public class CEPRulesTests {
	


	@Test
    public void testLoginCEPRules() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("com.main", "sellerapp-drools", "0.0.1-SNAPSHOT"));

	    KieSession session = kContainer.newKieSession("cepSession");
	    session.getAgenda().getAgendaGroup("login").setFocus();

	    session.insert(new LoginEvent("admin@gmail.com"));
	    session.insert(new LoginEvent("admin@gmail.com"));
	    session.insert(new LoginEvent("admin@gmail.com"));
	    session.insert(new LoginEvent("admin@gmail.com"));
	    session.insert(new LoginEvent("admin@gmail.com"));
	    session.insert(new LoginEvent("admin@gmail.com"));
	    int firedRules = session.fireAllRules();
        session.dispose();

	    assertEquals(1, firedRules);
    }
	
	@Test
    public void testReservationCEPRules() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("com.main", "sellerapp-drools", "0.0.1-SNAPSHOT"));

	    KieSession session = kContainer.newKieSession("cepSession");
	    session.getAgenda().getAgendaGroup("reservations").setFocus();
	    
	    session.insert(new ReservationEvent("admin@gmail.com"));
	    session.insert(new ReservationEvent("admin@gmail.com"));
	    session.insert(new ReservationEvent("admin@gmail.com"));
	    session.insert(new ReservationEvent("admin@gmail.com"));
	    session.insert(new ReservationEvent("admin@gmail.com"));
	    session.insert(new ReservationEvent("admin@gmail.com"));
	    session.insert(new ReservationEvent("admin@gmail.com"));
	    session.insert(new ReservationEvent("admin@gmail.com"));

	    int firedRules = session.fireAllRules();
        session.dispose();

	    assertEquals(1, firedRules);
    }
	
	
}
