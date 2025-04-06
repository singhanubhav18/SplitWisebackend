package com.splitwise.transaction_service.service;

import com.splitwise.transaction_service.auth.UserContextHolder;
import com.splitwise.transaction_service.client.AuthServiceClient;
import com.splitwise.transaction_service.document.TransactionDetail;
import com.splitwise.transaction_service.dto.CommonResponse;
import com.splitwise.transaction_service.dto.TransactionDto;
import com.splitwise.transaction_service.dto.UserDetailsDto;
import com.splitwise.transaction_service.enums.TransactionType;
import com.splitwise.transaction_service.exception.NoTransactionFound;
import com.splitwise.transaction_service.repository.TransactionDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionDetailService {

    private final TransactionDetailRepository transactionRepository;
    private final ModelMapper modelMapper;
    private  final AuthServiceClient authServiceClient;
    public CommonResponse<TransactionDto> getTransactionDetail(){
        Long userId= UserContextHolder.getCurrentUserId();
        log.info("Getting userId: {}", userId);
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

    public CommonResponse<TransactionDto> setNewTransactionDetail(){
        Long userId= UserContextHolder.getCurrentUserId();
        log.info("Getting userId: {}", userId);
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
    public CommonResponse<List<UserDetailsDto>> searchUserByName(String name) {
       // CommonResponse<List<UserDetailsDto>> response = new CommonResponse<>();
        Long userId= UserContextHolder.getCurrentUserId();
        List<UserDetailsDto> userDetailsList = authServiceClient.getUserByName(name);
        try {
            // Build response based on the result of userDetailsList
            return CommonResponse
                    .<List<UserDetailsDto>>builder()
                    .status(userDetailsList != null && !userDetailsList.isEmpty())
                    .data(userDetailsList != null ? userDetailsList : new ArrayList<>())
                    .description(userDetailsList != null && !userDetailsList.isEmpty()
                            ? "User details found with name " + userDetailsList.getFirst().getName()
                            : "No user details found with name " + name)
                    .build();
        } catch (Exception e) {
            return CommonResponse
                    .<List<UserDetailsDto>>builder()
                    .status(false)
                    .data(new ArrayList<>())
                    .description("No user details found with name: " + name)
                    .build();
        }
    }
}
