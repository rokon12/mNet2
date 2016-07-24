package org.jugbd.mnet.service;

import org.hibernate.Hibernate;
import org.jugbd.mnet.dao.OutdoorRegisterRepository;
import org.jugbd.mnet.dao.PatientDao;
import org.jugbd.mnet.dao.RegisterDao;
import org.jugbd.mnet.dao.VitalDao;
import org.jugbd.mnet.domain.*;
import org.jugbd.mnet.domain.enums.RegistrationType;
import org.jugbd.mnet.domain.enums.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Bazlur Rahman Rokon
 * @since 10/14/14.
 */
@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {

    private static final Logger log = LoggerFactory.getLogger(RegisterService.class);

    @Autowired
    private RegisterDao registerDao;

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private OutdoorRegisterRepository outdoorRegisterRepository;

    @Autowired
    private VitalDao vitalDao;

    @Override
    public Register save(Register register) {
        if (register.getId() != null) {
            Register registerFromDb = registerDao.findOne(register.getId());

            PatientContact patientContact = registerFromDb.getPatientContact();
            patientContact.setContactPerson(register.getPatientContact().getContactPerson());
            patientContact.setEmergencyContactNumber(register.getPatientContact().getEmergencyContactNumber());
            patientContact.setRelationship(register.getPatientContact().getRelationship());
            patientContact.setComments(register.getPatientContact().getComments());

            registerFromDb.setRegistrationId(register.getRegistrationId());
            registerFromDb.setWard(register.getWard());
            registerFromDb.setWardOther(register.getWardOther());
            registerFromDb.setBedNumber(register.getBedNumber());
            registerFromDb.setUnit(register.getUnit());
            registerFromDb.setAdmissionDate(register.getAdmissionDate());

            return registerDao.save(registerFromDb);
        } else {
            register.setPatient(patientDao.findOne(register.getPatient().getId()));
            register.setStartDatetime(new Date());
            register.setStatus(Status.ACTIVE);

            return registerDao.save(register);
        }
    }

    @Override
    public Register findOne(Long registerId) {

        //initializeRegister(register);

        return registerDao.findOne(registerId);
    }

    @Override
    public List<Register> findAllRegisterByPatientId(Long patientId) {

        return registerDao.findAllRegisterByPatientId(patientId);
    }

    private Register initializeRegister(Register register) {
        //log.info("initializeRegister() ={}", register);
        // Ref: http://stackoverflow.com/questions/19928568/hibernate-best-practice-to-pull-all-lazy-collections
        //register.getVitals().size();
        //register.getOperationalDetails().size();
        //register.getInvestigation().size();

        return register;
    }

    @Override
    public void closeRegister(Long registerId) {
        Register register = registerDao.findOne(registerId);
        register.setStatus(Status.CLOSED);
        register.setStopDatetime(new Date());
        registerDao.save(register);
    }

    @Override
    public void update(Register register) {

        registerDao.save(register);
    }

    @Override
    public OutdoorRegister save(OutdoorRegister register) {
        if (register.getId() != null) {
            OutdoorRegister registerFromDb = outdoorRegisterRepository.findOne(register.getId());

            PatientContact patientContact = registerFromDb.getPatientContact();
            patientContact.setContactPerson(register.getPatientContact().getContactPerson());
            patientContact.setEmergencyContactNumber(register.getPatientContact().getEmergencyContactNumber());
            patientContact.setRelationship(register.getPatientContact().getRelationship());
            patientContact.setComments(register.getPatientContact().getComments());
            registerFromDb.setRegistrationId(register.getRegistrationId());

            return outdoorRegisterRepository.save(registerFromDb);
        } else {

            register.setPatient(patientDao.findOne(register.getPatient().getId()));
            register.setStartDatetime(new Date());
            register.setStatus(Status.ACTIVE);

            return outdoorRegisterRepository.save(register);
        }
    }

    @Override
    public OutdoorRegister findOpdRegister(Long id) {

        return outdoorRegisterRepository.findOne(id);
    }

    @Override
    public Register findRegister(Long id) {

        return registerDao.findOne(id);
    }

    @Override
    public void update(OutdoorRegister register) {

        outdoorRegisterRepository.save(register);
    }

    @Override
    public Vital getLastVital(Long registerId) {
        Register register = findOne(registerId);

        return getVital(register.getVitals());
    }

    @Override
    public List<Visit> getVisits(Long registerId) {
        Register register = registerDao.findOne(registerId);

        return register.getVisits().stream()
                .filter(visit -> visit.getStatus() == Status.ACTIVE)
                .sorted((o1, o2) -> o2.getCreatedDate().compareTo(o1.getCreatedDate()))
                .collect(Collectors.toList());
    }

    @Override
    public void saveOutcome(String outcome, Long registerId, RegistrationType registrationType) {
        //if (registrationType == RegistrationType.OUTDOOR) {
        OutdoorRegister outdoorRegister = outdoorRegisterRepository.findOne(registerId);
        outdoorRegister.setOutcome(outcome);
        outdoorRegisterRepository.save(outdoorRegister);
        //}
    }

    @Override
    public void saveRemarks(String remark, Long registerId) {
        //OutdoorRegister outdoorRegister = outdoorRegisterRepository.findOne(registerId);
        //outdoorRegister.setRemarks(remark);
        Register register = registerDao.findOne(registerId);
        register.setRemarks(remark);
        registerDao.save(register);
    }

    @Override
    public Set<OperationalDetail> findOperationalDetailList(Long registerId) {

        Register register = registerDao.findOne(registerId);
        register.getOperationalDetails().size();

        return register.getOperationalDetails();
    }

    @Override
    public Set<Investigation> findInvestigations(Long registerId) {
        Register register = registerDao.findOne(registerId);
        register.getInvestigation().size();

        return register.getInvestigation();
    }

    @Override
    public Register loadRegister(Long registerId, Class clazz) {
        Register register = registerDao.findOne(registerId);

        //eger load
        if (clazz.isAssignableFrom(ChiefComplaint.class) && register.getChiefComplaint() != null) {
            Hibernate.initialize(register.getChiefComplaint().getCreatedBy());
            Hibernate.initialize(register.getChiefComplaint().getLastModifiedBy());
        } else if (clazz.isAssignableFrom(MedicalHistory.class) && register.getMedicalHistory() != null) {
            Hibernate.initialize(register.getMedicalHistory().getCreatedBy());
            Hibernate.initialize(register.getMedicalHistory().getLastModifiedBy());
        } else if (clazz.isAssignableFrom(ComplicationManagement.class) && register.getComplicationManagement() != null) {
            Hibernate.initialize(register.getComplicationManagement().getCreatedBy());
            Hibernate.initialize(register.getComplicationManagement().getLastModifiedBy());
        }else if (clazz.isAssignableFrom(Examination.class) && register.getExamination() != null) {
            Hibernate.initialize(register.getExamination().getCreatedBy());
            Hibernate.initialize(register.getExamination().getLastModifiedBy());
        }else if (clazz.isAssignableFrom(Diagnosis.class) && register.getDiagnosis() != null) {
            Hibernate.initialize(register.getDiagnosis().getCreatedBy());
            Hibernate.initialize(register.getDiagnosis().getLastModifiedBy());
        }else if (clazz.isAssignableFrom(DiagnosisFinal.class) && register.getDiagnosisFinal() != null) {
            Hibernate.initialize(register.getDiagnosisFinal().getCreatedBy());
            Hibernate.initialize(register.getDiagnosisFinal().getLastModifiedBy());
        }else if (clazz.isAssignableFrom(TreatmentPlan.class) && register.getTreatmentPlan() != null) {
            Hibernate.initialize(register.getTreatmentPlan().getCreatedBy());
            Hibernate.initialize(register.getTreatmentPlan().getLastModifiedBy());
        }


        return register;
    }

    private Vital getVital(Set<Vital> vitals) {

        return vitals.stream()
                .filter(vital -> vital.getStatus() == Status.ACTIVE)
                .sorted((e1, e2) -> e2.getCreatedDate().compareTo(e1.getCreatedDate()))
                .findFirst()
                .orElse(null);
    }
}
