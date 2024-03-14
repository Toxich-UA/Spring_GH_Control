package com.springghcontrol.util;


public final class Constant {

    private Constant() {
    // No need to instantiate the class, we can hide its constructor
  }

  //Temperature
  public static final String TEMPERATURE_DH22 = "DH22_temperature";
  public static final String TEMPERATURE_ONEWIRE_A = "OneWire_a";
  public static final String TEMPERATURE_ONEWIRE_B = "OneWire_b";
  public static final String TEMPERATURE_ONEWIRE_C = "OneWire_c";
  public static final String TEMPERATURE_ONEWIRE_D = "OneWire_d";
  public static final String TEMPERATURE_ONEWIRE_E = "OneWire_e";

  //Humidity
  public static final String HUMIDITY_DH22 = "DH22_humidity";
  public static final String HUMIDITY_SENSOR_A = "a";
  public static final String HUMIDITY_SENSOR_B = "b";
  public static final String HUMIDITY_SENSOR_C = "c";
  public static final String HUMIDITY_SENSOR_D = "d";


    public static final String EMPTY_JSON = """
            {
                "ip": "",
                "sensors": {
                    "temperature": {
                        "a": {
                            "val": -128,
                            "change": 0
                        },
                        "b": {
                            "val": -128,
                            "change": 0
                        },
                        "c": {
                            "val": -128,
                            "change": 0
                        },
                        "d": {
                            "val": -128,
                            "change": 0
                        },
                        "e": {
                            "val": -128,
                            "change": 0
                        },
                        "f": {
                            "val": -128,
                            "change": 0
                        }
                    },
                    "humidity":{
                        "a": {
                            "val": -128,
                            "change": 0
                        },
                        "b": {
                            "val": -128,
                            "change": 0
                        },
                        "c": {
                            "val": -128,
                            "change": 0
                        },
                        "d": {
                            "val": -128,
                            "change": 0
                        },
                        "e": {
                            "val": -128,
                            "change": 0
                        }
                      
                    }
                },
                "status":{
                    "fans": -1,
                    "pump": -1,
                    "lamps": -1
                }
            }
            """;

}
