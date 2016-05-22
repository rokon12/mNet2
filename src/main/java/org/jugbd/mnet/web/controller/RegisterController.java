package org.jugbd.mnet.web.controller;

import org.jugbd.mnet.domain.*;
import org.jugbd.mnet.domain.enums.RegistrationType;
import org.jugbd.mnet.exception.PatientNotFoundException;
import org.jugbd.mnet.service.PatientService;
import org.jugbd.mnet.service.RegisterService;
import org.jugbd.mnet.utils.StringUtils;
import org.jugbd.mnet.web.editor.CaseInsensitivePropertyEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Set;

/**
 * @author Bazlur Rahman Rokon
 * @since 10/14/14.
 */
@Controller
@Secured({"ROLE_ADMIN", "ROLE_USER"})
@RequestMapping("register")
public class RegisterController {
    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);

    private static final String REGISTER_CONVERT_PAGE = "register/convert";
    private static final String REGISTER_CREATE_PAGE = "register/create";
    private static final String REGISTER_OPD_PAGE = "register/opd";
    private static final String REDIRECT_REGISTER_OPD = "redirect:/register/opd/";
    private static final String REGISTER_IPD_PAGE = "register/ipd";
    private static final String REDIRECT_REGISTER_IPD_PAGE = "redirect:/register/ipd/";
    private static final String REGISTER_OPD_REGISTRATION_PAGE = "register/opd-registration";
    private static final String REDIRECT_PATIENT_SHOW_PAGE = "redirect:/patient/show/";
    private static final String REGISTER_OPD_EDIT_PAGE = "register/opd-edit";
    private static final String REGISTER_DIAGNOSIS_PAGE = "register/diagnosis";
    private static final String REGISTER_TREATMENT_PLAN_PAGE = "register/treatmentplan";
    private static final String REGISTER_EXAMINATION_PAGE = "register/examination";
    private static final String REGISTER_CHIEF_COMPLAINTS_PAGE = "register/chiefcomplaints";
    private static final String REGISTER_VITAL_PAGE = "register/vital";
    private static final String REGISTER_VISIT_NOTE_PAGE = "register/visit-note";
    private static final String REGISTER_OUTCOME_PAGE = "register/outcome";
    private static final String REDIRECT_REGISTER_OUTCOME_PAGE = "redirect:/register/outcome/";
    private static final String REGISTER_REMARKS_PAGE = "register/remarks";
    private static final String REDIRECT_REGISTER_REMARKS_PAGE = "redirect:/register/remarks/";
    private static final String REGISTER_MEDICAL_HISTORY_PAGE = "register/medical-history";
    private static final String REGISTER_OPERATIONAL_DETAIL_PAGE = "register/operational-detail";
    private static final String REGISTER_INVESTIGATION_PAGE = "register/investigation";
    private static final String REGISTER_COMPLICATION_MANAGEMENT_PAGE = "register/complicationmanagement";
    private static final String REGISTER_LIFE_STYLE_PAGE = "register/life-style";
    private static final String REGISTER_PICTURE_PAGE = "register/picture";
    private static final String REGISTER_PAGE = "register/show";

    @Autowired
    private PatientService patientService;

    @Autowired
    private RegisterService registerService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true));
        binder.registerCustomEditor(RegistrationType.class, new CaseInsensitivePropertyEditor<>(RegistrationType.class));
    }

    @RequestMapping(value = "show/{registerId}", method = RequestMethod.GET)
    public String showRegister(@PathVariable Long registerId, Model uiModel) {

        Register register = registerService.findRegister(registerId);
        uiModel.addAttribute("register", register);
        Patient patient = register.getPatient();
        uiModel.addAttribute("patient", patient);

        return REGISTER_PAGE;
    }

    @RequestMapping(value = "patient/{patientId}", method = RequestMethod.GET)
    public String create(@PathVariable(value = "patientId") Long patientId, Model uiModel) {

        Patient patient = patientService.findOne(patientId);
        Register register = new Register();
        register.setPatient(patient);
        uiModel.addAttribute("register", register);


        //EMERGENCY
        return REGISTER_CREATE_PAGE;
    }

    @RequestMapping(value = "patient/{patientId}/registration-type/{registrationType}", method = RequestMethod.GET)
    public String registerNewPatient(@PathVariable Long patientId,
                                     @PathVariable RegistrationType registrationType,
                                     Register register) {
        log.info("creating a new registration for patient id: {} with {}", patientId, registrationType.getLabel());

        Optional<Patient> patientOptional = patientService.findPatientById(patientId);
        if (patientOptional.isPresent()) {
            Patient patient = patientOptional.get();
            register.setPatient(patient);
            register.setRegistrationType(registrationType);
        } else {
            throw new PatientNotFoundException(patientId);
        }

        return REGISTER_CREATE_PAGE;
    }


