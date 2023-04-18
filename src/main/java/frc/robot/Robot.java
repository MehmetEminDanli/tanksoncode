// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SPI;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the manifest
 * file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
  double leftSlow = 0.24;
  double rightSlow = -0.24;
  double rotateSpeed = 0.35;
  double rotateSpeedSlow = 0.25;

  ADXRS450_Gyro gyro = new ADXRS450_Gyro();
  //
  AHRS gyrom = new AHRS(SPI.Port.kMXP);
  public static DifferentialDrive finalDrive;
  private final Relay m_relay = new Relay(0);

  private final Joystick m_stick = new Joystick(0);
private final XboxController koll=new XboxController(1);
  private final Timer m_timer2 = new Timer();
  private final Timer m_timer = new Timer();
  WPI_VictorSPX saga = new WPI_VictorSPX(1);
  WPI_VictorSPX sago = new WPI_VictorSPX(2);
  WPI_VictorSPX sola = new WPI_VictorSPX(3);
  WPI_VictorSPX solo = new WPI_VictorSPX(4);
  

  MotorControllerGroup sag = new MotorControllerGroup(sago, saga);
  MotorControllerGroup sol = new MotorControllerGroup(solo, sola);

  DifferentialDrive siemens = new DifferentialDrive(sag, sol);

  Solenoid intakeyukari = new Solenoid(PneumaticsModuleType.CTREPCM, 2);
  Solenoid intakeasa = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
  Compressor komprasor = new Compressor(1, PneumaticsModuleType.CTREPCM);

  DigitalInput enalt = new DigitalInput(9);
  DigitalInput orta = new DigitalInput(6);
  DigitalInput enust = new DigitalInput(5);
  WPI_TalonSRX denememotor = new WPI_TalonSRX(1);
  TalonFX falcon=new TalonFX(9);

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_relay.set(Relay.Value.kOn);
    sag.set(0);
    sola.set(0);

    // sag.setInverted(true);

  }

  /** This function is run once each time the robot enters autonomous mode. */
  @Override
  public void autonomousInit() {
    m_relay.set(Relay.Value.kOn);
    m_timer2.reset();
    m_timer2.start();
    gyro.reset();

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    if (m_timer2.get() > 0 & m_timer2.get() < 1.75) {

      sol.set(-0.25);
      sag.set(0.27);
    }

    if (m_timer2.get() > 6 & m_timer2.get() < 7.80) {
      System.out.print("geri");
      sol.set(0.25);
      sag.set(-0.26);
    }
    gyro.reset();

    int ontirmanma = Math.round(gyrom.getYaw());
    if (ontirmanma > 7 & ontirmanma < 30) {
      m_timer.reset();
      m_timer.start();
      if (m_timer2.get() < 0.5) {
        sol.set(0.29);
        sag.set(-0.29);
      }
      System.out.println("1.öntırmanma" + ontirmanma);

      sol.set(-0.29);
      sag.set(0.29);

    }
    if (ontirmanma > 3 & ontirmanma < 7) {
      m_timer.reset();
      m_timer.start();
      if (m_timer2.get() < 0.5) {
        sol.set(0.29);
        sag.set(-0.29);
      }
      System.out.println("2.öntırmanma" + ontirmanma);

      sol.set(-0.23);
      sag.set(0.23);

    }

    if (ontirmanma < -7 & ontirmanma > -30) {
      m_timer.reset();
      m_timer.start();
      if (m_timer2.get() < 0.05) {
        sol.set(-0.29);
        sag.set(0.29);
      }
      System.out.println("3.öntırmanma" + ontirmanma);

      sol.set(0.29);
      sag.set(-0.29);

    }
    if (ontirmanma < -3 & ontirmanma > -7) {
      m_timer.reset();
      m_timer.start();
      if (m_timer2.get() < 0.05) {
        sol.set(-0.29);
        sag.set(0.29);
      }
      System.out.println("4.öntırmanma" + ontirmanma);

      sol.set(0.23);
      sag.set(-0.23);

    }
    /*
     * if (m_timer2.get() > 3 & m_timer2.get() < 6) {
     * kol.set(0);
     * 
     * System.out.println(Math.round(gyro.getAngle()));
     * if (gyro.getAngle() > -100 & gyro.getAngle() < 300 & isAutonomous()) {
     * 
     * if (gyro.getAngle() < 180) {
     * while (gyro.getAngle() < 175 & isAutonomous()) {
     * sol.set(0.25);
     * sag.set(0.25);
     * 
     * }
     * while (gyro.getAngle() > 85 && gyro.getAngle() < 90 & isAutonomous()) {
     * sol.set(0.10);
     * sag.set(0.10);
     * }
     * }
     * 
     * if (gyro.getAngle() > 180) {
     * while (gyro.getAngle() > 175 & isAutonomous()) {
     * sol.set(-0.25);
     * sag.set(-0.25);
     * System.out.println(Math.round(gyro.getAngle()));
     * }
     * }
     * }
     * }
     */

  }

  @Override
  public void teleopInit() {
    sag.set(0.5);
    sol.set(1);
    gyro.reset();
    gyrom.reset();
  }

  /** This function is called periodically during teleoperated mode. */
  @Override
  public void teleopPeriodic() {
    sag.set(0);
    sol.set(0);
    siemens.arcadeDrive(m_stick.getX(), m_stick.getY());
    int ontirmanma = Math.round(gyrom.getYaw());
    int yukseklik = Math.round(gyrom.getPitch() + 88);
if(koll.getRawButton(4)==true){
  falcon.set(ControlMode.PercentOutput,1);
}
if(koll.getRawButton(1)==true){
  falcon.set(ControlMode.PercentOutput,-1);
}
    if (m_stick.getRawButton(7) == true) {
      if (ontirmanma > 1) {
        gyrom.reset();
      }
      if (ontirmanma < -1) {
        gyrom.reset();
      }
    }

    // System.out.println("1.öntırmanma" + ontirmanma);

    if (m_stick.getRawButton(8) == true & yukseklik > 1) {
      // System.out.println(Math.round(gyrom.getPitch()+88)+"yatay açı");

      if (ontirmanma > 7 & ontirmanma < 30) {
        m_timer2.reset();
        m_timer2.start();
        if (m_timer2.get() < 0.5) {
          sol.set(0.29);
          sag.set(-0.29);
        }
        System.out.println("1.öntırmanma" + ontirmanma);

        sol.set(-0.29);
        sag.set(0.29);

      }
      if (ontirmanma > 3 & ontirmanma < 7) {
        m_timer2.reset();
        m_timer2.start();
        if (m_timer2.get() < 0.5) {
          sol.set(0.29);
          sag.set(-0.29);
        }
        System.out.println("2.öntırmanma" + ontirmanma);

        sol.set(-0.23);
        sag.set(0.23);

      }

      if (ontirmanma < -7 & ontirmanma > -30) {
        m_timer2.reset();
        m_timer2.start();
        if (m_timer2.get() < 0.05) {
          sol.set(-0.29);
          sag.set(0.29);
        }
        System.out.println("3.öntırmanma" + ontirmanma);

        sol.set(0.29);
        sag.set(-0.29);

      }
      if (ontirmanma < -3 & ontirmanma > -7) {
        m_timer2.reset();
        m_timer2.start();
        if (m_timer2.get() < 0.05) {
          sol.set(-0.29);
          sag.set(0.29);
        }
        System.out.println("4.öntırmanma" + ontirmanma);

        sol.set(0.23);
        sag.set(-0.23);

      }

    }

    /* sensor deneme başlangıç */

    /* arabayı ortaya alma dotnet new mvc */
    if (m_stick.getRawButton(9) == true) {
      if (m_stick.getRawButton(9) == true&orta.get() == false ) {
        denememotor.set(0);
      }
      while(orta.get() == true& enalt.get()==false){
        denememotor.set(-0.25);
       
       }
       while(orta.get() == true& enust.get()==false){
        denememotor.set(0.25);
       
       }
    }
    /* arabayı üste alma */
      
     while(enust.get() == false&m_stick.getRawButton(10) == true){
      denememotor.set(0.25);
       }

    /* arabayı enalt alma */

     while(enalt.get() == false&m_stick.getRawButton(11) == true){
      denememotor.set(-0.25);      
    }
    
    if (m_stick.getRawButton(3) == true ) {
      denememotor.set(-0.25);
    }
    if (m_stick.getRawButton(4) == true) {
      denememotor.set(0.25);
    }
    
   
  }

  /** This function is called once each time the robot enters test mode. */
  @Override
  public void testInit() {
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }
}
