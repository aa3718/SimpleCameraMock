package ic.doc.camera;

public class Camera implements WriteListener {

  private boolean power;
  private final Sensor sensor;
  private final MemoryCard memoryCard;

  public Camera(Sensor sensor, MemoryCard memoryCard) {
    power = false;
    this.sensor = sensor;
    this.memoryCard = memoryCard;
  }

  public void pressShutter() {
    if(power) {
      memoryCard.write(sensor.readData());
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

  @Override
  public void writeComplete() {

  }
}

