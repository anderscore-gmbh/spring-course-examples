// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package net.gfu.seminar.spring.web;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import net.gfu.seminar.spring.domain.Guest;
import net.gfu.seminar.spring.web.GuestController;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect GuestController_Roo_Controller {
    
    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String GuestController.create(@Valid Guest guest, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, guest);
            return "guests/create";
        }
        uiModel.asMap().clear();
        guest.persist();
        return "redirect:/guests/" + encodeUrlPathSegment(guest.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(params = "form", produces = "text/html")
    public String GuestController.createForm(Model uiModel) {
        populateEditForm(uiModel, new Guest());
        return "guests/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String GuestController.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("guest", Guest.findGuest(id));
        uiModel.addAttribute("itemId", id);
        return "guests/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String GuestController.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("guests", Guest.findGuestEntries(firstResult, sizeNo));
            float nrOfPages = (float) Guest.countGuests() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("guests", Guest.findAllGuests());
        }
        return "guests/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String GuestController.update(@Valid Guest guest, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, guest);
            return "guests/update";
        }
        uiModel.asMap().clear();
        guest.merge();
        return "redirect:/guests/" + encodeUrlPathSegment(guest.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String GuestController.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Guest.findGuest(id));
        return "guests/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String GuestController.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Guest guest = Guest.findGuest(id);
        guest.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/guests";
    }
    
    void GuestController.populateEditForm(Model uiModel, Guest guest) {
        uiModel.addAttribute("guest", guest);
    }
    
    String GuestController.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}