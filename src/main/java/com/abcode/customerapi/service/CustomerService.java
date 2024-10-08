package com.abcode.customerapi.service;

import com.abcode.customerapi.dto.CustomerDTO;
import com.abcode.customerapi.dto.CustomerSearchCriteriaDTO;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO getCustomerById(Long id);

    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);

    void deleteCustomer(Long id);

    Page<CustomerDTO> getAllCustomersUsingPagination(@Valid CustomerSearchCriteriaDTO customerSearchCriteriaDTO);

}
