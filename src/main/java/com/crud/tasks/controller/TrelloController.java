package com.crud.tasks.controller;

import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@RequiredArgsConstructor
public class TrelloController {

    private final TrelloClient trelloClient;

    @GetMapping("getTrelloBoards")
    public void getTrelloBoards() {
        List<TrelloBoardDto> trelloBoards = trelloClient.getTrelloBoards();
        trelloBoards.forEach(trelloBoardDto -> {
            System.out.println(trelloBoardDto.getId() + " - " + trelloBoardDto.getName());
            System.out.println("This board contains lists: ");
            trelloBoardDto.getLists().forEach(trelloList -> System.out.println(trelloList.getName() + " - " +
                    trelloList.getId() + " - " + trelloList.isClosed()));
        });
    }

    @PostMapping("createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloClient.createNewCard(trelloCardDto);
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


