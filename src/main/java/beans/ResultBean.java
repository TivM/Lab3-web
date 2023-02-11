package beans;



import org.primefaces.event.SlideEndEvent;
import utils.DataBase;
import utils.HitChecker;
import utils.Result;
import utils.Validator;

import javax.annotation.PostConstruct;
//import javax.faces.bean.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.transaction.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@ManagedBean(name = "resultBean")
//@SessionScoped
public class ResultBean implements Serializable {
    private String xError = "";
    private String yError = "";
    private String rError = "";
    private int pointer5 = 0;
    private int pointer2 = 0;
    private int isPointer = 0;
    private Result newResult = new Result();
    private Result clickResult = new Result();
    private Validator validator = new Validator();
    private List<Result> resultList = new ArrayList<>();
    private List<Boolean> resultListForDraw = new ArrayList<>();
    private int isDrown;
    private DataBase dataBase;

    //Пофиксить баг, при изменении одного листа, меняется и другой, и сделать так чтобы точки меняли только цвет при
    //при изменении радиуса и не менялись факты попадания в таблице

//    @PostConstruct
//    public void postConstruct() {
//        resultList = dataBase.getResultsFromDB();
//        System.out.println("GET FROM DB");
//        System.out.println(resultList);
//    }



    public void addClick() throws HeuristicRollbackException, SystemException, HeuristicMixedException, NotSupportedException, RollbackException {
        System.out.println(clickResult);
        clickResult.setR(newResult.getR());
        makeClickErrors();

        System.out.println(clickResult);
        if (validator.checkR(clickResult) == "") {
            System.out.println("GOOD");
            coordinatesToValues(clickResult);
            makeResult(clickResult);
//            Добавляю только факт попадания или непопадания
            resultListForDraw.add(clickResult.isResult());

            resultList.add(clickResult);
            System.out.println(resultList);
            System.out.println("");
            dataBase.addResultToDB(clickResult);
            System.out.println("");
            clickResult = new Result();
            saveSubmitValues(newResult.getX(), newResult.getY(), newResult.getR());
        }
    }

    public int getIsDrown() {
        return isDrown;
    }

    public void setIsDrown(int isDrown) {
        this.isDrown = isDrown;
    }

    public void addResult() throws HeuristicRollbackException, SystemException, HeuristicMixedException, NotSupportedException, RollbackException {
        makeSubmitErrors();
        if ((newResult.getX() != null) && (newResult.getY() != null) && (newResult.getR() != null)) {
            if (validator.validate(newResult)) {
                makeResult(newResult);
//                Добавляю только факт попадания или непопадания
                resultListForDraw.add(newResult.isResult());
                resultList.add(newResult);
                dataBase.addResultToDB(newResult);

                saveSubmitValues(newResult.getX(), newResult.getY(), newResult.getR());
                System.out.println("addResultMethod");
            } else {
                newResult.setR(4.0f);
            }
        }
    }

    public void update() {
        isPointer = 1;
        isDrown = 1;
        resultList = dataBase.getResultsFromDB();
        if (newResult.getR() == null){

            newResult.setR(4f);
        }
        for (Result result2 : resultList) {
            //result1.setResult(false);
            System.out.println(result2.isResult());
            //Нужно проверить попадание исходя из нового радиуса
            result2.setResult(new HitChecker().isHit(result2.getX(), result2.getY(), newResult.getR()));

            System.out.println("GET FROM DB");
        }
    }

    public void makeClickErrors() {
        xError = "";
        yError = "";
        rError = validator.checkR(clickResult);
    }

    public void cleanTable(){
        resultList.clear();
    }


    public void coordinatesToValues(Result result) {
        result.setX(((float) ((result.getR() * (result.getX() - 150)) / 100) * (4.0f / result.getR())));
        result.setY((float) ((result.getR() * (150 - result.getY())) / 100) * (4.0f / result.getR()));
    }


    public void makeSubmitErrors() {
        xError = validator.checkX(newResult);
        yError = validator.checkY(newResult);
        rError = validator.checkR(newResult);
    }


