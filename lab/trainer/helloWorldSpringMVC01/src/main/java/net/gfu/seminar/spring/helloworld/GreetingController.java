package net.gfu.seminar.spring.helloworld;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/greeting")
@Scope("request")
public class GreetingController {
	
	@Autowired @Qualifier("greetingService")
	private GreetingService service;
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String setupForm(Model model) {
		System.out.println("Form created");
		AddGuestForm guestForm = new AddGuestForm();
		// you can add defaults here
		model.addAttribute(guestForm);
		return "/guest/add";
	}
	
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String welcome(Model model) {
		System.out.println("Form created");
		model.addAttribute("welcome", service.welcome());
		return "/guest/welcome";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView processForm(@ModelAttribute @Valid AddGuestForm addGuestForm,
			BindingResult result) {
		System.out.println("Form processed" + addGuestForm);
		ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
			mav.setViewName("/guest/add");
		} else {
			service.addGuest(new GuestImpl(addGuestForm.getFirstname(), addGuestForm.getLastname()));
			mav.addObject("welcome", service.welcome());
			mav.setViewName("/guest/welcome");
		}
		return mav;
	}
	
	@RequestMapping(value = "/to/{name}", method = RequestMethod.GET)
	public @ResponseBody String getTextMessage(@PathVariable String name) {
		return "Hello, " + name + "!";
	}

	@RequestMapping(value="/find/{guestId}", method = RequestMethod.GET, produces= {"application/xml", "text/xml"})
	public @ResponseBody Guest findKundeById(@PathVariable String guestId) {
		System.out.println("findbyId " + guestId);
		
		Long id = Long.parseLong(guestId);
		Guest guest = service.findById(id);
		System.out.println(guest);
		
		return guest;
	}
}
