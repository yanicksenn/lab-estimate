package com.yanicksenn.labestimate.color;

public class StandardIlluminants {

    public static class Deg2 {
        public static final StandardIlluminant A = new StandardIlluminant("A", new XY(0.44757, 0.40745), 2856);
        public static final StandardIlluminant B = new StandardIlluminant("B", new XY(0.34842, 0.35161), 4874);
        public static final StandardIlluminant C = new StandardIlluminant("C", new XY(0.31006, 0.31616), 6774);
        public static final StandardIlluminant D50 = new StandardIlluminant("D50", new XY(0.34567, 0.35850), 5003);
        public static final StandardIlluminant D55 = new StandardIlluminant("D55", new XY(0.33242, 0.34743), 5503);
        public static final StandardIlluminant D65 = new StandardIlluminant("D65", new XY(0.31271, 0.32902), 6504);
        public static final StandardIlluminant D75 = new StandardIlluminant("D75", new XY(0.29902, 0.31485), 7504);
        public static final StandardIlluminant E = new StandardIlluminant("E", new XY(0.33333, 0.33333), 5454);
        public static final StandardIlluminant F1 = new StandardIlluminant("F1", new XY(0.31310, 0.33727), 6430);
        public static final StandardIlluminant F2 = new StandardIlluminant("F2", new XY(0.37208, 0.37529), 4230);
        public static final StandardIlluminant F3 = new StandardIlluminant("F3", new XY(0.40910, 0.39430), 3450);
        public static final StandardIlluminant F4 = new StandardIlluminant("F4", new XY(0.44018, 0.40329), 2940);
        public static final StandardIlluminant F5 = new StandardIlluminant("F5", new XY(0.31379, 0.34531), 6350);
        public static final StandardIlluminant F6 = new StandardIlluminant("F6", new XY(0.37790, 0.38835), 4150);
        public static final StandardIlluminant F7 = new StandardIlluminant("F7", new XY(0.31292, 0.32933), 6500);
        public static final StandardIlluminant F8 = new StandardIlluminant("F8", new XY(0.34588, 0.35875), 5000);
        public static final StandardIlluminant F9 = new StandardIlluminant("F9", new XY(0.37417, 0.37281), 4150);
        public static final StandardIlluminant F10 = new StandardIlluminant("F10", new XY(0.34609, 0.35986), 5000);
        public static final StandardIlluminant F11 = new StandardIlluminant("F11", new XY(0.38052, 0.37713), 4000);
        public static final StandardIlluminant F12 = new StandardIlluminant("F12", new XY(0.43695, 0.40441), 3000);

        private Deg2() {
            throw new AssertionError();
        }
    }

    public static class Deg10 {
        public static final StandardIlluminant A = new StandardIlluminant("A", new XY(0.45117, 0.40594), 2856);
        public static final StandardIlluminant B = new StandardIlluminant("B", new XY(0.34980, 0.35270), 4874);
        public static final StandardIlluminant C = new StandardIlluminant("C", new XY(0.31039, 0.31905), 6774);
        public static final StandardIlluminant D50 = new StandardIlluminant("D50", new XY(0.34773, 0.35952), 5003);
        public static final StandardIlluminant D55 = new StandardIlluminant("D55", new XY(0.33411, 0.34877), 5503);
        public static final StandardIlluminant D65 = new StandardIlluminant("D65", new XY(0.31382, 0.33100), 6504);
        public static final StandardIlluminant D75 = new StandardIlluminant("D75", new XY(0.29968, 0.31740), 7504);
        public static final StandardIlluminant E = new StandardIlluminant("E", new XY(0.33333, 0.33333), 5454);
        public static final StandardIlluminant F1 = new StandardIlluminant("F1", new XY(0.31811, 0.33559), 6430);
        public static final StandardIlluminant F2 = new StandardIlluminant("F2", new XY(0.37925, 0.36733), 4230);
        public static final StandardIlluminant F3 = new StandardIlluminant("F3", new XY(0.41761, 0.38324), 3450);
        public static final StandardIlluminant F4 = new StandardIlluminant("F4", new XY(0.44920, 0.39074), 2940);
        public static final StandardIlluminant F5 = new StandardIlluminant("F5", new XY(0.31975, 0.34246), 6350);
        public static final StandardIlluminant F6 = new StandardIlluminant("F6", new XY(0.38660, 0.37847), 4150);
        public static final StandardIlluminant F7 = new StandardIlluminant("F7", new XY(0.31569, 0.32960), 6500);
        public static final StandardIlluminant F8 = new StandardIlluminant("F8", new XY(0.34902, 0.35939), 5000);
        public static final StandardIlluminant F9 = new StandardIlluminant("F9", new XY(0.37829, 0.37045), 4150);
        public static final StandardIlluminant F10 = new StandardIlluminant("F10", new XY(0.35090, 0.35444), 5000);
        public static final StandardIlluminant F11 = new StandardIlluminant("F11", new XY(0.38541, 0.37123), 4000);
        public static final StandardIlluminant F12 = new StandardIlluminant("F12", new XY(0.44256, 0.39717), 3000);

        private Deg10() {
            throw new AssertionError();
        }
    }

    private StandardIlluminants() {
        throw new AssertionError();
    }
}
