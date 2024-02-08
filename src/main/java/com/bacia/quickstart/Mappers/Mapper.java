package com.bacia.quickstart.Mappers;

import org.springframework.stereotype.Component;

@Component
public interface Mapper<A,B> {
    B mapEntityToDto(A a);
    A mapDtoToEntity(B b);
}
