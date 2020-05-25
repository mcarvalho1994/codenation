package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/quote")
public class QuoteController {

	@Autowired
	private QuoteService quoteService;

	@GetMapping
	public Quote getQuote() {
		return quoteService.getQuote();
	}

	@GetMapping("/{actor}")
	public Quote getQuoteByActor(@PathVariable("actor") String actor) {
		return quoteService.getQuoteByActor(actor);
	}

}
