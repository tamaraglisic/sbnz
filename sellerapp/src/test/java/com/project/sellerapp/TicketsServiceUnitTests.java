package com.project.sellerapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import com.project.sellerapp.dto.RegisteredUserDTO;
import com.project.sellerapp.dto.SkiResortDTO;
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
    public void testLiftTransportType() {
    	
    	SkiResortDTO resort = new SkiResortDTO();
    	resort.setLiftPrice(4500);
    	TicketsDTO t = new TicketsDTO();
    	t.setSkiResort(resort);
    	t.setTypeTicket("POJEDINACNA");
    	t.setUsingPeriod("POLUDNEVNA");
    	t.setTransportType("LIFT");
		
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
		TicketUserDTO odrasli = new TicketUserDTO(1L, "ODRASLI", 2, 0);
		ticketUsers.add(odrasli);
		t.setTicketUsers(ticketUsers);
		
		
		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.getAgenda().getAgendaGroup("transport_type").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		assertEquals(Double.valueOf(4500), t.getTicketUsers().stream().findFirst().get().getSingleTicketPrice());
		
		kieSession.dispose();
    }
    
    @Test
    public void testGondolaTransportType() {
    	
    	SkiResortDTO resort = new SkiResortDTO();
    	resort.setGondolaPrice(1000);
    	TicketsDTO t = new TicketsDTO();
    	t.setSkiResort(resort);
    	t.setTypeTicket("POJEDINACNA");
    	t.setUsingPeriod("POLUDNEVNA");
    	t.setTransportType("GONDOLA");
		
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
		TicketUserDTO odrasli = new TicketUserDTO(1L, "ODRASLI", 2, 0);
		ticketUsers.add(odrasli);
		t.setTicketUsers(ticketUsers);
		
		
		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.getAgenda().getAgendaGroup("transport_type").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		assertEquals(Double.valueOf(1000), t.getTicketUsers().stream().findFirst().get().getSingleTicketPrice());
		
		kieSession.dispose();
    }
    
    @Test
    public void testLiftAndGondolaTransportType() {
    	
    	SkiResortDTO resort = new SkiResortDTO();
    	resort.setLiftPrice(4500);
    	resort.setGondolaPrice(1000);
    	TicketsDTO t = new TicketsDTO();
    	t.setSkiResort(resort);
    	t.setTypeTicket("POJEDINACNA");
    	t.setUsingPeriod("POLUDNEVNA");
    	t.setTransportType("LIFT+GONDOLA");
		
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
		TicketUserDTO odrasli = new TicketUserDTO(1L, "ODRASLI", 2, 0);
		ticketUsers.add(odrasli);
		t.setTicketUsers(ticketUsers);
		
		
		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.getAgenda().getAgendaGroup("transport_type").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		assertEquals(Double.valueOf(5000), t.getTicketUsers().stream().findFirst().get().getSingleTicketPrice());
		
		kieSession.dispose();
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
	
	@Test
    public void regularGuest() {
		RegisteredUserDTO regUser =  new RegisteredUserDTO();
		Set<TicketsDTO> userTickets = new HashSet<>();
		SkiResortDTO resort = new SkiResortDTO(1L, "Kopaonik");
		
		resort.setOccupacyRate(55);
		// jedna kaarta
		TicketsDTO t = new TicketsDTO();
		t.setSkiResort(resort);
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
		t.setPrivilege(privilege);
		
		//  karta broj dva
		TicketsDTO t2 = new TicketsDTO();
		t2.setPrivilege(new HashSet<String>());
		t2.setSkiResort(resort);
    	t2.setTypeTicket("PORODICNA");
    	t2.setUsingPeriod("DNEVNA");
  
    	String inputString12 = "25 05 2020";
		String inputString22 = "28 05 2020";
		try {
			Date date1 = myFormat.parse(inputString12);
		    Date date2 = myFormat.parse(inputString22);
		    t2.setUsingStart(date1);
		    t2.setUsingEnd(date2);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		t2.setBill(0);
		t2.setTicketUsers(ticketUsers);
		
		// karta br tri
		TicketsDTO t3 = new TicketsDTO();
		t3.setSkiResort(resort);
    	t3.setTypeTicket("PORODICNA");
    	t3.setUsingPeriod("DNEVNA");
  
    	String inputString13 = "25 05 2021";
		String inputString23 = "28 05 2021";
		try {
			Date date1 = myFormat.parse(inputString13);
		    Date date2 = myFormat.parse(inputString23);
		    t3.setUsingStart(date1);
		    t3.setUsingEnd(date2);
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		t3.setBill(0);
		t3.setTicketUsers(ticketUsers);
		
		userTickets.add(t);
		userTickets.add(t2);
		userTickets.add(t3);
		
		regUser.setTickets(userTickets);
		
		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.getAgenda().getAgendaGroup("regular_guest").setFocus();
		kieSession.insert(regUser);
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		assertEquals("REGULAR_GUEST",  new ArrayList<String>(t.getPrivilege()).get(0));
		
		kieSession.dispose();
	}
	
	@Test
    public void addingDay() {
		
		SkiResortDTO resort = new SkiResortDTO(1L, "Kopaonik");
		resort.setOccupacyRate(55);
		// jedna kaarta
		TicketsDTO t = new TicketsDTO();
		t.setSkiResort(resort);
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
		privilege.add("REGULAR_GUEST");
		t.setPrivilege(privilege);
		
		
		KieSession kieSession = kieContainer.newKieSession("test-session");
		kieSession.getAgenda().getAgendaGroup("bonus").setFocus();
		kieSession.insert(t);
		kieSession.fireAllRules();
		
		try {
			assertEquals(myFormat.parse("29 05 2021"),  t.getUsingEnd());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		kieSession.dispose();
	}
	

}
