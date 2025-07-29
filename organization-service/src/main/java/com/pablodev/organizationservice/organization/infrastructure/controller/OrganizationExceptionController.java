package com.pablodev.organizationservice.organization.infrastructure.controller;

import com.pablodev.organizationservice.organization.domain.exception.OrganizationAlreadyExistsException;
import com.pablodev.organizationservice.organization.domain.exception.OrganizationDoesNotExistException;
import com.pablodev.organizationservice.organization.domain.exception.OrganizationIllegalArgumentException;
import com.pablodev.organizationservice.organization.domain.exception.UnknownOrganizationTypeException;
import com.pablodev.shared.domain.InvalidIdentifierException;
import com.pablodev.shared.infrastructure.controller.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrganizationExceptionController {

    @ExceptionHandler(OrganizationDoesNotExistException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(OrganizationDoesNotExistException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler({
            OrganizationAlreadyExistsException.class,
            OrganizationIllegalArgumentException.class,
            UnknownOrganizationTypeException.class,
            InvalidIdentifierException.class,
    })
    public ResponseEntity<ErrorResponse> handleBadRequest(RuntimeException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

}
