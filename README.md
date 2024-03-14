Info
=====
This project offer greenhouse control and monitoring functionality’s.
There are using raspberry pi (could be different pc) as main server,
and NodeMcu (or similar board with build in Wi-Fi) with set of sensors like: DS18B20, DH22, and capacitive soil moisture
sensor.

The `Node` (ESP32 board) will serve the web server to get data from sensors and provide it to the `server` (raspberry
pi) wia http response.

Usage
=====
- [ ] Get nodes
- [ ] 
- [ ] 
- [ ] 
- [ ] 
- [ ] 
- [ ] 

Node API
========
The GET request to

```http
    http://192.168.xxx.xxx/sensors
```

will return a data of all connected node with the sensor value and peripherals status

```json
{
    "ip": "192.168.xxx.xxx",
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
```

The -128, for sensors data and -1, for peripherals status is a default no data value.

### Request to

```http
    http://192.168.0.xxx/peripherals
```

will return current status for all peripherals. The value can be between [ -1:1 ], where:

* `-1 = no data`,
* `0 = OFF`,
* `1 = ON`

```json
{
    "fans" : 0,
    "pump" : 1,
    "lamps": 0
}
```

### Request to

```http
    http://192.168.xxx.xxx/setPeripheralStatus?peripheral={pName},status={1}
```

will set status of peripheral `{pName}` (fans, pump, lamps) to On (1) or Off (0)
and return it's current status (0 / 1)



Database
========
Server automatically makes request to all greenhouses every X minutes and add it data to the "Statistic" database.

Statistic
---------

```
ID | Greenhouse_ID | Air_temperature | Air_humidity | Soil_temperature | Soil_humidity
```

Greenhouse
----------

```
ID | IP | Greenhouse_ID | Comment
```
