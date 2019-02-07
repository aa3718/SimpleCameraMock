package ic.doc.camera;

public class Camera implements WriteListener {

  private boolean power;
  private Sensor sensor;
  private MemoryCard memoryCard;

  public Camera(Sensor sensorCnstr, MemoryCard memoryCardCnstr) {
    power = false;
    sensor = sensorCnstr;
    memoryCard = memoryCardCnstr;
  }

  public void pressShutter() {
    if(power == false) {
      return;
    }
  }

  public void powerOn() {
    power = true;
    sensor.powerUp();
  }

  public void powerOff() {
    power = false;
    sensor.powerDown();
  }

  @java.lang.Override
  public void writeComplete() {

  }
}

