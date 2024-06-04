package com.maverickstube.maverickshub.utils;

import org.modelmapper.ModelMapper;

public enum MapperSingleton {
    MAPPER;

    private final ModelMapper modelMapper;

    MapperSingleton() {
        this.modelMapper = new ModelMapper();
    }

    public <T> T map(Object from, Class<T> to) {
        return modelMapper.map(from, to);
    }
}
