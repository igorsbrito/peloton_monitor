package br.ufc.pelotonmonitor.services;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PagesService {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getData() {

		ModelAndView model = new ModelAndView("home");
	
		return model;

	}
	
	
	@RequestMapping(value = "/graphics", method = RequestMethod.GET)
	public ModelAndView getGraphics() {

		ModelAndView model = new ModelAndView("graphics");
	
		return model;

	}
	
	/* @RequestMapping(value="/teste/{foo}", method=RequestMethod.GET, produces="application/json") 
	    public @ResponseBody String byParameter(@PathVariable String foo) {
	        //Perform logic with foo 
	    	System.out.println(foo);
	    	
	        return "{nome:teste5}";
	    } 
	*/
}