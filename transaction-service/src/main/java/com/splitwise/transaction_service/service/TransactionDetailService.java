package com.splitwise.transaction_service.service;

import com.splitwise.transaction_service.document.TransactionDetail;
import com.splitwise.transaction_service.dto.CommonResponse;
import com.splitwise.transaction_service.dto.TransactionDto;
import com.splitwise.transaction_service.enums.TransactionType;
import com.splitwise.transaction_service.exception.NoTransactionFound;
import com.splitwise.transaction_service.repository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionDetailService {

    private final TransactionDetailRepository transactionRepository;
    private final ModelMapper modelMapper;
    public CommonResponse<TransactionDto> getTransactionDetail(int userId){
        Optional<TransactionDetail> transactionDetail = transactionRepository.findById(userId);
        if(transactionDetail.isPresent()){

            return CommonResponse
                    .<TransactionDto>builder()
                    .status(true)
                    .description("Transaction Detail fetched successfully")
                    .data(modelMapper.map(transactionDetail.get(), TransactionDto.class))
                    .build();
        }else{
            throw new NoTransactionFound("No Transaction found");
        }
    }

    public CommonResponse<TransactionDto> setNewTransactionDetail(int userId){
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setTotalAmount(0.0);
        transactionDetail.setCurrentStatus(TransactionType.SETTLED);
        transactionDetail.setId(userId);
        transactionDetail.setTransactionList(new ArrayList<>());

        transactionRepository.save(transactionDetail);
        return CommonResponse
                .<TransactionDto>builder()
                .status(true)
                .description("Transaction Detail saved successfully")
                .data(modelMapper.map(transactionDetail, TransactionDto.class))
                .build();
    }
}
