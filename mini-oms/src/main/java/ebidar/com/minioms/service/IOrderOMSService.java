package ebidar.com.minioms.service;

import ebidar.com.minioms.exception.NotFound;
import ebidar.com.minioms.exception.NotValidException;
import ebidar.com.minioms.exception.WalletNoBalanceException;
import ebidar.com.minioms.model.Dto.*;
import ebidar.com.minioms.model.enums.OrderState;
import org.springframework.transaction.annotation.Transactional;

public interface IOrderOMSService {


    @Transactional(rollbackFor = {Exception.class})
    public Long createOrder(OrderDto input) throws NotValidException, NotFound, WalletNoBalanceException;

    @Transactional(rollbackFor = {Exception.class})
    public void changeOrderState(Long id, OrderState orderState) throws NotFound;


}
