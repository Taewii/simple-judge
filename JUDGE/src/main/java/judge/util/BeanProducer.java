package judge.util;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;

public class BeanProducer {

    @Produces
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
