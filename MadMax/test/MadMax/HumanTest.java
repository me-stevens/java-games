/*
 * Tests for the Human class
 */
package MadMax;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class HumanTest {
	
	public HumanTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of setName method, of class Human. ---------------------------------
	 */
	@Test (expected = BadInputException.class)
	public void testSetNameThrowsException() throws Exception {
		System.out.println("setName - ThrowsException");
		String name    = "";
		Human instance = new HumanImpl();
		instance.setName(name);
	}

	@Test
	public void testSetName() throws Exception {
		System.out.println("setName");
		String name    = "Jane";
		Human instance = new HumanImpl();
		instance.setName(name);
		assertEquals(name, instance.getName());
	}

	/**
	 * Test of setLife method, of class Human. ---------------------------------
	 */
	@Test (expected = BadInputException.class)
	public void testSetLifeThrowsException() throws Exception {
		System.out.println("setLife - ThrowsException");
		Human instance = new HumanImpl();
		double life    = instance.getLifeMax() + 1;
		instance.setLife(life);
	}

	@Test
	public void testSetLife() throws Exception {
		System.out.println("setLife");
		double life    = 1.0;
		Human instance = new HumanImpl();
		instance.setLife(life);
		assertEquals(life, instance.getLife(), 0);
	}
	
	@Test
	public void testSetLifeNegative() throws Exception {
		System.out.println("setLife - Negative");
		Human instance  = new HumanImpl();
		double expected =  0.0;
		double life     = -1.0;
		instance.setLife(life);
		assertEquals(expected, instance.getLife(), 0);
	}

	/**
	 * Test of setExperience method, of class Human. ---------------------------
	 */
	@Test (expected = BadInputException.class)
	public void testSetExperienceThrowsException() throws Exception {
		System.out.println("setExperience - ThrowsException");
		double experience = -1.0;
		Human instance    = new HumanImpl();
		instance.setExperience(experience);
	}

	@Test
	public void testSetExperience() throws Exception {
		System.out.println("setExperience");
		double experience = 0.0;
		Human instance    = new HumanImpl();
		instance.setExperience(experience);
		assertEquals(experience, instance.getExperience(), 0);
	}

	/**
	 * Test of setLifeMax method, of class Human. ------------------------------
	 */
	@Test (expected = BadInputException.class)
	public void testSetLifeMaxThrowsException() throws Exception {
		System.out.println("setLifeMax - ThrowsException");
		double lifeMax = 0.0;
		Human instance = new HumanImpl();
		instance.setLifeMax(lifeMax);
	}

	@Test
	public void testSetLifeMax() throws Exception {
		System.out.println("setLifeMax");
		double lifeMax = 1.0;
		Human instance = new HumanImpl();
		instance.setLifeMax(lifeMax);
		assertEquals(lifeMax, instance.getLifeMax(), 0);
	}

	@Test
	public void testSetLifeMaxLowerThanLife() throws Exception {
		System.out.println("setLifeMax - LowerThanLife");
		double life    = 5.0;
		double lifeMax = life - 1.0;
		Human instance = new HumanImpl();
		instance.setLife(life);
		instance.setLifeMax(lifeMax);
		assertEquals(instance.getLifeMax(), instance.getLife(), 0);
	}

	/**
	 * Test of setID method, of class Human. -----------------------------------
	 */
	@Test (expected = BadInputException.class)
	public void testSetIDThrowsException() throws Exception {
		System.out.println("setID - ThrowsException");
		int ID = -1;
		Human instance = new HumanImpl();
		instance.setID(ID);
	}

	@Test
	public void testSetID() throws Exception {
		System.out.println("setID");
		int ID = 0;
		Human instance = new HumanImpl();
		instance.setID(ID);
		assertEquals(ID, instance.getID(), 0);
	}

	/**
	 * Test of getName method, of class Human. ---------------------------------
	 */
	@Test
	public void testGetName() {
		System.out.println("getName");
		Human instance   = new HumanImpl();
		String expResult = "";
		String result    = instance.getName();
		assertEquals(expResult, result);
	}

	/**
	 * Test of getLife method, of class Human. ---------------------------------
	 */
	@Test
	public void testGetLife() {
		System.out.println("getLife");
		Human instance   = new HumanImpl();
		double expResult = 10.0;
		double result    = instance.getLife();
		assertEquals(expResult, result, 0);
	}

	/**
	 * Test of getExperience method, of class Human. ---------------------------
	 */
	@Test
	public void testGetExperience() {
		System.out.println("getExperience");
		Human instance   = new HumanImpl();
		double expResult = 0.0;
		double result    = instance.getExperience();
		assertEquals(expResult, result, 0.0);
	}

	/**
	 * Test of getLifeMax method, of class Human.
	 */
	@Test
	public void testGetLifeMax() {
		System.out.println("getLifeMax");
		Human instance   = new HumanImpl();
		double expResult = 10.0;
		double result    = instance.getLifeMax();
		assertEquals(expResult, result, 0.0);
	}

	/**
	 * Test of getID method, of class Human. -----------------------------------
	 */
	@Test
	public void testGetID() {
		System.out.println("getID");
		Human instance = new HumanImpl();
		int expResult  = 0;
		int result     = instance.getID();
		assertEquals(expResult, result);
	}

	/**
	 * Test of doHarm method, of class Human. ----------------------------------
	 */
	@Test
	public void testDoHarm() {
		System.out.println("doHarm");
		Human instance   = new HumanImpl();
		double expResult = (2 * instance.getLife() + 3 * instance.getExperience()) / 5.0;
		double result    = instance.doHarm();
		assertEquals(expResult, result, 0.0);
	}

	/**
	 * Test of receiveHarm method, of class Human. -----------------------------
	 */
	@Test
	public void testReceiveHarm() {
		System.out.println("receiveHarm");
		double harm    = 0.0;
		Human instance = new HumanImpl();
		instance.receiveHarm(harm);
	}

	/**
	 * Test of die method, of class Human. -------------------------------------
	 */
	@Test
	public void testDie() {
		System.out.println("die");
		Human instance   = new HumanImpl();
		instance.die();
		double expResult = 0.0;
		double result    = instance.getLife();
		assertEquals(expResult, result, 0.0);
	}

	/**
	 * Test of toString method, of class Human. --------------------------------
	 */
	@Test
	public void testToString() {
		System.out.println("toString");
		Human instance   = new HumanImpl();
		String expResult = "Name = " + instance.getName() + "\nlife = " + instance.getLife() + "\nlife maximum = " + instance.getLifeMax() + "\nexperience = " + instance.getExperience() + "\n";
		String result    = instance.toString();
		assertEquals(expResult, result);
	}

	public class HumanImpl extends Human {

		@Override
		public void levelUp() {
		}
	}
}