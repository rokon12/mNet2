package org.jugbd.mnet.service;

import com.mysema.query.jpa.JPQLQuery;
import org.jugbd.mnet.dao.PatientDao;
import org.jugbd.mnet.domain.Patient;
import org.jugbd.mnet.utils.PatientIdGenerator;
import org.jugbd.mnet.web.controller.PatientSearchCmd;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//import static org.jugbd.mnet.domain.QDiagnosis.diagnosis;
//import static org.jugbd.mnet.domain.QPatient.patient;
//import static org.jugbd.mnet.domain.QRegister.register;

/**
 * @author ronygomes
 */

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    private PatientDao patientDao;

    @PersistenceContext
    private EntityManager em;

    @Override
    public Patient create(Patient patient) {
        LOGGER.debug("creating new patient with info : {}", patient);

        patient.setHealthId(PatientIdGenerator.generate(patient.getAddress()));

        if (patient.getDateOfBirth() == null) {
            setEstimatedDateOfBirth(patient);
        }

        return patientDao.save(patient);
    }

    private void setEstimatedDateOfBirth(Patient patient) {
        int day = patient.getDay() != null ? patient.getDay() : 0;
        int month = patient.getMonth() != null ? patient.getMonth() : 0;
        int year = patient.getYear() != null ? patient.getYear() : 0;

        LocalDate localDate = LocalDate
                .now()
                .minusDays(day)
                .minusMonths(month)
                .minusYears(year);
        Date dateOfBirth = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        patient.setDateOfBirth(dateOfBirth);
        patient.setBirthdateEstimated(true);
    }

    @Override
    public Patient findOne(Long id) {

        return patientDao.findOne(id);
    }

    //TODO work spring data repository to return optional value
    @Override
    public Optional<Patient> findPatientById(Long id) {
        Patient one = patientDao.findOne(id);

        return Optional.ofNullable(one);
    }

    @Override
    public List<Patient> findAll() {

        return patientDao.findAll();
    }

    @Override
    public Page findPatientBySearchCmd(PatientSearchCmd searchCmd, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Patient> findAll(Pageable pageable) {

        return patientDao.findAll(pageable);
    }

    @Override
    public long count() {

        return patientDao.count();
    }

    @Override
    public void update(Patient patient) {
        LOGGER.debug("Updating patient with info : {}", patient);

        Patient patientFromDb = findOne(patient.getId());

        patientFromDb.setName(patient.getName());
        patientFromDb.setDateOfBirth(patient.getDateOfBirth());
        patientFromDb.setGender(patient.getGender());
        patientFromDb.setBloodType(patient.getBloodType());
        patientFromDb.setContactNumber(patient.getContactNumber());
        patientFromDb.setAddress(patient.getAddress());
        patientFromDb.setDead(patient.getDead());
        patientFromDb.setDeathDate(patient.getDeathDate());
        patientFromDb.setBirthdateEstimated(patient.getBirthdateEstimated());
        patientFromDb.setNid(patient.getNid());
        patientFromDb.setMaritalStatus(patient.getMaritalStatus());
        patientFromDb.setOccupation(patient.getOccupation());
        patientFromDb.setEducationLevel(patient.getEducationLevel());
        patientFromDb.setBirthdateEstimated(patient.getBirthdateEstimated());

        if (patient.getDay() != null || patient.getMonth() != null || patient.getYear() != null) {
            patientFromDb.setDay(patient.getDay());
            patientFromDb.setMonth(patient.getMonth());
            patientFromDb.setYear(patient.getYear());

            setEstimatedDateOfBirth(patientFromDb);
        }

        patientDao.save(patientFromDb);
    }

//    public Page findPatientBySearchCmd(final PatientSearchCmd searchCmd, Pageable pageable) {
//        BooleanBuilder predicate = new BooleanBuilder();
//
//        JPAQuery from = new JPAQuery(em)
//                .from(patient);
//
//        if (StringUtils.isNotEmpty(searchCmd.getHealthId())) {
//            predicate.or(patient.healthId.like(getLikePattern(searchCmd.getHealthId().trim())));
//        }
//
//        if (StringUtils.isNotEmpty(searchCmd.getPhoneNumber())) {
//            predicate.or(patient.contactNumber.like(getLikePattern(searchCmd.getPhoneNumber().trim())));
//            predicate.or(register.patientContact.emergencyContactNumber.like(getLikePattern(searchCmd.getPhoneNumber().trim())));
//        }
//
//        if (StringUtils.isNotEmpty(searchCmd.getRegisterId())) {
//            predicate.or(register.registrationId.eq(searchCmd.getRegisterId().trim()));
//        }
//
//        if (StringUtils.isNotEmpty(searchCmd.getName())) {
//            predicate.or(patient.name.like(getLikePattern(searchCmd.getName())));
//        }
//
//        if (StringUtils.isNotEmpty(searchCmd.getDiagnosis())) {
//            predicate.or(diagnosis.comment.like(getLikePattern(searchCmd.getDiagnosis())));
//            predicate.or(diagnosis.icd10.like(getLikePattern(searchCmd.getDiagnosis())));
//        }
//
//        long count = from.distinct().where(predicate).count();
//        List<Patient> patients = applyPagination(from, pageable)
//                .orderBy(patient.name.asc())
//                .distinct()
//                .list(patient);
//
//        return count > 0 ? new PageImpl<>(patients, pageable, count) : new PageImpl<>(new ArrayList<>(), pageable, 0);
//    }

    protected JPQLQuery applyPagination(JPQLQuery query, Pageable pageable) {

        return pageable != null ? query.offset(pageable.getOffset()).limit(pageable.getPageSize()) : query;
    }

    private String getLikePattern(String searchTerm) {

        return "%" + searchTerm.toLowerCase() + "%";
    }
}
