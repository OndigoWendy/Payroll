package com.ondigo.payroll;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

    @Override
    public EntityModel<Employee> toModel(Employee employee) {

        return EntityModel.of(employee, //
                linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }
    @RestController
    class EmployeeController {

        private final EmployeeRepository repository;

        private final EmployeeModelAssembler assembler;

        EmployeeController(EmployeeRepository repository, EmployeeModelAssembler assembler) {

            this.repository = repository;
            this.assembler = assembler;
        }
}