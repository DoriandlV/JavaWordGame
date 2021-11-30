package com.codesse.codetest.wordgame;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Additional tests to validate the game specification
 */
public class AdditionalTests {


	static ValidWords validWords;
	WordGame service;

	@BeforeClass
	public static void oneTimeSetUp() {
		validWords = new ValidWordsImpl();
	}

	@Before
	public void setUp() throws Exception {

		service = new WordGameImpl("areallylongword", validWords);

	}


	/**
	 * This is the test supplied. But some candidates change it!!
	 */
	@Test //ok
	public void checkScoresFromValidWords() {

		assertEquals(3, service.submitWord("player1", "all"));
		assertEquals(4, service.submitWord("player2", "word"));
		assertEquals(0, service.submitWord("player3", "tale"));
		assertEquals(0, service.submitWord("player4", "glly"));
		assertEquals(6, service.submitWord("player5", "woolly"));
		assertEquals(0, service.submitWord("player6", "adder"));

	}

	@Test
	public void checkPlayerNamesInLeaderboard() {

		service.submitWord("player1", "all");
		service.submitWord("player2", "word");
		service.submitWord("player3", "tale");
		service.submitWord("player4", "glly");
		service.submitWord("player5", "woolly");

		assertEquals("player5", service.getPlayerNameAtPosition(0));
		assertEquals("player2", service.getPlayerNameAtPosition(1));
		assertEquals("player1", service.getPlayerNameAtPosition(2));

	}

	@Test
	public void checkWordEntriesInLeaderboard() {

		service.submitWord("player1", "all");
		service.submitWord("player2", "word");
		service.submitWord("player3", "tale");
		service.submitWord("player4", "glly");
		service.submitWord("player5", "woolly");

		assertEquals("woolly", service.getWordEntryAtPosition(0));
		assertEquals("word", service.getWordEntryAtPosition(1));
		assertEquals("all", service.getWordEntryAtPosition(2));

	}

	@Test
	public void checkNullScoreWhenLeaderboardPositionIsEmpty() {

		service.submitWord("player1", "all");

		assertNotNull(service.getScoreAtPosition(0));

		assertNull(service.getScoreAtPosition(1));
		assertNull(service.getScoreAtPosition(2));
		assertNull(service.getScoreAtPosition(3));
	}

	@Test
	public void checkNullPlayerWhenLeaderboardPositionIsEmpty() {

		service.submitWord("player1", "all");

		assertNotNull(service.getPlayerNameAtPosition(0));

		assertNull(service.getPlayerNameAtPosition(1));
		assertNull(service.getPlayerNameAtPosition(2));
		assertNull(service.getPlayerNameAtPosition(3));
	}

	@Test
	public void checkNullWordEntryWhenLeaderboardPositionIsEmpty() {

		service.submitWord("player1", "all");

		assertNotNull(service.getWordEntryAtPosition(0));

		assertNull(service.getWordEntryAtPosition(1));
		assertNull(service.getWordEntryAtPosition(2));
		assertNull(service.getWordEntryAtPosition(3));
	}


	@Test
	public void validateLeaderboardOrdering() {

		service.submitWord("player1", "all");
		service.submitWord("player2", "word");
		service.submitWord("player3", "tale");
		service.submitWord("player4", "glly");
		service.submitWord("player5", "woolly");

		assertEquals(new Integer(6), service.getScoreAtPosition(0));
		assertEquals("player5", service.getPlayerNameAtPosition(0));

		assertEquals(new Integer(4), service.getScoreAtPosition(1));
		assertEquals("player2", service.getPlayerNameAtPosition(1));

		assertEquals(new Integer(3), service.getScoreAtPosition(2));
		assertEquals("player1", service.getPlayerNameAtPosition(2));

	}

	@Test
	public void wordsWithSameScore_shouldBeAddedToLeaderboardInSubmissionOrder() {

		assertEquals(4, service.submitWord("player1", "long"));
		assertEquals(4, service.submitWord("player2", "word"));
		assertEquals(6, service.submitWord("player4", "woolly"));
		assertEquals(4, service.submitWord("player3", "real"));

		assertEquals(new Integer(6), service.getScoreAtPosition(0));
		assertEquals(new Integer(4), service.getScoreAtPosition(1));
		assertEquals(new Integer(4), service.getScoreAtPosition(2));
		assertEquals(new Integer(4), service.getScoreAtPosition(3));

		assertEquals("woolly", service.getWordEntryAtPosition(0));
		assertEquals("long", service.getWordEntryAtPosition(1));
		assertEquals("word", service.getWordEntryAtPosition(2));
		assertEquals("real", service.getWordEntryAtPosition(3));

		assertEquals("player4", service.getPlayerNameAtPosition(0));
		assertEquals("player1", service.getPlayerNameAtPosition(1));
		assertEquals("player2", service.getPlayerNameAtPosition(2));
		assertEquals("player3", service.getPlayerNameAtPosition(3));

	}

	@Test
	public void samePlayerCanAppearMultipleTimesOnTheLeaderboardWithDifferentWords() {

		assertEquals(4, service.submitWord("player1", "long"));
		assertEquals(4, service.submitWord("player1", "word"));
		assertEquals(6, service.submitWord("player1", "woolly"));
		assertEquals(4, service.submitWord("player1", "real"));

		assertEquals(new Integer(6), service.getScoreAtPosition(0));
		assertEquals(new Integer(4), service.getScoreAtPosition(1));
		assertEquals(new Integer(4), service.getScoreAtPosition(2));
		assertEquals(new Integer(4), service.getScoreAtPosition(3));

		assertEquals("player1", service.getPlayerNameAtPosition(0));
		assertEquals("player1", service.getPlayerNameAtPosition(1));
		assertEquals("player1", service.getPlayerNameAtPosition(2));
		assertEquals("player1", service.getPlayerNameAtPosition(3));

	}

	@Test
	public void validateLeaderboard_MultipleWordsWithSameScore() {

		service.submitWord("player1", "word");
		service.submitWord("player2", "long");
		service.submitWord("player3", "real");
		service.submitWord("player4", "wool");


		assertEquals(new Integer(4), service.getScoreAtPosition(0));
		assertEquals("player1", service.getPlayerNameAtPosition(0));
		assertEquals("word", service.getWordEntryAtPosition(0));

		assertEquals(new Integer(4), service.getScoreAtPosition(1));
		assertEquals("player2", service.getPlayerNameAtPosition(1));
		assertEquals("long", service.getWordEntryAtPosition(1));

		assertEquals(new Integer(4), service.getScoreAtPosition(2));
		assertEquals("player3", service.getPlayerNameAtPosition(2));
		assertEquals("real", service.getWordEntryAtPosition(2));

		assertEquals(new Integer(4), service.getScoreAtPosition(3));
		assertEquals("player4", service.getPlayerNameAtPosition(3));
		assertEquals("wool", service.getWordEntryAtPosition(3));
	}

	@Test
	public void duplicateWordSubmissionsAreNotAllowed() {

		assertEquals(4, service.submitWord("player1", "long"));
		assertEquals(0, service.submitWord("player2", "long"));

		assertEquals(new Integer(4), service.getScoreAtPosition(0));
		assertEquals("player1", service.getPlayerNameAtPosition(0));

		assertNull(service.getScoreAtPosition(1));
		assertNull(service.getPlayerNameAtPosition(1));

	}

}

