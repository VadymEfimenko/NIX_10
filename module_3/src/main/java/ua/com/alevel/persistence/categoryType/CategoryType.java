package ua.com.alevel.persistence.categoryType;

public enum CategoryType {

    COMMUNICATIONS("-"), BILLS("-"), HOUSEHOLD_EXPENSES("-"),
    FOOD("-"), OTHER_OUTCOME("-"),
    SALARY("+"), OTHER_INCOME("+");

    private String isIncome;

    CategoryType(String isIncome) {
        this.isIncome = isIncome;
    }

    public String getIsIncome() {
        return isIncome;
    }
}
