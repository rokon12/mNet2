package org.jugbd.mnet.web.controller;

import org.jugbd.mnet.domain.Register;
import org.jugbd.mnet.domain.Vital;
import org.jugbd.mnet.service.RegisterService;
import org.jugbd.mnet.service.VitalService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Bazlur Rahman Rokon
 * @since 10/17/14.
 */
@Controller
@Secured({"ROLE_ADMIN", "ROLE_USER"})
@RequestMapping(value = "vital")
public class VitalController {
	private static final Logger log = LoggerFactory.getLogger(VitalController.class);

	public static final String VITAL_CREATE_PAGE = "vital/create";
	public static final String REDIRECT_VITAL_SHOW_PAGE = "redirect:/vital/show/";
	public static final String VITAL_SHOW_PAGE = "vital/show";
	public static final String VITAL_INDEX_PAGE = "vital/index";
	public static final String REDIRECT_VITAL_INDEX_PAGE = "redirect:/vital/index/";

	@Autowired
	private VitalService vitalService;

	@Autowired
	private RegisterService registerService;

	@RequestMapping(value = "/create/{registerId}", method = RequestMethod.GET)
	public String create(@PathVariable Long registerId,
	                     Vital vital,
	                     Model uiModel) {

		Register register = registerService.findOne(registerId);

		uiModel.addAttribute("registerId", registerId);
		uiModel.addAttribute("registrationType", register.getRegistrationType());

		return VITAL_CREATE_PAGE;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String save(@Valid Vital vital,
	                   BindingResult result,
	                   Long registerId,
	                   Model uiModel,
	                   RedirectAttributes redirectAttributes) {

		if (registerId == null) {
			throw new RuntimeException("Unable to find Register Id");
		}

		if (result.hasErrors()) {
			Register register = registerService.findOne(registerId);
			uiModel.addAttribute("registerId", registerId);
			uiModel.addAttribute("registrationType", register.getRegistrationType());

			return VITAL_CREATE_PAGE;
		}

		if (isEmpty(vital)) {
			redirectAttributes.addFlashAttribute("message", "Sorry! Could not save empty vital");

			return "redirect:/vital/create/" + registerId;
		}

		Vital savedVital = vitalService.saveByRegisterId(vital, registerId);

		return REDIRECT_VITAL_SHOW_PAGE + savedVital.getId();
	}

	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id,
	                   Model uiModel) {

		Vital vital = vitalService.findOne(id);
		uiModel.addAttribute("vital", vital);
		Register register = vital.getRegister();
		uiModel.addAttribute("registrationType", register);
		uiModel.addAttribute("register", register);

		return VITAL_SHOW_PAGE;
	}

	@RequestMapping(value = "/index/{registerId}", method = RequestMethod.GET)
	public String index(@PathVariable Long registerId,
	                    Model uiModel) {
		Register register = registerService.findOne(registerId);
		List<Vital> vitals = vitalService.findByRegisterId(registerId);
		uiModel.addAttribute("vitals", vitals);
		uiModel.addAttribute("registerId", registerId);
		uiModel.addAttribute("registrationType", register.getRegistrationType());

		return VITAL_INDEX_PAGE;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public String delete(@PathVariable Long id) {
		Long registrationId = vitalService.delete(id);

		return REDIRECT_VITAL_INDEX_PAGE + registrationId;
	}

	@RequestMapping(value = "back", method = RequestMethod.GET)
	public String backToRegistrationPage(@RequestParam Long registerId) {
		Register register = registerService.findOne(registerId);

		return "redirect:/register/vitals/" + register.getId();
	}

	public boolean isEmpty(Vital vital) {

		return vital.getHeight() == null
			&& vital.getWeight() == null
			&& vital.getBmi() == null
			&& vital.getTemperature() == null
			&& vital.getPulse() == null
			&& vital.getRespiratoryRate() == null
			&& vital.getSystolic() == null
			&& vital.getDiastolic() == null;
	}
}
