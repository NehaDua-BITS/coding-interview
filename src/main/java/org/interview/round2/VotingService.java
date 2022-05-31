package org.interview.round2;

import java.util.*;

public class VotingService {

    private int numOfVotes;

    private Map<String, Map<Integer, Integer>> teamRankCountMap;

    public VotingService(int numOfVotes) {
        this.numOfVotes = numOfVotes;
        this.teamRankCountMap = new HashMap<>();
    }

    public List<String> getRanking(List<List<String>> votes) {
        List<String> rankingList = new ArrayList<>();

        if (votes == null || votes.size() == 0) {
            return rankingList;
        }

        prepareRankCountMap(votes);

        PriorityQueue<String> priorityQueue = new PriorityQueue<>((team1, team2) -> {
            int score1 = 0;
            int score2 = 0;
            for (int rank = 0; rank < numOfVotes; rank++) {
                score1 += ((numOfVotes - rank) * teamRankCountMap.get(team1).getOrDefault(rank+1, 0));
                score2 += ((numOfVotes - rank) * teamRankCountMap.get(team2).getOrDefault(rank+1, 0));
            }

            int diff = score2 - score1;
            if (diff != 0) { //no tie
                return diff;
            }

            //comparing using rank 1 count
            int count1 = teamRankCountMap.get(team1).getOrDefault(1, 0);
            int count2 = teamRankCountMap.get(team2).getOrDefault(1, 0);
            return Integer.compare(count2, count1);
        });

        //add all teams to priority queue
        priorityQueue.addAll(teamRankCountMap.keySet());

        int size = priorityQueue.size();
        for (int i = 0; i < size; i++) {
            rankingList.add(priorityQueue.poll());
        }

        return rankingList;
    }

    private void prepareRankCountMap(List<List<String>> votes) {

        int numOfVoters = votes.size();

        for (int i = 0; i < numOfVoters; i++) {
            List<String> teamsList = votes.get(i);
            for (int rank = 0; rank < numOfVotes; rank++) { //rank 1 to 3
                String teamName = teamsList.get(rank);
                Map<Integer, Integer> rankCountMap = teamRankCountMap.getOrDefault(teamName, new HashMap<>());
                teamRankCountMap.put(teamName, rankCountMap);
                Integer count = rankCountMap.getOrDefault(rank+1, 0);
                rankCountMap.put(rank+1, count+1);
            }
        }
    }
}
