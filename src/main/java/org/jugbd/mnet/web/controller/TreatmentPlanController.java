package org.jugbd.mnet.web.controller;

import org.jugbd.mnet.domain.Register;
import org.jugbd.mnet.domain.TreatmentPlan;
import org.jugbd.mnet.service.RegisterService;
import org.jugbd.mnet.service.TreatmentPlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * @author Bazlur Rahman Rokon
 * @date 12/25/14.
 */
@Controller
@Secured({"ROLE_ADMIN", "ROLE_USER"})
@RequestMapping(value = "treatmentplan")
public class TreatmentPlanController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TreatmentPlanController.class);
    public static final String REDIRECT_TO_TREATMENT_PLAN_REGISTER = "redirect:/register/treatmentplan/";
    public static final String TREATMENT_PLAN_CREATE_PAGE = "treatmentplan/create";
    public static final String TREATMENT_PLAN_EDIT_PAGE = "treatmentplan/edit";

    @Autowired
    private RegisterService registerService;

    @Autowired
    private TreatmentPlanService treatmentPlanService;

    @RequestMapping(value = "create/{registerId}", method = RequestMethod.GET)
    public String create(@PathVariable Long registerId,
                         TreatmentPlan treatmentPlan,
                         Model uiModel) {

        Register register = registerService.findOne(registerId);
        treatmentPlan.setRegister(register);

        return TREATMENT_PLAN_CREATE_PAGE;
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String save(@Valid TreatmentPlan treatmentPlan,
                       BindingResult result,
                       RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {

            return TREATMENT_PLAN_CREATE_PAGE;
        }

        TreatmentPlan treatmentPlanSaved = treatmentPlanService.save(treatmentPlan);
        redirectAttributes.addFlashAttribute("message", "Diagnosis successfully created!");

        return getRedirectUrl(treatmentPlanSaved);
    }

    @RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id,
                       Model uiModel) {

        TreatmentPlan treatmentPlan = treatmentPlanService.findOne(id);
        uiModel.addAttribute("treatmentPlan", treatmentPlan);

        return TREATMENT_PLAN_EDIT_PAGE;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String update(@Valid TreatmentPlan treatmentPlan,
                         BindingResult result,
                         RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {

            return TREATMENT_PLAN_EDIT_PAGE;
        }

        TreatmentPlan treatmentPlanSaved = treatmentPlanService.save(treatmentPlan);
        redirectAttributes.addFlashAttribute("message", "Treatment Plan successfully updated!");

        return getRedirectUrl(treatmentPlanSaved);
    }

    @RequestMapping(value = "back/{registerId}", method = RequestMethod.GET)
    public String back(@PathVariable Long registerId) {

        return REDIRECT_TO_TREATMENT_PLAN_REGISTER + registerId ;
    }

    private String getRedirectUrl(TreatmentPlan treatmentPlan) {

        return REDIRECT_TO_TREATMENT_PLAN_REGISTER + treatmentPlan.getRegister().getId() ;
    }
}
