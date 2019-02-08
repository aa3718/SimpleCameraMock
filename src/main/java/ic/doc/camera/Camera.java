package ic.doc.camera;

public class Camera implements WriteListener {

  private boolean power;
  private final Sensor sensor;
  private final MemoryCard memoryCard;
  private boolean currentlyWritting = false;

  public Camera(Sensor sensor, MemoryCard memoryCard) {
    power = false;
    this.sensor = sensor;
    this.memoryCard = memoryCard;
  }

  public void pressShutter() {
    if (power) {
      currentlyWritting = true;
      memoryCard.write(sensor.readData());
    }
  }

  public void powerOn() {
    power = true;
    sensor.powerUp();
  }

  public void powerOff() {
    power = false;
    if (!currentlyWritting) {
      sensor.powerDown();
    }
  }

  @Override
  public void writeComplete() {
    currentlyWritting = false;
  }
}
