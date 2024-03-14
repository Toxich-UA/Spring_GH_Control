#include <WiFiManager.h>
#include <ESPAsyncWebServer.h>
#include "ArduinoJson.h"

WiFiManager wifiManager;
AsyncWebServer server(80);

bool incline = true;
int initTemperature = 0;
int initHumidity = 0;

int fans = 0;
int lamps = 0;
int pump = 0;


void notFound(AsyncWebServerRequest *request) {
  request->send(404, "application/json", "{\"message\":\"Not found\"}");
}

StaticJsonDocument<768> doc;

void setup() {
  Serial.begin(115200);
  bool res = wifiManager.autoConnect("Node");

  server.on("/sensors", HTTP_GET, [](AsyncWebServerRequest *request) {
    doc["ip"] = WiFi.localIP();

    JsonObject sensors = doc.createNestedObject("sensors");

    JsonObject sensors_temperature = sensors.createNestedObject("temperature");

    JsonObject sensors_temperature_a = sensors_temperature.createNestedObject("a");
    sensors_temperature_a["val"] = getTemperature();
    sensors_temperature_a["change"] = 0;
    sensors_temperature_a["sensorName"] = "DH22_temperature";

    JsonObject sensors_temperature_b = sensors_temperature.createNestedObject("b");
    sensors_temperature_b["val"] = getTemperature();
    sensors_temperature_b["change"] = 0;
    sensors_temperature_b["sensorName"] = "OneWire_a";

    JsonObject sensors_temperature_c = sensors_temperature.createNestedObject("c");
    sensors_temperature_c["val"] = getTemperature();
    sensors_temperature_c["change"] = 0;
    sensors_temperature_c["sensorName"] = "OneWire_b";

    JsonObject sensors_temperature_d = sensors_temperature.createNestedObject("d");
    sensors_temperature_d["val"] = getTemperature();
    sensors_temperature_d["change"] = 0;
    sensors_temperature_d["sensorName"] = "OneWire_c";

    JsonObject sensors_temperature_e = sensors_temperature.createNestedObject("e");
    sensors_temperature_e["val"] = -128;
    sensors_temperature_e["change"] = 0;
    sensors_temperature_e["sensorName"] = "OneWire_d";

    JsonObject sensors_temperature_f = sensors_temperature.createNestedObject("f");
    sensors_temperature_f["val"] = -128;
    sensors_temperature_f["change"] = 0;
    sensors_temperature_f["sensorName"] = "OneWire_e";

    JsonObject sensors_humidity = sensors.createNestedObject("humidity");

    JsonObject sensors_humidity_a = sensors_humidity.createNestedObject("a");
    sensors_humidity_a["val"] = getHumidity();
    sensors_humidity_a["change"] = 0;
    sensors_humidity_a["sensorName"] = "DH22_Humidity";

    JsonObject sensors_humidity_b = sensors_humidity.createNestedObject("b");
    sensors_humidity_b["val"] = getHumidity();
    sensors_humidity_b["change"] = 0;
    sensors_humidity_b["sensorName"] = "Sensor_a";

    JsonObject sensors_humidity_c = sensors_humidity.createNestedObject("c");
    sensors_humidity_c["val"] = getHumidity();
    sensors_humidity_c["change"] = 0;
    sensors_humidity_c["sensorName"] = "Sensor_b";

    JsonObject sensors_humidity_d = sensors_humidity.createNestedObject("d");
    sensors_humidity_d["val"] = -128;
    sensors_humidity_d["change"] = 0;
    sensors_humidity_d["sensorName"] = "Sensor_c";

    JsonObject sensors_humidity_e = sensors_humidity.createNestedObject("e");
    sensors_humidity_e["val"] = -128;
    sensors_humidity_e["change"] = 0;
    sensors_humidity_e["sensorName"] = "Sensor_d";
    
    JsonObject sensors_humidity_f = sensors_humidity.createNestedObject("f");
    sensors_humidity_f["val"] = -128;
    sensors_humidity_f["change"] = 0;
    sensors_humidity_f["sensorName"] = "Sensor_e";

    JsonObject status = doc.createNestedObject("status");
    status["fans"] = fans;
    status["pump"] = pump;
    status["lamps"] = lamps;

    String response;

    serializeJson(doc, response);

    request->send(200, "application/json", response);
  });

  server.onNotFound(notFound);

  server.on("/peripherals", HTTP_GET, [](AsyncWebServerRequest *request) {
    JsonDocument doc;
    doc["fans"] = getPheripheralStatus("fans");
    doc["pump"] = getPheripheralStatus("pump");
    doc["lamps"] = getPheripheralStatus("lamps");
    String response;
    doc.shrinkToFit();  // optional
    serializeJson(doc, response);
    request->send(200, "application/json", response);
  });

  server.on("/setPeripheralStatus", HTTP_GET, [](AsyncWebServerRequest *request) {
    if (request->hasParam("peripheral") && request->hasParam("status")) {
      int status = request->getParam("status")->value().toInt();
      if (status == 0 || status == 1) {
        setPheripheralStatus(request->getParam("peripheral")->value(), status);
      } else request->send(404, "application/json", "{\"message\":\"Wrong status argumend. Shuold be 0 or 1\"}");
      request->send(200, "application/json", "{\"message\":\"Done\"}");
    } else {
      request->send(404, "application/json", "{\"message\":\"No parameters provided\"}");
    }
  });


  //Start Server
  server.begin();
}

void loop() {
}


int getPheripheralStatus(String pName) {
  if (pName == "fans") return fans;
  else if (pName == "pump") return pump;
  else if (pName == "lamps") return lamps;
  else return -1;
}

void setPheripheralStatus(String pName, int status) {
  if (pName == "fans") fans = status;
  else if (pName == "pump") pump = status;
  else if (pName == "lamps") lamps = status;
}

//For test purposes, fake data while sensors arent connected
int getTemperature() {
  if (initTemperature < 30 && incline) {
    initTemperature += 1;
  } else {
    incline = false;
  }
  if (initTemperature > 0 && incline == false) {
    initTemperature -= 1;
  } else {
    incline = true;
  }
  return initTemperature;
}
int getHumidity() {
  if (initHumidity < 30 && incline) {
    initHumidity += 1;
  } else {
    incline = false;
  }
  if (initHumidity > 0 && incline == false) {
    initHumidity -= 1;
  } else {
    incline = true;
  }
  return initHumidity;
}

// 3byrxd2TvHAZUuGKzJ