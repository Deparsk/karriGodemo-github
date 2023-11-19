package com.paschal.karriGodemo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication

public class KarriGoDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(KarriGoDemoApplication.class, args);
	}

}


@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
class CardController {
	private final CardService cardService;

@PostMapping
	CardResponse issueNewCard(@RequestBody IssueCardRequest request) {
	Card card = new Card(request.card(), request.description());
	Card savedCard = this.cardService.issueNewCard(card);
	return new CardResponse(savedCard.getId(), savedCard.getCard(), savedCard.getDescription());
	}
}

record IssueCardRequest (String card, String description) {}
record CardResponse (Long id, String card, String description) {}
@Service
@RequiredArgsConstructor
class CardService {
	private final CardRepository cardRepository;
	Card issueNewCard(Card card) {
		return this.cardRepository.save(card);
	}
}

interface CardRepository extends JpaRepository<Card, Long> { }
@Entity
@Getter
@NoArgsConstructor
class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String card;
	private String description;

	Card(String card, String description) {
		this.card = card;
		this.description = description;
	}
}
//echo "# karriGo-demo-github" >> README.md
//		git init
//		git add README.md
//		git commit -m "first commit"
//		git branch -M main
//		git remote add origin https://github.com/Deparsk/karriGo-demo-github.git
//		git push -u origin main