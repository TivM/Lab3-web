package utils;
//Разобраться с Entity + persistence.xml!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
@Entity
@Table(name = "Result")
public class Result implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "session_id", nullable = false)
    //@Basic
    private Integer session_id ;
    @Column(name = "x", nullable = false)
    //@Basic
    private Float x;
    @Column(name = "y", nullable = false)
    //@Basic
    private Float y;
    @Column(name = "r", nullable = false)
    //@Basic
    private Float r;
    @Column(name = "currentTime", nullable = false)
    //@Basic
    private String currentTime;
    @Column(name = "executionTime", nullable = false)
    //@Basic
    private Long executionTime;
    @Column(name = "result", nullable = false)
    //@Basic
    private Boolean result;




//    public Result() {
//        this.r=2.0f;
//    }

//    public String getSession_id() {
//        return session_id;
//    }

//    public void setSession_id(String session_id) {
//        this.session_id = session_id;
//    }


    public Integer getSession_id() {
        return session_id;
    }

    public void setSession_id(Integer session_id) {
        this.session_id = 2;
    }

    public Float getX() {
        return x;
    }

    public void setX(Float x) {
        this.x = x;
    }


    @Override
    public String toString() {
        return "Result{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", currentTime='" + currentTime + '\'' +
                ", executionTime=" + executionTime +
                ", result=" + result +
                '}';
    }

    public Float getY() {
        return y;
    }

    public void setY(Float y) {
        this.y = y;
    }

    public Float getR()
    {
        return r;
    }

    public void setR(Float r) {
        this.r = r;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result1 = (Result) o;
        return executionTime == result1.executionTime && result == result1.result && Objects.equals(x, result1.x)
                && Objects.equals(y, result1.y) && Objects.equals(r, result1.r)
                && Objects.equals(currentTime, result1.currentTime);
    }





}
