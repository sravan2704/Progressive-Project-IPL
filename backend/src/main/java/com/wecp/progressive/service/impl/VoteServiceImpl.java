package com.wecp.progressive.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.progressive.entity.Vote;
import com.wecp.progressive.repository.VoteRepository;
import com.wecp.progressive.service.VoteService;

@Service
public class VoteServiceImpl implements VoteService {

    private VoteRepository voteRepository;

    @Autowired
    public VoteServiceImpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    public List<Vote> getAllVotes()
    {
        return voteRepository.findAll();
    }
    public int createVote(Vote vote)
    {
       Vote voteObj =  voteRepository.save(vote);
        return (voteObj.getVoteId());
    }

    public Map<String,Long> getVotesCountOfAllCategories()
    {
        Map<String,Long> countMap = new HashMap<>();

        countMap.put("Team", voteRepository.countByCategory("Team"));
        countMap.put("Batsman", voteRepository.countByCategory("Batsman"));
        countMap.put("Bowler", voteRepository.countByCategory("Bowler"));
        countMap.put("All-rounder", voteRepository.countByCategory("All-rounder"));
        countMap.put("Wicketkeeper", voteRepository.countByCategory("Wicketkeeper"));

        return countMap;

    }

    

}