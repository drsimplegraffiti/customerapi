package com.abcode.customerapi.service.impl;


import com.abcode.customerapi.dto.CustomerDTO;
import com.abcode.customerapi.dto.CustomerSearchCriteriaDTO;
import com.abcode.customerapi.entity.Customer;
import com.abcode.customerapi.exception.ResourceAlreadyExistException;
import com.abcode.customerapi.exception.ResourceNotFoundException;
import com.abcode.customerapi.mappers.CustomerMapper;
import com.abcode.customerapi.repository.CustomerRepository;
import com.abcode.customerapi.service.CustomerService;
import com.abcode.customerapi.utils.SortItem;
import com.abcode.customerapi.utils.Utils;
import com.abcode.customerapi.utils.annotation.ExecutionTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;


    @ExecutionTime
    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {

        Customer recordFromDB = customerRepository.findByEmail(customerDTO.getEmail());

        if (recordFromDB != null) {
            throw new ResourceAlreadyExistException("Customer", "email", customerDTO.getEmail());
        }

        Customer recordToBeSaved = customerMapper.customerDTOToCustomer(customerDTO);

        Customer savedRecord = customerRepository.save(recordToBeSaved);

        return customerMapper.customerToCustomerDTO(savedRecord);
    }


    @ExecutionTime
    @Override
    public CustomerDTO getCustomerById(Long id) {

        Customer recordFromDB = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        return customerMapper.customerToCustomerDTO(recordFromDB);
    }


    @ExecutionTime
    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {

        Customer recordFromDB = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        // just to be safe that the object does not have another id
        customerDTO.setId(recordFromDB.getId());

        Customer recordToBeSaved = customerMapper.customerDTOToCustomer(customerDTO);

        Customer savedRecord = customerRepository.save(recordToBeSaved);

        return customerMapper.customerToCustomerDTO(savedRecord);
    }


    @ExecutionTime
    @Override
    public void deleteCustomer(Long id) {

        Customer recordFromDB = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));

        customerRepository.delete(recordFromDB);
    }

    @Override
    public Page<CustomerDTO> getAllCustomersUsingPagination(
            CustomerSearchCriteriaDTO customerSearchCriteriaDTO) {

        Integer page = customerSearchCriteriaDTO.getPage();
        Integer size = customerSearchCriteriaDTO.getSize();
        List<SortItem> sortList = customerSearchCriteriaDTO.getSortList();

        // this pageable will be used for the pagination.
        Pageable pageable = Utils.createPageableBasedOnPageAndSizeAndSorting(sortList, page, size);

        Page<Customer> recordsFromDb = customerRepository.getAllCustomersUsingPagination(customerSearchCriteriaDTO, pageable);

        List<CustomerDTO> result = customerMapper.customerListToCustomerDTOList(recordsFromDb.getContent());

        return new PageImpl<>(result, pageable, recordsFromDb.getTotalElements());

    }
}
