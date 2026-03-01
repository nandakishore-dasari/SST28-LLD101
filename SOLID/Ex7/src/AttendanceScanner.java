public class AttendanceScanner implements Powerable, AttendanceScannerDevice {
    @Override public void powerOn() { }
    @Override public void powerOff() { }
    @Override public int scanAttendance() { return 3; }
}