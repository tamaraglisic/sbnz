package com.project.sellerapp;

import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import static org.junit.Assert.assertEquals;


import com.project.sellerapp.dto.LoginEvent;

public class CEPRulesTests {
	


	@Test
    public void testCEPRules() {
		KieServices ks = KieServices.Factory.get();
	    KieContainer kContainer = ks.newKieContainer(ks.newReleaseId("com.main", "sellerapp-drools", "0.0.1-SNAPSHOT"));

	    KieSession session = kContainer.newKieSession("cepSession");

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
}
