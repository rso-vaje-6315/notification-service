package si.rso.notifications.mappers;

import si.rso.notifications.lib.Sample;
import si.rso.notifications.persistence.SampleEntity;

public class SampleMapper {
    
    public static Sample fromEntity(SampleEntity entity) {
        return new Sample();
    }
    
}