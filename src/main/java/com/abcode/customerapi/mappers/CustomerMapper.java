package com.abcode.customerapi.mappers;

import com.abcode.customerapi.dto.CustomerDTO;
import com.abcode.customerapi.dto.CustomerRequestDTO;
import com.abcode.customerapi.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer customerDTOToCustomer(CustomerDTO customerDTO);

    CustomerDTO customerToCustomerDTO(Customer customer);

    List<Customer> customerDTOListToCustomerList(List<CustomerDTO> customerDTOList);

    List<CustomerDTO> customerListToCustomerDTOList(List<Customer> customerList);

    CustomerDTO customerRequestDTOToCustomerDTO(CustomerRequestDTO customerRequestDTO);

}
