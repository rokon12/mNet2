package org.jugbd.mnet.service;

import org.jugbd.mnet.dao.InvestigationDao;
import org.jugbd.mnet.domain.Investigation;
import org.jugbd.mnet.domain.Register;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bazlur Rahman Rokon
 * @date 12/27/14.
 */
@Transactional
@Service
public class InvestigationServiceImpl implements InvestigationService {

    @Autowired
    private InvestigationDao investigationDao;

    @Autowired
    private RegisterService registerService;

    @Override
    public Investigation save(Investigation investigation) {

        if (investigation.getId() == null) {
            Register register = registerService.findOne(investigation.getRegister().getId());
            investigation.setRegister(register);

            return investigationDao.save(investigation);
        } else {
            Investigation savedInvestigation = investigationDao.findOne(investigation.getId());

            savedInvestigation.setBloodCbc(investigation.getBloodCbc());
            savedInvestigation.setBloodCs(investigation.getBloodCs());
            savedInvestigation.setWoundCs(investigation.getWoundCs());
            savedInvestigation.setElectrolyte(investigation.getElectrolyte());
            savedInvestigation.setBloodSugar(investigation.getBloodSugar());
            savedInvestigation.setUrineRe(investigation.getUrineRe());
            savedInvestigation.setsCreatinine(investigation.getsCreatinine());
            savedInvestigation.setCxr(investigation.getCxr());
            savedInvestigation.setEcg(investigation.getEcg());
            savedInvestigation.setEcho(investigation.getEcho());
            savedInvestigation.setsCalcium(investigation.getsCalcium());
            savedInvestigation.setsMg(investigation.getsMg());
            savedInvestigation.setdDimer(investigation.getdDimer());
            savedInvestigation.setAptt(investigation.getAptt());
            savedInvestigation.setAda(investigation.getAda());
            savedInvestigation.setOther(investigation.getOther());
            savedInvestigation.setsBilirubin(investigation.getsBilirubin());
            savedInvestigation.setSgpt(investigation.getSgpt());
            savedInvestigation.setSgut(investigation.getSgut());
            savedInvestigation.setAlkalinePhosphatase(investigation.getAlkalinePhosphatase());
            savedInvestigation.setsTotalProtein(investigation.getsTotalProtein());
            savedInvestigation.setSerumAlbumin(investigation.getSerumAlbumin());
            savedInvestigation.setAlbuminGlobulinRatio(investigation.getAlbuminGlobulinRatio());
            savedInvestigation.setPt(investigation.getPt());
            savedInvestigation.setxRaysDate(investigation.getxRaysDate());
            savedInvestigation.setPlainXRayAbdomen(investigation.getPlainXRayAbdomen());
            savedInvestigation.setxRayKub(investigation.getxRayKub());
            savedInvestigation.setxRayOthers(investigation.getxRayOthers());
            savedInvestigation.setUltrasonogramDate(investigation.getUltrasonogramDate());
            savedInvestigation.setUsgOfWA(investigation.getUsgOfWA());
            savedInvestigation.setUsgOfHBS(investigation.getUsgOfHBS());
            savedInvestigation.setUsgOfLA(investigation.getUsgOfLA());
            savedInvestigation.setUsaKub(investigation.getUsaKub());
            savedInvestigation.setUltrasonogramOthers(investigation.getUltrasonogramOthers());
            savedInvestigation.setCtScanDate(investigation.getCtScanDate());
            savedInvestigation.setNameOfCt(investigation.getNameOfCt());
            savedInvestigation.setCtFindings(investigation.getCtFindings());
            savedInvestigation.setMrcpDate(investigation.getMrcpDate());
            savedInvestigation.setMrcpFindings(investigation.getMrcpFindings());
            savedInvestigation.setMriDate(investigation.getMriDate());
            savedInvestigation.setNameOfMri(investigation.getNameOfMri());
            savedInvestigation.setMriFindings(investigation.getMriFindings());
            savedInvestigation.setErcpDate(investigation.getErcpDate());
            savedInvestigation.setErcpFindings(investigation.getErcpFindings());
            savedInvestigation.setIntervention(investigation.getIntervention());
            savedInvestigation.setUpperGiEndoscopyDate(investigation.getUpperGiEndoscopyDate());
            savedInvestigation.setUpperGiEndoscopyFindings(investigation.getUpperGiEndoscopyFindings());
            savedInvestigation.setColonscopyDate(investigation.getColonscopyDate());
            savedInvestigation.setColonscopyFindings(investigation.getColonscopyFindings());
            savedInvestigation.setBiopsyDate(investigation.getBiopsyDate());
            savedInvestigation.setBiopsyFindings(investigation.getBiopsyFindings());
            savedInvestigation.setBiopsy(investigation.getBiopsy());
            savedInvestigation.setContrastDate(investigation.getContrastDate());
            savedInvestigation.setNameOfContractFilm(investigation.getNameOfContractFilm());
            savedInvestigation.setContrastFindings(investigation.getContrastFindings());
            savedInvestigation.setOtherInvestigation(investigation.getOtherInvestigation());

            return investigationDao.save(savedInvestigation);
        }
    }

    @Override
    public Investigation findOne(Long id) {

        return investigationDao.findOne(id);
    }
}
