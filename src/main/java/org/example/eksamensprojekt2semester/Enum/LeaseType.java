package org.example.eksamensprojekt2semester.Enum;

public enum LeaseType {
        //Designet af tobias, lavet af frederik
        LIMITED("Limited"),
        UNLIMITED("Unlimited");

        private final String prettyprint;

        LeaseType(String prettyprint) {
            this.prettyprint = prettyprint;
        }

        @Override
        public String toString() {
            return prettyprint;
        }

        public static LeaseType fromString(String value) {
            for (LeaseType type : LeaseType.values()) {
                if (type.prettyprint.equalsIgnoreCase(value)) {
                    return type;
                }
            }
            return null;
        }
}
