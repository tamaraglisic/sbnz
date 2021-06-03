package com.project.sellerapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.project.sellerapp.dto.TicketUserDTO;
import com.project.sellerapp.dto.TicketsDTO;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketsServiceUnitTests {
	
	private static KieContainer kieContainer;
	private static SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");

    @Before
    public void setup() {
        KieServices kieServices = KieServices.Factory.get();
        kieContainer = kieServices.newKieContainer(kieServices.
                newReleaseId("com.main", "sellerapp-drools", "0.0.1-SNAPSHOT"));
    }
    
    @Test
    public void testFamilyTicketType() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket("POJEDINACNA");
		String inputString1 = "25 05 2021";
		String inputString2 = "28 05 2021";
		try {
			Date date1 = myFormat.parse(inputString1);
		    Date date2 = myFormat.parse(inputString2);
		    t.setUsingStart(date1);
		    t.setUsingEnd(date2);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO odrasli = new TicketUserDTO(1L, "ODRASLI", 2, 100);
		TicketUserDTO deca = new TicketUserDTO(1L, "DECA", 3, 100);
		ticketUsers.add(odrasli);
		ticketUsers.add(deca);
		t.setTicketUsers(ticketUsers);
		
		t.setUsingPeriod("DNEVNA");
		
		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.getAgenda().getAgendaGroup("type_ticket").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		assertEquals("PORODICNA", t.getTypeTicket());
		
		kieSession.dispose();
    }
    
    @Test
    public void testGroupTicketType() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket("POJEDINACNA");
		String inputString1 = "25 05 2021";
		String inputString2 = "28 05 2021";
		try {
			Date date1 = myFormat.parse(inputString1);
		    Date date2 = myFormat.parse(inputString2);
		    t.setUsingStart(date1);
		    t.setUsingEnd(date2);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO odrasli = new TicketUserDTO(1L, "ODRASLI", 20, 100);
		ticketUsers.add(odrasli);
		t.setTicketUsers(ticketUsers);
		
		t.setUsingPeriod("DNEVNA");
		
		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.getAgenda().getAgendaGroup("type_ticket").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		assertEquals("GRUPNA", t.getTypeTicket());
		
		kieSession.dispose();
    }
    
	@Test
    public void testHalfDayUsingPeriod() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket("POJEDINACNA");
    	t.setUsingPeriod("POLUDNEVNA");
		
    	String inputString1 = "25 05 2021";
		String inputString2 = "28 05 2021";
		try {
			Date date1 = myFormat.parse(inputString1);
		    Date date2 = myFormat.parse(inputString2);
		    t.setUsingStart(date1);
		    t.setUsingEnd(date2);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO odrasli = new TicketUserDTO(1L, "ODRASLI", 2, 100);
		ticketUsers.add(odrasli);
		t.setTicketUsers(ticketUsers);
		
		
		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.getAgenda().getAgendaGroup("using_period").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		assertEquals(Double.valueOf(65), t.getTicketUsers().stream().findFirst().get().getSingleTicketPrice());
		
		kieSession.dispose();
    }
    
	@Test
    public void testNightUsingPeriod() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket("POJEDINACNA");
    	t.setUsingPeriod("NOCNA");
  
    	String inputString1 = "25 05 2021";
		String inputString2 = "28 05 2021";
		try {
			Date date1 = myFormat.parse(inputString1);
		    Date date2 = myFormat.parse(inputString2);
		    t.setUsingStart(date1);
		    t.setUsingEnd(date2);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO odrasli = new TicketUserDTO(1L, "ODRASLI", 2, 100);
		ticketUsers.add(odrasli);
		t.setTicketUsers(ticketUsers);
		
		
		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.getAgenda().getAgendaGroup("using_period").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		assertEquals(Double.valueOf(125), t.getTicketUsers().stream().findFirst().get().getSingleTicketPrice());
		
		kieSession.dispose();
    }
	
	@Test
    public void testGroupDiscount() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket("GRUPNA");
    	t.setUsingPeriod("NOCNA");
  
    	String inputString1 = "25 05 2021";
		String inputString2 = "28 05 2021";
		try {
			Date date1 = myFormat.parse(inputString1);
		    Date date2 = myFormat.parse(inputString2);
		    t.setUsingStart(date1);
		    t.setUsingEnd(date2);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		t.setBill(100);
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO odrasli = new TicketUserDTO(1L, "ODRASLI", 20, 100);
		ticketUsers.add(odrasli);
		t.setTicketUsers(ticketUsers);
		
		
		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.getAgenda().getAgendaGroup("type_ticket_discount").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		assertEquals(Double.valueOf(90), t.getBill());
		
		kieSession.dispose();
    }
	
	@Test
    public void testFamilyDiscount() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket("PORODICNA");
    	t.setUsingPeriod("NOCNA");
  
    	String inputString1 = "25 05 2021";
		String inputString2 = "28 05 2021";
		try {
			Date date1 = myFormat.parse(inputString1);
		    Date date2 = myFormat.parse(inputString2);
		    t.setUsingStart(date1);
		    t.setUsingEnd(date2);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		t.setBill(100);
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO odrasli = new TicketUserDTO(1L, "ODRASLI", 2, 100);
		TicketUserDTO deca = new TicketUserDTO(2L, "DECA", 2, 100);
		ticketUsers.add(odrasli);
		ticketUsers.add(deca);
		t.setTicketUsers(ticketUsers);
		
		
		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.getAgenda().getAgendaGroup("type_ticket_discount").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		assertEquals(Double.valueOf(85), t.getBill());
		
		kieSession.dispose();
    }
	
	@Test
    public void testPeriodDiscountChild() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket("PORODICNA");
    	t.setUsingPeriod("NOCNA");
  
    	String inputString1 = "25 05 2021";
		String inputString2 = "28 05 2021";
		try {
			Date date1 = myFormat.parse(inputString1);
		    Date date2 = myFormat.parse(inputString2);
		    t.setUsingStart(date1);
		    t.setUsingEnd(date2);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		t.setBill(100);
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO deca = new TicketUserDTO(2L, "DECA", 2, 100);
		ticketUsers.add(deca);
		t.setTicketUsers(ticketUsers);
		
		
		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.getAgenda().getAgendaGroup("period_discount").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		assertEquals(Double.valueOf(72), t.getTicketUsers().stream().findFirst().get().getSingleTicketPrice());
		
		kieSession.dispose();
    }
	
	@Test
    public void testPeriodDiscountSenior() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket("PORODICNA");
    	t.setUsingPeriod("NOCNA");
  
    	String inputString1 = "25 05 2021";
		String inputString2 = "28 05 2021";
		try {
			Date date1 = myFormat.parse(inputString1);
		    Date date2 = myFormat.parse(inputString2);
		    t.setUsingStart(date1);
		    t.setUsingEnd(date2);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		t.setBill(100);
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO deca = new TicketUserDTO(2L, "SENIOR", 2, 100);
		ticketUsers.add(deca);
		t.setTicketUsers(ticketUsers);
		
		
		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.getAgenda().getAgendaGroup("period_discount").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		assertEquals(Double.valueOf(84), t.getTicketUsers().stream().findFirst().get().getSingleTicketPrice());
		
		kieSession.dispose();
    }
	
	@Test
    public void testCalculatingBillNoPrivileges() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket("POJEDINACNA");
    	t.setUsingPeriod("DNEVNA");
  
    	String inputString1 = "25 05 2021";
		String inputString2 = "28 05 2021";
		try {
			Date date1 = myFormat.parse(inputString1);
		    Date date2 = myFormat.parse(inputString2);
		    t.setUsingStart(date1);
		    t.setUsingEnd(date2);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		t.setBill(100);
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO odrasli = new TicketUserDTO(2L, "ODRASLI", 2, 100);
		ticketUsers.add(odrasli);
		t.setTicketUsers(ticketUsers);
		
		
		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.getAgenda().getAgendaGroup("calculating_bill").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		assertEquals(Double.valueOf(800), t.getBill());
		
		kieSession.dispose();
    }
    
	@Test
    public void testStudentAndLoyalty() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket("POJEDINACNA");
    	t.setUsingPeriod("DNEVNA");
  
    	String inputString1 = "25 05 2021";
		String inputString2 = "28 05 2021";
		try {
			Date date1 = myFormat.parse(inputString1);
		    Date date2 = myFormat.parse(inputString2);
		    t.setUsingStart(date1);
		    t.setUsingEnd(date2);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		t.setBill(0);
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO odrasli = new TicketUserDTO(1L, "ODRASLI", 5, 100);
		ticketUsers.add(odrasli);
		TicketUserDTO senior = new TicketUserDTO(3L, "SENIOR", 2, 100);
		ticketUsers.add(senior);
		t.setTicketUsers(ticketUsers);
		
		Set<String> privilege = new HashSet<>();
		privilege.add("Student 123");
		privilege.add("Loyalty 444");
		
		t.setPrivilege(privilege);
		
		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.getAgenda().getAgendaGroup("calculating_bill").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		/*
		 * studentski popust 10%
		 * loyalty popust 15%
		 * 90+85+300+200
		 * *4
		 * =2700
		 * 
		 */
		assertEquals(Double.valueOf(2700), t.getBill());
		
		kieSession.dispose();
    }
	
	@Test
    public void testStudentFamily() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket("PORODICNA");
    	t.setUsingPeriod("DNEVNA");
  
    	String inputString1 = "25 05 2021";
		String inputString2 = "28 05 2021";
		try {
			Date date1 = myFormat.parse(inputString1);
		    Date date2 = myFormat.parse(inputString2);
		    t.setUsingStart(date1);
		    t.setUsingEnd(date2);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		t.setBill(0);
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO odrasli = new TicketUserDTO(1L, "ODRASLI", 5, 100);
		ticketUsers.add(odrasli);
		TicketUserDTO senior = new TicketUserDTO(3L, "DECA", 2, 100);
		ticketUsers.add(senior);
		t.setTicketUsers(ticketUsers);
		
		Set<String> privilege = new HashSet<>();
		privilege.add("Student 123");
		
		t.setPrivilege(privilege);
		
		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.getAgenda().getAgendaGroup("calculating_bill").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		/*
		 * studentski popust 10%
		 *
		 * 90+400+200
		 * *4
		 * =2760
		 * 
		 */
		assertEquals(Double.valueOf(2760), t.getBill());
		
		kieSession.dispose();
    }
    
	@Test
    public void testLoyaltyFamily() {
    	
    	TicketsDTO t = new TicketsDTO();
    	t.setTypeTicket("PORODICNA");
    	t.setUsingPeriod("DNEVNA");
  
    	String inputString1 = "25 05 2021";
		String inputString2 = "28 05 2021";
		try {
			Date date1 = myFormat.parse(inputString1);
		    Date date2 = myFormat.parse(inputString2);
		    t.setUsingStart(date1);
		    t.setUsingEnd(date2);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		t.setBill(0);
		Set<TicketUserDTO> ticketUsers = new HashSet<>();
		TicketUserDTO odrasli = new TicketUserDTO(1L, "ODRASLI", 5, 100);
		ticketUsers.add(odrasli);
		TicketUserDTO senior = new TicketUserDTO(3L, "DECA", 2, 100);
		ticketUsers.add(senior);
		t.setTicketUsers(ticketUsers);
		
		Set<String> privilege = new HashSet<>();
		privilege.add("Loyalty 444");
		
		t.setPrivilege(privilege);
		
		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.getAgenda().getAgendaGroup("calculating_bill").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		kieSession.getAgenda().getAgendaGroup("type_ticket_discount").setFocus();
		kieSession.insert(t);
		
		kieSession.fireAllRules();
		/*
		 * 
		 * loyalty popust 15%
		 * family discount 15%
		 * 7*100
		 * *4
		 * =2800
		 * 2800-15% = 2380
		 * 2380 -15% = 2023
		 */
		assertEquals(Double.valueOf(2023), t.getBill());
		
		kieSession.dispose();
    }

}