    public void addCheck() throws HeuristicRollbackException, SystemException, HeuristicMixedException, NotSupportedException, RollbackException {
        isPointer = 1;
        if (newResult.getR()==null){
            makeClickErrors();
        }
        else if (validator.validateR(newResult)) {
            isDrown = 1;
            if (clickResult.getX() != null && clickResult.getY() != null) addClick();
            else addResult();
//            for (Result results: resultList){
//                new HitChecker().isHit(results.getX(), results.getY(), results.getR());
//            }
        } else {
            rError = validator.checkR(newResult);
            newResult.setR(4.0f);
        }

//        for (int i = 0; i<resultList.size();i++){
//            resultList.get(i).setResult(new HitChecker().isHit(resultList.get(i).getX(),
//                    resultList.get(i).getY(), newResult.getR()));
//
//
//
//        }

//        for (Result result2: resultList){
//            resultListForDraw.add(result2);
//        }

        System.out.println(resultList);
        System.out.println(resultListForDraw);
        System.out.println("x = " + newResult.getX());
        System.out.println("y = " + newResult.getY());
        System.out.println("r = " + newResult.getR());
    }


    public void saveSubmitValues(Float x, Float y, Float r) {
        newResult = new Result();
        newResult.setX(x);
        newResult.setY(y);
        newResult.setR(r);
    }

    public void makeResult(Result result) {
        result.setCurrentTime(new TimeBean().getTime());
        long start = System.nanoTime();
        result.setResult(new HitChecker().isHit(result.getX(), result.getY(), result.getR()));

        result.setExecutionTime((System.nanoTime() - start) / 1000);
        System.out.println("make result!");
    }

    public void defaultResult() {
        newResult.setX(null);
        newResult.setY(null);
        newResult.setR(null);
        clickResult.setX(null);
        clickResult.setY(null);
        clickResult.setR(null);
        isPointer = 0;
        xError = "";
        yError = "";
        rError = "";
        isDrown = 0;
        isPointer = 0;
        resultList.clear();
    }

    public void setterFotButton1(){
        newResult.setX(-3F);
    }
    public void setterFotButton2(){newResult.setX(-2F);}
    public void setterFotButton3(){newResult.setX(-1F);}
    public void setterFotButton4(){newResult.setX(0F);}
    public void setterFotButton5(){newResult.setX(1F);}
    public void setterFotButton6(){
        newResult.setX(2F);
    }
    public void setterFotButton7(){
        newResult.setX(3F);
    }
    public void setterFotButton8(){newResult.setX(4F);}
    public void setterFotButton9(){
        newResult.setX(5F);
    }

    //НАВЕСТИ МАРАФЕТ
    public void onSlideEnd(SlideEndEvent event) {
        //FacesMessage message = new FacesMessage("Slide Ended", "Value: " + event.getValue());
        //FacesContext.getCurrentInstance().addMessage(null, message);
        isDrown = 1;
        newResult.setR((float) event.getValue());

        for (int i = 0; i < resultList.size(); i++) {
            resultList.get(i).setResult(new HitChecker().isHit(resultList.get(i).getX(),
                    resultList.get(i).getY(), newResult.getR()));

        }
    }



    public String getxError() {
        return xError;
    }

    public void setxError(String xError) {
        this.xError = xError;
    }

    public String getyError() {
        return yError;
    }

    public void setyError(String yError) {
        this.yError = yError;
    }

    public String getrError() {
        return rError;
    }

    public void setrError(String rError) {
        this.rError = rError;
    }

    public int getPointer5() {
        return pointer5;
    }

    public void setPointer5(int pointer5) {
        this.pointer5 = pointer5;
    }

    public int getPointer2() {
        return pointer2;
    }

    public void setPointer2(int pointer2) {
        this.pointer2 = pointer2;
    }

    public int getIsPointer() {
        return isPointer;
    }

    public void setIsPointer(int isPointer) {
        this.isPointer = isPointer;
    }

    public Result getNewResult() {
        return newResult;
    }

    public void setNewResult(Result newResult) {
        this.newResult = newResult;
    }

    public Result getClickResult() {
        return clickResult;
    }

    public void setClickResult(Result clickResult) {
        this.clickResult = clickResult;
    }

    public List<Boolean> getResultListForDraw() {
        return resultListForDraw;
    }

    public void setResultListForDraw(List<Boolean> resultListForDraw) {
        this.resultListForDraw = resultListForDraw;
    }

    public Validator getValidator() {
        return validator;
    }

    public void setValidator(Validator validator) {
        this.validator = validator;
    }

    public List<Result> getResultList(){
        return resultList;
    }

    public void setResultList(List<Result> resultList) {
        this.resultList = resultList;
    }

    public DataBase getDataBase() {
        return dataBase;
    }

    public void setDataBase(DataBase dataBase) {
        this.dataBase = dataBase;
    }


}