//    @RequestMapping(value = "opd/{patientId}/new", method = RequestMethod.GET)
//    public String createOutPatient(@PathVariable(value = "patientId") Long patientId, Model uiModel) {
//        Patient patient = patientService.findOne(patientId);
//
//        OutdoorRegister outdoorRegister = new OutdoorRegister();
//        outdoorRegister.setPatient(patient);
//        uiModel.addAttribute("outdoorRegister", outdoorRegister);
//
//        return REGISTER_OPD_PAGE;
//    }
//
//    @RequestMapping(value = "opd/save", method = RequestMethod.POST)
//    public String saveOpd(@Valid OutdoorRegister outdoorRegister,
//                          BindingResult result,
//                          RedirectAttributes redirectAttributes) {
//
//        if (result.hasErrors()) {
//
//            return REGISTER_OPD_PAGE;
//        }
//
//        registerService.save(outdoorRegister);
//
//        return REDIRECT_REGISTER_OPD + outdoorRegister.getId();
//    }

//    @RequestMapping(value = "ipd/{patientId}/new", method = RequestMethod.GET)
//    public String createInpatient(@PathVariable(value = "patientId") Long patientId, Model uiModel) {
//        Patient patient = patientService.findOne(patientId);
//        Register register = new Register();
//        register.setPatient(patient);
//        uiModel.addAttribute("register", register);
//
//        return REGISTER_IPD_PAGE;
//    }
//
//    @RequestMapping(value = "ipd/save", method = RequestMethod.POST)
//    public String saveInpatient(@Valid Register register,
//                                BindingResult result,
//                                RedirectAttributes redirectAttributes) {
//
//        if (result.hasErrors()) {
//
//            return REGISTER_IPD_PAGE;
//        }
//
//        registerService.save(register);
//
//        return REDIRECT_REGISTER_IPD_PAGE + register.getId();
//    }

