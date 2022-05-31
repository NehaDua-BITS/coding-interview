package round2;

import org.interview.round2.VotingService;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class VotingServiceTest {

    @Test
    public void testRankingForSameVotes() {
        List<List<String>> votesList = new ArrayList<>();

        List<String> vote1 = new ArrayList<>(3);
        vote1.add("team1");
        vote1.add("team2");
        vote1.add("team3");

        List<String> vote2 = new ArrayList<>(3);
        vote2.add("team1");
        vote2.add("team2");
        vote2.add("team3");

        List<String> vote3 = new ArrayList<>(3);
        vote3.add("team1");
        vote3.add("team2");
        vote3.add("team3");

        votesList.add(vote1);
        votesList.add(vote2);
        votesList.add(vote3);

        VotingService votingService = new VotingService(3);
        List<String> winners = votingService.getRanking(votesList);

        Assert.assertEquals(3, winners.size());
        Assert.assertEquals("team1", winners.get(0));
        Assert.assertEquals("team2", winners.get(1));
        Assert.assertEquals("team3", winners.get(2));
    }

    @Test
    public void testRankingForDifferentVotes() {

        List<List<String>> votesList = new ArrayList<>();

        List<String> vote1 = new ArrayList<>(3);
        vote1.add("team1");
        vote1.add("team2");
        vote1.add("team3");

        List<String> vote2 = new ArrayList<>(3);
        vote2.add("team2");
        vote2.add("team1");
        vote2.add("team3");

        List<String> vote3 = new ArrayList<>(3);
        vote3.add("team1");
        vote3.add("team2");
        vote3.add("team3");

        votesList.add(vote1);
        votesList.add(vote2);
        votesList.add(vote3);

        VotingService votingService = new VotingService(3);
        List<String> winners = votingService.getRanking(votesList);

        Assert.assertEquals(3, winners.size());
        Assert.assertEquals("team1", winners.get(0));
        Assert.assertEquals("team2", winners.get(1));
        Assert.assertEquals("team3", winners.get(2));
    }

    @Test
    public void testRankingForTie() {

        List<List<String>> votesList = new ArrayList<>();

        List<String> vote1 = new ArrayList<>(3);
        vote1.add("A");
        vote1.add("B");
        vote1.add("C");

        List<String> vote2 = new ArrayList<>(3);
        vote2.add("A");
        vote2.add("C");
        vote2.add("D");

        List<String> vote3 = new ArrayList<>(3);
        vote3.add("D");
        vote3.add("E");
        vote3.add("B");

        List<String> vote4 = new ArrayList<>(3);
        vote4.add("F");
        vote4.add("D");
        vote4.add("G");

        votesList.add(vote1);
        votesList.add(vote2);
        votesList.add(vote3);
        votesList.add(vote4);

        VotingService votingService = new VotingService(3);
        List<String> winners = votingService.getRanking(votesList);

        Assert.assertEquals(7, winners.size());
        Assert.assertEquals("A", winners.get(0));
        Assert.assertEquals("D", winners.get(1));
        Assert.assertEquals("F", winners.get(2));

        Assert.assertEquals("B", winners.get(3));
        Assert.assertEquals("C", winners.get(4));
        Assert.assertEquals("E", winners.get(5));
        Assert.assertEquals("G", winners.get(6));

    }


    @Test
    public void testForDifferentVotes() {
        List<List<String>> votesList = new ArrayList<>();

        List<String> vote1 = new ArrayList<>(4);
        vote1.add("team1");
        vote1.add("team2");
        vote1.add("team3");
        vote1.add("team4");

        List<String> vote2 = new ArrayList<>(4);
        vote2.add("team1");
        vote2.add("team2");
        vote2.add("team3");
        vote2.add("team4");

        List<String> vote3 = new ArrayList<>(4);
        vote3.add("team1");
        vote3.add("team2");
        vote3.add("team3");
        vote3.add("team4");

        votesList.add(vote1);
        votesList.add(vote2);
        votesList.add(vote3);

        VotingService votingService = new VotingService(4);
        List<String> winners = votingService.getRanking(votesList);

        Assert.assertEquals(4, winners.size());
        Assert.assertEquals("team1", winners.get(0));
        Assert.assertEquals("team2", winners.get(1));
        Assert.assertEquals("team3", winners.get(2));
        Assert.assertEquals("team4", winners.get(3));
    }
}
