package myapps.wycoco.com.ethelonstartup.Models;

/**
 * Created by dell on 8/28/2017.
 */

public class EvaluationCriteria {
    private String criteriaName;
    private int criteriaRating;

    public EvaluationCriteria() {
    }

    public EvaluationCriteria(String criteriaName, int criteriaRating) {
        this.criteriaName = criteriaName;
        this.criteriaRating = criteriaRating;
    }

    public String getCriteriaName() {
        return criteriaName;
    }

    public void setCriteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
    }

    public int getCriteriaRating() {
        return criteriaRating;
    }

    public void setCriteriaRating(int criteriaRating) {
        this.criteriaRating = criteriaRating;
    }
}
