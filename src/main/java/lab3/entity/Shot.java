package lab3.entity;


import javax.persistence.*;


@Entity
@Table(name = "shot")
public class Shot{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "x", nullable = false)
    private double x;

    @Column(name = "y", nullable = false)
    private double y;
    @Column(name = "r", nullable = false)
    private double r;

    @Column(name = "status", nullable = false)
    private boolean status;

    @Column(name = "current_time", nullable = false)
    private String currentTime;

    @Column(name = "script_time", nullable = false)
    private long scriptTime;

    public Integer getID() {
        return id;
    }

    public void setID(Integer id) {
        this.id = id;
    }
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }


    private boolean r1 = false;

    private boolean r2 = false;

    private boolean r3 = false;

    private boolean r4 = false;

    private boolean r5 = false;



    public boolean getR1() {
        return r1;
    }

    public boolean getR2() {
        return r2;
    }

    public boolean getR3() {
        return r3;
    }

    public boolean getR4() {
        return r4;
    }

    public boolean getR5() {
        return r5;
    }

    public double getR() throws NumberFormatException{
        if (r1) {
            return 1;
        } else if (r2) {
            return 2;
        } else if (r3) {
            return 3;
        } else if (r4) {
            return 4;
        } else if (r5) {
            return 5;
        }else{
            return r;

        }
    }

    public void setR(double r) throws NumberFormatException{
        if (r1) {
            this.r = r;
        } else if (r2) {
            this.r = r;
        } else if (r3) {
            this.r = r;
        } else if (r4) {
            this.r = r;
        } else if (r5) {
            this.r = r;
        } else {
            this.r = r;

        }


    }

    public void setR1(boolean value) {
        r1 = value;
    }

    public void setR2(boolean value) {
        r2 = value;
    }

    public void setR3(boolean value) {
        r3 = value;
    }

    public void setR4(boolean value) {
        r4 = value;
    }

    public void setR5(boolean value) {
        r5 = value;
    }


    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getCurrentTime() {
        return currentTime;
    }
    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }
    public long getScriptTime() {
        return scriptTime;
    }
    public void setScriptTime(long scriptTime) {
        this.scriptTime = scriptTime;
    }



}
