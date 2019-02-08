package ic.doc.camera;

import org.jmock.Expectations;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.junit.Rule;
import org.junit.Test;

public class CameraTest {

  @Rule public JUnitRuleMockery context = new JUnitRuleMockery();

  Sensor sensor = context.mock(Sensor.class);
  MemoryCard memoryCard = context.mock(MemoryCard.class);

  final Camera camera = new Camera(sensor, memoryCard);

  @Test
  public void switchingTheCameraOnPowersUpTheSensor() {
    context.checking(
        new Expectations() {
          {
            exactly(1).of(sensor).powerUp();
          }
        });
    camera.powerOn();
  }

  @Test
  public void switchingTheCameraOffPowersDownTheSensor() {
    context.checking(
        new Expectations() {
          {
            ignoring(sensor).powerUp();
            exactly(1).of(sensor).powerDown();
          }
        });
    camera.powerOn();
    camera.powerOff();
  }

  @Test
  public void pressingTheShutterWhenThePowerIsOffDoesNothing() {
    context.checking(
        new Expectations() {
          {
            never(sensor).readData();
            never(memoryCard).write(with(any(byte[].class)));
          }
        });
    camera.pressShutter();
  }

  @Test
  public void pressingShutterWhilePowerOnCopiesSensorDataToMemoryCard() {
    byte[] photo = new byte[8];
    context.checking(
        new Expectations() {
          {
            ignoring(sensor).powerUp();
            exactly(1).of(sensor).readData();
            will(returnValue(photo));
            exactly(1).of(memoryCard).write(photo);
          }
        });
    camera.powerOn();
    camera.pressShutter();
  }

  @Test
  public void switchingCameraOffDoesNotPowerDownSensorWhileDataBeingWritten() {
    context.checking(
        new Expectations() {
          {
            ignoring(sensor).powerUp();
            ignoring(memoryCard).write(with(any(byte[].class)));
            exactly(1).of(sensor).readData();
            never(sensor).powerDown();
          }
        });
    camera.powerOn();
    camera.pressShutter();
    camera.powerOff();
    camera.writeComplete();
  }

  @Test
  public void switchingCameraOffPowersDownSensorAfterDataIsWritten() {
    context.checking(
        new Expectations() {
          {
            ignoring(sensor).powerUp();
            ignoring(memoryCard).write(with(any(byte[].class)));
            exactly(1).of(sensor).readData();
            exactly(1).of(sensor).powerDown();
          }
        });
    camera.powerOn();
    camera.pressShutter();
    camera.writeComplete();
    camera.powerOff();
  }
}
