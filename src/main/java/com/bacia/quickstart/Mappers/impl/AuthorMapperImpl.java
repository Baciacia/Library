package com.bacia.quickstart.Mappers.impl;

import com.bacia.quickstart.Domain.DTO.AuthorDto;
import com.bacia.quickstart.Domain.Entity.AuthorEntity;
import com.bacia.quickstart.Mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperImpl implements Mapper<AuthorEntity, AuthorDto> {
    private final ModelMapper modelMapper;
    @Autowired
    public AuthorMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public AuthorDto mapEntityToDto(AuthorEntity authorEntity) {
        return modelMapper.map(authorEntity, AuthorDto.class);
    }

    @Override
    public AuthorEntity mapDtoToEntity(AuthorDto authorDto) {
        return modelMapper.map(authorDto, AuthorEntity.class);
    }
}
