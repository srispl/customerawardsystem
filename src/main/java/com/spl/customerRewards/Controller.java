package com.spl.customerRewards;

import com.spl.customerRewards.domain.CustomerInfo;
import com.spl.customerRewards.domain.Request;
import com.spl.customerRewards.domain.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class Controller {

    @PostMapping(
            value = "/calculateRewards", consumes = "application/json", produces = "application/json")
    public Response calculateRewards(@RequestBody Request request) {
        Response response = new Response();
        if(request.getCustomerInfoList() == null || request.getCustomerInfoList().size() == 0) {
            response.setError("Empty Customer info");
            return response;
        }
        try {
            calculateRewardsForEachCustomer(request, response);
        } catch (ParseException | RuntimeException ex) {
            response.setError(ex.getMessage());
        }
        return response;
    }

    private void calculateRewardsForEachCustomer(Request request, Response response) throws ParseException, RuntimeException {
        response.setCustomerInfoList(request.getCustomerInfoList());
        for(int i=0; i< response.getCustomerInfoList().size(); i++) {
            CustomerInfo customerInfo = request.getCustomerInfoList().get(i);
            if(customerInfo.getTransactions() != null && customerInfo.getTransactions().size() > 0) {
                Map<Integer, Integer> monthlyRewards = calculateMonthlyTransactionData(customerInfo, response);
                customerInfo.setMonthlyRewardPoints(monthlyRewards);
                int totalRewards = monthlyRewards.values().stream().reduce(0, Integer::sum);
                customerInfo.setTotalRewardPoints(totalRewards);
            }
        }
    }

    private Map<Integer, Integer> calculateMonthlyTransactionData( CustomerInfo customerInfo, Response response) throws ParseException, RuntimeException {
        Map<Integer, Integer> monthlyTransactionData = new HashMap<>();
        String pattern = "MM-dd-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        for(Map.Entry<String, Integer> transactionRewards : customerInfo.getTransactions().entrySet()) {
            if(transactionRewards.getValue() < 0) {
                throw new RuntimeException("Invalid transaction data");
            }
            Date parsedDate = simpleDateFormat.parse(transactionRewards.getKey());
            Calendar c = Calendar.getInstance();
            c.setTime(parsedDate);
            int transactionMonth = c.get(Calendar.MONTH) + 1;
            if(monthlyTransactionData.get(transactionMonth) != null) {
                monthlyTransactionData.put(transactionMonth,
                        monthlyTransactionData.get(transactionMonth)
                                + calculateRewards(transactionRewards.getValue()));
            } else if(monthlyTransactionData.size() < 3) {
                monthlyTransactionData.put(transactionMonth, calculateRewards(transactionRewards.getValue()));
            } else {
                throw new RuntimeException("More than 3 months data provided");
            }
        }
        return monthlyTransactionData;
    }

    private int calculateRewards(int totalTransactionAmount) {
        int totalRewards = 0;
        if(totalTransactionAmount - 50 > 0) {
            totalRewards += (totalTransactionAmount - 50) * 1;
        }
        if(totalTransactionAmount - 100 > 0) {
            totalRewards += (totalTransactionAmount - 100) * 1;
        }
        return totalRewards;
    }

    @GetMapping(value = "/health")
    public String healthCheck() {
        return "Healthy";
    }
    
}
