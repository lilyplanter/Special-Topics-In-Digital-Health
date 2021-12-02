// Force detector Project 
// Illumination directly propotional to Force value. 
// Green 900 - 1000
// Blue 600 -900
// Red 300 - 600 
// White 60 - 300

//int red_light_pin= 4;
int green_light_pin = 3;
int blue_light_pin = 2;

int fsrPin = A0;     // the FSR and 10K pulldown are connected to a0
int fsrReading;     // the analog reading from the FSR resistor divider
 
 
  Serial.print("Analog reading = ");
  Serial.print(fsrReading);     // the raw analog reading
 
void setup() {
 // pinMode(red_light_pin, OUTPUT);
  pinMode(green_light_pin, OUTPUT);
  pinMode(blue_light_pin, OUTPUT);

  Serial.begin(9600);   

  while (!Serial) {
   ; // wait for serial port to connect. Needed for native USB port only
 }
  
 Serial.println("ASCII Table ~ Character Map");

 

} 
void loop() {

Serial.write(fsrPin);
  
 Serial.print(", dec: ");
 Serial.print(fsrPin);
  
   int fsrReading = analogRead(fsrPin);
Serial.println(fsrReading);
 if (fsrReading < 100) {
      //RGB_color(255, 255, 255); // White
      digitalWrite(green_light_pin, HIGH);
              digitalWrite(blue_light_pin, LOW);   
      delay(500);
  } else if (fsrReading < 500) {
    digitalWrite(blue_light_pin, HIGH);
        digitalWrite(green_light_pin, LOW);

    RGB_color(255, 0, 0); // Red
    delay(500);
  } 
  else {
    RGB_color(0, 0, 255); // Blue
    delay(500);
  }
  
  } else {
    RGB_color(0, 255, 0); // Green
    delay(500);
  
 
  RGB_color(255, 255, 125); // Raspberry
  delay(1000);
  RGB_color(0, 255, 255); // Cyan
  delay(1000);
  RGB_color(255, 0, 255); // Magenta
  delay(1000);
  RGB_color(255, 255, 0); // Yellow
  delay(1000);

}
void RGB_color(int red_light_value, int green_light_value, int blue_light_value)
 {
  analogWrite(red_light_pin, red_light_value);
  analogWrite(green_light_pin, green_light_value);
  analogWrite(blue_light_pin, blue_light_value);
}

/* FSR simple testing sketch.  <br>Connect one end of FSR to power, the other end to Analog 0.
Then connect one end of a 10K resistor from Analog 0 to ground 
*/
