package org.jugbd.mnet.web.controller;

import org.jugbd.mnet.domain.Diagnosis;
import org.jugbd.mnet.domain.Register;
import org.jugbd.mnet.service.DiagnosisService;
import org.jugbd.mnet.service.RegisterService;
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
 * @author Mushfekur Rahman (mushfek0001)
 */
@Controller
@Secured({"ROLE_ADMIN", "ROLE_USER"})
@RequestMapping("/diagnosis")
public class DiagnosisController {

    private static final Logger log = LoggerFactory.getLogger(DiagnosisController.class);

    public static final String REDIRECT_REGISTER_DIAGNOSIS_PAGE = "redirect:/register/diagnosis/";
    public static final String DIAGNOSIS_CREATE_PAGE = "diagnosis/create";
    public static final String DIAGNOSIS_EDIT_PAGE = "diagnosis/edit";

    @Autowired
    private DiagnosisService diagnosisService;

    @Autowired
    private RegisterService registerService;

    @RequestMapping(value = "/create/{registerId}", method = RequestMethod.GET)
    public String create(@PathVariable Long registerId,
                         Diagnosis diagnosis,
                         Model uiModel) {

        Register register = registerService.findOne(registerId);
        diagnosis.setRegister(register);
        uiModel.addAttribute("registrationType", register.getRegistrationType());

        return DIAGNOSIS_CREATE_PAGE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@Valid Diagnosis diagnosis,
                       BindingResult result,
                       RedirectAttributes redirectAttrs) {

        if (result.hasErrors()) {

            return DIAGNOSIS_CREATE_PAGE;
        }

        Diagnosis diagnosisFromDb = diagnosisService.save(diagnosis);
        redirectAttrs.addFlashAttribute("message", "Diagnosis successfully created!");

        return getRedirectUrl(diagnosisFromDb);
    }

    @RequestMapping(value = "/edit/{diagnosisId}", method = RequestMethod.GET)
    public String editDiagnosis(@PathVariable("diagnosisId") Long diagnosisId,
                                Model uiModel) {

        Diagnosis diagnosis = diagnosisService.findOne(diagnosisId);
        uiModel.addAttribute("diagnosis", diagnosis);
        uiModel.addAttribute("registrationType", diagnosis.getRegister().getRegistrationType());

        return DIAGNOSIS_EDIT_PAGE;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String update(@Valid Diagnosis diagnosis,
                         BindingResult result,
                         Model uiModel,
                         RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            uiModel.addAttribute("registrationType", diagnosis.getRegister().getRegistrationType());

            return DIAGNOSIS_CREATE_PAGE;
        }

        Diagnosis diagnosisFromDb = diagnosisService.save(diagnosis);
        redirectAttributes.addFlashAttribute("message", "Diagnosis successfully updated!");

        return getRedirectUrl(diagnosisFromDb);
    }

    @RequestMapping(value = "/back/{registerId}", method = RequestMethod.GET)
    public String back(@PathVariable Long registerId) {

        return REDIRECT_REGISTER_DIAGNOSIS_PAGE + registerId;
    }

    private String getRedirectUrl(Diagnosis diagnosis) {

        return REDIRECT_REGISTER_DIAGNOSIS_PAGE + diagnosis.getRegister().getId();
    }
}
