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
    // not implemented
  }

  public void powerOn() {
    power = true;
    sensor.powerUp();
    // not implemented
  }

  public void powerOff() {
    // not implemented
  }

  @java.lang.Override
  public void writeComplete() {

  }
}

