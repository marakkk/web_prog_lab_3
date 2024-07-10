package lab3.mbean;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import lab3.entity.Shot;

public class MBeanServerSetup {
    public static void main(String[] args) {
        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

            ShotCount shotCount = new ShotCount();
            ObjectName shotCountName = new ObjectName("lab3.mbean:type=ShotCount");
            mbs.registerMBean(shotCount, shotCountName);

            List<Shot> shots = new ArrayList<>();
            ShotArea shotArea = new ShotArea(shots);
            ObjectName shotAreaName = new ObjectName("lab3.mbean:type=ShotArea");
            mbs.registerMBean(shotArea, shotAreaName);

            System.out.println("MBeans registered successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
