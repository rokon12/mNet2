package org.jugbd.mnet.web.controller;

import org.jugbd.mnet.domain.DiagnosisFinal;
import org.jugbd.mnet.domain.Register;
import org.jugbd.mnet.service.DiagnosisService;
import org.jugbd.mnet.service.RegisterService;
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
 * @since 7/19/16.
 */

@Controller
@Secured({"ROLE_ADMIN", "ROLE_USER"})
@RequestMapping("/diagnosis-final")
public class DiagnosisFinalController {

	private static final String DIAGNOSIS_CREATE_PAGE = "diagnosisfinal/create";
	private static final String DIAGNOSIS_EDIT_PAGE = "diagnosisfinal/edit";
	private static final String REDIRECT_REGISTER_DIAGNOSIS_PAGE = "redirect:/register/diagnosis-final/";

	@Autowired
	private DiagnosisService diagnosisService;

	@Autowired
	private RegisterService registerService;

	@RequestMapping(value = "/create/{registerId}", method = RequestMethod.GET)
	public String create(@PathVariable Long registerId,
	                     DiagnosisFinal diagnosisFinal,
	                     Model uiModel) {

		Register register = registerService.findOne(registerId);
		diagnosisFinal.setRegister(register);
		uiModel.addAttribute("registrationType", register.getRegistrationType());

		return DIAGNOSIS_CREATE_PAGE;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String save(@Valid DiagnosisFinal diagnosisFinal,
	                   BindingResult result,
	                   RedirectAttributes redirectAttrs) {

		if (result.hasErrors()) {

			return DIAGNOSIS_CREATE_PAGE;
		}

		DiagnosisFinal diagnosisFromDb = diagnosisService.save(diagnosisFinal);
		redirectAttrs.addFlashAttribute("message", "Final Diagnosis successfully created!");

		return getRedirectUrl(diagnosisFromDb);
	}

	@RequestMapping(value = "/edit/{diagnosisId}", method = RequestMethod.GET)
	public String editDiagnosis(@PathVariable("diagnosisId") Long diagnosisId, Model uiModel) {

		DiagnosisFinal diagnosisFinal = diagnosisService.findOneFinalDiagnosis(diagnosisId);
		uiModel.addAttribute("diagnosisFinal", diagnosisFinal);
		uiModel.addAttribute("registrationType", diagnosisFinal.getRegister().getRegistrationType());

		return DIAGNOSIS_EDIT_PAGE;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String update(@Valid DiagnosisFinal diagnosisFinal,
	                     BindingResult result,
	                     Model uiModel,
	                     RedirectAttributes redirectAttributes) {

		if (result.hasErrors()) {
			uiModel.addAttribute("registrationType", diagnosisFinal.getRegister().getRegistrationType());

			return DIAGNOSIS_CREATE_PAGE;
		}

		DiagnosisFinal diagnosisFromDb = diagnosisService.save(diagnosisFinal);
		redirectAttributes.addFlashAttribute("message", "Diagnosis successfully updated!");

		return getRedirectUrl(diagnosisFromDb);
	}

	@RequestMapping(value = "/back/{registerId}", method = RequestMethod.GET)
	public String back(@PathVariable Long registerId) {

		return REDIRECT_REGISTER_DIAGNOSIS_PAGE + registerId;
	}

	private String getRedirectUrl(DiagnosisFinal diagnosis) {

		return REDIRECT_REGISTER_DIAGNOSIS_PAGE + diagnosis.getRegister().getId();
	}
}
