package org.jugbd.mnet.dao;

import org.jugbd.mnet.domain.DiagnosisFinal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bazlur Rahman Rokon
 * @since 7/19/16.
 */
@Repository
public interface DiagnosisFinalDao extends JpaRepository<DiagnosisFinal, Long> {
}
