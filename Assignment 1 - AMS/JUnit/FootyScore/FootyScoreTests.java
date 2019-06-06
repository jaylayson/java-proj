package junit.FootyScore;

import static org.junit.Assert.*;

import org.junit.Test;

public class FootyScoreTests {

	@Test
	public void testGoals() {
		FootyScore tg = new FootyScore();

		tg.kickGoal();
		tg.kickGoal();

		assertEquals(12, tg.getPoints());
	}

	@Test
	public void testSayScore() {
		FootyScore tss = new FootyScore();

		tss.kickGoal();
		tss.kickGoal();
		tss.kickGoal();
		tss.kickBehind();

		assertEquals("3, 1, 19", tss.sayScore());

	}

	@Test
	public void testInfrontof() {
		FootyScore broncos = new FootyScore();
		FootyScore storm = new FootyScore();

		broncos.kickGoal();
		broncos.kickBehind();
		storm.kickGoal();

		assertTrue(broncos.inFrontOf(storm));
	}

	@Test
	public void testInfrontofOther() {
		FootyScore brazil = new FootyScore();
		FootyScore argentina = new FootyScore();

		assertFalse(argentina.inFrontOf(brazil));


	}

	@Test
	public void testInitialScore() {
		FootyScore Gryffindor = new FootyScore();
		assertEquals(0, Gryffindor.getPoints());

	}


	@Test
	public void testInitialScore1() {
		FootyScore Slytherin = new FootyScore();
		assertEquals(0, Slytherin.getPoints());

	}

}
