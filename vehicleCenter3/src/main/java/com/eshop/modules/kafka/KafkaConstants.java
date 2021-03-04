package com.eshop.modules.kafka;

public class KafkaConstants {
	
    public static final String TOPIC_VEHICLE_INFO= "vehicleInfo";

    public static final String TOPIC_VEHICLE_ALARM = "vehicleAlarm";

    public static final String BOOTSTRAP_SERVERS = "localhost:9092";

    public static final String GROUP_ID_VEHICLE_INFO = "vehicle-data";

    public static final String VEHICLE_DATA_CONTAINER = "vehicleDataContainer1";
    
    public static final String ACKS_CONFIG = "1";
}