//    @RequestMapping(value = "opd/{id}", method = RequestMethod.GET)
//    public String openOpdRegistration(@PathVariable Long id, Model uiModel) {
//        prepareData(id, RegistrationType.OUTDOOR, uiModel);
//
//        return REGISTER_OPD_REGISTRATION_PAGE;
//    }
//
//    @RequestMapping(value = "ipd/{id}", method = RequestMethod.GET)
//    public String openIpdRegistration(@PathVariable Long id, Model uiModel) {
//        prepareData(id, RegistrationType.INDOOR, uiModel);
//
//        return "register/ipd-registration";
//    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(@Valid Register register,
                       BindingResult result,
                       RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {

            return REGISTER_CREATE_PAGE;
        }

        registerService.save(register);

        return REDIRECT_PATIENT_SHOW_PAGE + register.getPatient().getId();
    }

    @RequestMapping(value = "close/{registerId}", method = RequestMethod.POST)
    public String close(@PathVariable(value = "registerId") Long registerId,
                        @RequestParam RegistrationType registrationType,
                        RedirectAttributes redirectAttributes) {

        registerService.closeRegister(registerId, registrationType);
        redirectAttributes.addFlashAttribute("message", "Registration has been closed!");
        Patient patient = registerService.findRegisterEither(registerId, registrationType)
                .fold(Register::getPatient, OutdoorRegister::getPatient);

        return REDIRECT_PATIENT_SHOW_PAGE + patient.getId();
    }


    @RequestMapping(value = "cancel/{patientId}", method = RequestMethod.GET)
    public String cancel(@PathVariable(value = "patientId") Long patientId) {
        log.debug("cancel()");

        return REDIRECT_PATIENT_SHOW_PAGE + patientId;
    }

    @RequestMapping(value = "/edit/{registrationId}", method = RequestMethod.GET)
    public String editRegistration(@PathVariable Long registrationId,
                                   @RequestParam(value = "type") String type,
                                   Model uiModel) {

        if (StringUtils.isNotEmpty(type) && type.equalsIgnoreCase("opd")) {
            OutdoorRegister outdoorRegister = registerService.findOpdRegister(registrationId);
            uiModel.addAttribute("outdoorRegister", outdoorRegister);

            return REGISTER_OPD_EDIT_PAGE;
        } else {
            Register register = registerService.findOne(registrationId);

            uiModel.addAttribute("register", register);

            return "register/ipd-edit";
        }
    }

    @RequestMapping(value = "opd/update", method = RequestMethod.POST)
    public String updateOpd(@Valid OutdoorRegister outdoorRegister,
                            BindingResult result,
                            RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {

            return REGISTER_OPD_EDIT_PAGE;
        }

        registerService.save(outdoorRegister);

        return REDIRECT_REGISTER_OPD + outdoorRegister.getId();
    }

    @RequestMapping(value = "ipd/update", method = RequestMethod.POST)
    public String updateRegistration(@Valid Register register) {
        Register savedRegister = registerService.save(register);

        return REDIRECT_REGISTER_IPD_PAGE + savedRegister.getId();
    }

    //Diagnosis

    @RequestMapping(value = "/diagnosis/{registerId}", method = RequestMethod.GET)
    public String diagnosis(@PathVariable Long registerId, Diagnosis diagnosis,
                            Model uiModel) {

        Register register = registerService.findOne(registerId);
        diagnosis.setRegister(register);
        prepareData(register, uiModel);

        return REGISTER_DIAGNOSIS_PAGE;
    }

    //Plan of Rx

    @RequestMapping(value = "/treatmentplan/{registerId}", method = RequestMethod.GET)
    public String treatmentPlan(@PathVariable Long registerId,
                                TreatmentPlan treatmentPlan,
                                Model uiModel) {
        Register register = registerService.findOne(registerId);
        treatmentPlan.setRegister(register);
        prepareData(register, uiModel);

        return REGISTER_TREATMENT_PLAN_PAGE;
    }

    //Examination

    @RequestMapping(value = "/examination/{registerId}", method = RequestMethod.GET)
    public String examination(@PathVariable Long registerId, Model uiModel) {
        Register register = registerService.findOne(registerId);
        prepareData(register,  uiModel);

        return REGISTER_EXAMINATION_PAGE;
    }

    //Cheif Complaints
    @RequestMapping(value = "/chiefcomplaints/{registerId}", method = RequestMethod.GET)
    public String chiefcomplaints(@PathVariable Long registerId,
                                  Model uiModel) {

        Register register = registerService.findOne(registerId);
        uiModel.addAttribute("chiefcomplaints", register);
        prepareData(register, uiModel);

        return REGISTER_CHIEF_COMPLAINTS_PAGE;
    }

    //vitals
    @RequestMapping(value = "/vitals/{registerId}", method = RequestMethod.GET)
    public String vital(@PathVariable Long registerId,
                        @RequestParam RegistrationType registrationType,
                        Model uiModel) {

        uiModel.addAttribute("lastVital", registerService.getLastVital(registerId, registrationType));
        prepareData(registerId, registrationType, uiModel);

        return REGISTER_VITAL_PAGE;
    }

    //Visit note
    @RequestMapping(value = "/visits/{registerId}", method = RequestMethod.GET)
    public String visitNotes(@PathVariable Long registerId,
                             @RequestParam RegistrationType registrationType,
                             Model uiModel) {

        uiModel.addAttribute("visits", registerService.getVisits(registerId, registrationType));
        prepareData(registerId, registrationType, uiModel);

        return REGISTER_VISIT_NOTE_PAGE;
    }

    //outcome
    @RequestMapping(value = "/outcome/{registerId}", method = RequestMethod.GET)
    public String outcome(@PathVariable Long registerId,
                          @RequestParam RegistrationType registrationType,
                          Model uiModel) {
        prepareData(registerId, registrationType, uiModel);

        return REGISTER_OUTCOME_PAGE;
    }

    @RequestMapping(value = "/edit-outcome/{registerId}", method = RequestMethod.GET)
    public String editOutcome(@PathVariable Long registerId,
                              @RequestParam RegistrationType registrationType,
                              Model uiModel) {
        prepareData(registerId, registrationType, uiModel);
        uiModel.addAttribute("edit", true);
        uiModel.addAttribute("registerId", registerId);

        return REGISTER_OUTCOME_PAGE;
    }

    @RequestMapping(value = "/edit-outcome/{registerId}", method = RequestMethod.POST)
    public String saveOutcome(@PathVariable Long registerId,
                              @RequestParam RegistrationType registrationType,
                              String outcome,
                              Model uiModel) {

        registerService.saveOutcome(outcome, registerId, registrationType);

        return REDIRECT_REGISTER_OUTCOME_PAGE + registerId + "?registrationType=" + registrationType;
    }

    //Remarks
    @RequestMapping(value = "/remarks/{registerId}", method = RequestMethod.GET)
    public String remarks(@PathVariable Long registerId,
                          Model uiModel) {
        Register register = registerService.findOne(registerId);
        prepareData(register, uiModel);

        return REGISTER_REMARKS_PAGE;
    }

    @RequestMapping(value = "/edit-remarks/{registerId}", method = RequestMethod.GET)
    public String editRemarks(@PathVariable Long registerId,
                              Model uiModel) {
        Register register = registerService.findOne(registerId);
        prepareData(register,  uiModel);
        uiModel.addAttribute("edit", true);
        uiModel.addAttribute("registerId", registerId);

        return REGISTER_REMARKS_PAGE;
    }

    @RequestMapping(value = "/edit-remarks/{registerId}", method = RequestMethod.POST)
    public String saveRemarks(@PathVariable Long registerId,
                              String remarks,
                              Model uiModel) {

        registerService.saveRemarks(remarks, registerId);

        return REDIRECT_REGISTER_REMARKS_PAGE + registerId ;
    }

    // Convert OPD to IPD

