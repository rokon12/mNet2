package org.jugbd.mnet.web.controller;

import org.jugbd.mnet.domain.Register;
import org.jugbd.mnet.domain.Visit;
import org.jugbd.mnet.service.RegisterService;
import org.jugbd.mnet.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * @author Bazlur Rahman Rokon
 * @date 10/6/15.
 */
@Controller
@RequestMapping("visit")
public class VisitController {

    public static final String VISIT_CREATE_PAGE = "visit/create";
    public static final String REDIRECT_REGISTER_VISITS_PAGE = "redirect:/register/visits/";

    @Autowired
    private RegisterService registerService;

    @Autowired
    private VisitService visitService;

    @RequestMapping(value = "new/{registerId}", method = RequestMethod.GET)
    private String createVisitNote(Visit visit,
                                   @PathVariable Long registerId,
                                   Model uiModel) {
        uiModel.addAttribute("registerId", registerId);
        Register register = registerService.findOne(registerId);

        visit.setRegister(register);
        uiModel.addAttribute("registrationType", register.getRegistrationType());

        return VISIT_CREATE_PAGE;
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    private String saveVisitNote(@Valid Visit visit,
                                 BindingResult result,
                                 Long registerId) {
        if (result.hasErrors()) {

            return VISIT_CREATE_PAGE;
        }

        visitService.save(visit, registerId);

        return REDIRECT_REGISTER_VISITS_PAGE + registerId ;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable Long id) {
        Long registrationId = visitService.delete(id);

        return REDIRECT_REGISTER_VISITS_PAGE + registrationId;
    }
}
