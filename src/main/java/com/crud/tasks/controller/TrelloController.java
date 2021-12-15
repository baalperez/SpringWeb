package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {

    private final TrelloService trelloService;
    private final TrelloClient trelloClient;

    @GetMapping("getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloService.fetchTrelloBoards();
    }

    @PostMapping("createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloService.createTrelloCard(trelloCardDto);
    }

    @GetMapping("getBadges")
    public void getBadges() {
        trelloClient.getTrelloBadges();
        List<CreatedTrelloCard> trelloCards = trelloClient.getTrelloBadges();
        trelloCards.forEach(card -> {
            System.out.println("id: " + card.getId());
            System.out.println("badges:");
            System.out.println("  votes: " + card.getBadges().getVotes());
            System.out.println("  attachmentsByType:");
            System.out.println("    trello:");
            System.out.println("      card: " + card.getBadges().getAttachments().getTrello().getCard());
            System.out.println("      board: " + card.getBadges().getAttachments().getTrello().getBoard());
        });
    }
}