//    @RequestMapping(value = "/convert-to-ipd/{registerId}", method = RequestMethod.GET)
//    public String convert(@PathVariable Long registerId,
//                          @RequestParam RegistrationType registrationType,
//                          Model uiModel) {
//
//        OutdoorRegister outdoorRegister = registerService.findOpdRegister(registerId);
//
//        Register register = new Register();
//        register.setRegistrationId(outdoorRegister.getRegistrationId());
//        register.setPatientContact(outdoorRegister.getPatientContact());
//        register.setPatient(outdoorRegister.getPatient());
//
//        uiModel.addAttribute("register", register);
//        uiModel.addAttribute("registerId", registerId);
//
//        return REGISTER_CONVERT_PAGE;
//    }
//
//    @RequestMapping(value = "/convert-to-ipd/{registerId}", method = RequestMethod.POST)
//    public String completeConversion(@PathVariable Long registerId,
//                                     @Valid Register register,
//                                     BindingResult result,
//                                     Model uiModel) {
//        if (result.hasErrors()) {
//            uiModel.addAttribute("registerId", registerId);
//
//            return REGISTER_CONVERT_PAGE;
//        }
//
//        Register savedRegister = registerService.convertOutdoorRegisterToIndoorRegister(registerId, register);
//
//        return REDIRECT_PATIENT_SHOW_PAGE + savedRegister.getPatient().getId();
//    }

    @RequestMapping(value = "/medicalhistory/{registerId}", method = RequestMethod.GET)
    public String pastMedicalHistory(@PathVariable Long registerId, Model uiModel) {
        Register register = registerService.findOne(registerId);
        prepareData(register, uiModel);

        return REGISTER_MEDICAL_HISTORY_PAGE;
    }

    @RequestMapping(value = "/operationaldetail/{registerId}", method = RequestMethod.GET)
    public String operationalDetails(@PathVariable Long registerId, Model uiModel) {

        Register register = registerService.findOne(registerId);
        prepareData(register, uiModel);

        Set<OperationalDetail> detailList = registerService.findOperationalDetailList(registerId);
        uiModel.addAttribute("operationaldetails", detailList);

        return REGISTER_OPERATIONAL_DETAIL_PAGE;
    }

    @RequestMapping(value = "/investigation/{registerId}", method = RequestMethod.GET)
    public String investigation(@PathVariable Long registerId, Model uiModel) {
        Register register = registerService.findOne(registerId);
        prepareData(register,  uiModel);

        Set<Investigation> investigations = registerService.findInvestigations(registerId);

        uiModel.addAttribute("investigations", investigations);

        return REGISTER_INVESTIGATION_PAGE;
    }

    //complicationmanagement
    @RequestMapping(value = "/complicationmanagement/{registerId}", method = RequestMethod.GET)
    public String complicationmanagement(@PathVariable Long registerId, Model uiModel) {
        Register register = registerService.findOne(registerId);
        prepareData(register,  uiModel);

        return REGISTER_COMPLICATION_MANAGEMENT_PAGE;
    }
//
//    @RequestMapping(value = "/lifestyle/{registerId}", method = RequestMethod.GET)
//    public String lifeStyle(@PathVariable Long registerId, Model uiModel) {
//        prepareData(registerId, RegistrationType.INDOOR, uiModel);
//
//        return REGISTER_LIFE_STYLE_PAGE;
//    }

    @RequestMapping(value = "/picture/{registerId}", method = RequestMethod.GET)
    public String pictureInformation(@PathVariable Long registerId, Model uiModel) {
        Register register = registerService.findOne(registerId);
        prepareData(register,  uiModel);

        return REGISTER_PICTURE_PAGE;
    }

    private void prepareData(@PathVariable Long registerId, RegistrationType registrationType, Model uiModel) {
        uiModel.addAttribute("register", registerService.findRegister(registerId, registrationType));
        uiModel.addAttribute("registrationType", registrationType);

        Patient patient = registerService.findRegisterEither(registerId, registrationType)
                .fold(Register::getPatient, OutdoorRegister::getPatient);

        uiModel.addAttribute("patient", patient);
    }

    private void prepareData(Register register,  Model uiModel) {
        uiModel.addAttribute("register", register);
        uiModel.addAttribute("registrationType", register.getRegistrationType());
        uiModel.addAttribute("patient", register.getPatient());
    }
}

