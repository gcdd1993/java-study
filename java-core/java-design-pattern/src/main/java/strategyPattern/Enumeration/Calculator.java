package strategyPattern.Enumeration;

/**
 * @author: gaochen
 * Date: 2019/1/21
 */
public enum Calculator {
    /**
     * 加
     */
    ADD{
        @Override
        public double calculate(double d1, double d2) {
            return d1 + d2;
        }
    },
    /**
     * 减
     */
    SUB {
        @Override
        public double calculate(double d1, double d2) {
            return d1 - d2;
        }
    },
    /**
     * 乘
     */
    MULTY {
        @Override
        public double calculate(double d1, double d2) {
            return d1 * d2;
        }
    },
    /**
     * 除法
     */
    DIVIDE {
        @Override
        public double calculate(double d1, double d2) {
            return d1 / d2;
        }
    };

    public abstract double calculate(double d1,double d2);
}
