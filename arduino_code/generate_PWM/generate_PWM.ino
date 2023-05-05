/* generate_PWM.ino as of 5/2/2023
the following code is used in the Cal Poly EMPS Vibration Table senior project. this code is uploaded to the Arduino Mega 
and interfaces the UI and vibration table. 

inputs from UI:
start signal

outputs to vibe table:
vibration schedule

*/
// Pin number for PWM output
const int pwmPin = 2;

// Variables for timing
unsigned long previousMillis = 0; // Stores the previous time
unsigned long intervalOn = 5000; // Default interval for ON time in milliseconds (5 seconds)
unsigned long intervalOff = 1000; // Default interval for OFF time in milliseconds (2 seconds)
int dutyCycle = 90; // Default Duty cycle in percentage (80%)
boolean flagPWM = false; 
char incomingChar;

// flag to start signal

// Function to generate PWM signal
void generatePWM(unsigned long onTime, unsigned long offTime, int duty) {
  unsigned long currentMillis = millis(); // Get the current time
  unsigned long elapsedMillis = currentMillis - previousMillis; // Calculate elapsed time since last update

  // Check if it's time to turn ON the PWM signal
  if (elapsedMillis <= onTime && !digitalRead(pwmPin)) {
    // Set PWM signal ON with specified duty cycle
    analogWrite(pwmPin, duty * 255 / 100); // Convert duty cycle percentage to PWM value
  }

  // Check if it's time to turn OFF the PWM signal
  if (elapsedMillis > onTime && digitalRead(pwmPin)) {
    // Set PWM signal OFF
    analogWrite(pwmPin, 0); // 0% duty cycle (fully OFF)
  }

  // Check if it's time to update the PWM timing
  if (elapsedMillis >= onTime + offTime) {
    // Update the previousMillis
    previousMillis = currentMillis;
    intervalOff = offTime; // this line is probably breaking functionality. check later
  }
}

// function to build schedules
void buildSchedule(int intensity, unsigned long totalDuration, unsigned long onTime, unsigned long offTime, int duty){
  //to do: call generatePWM() 
}

// use this section for initializing code
void setup() {
  // Set the PWM pin as an OUTPUT
  pinMode(pwmPin, OUTPUT);
  pinMode(12,OUTPUT);
  pinMode(11,OUTPUT);
  Serial.begin(9600);

}

// this section loops indefinitely
void loop() {
  // check if the arduino is receiving serial data  
  if (Serial.available() > 0) {
    // assign the serial data to a variable
    incomingChar = Serial.read();
    
    if (incomingChar == 's'){
        // Turn on PWM signaling
        flagPWM = true;   
    }
    
    else {
        // Turn off the PWM signaling
        flagPWM = false;
    }   

  } 


  if (flagPWM) {
    generatePWM(intervalOn, intervalOff, dutyCycle);
    digitalWrite(11,HIGH);
    digitalWrite(12,LOW);
  }

  else if (flagPWM == false) {
    generatePWM(intervalOn, intervalOff, 0);
    digitalWrite(11,LOW);
    digitalWrite(12,HIGH);
  }

  

}