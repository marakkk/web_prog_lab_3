package lab3.beans;

import lab3.database.DatabaseHelper;
import lab3.entity.Shot;
import lab3.mbean.ShotCount;
import lab3.mbean.ShotArea;
import lab3.utils.AreaHitChecker;
import lab3.utils.Validator;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.management.*;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;

@ManagedBean(name = "shotsBean")
@SessionScoped
public class ShotsBean implements Serializable {

    private Shot shot;

    private List<Shot> shotsList;
    private int timezone;
    private ShotCount shotCountMBean;
    private ShotArea shotAreaMBean;

    @PostConstruct
    public void init() {

        shot = new Shot();
        shotsList = new LinkedList<>();
        shotCountMBean = new ShotCount();
        shotAreaMBean = new ShotArea(shotsList);

        try {
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName shotCountName = new ObjectName("lab3.mbean:type=ShotCount");
            mbs.registerMBean(shotCountMBean, shotCountName);

            ObjectName shotAreaName = new ObjectName("lab3.mbean:type=ShotArea");
            mbs.registerMBean(shotAreaMBean, shotAreaName);

            shotCountMBean.addNotificationListener(new NotificationListener() {
                @Override
                public void handleNotification(Notification notification, Object handback) {
                    System.out.println("Received notification: " + notification.getMessage());
                }
            }, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void add() {
        LocalDateTime startTime = LocalDateTime.now(ZoneOffset.UTC);
        if (Validator.isValid(shot)) {
            shot.setStatus(AreaHitChecker.isHit(shot));
            shot.setCurrentTime(startTime.minusMinutes(getTimezone()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            shot.setScriptTime(Math.round(LocalDateTime.now().minusNanos(startTime.getNano()).getNano() * 0.001));

            shotsList.add(shot);

            shotCountMBean.incrementTotalShots();
            if (shot.isStatus()) {
                shotCountMBean.incrementHits();
            }

            shot = new Shot();

        } else {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            HttpServletResponse response = (HttpServletResponse) context.getResponse();
            response.setStatus(500);
        }
    }



    public void addThroughPlot() {
        String string_x = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("x");
        String string_y = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("y");
        String string_r = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("r");
        shot.setX(Double.parseDouble(string_x));
        shot.setY(Double.parseDouble(string_y));
        shot.setR(Double.parseDouble(string_r));
        add();
    }

    public List<Shot> getShotsList() {
        return shotsList;
    }

    public void clear() {
        shotsList.clear();
        shotCountMBean.clearTable();
    }

    public void setTimezone() {
        timezone = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("timezone"));
    }

    private int getTimezone() {
        return timezone;
    }

    public Shot getShot() {
        return shot;
    }

    public void setShotsList(LinkedList<Shot> shotsList) {
        this.shotsList = shotsList;
    }
}
