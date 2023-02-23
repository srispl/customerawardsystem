package com.spl.customerRewards;

import com.spl.customerRewards.domain.CustomerInfo;
import com.spl.customerRewards.domain.Request;
import com.spl.customerRewards.domain.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ControllerTest {

  @Test
  public void whenPositiveDollar_ThenCalculateReward() {
    Controller controller = new Controller();
    Request request = new Request();
    List<CustomerInfo> customerList = new ArrayList<>();
    CustomerInfo bob = new CustomerInfo();
    bob.setName("Bob");
    Map<String, Integer> transactions = new HashMap<>();
    transactions.put("01-01-2023", 51);
    transactions.put("02-01-2023", 20);
    transactions.put("01-02-2023", 51);
    transactions.put("02-02-2023", 100);
    bob.setTransactions(transactions);
    customerList.add(bob);
    request.setCustomerInfoList(customerList);
    Response response = controller.calculateRewards(request);
    Assertions.assertEquals(52, response.getCustomerInfoList().get(0).getTotalRewardPoints());
    Assertions.assertEquals(2, response.getCustomerInfoList().get(0).getMonthlyRewardPoints().get(1));
    Assertions.assertEquals(50, response.getCustomerInfoList().get(0).getMonthlyRewardPoints().get(2));
    Assertions.assertNull(response.getError());
  }

  @Test
  public void whenNegativeDollar_ThenReturnError() {
    Controller controller = new Controller();
    Request request = new Request();
    List<CustomerInfo> customerList = new ArrayList<>();
    CustomerInfo bob = new CustomerInfo();
    bob.setName("Bob");
    Map<String, Integer> transactions = new HashMap<>();
    transactions.put("01-01-2023", -51);
    transactions.put("02-01-2023", 20);
    transactions.put("01-02-2023", 51);
    transactions.put("02-02-2023", 100);
    bob.setTransactions(transactions);
    customerList.add(bob);
    request.setCustomerInfoList(customerList);
    Response response = controller.calculateRewards(request);
    Assertions.assertEquals(0, response.getCustomerInfoList().get(0).getTotalRewardPoints());
    Assertions.assertNull(response.getCustomerInfoList().get(0).getMonthlyRewardPoints());
    Assertions.assertEquals("Invalid transaction data", response.getError());
  }

  @Test
  public void whenMoreThan3MonthsData_ThenReturnZero() {
    Controller controller = new Controller();
    Request request = new Request();
    List<CustomerInfo> customerList = new ArrayList<>();
    CustomerInfo bob = new CustomerInfo();
    bob.setName("Bob");
    Map<String, Integer> transactions = new HashMap<>();
    transactions.put("01-01-2023", 51);
    transactions.put("02-01-2023", 20);
    transactions.put("01-02-2023", 51);
    transactions.put("02-02-2023", 100);
    transactions.put("03-02-2023", 1000);
    transactions.put("04-27-2023", 100);
    bob.setTransactions(transactions);
    customerList.add(bob);
    request.setCustomerInfoList(customerList);
    Response response = controller.calculateRewards(request);
    Assertions.assertEquals(0, response.getCustomerInfoList().get(0).getTotalRewardPoints());
    Assertions.assertNull(response.getCustomerInfoList().get(0).getMonthlyRewardPoints());
    Assertions.assertEquals("More than 3 months data provided", response.getError());
  }

  @Test
  public void whenTransactionsIsNotNull_ThenReturnZero() {
    Controller controller = new Controller();
    Request request = new Request();
    List<CustomerInfo> customerList = new ArrayList<>();
    CustomerInfo bob = new CustomerInfo();
    bob.setName("Bob");
    customerList.add(bob);
    request.setCustomerInfoList(customerList);
    Response response = controller.calculateRewards(request);
    Assertions.assertEquals(0, response.getCustomerInfoList().get(0).getTotalRewardPoints());
    Assertions.assertNull(response.getCustomerInfoList().get(0).getMonthlyRewardPoints());
    Assertions.assertNull(response.getError());
  }

  @Test
  public void whenTransactionsIsEmpty_ThenReturnZero() {
    Controller controller = new Controller();
    Request request = new Request();
    List<CustomerInfo> customerList = new ArrayList<>();
    CustomerInfo bob = new CustomerInfo();
    bob.setName("Bob");
    Map<String, Integer> transactions = new HashMap<>();
    bob.setTransactions(transactions);
    customerList.add(bob);
    request.setCustomerInfoList(customerList);
    Response response = controller.calculateRewards(request);
    Assertions.assertEquals(0, response.getCustomerInfoList().get(0).getTotalRewardPoints());
    Assertions.assertNull(response.getCustomerInfoList().get(0).getMonthlyRewardPoints());
    Assertions.assertNull(response.getError());
  }

  @Test
  public void whenCustomerInfoIsEmpty_ThenReturnZero() {
    Controller controller = new Controller();
    Request request = new Request();
    List<CustomerInfo> customerList = new ArrayList<>();
    request.setCustomerInfoList(customerList);
    Response response = controller.calculateRewards(request);
    Assertions.assertEquals("Empty Customer info", response.getError());
  }

  @Test
  public void whenCustomerInfoIsNull_ThenReturnZero() {
    Controller controller = new Controller();
    Request request = new Request();
    Response response = controller.calculateRewards(request);
    Assertions.assertEquals("Empty Customer info", response.getError());
  }
}
