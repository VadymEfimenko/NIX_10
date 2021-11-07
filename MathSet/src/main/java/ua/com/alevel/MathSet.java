package ua.com.alevel;

import java.util.Arrays;

public class MathSet<T extends Number> {
    static int fullness;
    public int capacity = 10;
    Number[] mathSet;

    public MathSet() {
        mathSet = new Number[capacity];
    }

    public MathSet(int capacity) {
        this.mathSet = new Number[capacity];
    }

    public MathSet(Number[] numbers) {
        this.mathSet = numbers;
    }

    public MathSet(Number[]... numbers) {
        int length = 0;
        int mathSetInd = 0;
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                if (numbers[i][j] != null)
                    length++;
            }
        }
        this.mathSet = new Number[length];
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers[i].length; j++) {
                if (numbers[i][j] != null)
                    mathSet[mathSetInd] = numbers[i][j];
                mathSetInd++;
            }
        }
    }

    public MathSet(MathSet numbers) {
        mathSet = numbers.mathSet;
    }

    public MathSet(MathSet... numbers) {

    }

    public static int getFullness(MathSet ms) {
        fullness = 0;
        for (int i = 0; i < ms.mathSet.length; i++) {
            if (ms.mathSet[i] != null) {
                fullness++;
            }
        }
        return fullness;
    }

    public void add(Number n) {
        fullness = 0;
        for (int i = 0; i < mathSet.length; i++) {
            if (mathSet[i] != null) {
                fullness++;
            }
        }
        if (fullness == mathSet.length) {
            Number[] temp = new Number[mathSet.length * 3 / 2 + 1];
            for (int i = 0; i < mathSet.length; i++) {
                temp[i] = mathSet[i];
            }
            mathSet = temp;
        }
        if (!contains(n))
            mathSet[fullness] = n;
    }

    public void add(Number... n) {
        for (int i = 0; i < n.length; i++) {
            add(n[i]);
        }
    }

    public void join(MathSet ms) {
        removeNull(ms);
        for (int i = 0; i < ms.mathSet.length; i++) {
            add(ms.mathSet[i]);
        }
    }

    public void join(MathSet... ms) {
        for (int i = 0; i < ms.length; i++) {
            this.join(ms[i]);
        }
    }

    public void sortDesc() {
        Number temp;
        for (int i = 0; i < mathSet.length; i++) {
            for (int j = i + 1; j < mathSet.length; j++) {
                if (mathSet[i] != null && mathSet[j] != null) {
                    if (mathSet[i].doubleValue() < mathSet[j].doubleValue()) {
                        temp = mathSet[i];
                        mathSet[i] = mathSet[j];
                        mathSet[j] = temp;
                    }
                }
            }
        }
    }

    public void sortDesc(int firstIndex, int lastIndex) {
        Number temp;
        for (int i = firstIndex; i <= lastIndex; i++) {
            if (i >= 0 && lastIndex <= mathSet.length)
                for (int j = i + 1; j <= lastIndex; j++) {
                    if (mathSet[i] != null && mathSet[j] != null) {
                        if (mathSet[i].doubleValue() < mathSet[j].doubleValue()) {
                            temp = mathSet[i];
                            mathSet[i] = mathSet[j];
                            mathSet[j] = temp;
                        }
                    }
                }
        }
    }

    public void sortDesc(Number value) {
        sortDesc(getIndex(value), mathSet.length - 1);
    }

    public void sortAsc() {
        Number temp;
        for (int i = 0; i < mathSet.length; i++) {
            for (int j = i + 1; j < mathSet.length; j++) {
                if (mathSet[i] != null && mathSet[j] != null) {
                    if (mathSet[i].doubleValue() > mathSet[j].doubleValue()) {
                        temp = mathSet[i];
                        mathSet[i] = mathSet[j];
                        mathSet[j] = temp;
                    }
                }
            }
        }
    }

    public void sortAsc(int firstIndex, int lastIndex) {
        Number temp;
        for (int i = firstIndex; i <= lastIndex; i++) {
            if (i >= 0 && lastIndex <= mathSet.length)
                for (int j = i + 1; j <= lastIndex; j++) {
                    if (mathSet[i] != null && mathSet[j] != null) {
                        if (mathSet[i].doubleValue() > mathSet[j].doubleValue()) {
                            temp = mathSet[i];
                            mathSet[i] = mathSet[j];
                            mathSet[j] = temp;
                        }
                    }
                }
        }
    }

    public void sortAsc(Number value) {
        sortAsc(getIndex(value), mathSet.length - 1);
    }

    public void intersection(MathSet ms) {
        MathSet temp = new MathSet();
        for (int i = 0; i < mathSet.length; i++) {
            for (int j = 0; j < ms.mathSet.length; j++) {
                if (mathSet[i] != null) {
                    if (mathSet[i] == ms.mathSet[j]) {
                        temp.add(mathSet[i]);
                    }
                }
            }
        }
        mathSet = temp.mathSet;
    }

    public void intersection(MathSet... ms) {
        for (int i = 0; i < ms.length; i++) {
            this.intersection(ms[i]);
        }
    }

    public Number get(int index) {
        return mathSet[index];
    }

    public Number getMax() {
        this.sortDesc();
        return this.mathSet[0];
    }

    public Number getMin() {
        this.sortAsc();
        return this.mathSet[0];
    }

    public Number getAverage() {
        Number sum = 0;
        for (int i = 0; i <= getFullness(this); i++) {
            if (mathSet[i] != null)
                sum = sum.doubleValue() + mathSet[i].doubleValue();
        }
        return sum.doubleValue() / getFullness(this);
    }

    public Number getMedian() {
        this.sortAsc();
        double median = 0;
        if (getFullness(this) % 2 == 0) {
            median = (mathSet[getFullness(this) / 2 - 1].doubleValue() + mathSet[getFullness(this) / 2].doubleValue()) / 2;

        } else if (getFullness(this) % 2 == 1) {
            median = mathSet[getFullness(this) / 2].doubleValue();
        }
        return median;
    }

    public int getIndex(Number value) {
        int index = -1;
        if (this.contains(value)) {
            for (int i = 0; i < mathSet.length; i++) {
                if (value == mathSet[i]) {
                    index = i;
                }
            }
        }
        return index;
    }

    public Number[] toArray() {
        Number[] temp = new Number[getFullness(this)];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = mathSet[i];
        }
        return temp;
    }

    public Number[] toArray(int firstIndex, int lastIndex) {
        Number[] temp = new Number[lastIndex - firstIndex + 1];
        int tempIndex = 0;
        for (int i = firstIndex; i <= lastIndex; i++) {
            temp[tempIndex] = mathSet[i];
            tempIndex++;
        }
        return temp;
    }

    public MathSet cut(int firstIndex, int lastIndex) {
        MathSet temp = new MathSet(lastIndex - firstIndex + 1);
        for (int i = 0; i < temp.mathSet.length; i++) {
            temp.mathSet[i] = this.mathSet[firstIndex];
            firstIndex++;
        }
        return temp;
    }

    public void clear() {
        for (int i = 0; i < mathSet.length; i++) {
            mathSet[i] = null;
        }
        removeNull(this);
    }

    public void clear(Number[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = null;
        }

    }

    public Number[] removeNull(MathSet ms) {
        MathSet mathSetNoNull = new MathSet(getFullness(ms));
        for (int i = 0; i < ms.mathSet.length; i++) {
            if (ms.mathSet[i] != null) {
                mathSetNoNull.add(ms.mathSet[i]);
            }
        }
        return mathSetNoNull.toArray();
    }

    public boolean contains(Number n) {
        for (Number num : mathSet) {
            if (num == n) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "MathSet{" +
                "mathSet=" + Arrays.toString(mathSet) +
                '}';
    }
}
