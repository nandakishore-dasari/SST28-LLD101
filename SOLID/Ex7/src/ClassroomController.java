public class ClassroomController {
    private final DeviceRegistry reg;

    public ClassroomController(DeviceRegistry reg) { this.reg = reg; }

    public void startClass() {
        // Find devices by their specific capabilities/types
        Projector pj = reg.getFirstOf(Projector.class);
        pj.powerOn();
        pj.connectInput("HDMI-1");

        Dimmable lights = reg.getFirstOf(Dimmable.class);
        lights.setBrightness(60);

        ClimateControllable ac = reg.getFirstOf(ClimateControllable.class);
        ac.setTemperatureC(24);

        AttendanceScannerDevice scan = reg.getFirstOf(AttendanceScannerDevice.class);
        System.out.println("Attendance scanned: present=" + scan.scanAttendance());
    }

    public void endClass() {
        System.out.println("Shutdown sequence:");
        reg.getFirstOf(Projector.class).powerOff();
        reg.getFirstOf(LightsPanel.class).powerOff();
        reg.getFirstOf(AirConditioner.class).powerOff();
    }
}