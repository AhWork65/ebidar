package ebidar.com.minioms.service;

import ebidar.com.minioms.exception.NotFound;
import ebidar.com.minioms.exception.NotValidException;
import ebidar.com.minioms.model.Customer;
import ebidar.com.minioms.model.Dto.CustomerDto;
import ebidar.com.minioms.model.Dto.ShareDto;
import ebidar.com.minioms.model.Share;
import ebidar.com.minioms.model.enums.SettlementDateType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IShareOMSService {


    @Transactional(rollbackFor = {Exception.class})
    void createShare(ShareDto input) throws NotValidException;

    @Transactional(rollbackFor = {Exception.class})
    void deleteShare(String shareCode) throws NotFound;

    public List<Share> getAllShare();

    Share getShareById(Long id) throws NotFound;

    Share getShareByCode(String code) throws NotFound;

    public List<Share> getShareBySettlementDateType(SettlementDateType dateType) throws NotFound;


}
