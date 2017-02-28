package hello.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller 
public class HelloController {
	@Autowired
	private Twitter twitter;
	@RequestMapping("/hello")
	public String hello(@RequestParam(defaultValue = "SpringBootMVC4ApplicationTest") String search, Model model){
		SearchResults searchResults = twitter.searchOperations().search(search);
		List<String> tweets = searchResults.getTweets().stream().map(Tweet::getText).collect(Collectors.toList());
		model.addAttribute("tweets", tweets);
		
		return "resultPage1";

		/*SearchResults searchResult = twitter.searchOperations().search(search);
		String text;
		if(searchResult.getTweets().size() > 0){
			text = searchResult.getTweets().get(0).getText();
		}else{
			text = search;
		}
		model.addAttribute("message", text);
		return "resultPage";*/	
	}
}
