package org.jugbd.mnet.service;

import org.jugbd.mnet.dao.DiagnosisDao;
import org.jugbd.mnet.dao.DiagnosisFinalDao;
import org.jugbd.mnet.domain.Diagnosis;
import org.jugbd.mnet.domain.DiagnosisFinal;
import org.jugbd.mnet.domain.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mushfekur Rahman (mushfek0001)
 */
@Service
@Transactional
public class DiagnosisServiceImpl implements DiagnosisService {

    private DiagnosisDao diagnosisDao;
    private DiagnosisFinalDao diagnosisFinalDao;
    private RegisterService registerService;

    @Autowired
    public DiagnosisServiceImpl(DiagnosisDao diagnosisDao, DiagnosisFinalDao diagnosisFinalDao, RegisterService registerService) {
        this.diagnosisDao = diagnosisDao;
        this.diagnosisFinalDao = diagnosisFinalDao;
        this.registerService = registerService;
    }

    @Override
    public Diagnosis save(Diagnosis diagnosis) {

        if (diagnosis.getId() == null) {
            Register register = registerService.findOne(diagnosis.getRegister().getId());
            diagnosis.setRegister(register);
            Diagnosis savedDiagnosis = diagnosisDao.save(diagnosis);

            register.setDiagnosis(savedDiagnosis);
            registerService.save(register);

            return savedDiagnosis;
        } else {

            return updateDiagnosis(diagnosis);
        }
    }

    @Override
    public Diagnosis findOne(Long id) {

        return diagnosisDao.findOne(id);
    }

    @Override
    public DiagnosisFinal save(DiagnosisFinal diagnosisFinal) {
        if (diagnosisFinal.getId() == null){
            Register register = registerService.findOne(diagnosisFinal.getRegister().getId());
            diagnosisFinal.setRegister(register);
            DiagnosisFinal savedDiagnosis = diagnosisFinalDao.save(diagnosisFinal);

            register.setDiagnosisFinal(savedDiagnosis);
            registerService.save(register);

            return savedDiagnosis;
        }

         return updateDiagnosis(diagnosisFinal);
    }

    private DiagnosisFinal updateDiagnosis(DiagnosisFinal diagnosisFinal) {
        DiagnosisFinal diagnosisFinalFromDb = diagnosisFinalDao.findOne(diagnosisFinal.getId());
        diagnosisFinalFromDb.setDiagnosis(diagnosisFinal.getDiagnosis());
        diagnosisFinalFromDb.setSystemInvolved(diagnosisFinal.getSystemInvolved());

        return diagnosisFinalDao.save(diagnosisFinalFromDb);
    }

    @Override
    public DiagnosisFinal findOneFinalDiagnosis(Long id) {

        return diagnosisFinalDao.findOne(id);
    }

    private Diagnosis updateDiagnosis(Diagnosis diagnosis) {
        Diagnosis diagnosisFromDb = diagnosisDao.findOne(diagnosis.getId());
        diagnosisFromDb.setIcd10(diagnosis.getIcd10());
        diagnosisFromDb.setDiagnosis(diagnosis.getDiagnosis());
        diagnosisFromDb.setComment(diagnosis.getComment());

        return diagnosisDao.save(diagnosisFromDb);
    }
}
