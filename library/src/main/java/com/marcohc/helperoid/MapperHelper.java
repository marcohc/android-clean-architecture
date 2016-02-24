package com.marcohc.helperoid;

import org.modelmapper.ModelMapper;

/**
 * Singleton for mapping useful methods
 */
public class MapperHelper {

    private static MapperHelper instance;

    private ModelMapper modelMapper;

    private MapperHelper() {
        modelMapper = new ModelMapper();
    }

    // ************************************************************************************************************************************************************************
    // * Public methods
    // ************************************************************************************************************************************************************************

    public static MapperHelper getInstance() {
        if (instance == null) {
            instance = new MapperHelper();
        }
        return instance;
    }

    public <D> D map(Object source, Class<D> destinationType) {
        return modelMapper.map(source, destinationType);
    }

    /**
     * To modify configurations
     */
    public ModelMapper getModelMapper() {
        return modelMapper;
    }
}
