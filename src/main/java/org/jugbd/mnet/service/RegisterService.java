package org.jugbd.mnet.service;

import org.jugbd.mnet.domain.*;
import org.jugbd.mnet.domain.enums.RegistrationType;
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

    List<Register> findAllRegisterByPatientId(Long patientId);

    void closeRegister(Long registerId, RegistrationType registrationType);

    void update(Register register);

    OutdoorRegister save(OutdoorRegister register);

    OutdoorRegister findOpdRegister(Long id);

    Register findRegister(Long id);

    void update(OutdoorRegister register);

    Vital getLastVital(Long registerId);

    List<Visit> getVisits(Long registerId);

    void saveOutcome(String outcome, Long registerId, RegistrationType registrationType);

    void saveRemarks(String remark, Long registerId);

    Set<OperationalDetail> findOperationalDetailList(Long registerId);

    Set<Investigation> findInvestigations(Long registerId);

    Register loadRegister(Long registerId, Class clazz);
}
