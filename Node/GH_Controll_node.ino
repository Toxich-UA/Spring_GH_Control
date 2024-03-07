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
  wifiManager.setSTAStaticIPConfig(IPAddress(192, 168, 1, 90), IPAddress(192, 168, 1, 1), IPAddress(255, 255, 255, 0));  // optional DNS 4th argument
  bool res = wifiManager.autoConnect("Node");

  server.on("/sensors", HTTP_GET, [](AsyncWebServerRequest *request) {
    doc["ip"] = WiFi.localIP();

    JsonObject sensors = doc.createNestedObject("sensors");

    JsonObject sensors_temperature = sensors.createNestedObject("temperature");

    JsonObject sensors_temperature_DH22_temperature = sensors_temperature.createNestedObject("DH22_temperature");
    sensors_temperature_DH22_temperature["val"] = getTemperature();
    sensors_temperature_DH22_temperature["change"] = 0;

    JsonObject sensors_temperature_OneWire_a = sensors_temperature.createNestedObject("OneWire_a");
    sensors_temperature_OneWire_a["val"] = getTemperature();
    sensors_temperature_OneWire_a["change"] = 0;

    JsonObject sensors_temperature_OneWire_b = sensors_temperature.createNestedObject("OneWire_b");
    sensors_temperature_OneWire_b["val"] = getTemperature();
    sensors_temperature_OneWire_b["change"] = 0;

    JsonObject sensors_temperature_OneWire_c = sensors_temperature.createNestedObject("OneWire_c");
    sensors_temperature_OneWire_c["val"] = getTemperature();
    sensors_temperature_OneWire_c["change"] = 0;

    JsonObject sensors_temperature_OneWire_d = sensors_temperature.createNestedObject("OneWire_d");
    sensors_temperature_OneWire_d["val"] = -128;
    sensors_temperature_OneWire_d["change"] = 0;

    JsonObject sensors_temperature_OneWire_e = sensors_temperature.createNestedObject("OneWire_e");
    sensors_temperature_OneWire_e["val"] = -128;
    sensors_temperature_OneWire_e["change"] = 0;

    JsonObject sensors_humidity = sensors.createNestedObject("humidity");

    JsonObject sensors_humidity_DH22_humidity = sensors_humidity.createNestedObject("DH22_humidity");
    sensors_humidity_DH22_humidity["val"] = getHumidity();
    sensors_humidity_DH22_humidity["change"] = 0;

    JsonObject sensors_humidity_a = sensors_humidity.createNestedObject("a");
    sensors_humidity_a["val"] = getHumidity();
    sensors_humidity_a["change"] = 0;

    JsonObject sensors_humidity_b = sensors_humidity.createNestedObject("b");
    sensors_humidity_b["val"] = getHumidity();
    sensors_humidity_b["change"] = 0;

    JsonObject sensors_humidity_c = sensors_humidity.createNestedObject("c");
    sensors_humidity_c["val"] = -128;
    sensors_humidity_c["change"] = 0;

    JsonObject sensors_humidity_d = sensors_humidity.createNestedObject("d");
    sensors_humidity_d["val"] = -128;
    sensors_humidity_d["change"] = 0;

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
    initTemperature = 0;
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
    initHumidity = 0;
  } else {
    incline = true;
  }
  return initHumidity;
}

// 3byrxd2TvHAZUuGKzJ