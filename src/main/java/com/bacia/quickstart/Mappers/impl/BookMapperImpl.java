package com.bacia.quickstart.Mappers.impl;

import com.bacia.quickstart.Domain.DTO.BookDto;
import com.bacia.quickstart.Domain.Entity.BookEntity;
import com.bacia.quickstart.Mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements Mapper<BookEntity, BookDto> {

    private ModelMapper modelMapper;

    @Autowired
    public BookMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public BookDto mapEntityToDto(BookEntity bookEntity) {
        return modelMapper.map(bookEntity, BookDto.class);
    }
    @Override
    public BookEntity mapDtoToEntity(BookDto bookDto) {
        return modelMapper.map(bookDto, BookEntity.class);
    }
}
