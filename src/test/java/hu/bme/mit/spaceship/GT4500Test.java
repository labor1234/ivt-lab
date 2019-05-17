package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;
  private TorpedoStore primary;
  private TorpedoStore secondary;

  @BeforeEach
  public void init(){
    primary=mock(TorpedoStore.class);
    secondary=mock(TorpedoStore.class);
    this.ship = new GT4500(primary,secondary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    verify(primary,times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);

  }


@Test
public void Testsecondaryfire(){
  when(primary.isEmpty()).thenReturn(true);
  when(secondary.isEmpty()).thenReturn(false);
  boolean result= ship.fireTorpedo(FiringMode.SINGLE);
  verify(secondary,times(1)).fire(1);
  verify(primary,times(0)).fire(1);
}

@Test
public void Testprimaryfire(){
  when(primary.isEmpty()).thenReturn(false);
  when(secondary.isEmpty()).thenReturn(true);
  boolean result= ship.fireTorpedo(FiringMode.SINGLE);
  verify(primary,times(1)).fire(1);
  verify(secondary,times(0)).fire(1);
}

@Test
public void TestfireAllTorpedo(){
  when(primary.isEmpty()).thenReturn(false);
  when(secondary.isEmpty()).thenReturn(false);
  boolean result= ship.fireTorpedo(FiringMode.ALL);
  verify(secondary,times(1)).fire(1);
  verify(primary,times(1)).fire(1);
}

@Test
public void TestfireAllTorpedo2(){
  when(primary.isEmpty()).thenReturn(true);
  when(secondary.isEmpty()).thenReturn(true);
  boolean result= ship.fireTorpedo(FiringMode.ALL);
  verify(secondary,times(0)).fire(1);
  verify(primary,times(0)).fire(1);
}



}
