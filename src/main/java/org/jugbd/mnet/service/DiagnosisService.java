package org.jugbd.mnet.service;

import org.jugbd.mnet.domain.Diagnosis;
import org.jugbd.mnet.domain.DiagnosisFinal;
import org.springframework.stereotype.Component;

/**
 * @author Mushfekur Rahman (mushfek0001)
 */
@Component
public interface DiagnosisService {
    Diagnosis save(Diagnosis diagnosis);

    Diagnosis findOne(Long id);

    DiagnosisFinal save(DiagnosisFinal diagnosisFinal);

    DiagnosisFinal findOneFinalDiagnosis(Long id);
}
