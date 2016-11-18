package org.jugbd.mnet.service;

import org.jugbd.mnet.dao.VisitRepository;
import org.jugbd.mnet.domain.Register;
import org.jugbd.mnet.domain.Visit;
import org.jugbd.mnet.domain.enums.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Bazlur Rahman Rokon
 * @date 10/6/15.
 */

@Service
public class VisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private RegisterService registerService;

    public Visit save(Visit visit, Long registerId) {

        Register register = registerService.findOne(registerId);
        visit.setRegister(register);
        visit.setStatus(Status.ACTIVE);
        visit.setVisitTime(new Date());

        return visitRepository.save(visit);
    }

    public Long delete(Long id) {
        Visit visit = visitRepository.findOne(id);
        visit.setStatus(Status.DELETED);
        Visit savedVital = visitRepository.save(visit);

        return savedVital.getRegister().getId();
    }
}
