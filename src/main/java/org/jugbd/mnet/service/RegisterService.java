package org.jugbd.mnet.service;

import org.jugbd.mnet.domain.*;
import org.jugbd.mnet.domain.enums.RegistrationType;
import org.jugbd.mnet.utils.Either;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author Bazlur Rahman Rokon
 * @since 10/14/14.
 */
@Component
public interface RegisterService {
    Register save(Register register);

    Register findOne(Long registerId);

    Register findActiveRegisterByPatientId(Long patientId);

    List<Register> findAllRegisterByPatientId(Long patientId);

    void closeRegister(Long registerId, RegistrationType registrationType);

    void update(Register register);

    void addVital(Vital vital, Long registerId);

    OutdoorRegister save(OutdoorRegister register);

    OutdoorRegister findOpdRegister(Long id);

    Diagnosis findDiagnosis(Long registerId, RegistrationType registrationType);

    Object findRegister(Long registerId, RegistrationType registrationType);

    Register findRegister(Long id);

    Either<Register, OutdoorRegister> findRegisterEither(Long registerId, RegistrationType registrationType);

    TreatmentPlan findTreatmentPlan(Long registerId, RegistrationType registrationType);

    void update(OutdoorRegister register);

    Examination findExamination(Long registerId, RegistrationType registrationType);

    ChiefComplaint findChiefcomplaints(Long registerId, RegistrationType registrationType);

    Vital getLastVital(Long registerId);

    List<Visit> getVisits(Long registerId);

    void saveOutcome(String outcome, Long registerId, RegistrationType registrationType);

    void saveRemarks(String remark, Long registerId);

    Register convertOutdoorRegisterToIndoorRegister(Long registerId, Register register);

    MedicalHistory findMedicalHistory(Long registerId);

    Set<OperationalDetail> findOperationalDetailList(Long registerId);

    Set<Investigation> findInvestigations(Long registerId);

    List<OutdoorRegister> findAllOutdoorRegisterByPatientId(Long patientId);

    Register loadRegister(Long registerId, Class clazz);
}
